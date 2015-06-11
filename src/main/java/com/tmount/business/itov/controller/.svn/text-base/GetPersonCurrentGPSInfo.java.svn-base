package com.tmount.business.itov.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Decoder;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MapUtil;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetPersonCurrentGPSInfo extends ControllerBase {
	String json = "";

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// 根据应用ID号查询获得对应设备列表信息
		Map<String, String> params = new HashMap<String, String>();
		String deviceuid = new String(ParamData.getString(
				requestParam.getBodyNode(), "deviceuid"));
		String mapType = new String(ParamData.getString(
				requestParam.getBodyNode(), "mapType"));// 0—百度 1—图吧 2—高德
		params.put("deviceuid", deviceuid);
		json = GetLaunchInfo.getCurrentGPSInfo(params);
		json = URLDecoder.decode(json, "utf-8");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		if (null != jsonNode) {
			String status = jsonNode.get("Status").asText();
			if ("1".equals(status)) {
				JsonNode device_status_json = jsonNode.get("Data");
				String Latitude = device_status_json.get("Latitude").asText();// 纬度
				String Longitude = device_status_json.get("Longitude")
						.asText();// 经度
				String Direction = device_status_json.get("Direction")
						.asText();

				// 调用百度地图api
				JsonNode jsonNodeMap = MapUtil.getMap(Longitude, Latitude);
				Iterator it = jsonNodeMap.iterator();
				while (it.hasNext()) {
					JsonNode js = (JsonNode) it.next();
					String LatitudeBase64 = js.get("x").textValue();
					String LongitudeBase64 = js.get("y").textValue();
					// 解析base64串
					String LongitudeStr = new String(
							new BASE64Decoder().decodeBuffer(LatitudeBase64));
					String LatitudeStr = new String(
							new BASE64Decoder().decodeBuffer(LongitudeBase64));
					responseBodyJson.writeStringField("Latitude", LatitudeStr);// 纬度
					responseBodyJson
							.writeStringField("Longitude", LongitudeStr);// 经度
					responseBodyJson.writeStringField("Direction", Direction);// 方向
					// 调用百度地图api，获取逆地理名称
					String param = "";
					param += "&output=json&pois=1&location=";
					param += LatitudeStr + "," + LongitudeStr;
					System.out.println("param" + param);
					String json_map = MapUtil.getBaiduInverseGeographic(param);
					json_map = URLDecoder.decode(json_map, "utf-8");
					System.out.println("retJson=" + json_map);
					// 解析json
					String retMsg = "";
					if (StringUtils.isNotEmpty(json_map)) {
						ObjectMapper mappers = new ObjectMapper();
						JsonNode jsonNodeResver = mappers.readTree(json_map);
						JsonNode comment = jsonNodeResver.get("result").get(
								"addressComponent");
						retMsg = comment.get("province").textValue()
								+ comment.get("city").textValue()
								+ comment.get("district").textValue()
								+ comment.get("street").textValue()
								+ comment.get("street_number").textValue();
					}
					responseBodyJson.writeStringField("Location", retMsg);

				}
			}
		}
	}

	@RequestMapping(value = "currentgpsinfoperson.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);

	}

}
