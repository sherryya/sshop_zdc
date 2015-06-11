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
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 查询全部主播信息
 * 
 * @author
 * 
 */
@Controller
public class SelectAllHostController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItovHostUserService zdcHostService;
	
	@Autowired
	private PttSubaccountService accountService;

	@RequestMapping(value = "hostList.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		int pageSize = ParamData.getInt(requestParam.getBodyNode(), "pageSize"); //每页多少条
		logger.info("hostList.get 查询全部主播信息");
		TZdcHostUser tZdcHostUser = new TZdcHostUser();
		Integer pageNo = new Integer(ParamData.getInt(requestParam.getBodyNode(),"pageNum",-1));//第几页
		if (pageNo != -1) {
			int startLimit = (pageNo-1)*pageSize;
			tZdcHostUser.setStartLimit(startLimit);		
		}
		tZdcHostUser.setPageSize(pageSize);
		//查询全部主播信息
		List<TZdcHostUser> hostList = zdcHostService.selectHostInfo(tZdcHostUser);
		for(TZdcHostUser zdcUser:hostList)
		{
			if(zdcUser.getAccountid()!=null)
			{
				//根据账户id查询坐席状态
				TItovPttSubaccount ptt = accountService.selectByAccount_id(zdcUser.getAccountid());
				zdcUser.setVoip_status(ptt.getVoip_status());
				zdcUser.setHostType(ptt.getHostType());
			
			}
			
		}
		 Type listType = new TypeToken<ArrayList<TZdcHostUser>>(){}.getType();
	     Gson gson=new Gson();
	     String json=gson.toJson(hostList, listType);
	    //查询总条数    
		int totalCount = zdcHostService.selectCount();
		responseBodyJson.writeNumberField("totalCount", totalCount);
		responseBodyJson.writeStringField("hostList",json.replace("\"", "'"));
		
		
	}

}
