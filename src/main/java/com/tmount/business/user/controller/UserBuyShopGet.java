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
import com.tmount.db.user.dto.UsBuyItemsKey;
import com.tmount.db.user.vo.UsBuyItemsInput;
import com.tmount.db.user.vo.UsUserBuyItemsView;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MyNaming;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class UserBuyShopGet extends ControllerBase {

	@Autowired
	UserService userService;
	
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(),"user_id"));
		
		UsBuyItemsKey usBuyItemsKey = new UsBuyItemsKey();
		usBuyItemsKey.setUserId(userId);
		UsBuyItemsInput usBuyItemsInput  =new  UsBuyItemsInput();
		usBuyItemsInput.setUserId(userId);
		

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.setPropertyNamingStrategy(new MyNaming() );
		
		responseBodyJson.writeArrayFieldStart("shop_list");
		
		List<UsUserBuyItemsView> usUserBuyShopList = userService.getUsBuyShopList(usBuyItemsKey);
		
		
		for(int i = 0 ;i< usUserBuyShopList.size() ;i++)
		{
			responseBodyJson.writeStartObject();
			responseBodyJson.writeNumberField("shop_id", usUserBuyShopList.get(i).getShopId());
			responseBodyJson.writeStringField("shop_name", usUserBuyShopList.get(i).getShopName());
			responseBodyJson.writeNumberField("scan_pic", usUserBuyShopList.get(i).getScan_pic());
			
			usBuyItemsKey.setShopId(usUserBuyShopList.get(i).getShopId());
			usBuyItemsInput.setShopId(usUserBuyShopList.get(i).getShopId());
			List<UsUserBuyItemsView> usUserBuyItemsViewList  = userService.getUsBuyItemsList(usBuyItemsInput);
			
			
			responseBodyJson.writeFieldName("items_list");
			objectMapper.writeValue(responseBodyJson, usUserBuyItemsViewList);
			
			responseBodyJson.writeEndObject();
		}
		
		responseBodyJson.writeEndArray();
		
		
		
		//objectMapper.writeValue(responseBodyJson, usUserBuyShopList);
	}

	@RequestMapping(value = "user.buy.shop.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
		
	}

}
