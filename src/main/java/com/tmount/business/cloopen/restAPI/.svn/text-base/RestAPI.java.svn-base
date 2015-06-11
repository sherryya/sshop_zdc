/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.cloopen.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.tmount.business.cloopen.restAPI;

import java.io.ByteArrayInputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tmount.business.cloopen.model.httprequest.AgentSysInfo;
import com.tmount.business.cloopen.model.httprequest.CallBackRequest;
import com.tmount.business.cloopen.model.httprequest.LandingCallsRequest;
import com.tmount.business.cloopen.model.httprequest.SendSMSRequest;
import com.tmount.business.cloopen.model.httprequest.SubAccountCreate;
import com.tmount.business.cloopen.model.httprequest.VoiceVerifyCodeRequest;
import com.tmount.business.cloopen.util.CcopHttpClient;
import com.tmount.business.cloopen.util.DateUtil;
import com.tmount.business.cloopen.util.EncryptUtil;
import com.tmount.business.cloopen.util.JsonUtils;
import com.tmount.business.cloopen.util.PropertiesUtil;
import com.tmount.business.ptt.controller.startService;
import com.tmount.util.DatatimeUtil;

/**
 * 
* <p>Title: RestAPI.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: http://www.cloopen.com</p>
* @author JorstinChan
* @date 2013-9-27
* @version 2.4
 */
