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
import com.tmount.business.mileage.service.ZdcCanstreamService;

import com.tmount.business.mileage.service.ZdcFaultOriginalService;
import com.tmount.db.mileage.dto.TZdcFaultCodeLog;

import com.tmount.business.mileage.service.ZdcGpsinfoService;

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
public class NewCanStreamController extends ControllerBase {
	@Autowired
	private ZdcCanstreamService zdcCanService;

	@Autowired
	private ZdcFaultOriginalService zdcFaultOriginalService;

	@Autowired
	private ZdcGpsinfoService gpsService;

	@RequestMapping(value = "newCanStream.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {

		TZdcFaultCodeLog tZdcFaultCodeLog = new TZdcFaultCodeLog();
		String deviceuid = ParamData.getString(requestParam.getBodyNode(), "deviceuid");
		ZdcCanstream canStreamBean=new ZdcCanstream();
		 canStreamBean = zdcCanService.selectNewCan(deviceuid);
		 tZdcFaultCodeLog.setDeviceid(deviceuid);
		if (canStreamBean != null) {
			
		}
		else
		{
			canStreamBean=new ZdcCanstream();
		}
		List<TZdcFaultCodeLog> tZdcFaultCodeLog_list = zdcFaultOriginalService.selectFaultCode(tZdcFaultCodeLog);
		if (tZdcFaultCodeLog_list.size() > 0)
			canStreamBean.setFault_code(String.valueOf(tZdcFaultCodeLog_list.size()));
		// 查询最新gps信息
		ZdcGpsinfo gpsInfo = gpsService.selectNewGps(deviceuid);
		responseBodyJson.writeFieldName("Object");
		ObjectMapper objectMapper = new ObjectMapper();
		if (gpsInfo != null) {
			canStreamBean.setLatitude(gpsInfo.getLatitude());
			canStreamBean.setLongitude(gpsInfo.getLongitude());
		}
		objectMapper.writeValue(responseBodyJson, canStreamBean);

	}
}
