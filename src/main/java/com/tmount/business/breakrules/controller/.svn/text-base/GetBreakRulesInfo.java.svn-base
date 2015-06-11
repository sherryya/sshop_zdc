package com.tmount.business.breakrules.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.breakrules.platform.GetPlatformInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBasePlatform;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.tmount.util.ResponseData;

@Controller
public class GetBreakRulesInfo extends ControllerBasePlatform {
	Logger logger = Logger.getLogger(GetBreakRulesCityList.class);
	
	String json = "";
	
	@RequestMapping(value = "breakrules.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
	}

	@Override
	protected String doService(RequestParam requestParam) throws ShopException,
	JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		String city = ParamData.getString(requestParam.getBodyNode(), "city");//城市代码
		String hphm = ParamData.getString(requestParam.getBodyNode(), "hphm");//号牌号码 完整7位
		String hpzl = ParamData.getString(requestParam.getBodyNode(), "hpzl");//号牌类型，默认02
		String engineno = ParamData.getString(requestParam.getBodyNode(), "engineno");//发动机号 (根据城市接口中的参数填写)
		String classno = ParamData.getString(requestParam.getBodyNode(), "classno");//车架号 (根据城市接口中的参数填写)
		
		//调用聚合数据的接口
		json = GetPlatformInfo.getBreakInfo(city,hphm,hpzl,engineno,classno);
		//json = URLDecoder.decode(json, "utf-8");
		return json;
	}
	
	@Override
	protected ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		ResponseData rd = mapper.readValue(json, ResponseData.class);
		return rd;
	}
	
}