public class RestAPI {
	// 请求的主机名（IP或者域名）,端口号
	String filePath ="/"+this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
	private String hostname = PropertiesUtil.readValue(filePath, "hostname");
	private String port = PropertiesUtil.readValue(filePath, "port");
	private String softVer = PropertiesUtil.readValue(filePath, "softVer");
	private String domain=PropertiesUtil.readValue(filePath, "domain");
	Logger logger = Logger.getLogger(startService.class.getName());
	/**
	* @brief					创建子账号
	* @param accountSid			主账号
	* @param authToken			主账号令牌
	* @param appId				应用id
	* @param friendlyName		申请的邮箱
	* @param status				状态
	* @return Httppost 			协议包封装
	* @throws NoSuchAlgorithmException
	* @throws KeyManagementException  jdbc.properties
	*/
	public String CreateSubAccount(String accountSid, String authToken, String appId, String friendlyName,
			String status) throws NoSuchAlgorithmException, KeyManagementException {
		// 返回的响应字符串
		String result = "";
		// 创建HttpClient
		CcopHttpClient chc = new CcopHttpClient();
		DefaultHttpClient httpclient = chc.registerSSL(domain,"TLS", 8883, "https");
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
			// md5(主账户Id +主账户授权令牌 + 时间戳)
			String sig = accountSid + authToken + timestamp;
			// MD5加密
			EncryptUtil eu = new EncryptUtil();
			String signature = eu.md5Digest(sig);
			String url = (new StringBuilder(hostname)).append(":").append(port)
						 .append("/").append(softVer).append("/Accounts/").append(accountSid)
					     .append("/SubAccounts?sig=").append(signature).toString();
			// 创建HttpPost
			HttpPost httppost = new HttpPost(url);
			setHttpHeader(httppost);
			String src = accountSid + ":" + timestamp;
			// base64(主账户Id + 冒号 +时间戳)
			String auth = eu.base64Encoder(src);
			httppost.setHeader("Authorization", auth);
			String body = null;
			if(!JsonUtils.JSON_ENABLE) {
				// 构造Body
				body = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?><SubAccount>")
	              .append("<appId>")).append(appId).append("</appId>")
	              .append("<friendlyName>").append(friendlyName).append("</friendlyName>")
	              .append("</SubAccount>").toString();
			} else {
				SubAccountCreate subAccount = new SubAccountCreate(appId, friendlyName, accountSid);
				Gson gson = new Gson();
				body = gson.toJson(subAccount);
			}
			System.out.println("CreateSubAccount : request _" + body);
			BasicHttpEntity requestBody = new BasicHttpEntity();
            requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
            requestBody.setContentLength(body.getBytes("UTF-8").length);
            httppost.setEntity(requestBody);
			// 执行客户端请求
			HttpResponse response = httpclient.execute(httppost);
			// 获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	private void setHttpHeader(AbstractHttpMessage httpMessage) {
		if(!JsonUtils.JSON_ENABLE) {
			// 构造请求头信息
			httpMessage.setHeader("Accept", "application/xml");
			httpMessage.setHeader("Content-Type", "application/xml;charset=utf-8");
		} else {
			// 构造请求头信息
			httpMessage.setHeader("Accept", "application/json");
			httpMessage.setHeader("Content-Type", "application/json;charset=utf-8");
		}
	}
    /**
     * 短信发送
     * @param to  手机号
     * @param body 
     * @param msgType   短信类型          0: 普通短信    1:大于65个字  
     * @param sms_type  模板类型          0: 用户注册  1:设备预约  2：下发预约单号  3：tomcat监控
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
	public String SendSMS(String to,String body, String msgType,String sms_type) throws NoSuchAlgorithmException, KeyManagementException {
		String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		filePath ="/"+filePath;
		AgentSysInfo agentSysInfo=new AgentSysInfo();
		agentSysInfo=	PropertiesUtil.getAgentSysInfo(filePath);
		String accountSid=agentSysInfo.getAccountSid();
		String authToken=agentSysInfo.getAuthToken();
		String appId=agentSysInfo.getAppId();
		String subAccountSid=agentSysInfo.getSub_account();
		String app_name=agentSysInfo.getApp_name();
		String reserve_date=agentSysInfo.getReserve_date();
		String reserve_address=agentSysInfo.getReserve_address();
		String sms_time=agentSysInfo.getSms_time();
		String reslut = "";
		// 创建HttpClient
		CcopHttpClient chc = new CcopHttpClient();
		DefaultHttpClient httpclient = chc.registerSSL("app.cloopen.com",	"TLS", 8883, "https");
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			// md5(主账户Id + 主账户授权令牌 + 时间戳)
			String sig = accountSid + authToken + timestamp;
			// MD5加密
			EncryptUtil eu = new EncryptUtil();
			String signature = eu.md5Digest(sig);
			String url = (new StringBuilder(hostname)).append(":").append(port)
					 	 .append("/").append(softVer).append("/Accounts/")
					     .append(accountSid).append("/SMS/TemplateSMS?sig=")//Messages   TemplateSMS
					     .append(signature).toString();
			// 创建HttpPost
			System.out.println("url:"+url);
			HttpPost httppost = new HttpPost(url);
			setHttpHeader(httppost);
			String src = accountSid + ":" + timestamp;
			String auth = eu.base64Encoder(src);
			httppost.setHeader("Authorization", auth);// base64(主账户Id + 冒号 + 时间戳)
			String bodyData = null;
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~SMS is bigining  sms_type:"+ sms_type);
			if(!JsonUtils.JSON_ENABLE) {
				// 构造Body
				if(sms_type.equalsIgnoreCase("0"))//用户注册，短信验证
				{
				bodyData = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?>")).append("<TemplateSMS>")
					      .append("<to>").append(to).append("</to>")
					      .append("<appId>").append(appId).append("</appId>")
					      .append("<templateId>4106</templateId>")
					      .append("<datas>")
					      .append("<data>"+app_name+"</data>")
					      .append("<data>"+body+"</data>")
					      /*.append("<data>"+sms_time+"</data>")*/
					      .append("</datas>")
					      .append("</TemplateSMS>").toString();
				}
				else if(sms_type.equalsIgnoreCase("1"))//设备预约
				{
				bodyData = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?>")).append("<TemplateSMS>")
					      .append("<to>").append(to).append("</to>")
					      .append("<appId>").append(appId).append("</appId>")
					      .append("<templateId>1519</templateId>")
					      .append("<datas>")
					      .append("<data>"+body+"</data>")
					      .append("<data>"+sms_time+"</data>")
					      .append("</datas>")
					      .append("</TemplateSMS>").toString();
				}
				else if(sms_type.equalsIgnoreCase("2"))//发送预约单号
				{
				bodyData = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?>")).append("<TemplateSMS>")
					      .append("<to>").append(to).append("</to>")
					      .append("<appId>").append(appId).append("</appId>")
					      .append("<templateId>1520</templateId>")
					      .append("<datas>")
					      .append("<data>"+DatatimeUtil.getCurrentDate().toString()+"</data>")//当前日起
					      .append("<data>"+body+"</data>")//预约单号
					      .append("<data>"+reserve_date+"</data>")//发售日期
					      .append("<data>"+reserve_address+"</data>")//领取地址
					      .append("</datas>")
					      .append("</TemplateSMS>").toString();
				}
				else if(sms_type.equalsIgnoreCase("3"))//tomcat 监控
				{
				bodyData = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?>")).append("<TemplateSMS>")
					      .append("<to>").append(to).append("</to>")
					      .append("<appId>").append(appId).append("</appId>")
					      .append("<templateId>1758</templateId>")
					      .append("<datas>")
					      .append("<data>"+body+"</data>")//宕机服务器
					      .append("</datas>")
					      .append("</TemplateSMS>").toString();
				}
			} else {
				SendSMSRequest sendSMS = new SendSMSRequest(to, bodyData, msgType, appId, subAccountSid);
				Gson gson = new Gson();
				bodyData = gson.toJson(sendSMS);
			}
			System.out.println("发送报文 : request _" + bodyData);
			BasicHttpEntity requestBody = new BasicHttpEntity();
            requestBody.setContent(new ByteArrayInputStream(bodyData.getBytes("UTF-8")));
            requestBody.setContentLength(bodyData.getBytes("UTF-8").length);
            httppost.setEntity(requestBody);
			// 执行客户端请求
			HttpResponse response = httpclient.execute(httppost);
			// 获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				reslut = EntityUtils.toString(entity, "UTF-8");
			}
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		//返回发送短信的响应体
		return reslut;
	}
	/**
	* @brief				双向回拨
	* @param subAccountSid	子账号	
	* @param subToken		子账号令牌
	* @param voipAccount	VoIP账号
	* @param from			主叫电话
	* @param to				被叫电话
	* @return Httppost 		协议包封装
	* @throws NoSuchAlgorithmException
	* @throws KeyManagementException
	*/
	public String CallBack(String subAccountSid, String subToken, String voipAccount, String from, String to)
			throws NoSuchAlgorithmException, KeyManagementException {
		// 返回的响应字符串
		String result = "";
		// 创建HttpClient
		CcopHttpClient chc = new CcopHttpClient();
		DefaultHttpClient httpclient = chc.registerSSL("sandboxapp.cloopen.com",
				"TLS", 8883, "https");
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(),
					DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			// md5(子账户Id + 子账户授权令牌 + 时间戳)
			String sig = subAccountSid + subToken + timestamp;
			// MD5加密
			EncryptUtil eu = new EncryptUtil();
			String signature = eu.md5Digest(sig);
			
			String url = (new StringBuilder(hostname)).append(":").append(port)
						 .append("/").append(softVer)
					     .append("/SubAccounts/").append(subAccountSid)
					     .append("/Calls/Callback?sig=").append(signature).toString();
			// 创建HttpPost
			HttpPost httppost = new HttpPost(url);
			setHttpHeader(httppost);
			String src = subAccountSid + ":" + timestamp;
			String auth = eu.base64Encoder(src);
			httppost.setHeader("Authorization", auth);// base64(子账户Id + 冒号 + 时间戳)
			
			String body = null;
			if(!JsonUtils.JSON_ENABLE) {
				
				// 构造Body
				body = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?><CallBack>")
					.append("<from>").append(from).append("</from>")
					.append("<to>").append(to).append("</to></CallBack>").toString());
			} else {
				CallBackRequest callBackRequest = new CallBackRequest(subAccountSid, voipAccount, from, to);
				Gson gson = new Gson();
				body = gson.toJson(callBackRequest);
			}
			System.out.println("CreateSubAccount : request _" + body);
			
			BasicHttpEntity requestBody = new BasicHttpEntity();
            requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
            requestBody.setContentLength(body.getBytes("UTF-8").length);
            httppost.setEntity(requestBody);
			
			// 执行客户端请求
			HttpResponse response = httpclient.execute(httppost);

			// 获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	/**
	* @brief				账户信息查询
	* @param accountSid		主账号
	* @param authToken		主账号令牌
	*/
	public String QueryAccountInfo(String accountSid, String authToken)
			throws NoSuchAlgorithmException, KeyManagementException {
		// 返回的响应字符串
		String result = "";
		// 创建HttpClient
		CcopHttpClient chc = new CcopHttpClient();
		DefaultHttpClient httpclient = chc.registerSSL("app.cloopen.com",
				"TLS", 8883, "https");
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
			// md5(主账户Id +主账户授权令牌 + 时间戳)
			String sig = accountSid + authToken + timestamp;
			// MD5加密
			EncryptUtil eu = new EncryptUtil();
			String signature = eu.md5Digest(sig);
			
			String url = (new StringBuilder(hostname)).append(":").append(port)
						 .append("/").append(softVer).append("/Accounts/").append(accountSid)
					     .append("/AccountInfo?sig=").append(signature).toString();
			// 创建HttpGet
			System.out.println(url);
			HttpGet httpget = new HttpGet(url);
			setHttpHeader(httpget);
			String src = accountSid + ":" + timestamp;
			// base64(主账户Id + 冒号 +时间戳)
			String auth = eu.base64Encoder(src);
			httpget.setHeader("Authorization", auth);
			
			// 执行客户端请求
			HttpResponse response = httpclient.execute(httpget);

			// 获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	/**
	* @brief				营销外呼
	* @param accountSid		主账号
	* @param authToken		主账号令牌
	* @param appId			应用id
	* @param mediaName		多媒体文件名字
	* @param mediaTxt		多媒体文件内容
	* @param playTimes		播放次数
	* @param to				要外呼的号码
	*/
	public String LandingCalls(String accountSid, String authToken, String appId, String mediaName, String playTimes, String to)throws NoSuchAlgorithmException, KeyManagementException {
		// 返回的响应字符串
		String result = "";
		// 创建HttpClient
		CcopHttpClient chc = new CcopHttpClient();
		DefaultHttpClient httpclient = chc.registerSSL("sandboxapp.cloopen.com",
				"TLS", 8883, "https");
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
			// md5(主账户Id +主账户授权令牌 + 时间戳)
			String sig = accountSid + authToken + timestamp;
			// MD5加密
			EncryptUtil eu = new EncryptUtil();
			String signature = eu.md5Digest(sig);
			
			String url = (new StringBuilder(hostname)).append(":").append(port)
						 .append("/").append(softVer).append("/Accounts/").append(accountSid)
					     .append("/Calls/LandingCalls?sig=").append(signature).toString();
			// 创建HttpPost
			HttpPost httppost = new HttpPost(url);
			setHttpHeader(httppost);
			String src = accountSid + ":" + timestamp;
			// base64(主账户Id + 冒号 +时间戳)
			String auth = eu.base64Encoder(src);
			httppost.setHeader("Authorization", auth);

			String body = null;
			if(!JsonUtils.JSON_ENABLE) {
				// 构造Body
				StringBuilder bodyBuilder = new StringBuilder("<?xml version='1.0' encoding='utf-8'?><LandingCall>");
				bodyBuilder.append("<appId>").append(appId).append("</appId>");
				bodyBuilder.append("<mediaName>").append(mediaName).append("</mediaName>");
				bodyBuilder.append("<playTimes>").append(playTimes).append("</playTimes>");
				bodyBuilder.append("<to>").append(to).append("</to></LandingCall>");
				body = bodyBuilder.toString();
				
			} else {
				LandingCallsRequest landingCallsRequest = new LandingCallsRequest(appId, mediaName, playTimes, to);
				Gson gson = new Gson();
				body = gson.toJson(landingCallsRequest);
			}
			System.out.println("CreateSubAccount : request _" + body);
			
			BasicHttpEntity requestBody = new BasicHttpEntity();
            requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
            requestBody.setContentLength(body.getBytes("UTF-8").length);
            httppost.setEntity(requestBody);
			
			// 执行客户端请求
			HttpResponse response = httpclient.execute(httppost);

			// 获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	/**
	* @brief				语音验证码
	* @param accountSid		主账号
	* @param authToken		主账号令牌
	* @param appId			应用id
	* @param verifyCode		验证码内容，为数字和英文字母，不区分大小写，长度4-20位
	* @param playTimes		播放次数，1－3次
	* @param to				接收号码
	*/
	public String VoiceVerifyCode(String accountSid, String authToken, String appId, String verifyCode, String playTimes, String to)
			throws NoSuchAlgorithmException, KeyManagementException {
		// 返回的响应字符串
		String result = "";
		// 创建HttpClient
		CcopHttpClient chc = new CcopHttpClient();
		DefaultHttpClient httpclient = chc.registerSSL("sandboxapp.cloopen.com",
				"TLS", 8883, "https");
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
			// md5(主账户Id +主账户授权令牌 + 时间戳)
			String sig = accountSid + authToken + timestamp;
			// MD5加密
			EncryptUtil eu = new EncryptUtil();
			String signature = eu.md5Digest(sig);
			String url = (new StringBuilder(hostname)).append(":").append(port)
						 .append("/").append(softVer).append("/Accounts/")
						 .append(accountSid).append("/Calls/VoiceVerify?sig=")
					     .append(signature).toString();
			// 创建HttpPost
			HttpPost httppost = new HttpPost(url);
			setHttpHeader(httppost);
			String src = accountSid + ":" + timestamp;
			// base64(主账户Id + 冒号 +时间戳)
			String auth = eu.base64Encoder(src);
			httppost.setHeader("Authorization", auth);

			String body = null;
			if(!JsonUtils.JSON_ENABLE) {
				// 构造Body
				body = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?><VoiceVerify>")
					.append("<appId>")).append(appId).append("</appId>")
					.append("<verifyCode>").append(verifyCode).append("</verifyCode>")
					.append("<playTimes>").append(playTimes).append("</playTimes>")
					.append("<to>").append(to).append("</to></VoiceVerify>")
					.toString();
			} else {
				
				VoiceVerifyCodeRequest verifyCodeRequest  = new VoiceVerifyCodeRequest(appId, verifyCode, playTimes, to);
				Gson gson = new Gson();
				body = gson.toJson(verifyCodeRequest);
			}
			System.out.println("CreateSubAccount : request _" + body);
			
			
			BasicHttpEntity requestBody = new BasicHttpEntity();
            requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
            requestBody.setContentLength(body.getBytes("UTF-8").length);
            httppost.setEntity(requestBody);
			
			// 执行客户端请求
			HttpResponse response = httpclient.execute(httppost);

			// 获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	
	
	public String getSubAccountSid(String accountSid, String authToken, String appId)
			throws NoSuchAlgorithmException, KeyManagementException {
		// 返回的响应字符串
		String result = "";
		// 创建HttpClient
		CcopHttpClient chc = new CcopHttpClient();
		DefaultHttpClient httpclient = chc.registerSSL("app.cloopen.com",
				"TLS", 8883, "https");
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
			// md5(主账户Id +主账户授权令牌 + 时间戳)
			String sig = accountSid + authToken + timestamp;
			// MD5加密
			EncryptUtil eu = new EncryptUtil();
			String signature = eu.md5Digest(sig);
			String url = (new StringBuilder(hostname))
						 .append("/").append(softVer).append("/Accounts/")
						 .append(accountSid).append("/GetSubAccounts?sig=")
					     .append(signature).toString();
			// 创建HttpPost
			System.out.println("url:"+url);
			HttpPost httppost = new HttpPost(url);
			setHttpHeader(httppost);
			String src = accountSid + ":" + timestamp;
			// base64(主账户Id + 冒号 +时间戳)
			String auth = eu.base64Encoder(src);
			httppost.setHeader("Authorization", auth);

			String body = null;
			if(!JsonUtils.JSON_ENABLE) {
				// 构造Body
				body = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?><SubAccount>")
					.append("<appId>")).append(appId).append("</appId>")
					.append("<startNo>1</startNo>")
					.append("<offset>100</offset></SubAccount>")
					.toString();
			} 
			System.out.println("CreateSubAccount : request _" + body);
			
			
			BasicHttpEntity requestBody = new BasicHttpEntity();
            requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
            requestBody.setContentLength(body.getBytes("UTF-8").length);
            httppost.setEntity(requestBody);
			
			// 执行客户端请求
			HttpResponse response = httpclient.execute(httppost);

			// 获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	
	/**
	 * 关闭子账号
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param subAccountSid
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public String CloseSubAccount(String accountSid, String authToken, String appId,String subAccountSid)
			throws NoSuchAlgorithmException, KeyManagementException {
		// 返回的响应字符串
		String result = "";
		// 创建HttpClient
		CcopHttpClient chc = new CcopHttpClient();
		DefaultHttpClient httpclient = chc.registerSSL("app.cloopen.com",
				"TLS", 8883, "https");
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
			// md5(主账户Id +主账户授权令牌 + 时间戳)
			String sig = accountSid + authToken + timestamp;
			// MD5加密
			EncryptUtil eu = new EncryptUtil();
			String signature = eu.md5Digest(sig);
			String url = (new StringBuilder(hostname))
						 .append("/").append(softVer).append("/Accounts/")
						 .append(accountSid).append("/CloseSubAccount?sig=")
					     .append(signature).toString();
			// 创建HttpPost
			System.out.println("url:"+url);
			HttpPost httppost = new HttpPost(url);
			setHttpHeader(httppost);
			String src = accountSid + ":" + timestamp;
			// base64(主账户Id + 冒号 +时间戳)
			String auth = eu.base64Encoder(src);
			httppost.setHeader("Authorization", auth);

			String body = null;
			if(!JsonUtils.JSON_ENABLE) {
				// 构造Body
				body = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?><SubAccount>")
					.append("<appId>")).append(appId).append("</appId>")
					.append("<subAccountSid>"+subAccountSid+"</subAccountSid> </SubAccount>")
					.toString();
			} 
			System.out.println("CloseSubAccount : request _" + body);
			
			
			BasicHttpEntity requestBody = new BasicHttpEntity();
            requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
            requestBody.setContentLength(body.getBytes("UTF-8").length);
            httppost.setEntity(requestBody);
			
			// 执行客户端请求
			HttpResponse response = httpclient.execute(httppost);

			// 获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
}
