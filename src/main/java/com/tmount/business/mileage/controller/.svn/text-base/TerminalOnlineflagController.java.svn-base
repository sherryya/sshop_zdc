package com.tmount.business.mileage.controller;

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
import com.tmount.business.mileage.service.TZdcTerminalOnlineflagService;
import com.tmount.business.mileage.service.ZdcCanstreamService;

import com.tmount.business.mileage.service.ZdcFaultOriginalService;
import com.tmount.db.mileage.dto.TZdcFaultCodeLog;

import com.tmount.business.mileage.service.ZdcGpsinfoService;

import com.tmount.db.mileage.dto.TZdcTerminalOnlineflag;
import com.tmount.db.mileage.dto.ZdcCanstream;
import com.tmount.db.mileage.dto.ZdcGpsinfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 得到最新CAN数据流 也就是设备的最后那条
 * 
 * @author
 * 
 */
@Controller
public class TerminalOnlineflagController extends ControllerBase {
	@Autowired
	private TZdcTerminalOnlineflagService terminalOnlineService;

	@RequestMapping(value = "terminalOnlineFlag.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {

		long account_id_tel = ParamData.getLong(requestParam.getBodyNode(), "account_id_tel");
		TZdcTerminalOnlineflag terminalOnlineFlag = terminalOnlineService.selectInfoByaccountIdTel(account_id_tel);
		if(null!=terminalOnlineFlag)
		{
		responseBodyJson.writeFieldName("Object");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(responseBodyJson, terminalOnlineFlag);
		}else
		{
			responseBodyJson.writeNumberField("result", 1);   //表示没查到对应信息
		}

	}
}
