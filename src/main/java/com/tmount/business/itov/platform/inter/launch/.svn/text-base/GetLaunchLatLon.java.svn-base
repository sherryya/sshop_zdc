package com.tmount.business.itov.platform.inter.launch;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.launch.util.UtilDate;

public class GetLaunchLatLon {
	/**
	 * 得到经纬度值
	 * @param deviceuid
	 * @param date
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public static String getLatLon_Json(String deviceuid, String date,String starttime, String endtime) 
	{

		Map<String, String> params = new HashMap<String, String>();
		String json = "";
		// 根据应用ID号查询获得对应设备列表信息
		// 1.比较开始时间和结束时间，如果开始时间大于结束时间，则要分两次查询
		try {
			if (UtilDate.compareToDate(date, starttime, endtime)) {
				params.put("deviceuid", deviceuid);
				params.put("date", date);
				params.put("starttime", starttime);
				params.put("endtime", endtime);
				json = GetLaunchInfo.getGPSInfo(params);
				json = URLDecoder.decode(json, "utf-8");
				return json;
			} else {
				// 如果开始时间大于结束时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date current_date = sdf.parse(date);
				Calendar c = Calendar.getInstance();
				c.setTime(current_date);
				int day = c.get(Calendar.DATE);
				c.set(Calendar.DATE, day - 1);
				String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
				// 取第一天数据
				params.put("deviceuid", deviceuid);
				params.put("date", dayBefore);
				params.put("starttime", starttime);
				params.put("endtime", "23:59:59");
				json = GetLaunchInfo.getGPSInfo(params);
				json = URLDecoder.decode(json, "utf-8");
				System.out.println("firstDayJson=" + json);
				// 取第二天数据
				params.put("deviceuid", deviceuid);
				params.put("date", date);
				params.put("starttime", "00:00:00");
				params.put("endtime", endtime);
				String jsonSecondDay = GetLaunchInfo.getGPSInfo(params);
				jsonSecondDay = URLDecoder.decode(jsonSecondDay, "utf-8");
				System.out.println("secondDayJson=" + jsonSecondDay);
				int bodyIndex = jsonSecondDay.indexOf("\"Data\":[");
				if (bodyIndex == -1) {
					throw new Exception("未找到Data!");
				}
				String bodyStr = jsonSecondDay.substring(bodyIndex + 8,	jsonSecondDay.length() - 2); // 只验证body里两个大括号中的内容，包括大括号。
				System.out.println("secondDayJsonStr=" + bodyStr);
				// 将bodyStr拼入json
				json = json.substring(0, json.length() - 2) + "," + bodyStr+ "]}";
				System.out.println("json=" + json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	public static String getLatLon(String deviceuid ,String starttime,String endtime)
	{
		String date = UtilDate.getStringDateShort(endtime);
		String starttimeT = UtilDate.getTimeShort(starttime);
		String endtimeT =UtilDate.getTimeShort(endtime);
		String start_Latitude="";
		String start_Longitude="";
		String end_Latitude="";
		String end_Longitude="";
		try {
			String jscontent = new GetLaunchLatLon().getLatLon_Json(deviceuid, date,	starttimeT, endtimeT);
			ObjectMapper objectmappercontent = new ObjectMapper();
			JsonNode jsonNodeContent = objectmappercontent.readTree(jscontent);
			String str_data=jsonNodeContent.get("Data").toString();
			ObjectMapper object_json = new ObjectMapper();
			JsonNode json_data = object_json.readTree(str_data);
			Integer icount=json_data.size();
			if(icount>1)  
			{
				String start_latlon=json_data.get(0).toString();
				ObjectMapper object_json_start = new ObjectMapper();
				JsonNode json_data_start = object_json_start.readTree(start_latlon);
				start_Latitude=json_data_start.get("Latitude").asText();
				start_Longitude=json_data_start.get("Longitude").asText();
				String end_latlon=json_data.get(icount-1).toString();
				ObjectMapper object_json_end = new ObjectMapper();
				JsonNode json_data_end = object_json_end.readTree(end_latlon);
				end_Latitude=json_data_end.get("Latitude").asText();
				end_Longitude=json_data_end.get("Longitude").asText();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return start_Latitude+","+start_Longitude+","+end_Latitude+","+end_Longitude;
	}
	
	public static void main(String[] args) {
		String deviceuid = "9D0C35C1-71BC-89E6-C7DE-EB6DB194F778";
		String date = "2014-05-29";
		String starttime = "2014-05-29 01:10:10";
		String endtime = "2014-05-29 23:11:11";
	    System.out.println( new GetLaunchLatLon().getLatLon(deviceuid,starttime, endtime));	
		
	}
}
