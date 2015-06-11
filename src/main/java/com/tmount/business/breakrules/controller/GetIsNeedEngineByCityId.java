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
 * 根据城市code查询是否需要发动机号或者车架号
 * 20141230
 * @author Administrator
 *
 */
@Controller
public class GetIsNeedEngineByCityId extends ControllerBase {
	Logger logger = Logger.getLogger(GetIsNeedEngineByCityId.class);
	String json = "";
	@Autowired
	private TzdcBreakRuleCityService tbreakcityService;
	@RequestMapping(value = "getIsNeedByCityCode.get")
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
		String city_name = ParamData.getString(requestParam.getBodyNode(), "city_name");
		//根据城市名称查询是否需要发动机参数或者车架号蚕食
		List<TZdcBreakrulescitylist> cityList = tbreakcityService.selectByCityName(city_name);
		
        if(cityList!=null && cityList.size()>0)	 
        {
        	TZdcBreakrulescitylist city = cityList.get(0);
        	responseBodyJson.writeNumberField("engine", city.getEngine());
        	responseBodyJson.writeNumberField("engineno", city.getEngineno());
        	responseBodyJson.writeNumberField("classt", city.getClasst());
        	responseBodyJson.writeNumberField("classno", city.getClassno());
        }
	}

}
