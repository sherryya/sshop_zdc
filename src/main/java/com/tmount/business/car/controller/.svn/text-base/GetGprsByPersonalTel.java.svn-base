package com.tmount.business.car.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.business.user.service.UserService;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.jpush.examples.JPushClientExample;
import com.tmount.util.ControllerBase;
import com.tmount.util.MapUtil;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetGprsByPersonalTel  extends ControllerBase {
	@Autowired
	private UserService userService;
	String json = "";
	String lonlat = "";
	String retMsg = "";
	@RequestMapping(value = "GprsByPersonalTel.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String personal_tel = ParamData.getString(requestParam.getBodyNode(), "personal_tel");
		CommonBean commonBean = userService.getCommonDeviceUidForGPS(personal_tel);
		if(commonBean!=null){
			String deviceuid = commonBean.getTerminal_deviceuid();
			// 根据应用ID号查询获得对应设备列表信息
			Map<String, String> params = new HashMap<String, String>();
			params.put("deviceuid", deviceuid);
			json = GetLaunchInfo.getCurrentGPSInfo(params);
			json = URLDecoder.decode(json, "utf-8");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(json);
			String Latitude ="";
			String Longitude ="";
			if (null != jsonNode) {
				String status = jsonNode.get("Status").asText();
				if ("1".equals(status)) {
					JsonNode device_status_json = jsonNode.get("Data");
				
					 Latitude = device_status_json.get("Latitude").toString();// 纬度
					 Longitude = device_status_json.get("Longitude").toString();// 经度
					String latlon= getMapBarLatLon(Longitude,Latitude);
					System.out.println("latlon=~~~~~~~~~~~~~~~~~~~~~~~~~~"+ latlon);
					if(!latlon.equalsIgnoreCase(""))
					{
						Longitude=latlon.split(",")[0].toString();
						Latitude=latlon.split(",")[1].toString();
					}
					System.out.println("Longitude=~~~~~~~~~~~~~~~~~~~~~~~~~~"+ Longitude);
					System.out.println("Latitude=~~~~~~~~~~~~~~~~~~~~~~~~~~"+ Latitude);
			        JsonFactory jfactory = new JsonFactory();
					StringWriter jsonWrite = new StringWriter();
					JsonGenerator json = jfactory.createJsonGenerator(jsonWrite);
					json.writeStartObject(); 
					json.writeFieldName("body");
					json.writeStartObject(); 
			    	json.writeStringField("Latitude",Longitude);//目标地纬度（已转换成对应的地图的经纬度）;
			    	json.writeStringField("Longitude",Latitude);//目标地纬度（已转换成对应的地图的经纬度）;
			        responseBodyJson.writeArrayFieldStart("Data");
			        responseBodyJson.writeEndArray();
			        json.writeStringField("Msgtype","1");//信息类型 0-广告 1-导航 2-消息 3-通知
			        json.writeStringField("Title","找车");//标题
			        json.writeStringField("Imageurl","");//图片url
			        json.writeStringField("Noticeurl","");//广告url
			        json.writeStringField("Content","");//内容
			        json.writeStringField("strType", "0");//导航类型 0-最快路线 1-最短路线 2-避开高速 3-步行
			        json.writeStringField("strNum", "");
					json.writeEndObject(); 
					json.writeEndObject(); 
					json.close();
					String bodyResponseJson = jsonWrite.toString();
				    System.out.println(bodyResponseJson);
					String retStr= JPushClientExample.SendMsg(personal_tel,"车辆位置",bodyResponseJson);
				    responseBodyJson.writeStringField("flag:", retStr);	// 0:发送成功   否则失败	
				}
			}
		}else{
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_DEFAULT_CAR,null);
		}
	}
	
	private String getMapBarLatLon(String start_Longitude,String start_Latitude) throws IOException
	{
		if (StringUtils.isNotEmpty(start_Latitude)&& StringUtils.isNotEmpty(start_Longitude)) {
			// //调用图吧地图api,得到图吧坐标
			String param_mapbar = start_Longitude + ","+ start_Latitude ;
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
			@SuppressWarnings("rawtypes")
			List lonlatList = el_req_mapbar.element("item").elements();
			String lonlat = "";
			if (lonlatList != null && lonlatList.size() > 0) {
				if ((Element) lonlatList.get(0) != null) {
					lonlat = ((Element) lonlatList.get(0)).getText();
				}
				System.out.println("mapbar=~~~~~~~~~~~~~~~~~~~~~~~~~~"+ lonlat);
			}
			return lonlat;
		}
		else
		{
			return "";
		}
	}
}

