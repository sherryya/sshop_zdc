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


import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.tmount.business.cloopen.model.httprequest.AgentSysInfo;
import com.tmount.business.cloopen.util.PropertiesUtil;
import com.tmount.tools.GuidCreator;
/**
 * 
* <p>Title: RestExamples.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: http://www.cloopen.com</p>
* @author JorstinChan
* @date 2013-9-27 1111111111
* @version 2.4
 */
public class RestExamples {

	//public static void main(String[] args) throws Exception {

		// 创建子账户
		//new RestExamples().CreateSubAccount("jingzhi");
		// 发送短信
		// 请输入接收电话号和信息内容
		//new RestExamples().SendSMS("15945150563", "1234","2");
	/*	    int max=999999;
	        int min=100000;
	        Random random = new Random();


	        int s = random.nextInt(max)%(max-min+1) + min;
	        System.out.println(s);*/
		// 双向回拨
		// 主叫号码,国内：0086+手机号码，0086+区号+电话号码，区号去掉第1位数字;
		//CallBack("008613512345678", "008613512345678");

		//账户信息查询
		//new RestExamples().QueryAccountInfo();
		//new RestExamples().getSubAccountSid();
		
		// 营销外呼
		// 电话号码,手机号 区号+电话号码
		//LandingCalls("13512345678");

