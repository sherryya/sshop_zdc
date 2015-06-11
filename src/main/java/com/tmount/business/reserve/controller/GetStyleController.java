package com.tmount.business.reserve.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.manage.service.TItov_cityService;
import com.tmount.business.reserve.service.ReserveService;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.manage.dto.TItov_city_manage;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

//
/**
 * 得到指定区县信息
 * 
 * @author qgy
 * 
 */
@Controller
public class GetStyleController extends ControllerBase{

    @Autowired
    private ReserveService tItov_cityService;

    @RequestMapping(value = "reserve.carstyle.get")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
    	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson) throws ShopException,
	    JsonGenerationException, IOException {
	try {
	    List<TItov_city_manage> arr = new ArrayList<TItov_city_manage>();
	    TItov_city_manage tItov_city_manage = new TItov_city_manage();
	    
	    String cId = ParamData.getString(requestParam.getBodyNode(),
			    "cId");// 
	    tItov_city_manage.setcId(cId);
	    arr = tItov_cityService.selectCountryByWhere(tItov_city_manage);
	    Type listType = new TypeToken<ArrayList<CarModel>>() {
	    }.getType();
	    Gson gson = new Gson();
	    String json = gson.toJson(arr, listType);
	    responseBodyJson
		    .writeStringField("result", json.replace("\"", "'"));
	} catch (Exception e) {
	    // TODO: handle exception
		e.printStackTrace();
	    throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK,
		    new Object[] { null });
	}
    }


}
