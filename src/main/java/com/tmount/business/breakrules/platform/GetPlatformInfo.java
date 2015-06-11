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

public class GetPlatformInfo {

	/**
	 * 根据省代码，获取支持城市参数接口 
	 * province string N 默认全部，省份简写，如：ZJ、JS dtype string N
	 * 返回数据格式：json或xml或jsonp,默认json callback String N 返回格式选择jsonp时，必须传递 key
	 * string Y 你申请的key
	 */
	public static String getCityList(String province) {
		String url = PlatFormConfig.breakrules_citylist_url + "province="
				+ province + "&dtype=json&callback=&key="
				+ PlatFormConfig.breakrules_key;
		System.out.println("breakrules_citylist_url" + url);
		String charset = "UTF-8";
		String json = get(url, charset);
		System.out.println("breakrules_citylist_response_json" + json);
		return json;
	}

		
		/**
		 * 20141128
		 * @param engineno 发动机号
		 * @param classno  车架号
		 * @param hphm 号牌号码
		 * @return
		 */
		public static String getInfo(String city_code,String engineno,String classno,String hphm)
		{
			String url=PlatFormConfig.breakrules_citylist_url1+city_code+"&key="+PlatFormConfig.breakrules_key1;
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
			url = url+hphm+engineno+classno;
			String charset = "UTF-8";
			String json = get(url, charset);
			System.out.println("breakrules_citylist_response_json" + json);
			return json;
		}
	/**
	 * 20141128
	 * 根据省代码，获取支持城市参数接口 
	 * province string N 默认全部，省份简写，如：ZJ、JS dtype string N
	 * 返回数据格式：json或xml或jsonp,默认json callback String N 返回格式选择jsonp时，必须传递 key
	 * string Y 你申请的key
	 */
	public static String getAllCityList() {
		String url = "http://v.juhe.cn/wz/citys?key=35d4681985d16ac0657233a7aab76fe5&dtype=json&province=&format=";
		System.out.println("breakrules_allcitylist_url" + url);
		String charset = "UTF-8";
		String json = get(url, charset);
		System.out.println("breakrules_allcitylist_response_json" + json);
		return json;
	}
	/*
	 * dtype string 是 返回数据格式：json或xml或jsonp,默认json callback String 否
	 * 返回格式选择jsonp时，必须传递 key string 是 你申请的key city String 是 城市代码 * hphm String 是
	 * 号牌号码 完整7位 * hpzl String 是 号牌类型，默认02 engineno String 否 发动机号 (根据城市接口中的参数填写)
	 * classno String 否 车架号 (根据城市接口中的参数填写)
	 * 
	 */
	public static String getBreakInfo(String city, String hphm, String hpzl,
			String engineno, String classno) throws UnsupportedEncodingException {
		String json ="";
		String encode_hphm = URLEncoder.encode(hphm, "UTF-8");
		String url = PlatFormConfig.breakrules_info_url+"dtype=json&callback=&key="
				+ PlatFormConfig.breakrules_key+"&city="+city+"&hphm="+encode_hphm+"&hpzl="+hpzl+"&engineno="+engineno+"&classno="+classno;
		String charset = "UTF-8";
		System.out.println("breakrules_citylist_url:" + url);
		json = get(url, charset);
		System.out.println("breakrules_info_response_json:" + json);
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

}
