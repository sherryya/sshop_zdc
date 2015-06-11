package com.tmount.business.sys.controller;

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
import com.tmount.business.sys.service.SysService;
import com.tmount.db.pub.dto.Dict;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MyNaming;
import com.tmount.util.RequestParam;
@Controller
public class ReArrivalTimeDicGet extends ControllerBase {
	@Autowired
	private SysService sysService;

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		
		
		List<Dict> reArrivalTimeDic =  sysService.selectReArrivalTimeDic();
		
		
		responseBodyJson.writeFieldName("arrival_time_list");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.setPropertyNamingStrategy(new MyNaming() );
		objectMapper.writeValue(responseBodyJson, reArrivalTimeDic);

	}
	@RequestMapping(value = "sys.arrivaltime.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
	}

}
