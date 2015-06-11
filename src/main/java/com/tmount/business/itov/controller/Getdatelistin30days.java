package com.tmount.business.itov.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBasePlatform;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.tmount.util.ResponseData;

@Controller
public class Getdatelistin30days extends ControllerBasePlatform {
	String json = "";
	@Override
	protected String doService(RequestParam requestParam) throws ShopException,
			JsonGenerationException, IOException {
		// 根据应用ID号查询获得对应设备列表信息
		Map<String, String> params = new HashMap<String, String>();
		String deviceuid = new String (ParamData.getString(requestParam.getBodyNode(), "deviceuid"));
		String checkDate = new String (ParamData.getString(requestParam.getBodyNode(), "checkDate"));//YYYy-MM-DD
		params.put("deviceuid", deviceuid);
		String dateListjson= GetLaunchInfo.getdatelistin30days(params);
		dateListjson = URLDecoder.decode(dateListjson,"utf-8");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(dateListjson);
		if(null != jsonNode){
			String status = jsonNode.get("Status").asText();
			String Message = jsonNode.get("Message").asText();
			String ErrorCode = jsonNode.get("ErrorCode").asText();
			if("1".equals(status)){
				JsonNode device_status_json = jsonNode.get("Data");//GPSTimeInDefaultTimeZone
				Iterator device_status_json_iterator = device_status_json.iterator();
				while(device_status_json_iterator.hasNext()){
					JsonNode GPSTimeInDefaultTimeZoneNode = (JsonNode)device_status_json_iterator.next();
					String GPSTimeInDefaultTimeZone = GPSTimeInDefaultTimeZoneNode.get("GPSTimeInDefaultTimeZone").asText();
					String gpsTime = GPSTimeInDefaultTimeZone.substring(0, 10);
					if(gpsTime.equals(checkDate)){
						//调用查询历程接口
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
						json= GetLaunchInfo.getlistbypage(params);
						break;
					}else{
						json = "{}";
					}
				}
			}
		}
		json = URLDecoder.decode(json,"utf-8");
		return json;
		
		/*
		ObjectMapper objectmapper = new ObjectMapper();
		JsonNode jsonNode = objectmapper.readTree(json);
		responseBodyJson.writeObject(jsonNode);
		*/
	}
	
	@RequestMapping(value = "datelistin30days.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);

	}
	
	@Override
	protected ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		ResponseData rd = mapper.readValue(json, ResponseData.class);
		return rd;
	}
}
