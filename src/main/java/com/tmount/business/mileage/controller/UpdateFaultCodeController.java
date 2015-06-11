package com.tmount.business.mileage.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.mileage.service.ZdcFaultOriginalService;
import com.tmount.db.mileage.dto.TZdcFaultCodeLog;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class UpdateFaultCodeController extends ControllerBase {
	@Autowired
	private ZdcFaultOriginalService zdcFaultOriginalService;
	@RequestMapping(value = "FaultCode.upd")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {

		String deviceid = ParamData.getString(requestParam.getBodyNode(), "deviceid");
		String types = ParamData.getString(requestParam.getBodyNode(), "types");
		TZdcFaultCodeLog tZdcFaultCodeLog=new  TZdcFaultCodeLog();
		try {
			String [] arr_types=types.split(",");
			for(String type:arr_types)
			{
				tZdcFaultCodeLog.setDeviceid(deviceid);
				tZdcFaultCodeLog.setFaultType(type);
				zdcFaultOriginalService.updateByPrimaryKey(tZdcFaultCodeLog);
			}
			responseBodyJson.writeStringField("ret", "1");
		} catch (Exception e) {
			// TODO: handle exception
			responseBodyJson.writeStringField("ret", "0");
		}
	}
}
