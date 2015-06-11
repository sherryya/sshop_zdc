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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.business.host.service.TItovHostStaticService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.db.host.dto.TZdcHostStatic;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 根据voipaccount查询主播在线统计列表
 * 
 * @author
 * 
 */
@Controller
public class HostStaticInfoController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItovHostStaticService zdcHostStaticService;

	@RequestMapping(value = "hostStaticByAccount.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String voipAccount = ParamData.getString(requestParam.getBodyNode(), "voipAccount"); //
		int page_num = ParamData.getInt(requestParam.getBodyNode(), "page_num",-1); //
		int page_size = ParamData.getInt(requestParam.getBodyNode(), "page_size",-1); //
		TZdcHostStatic thost = new TZdcHostStatic();
		//设置账户信息
		thost.setVoipaccount(voipAccount);
		//设置分页
		if (page_num != -1) {
			int startLimit = (page_num-1)*page_size;
			thost.setStartLimit(startLimit);		
		}
		if(page_size!=-1)
		{
			thost.setPageSize(page_size);
		}
		List<TZdcHostStatic> hostStaticList = zdcHostStaticService.selectInfoByVoipAccount(thost);
		//查询总条数
		int count = zdcHostStaticService.selectCountByVoipAccount(voipAccount);
		Type listType = new TypeToken<ArrayList<TZdcHostStatic>>(){}.getType();
	    Gson gson=new Gson();
	    String json=gson.toJson(hostStaticList, listType);
		responseBodyJson.writeStringField("hostStaticInfoList",json.replace("\"", "'"));
		responseBodyJson.writeNumberField("totalCount", count);
		
	}

}
