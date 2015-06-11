package com.tmount.business.host.controller;

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
import com.tmount.business.user.service.UserService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 鏍规嵁涓绘挱id娲诲緱涓绘挱淇℃伅
 * 
 * @author
 * 
 */
@Controller
public class getHostByIdController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItovHostUserService zdcHostService;

	@RequestMapping(value = "gethostinfoById.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		int id = ParamData.getInt(requestParam.getBodyNode(), "id"); //姣忛〉澶氬皯鏉�
		logger.info("gethostinfoById.get");
		//get host info
		List<TZdcHostUser> hostList = zdcHostService.selectById(id);
		Type listType = new TypeToken<ArrayList<TZdcHostUser>>(){}.getType();
	    Gson gson=new Gson();
	    
	    if(null != hostList && hostList.size()>0)
	    {
	    	TZdcHostUser hostUser = hostList.get(0);
	    	//get accountId
	    	if(null == hostUser.getAccountid())
	    	{
	    		responseBodyJson.writeNumberField("accountid",0);
	    	}else
	    	{
	    		Long account_id = hostUser.getAccountid();
	    		TZdcHostUser host = zdcHostService.selectAccountInfo(account_id);
	    		if(null!=host)
	    		{
	    			hostUser.setAccount_name(host.getAccount_name());
	    			hostUser.setHostType(host.getHostType());
	    			hostUser.setRoomId(host.getRoomId());
	    			if(null!=host.getChannelType())
	    			{
	    				hostUser.setChannelType(host.getChannelType());
	    			}
	    		}
	    		responseBodyJson.writeNumberField("accountid",account_id);
	    	}
	    
	    		
	    	
	    	
	    }
	    String json=gson.toJson(hostList, listType);
		responseBodyJson.writeStringField("hostList",json.replace("\"", "'"));
		
		
	}

}
