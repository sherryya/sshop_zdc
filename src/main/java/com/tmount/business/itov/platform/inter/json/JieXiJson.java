package com.tmount.business.itov.platform.inter.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.tmount.business.itov.platform.inter.beans.DevicesList;


public class JieXiJson {

	/**
	 * 得到设备信息列表
	 * @param dl_json
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static ArrayList<DevicesList> getDevicelist(String dl_json)
	{
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON("["+dl_json.toString().replace("null", "\"\"")+"]");
		List list = (List) JSONSerializer.toJava(jsonArray);
		ArrayList<DevicesList> arr_dl=new ArrayList<DevicesList>();
		DevicesList bean=null;
		for (Object obj : list) {
			JSONObject jsonObject = JSONObject.fromObject(obj);
			JSONArray jsonArray1 = (JSONArray) JSONSerializer.toJSON(jsonObject.get("Data"));
			List list1 = (List) JSONSerializer.toJava(jsonArray1);
			for (Object obj1 : list1) {
			JSONObject jsonObject1 = JSONObject.fromObject(obj1);
			 bean = new DevicesList();
			 bean.setDeviceUID(jsonObject1.getString("DeviceUID"));
			 bean.setDeviceType(jsonObject1.getString("DeviceType"));
			 bean.setDeviceSNAndType(jsonObject1.getString("DeviceSNAndType"));
			 bean.setDeviceSN(jsonObject1.getString("DeviceSN"));
			 bean.setDeviceTimeZone(jsonObject1.getString("DeviceTimeZone"));
			 bean.setCarSeriesCode(jsonObject1.getString("CarSeriesCode"));
			arr_dl.add(bean);
			}
		}
		System.out.println(arr_dl);
		return arr_dl;
	}
	
}
