package com.tmount.business.ptt.controller;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.exception.ShopException;
import com.tmount.jpush.examples.JPushClientExample;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 发送指令  通知用户下线
 * 
 * @author dell
 * 
 */
@Controller
public class SendOperInstructForOfflineController extends ControllerBase {
	static Logger logger = Logger.getLogger(startService.class.getName());

	@RequestMapping(value = "SendOperInstructForOfflineController.send")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String strUser = ParamData.getString(requestParam.getBodyNode(), "username");// 用户名
		String Msgtype = ParamData.getString(requestParam.getBodyNode(), "Msgtype");// 信息类型
		String Imei = ParamData.getString(requestParam.getBodyNode(), "imei");// IMEI
		String strTitle = "";
		JsonFactory jfactory = new JsonFactory();
		StringWriter jsonWrite = new StringWriter();
		JsonGenerator json = jfactory.createJsonGenerator(jsonWrite);
		json.writeStartObject();
		json.writeFieldName("body");
		json.writeStartObject();
		json.writeStringField("Msgtype", Msgtype);
		json.writeStringField("Title", "");//
		json.writeStringField("Imageurl", "");//
		json.writeStringField("Noticeurl", "");//
		json.writeStringField("Content", strUser);//
		json.writeStringField("strType",Imei);
		json.writeStringField("strNum", "");
		json.writeEndObject();
		json.writeEndObject();
		json.close();
		String bodyResponseJson = jsonWrite.toString();
		logger.info("bodyResponseJson:~~~~~~~~~~~~~bodyResponseJson~~~~~~~~~~~~~~~~~~" + bodyResponseJson);
		String retStr = "";
		retStr = JPushClientExample.SendMsg(strUser, strTitle, bodyResponseJson);
		logger.info("retStr:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + retStr);
		logger.info("strUser:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + strUser);
		logger.info("****************Msgtype："+Msgtype+"****************");
		responseBodyJson.writeStringField("flag:", retStr);
		responseBodyJson.writeStringField("ret", retStr);
	}
}
