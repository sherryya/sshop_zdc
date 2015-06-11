package com.tmount.util;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopParamErrorBundle;
import com.tmount.config.AppPropertiesConfig;
import com.tmount.exception.ShopException;
import com.tmount.exception.ShopParamException;
import com.tmount.system.MD5;
import com.tmount.system.ZipUtil;
import com.tmount.system.dal.DataSourceContextHolder;

/**
 * 打包生成servlet的JSON格式数据
 * 
 * @author lugz
 * 
 */
public abstract class ControllerBaseBreakRules {
	@Autowired
	private AppPropertiesConfig appPropertiesConfig;

	/**
	 * 
	 * @param node
	 * @return 响应的字符串。
	 */
	protected abstract String doService(RequestParam requestParam)
			throws ShopException, JsonGenerationException, IOException;

	protected abstract ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException;

	public abstract void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * 路由函数。
	 */
	private void routing(RequestParam requestParam) {
		// 等待实现。
	}

	/**
	 * 验证请求参数是否合法。
	 * 
	 * @throws ShopParamException
	 */
	private void validRequestParam(RequestDataHeader requestDataHeader,
			String gbkStr) throws ShopParamException {
		int bodyIndex = gbkStr.indexOf("\"body\":{");
		if (bodyIndex == -1) {
			throw new ShopParamException(ShopParamErrorBundle.LOST_IN_PARAM,
					new Object[] { "body" });
		}
		String bodyStr = gbkStr.substring(bodyIndex + 7, gbkStr.length() - 1); // 只验证body里两个大括号中的内容，包括大括号。
		if (appPropertiesConfig.getAppKeyProps().getProperty(
				requestDataHeader.getAppKey()) == null) {
			throw new ShopParamException(
					ShopParamErrorBundle.INVALID_PARAM_VALUE, new Object[] {
							"app_key", requestDataHeader.getAppKey() });
		}

		System.out.println("body--"
				+ bodyStr
				+ appPropertiesConfig.getAppKeyProps().getProperty(
						requestDataHeader.getAppKey()) + "--"
				+ requestDataHeader.getSign());
		String signValid = MD5.getMD5(bodyStr
				+ appPropertiesConfig.getAppKeyProps().getProperty(
						requestDataHeader.getAppKey()));
		if (!requestDataHeader.getSign().equals(signValid)) {
			throw new ShopParamException(
					ShopParamErrorBundle.INVALID_SIGN_PARAM,
					new Object[] { requestDataHeader.getSign() });
		}
	}

	public void sendData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long startTime = System.currentTimeMillis();
		// 清除数据源的设置。
		DataSourceContextHolder.clearDataSource();

		Logger logger = Logger.getLogger(ControllerBase.class);
		String requestDispatcherPath = "";
		int requestDispatcherPathStartPos = request.getRequestURI()
				.lastIndexOf("/");
		if (requestDispatcherPathStartPos != -1) {
			requestDispatcherPath = request.getRequestURI().substring(
					requestDispatcherPathStartPos + 1);
		}

		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat(ParamData.DATE_FORMAT);
		ReponseDataHeader reponseDataHeader = new ReponseDataHeader();
		RequestDataHeader requestDataHeader = new RequestDataHeader();

