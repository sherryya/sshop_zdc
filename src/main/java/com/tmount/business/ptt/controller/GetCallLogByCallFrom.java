package com.tmount.business.ptt.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.ptt.service.PttCallLogService;
import com.tmount.db.ptt.dto.TItovPttCallLog;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MyNaming;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetCallLogByCallFrom extends ControllerBase {
	@Autowired
	PttCallLogService pttCallLogService;
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		String callfrom=ParamData.getString(requestParam.getBodyNode(), "callfrom");
		int pageSize = new Integer(ParamData.getInt(requestParam.getBodyNode(),"page_size",-1));
		int startLimit =-1;
		int pageNo = new Integer(ParamData.getInt(requestParam.getBodyNode(),"page_no",-1));
	/*	SimpleDateFormat tempDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String datetime1 = tempDate1.format(new java.util.Date());
		System.out.println(datetime1);*/
		TItovPttCallLog tItovPttCallLog = new TItovPttCallLog();
		tItovPttCallLog.setCallfrom(callfrom);
		if (pageNo != -1) {
			startLimit = (pageNo-1)*pageSize;
			tItovPttCallLog.setStartLimit(startLimit);		
		}
		if (pageSize != -1) {
			tItovPttCallLog.setPageSize(pageSize);
		}
		int recordCount = pttCallLogService.selectCountByCallFrom(tItovPttCallLog);
		int pageCount = 0;
		if(pageSize!=-1){			
			pageCount =( recordCount+(pageSize-1))/pageSize;
		}
		List<TItovPttCallLog> usUserBuyItemsViewList  = pttCallLogService.selectListByCallFrom(tItovPttCallLog);
		responseBodyJson.writeNumberField("page_count", pageCount);
		responseBodyJson.writeNumberField("sum_count", recordCount);
		responseBodyJson.writeFieldName("items_list");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.setPropertyNamingStrategy(new MyNaming() );
		objectMapper.writeValue(responseBodyJson, usUserBuyItemsViewList);
/*		SimpleDateFormat tempDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String datetime2 = tempDate2.format(new java.util.Date());
		System.out.println(datetime2);*/
	}

	@RequestMapping(value = "callLogByCallFrom.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
		
	}

}
