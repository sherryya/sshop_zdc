package com.tmount.business.driver.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Decoder;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.driver.service.DriverService;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MapUtil;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class DriverGetCurrentGPSInfo extends ControllerBase {
	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "dirvergetcurrentgps.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson)throws ShopException, JsonGenerationException, IOException {
		String car_id = new String(ParamData.getString(requestParam.getBodyNode(), "car_id"));// 驾驶车ID
		CommonBean cb = driverService.getDeviceUID(Integer.valueOf(car_id));// 通过car_id得到终端UID
		if (cb != null) {
			String deviceUID = cb.getTerminal_deviceuid();// 得到UID
			Map<String, String> params = new HashMap<String, String>();
			params.put("deviceuid", deviceUID);
			String json = GetLaunchInfo.getCurrentGPSInfo(params);//得到最新的GPS信息
			json = URLDecoder.decode(json, "utf-8");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(json);
			if (null != jsonNode) {
				String status = jsonNode.get("Status").textValue();
				if ("1".equals(status)) {
					JsonNode device_status_json = jsonNode.get("Data");
					responseBodyJson.writeStringField("car_id", car_id);//车辆ID
					String Latitude = device_status_json.get("Latitude").toString();//纬度
					String Longitude = device_status_json.get("Longitude").toString();//经度
					//调用百度地图api
					JsonNode jsonNodeMap = MapUtil.getMap(Longitude,Latitude);
					Iterator it = jsonNodeMap.iterator();
					while(it.hasNext()){
						JsonNode js = (JsonNode)it.next();
						String LatitudeBase64 = js.get("x").textValue();
						String LongitudeBase64 = js.get("y").textValue();
						//解析base64串
						String LongitudeStr  = new String (new BASE64Decoder().decodeBuffer(LatitudeBase64));
						String   LatitudeStr= new String (new BASE64Decoder().decodeBuffer(LongitudeBase64));
						responseBodyJson.writeStringField("Latitude", LatitudeStr);//纬度
						responseBodyJson.writeStringField("Longitude", LongitudeStr);//经度
					}
				}
			}
		}
		else
		{
			throw new ShopBusiException(
					ShopBusiErrorBundle.IS_EXIST_CAR_TER,null);
		}
	}
}
