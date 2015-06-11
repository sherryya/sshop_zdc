package com.tmount.business.car.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.db.car.dto.QRcodeTerminalInfo;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;


@Controller
public class QRcodeTerminalInfoList extends ControllerBase {
	@Autowired
	private TerminalInfoService terminalInfoService;
	@RequestMapping(value = "qrcodeTerminalInfo.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String car_id = ParamData.getString(requestParam.getBodyNode(), "car_id");
		
		String terminal_imei = ParamData.getString(requestParam.getBodyNode(), "terminal_imei");
		int startLimit = ParamData.getInt(requestParam.getBodyNode(), "startLimit");
		int pageSize = ParamData.getInt(requestParam.getBodyNode(), "pageSize");
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
		terminalInfo.setPageSize(pageSize);
		terminalInfo.setStartLimit(startLimit);
		List<QRcodeTerminalInfo> list = terminalInfoService.selectTerminalInfo(terminalInfo);
		responseBodyJson.writeArrayFieldStart("Data");
		for(QRcodeTerminalInfo commonBean :list){
			responseBodyJson.writeStartObject();
			responseBodyJson.writeStringField("terminal_imei", commonBean.getTerminal_imei());
			
			responseBodyJson.writeStringField("terminal_status",commonBean.getTerminal_status());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
			if(null==commonBean.getIsprint())
			{
				responseBodyJson.writeStringField("isprint", "否");
			}else
			{
				responseBodyJson.writeStringField("isprint", commonBean.getIsprint());
			}
			if(null!=commonBean.getPrint_date())
			{
			  responseBodyJson.writeStringField("print_date",sdf.format(commonBean.getPrint_date()));
			}else
			{
				 responseBodyJson.writeStringField("print_date","无");
			}
			//responseBodyJson.writeStringField("", value);
			//responseBodyJson.writeNumberField("id", commonBean.getId());
			responseBodyJson.writeEndObject();
		}
		responseBodyJson.writeEndArray();
	}
}
