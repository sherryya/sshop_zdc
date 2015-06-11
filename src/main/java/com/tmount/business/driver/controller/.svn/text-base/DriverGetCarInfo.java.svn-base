package com.tmount.business.driver.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.driver.service.DriverService;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.user.dto.UsDriversInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class DriverGetCarInfo extends ControllerBase {
	@Autowired
	private DriverService driverService;
	@RequestMapping(value = "dirvergetcar.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String driver_id = new String(ParamData.getString(requestParam.getBodyNode(), "driver_id"));// 驾驶员ID
		UsDriversInfo usDriversInfo = new UsDriversInfo();
		usDriversInfo.setId(Integer.valueOf(driver_id));
		UsDriversInfo di = driverService.getCarInfoByDriverID(usDriversInfo);
		if (di != null) {
			String cars = di.getCar();// 得到驾驶车辆信息
			System.out.println(cars);
			String[] car = cars.split(",");
			if (car.length > 0) {
				List<CarInfo> s = new ArrayList<CarInfo>();
				CarInfo carInfo1;
				for (String c : car) {
					carInfo1 = new CarInfo();
					carInfo1 = driverService.getCarInfoByCarID(c);
					s.add(carInfo1);
				}
				responseBodyJson.writeArrayFieldStart("activity_list");
				if (s.size() > 0) {
					CarInfo carInfo;
					Iterator<CarInfo> it = s.iterator();
					while (it.hasNext()) {
						carInfo = it.next();
						responseBodyJson.writeStartObject();
						responseBodyJson.writeNumberField("id", carInfo.getId());
						responseBodyJson.writeStringField("carnum",carInfo.getCarnum());
						responseBodyJson.writeStringField("carname",carInfo.getCarname());
						responseBodyJson.writeEndObject();
					}
				}
				responseBodyJson.writeEndArray();
			}
			else
			{
				throw new ShopBusiException(
						ShopBusiErrorBundle.NOT_EXITS,new Object[] { "驾驶车辆" });
			}
		}
		else
		{
			throw new ShopBusiException(
					ShopBusiErrorBundle.NOT_EXITS,new Object[] { "驾驶车辆" });
		}
			

	}
}
