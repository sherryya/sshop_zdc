package com.tmount.business.mileage.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.mileage.service.ZdcFaultOriginalService;
import com.tmount.db.mileage.dto.TZdcFaultCodeLog;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class SelectFaultCodeController extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(SelectFaultCodeController.class.getName());
	@Autowired
	private ZdcFaultOriginalService zdcFaultOriginalService;
	TZdcFaultCodeLog tZdcFaultCodeLog = new TZdcFaultCodeLog();
	@RequestMapping(value = "FaultCode.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		String deviceid = ParamData.getString(requestParam.getBodyNode(), "deviceid");
		tZdcFaultCodeLog.setDeviceid(deviceid);
		List<TZdcFaultCodeLog> tZdcFaultCodeLog_list = zdcFaultOriginalService.selectFaultCode(tZdcFaultCodeLog);
		responseBodyJson.writeFieldName("Object");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(responseBodyJson, tZdcFaultCodeLog_list);
	}
}
