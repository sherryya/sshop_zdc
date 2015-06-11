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
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.business.itov.platform.inter.launch.GetLaunchLatLon;
import com.tmount.business.itov.service.CompanyComparatorKilometerObject;
import com.tmount.db.itov.dto.MessageDriverKilometer;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MapUtil;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class Getlistbypage extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	String json = "";

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
		String endtime = new String(ParamData.getString(
				requestParam.getBodyNode(), "endtime"));
		String pagesize = new String(ParamData.getString(
				requestParam.getBodyNode(), "pagesize"));
		String targetpage = new String(ParamData.getString(
				requestParam.getBodyNode(), "targetpage"));

		params.put("deviceuid", deviceuid);
		params.put("date", date);
		params.put("starttime", starttime);
		params.put("endtime", endtime);
		params.put("pagesize", pagesize);
		params.put("targetpage", targetpage);
		json = GetLaunchInfo.getlistbypage(params);
		json = URLDecoder.decode(json, "utf-8");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~" + json);
		responseBodyJson.writeArrayFieldStart("Data");
		List<MessageDriverKilometer> list = new ArrayList();
		ObjectMapper objectmapper = new ObjectMapper();
		JsonNode jsonNode = objectmapper.readTree(json);
		// 1.解析jsonNode
		if (jsonNode != null) {
			JsonNode json_data = jsonNode.get("Data");
			Iterator it_data = json_data.iterator();
			while (it_data.hasNext()) {
				JsonNode json_sub = (JsonNode) it_data.next();
				String jscontent = json_sub.get("JsonContent").textValue();
				System.out.println(jscontent);
				ObjectMapper objectmappercontent = new ObjectMapper();
				JsonNode jsonNodeContent = objectmappercontent
						.readTree(jscontent);
				Iterator it = jsonNodeContent.iterator();
				MessageDriverKilometer messagedriverkilometer = new MessageDriverKilometer();// 用于存在list中，将来做比较
				while (it.hasNext()) {
					JsonNode json_sub_content = (JsonNode) it.next();
					String DFDataStreamID = json_sub_content.get(
							"DFDataStreamID").textValue();
					if ("0000E005".equals(DFDataStreamID)) {
						// 本次行驶时间
						// 本次行驶时间
						messagedriverkilometer.setDriverTime(json_sub_content
								.get("DFDataStreamValue").toString() + "min");
					} else if ("0000E002".equals(DFDataStreamID)) {
						// 本次行驶里程
						messagedriverkilometer
								.setDriverKilometer(json_sub_content.get(
										"DFDataStreamValue").toString()
										+ "km");
					} else if ("0000E004".equals(DFDataStreamID)) {
						// 本次耗油
						messagedriverkilometer.setOilDeplete(json_sub_content
								.get("DFDataStreamValue").toString() + "L");
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
			// 3.所有解析完成之后，进行list.sort的比较开始时间
			if (list != null && list.size() > 1) {
				CompanyComparatorKilometerObject comparator = new CompanyComparatorKilometerObject();
				Collections.sort(list, comparator);
			}
			for (MessageDriverKilometer meter : list) {
				if(meter.getDriverTime().equalsIgnoreCase("0min"))
					continue;
				responseBodyJson.writeStartObject();
				// 本次行驶时间
				
				responseBodyJson.writeStringField("totaltime",
						meter.getDriverTime());

				// 本次行驶里程
				responseBodyJson.writeStringField("totalkilo",
						meter.getDriverKilometer());

				// 本次耗油
				responseBodyJson.writeStringField("totaloil",
						meter.getOilDeplete());

				// 开始时间
				responseBodyJson.writeStringField("startTime",
						meter.getStartTime());
				// 本次结束时间
				responseBodyJson
						.writeStringField("endTime", meter.getEndTime());

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
						// //调用图吧地图api,得到图吧坐标
						String param_mapbar = start_Latitude + ","
								+ start_Longitude;
						System.out.println("param_mapbar:" + param_mapbar);
						String retXml = MapUtil.getMapBar(param_mapbar);
						System.out.println("retXml" + retXml);
						Document document_mapbar = null;
						try {
							document_mapbar = DocumentHelper.parseText(retXml);
						} catch (DocumentException e) {
							e.printStackTrace();
						}

						Element el_req_mapbar = document_mapbar.getRootElement();
						List lonlatList = el_req_mapbar.element("item").elements();
						String lonlat = "";
						if (lonlatList != null && lonlatList.size() > 0) {

							if ((Element) lonlatList.get(0) != null) {
								lonlat = ((Element) lonlatList.get(0)).getText();
							}
							System.out.println("mapbar=~~~~~~~~~~~~~~~~~~~~~~~~~~"
									+ lonlat);
						}

						// 调用图吧地图api，获取图吧逆地理名称
						String param = "";
						param += "detail=1&zoom=11&road=1&customer=2&latlon=";
						param += lonlat;
						String xml = MapUtil.readContentFromGet(param);
						System.out.println("解析参数："+param);
                        System.out.println("解析完的地址:"+xml);
						Document document = null;
						try {
							document = DocumentHelper.parseText(xml);
						} catch (DocumentException e) {
							e.printStackTrace();
						}
						Element el_req = document.getRootElement();
						String retMsg = "";
						if (el_req.element("poi") != null) {
							retMsg += el_req.element("poi").getText() + ",";
							retMsg = URLDecoder.decode(retMsg, "utf-8");
						}
						if(retMsg.length()>1)
						retMsg=retMsg.substring(0, retMsg.length()-1);
						responseBodyJson.writeStringField("startLocation", retMsg);
					}
					if (StringUtils.isNotEmpty(end_Latitude)
							&& StringUtils.isNotEmpty(end_Longitude)) {
						// //调用图吧地图api,得到图吧坐标
						String param_mapbar = end_Latitude + "," + end_Longitude;
						System.out.println("param_mapbar:" + param_mapbar);
						String retXml = MapUtil.getMapBar(param_mapbar);
						Document document_mapbar = null;
						try {
							document_mapbar = DocumentHelper.parseText(retXml);
						} catch (DocumentException e) {
							e.printStackTrace();
						}

						Element el_req_mapbar = document_mapbar.getRootElement();
						List lonlatList = el_req_mapbar.element("item").elements();
						String lonlat = "";
						if (lonlatList != null && lonlatList.size() > 0) {

							if ((Element) lonlatList.get(0) != null) {
								lonlat = ((Element) lonlatList.get(0)).getText();
							}
							System.out.println("mapbar=~~~~~~~~~~~~~~~~~~~~~~~~~~"
									+ lonlat);
						}
						// 调用图吧地图api，获取图吧逆地理名称
						String param = "";
						param += "detail=1&zoom=11&road=1&customer=2&latlon=";
						// param += end_Latitude + "," + end_Longitude;
						param += lonlat;
						
						
						String xml = MapUtil.readContentFromGet(param);
                           
						Document document = null;
						try {
							document = DocumentHelper.parseText(xml);
						} catch (DocumentException e) {
							e.printStackTrace();
						}
						Element el_req = document.getRootElement();
						String retMsg = "";
						if (el_req.element("poi") != null) {
							retMsg += el_req.element("poi").getText() + ",";
							retMsg = URLDecoder.decode(retMsg, "utf-8");
						}
						if(retMsg.length()>1)
						retMsg=retMsg.substring(0, retMsg.length()-1);
						responseBodyJson.writeStringField("endLocation", retMsg);
					}
				}else{
					responseBodyJson.writeStringField("startLocation", "暂无数据");
					responseBodyJson.writeStringField("endLocation", "暂无数据");
				}
				
				responseBodyJson.writeEndObject();
			}

		}
		responseBodyJson.writeEndArray();
	}

	@RequestMapping(value = "listbypage.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);

	}

}
