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
import com.tmount.db.manage.dto.TItov_shop4s_user;
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
public class GetUser4sByIdController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItov_shop4sService tItov_shop4sService;

	@RequestMapping(value = "getUser4sinfoById.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		int id = ParamData.getInt(requestParam.getBodyNode(), "id"); 
		logger.info("getUser4sinfoById.get");
		//get host info
		List<TItov_shop4s_user> user4sList = tItov_shop4sService.selectUser4sById(id);
		Type listType = new TypeToken<ArrayList<TItov_shop4s_user>>(){}.getType();
	    Gson gson=new Gson();
	    
	    if(null != user4sList && user4sList.size()>0)
	    {
	    	TItov_shop4s_user User4s = user4sList.get(0);
	    	//get accountId
	    	if(null==User4s)
	    	{
	    		responseBodyJson.writeNumberField("accountid",0);
	    	}else
	    	{
	    		Long account_id = User4s.getAccount_id();
	    		responseBodyJson.writeNumberField("accountid",account_id);
	    	}
	    }
	    String json=gson.toJson(user4sList, listType);
		responseBodyJson.writeStringField("user4sList",json.replace("\"", "'"));
		
		
	}

}
