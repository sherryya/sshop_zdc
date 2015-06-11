package com.tmount.business.car.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.db.util.dto.CommonBean;
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
public class CarBrandMessage extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	
	
	@RequestMapping(value = "carbrands.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		Long id = ParamData.getLong(requestParam.getBodyNode(),
				"id");//数据库修改最大时间
		//1.查询t_itov_car_brand的最大修改时间
		Long maxId = carInfoService.getMaxId();
		if(maxId>id){
			//2.查询所有大于id的记录
			List <CommonBean> list = carInfoService.getCarBrands(id);
			responseBodyJson.writeArrayFieldStart("Data");
			for(CommonBean commonBean :list){
				responseBodyJson.writeStartObject();
				responseBodyJson.writeStringField("car_brands", commonBean.getCar_brands());
				responseBodyJson.writeStringField("car_model", commonBean.getCar_model());
				responseBodyJson.writeStringField("car_style",commonBean.getCar_style());
				responseBodyJson.writeStringField("is_valid", commonBean.getIs_valid());
				responseBodyJson.writeNumberField("id", commonBean.getId());
				responseBodyJson.writeEndObject();
			}
			responseBodyJson.writeEndArray();
		}
		
	}
}
