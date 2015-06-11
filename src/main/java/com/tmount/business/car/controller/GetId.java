package com.tmount.business.car.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;


@Controller
public class GetId extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	@RequestMapping(value = "getIdTest.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String car_id = ParamData.getString(requestParam.getBodyNode(), "car_id");
		
		String name = ParamData.getString(requestParam.getBodyNode(), "name");
		//int id = ParamData.getInt(requestParam.getBodyNode(), "id");
		//int age = ParamData.getInt(requestParam.getBodyNode(), "age");
		
		//int valueupd = carInfoService.updtestupd(testupd);
		
		int v = carInfoService.queryId(name);
		TestUpd testupd = new TestUpd();
		testupd.setValue(v+1);
		testupd.setName(name);
		
		int value = carInfoService.updtestupd(testupd);
		//int value  = carInfoService.getCarId(car_id);
	    responseBodyJson.writeNumberField("value",value);
	   //.writeNumberField("valueupd",valueupd);
	}
}
