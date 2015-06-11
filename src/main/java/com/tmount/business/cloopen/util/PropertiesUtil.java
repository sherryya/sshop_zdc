package com.tmount.business.cloopen.util;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import com.tmount.business.cloopen.model.httprequest.AgentSysInfo;
public class PropertiesUtil {
	/**
	 * 根据key读取value
	 * @param filePath 资源文件路径
	 * @param key 资源文件中key名称
	 */
	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			String value = props.getProperty(key);
			System.out.println(key + "=" + value);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	/**
	 * 云通讯接口参数
	 * @return
	 */
	public static AgentSysInfo getAgentSysInfo(String filePath)
	{  
		//String filePath = Context.getCurrentContext().getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		//String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		String hostname = PropertiesUtil.readValue(filePath, "hostname");
		String port = PropertiesUtil.readValue(filePath, "port");
		String softVer = PropertiesUtil.readValue(filePath, "softVer");
		String domain=PropertiesUtil.readValue(filePath, "domain");
		String accountSid=PropertiesUtil.readValue(filePath, "main_account");
		String authToken=PropertiesUtil.readValue(filePath, "main_token");
		String appId=PropertiesUtil.readValue(filePath, "app_id");
		String app_name=PropertiesUtil.readValue(filePath, "app_name");
		String reserve_date=PropertiesUtil.readValue(filePath, "reserve_date");
		String reserve_address=PropertiesUtil.readValue(filePath, "reserve_address");
		String sms_time=PropertiesUtil.readValue(filePath, "sms_time");
		String APPKEY=PropertiesUtil.readValue(filePath, "APPKEY");
		String APPSECRET=PropertiesUtil.readValue(filePath, "APPSECRET");
		String domain_65Net=PropertiesUtil.readValue(filePath, "domain_65Net");
		String androidpn_uri=PropertiesUtil.readValue(filePath, "androidpn_uri");
		String androidpn_apiKey=PropertiesUtil.readValue(filePath, "androidpn_apiKey");
		AgentSysInfo agentSysInfo=new AgentSysInfo();
		agentSysInfo.setAccountSid(accountSid);
		agentSysInfo.setAppId(appId);
		agentSysInfo.setAuthToken(authToken);
		agentSysInfo.setDomain(domain);
		agentSysInfo.setHostname(hostname);
		agentSysInfo.setPort(port);
		agentSysInfo.setSoftVer(softVer);
		agentSysInfo.setApp_name(app_name);
		agentSysInfo.setReserve_date(reserve_date);
		agentSysInfo.setReserve_address(reserve_address);
		agentSysInfo.setSms_time(sms_time);
		agentSysInfo.setAPPKEY(APPKEY);
		agentSysInfo.setAPPSECRET(APPSECRET);
		agentSysInfo.setDomain_65Net(domain_65Net);
		agentSysInfo.setAndroidpn_uri(androidpn_uri);
		agentSysInfo.setAndroidpn_apiKey(androidpn_apiKey);
		return agentSysInfo;
				
	}
}
