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
import com.tmount.business.mileage.service.ZdcGpsinfoService;
import com.tmount.business.mileage.service.ZdcMileageService;
import com.tmount.db.mileage.dto.ZdcGpsinfo;
import com.tmount.db.mileage.dto.ZdcMileage;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 查询某个里程的轨迹信息
 * 
 * @author
 * 
 */
@Controller
public class MileTrajectoryController extends ControllerBase {
    @Autowired
    private ZdcMileageService zdcMileageService;
    @Autowired
    private ZdcGpsinfoService zdcGpsinfoService;

    @RequestMapping(value = "mileTrajectory.get")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson) throws ShopException,
	    JsonGenerationException, IOException {
	Long id = ParamData.getLong(requestParam.getBodyNode(), "id");
	// 查询某个里程信息
	List<ZdcMileage> mileList = zdcMileageService.selectByPrimaryKey(id);
	ZdcMileage zdcMile = new ZdcMileage();
	if (mileList != null && mileList.size() > 0) {
	    zdcMile.setId(mileList.get(0).getId());
	    zdcMile.setDeviceuid(mileList.get(0).getDeviceuid());
	    zdcMile.setDatastreamid14(mileList.get(0).getDatastreamid14());
	    zdcMile.setDatastreamid15(mileList.get(0).getDatastreamid15());
	}
	List<ZdcGpsinfo> gpsList = null;
	if (zdcMile.getDatastreamid14() == null) {
	    gpsList = zdcGpsinfoService.selectGpsInfoByMileSevice(zdcMile);
	} else {
	    gpsList = zdcGpsinfoService.selectGpsInfoByMileRange(zdcMile);
	}
	// 根据里程的起始和结束时间查询gps信息
	/*
	 * responseBodyJson.writeFieldName("Object"); ObjectMapper objectMapper
	 * = new ObjectMapper(); objectMapper.writeValue(responseBodyJson,
	 * gpsList);
	 */
	responseBodyJson.writeArrayFieldStart("gpsData");
	for (ZdcGpsinfo gps : gpsList) {

	    responseBodyJson.writeStartObject();
	    responseBodyJson.writeNumberField("Latitude", gps.getLatitude());
	    responseBodyJson.writeNumberField("getLongitude",
		    gps.getLongitude());
	    responseBodyJson.writeEndObject();
	}
	responseBodyJson.writeEndArray();

    }
}
