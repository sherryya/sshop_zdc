package com.tmount.business.host.controller;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 根据状态查询主播
 */
@Controller
public class SelectHostByVoipStatusController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItovHostUserService zdcHostService;
	
	@RequestMapping(value = "hostListByVoipStatus.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String voipStatus = ParamData.getString(requestParam.getBodyNode(), "voip_status"); //
		String hostType = ParamData.getString(requestParam.getBodyNode(), "hostType"); //
		int pageSize = ParamData.getInt(requestParam.getBodyNode(), "pageSize"); // 每页多少条
		Integer pageNo = new Integer(ParamData.getInt(requestParam.getBodyNode(),"pageNum",-1));//第几页
		TZdcHostUser tZdcHostUser = new TZdcHostUser();
		if (pageNo != -1) {
			int startLimit = (pageNo-1)*pageSize;
			tZdcHostUser.setStartLimit(startLimit);		
		}
		tZdcHostUser.setPageSize(pageSize);
		if(StringUtils.isNotBlank(voipStatus))
		{
			int voip_status = Integer.valueOf(voipStatus);
			tZdcHostUser.setVoip_status(voip_status);
			
		}
		if(StringUtils.isNotBlank(hostType))
		{
			tZdcHostUser.setHostType(hostType);
		}
		
		List<TZdcHostUser> hostList = zdcHostService.selectHostByStatus(tZdcHostUser);
		 Type listType = new TypeToken<ArrayList<TZdcHostUser>>(){}.getType();
	     Gson gson=new Gson();
	     String json=gson.toJson(hostList, listType);
	    //查询总条数    
		int totalCount = zdcHostService.selectCountByStatus(tZdcHostUser);
		responseBodyJson.writeNumberField("totalCount", totalCount);
		responseBodyJson.writeStringField("hostList",json.replace("\"", "'"));		
	}
}
