package com.tmount.business.breakrules.platform;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

public class BreakCityListPlat {

	
	//申请的key(违章查询)
	public static String breakrules_key = "35d4681985d16ac0657233a7aab76fe5";
	//获取支持城市接口
	public static String breakrules_citylist_url = "http://v.juhe.cn/wz/query?city=";
	
	/**
	 * 20141128
	 * @param engineno 发动机号
	 * @param classno  车架号
	 * @param hphm 号牌号码
	 * @return
	 */
	public static String getInfo(String city_code,String engineno,String classno,String hphm)
	{
		String url=breakrules_citylist_url+city_code+"&key="+breakrules_key;
		if(StringUtils.isNotBlank(engineno))
		{
			engineno = "&engineno="+engineno;
		}
		if(StringUtils.isNotBlank(classno))
		{
			classno = "&classno="+classno;
		}
		if(StringUtils.isNotBlank(hphm))
		{
			try {
				hphm = URLEncoder.encode(hphm, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hphm = "&hphm="+hphm;
		}
		url = url+engineno+classno+hphm;
		String charset = "UTF-8";
		String json = get(url, charset);
		System.out.println("breakrules_citylist_response_json" + json);
		return json;
	}
	public static String get(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = "";
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// 模拟浏览器
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			
			connection.setRequestProperty("User-agent", userAgent);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) throws Exception {
		
		String tt = BreakCityListPlat.getInfo("HLJ_HAERBIN","","VF1LZL4T4BC256702","黑A8212C");
		System.out.println("ttt------>>"+tt);
		//String json = GetPlatformInfo.getCityList("HLJ");
		//System.out.println("json---->>"+json);
	}

}
