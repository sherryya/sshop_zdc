package com.tmount.business.commanager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.user.controller.UserLogin;
import com.tmount.db.commanager.dao.ComManager;
import com.tmount.db.commanager.dto.DriversListInfoByCarID;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetDriverListInfoByCarID extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(UserLogin.class.getName());
	@Autowired
	private ComManager comManager;

	@RequestMapping(value = "driversListInfoByCarID.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		String car_id = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_id"));// 车辆ID
		DriversListInfoByCarID driversListInfoByCarID = new DriversListInfoByCarID();
		driversListInfoByCarID.setDs_car_id(car_id);
		List<DriversListInfoByCarID> list_DriverList = new ArrayList<DriversListInfoByCarID>();
		list_DriverList = comManager
				.getDriverListInfoByCarId(driversListInfoByCarID);
		responseBodyJson.writeArrayFieldStart("activity_list");
		String str_ds_drivers_id = "";
		String str_ds_type = "";
		String str_ds_time = "";
		String str_ds_time1 = "";
		String str_name = "";
		String str_tel = "";
		String str_sex = "";
		if (list_DriverList.size() != 0) {
			Iterator<DriversListInfoByCarID> it = list_DriverList.iterator();
			while (it.hasNext()) {
				DriversListInfoByCarID driversListInfoByCarID1 = new DriversListInfoByCarID();
				driversListInfoByCarID1 = it.next();
				if (str_ds_drivers_id.equals(driversListInfoByCarID1.getDs_drivers_id())) 
				{
					str_ds_time1 = driversListInfoByCarID1.getDs_time();//得到某个驾驶员最后签到时间
					str_ds_drivers_id = driversListInfoByCarID1.getDs_drivers_id();
					//str_ds_type = driversListInfoByCarID1.getDs_type();
					str_name = driversListInfoByCarID1.getName();
					str_tel = driversListInfoByCarID1.getTel();
					str_sex = driversListInfoByCarID1.getSex();

				} else {
					if (!str_ds_time.equals("")) 
					{
						responseBodyJson.writeStartObject();
						responseBodyJson.writeStringField("ds_drivers_id",	str_ds_drivers_id);
						//responseBodyJson.writeStringField("ds_type",str_ds_type);
						if (str_ds_time1.equals("")) 
						{
							responseBodyJson.writeStringField("ds_time",	str_ds_time);
						} else 
						{
							responseBodyJson.writeStringField("ds_time",str_ds_time + "-->" + str_ds_time1);
						}
						responseBodyJson.writeStringField("name", str_name);
						responseBodyJson.writeStringField("tel", str_tel);
						responseBodyJson.writeStringField("sex", str_sex);
						responseBodyJson.writeEndObject();
					}
					/**
					 * 记录下一条数据
					 */
					str_ds_drivers_id = driversListInfoByCarID1.getDs_drivers_id();
					//str_ds_type = driversListInfoByCarID1.getDs_type();
					str_ds_time = driversListInfoByCarID1.getDs_time();
					str_name = driversListInfoByCarID1.getName();
					str_tel = driversListInfoByCarID1.getTel();
					str_sex = driversListInfoByCarID1.getSex();
				}
			}
			/**
			 * 保存最后一条记录
			 */
			responseBodyJson.writeStartObject();
			responseBodyJson.writeStringField("ds_drivers_id",	str_ds_drivers_id);
			//responseBodyJson.writeStringField("ds_type",str_ds_type);
			if (str_ds_time1.equals(str_ds_time)) 
			{
				responseBodyJson.writeStringField("ds_time",	str_ds_time);
			} else 
			{
				responseBodyJson.writeStringField("ds_time",str_ds_time + "-->" + str_ds_time1);
			}
			responseBodyJson.writeStringField("name", str_name);
			responseBodyJson.writeStringField("tel", str_tel);
			responseBodyJson.writeStringField("sex", str_sex);
			responseBodyJson.writeEndObject();
		}
		responseBodyJson.writeEndArray();
	}
}
