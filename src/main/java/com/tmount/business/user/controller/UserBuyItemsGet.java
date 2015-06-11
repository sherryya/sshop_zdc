package com.tmount.business.user.controller;

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
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.vo.UsBuyItemsInput;
import com.tmount.db.user.vo.UsUserBuyItemsView;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MyNaming;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class UserBuyItemsGet extends ControllerBase {

	@Autowired
	UserService userService;
	
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(),"user_id"));
		int pageSize = new Integer(ParamData.getInt(requestParam.getBodyNode(),"page_size",-1));
		int startLimit =-1;
		int pageNo = new Integer(ParamData.getInt(requestParam.getBodyNode(),"page_no",-1));
		
	
		UsBuyItemsInput sBuyItemsInput = new UsBuyItemsInput();
		sBuyItemsInput.setUserId(userId);
		if (pageNo != -1) {
			startLimit = (pageNo-1)*pageSize;
			sBuyItemsInput.setStartLimit(startLimit);		
		}
		if (pageSize != -1) {
			sBuyItemsInput.setPageSize(pageSize);
		}
		int recordCount = userService.getUsBuyItemsCount(sBuyItemsInput);
		int pageCount = 0;
		if(pageSize!=-1){			
			pageCount =( recordCount+(pageSize-1))/pageSize;
		}
		
		
		List<UsUserBuyItemsView> usUserBuyItemsViewList  = userService.getUsBuyItemsList(sBuyItemsInput);
		responseBodyJson.writeNumberField("page_count", pageCount);
		responseBodyJson.writeFieldName("items_list");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.setPropertyNamingStrategy(new MyNaming() );
		objectMapper.writeValue(responseBodyJson, usUserBuyItemsViewList);
	}

	@RequestMapping(value = "user.buy.items.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
		
	}

}
