package com.tmount.business.car.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;


@Controller
public class GetTerminalInfoByVoipAccount extends ControllerBase {
	@Autowired
	private TerminalInfoService terminalInfoService;
	String json = "";
	String lonlat = "";
	String retMsg = "";
	@RequestMapping(value = "TerminalInfoByVoipAccount.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String voipAccount = ParamData.getString(requestParam.getBodyNode(), "voipAccount");
		TerminalInfo terminalInfo = terminalInfoService.getTerminalInfoByVoip(voipAccount);
		if (terminalInfo != null) {
			String terminal_iemi = terminalInfo.getTerminal_imei();
			responseBodyJson.writeStringField("terminal_iemi", terminal_iemi);
		} else {
			responseBodyJson.writeStringField("terminal_iemi", ",");
		}
	}
}
