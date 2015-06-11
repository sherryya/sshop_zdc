package com.tmount.business.itov.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetGPSInfoperson extends ControllerBase {
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// 根据应用ID号查询获得对应设备列表信息
		Map<String, String> params = new HashMap<String, String>();
		String deviceuid = new String(ParamData.getString(
				requestParam.getBodyNode(), "deviceuid"));
		String date = new String(ParamData.getString(
				requestParam.getBodyNode(), "date"));

		String starttime = new String(ParamData.getString(
				requestParam.getBodyNode(), "starttime"));
		String utcEndTime = "";
		String utcStartTime = "";
		String utcStartTime_first_day="";
		String utcStartTime_secode_day ="";
		Double endTime = 0d;
		Double startTime = 0d;
		Double startTime_first_day = 0d;
		Double startTime_secode_day = 0d;
		Date time = null;
		Date timeend = null;
		try {
			SimpleDateFormat sdf_all = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			sdf_all.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			System.out.println(date + " " + starttime);
			//time = sdf_all.parse(date + " " + starttime);
			time = sdf_all.parse(starttime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeZone(TimeZone.getTimeZone("GMT-0"));
			calendar.setTime(time);
			System.out.println(calendar.getTimeInMillis());
			utcStartTime = calendar.getTimeInMillis() / 1000 + "";
			startTime = Double.parseDouble(utcStartTime);
			/*
			 * Long timecode =time.UTC(time.getYear(), time.getMonth(),
			 * time.getDate(), time.getHours(), time.getMinutes(),
			 * time.getSeconds()); utcStartTime = timecode/1000+""; startTime =
			 * Double.parseDouble(utcStartTime);
			 * System.out.println("utcStartTime"+utcStartTime);
			 */
			SimpleDateFormat sdf_allend = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			sdf_allend.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String endtime = new String(ParamData.getString(
					requestParam.getBodyNode(), "endtime"));

			//timeend = sdf_allend.parse(date + " " + endtime);// "1381198083000"
			timeend = sdf_allend.parse(endtime);												// 1381226883000
			Calendar calendarend = Calendar.getInstance();
			calendarend.setTimeZone(TimeZone.getTimeZone("GMT-0"));
			calendarend.setTime(timeend);
			System.out.println(calendarend.getTimeInMillis());
			utcEndTime = calendarend.getTimeInMillis() / 1000 + "";
			endTime = Double.parseDouble(utcEndTime);
			/*
			 * utcEndTime=(timeend.UTC(timeend.getYear(), timeend.getMonth(),
			 * timeend.getDate(), timeend.getHours(), timeend.getMinutes(),
			 * timeend.getSeconds()))/1000+""; endTime =
			 * Double.parseDouble(utcEndTime);
			 * System.out.println("utcEndTime"+utcEndTime);
			 */

			String mapType = new String(ParamData.getString(
					requestParam.getBodyNode(), "mapType"));// 0—百度 1—图吧 2—高德

			// 根据开始时间和结束时间去寻找对应的历史GPS信息,magobd数据库
			responseBodyJson.writeArrayFieldStart("Data");
			//Mongo mongo = new Mongo("125.211.221.231", 10900);
			Mongo mongo = new Mongo("172.16.1.101", 30000);
			DB db = mongo.getDB("cardb");
			boolean connectFlag = db.authenticate("dbcaradm",
					"dbcar".toCharArray());
			if (connectFlag) {
				// 判断开始时间和结束时间
				if (time.before(timeend)) {
					System.out
							.println("t_ov_gpshis" + date.replaceAll("-", ""));
					DBCollection gpshis = db.getCollection("t_ov_gpshis"
							+ date.replaceAll("-", ""));
					BasicDBObject condition = new BasicDBObject();
					// 比较符 "$gt"： 大于 "$gte"：大于等于
					// "$lt"： 小于
					// "$lte"：小于等于
					// "$in"： 包含
					// 以下条件查询20<=age<30
					condition.put("deviceuid", deviceuid);
					condition.put("gpstime", new BasicDBObject("$gte",
							startTime).append("$lt", endTime));
					// condition.put("gpstime", new
					// BasicDBObject("$gte","1386520952000").append("$lt", "1386540364000"));
					DBCursor dbcursor = gpshis.find(condition);
					while (dbcursor.hasNext()) {
						String dbcursor_message = dbcursor.next().toString();
						if (StringUtils.isNotEmpty(dbcursor_message)) {
							responseBodyJson.writeStartObject();
							ObjectMapper objectmapper = new ObjectMapper();
							JsonNode dbcursorJson = objectmapper
									.readTree(dbcursor_message);
							responseBodyJson.writeNumberField("Latitude",
									dbcursorJson.get("convert_latitude")
											.doubleValue());
							responseBodyJson.writeNumberField("Longitude",
									dbcursorJson.get("convert_longitude")
											.doubleValue());
							responseBodyJson
									.writeNumberField("Direction", dbcursorJson
											.get("direction").doubleValue());
							responseBodyJson.writeEndObject();
						}
					}
				} else {
					// 如果开始时间大于结束时间
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date current_date = sdf.parse(date);
					Calendar c = Calendar.getInstance();
					c.setTime(current_date);
					int day = c.get(Calendar.DATE);
					c.set(Calendar.DATE, day - 1);

					String dayBefore = new SimpleDateFormat("yyyy-MM-dd")
							.format(c.getTime());
					//1.查询前一天的数据
					DBCollection gpshis = db.getCollection("t_ov_gpshis"
							+ dayBefore.replaceAll("-", ""));
					BasicDBObject condition = new BasicDBObject();
					// 比较符 "$gt"： 大于 "$gte"：大于等于
					// "$lt"： 小于
					// "$lte"：小于等于
					// "$in"： 包含
					// 以下条件查询20<=age<30
					condition.put("deviceuid", deviceuid);
					SimpleDateFormat sdf_all_first_day = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					sdf_all_first_day.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
					System.out.println(dayBefore + " " + starttime);
					time = sdf_all_first_day.parse(dayBefore + " " + starttime);
					Calendar calendar_first_start = Calendar.getInstance();
					calendar_first_start.setTimeZone(TimeZone.getTimeZone("GMT-0"));
					calendar_first_start.setTime(time);
					System.out.println(calendar_first_start.getTimeInMillis());
					utcStartTime = calendar_first_start.getTimeInMillis() / 1000 + "";
					startTime = Double.parseDouble(utcStartTime);
					
					
					sdf_all_first_day.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
					time = sdf_all_first_day.parse(dayBefore + " " + "23:59:59");
					Calendar calendar_first_day = Calendar.getInstance();
					calendar_first_day.setTimeZone(TimeZone.getTimeZone("GMT-0"));
					calendar_first_day.setTime(time);
					utcStartTime_first_day = calendar_first_day.getTimeInMillis() / 1000 + "";
					startTime_first_day = Double.parseDouble(utcStartTime_first_day);
					
					System.out.println("开始查询时间："+startTime);
					System.out.println("结束查询时间："+startTime_first_day);
					condition.put("gpstime", new BasicDBObject("$gte",
							startTime).append("$lt", startTime_first_day));
					DBCursor dbcursor = gpshis.find(condition);
					while (dbcursor.hasNext()) {
						String dbcursor_message = dbcursor.next().toString();
						if (StringUtils.isNotEmpty(dbcursor_message)) {
							responseBodyJson.writeStartObject();
							ObjectMapper objectmapper = new ObjectMapper();
							JsonNode dbcursorJson = objectmapper
									.readTree(dbcursor_message);
							responseBodyJson.writeNumberField("Latitude",
									dbcursorJson.get("convert_latitude")
											.doubleValue());
							responseBodyJson.writeNumberField("Longitude",
									dbcursorJson.get("convert_longitude")
											.doubleValue());
							responseBodyJson
									.writeNumberField("Direction", dbcursorJson
											.get("direction").doubleValue());
							responseBodyJson.writeEndObject();
						}
					}
					//2.查询第二天的数据
					DBCollection gpshis_secode = db.getCollection("t_ov_gpshis"
							+ date.replaceAll("-", ""));
					BasicDBObject condition_secode = new BasicDBObject();
					// 比较符 "$gt"： 大于 "$gte"：大于等于
					// "$lt"： 小于
					// "$lte"：小于等于
					// "$in"： 包含
					// 以下条件查询20<=age<30
					condition_secode.put("deviceuid", deviceuid);
					SimpleDateFormat sdf_all_secode_day = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					sdf_all_secode_day.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
					time = sdf_all_secode_day.parse(date + " " + "00:00:00");
					Calendar calendar_secode_day = Calendar.getInstance();
					calendar_secode_day.setTimeZone(TimeZone.getTimeZone("GMT-0"));
					calendar_secode_day.setTime(time);
					utcStartTime_secode_day = calendar_secode_day.getTimeInMillis() / 1000 + "";
					startTime_secode_day = Double.parseDouble(utcStartTime_secode_day);
					System.out.println("开始查询时间："+startTime_secode_day);
					System.out.println("结束查询时间："+endTime);
					condition_secode.put("gpstime", new BasicDBObject("$gte",
							startTime_secode_day).append("$lt", endTime));
					DBCursor dbcursor_secode = gpshis_secode.find(condition);
					while (dbcursor_secode.hasNext()) {
						String dbcursor_message = dbcursor_secode.next().toString();
						if (StringUtils.isNotEmpty(dbcursor_message)) {
							responseBodyJson.writeStartObject();
							ObjectMapper objectmapper = new ObjectMapper();
							JsonNode dbcursorJson = objectmapper
									.readTree(dbcursor_message);
							responseBodyJson.writeNumberField("Latitude",
									dbcursorJson.get("convert_latitude")
											.doubleValue());
							responseBodyJson.writeNumberField("Longitude",
									dbcursorJson.get("convert_longitude")
											.doubleValue());
							responseBodyJson
									.writeNumberField("Direction", dbcursorJson
											.get("direction").doubleValue());
							responseBodyJson.writeEndObject();
						}
					}
				}

			}
			responseBodyJson.writeEndArray();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "gpsinfoperson.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);

	}

}
