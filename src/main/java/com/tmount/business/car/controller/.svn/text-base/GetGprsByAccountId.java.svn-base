package com.tmount.business.car.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
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

import sun.misc.BASE64Decoder;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.CarDelete;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MapUtil;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetGprsByAccountId  extends ControllerBase {
	@Autowired
	private UserService userService;
	String json = "";
	String lonlat = "";
	String retMsg = "";
	@RequestMapping(value = "GprsByAccountId.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		Long account_id = ParamData.getLong(requestParam.getBodyNode(), "account_id");
		CommonBean commonBean = userService.getCommonDeviceUid(account_id);
		if(commonBean!=null){
			String deviceuid = commonBean.getTerminal_deviceuid();
			responseBodyJson.writeStringField("cardNum", commonBean.getCar_plate_number());//车牌号
			responseBodyJson.writeStringField("cardname",commonBean.getCar_name());//车辆名称
			// 根据应用ID号查询获得对应设备列表信息
			Map<String, String> params = new HashMap<String, String>();
			params.put("deviceuid", deviceuid);
			json = GetLaunchInfo.getCurrentGPSInfo(params);
			json = URLDecoder.decode(json, "utf-8");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(json);
			System.out.println("~~~~$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$~~~~json~~~~~~"+json);
			String Latitude ="";
			String Longitude ="";
			if (null != jsonNode) {
				String status = jsonNode.get("Status").asText();
				if ("1".equals(status)) {
					JsonNode device_status_json = jsonNode.get("Data");
					 Latitude = device_status_json.get("Latitude").toString();// 纬度
					 Longitude = device_status_json.get("Longitude")
							.toString();// 经度
					String Direction = device_status_json.get("Direction")
							.toString();
					String Speed = device_status_json.get("Speed")
							.toString();
					String GPSTimeInDefaultTimeZone = device_status_json.get("GPSTimeInDefaultTimeZone")
							.toString();

					responseBodyJson.writeStringField("Direction", Direction);// 方向
					responseBodyJson.writeStringField("Speed", Speed);// 瞬间速度
					responseBodyJson.writeStringField("GPSTimeInDefaultTimeZone", GPSTimeInDefaultTimeZone);// 当前时间
				   //图吧坐标转化
					String xml = MapUtil.getMapBar(Longitude+","+Latitude);
					System.out.println("retXml" + xml);
					Document document_mapbar = null;
					try {
						document_mapbar = DocumentHelper.parseText(xml);
					} catch (DocumentException e) {
						e.printStackTrace();
					}

					Element el_req_mapbar = document_mapbar.getRootElement();
					List lonlatList = el_req_mapbar.element("item").elements();
					
					if (lonlatList != null && lonlatList.size() > 0) {

						if ((Element) lonlatList.get(0) != null) {
							lonlat = ((Element) lonlatList.get(0)).getText();
						}
						System.out.println("mapbar=~~~~~~~~~~~~~~~~~~~~~~~~~~"
								+ lonlat);
					}
					System.out.println("~~~~~~~~~~~lonlat~~~~~~~~~~~~~"+lonlat);
					

					responseBodyJson.writeStringField("lonlatMsg", "");
					responseBodyJson.writeStringField("lonlat",lonlat);
					
				}
			}
			
			
		}else{
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_DEFAULT_CAR,null);
		}
		
	}
	
}

