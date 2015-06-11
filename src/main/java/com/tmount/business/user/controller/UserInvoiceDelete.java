package com.tmount.business.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsInvoiceCfgKey;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class UserInvoiceDelete extends ControllerBase {
	@Autowired
	private UserService userService;
	
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		UsInvoiceCfgKey usInvoiceCfgKey  = new UsInvoiceCfgKey();
		usInvoiceCfgKey.setUserId(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		usInvoiceCfgKey.setOrders(ParamData.getInt(requestParam.getBodyNode(), "orders"));
		
		userService.deleteUserInvoice(usInvoiceCfgKey);
		
	}

	@RequestMapping(value = "user.invoice.delete")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);

	}

}