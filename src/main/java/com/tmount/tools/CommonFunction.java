package com.tmount.tools;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sun.misc.BASE64Decoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.util.MapUtil;

public class CommonFunction {
	/**
	 * 得到设备在线状态
	 * @param deviceUID
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
public static String getCurrentState(String deviceUID) throws JsonProcessingException, IOException
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put("deviceuid", deviceUID);
		String 	json= GetLaunchInfo.getDevicesOnlineInfo(params);
		json = URLDecoder.decode(json,"utf-8");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		String device_status="";
		if(null != jsonNode){
			String status = jsonNode.get("Status").textValue();
/*			String Message = jsonNode.get("Message").textValue();
			String ErrorCode = jsonNode.get("ErrorCode").textValue();
			String Data = jsonNode.get("Data").textValue();*/
			if("1".equals(status)){
				JsonNode device_status_json = jsonNode.get("Data");
				if(device_status_json!=null){
					String device_status_flag = device_status_json.toString();
					if("0".equals(device_status_flag)){
						device_status ="离线";
					}else if("1".equals(device_status_flag)){
						device_status ="在线";
					}else{
						device_status ="未知错误";
					}
				}else{
					device_status ="未知错误";
				}
			}else{
				device_status ="未知错误";
			}
		}else{
			device_status ="";
		}
		return device_status;
	}
/**
 * 得到当前精度和纬度
 * @param deviceUID
 * @return
 * @throws IOException
 */
public static String getLocatin(String deviceUID) throws IOException
{
		Map<String, String> params = new HashMap<String, String>();
		String ret="";
		params.put("deviceuid", deviceUID);
		String json = GetLaunchInfo.getCurrentGPSInfo(params);//得到最新的GPS信息
		json = URLDecoder.decode(json, "utf-8");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		if (null != jsonNode) {
			String status = jsonNode.get("Status").textValue();
			if ("1".equals(status)) {
				JsonNode device_status_json = jsonNode.get("Data");
				if(!device_status_json.equals(""))
				{
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
						ret=LongitudeStr+","+LatitudeStr;
					}
				}
			}
		}
		return ret;
}
}
