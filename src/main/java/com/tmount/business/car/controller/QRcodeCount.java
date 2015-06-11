package com.tmount.business.car.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.db.car.dto.QRcodeTerminalInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;


@Controller
public class QRcodeCount extends ControllerBase {
	@Autowired
	private TerminalInfoService terminalInfoService;
	@RequestMapping(value = "qrcodeCount.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		
		String terminal_imei = ParamData.getString(requestParam.getBodyNode(), "terminal_imei");
		String isprint = ParamData.getString(requestParam.getBodyNode(), "isprint");
		QRcodeTerminalInfo terminalInfo = new QRcodeTerminalInfo();
		if(StringUtils.isNotBlank(terminal_imei))
		{
		   terminalInfo.setTerminal_imei(terminal_imei);
		}
		if(StringUtils.isNotBlank(isprint))
		{
		   terminalInfo.setIsprint(isprint);
		}
		int count = terminalInfoService.selectCountTerminalInfo(terminalInfo);
		responseBodyJson.writeNumberField("count",count);
	}
}
