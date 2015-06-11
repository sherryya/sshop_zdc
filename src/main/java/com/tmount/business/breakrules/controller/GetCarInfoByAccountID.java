package com.tmount.business.breakrules.controller;
import com.tmount.util.ParamData;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.breakrules.platform.BreakCityListPlat;
import com.tmount.business.breakrules.platform.GetPlatformInfo;
import com.tmount.business.breakrules.service.TzdcBreakRuleCityService;
import com.tmount.business.breakrules.service.TzdcBreakRulesService;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.db.breakrules.dto.TZdcBreakrules;
import com.tmount.db.breakrules.dto.TZdcBreakrulescitylist;
import com.tmount.db.car.vo.TItovCarVo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 如果车机绑定则可根据accountId获得车架号,车牌号,发动机号
 * 20141222
 * @author Administrator
 *
 */
@Controller
public class GetCarInfoByAccountID extends ControllerBase {
	Logger logger = Logger.getLogger(GetCarInfoByAccountID.class);
	String json = "";
    @Autowired
    private CarInfoService carService;
	@RequestMapping(value = "breakrulesCarInfo.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		long account_id = ParamData.getLong(requestParam.getBodyNode(), "account_id");
		//查询全部车辆信息
		List<TItovCarVo> carList = carService.qryCarInfoByAccountID(account_id);
        if(carList!=null && carList.size()>0)	 
        {
        	TItovCarVo carvo = carList.get(0);
        	responseBodyJson.writeStringField("car_carcase_num", carvo.getCar_carcase_num()==null?"":carvo.getCar_carcase_num());
        	responseBodyJson.writeStringField("car_engine_num", carvo.getCar_engine_num()==null?"": carvo.getCar_engine_num());
        	responseBodyJson.writeStringField("car_plate_num", carvo.getCarPlateNumber()==null?"":carvo.getCarPlateNumber());
        	responseBodyJson.writeStringField("car_style", carvo.getCarStyle()==null?"":carvo.getCarStyle());
        	responseBodyJson.writeStringField("car_Color", carvo.getCarColor()==null?"":carvo.getCarColor());
        	responseBodyJson.writeStringField("car_driving_license_date", carvo.getCar_driving_license_date()==null?"":carvo.getCar_driving_license_date());
        }
	}

}
