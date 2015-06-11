package com.tmount.business.itov.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Decoder;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.business.itov.platform.inter.launch.GetLaunchLatLon;
import com.tmount.business.itov.service.ComparatorKilometerObject;
import com.tmount.db.itov.dto.MessageDriverKilometer;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MapUtil;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class Getdatelistin30daysperson extends ControllerBase {
	Logger logger = Logger.getLogger(Getdatelistin30daysperson.class);
	String json = "";

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// 根据应用ID号查询获得对应设备列表信息
		Map<String, String> params = new HashMap<String, String>();
		String deviceuid = new String(ParamData.getString(requestParam
				.getBodyNode(), "deviceuid"));
		String checkDate = new String(ParamData.getString(requestParam
				.getBodyNode(), "checkDate"));// YYYy-MM-DD
		params.put("deviceuid", deviceuid);
		List<MessageDriverKilometer> list = new ArrayList();

		// 调用查询里程接口
		String starttime = "00:00:00";
		String endtime = "23:59:59";
		String pagesize = "100";
		String targetpage = "1";

		params.put("deviceuid", deviceuid);
		params.put("date", checkDate);
		params.put("starttime", starttime);
		params.put("endtime", endtime);
		params.put("pagesize", pagesize);
		params.put("targetpage", targetpage);


		json = GetLaunchInfo.getlistbypage(params);
		json = URLDecoder.decode(json, "utf-8");
		System.out.println(json);

		ObjectMapper listByPageMaaper = new ObjectMapper();
		if (StringUtils.isNotEmpty(json)) {
			JsonNode listByPageJson = listByPageMaaper.readTree(json);
			// 1.解析listByPageJson
			if (listByPageJson != null) {
				JsonNode json_data = listByPageJson.get("Data");
				Iterator it_data = json_data.iterator();
				while (it_data.hasNext()) {
					JsonNode json_sub = (JsonNode) it_data.next();
					String jscontent = json_sub.get("JsonContent").textValue();
					ObjectMapper objectmappercontent = new ObjectMapper();
					JsonNode jsonNodeContent = objectmappercontent
							.readTree(jscontent);
					Iterator it = jsonNodeContent.iterator();
					MessageDriverKilometer messagedriverkilometer = new MessageDriverKilometer();// 用于存在list中，将来做比较
					while (it.hasNext()) {
						JsonNode json_sub_content = (JsonNode) it.next();
						String DFDataStreamID = json_sub_content.get(
								"DFDataStreamID").textValue();
						if ("0000E002".equals(DFDataStreamID)) {
							// 本次行驶里程
							logger.info("~~~~~~~~~~~~bencilicheng~~~~~~~~~~~~~~~~"+json_sub_content.get(
											"DFDataStreamValue").toString());
							messagedriverkilometer
									.setDriverKilometer(json_sub_content.get(
											"DFDataStreamValue").toString());
						} else if ("0000E004".equals(DFDataStreamID)) {
							logger.info("~~~~~~~~~~~~benciyouhao~~~~~~~~~~~~~~~~"+json_sub_content.get(
							"DFDataStreamValue").toString());
							// 本次耗油
							messagedriverkilometer
									.setOilDeplete(json_sub_content.get(
											"DFDataStreamValue").toString());
						} else if ("0000E005".equals(DFDataStreamID)) {
							// 本次行驶时间
							messagedriverkilometer
									.setDriverTime(json_sub_content.get(
											"DFDataStreamValue").toString());
						}  else if ("0000E007".equals(DFDataStreamID)) {
							// 怠速次数
							logger.info("~~~~~~~~~~~~daisucishu~~~~~~~~~~~~~~~~"+json_sub_content.get(
							"DFDataStreamValue").toString());
							messagedriverkilometer
									.setIdlingNumber(json_sub_content.get(
											"DFDataStreamValue").toString());
						} else if ("0000E006".equals(DFDataStreamID)) {
							// 怠速时间
							logger.info("~~~~~~~~~~~~daisushijian~~~~~~~~~~~~~~~~"+json_sub_content.get(
							"DFDataStreamValue").toString());
							messagedriverkilometer
									.setIdlingTime(json_sub_content.get(
											"DFDataStreamValue").toString());

						}
					}
					String startTime = json_sub.get( "TripStartTime").asText();
					logger.info("~~~~~~~~~~~~startTime~~~~~~~~~~~~~~~~"+startTime);
					messagedriverkilometer.setStartTime(startTime);
					messagedriverkilometer.setStartTimeStr(startTime);
					String endTime = json_sub.get( "TripEndTime").asText();
					logger.info("~~~~~~~~~~~~endTime~~~~~~~~~~~~~~~~"+endTime);
					messagedriverkilometer.setEndTime(endTime);
					messagedriverkilometer.setEndTimeStr(endTime);
					list.add(messagedriverkilometer);
				}

			}
		}

		// 3.所有解析完成之后，进行list.sort的比较开始时间
		if (list != null && list.size() > 1) {
			ComparatorKilometerObject comparator = new ComparatorKilometerObject();
			Collections.sort(list, comparator);
		}
		responseBodyJson.writeArrayFieldStart("Data");
		for (MessageDriverKilometer meter : list) {
			if(meter.getDriverTime().equalsIgnoreCase("0"))
				continue;
			responseBodyJson.writeStartObject();
			// 本次行驶里程
			responseBodyJson.writeStringField("driverKilometer", meter
					.getDriverKilometer());
			// 本次耗油
			responseBodyJson.writeStringField("oilDeplete", meter
					.getOilDeplete());
			// 开始时间
			responseBodyJson
					.writeStringField("startTime", meter.getStartTime());
			// 本次结束时间
			responseBodyJson.writeStringField("endTime", meter.getEndTime());
			// 本次行驶时间
			responseBodyJson.writeStringField("driverTime", meter
					.getDriverTime());
			// 本次行驶急减速次数
			responseBodyJson.writeStringField("speedFastDownNumber","0");
			// 怠速次数
			responseBodyJson.writeStringField("idlingNumber", meter
					.getIdlingNumber());

			// 怠速时间
			responseBodyJson.writeStringField("idlingTime", meter
					.getIdlingTime());
			//根据开始时间和结束时间，来查询对应的开始经纬度和结束经纬度
			String message = GetLaunchLatLon.getLatLon(deviceuid, meter.getStartTime(), meter.getEndTime());
			String start_Latitude = "";
			String start_Longitude="";
			String end_Latitude = "";
			String end_Longitude ="";
			if(!",,,".equals(message)){
				String[] message_array = message.split(",");
				// 开始经度
			    start_Latitude = message_array[1];
				logger.info("~~~~~~~~~~~~kaishijingdu~~~~~~~~~~~~~~~~"+start_Latitude);
				// 开始纬度
				 start_Longitude = message_array[0];
				logger.info("~~~~~~~~~~~~kaishiweidu~~~~~~~~~~~~~~~~"+start_Longitude);
				// 结束经度
				 end_Latitude = message_array[3];
				logger.info("~~~~~~~~~~~~jieshujingdu~~~~~~~~~~~~~~~~"+end_Latitude);
				// 结束纬度
				 end_Longitude =message_array[2];
				logger.info("~~~~~~~~~~~~jieshuweidu~~~~~~~~~~~~~~~~"+end_Longitude);
				if (StringUtils.isNotEmpty(start_Latitude)
						&& StringUtils.isNotEmpty(start_Longitude)) {
					// //调用百度地图api,得到百度坐标
					String param_mapbar = start_Latitude + "," + start_Longitude;
					System.out.println("startPointparam_mapbar:" + param_mapbar);

					JsonNode jsonNodeMap = MapUtil.getMap(start_Latitude,
							start_Longitude);

					Iterator itgetMap = jsonNodeMap.iterator();
					String lonlat = "";
					while (itgetMap.hasNext()) {
						JsonNode js = (JsonNode) itgetMap.next();
						String LatitudeBase64 = js.get("x").textValue();
						String LongitudeBase64 = js.get("y").textValue();
						// 解析base64串
						String LongitudeStr = new String(new BASE64Decoder()
								.decodeBuffer(LatitudeBase64));
						String LatitudeStr = new String(new BASE64Decoder()
								.decodeBuffer(LongitudeBase64));
						lonlat = LatitudeStr + "," + LongitudeStr;
					}

					System.out.println("startPointlonlat" + lonlat);

					// 调用百度吧地图api，获取百度逆地理名称
					String param = "";
					param += "&output=json&pois=1&location=";
					param += lonlat;

					String xml = MapUtil.getBaiduInverseGeographic(param);
					xml = URLDecoder.decode(xml, "utf-8");
					System.out.println("retJson=" + xml);
					// 解析json
					String retMsg = "";
					if (StringUtils.isNotEmpty(xml)) {
						ObjectMapper mappers = new ObjectMapper();
						JsonNode jsonNodeResver = mappers.readTree(xml);
						JsonNode comment = jsonNodeResver.get("result").get(
								"addressComponent");
						retMsg = comment.get("province").textValue()
								+ comment.get("city").textValue()
								+ comment.get("district").textValue()
								+ comment.get("street").textValue()
								+ comment.get("street_number").textValue();
					}

					responseBodyJson.writeStringField("startLocation", retMsg);
				}
				if (StringUtils.isNotEmpty(end_Latitude)
						&& StringUtils.isNotEmpty(end_Longitude)) {
					// //调用百度地图api,得到百度坐标
					String param_mapbar = end_Latitude + "," + end_Longitude;
					System.out.println("endPointparam_mapbar:" + param_mapbar);

					JsonNode jsonNodeMap = MapUtil.getMap(end_Latitude,
							end_Longitude);

					Iterator itgetMap = jsonNodeMap.iterator();
					String lonlat = "";
					while (itgetMap.hasNext()) {
						JsonNode js = (JsonNode) itgetMap.next();
						String LatitudeBase64 = js.get("x").textValue();
						String LongitudeBase64 = js.get("y").textValue();
						// 解析base64串
						String LongitudeStr = new String(new BASE64Decoder()
								.decodeBuffer(LatitudeBase64));
						String LatitudeStr = new String(new BASE64Decoder()
								.decodeBuffer(LongitudeBase64));
						lonlat = LatitudeStr + "," + LongitudeStr;
					}

					System.out.println("endPointlonlat" + lonlat);

					// 调用百度吧地图api，获取百度逆地理名称
					String param = "";
					param += "&output=json&pois=1&location=";
					param += lonlat;
					String xml = MapUtil.getBaiduInverseGeographic(param);
					xml = URLDecoder.decode(xml, "utf-8");
					System.out.println("retJson=" + xml);
					// 解析json
					String retMsg = "";
					if (StringUtils.isNotEmpty(xml)) {
						ObjectMapper mappers = new ObjectMapper();
						JsonNode jsonNodeResver = mappers.readTree(xml);
						JsonNode comment = jsonNodeResver.get("result").get(
								"addressComponent");
						retMsg = comment.get("province").textValue()
								+ comment.get("city").textValue()
								+ comment.get("district").textValue()
								+ comment.get("street").textValue()
								+ comment.get("street_number").textValue();
					}
					responseBodyJson.writeStringField("endLocation", retMsg);
				}
			}else{
				responseBodyJson.writeStringField("startLocation", "暂无数据");
				responseBodyJson.writeStringField("endLocation", "暂无数据");
			}
			
			responseBodyJson.writeEndObject();
		}
		responseBodyJson.writeEndArray();

	}

	@RequestMapping(value = "datelistin30daysperson.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);

	}
}
