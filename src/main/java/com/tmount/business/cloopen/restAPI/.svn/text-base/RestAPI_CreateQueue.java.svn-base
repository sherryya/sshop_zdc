package com.tmount.business.cloopen.restAPI;

import java.io.ByteArrayInputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.tmount.business.cloopen.model.httprequest.AgentSysInfo;
import com.tmount.business.cloopen.util.CcopHttpClient;
import com.tmount.business.cloopen.util.DateUtil;
import com.tmount.business.cloopen.util.EncryptUtil;
import com.tmount.business.cloopen.util.HttpHeader;
import com.tmount.business.cloopen.util.JsonUtils;
import com.tmount.business.cloopen.util.PropertiesUtil;
/**
 * 创建队列
 * @author dell
 *
 */
public class RestAPI_CreateQueue {
	
	
	
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException {
		RestAPI_CreateQueue c=new  RestAPI_CreateQueue();
		String ret= c.GetAgentCallForwarding("1","VOIP转落地");
		System.out.println(ret);
	}
	
	
	public  String GetAgentCallForwarding( String queuetype,String typedes) throws NoSuchAlgorithmException, KeyManagementException {
		String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~"+this.getClass().getClassLoader().getResource("config.properties").toString());
		System.out.println("~~~~~~~~~~~~~~~~~filePath~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+filePath);
		filePath = "/"+filePath;
		AgentSysInfo agentSysInfo=new AgentSysInfo();
		agentSysInfo=	PropertiesUtil.getAgentSysInfo(filePath);
		String hostname = agentSysInfo.getHostname();
		String port = agentSysInfo.getPort();
		String softVer = agentSysInfo.getSoftVer();
		String domain=agentSysInfo.getDomain();
		String accountSid=agentSysInfo.getAccountSid();
		String authToken=agentSysInfo.getAuthToken();
		String appId=agentSysInfo.getAppId();
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
					     .append("/ivr/createqueue?sig=").append(signature).append("&queuetype=").append(queuetype).toString();
			System.out.println(url);
			// 创建HttpPost
			HttpPost httppost = new HttpPost(url);
			HttpHeader.setHttpHeader(httppost);
			String src = accountSid + ":" + timestamp;
			// base64(主账户Id + 冒号 +时间戳)
			String auth = eu.base64Encoder(src);
			httppost.setHeader("Authorization", auth);
			String body = null;
			if(!JsonUtils.JSON_ENABLE) {
				// 构造Body
				body = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?><Request>"))
				.append("<Appid>").append(appId).append("</Appid>")
	            .append("<CreateQueue  ").append("queuetype=\"").append(queuetype).append("\"  typedes=\""+typedes+"\"").append("/>")
	            .append("</Request>").toString();
				System.out.println("RestAPI_AgentCallForwarding_body:"+body);
			}
			System.out.println("RestAPI_CreateQueue : request _" + body);
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
