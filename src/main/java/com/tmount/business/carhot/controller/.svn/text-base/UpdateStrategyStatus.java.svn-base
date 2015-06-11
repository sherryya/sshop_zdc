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
 *车策略的状态更新
 * 
 * @author 
 * 
 */
@Controller
public class UpdateStrategyStatus extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	@RequestMapping(value = "updatestartegystatus.upd")
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
		int is_use = ParamData.getInt(requestParam.getBodyNode(),"is_use");//状态
		TZdcCarhotStrategy startInfo  = new TZdcCarhotStrategy();
		startInfo.setDeviceId(device_id);
		startInfo.setIs_use(is_use);
		int t = carInfoService.updateCarHotStatus(startInfo);
		if(t>0)
		{
			responseBodyJson.writeNumberField("result", 1);  //1表示更新成功0表示失败
		}else{
			responseBodyJson.writeNumberField("result", 0);  //表示更新失败
		}
		
		
	}
}
