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
public class GetTroubleCodeInfo extends ControllerBasePlatform {
	String json = "";
	@Override
	protected String doService(RequestParam requestParam) throws ShopException,
			JsonGenerationException, IOException {
		// 根据应用ID号查询获得对应设备列表信息
		Map<String, String> params = new HashMap<String, String>();
		String deviceuid = new String(ParamData.getString(
				requestParam.getBodyNode(), "deviceuid"));
		String date = new String(ParamData.getString(
				requestParam.getBodyNode(), "date"));
		String starttime = new String(ParamData.getString(
				requestParam.getBodyNode(), "starttime"));
		String endtime = new String(ParamData.getString(
				requestParam.getBodyNode(), "endtime"));
		params.put("deviceuid", deviceuid);
		params.put("date", date);
		params.put("starttime", starttime);
		params.put("endtime", endtime);
		json = GetLaunchInfo.getTroubleCodeInfo(params);
		json = URLDecoder.decode(json, "utf-8");
		return json;

		/*
		 * ObjectMapper objectmapper = new ObjectMapper(); JsonNode jsonNode =
		 * objectmapper.readTree(json); responseBodyJson.writeObject(jsonNode);
		 */
	}

	@RequestMapping(value = "troublecodeinfo.get")
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
