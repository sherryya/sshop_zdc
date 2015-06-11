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
import com.tmount.business.mileage.service.ZdcGpsinfoService;
import com.tmount.business.mileage.service.ZdcMileageService;
import com.tmount.db.mileage.dto.ZdcGpsinfo;
import com.tmount.db.mileage.dto.ZdcMileage;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 查询某个GPS的轨迹信息
 * 
 * @author  zsh
 * 
 */
@Controller
public class GpsTrajectoryController extends ControllerBase {

    @Autowired
    private ZdcGpsinfoService zdcGpsinfoService;

    @RequestMapping(value = "gpsTrajectory.get")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson) throws ShopException,
	    JsonGenerationException, IOException {
    String deviceuid = ParamData.getString(requestParam.getBodyNode(),"deviceuid");
	String date =  ParamData.getString(requestParam.getBodyNode(),"date");
	ZdcMileage zdcMile = new ZdcMileage();
	zdcMile.setDeviceuid(deviceuid);
    zdcMile.setDatastreamid14(date+" 00:00:00");
    zdcMile.setDatastreamid15(date+" 23:59:59");
	List<ZdcGpsinfo> gpsList = null;
	gpsList = zdcGpsinfoService.selectGpsInfoByMileRange(zdcMile);
	responseBodyJson.writeArrayFieldStart("gpsData");
	for (ZdcGpsinfo gps : gpsList) {
	    responseBodyJson.writeStartObject();
	    responseBodyJson.writeNumberField("Latitude", gps.getLatitude());
	    responseBodyJson.writeNumberField("getLongitude", gps.getLongitude());
	    responseBodyJson.writeEndObject();
	}
	responseBodyJson.writeEndArray();

    }
}
