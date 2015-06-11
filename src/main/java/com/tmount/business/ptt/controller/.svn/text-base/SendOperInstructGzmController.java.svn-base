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
 * 通用发送指令
 * @author dell
 *
 */
@Controller
public class SendOperInstructGzmController extends ControllerBase {
	static Logger logger = Logger.getLogger(startService.class.getName());
	@RequestMapping(value = "sendOperInstructGzmController.send")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException,	JsonGenerationException, IOException {
		
		String IMEI = ParamData.getString(requestParam.getBodyNode(), "IMEI");//  IMEI号
		String sendConent = ParamData.getString(requestParam.getBodyNode(), "sendConent");//  发送类型 
        //6-故障码 
		String Msgtype = ParamData.getString(requestParam.getBodyNode(), "Msgtype");//信息类型 
		String Gzmtype = ParamData.getString(requestParam.getBodyNode(), "Gzmtype");//故障码类别   0:  1:警告级别  3:最高级别
        String strTitle="故障码推送";
        JsonFactory jfactory = new JsonFactory();
		StringWriter jsonWrite = new StringWriter();
		JsonGenerator json = jfactory.createJsonGenerator(jsonWrite);
		json.writeStartObject(); 
		json.writeFieldName("body");
		json.writeStartObject(); 
        json.writeStringField("Msgtype",Msgtype);//信息类型 0-广告 1-导航 2-音乐 3-视频   4-路况 5-新闻  6-故障码  7-下发指令  8-指令回馈  9--得到车门状态  10--返回车门状态    11-得到车窗状态    12--返回车窗状态
        json.writeStringField("Title","");//
        json.writeStringField("Imageurl","");//
        json.writeStringField("Noticeurl","");//
        json.writeStringField("Content",sendConent);//
        json.writeStringField("strType", "");
        json.writeStringField("strNum", Gzmtype);
		json.writeEndObject(); 
		json.writeEndObject(); 
		json.close();
		String bodyResponseJson = jsonWrite.toString();
		logger.info("bodyResponseJson:~~~~~~~~~~~~~bodyResponseJson~~~~~~~~~~~~~~~~~~"+bodyResponseJson);
		String retStr="";
        retStr= JPushClientExample.SendMsg(IMEI,strTitle,bodyResponseJson);
        logger.info("retStr:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+retStr);
    	responseBodyJson.writeStringField("flag:", retStr);
    	responseBodyJson.writeStringField("ret", retStr);
	}
}
