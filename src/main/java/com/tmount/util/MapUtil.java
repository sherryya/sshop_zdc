package com.tmount.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.ptt.controller.startService;
import com.tmount.business.util.ClientHelper;
import com.tmount.db.itov.dto.MessageDriverKilometer;

public class MapUtil {

	// private static final String url =
	// "http://api.map.baidu.com/ag/coord/convert?from=2&to=4";
	private static final String url = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&mode=1";
	static Logger logger = Logger.getLogger(startService.class.getName());
	public static final String LOGON_SITE_LOGIN = "10.110.0.206";
	public static final int LOGON_PORT = 28001;
	public static final String BAIDU_InverseGeographic= "http://api.map.baidu.com/geocoder/v2/?ak=5PcpmkXyitGo3RbBZ93KcsF4";//百度逆地理url
	//查询驾车导航地标点名称列表

	//city=%B1%B1%BE%A9%CA%D0&keyword=%E9%98%9C%E6%88%90%E9%97%A8&searchType=route&customer=2
	/*
	 * 
	 * 将gps坐标转化为百度坐标126.622044,45.711547
	 */
	public static JsonNode getMap(String Latitude, String Longitude) {
		try {
			ClientHelper ch = new ClientHelper(LOGON_SITE_LOGIN, LOGON_PORT);
			String param = "&x=" + Latitude + "&y=" + Longitude;
			System.out.println(url + param);
			String message = ch.get(url + param);
			ObjectMapper om = new ObjectMapper();
			return om.readTree(message);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 
	 * 百度逆地理
	 */
	public static String  getBaiduInverseGeographic(String  param) {
		String url = BAIDU_InverseGeographic + param;
		String retStr = "";
		try{
			java.net.URL getUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			connection.setRequestProperty("contentType", "utf-8");
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			
			String lines;
			while ((lines = reader.readLine()) != null) {
				retStr = retStr + lines;
			}
			reader.close();
			connection.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return retStr;
	}
	
	/*
	 * 
	 * 图吧坐标转化
	 */
	
	public static String  getMapBar(String param)  throws  java.io.IOException{
        String url = "http://geocode.mapbar.com/Decode/encode_xml.jsp?lonlat=";
        url = url +param;
        java.net.URL getUrl  =   new  URL(url);
        HttpURLConnection connection  =  (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        BufferedReader reader  =   new  BufferedReader( new  InputStreamReader(connection.getInputStream()));
        String retStr="";
        String lines;
        while((lines  =  reader.readLine())  !=   null ){
            retStr=retStr+lines;
        } 
        reader.close();
        connection.disconnect();
        return retStr;
    } 
	
	/*
	 * 
	 * 图吧逆地理
	 */
	public static String readContentFromGet(String param)
			throws java.io.IOException {
		String url = "http://geocode.mapbar.com/dynamic/inverse/getInverseGeocoding.jsp?";
		url = url + param;
		java.net.URL getUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestProperty("contentType", "utf-8");
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		String retStr = "";
		String lines;
		while ((lines = reader.readLine()) != null) {
			retStr = retStr + lines;
		}
		reader.close();
		connection.disconnect();
		return retStr;

	}

	/**
	 * 百度逆地理解析
	 * @param parsm
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static String getBaiduInverseGeographic1(String parsm) throws IOException, DocumentException
	{
		//String url="http://api.map.baidu.com/geocoder/v2/?ak=VaHbzWGtaGeasqewm5rrLHXs&callback=renderReverse&location="+parsm+"&output=json&pois=0";
		String url="http://api.map.baidu.com/geocoder/v2/?ak=VaHbzWGtaGeasqewm5rrLHXs&callback=renderReverse&location="+parsm+"&output=json&pois=0";
		java.net.URL getUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestProperty("contentType", "utf-8");
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		String retStr = "";
		String lines;
		while ((lines = reader.readLine()) != null) {
			retStr = retStr + lines;
		}
		reader.close();
		connection.disconnect();
		retStr=  retStr.replace("renderReverse&&renderReverse(", "").substring(0, retStr.replace("renderReverse&&renderReverse(", "").length()-1);
		ObjectMapper objectmapper = new ObjectMapper();
		JsonNode jsonNode = objectmapper.readTree(retStr);
		String formatted_address="";
		// 1.解析jsonNode
		if (jsonNode != null) {
			JsonNode json_data = jsonNode.get("result");
			formatted_address=	json_data.get("formatted_address").toString();
		}
		return formatted_address.replace("\"", "");
	}
	public static void test() {
		// TODO Auto-generated method stub
		try {
			String param = "";
			param += "detail=1&zoom=11&road=1&customer=2&latlon=";
			param += "116.33850,39.93834";
			String xml = readContentFromGet(param);

			Document document = DocumentHelper.parseText(xml);
			Element el_req = document.getRootElement();

			String retMsg = "";
			// 省
			if (el_req.element("province") != null) {
				retMsg += el_req.element("province").getText() + ",";
			}
			// 市
			if (el_req.element("city") != null) {
				retMsg += el_req.element("city").getText() + ",";
			}
			// 区县
			if (el_req.element("dist") != null) {
				retMsg += el_req.element("dist").getText() + ",";
			}
			// 区域
			if (el_req.element("area") != null) {
				retMsg += el_req.element("area").getText() + ",";
			}
			// 乡镇
			if (el_req.element("town") != null) {
				retMsg += el_req.element("town").getText() + ",";
			}
			// 村
			if (el_req.element("village") != null) {
				retMsg += el_req.element("village").getText() + ",";
			}
			// 附近地标
			if (el_req.element("poi") != null) {
				retMsg += el_req.element("poi").getText() + ",";
			}
			System.out.println("返回信息:" + retMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//double lon=12640.850240;
	//double lat=4544.661350;
	private static String getBaiduLocation(String str_lon,String str_lat)
	{
		 double lon=Double.valueOf(str_lon);
		 double lat=Double.valueOf(str_lat);
	     int i_lat=	(int)(lat/100);
	     int i_lon=	(int)(lon/100);
	     long l_lon=  Long.valueOf( String.valueOf(lon/100).replaceAll("\\d+\\.", ""))*100/60;
	     long l_lat=  Long.valueOf( String.valueOf(lat/100).replaceAll("\\d+\\.", ""))*100/60;
	     String s_lon= i_lon+"."+ l_lon;
	     String s_lat= i_lat+"."+l_lat;
	     return s_lon+","+s_lat;
	}
	public static void main(String[] args) throws IOException, DocumentException {

		String ret = getBaiduLocation("12640.850240", "4544.661350");
		String url="http://api.map.baidu.com/geoconv/v1/?coords=";
		//http://api.map.baidu.com/geoconv/v1/?coords=126.6808373,45.744355833333341&from=1&to=5&ak=flj1sH6FvA3RaxqrwXAVAprf
		System.out.println(ret);
		String retStr = "";
		try{
			url=url+ret+";"+ret+"&from=1&to=5&ak=flj1sH6FvA3RaxqrwXAVAprf";
			java.net.URL getUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.setRequestProperty("contentType", "utf-8");
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
				retStr = retStr + lines;
			}
			reader.close();
			connection.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		ObjectMapper objectmapper = new ObjectMapper();
		JsonNode jsonNode = objectmapper.readTree(retStr);
		// 1.解析jsonNode
		if (jsonNode != null) {
			int status = jsonNode.get("status").asInt();
			if (status == 0) {
				JsonNode json_data = jsonNode.get("result");
				Iterator it = json_data.iterator();
				while (it.hasNext()) {
					JsonNode json_sub_content = (JsonNode) it.next();
					System.out.println(json_sub_content.get("x"));
					System.out.println(json_sub_content.get("y"));
				}
			}
		}
	}
}
