package com.tmount.business.ptt.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.net.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.cloopen.model.httprequest.AgentSysInfo;
import com.tmount.business.cloopen.util.PropertiesUtil;
import com.tmount.business.ptt.service.CommonAnalyCanStreamOrder;
import com.tmount.exception.ShopException;
import com.tmount.jpush.examples.JPushClientExample;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 通用发送指令
 * 
 * @author dell
 * 
 */
@Controller
public class SendOperInstructController extends ControllerBase {
	static Logger logger = Logger.getLogger(startService.class.getName());
	Socket socket = null;
	BufferedReader br = null;
	PrintWriter pw = null;
	@RequestMapping(value = "sendOperInstructController.send")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String sendTerminal = ParamData.getString(requestParam.getBodyNode(), "sendTerminal");// 发送终端   TEL:手机   CAR:车机	CARHOT 点火模块												 
		String tel = ParamData.getString(requestParam.getBodyNode(), "Tel");// 手机号
		String IMEI = ParamData.getString(requestParam.getBodyNode(), "IMEI");// IMEI号
		// 7:-->1001：关窗 1002：锁车 1003:点火
		// 8:-->10011:关窗成功 10010:失败 10021:成功 10020失败
		
		// 9:-->2E900241012B --车门指令
		//10:-->2E410201AF0C --得到车门的最新状态

		//11:->2E9002410428 --查看车窗状态
		//12:-->2E41050403050705A1  -得到车窗的最新状态
		
		//13:-->2E9002A101CB  --锁车指令
		//14:-->2EA101005D    --锁车成功    2EA101015C  失败
		
		//15:-->2E9002A102CA  --查看中控锁的最新状态
		//16:--> 2EA101015C --中控锁的最新状态(已落锁)  2EA101005D   --中控锁的最新状态(未落锁)   --2EA101FF5E   操作超时
		
		//故障码清除
		//17:-->2E900261000C  --清除  发动机故障 
		//18:-->2E610200009C  成功   2E610200019B  失败
		
		//19:-->2E900261010B  --清除  自动变速箱故障
		//20:-->2E610201009B  成功   2E610201019A  失败	

		//21:-->2E900261020A  --清除  ABS
		//22:-->2E610202009A  成功   2E6102020199  失败
		
		//23:-->2E9002610309  --清除  安全气囊  
		//24:-->2E6102030099  成功   2E6102030198  失败	
		
		//25:-->2E9002610408  --清除  中控锁  
		//26:-->2E6102040098  成功   2E6102040197  失败
		
		//27:-->2E9002610507  --清除  中央电器  
		//28:-->2E6102050097  成功   2E6102050196  失败
		
		//29:-->2E9002610507, 2E9002610507 --清除   多个控制单元的故障码  
		//30:-->2E6102050097   成功   2E6102050196  失败
		String strType = ParamData.getString(requestParam.getBodyNode(), "sendType");// 发送类型
		//  0-广告 1-导航 2-音乐 3-视频 4-路况 5-新闻 6-故障码 7-下发指令 8-指令回馈
		//  9 --得到车门状态                         10--返回车门状态 
		//  11--得到车窗状态                         12--返回车窗状态  
		//  13--锁车指令                                 14--锁车结果(也就是中控锁的最新状态)  
		//  15---查看中控锁的最新状态      16--中控锁的最新状态  
		String Msgtype = ParamData.getString(requestParam.getBodyNode(), "Msgtype");// 信息类型
		String strUser = "";
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
		json.writeStringField("Content", tel + "," + IMEI);//
		json.writeStringField("strType", getData(strType, Msgtype));
		json.writeStringField("strNum", "");
		json.writeEndObject();
		json.writeEndObject();
		json.close();
		String bodyResponseJson = jsonWrite.toString();
		logger.info("bodyResponseJson:~~~~~~~~~~~~~bodyResponseJson~~~~~~~~~~~~~~~~~~" + bodyResponseJson);
		String retStr = "";
		strUser = tel;
		if (sendTerminal.equalsIgnoreCase("CARHOT")) {  //手机端发送到平台，平台通过socket 发送到设备  现在不用这块了，直接通过android 程序发送到socket
			retStr = sendNotification(strUser, strTitle, bodyResponseJson);
		} else {//平台推送信息到手机端
			retStr = JPushClientExample.SendMsg(strUser, strTitle, bodyResponseJson);
			logger.info("retStr:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + retStr);
			logger.info("strUser:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + strUser);
			logger.info("****************返回指令："+strType+"****************");
			logger.info("****************Msgtype："+Msgtype+"****************");
			responseBodyJson.writeStringField("flag:", retStr);
			responseBodyJson.writeStringField("ret", retStr);
		}
	}
	private String getData(String strType, String Msgtype) {

		if (Msgtype.equalsIgnoreCase("10")||(Msgtype.equalsIgnoreCase("12"))||(Msgtype.equalsIgnoreCase("14"))||(Msgtype.equalsIgnoreCase("16")))//车门状态、车窗状态、锁车指令、中控锁状态 ,
		{
			return CommonAnalyCanStreamOrder.getCanStatus(strType,Msgtype);
		}
		else if (Msgtype.equalsIgnoreCase("34"))
		{
			return String.valueOf(CommonAnalyCanStreamOrder.getCarStatus(strType));
		}
		else {
			return strType;
		}
	}
	public static void main(String[] args) {
	//System.out.println(	new SendOperInstructController().getData("2E410201A01B", "10"));
		new SendOperInstructController().sendNotification("18646117093","2222","测试");
	}
	public String sendNotification(String strUser, String strTitle,String  bodyResponseJson) {
		String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		filePath ="/"+filePath;
		AgentSysInfo agentSysInfo=new AgentSysInfo();
		agentSysInfo=	PropertiesUtil.getAgentSysInfo(filePath);
		String Androidpn_apiKey = agentSysInfo.getAndroidpn_apiKey();
		String Androidpn_uri = agentSysInfo.getAndroidpn_uri();
		String ret="-2";
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Androidpn_uri); 			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		NameValuePair[] data = {
				    new NameValuePair("apiKey", Androidpn_apiKey), 
				    new NameValuePair("title", strTitle), 
				    new NameValuePair("message", bodyResponseJson),
				    new NameValuePair("uri", ""), 
				    new NameValuePair("sendtype", "1"),
				    new NameValuePair("username",  strUser),
			};		
		method.setRequestBody(data);				
			try {  
				client.executeMethod(method);
				method.getResponseBodyAsString();	
				ret= "1";
				logger.info("androidpn 推送成功:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				logger.info("androidpn username:~~~~~~~~~~~~~~~~~"+strUser+"~~~~~~~~~~~~~~");
				logger.info("androidpn message:~~~~~~~~~~~~~~~~~"+bodyResponseJson+"~~~~~~~~~~~~~~");
			} catch (HttpException e) {
				ret= "-1";
				logger.info("androidpn 推送失败:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ret= "-3";
				logger.info("androidpn 推送失败:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				e.printStackTrace();
			}	
			return ret;
	}
}
