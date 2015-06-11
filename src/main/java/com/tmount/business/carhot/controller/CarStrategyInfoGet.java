package com.tmount.business.carhot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.db.car.dto.TZdcCarhotStrategy;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *车策略
 * 
 * @author 
 * 
 */
@Controller
public class CarStrategyInfoGet extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	@RequestMapping(value = "carstarte.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		String device_id = ParamData.getString(requestParam.getBodyNode(),"device_id");//设备号
		TZdcCarhotStrategy startInfo = carInfoService.selectStrategyInfo(device_id);
		responseBodyJson.writeArrayFieldStart("strateData");
		if(null!=startInfo)
		{
			responseBodyJson.writeStartObject();
			responseBodyJson.writeStringField("device_id", startInfo.getDeviceId());
			responseBodyJson.writeStringField("straType", startInfo.getStrategyType());
			responseBodyJson.writeStringField("straValue", startInfo.getStrategyValue());
			responseBodyJson.writeEndObject();
			
		}
		responseBodyJson.writeEndArray();
		
		
	}
}
