package com.tmount.business.zdjs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.zdjs.service.GetUIDService;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.db.zdjs.dto.GetUID;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.RequestParam;

/**
 * 得到用户的UID信息　用于接口
 * 
 * @author 
 * 
 */
@Controller
public class GetUIDController extends ControllerBase {
	@Autowired
	private GetUIDService getUIDService;
	
	
	@RequestMapping(value = "user.uid.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	

			List <GetUID> list = getUIDService.selectAll();
			responseBodyJson.writeArrayFieldStart("Data");
			for(GetUID commonBean :list){
				responseBodyJson.writeStartObject();
				responseBodyJson.writeStringField("terminal_deviceuid", commonBean.getTerminal_deviceuid());
				responseBodyJson.writeStringField("account_name", commonBean.getAccount_name());
				responseBodyJson.writeNumberField("account_id",commonBean.getAccount_id());
				responseBodyJson.writeStringField("carSeriesName", commonBean.getCarSeriesName());
				responseBodyJson.writeEndObject();
			}
			responseBodyJson.writeEndArray();
		
		
	}
}