		// 语音验证码
		// 电话号码,国家号+手机号  国家号+区号+电话号码
		// 如国内：0086+手机号码，0086+区号+电话号码
		//VoiceVerifyCode("008613512345678");
	//}
	/**
	 * @brief				创建子账户。一般用户在网站申请测试账号成功后系统默认分配两个子账户，用户还可以通过创建子账户接口创建多个子账户。
	 * @param accountName	子账号名称
	 */
	public  String CreateSubAccount(String accountName) {
		// 获取配置文件路径
		//String relativelyPath = System.getProperty("user.dir");
		//String filePath = relativelyPath + "\\src\\main\\resource\\config.properties";
		String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		filePath ="/"+filePath;
		
		String result  ="";
		try {
			// 读取账号配置
			String accountSid = PropertiesUtil.readValue(filePath, "main_account");// 主账户Id
			String authToken = PropertiesUtil.readValue(filePath, "main_token");// 主账户授权令牌
			String appId = PropertiesUtil.readValue(filePath, "app_id");// 应用Id
			// 应用内容参数
			//String friendlyName = accountName;// 子账户名称
			GuidCreator myGUID = new GuidCreator();
			String friendlyName = myGUID.createNewGuid(GuidCreator.FormatString);
			String status = "1";// 子账户状态
			// 实例化RestAPI
			RestAPI rest = new RestAPI();
            // 调用创建子帐户接口  
			result = rest.CreateSubAccount(accountSid, authToken, appId, friendlyName, status);
			//打印创建子账号响应体
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @brief			发送短信
	 * @param dest 		对方手机号
	 * @param content 	短信内容
	 * @param msgType 	短信类型  0：普通类型  1 ： 长短信
	 */
	public  void SendSMS(String dest, String content,String msgType) {
		try {
			String to = dest;// 短信接收端手机号
			RestAPI rest = new RestAPI();
			// 调用发送短信接口I
			String result = rest.SendSMS(to, content, msgType,"2");
			//打印发送短信响应体
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * @brief		双向回拨
	 * @param src 	本端电话号码
	 * @param dest 	对端电话号码
	 */
	public static String CallBack(String src, String dest) {
		// 获取配置文件路径
		String relativelyPath = System.getProperty("user.dir");
		String filePath = relativelyPath + "\\conf\\config.properties";
		String result = "";
		try {
			// 读取账号配置
			String subAccountSid = PropertiesUtil.readValue(filePath, "sub_account");// 子账户Id
			String subAuthToken = PropertiesUtil.readValue(filePath, "sub_token");// 子账户授权令牌
			String voipId = PropertiesUtil.readValue(filePath, "voip_account");// VoIP帐号

			// 应用内容参数
			String from = src;// 主叫号码,国内：0086+手机号码，0086+区号+电话号码，区号去掉第1位数字
			String to = dest;// 被叫号码

			// 实例化RestAPI
			RestAPI rest = new RestAPI();
			// 调用回拨接口
			result = rest.CallBack(subAccountSid, subAuthToken, voipId, from, to);
			//打印双向回呼响应体
			System.out.println("Response content is: " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

	/**
	* @brief				账户信息查询
	* @param accountSid		主账号
	* @param authToken		主账号令牌
	*/
	public  void QueryAccountInfo() {
		// 获取配置文件路径
		//String relativelyPath = System.getProperty("user.dir");
		//String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);//relativelyPath + "\\conf\\config.properties";
		String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		try {
			// 读取账号配置
			String accountSid = PropertiesUtil.readValue(filePath, "main_account");// 主账户Id
			String authToken = PropertiesUtil.readValue(filePath, "main_token");// 主账户授权令牌

			// 实例化RestAPI
			RestAPI rest = new RestAPI();
            // 调用创建子帐户接口  
			String result = rest.QueryAccountInfo(accountSid, authToken);
			//打印创建子账号响应体
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* @brief				营销外呼
	* @param to				要外呼的号码
	*/
	public static void LandingCalls(String to) {
		// 获取配置文件路径
		String relativelyPath = System.getProperty("user.dir");
		String filePath = relativelyPath + "\\conf\\config.properties";
		try {
			// 读取账号配置
			String accountSid = PropertiesUtil.readValue(filePath, "main_account");// 主账户Id
			String authToken = PropertiesUtil.readValue(filePath, "main_token");// 主账户授权令牌
			String appId = PropertiesUtil.readValue(filePath, "app_id");// 应用Id

			// 应用内容参数
			String mediaName = "marketingcall.wav";// 子账户类型
			String playTimes = "2";

			// 实例化RestAPI
			RestAPI rest = new RestAPI();
            // 调用创建子帐户接口  
			String result = rest.LandingCalls(accountSid, authToken, appId, mediaName, playTimes, to);
			//打印创建子账号响应体
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* @brief				语音验证码
	* @param to				接收号码
	*/
	public static void VoiceVerifyCode(String to) {
		// 获取配置文件路径
		String relativelyPath = System.getProperty("user.dir");
		String filePath = relativelyPath + "\\conf\\config.properties";
		try {
			// 读取账号配置
			String accountSid = PropertiesUtil.readValue(filePath, "main_account");// 主账户Id
			String authToken = PropertiesUtil.readValue(filePath, "main_token");// 主账户授权令牌
			String appId = PropertiesUtil.readValue(filePath, "app_id");// 应用Id

			// 应用内容参数
			String verifyCode = "1a8d9u";
			String playTimes = "1";

			// 实例化RestAPI
			RestAPI rest = new RestAPI();
            // 调用创建子帐户接口  
			String result = rest.VoiceVerifyCode(accountSid, authToken, appId, verifyCode, playTimes, to);
			//打印创建子账号响应体
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到子账号信息
	 * @return
	 */
	public  String getSubAccountSid() {
		// 获取配置文件路径
		String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		filePath ="/"+filePath;
		String result  ="";
		try {
			// 读取账号配置
			String accountSid = PropertiesUtil.readValue(filePath, "main_account");// 主账户Id
			String authToken = PropertiesUtil.readValue(filePath, "main_token");// 主账户授权令牌
			String appId = PropertiesUtil.readValue(filePath, "app_id");// 应用Id
			// 应用内容参数
			// 实例化RestAPI
			RestAPI rest = new RestAPI();
            // 调用创建子帐户接口  
			result = rest.getSubAccountSid(accountSid, authToken, appId);
			//打印创建子账号响应体
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 关闭子账号
	 * @param subAccountSid
	 * @return
	 */
	public  String CloseSubAccount(String subAccountSid) {
		// 获取配置文件路径
		String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		filePath ="/"+filePath;
		String result  ="";
		try {
			// 读取账号配置
			String accountSid = PropertiesUtil.readValue(filePath, "main_account");// 主账户Id
			String authToken = PropertiesUtil.readValue(filePath, "main_token");// 主账户授权令牌
			String appId = PropertiesUtil.readValue(filePath, "app_id");// 应用Id
			// 应用内容参数
			// 实例化RestAPI
			RestAPI rest = new RestAPI();
            // 调用创建子帐户接口  
			result = rest.CloseSubAccount(accountSid, authToken, appId,subAccountSid);
			//打印创建子账号响应体
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		//new RestExamples().CloseSubAccount("636a4f93250f11e48437d89d672b9690");
		//new RestExamples().CreateSubAccount("15683396639");
		GuidCreator myGUID = new GuidCreator();
		 String userId = myGUID.createNewGuid(GuidCreator.FormatString);
		 System.out.println(userId);
	}
}