		int contentLength = request.getContentLength();
		String responseJson = "";
		if (contentLength > 0) {
			try {
				byte[] bufIn = new byte[contentLength];
				request.getInputStream().read(bufIn);
				byte[] utf8Str = ZipUtil.decompress(bufIn);

				// 得到GBK编码格式的字符串:
				String gbkStr = new String(utf8Str, "UTF-8");

				System.out.println("request utf8Str:" + new String(utf8Str));
				System.out.println("request gbkStr:" + gbkStr);

				JsonNode jsonNode = mapper.readTree(gbkStr);
				JsonNode headNode = jsonNode.get("head");
				JsonNode bodyNode = jsonNode.get("body");

				requestDataHeader.init(headNode);
				validRequestParam(requestDataHeader, gbkStr);
				RequestParam requestParam = new RequestParam(requestDataHeader,
						bodyNode, sdf);
				routing(requestParam);

				// //////
				String bodyStr = doService(requestParam);
				String body = "";
				if (StringUtils.isNotEmpty(bodyStr)) {
					bodyStr = bodyStr.substring(1,bodyStr.length()-1);
					String[] arr = bodyStr.split(",");
					if("e".equals((arr[0].replace("\"", "")))){
						reponseDataHeader.setErrorCode("1");
					}else{
						reponseDataHeader.setErrorCode("0");
					}
					reponseDataHeader.setErrorMessage(arr[1].replaceAll("\"", ""));
					body = arr[2];
					
				}

				String bodyResponseJson = "{\"body\":{\"content\":" + body + "}}";
				System.out.println("bodyResponseJson:" +bodyResponseJson);
				JsonFactory jfactory = new JsonFactory();

				reponseDataHeader
						.setCommandId(requestDataHeader.getCommandId());
				reponseDataHeader
						.setTimestamp(requestDataHeader.getTimestamp());
				reponseDataHeader.setVer(requestDataHeader.getVer());
				reponseDataHeader.setSignMethod(requestDataHeader
						.getSignMethod());
				reponseDataHeader.setAppKey(requestDataHeader.getAppKey());
				reponseDataHeader.setSign(MD5.getMD5(bodyResponseJson
						.substring(8, bodyResponseJson.length() - 1)
						+ appPropertiesConfig.getAppKeyProps().getProperty(
								requestDataHeader.getAppKey())));

				StringWriter writerHeader = new StringWriter();
				JsonGenerator responseHeaderJson = jfactory
						.createJsonGenerator(writerHeader);
				responseHeaderJson.writeStartObject(); // {
				reponseDataHeader.toJsonStr(responseHeaderJson);
				responseHeaderJson.writeEndObject(); // }
				responseHeaderJson.close();
				String headerJson = writerHeader.toString();
				responseJson = headerJson.substring(0, headerJson.length() - 1)
						+ ","
						+ bodyResponseJson.substring(1,
								bodyResponseJson.length() - 1) + "}";
				System.out.println("itov"+responseJson);

			} catch (ShopException e) {
				e.printStackTrace();
				reponseDataHeader
						.setCommandId(requestDataHeader.getCommandId());
				reponseDataHeader
						.setTimestamp(requestDataHeader.getTimestamp());
				reponseDataHeader.setErrorCode(e.getFullErrorCode());
				reponseDataHeader.setErrorMessage(e.getErrorMessage());

				JsonFactory jfactory = new JsonFactory();
				StringWriter writer = new StringWriter();
				JsonGenerator responseBodyJson = jfactory
						.createJsonGenerator(writer);

				responseBodyJson.writeStartObject(); // {
				reponseDataHeader.toJsonStr(responseBodyJson);
				responseBodyJson.writeFieldName("body");
				responseBodyJson.writeStartObject(); // {
				responseBodyJson.writeEndObject(); // }
				responseBodyJson.writeEndObject(); // }
				responseBodyJson.close();
				responseJson = writer.toString();
			} catch (Exception e) {
				e.printStackTrace();
				reponseDataHeader
						.setCommandId(requestDataHeader.getCommandId());
				reponseDataHeader
						.setTimestamp(requestDataHeader.getTimestamp());
				reponseDataHeader.setErrorCode("100");
				reponseDataHeader
						.setErrorMessage(e.getMessage() == null ? "未知异常" : e
								.getMessage());
				JsonFactory jfactory = new JsonFactory();
				StringWriter writer = new StringWriter();
				JsonGenerator responseBodyJson = jfactory
						.createJsonGenerator(writer);

				responseBodyJson.writeStartObject(); // {
				reponseDataHeader.toJsonStr(responseBodyJson);
				responseBodyJson.writeFieldName("body");
				responseBodyJson.writeStartObject(); // {
				responseBodyJson.writeEndObject(); // }
				responseBodyJson.writeEndObject(); // }
				responseBodyJson.close();
				responseJson = writer.toString();
			}
		} else {
			reponseDataHeader.setCommandId(requestDataHeader.getCommandId());
			reponseDataHeader.setTimestamp(requestDataHeader.getTimestamp());
			reponseDataHeader.setErrorCode("100");
			reponseDataHeader.setErrorMessage("传入的请求报文错误。");
			JsonFactory jfactory = new JsonFactory();
			StringWriter writer = new StringWriter();
			JsonGenerator responseBodyJson = jfactory
					.createJsonGenerator(writer);

			responseBodyJson.writeStartObject(); // {
			reponseDataHeader.toJsonStr(responseBodyJson);
			responseBodyJson.writeFieldName("body");
			responseBodyJson.writeStartObject(); // {
			responseBodyJson.writeEndObject(); // }
			responseBodyJson.writeEndObject(); // }
			responseBodyJson.close();
			responseJson = writer.toString();
		}
		// 得到UTF-8编码格式的字符串:
		byte[] utf8Str = responseJson.getBytes("UTF-8");

		byte[] zipStr = ZipUtil.compress(utf8Str);
		System.out.println("send gbkStr:" + responseJson.toString());
		System.out.println("send utf8Str:" + new String(utf8Str));
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setContentLength(zipStr.length);
		response.getOutputStream().write(zipStr);
		response.flushBuffer();

		Long endTime = System.currentTimeMillis();
		if (!requestDispatcherPath.equals("")) {
			logger.debug("requestDispatcherPath:" + requestDispatcherPath + " "
					+ endTime + " " + (endTime - startTime));
		}
	}
}