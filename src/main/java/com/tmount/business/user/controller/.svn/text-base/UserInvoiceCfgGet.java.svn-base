package com.tmount.business.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsInvoiceCfg;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MyNaming;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class UserInvoiceCfgGet extends ControllerBase {

	@Autowired
	private UserService userService;
	
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		
		List<UsInvoiceCfg> usInvoiceCfgList = userService.getUsInvoiceCfgListByUserId(userId);
		
		responseBodyJson.writeFieldName("invoice_list");
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(new MyNaming());
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.writeValue(responseBodyJson, usInvoiceCfgList);
		
		

	}
	
	@RequestMapping(value = "user.invoice.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);

	}

}
