package com.tmount.business.carhot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.db.car.vo.TItovCarVo;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *手机号绑定的所有车辆信息
 * 
 * @author 
 * 
 */
@Controller
public class CarInfoByAccountId extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	
	
	@RequestMapping(value = "carInfoByAccountId.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		Long account_id = ParamData.getLong(requestParam.getBodyNode(),
				"account_id");//数据库修改最大时间
		//1.查询t_itov_car_brand的最大修改时间
		List<TItovCarVo> list  = carInfoService.getCarByAccountId(account_id);
		//if(list!=null && list.size()>0)
		//{
			ObjectMapper mapper = new ObjectMapper();
			responseBodyJson.writeFieldName("Data");
			mapper.writeValue(responseBodyJson, list);
		//}else
		//{
		//	responseBodyJson.writeStringField("result", "没有车辆信息");
		//}
		
		
	}
}
