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
import com.tmount.db.car.dto.CarDelete;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *车品牌数据同步接口
 * 
 * @author 
 * 
 */
@Controller
public class CarHotDelete extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	@RequestMapping(value = "carHotDelete.del")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		int car_id = ParamData.getInt(requestParam.getBodyNode(),"car_id");//
		
		try{
		carInfoService.deleteCarByCarID(car_id);  //删除车辆绑定信息
		CarDelete car = new CarDelete();
		car.setCar_id(car_id);
		carInfoService.deleteCarInfoByCarID(car);  //删除车辆信息
		responseBodyJson.writeNumberField("result", 1);  //表示删除成功
		}catch(Exception e){
			responseBodyJson.writeNumberField("result", 0);  //表示删除失败
		}
		
	}
}
