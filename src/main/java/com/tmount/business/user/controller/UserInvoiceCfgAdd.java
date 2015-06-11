package com.tmount.business.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.pub.service.PubService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsInvoiceCfg;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class UserInvoiceCfgAdd extends ControllerBase {
	@Autowired
	private UserService userService;
	@Autowired
	private PubService pubService;

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		String invoiceTitle = ParamData.getString(requestParam.getBodyNode(), "invoice_title");
		String invoiceContent = ParamData.getString(requestParam.getBodyNode(), "invoice_content");
		String identification = ParamData.getString(requestParam.getBodyNode(), "identification");
		String mark = ParamData.getString(requestParam.getBodyNode(), "mark");
		
		UsInvoiceCfg usInvoiceCfg = new UsInvoiceCfg(); 
		
		
		usInvoiceCfg.setUserId(userId);
		usInvoiceCfg.setInvoiceTitle(invoiceTitle);
		usInvoiceCfg.setInvoiceContent(invoiceContent);
		usInvoiceCfg.setIdentification(identification);
		usInvoiceCfg.setMark(mark);
		usInvoiceCfg.setOpTime(pubService.getDbTime().getDbTime()) ;
		
		
		userService.insertUsInvoiceCfg(usInvoiceCfg);
		

	}
	@RequestMapping(value = "user.invoice.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
			
	}

}
