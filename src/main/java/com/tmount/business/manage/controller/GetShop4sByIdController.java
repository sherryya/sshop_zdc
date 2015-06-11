package com.tmount.business.manage.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.manage.service.TItov_shop4sService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 通过id查找shop4s信息
 * 
 * @author
 * 
 */
@Controller
public class GetShop4sByIdController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItov_shop4sService tItov_shop4sService;

	@RequestMapping(value = "getshop4sinfoById.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		int id = ParamData.getInt(requestParam.getBodyNode(), "id"); //
		logger.info("getshop4sinfoById.get");
		//get host info
		List<TItov_shop4s_manage> shop4sList = tItov_shop4sService.selectById(id);
		Type listType = new TypeToken<ArrayList<TItov_shop4s_manage>>(){}.getType();
	    Gson gson=new Gson();
	    
	    if(null != shop4sList && shop4sList.size()>0)
	    {
	    	TItov_shop4s_manage shop4s = shop4sList.get(0);
	    	//get accountId
	    	if(shop4s==null)
	    	{
	    		responseBodyJson.writeNumberField("shop4s_id",0);
	    	}else
	    	{
	    		int shop4s_id = shop4s.getShop4s_id();
	    		//TItov_shop4s_manage shop_4s = tItov_shop4sService.selectShop4sInfo(shop4s_id);
	    			
	    			shop4s.setpId(shop4s.getpId());
	    			shop4s.setcId(shop4s.getcId());
	    			shop4s.setdId(shop4s.getdId());
	    			
	    			shop4s.setShop4s_name(shop4s.getShop4s_name());
	    			shop4s.setShop4s_address(shop4s.getShop4s_address());
	    			shop4s.setShop4s_principal(shop4s.getShop4s_principal());
	    			shop4s.setShop4s_tel(shop4s.getShop4s_tel());
	    			shop4s.setShop4s_note(shop4s.getShop4s_note());
	    			
	    			/*
	    			hostUser.setAccount_name(host.getAccount_name());
	    			hostUser.setHostType(host.getHostType());
	    			hostUser.setRoomId(host.getRoomId());
	    			if(null!=host.getChannelType())
	    			{
	    				hostUser.setChannelType(host.getChannelType());
	    			}
	    			*/
	    		
	    		responseBodyJson.writeNumberField("shop4s_id",shop4s_id);
	    	}
	    
	    		
	    	
	    	
	    }
	    String json=gson.toJson(shop4sList, listType);
		responseBodyJson.writeStringField("shop4sList",json.replace("\"", "'"));
		
		
	}

}
