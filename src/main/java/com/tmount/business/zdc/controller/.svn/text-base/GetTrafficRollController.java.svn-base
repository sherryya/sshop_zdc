package com.tmount.business.zdc.controller;

import java.io.IOException;
import java.util.Iterator;
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
import com.tmount.business.user.service.UserService;
import com.tmount.business.zdc.service.NewsService;
import com.tmount.business.zdc.service.TrafficService;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.db.zdc.dto.NewDetailSub;
import com.tmount.db.zdc.dto.NewsRollSub;
import com.tmount.db.zdc.dto.TrafficRollSub;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetTrafficRollController extends ControllerBaseByLogin {
    Logger logger = Logger.getLogger(GetTrafficRollController.class.getName());
    @Autowired
    private TrafficService trafficService;

    @RequestMapping(value = "TrafficRoll.get")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson, HttpServletRequest request)
	    throws ShopException, JsonGenerationException, IOException {
	String ID = ParamData.getString(requestParam.getBodyNode(), "CID");
	if (ID.equals("0") || ID == "0") {
	    List<TrafficRollSub> trafficRollSub = trafficService
		    .selectTrafficInfo();
	    responseBodyJson.writeFieldName("Object");
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.writeValue(responseBodyJson, trafficRollSub);
	} else {
	    List<TrafficRollSub> newsRollSub = trafficService
		    .selectTrafficInfo(Integer.parseInt(ID));
	    responseBodyJson.writeFieldName("Object");
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.writeValue(responseBodyJson, newsRollSub);
	}
    }
}
