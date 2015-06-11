package com.tmount.business.itov.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBasePlatform;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.tmount.util.ResponseData;

@Controller
public class Getmulitcurrentgpsinfo extends ControllerBasePlatform {
	String json = "";
	@Override
	protected String doService(RequestParam requestParam) throws ShopException,
			JsonGenerationException, IOException {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("devicegroupuid", new String (ParamData.getString(requestParam.getBodyNode(), "devicegroupuid")));
		params.put("devicestatus", new String (ParamData.getString(requestParam.getBodyNode(), "devicestatus")));
		params.put("pagesize", new String (ParamData.getString(requestParam.getBodyNode(), "pagesize")));
		params.put("targetpage", new String (ParamData.getString(requestParam.getBodyNode(), "targetpage")));

		json= GetLaunchInfo.getmulitcurrentgpsinfo(params);
		json = URLDecoder.decode(json,"utf-8");
		return json;
	}
	
	@RequestMapping(value = "mulitcurrentgpsinfo.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);

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
