package com.tmount.business.zdjs.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetGPSInfoController extends ControllerBase {
	String json = "";
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// 根据应用ID号查询获得对应设备列表信息
		Map<String, String> params = new HashMap<String, String>();
		String deviceuid = new String (ParamData.getString(requestParam.getBodyNode(), "deviceuid"));
		String date = new String (ParamData.getString(requestParam.getBodyNode(), "date"));
		String starttime = new String (ParamData.getString(requestParam.getBodyNode(), "starttime"));
		String endtime = new String (ParamData.getString(requestParam.getBodyNode(), "endtime"));
		//1.比较开始时间和结束时间，如果开始时间大于结束时间，则要分两次查询
		try{
			if(compareToDate(date,starttime,endtime)){
				params.put("deviceuid", deviceuid);
				params.put("date", date);
				params.put("starttime", starttime);
				params.put("endtime", endtime);
				json= GetLaunchInfo.getGPSInfo(params);
				json = URLDecoder.decode(json,"utf-8");
				responseBodyJson.writeStringField("deviceuid", deviceuid);
				getGPS(responseBodyJson,json);
				
			}else{
				// 如果开始时间大于结束时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date current_date = sdf.parse(date);
				Calendar c = Calendar.getInstance();
				c.setTime(current_date);
				int day = c.get(Calendar.DATE);
				c.set(Calendar.DATE, day - 1);
				String dayBefore = new SimpleDateFormat("yyyy-MM-dd")
						.format(c.getTime());
				//取第一天数据
				params.put("deviceuid", deviceuid);
				params.put("date", dayBefore);
				params.put("starttime", starttime);
				params.put("endtime", "23:59:59");
				json= GetLaunchInfo.getGPSInfo(params);
				json = URLDecoder.decode(json,"utf-8");
				System.out.println("firstDayJson="+json);
				//取第二天数据
				params.put("deviceuid", deviceuid);
				params.put("date", date);
				params.put("starttime", "00:00:00");
				params.put("endtime", endtime);
				String jsonSecondDay= GetLaunchInfo.getGPSInfo(params);
				jsonSecondDay = URLDecoder.decode(jsonSecondDay,"utf-8");
				System.out.println("secondDayJson="+jsonSecondDay);
				int bodyIndex = jsonSecondDay.indexOf("\"Data\":["); 
				if (bodyIndex == -1) {
					throw new Exception("未找到Data!");
				}
				String bodyStr = jsonSecondDay.substring(bodyIndex + 8,
						jsonSecondDay.length() - 2); // 只验证body里两个大括号中的内容，包括大括号。
				System.out.println("secondDayJsonStr="+bodyStr);
				//将bodyStr拼入json
				json =json.substring(0, json.length()-2)+","+bodyStr+"]}";
				System.out.println("json="+json);
				getGPS(responseBodyJson,json);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "zdjs.gpsinfo.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}	 
	private void getGPS(JsonGenerator responseBodyJson ,String json) throws Exception
	{		
		ObjectMapper objectmapper = new ObjectMapper();
		JsonNode dbcursorJson = objectmapper.readTree(json);
		Iterator it_data = dbcursorJson.get("Data").iterator();
		responseBodyJson.writeArrayFieldStart("Data");
		while (it_data.hasNext()) {
			JsonNode json_sub = (JsonNode) it_data.next();
			String Latitude = json_sub.get("Latitude").asText();
			String Longitude=json_sub.get("Longitude").asText();
			String GPSTimeInDefaultTimeZone=json_sub.get("GPSTimeInDefaultTimeZone").textValue();
			responseBodyJson.writeStartObject();
			// 经度
			//String lonlat=getLatLon(Longitude,Latitude);
			responseBodyJson.writeStringField("lonlat",Longitude+","+Latitude);
			responseBodyJson.writeStringField("GPSTimeInDefaultTimeZone",GPSTimeInDefaultTimeZone);
			responseBodyJson.writeEndObject();
		}
		responseBodyJson.writeEndArray();
	}
	
	private String getLatLon(String Longitude,String Latitude) throws Exception
	{
	    String param = Longitude + "," + Latitude ;
	    String retMsg="";
	    String retXml="";
	    retXml = readContentFromGet(param);
		Document document = DocumentHelper.parseText(retXml);
		Element el_req = document.getRootElement();
		List lonlatList = el_req.element("item").elements();
		if (lonlatList != null && lonlatList.size() > 0) {
			for (int j = 0; j < lonlatList.size(); j++) {
				retMsg =  ((Element) lonlatList.get(j)).getText();
			}
		}
		return retMsg;
	}
	public boolean compareToDate(String date,String starttime,String endtime){
		boolean flag =false;
		try{
			SimpleDateFormat sdf_all = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			sdf_all.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			Date startTime = sdf_all.parse(date + " " + starttime);
			Date endTime = sdf_all.parse(date + " " + endtime);
			if(startTime.before(endTime)){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return flag;
	}
	 public static  String  readContentFromGet(String param)  throws  java.io.IOException{
         String url = "http://geocode.mapbar.com/Decode/encode_xml.jsp?lonlat=";
         url = url +param;
         java.net.URL getUrl  =   new  URL(url);
         HttpURLConnection connection  =  (HttpURLConnection) getUrl.openConnection();
     	 connection.setConnectTimeout(100000);
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
}
