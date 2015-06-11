package com.tmount.business.car.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.db.car.dto.CarDelete;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class UpdateCarNameController extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;

	@RequestMapping(value = "carname.upd")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String car_id = new String(String.valueOf(ParamData.getInt(requestParam.getBodyNode(), "car_id")));
		String car_name = ParamData.getString(requestParam.getBodyNode(), "car_name");
		CarInfo carInfo = new CarInfo();
		carInfo.setCar_id(Integer.valueOf(car_id));
		carInfo.setCar_name(car_name);
	    carInfoService.updatecarname(carInfo);
		responseBodyJson.writeStringField("result", "OK");
	}

}
