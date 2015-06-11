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

@Controller
public class SendMsgByJPush extends ControllerBase {
	/*
	 * @Autowired private DriverService driverService;
	 */
	static Logger logger = Logger.getLogger(startService.class.getName());
	@RequestMapping(value = "sendMsgByJPush.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException,	JsonGenerationException, IOException {
		
		String strlatlon=new String(ParamData.getString(requestParam.getBodyNode(), "strlatlon"));//经纬度
        String strUser= new String(ParamData.getString(requestParam.getBodyNode(), "strUser"));//用户名 推送标识
        String strTitle=new String(ParamData.getString(requestParam.getBodyNode(), "strTitle"));// 标题 
        String mMessage = new String(ParamData.getString(requestParam.getBodyNode(), "mMessage"));//途经点信息含顺序，每一个用$隔开，然后用","隔开
        String Msgtype = new String(ParamData.getString(requestParam.getBodyNode(), "Msgtype"));
        //信息类型 0-广告 1-导航 2-音乐 3-视频   4-路况 5-新闻  6-故障码  7-下发指令          100  违章推送         101  互踢 用户     
        String Content = new String(ParamData.getString(requestParam.getBodyNode(), "Content"));//内容
        String strType = new String(ParamData.getString(requestParam.getBodyNode(), "strType"));//导航类型 0-最快路线 1-最短路线 2-避开高速 3-步行
        String strNum=new String(ParamData.getString(requestParam.getBodyNode(), "strNum"));// 随机数
        JsonFactory jfactory = new JsonFactory();
		StringWriter jsonWrite = new StringWriter();
		JsonGenerator json = jfactory.createJsonGenerator(jsonWrite);
		json.writeStartObject(); 
		json.writeFieldName("body");
		json.writeStartObject(); 
        if(strlatlon!=null && (!"".equals(strlatlon))){
        	String[] latlon = strlatlon.split(",");
    		json.writeStringField("Latitude",latlon[0]);//目标地纬度（已转换成对应的地图的经纬度）;
    		json.writeStringField("Longitude",latlon[1]);//目标地纬度（已转换成对应的地图的经纬度）;
        }
        responseBodyJson.writeArrayFieldStart("Data");
        if(mMessage!=null && (!"".equals(mMessage))){
        	String[] mMessageArray = mMessage.split("$");
        	for(int i = 0;i<mMessageArray.length;i++){
        		String[] detail = mMessageArray[i].split(",");
        		json.writeStringField("Mlatitude",detail[0]);//途径地纬度（已转换成对应的地图的经纬度）;
        		json.writeStringField("Mlongitude",detail[1]);//途径地经度（已转换成对应的地图的经纬度）;
        		json.writeStringField("Order",detail[2]);//途径地顺序
        	}
        }
        responseBodyJson.writeEndArray();
        json.writeStringField("Msgtype",Msgtype);//信息类型 0-广告 1-导航 2-音乐 3-视频   4-路况 5-新闻  6-故障码 
        json.writeStringField("Title",strTitle);//标题
        json.writeStringField("Imageurl","");//图片url
        json.writeStringField("Noticeurl","");//广告url
        json.writeStringField("Content",Content);//内容
        json.writeStringField("strType", strType);//导航类型 0-最快路线 1-最短路线 2-避开高速 3-步行
        json.writeStringField("strNum", strNum);
		json.writeEndObject(); 
		json.writeEndObject(); 
		json.close();
		String bodyResponseJson = jsonWrite.toString();
		logger.info("bodyResponseJson:~~~~~~~~~~~~~bodyResponseJson~~~~~~~~~~~~~~~~~~"+bodyResponseJson);
		String retStr="";
		if(Msgtype.equals("1")||Msgtype.equals("2")||Msgtype.equals("3")||Msgtype.equals("6"))
		{
          retStr= JPushClientExample.SendMsg(strUser,strTitle,bodyResponseJson);
		}
		else
		{
		  retStr= JPushClientExample.SendMsgRegistrationID(strTitle,bodyResponseJson);
		}
        logger.info("retStr:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+retStr);
        logger.info("strUser:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+strUser);
    	responseBodyJson.writeStringField("flag:", retStr);
    	responseBodyJson.writeStringField("ret", retStr);
	}
}
