package com.tmount.business.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsFavouriteShop;
import com.tmount.db.user.vo.UsFavouriteShopInput;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MyNaming;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class UserFavouriteShopListGet extends ControllerBase {

	@Autowired
	private UserService userService;
	
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(),
		"user_id"));
		Long shopId = new Long(ParamData.getLong(requestParam.getBodyNode(),"shop_id",-1));
		int pageNo = new Integer(ParamData.getInt(requestParam.getBodyNode(),"page_no",-1));
		int pageSize = new Integer(ParamData.getInt(requestParam.getBodyNode(),"page_size",-1));
		int startLimit = new Integer(ParamData.getInt(requestParam.getBodyNode(),"start_limit",-1));
		UsFavouriteShopInput usFavouriteShopInput = new UsFavouriteShopInput();
		if(userId!=-1){
			usFavouriteShopInput.setUserId(userId);
		}
		if(shopId!=-1){
			usFavouriteShopInput.setShopId(shopId);
		}
		if(pageNo!=-1){
			usFavouriteShopInput.setPageNo(pageNo);	
		}
		if(pageSize!=-1){
			usFavouriteShopInput.setPageSize(pageSize);		
		}
		if(startLimit!=-1){
			usFavouriteShopInput.setStartLimit(startLimit);		
		}
		if(pageNo!=-1&&pageSize!=-1){
			startLimit = (pageNo-1)*pageSize;
			usFavouriteShopInput.setStartLimit(startLimit);		
		}
		int recordCount = userService.selectUsFavouriteCountBySelective(usFavouriteShopInput);
		System.out.println("@:***recordCount***"+recordCount);
		int pageCount = 0;
		if(pageSize!=-1){			
			pageCount =( recordCount+(pageSize-1))/pageSize;
		}
		
		
		ArrayList<UsFavouriteShop> usFavouriteShopList = (ArrayList<UsFavouriteShop>) userService.selectUsFavouriteShopList(usFavouriteShopInput);
		Collections.reverse(usFavouriteShopList);
		
		responseBodyJson.writeNumberField("page_count", pageCount);
		responseBodyJson.writeFieldName("user_favourite_shop_list");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.setPropertyNamingStrategy(new MyNaming() );
		objectMapper.writeValue(responseBodyJson, usFavouriteShopList);
		
		
	}
	
	@RequestMapping(value = "user.favourite.shop.list.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);

	}

}
