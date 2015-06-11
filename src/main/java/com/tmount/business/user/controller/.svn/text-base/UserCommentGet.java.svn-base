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
import com.tmount.db.user.dto.UsUserComment;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 用户评论获取
 * @author rendi
 *
 */
@Controller
public class UserCommentGet extends ControllerBase {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "user.comment.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(),"userId",-1));
		Long shopId = new Long(ParamData.getLong(requestParam.getBodyNode(),"shopId",-1));
		Long itemsId = new Long(ParamData.getLong(requestParam.getBodyNode(),"itemsId",-1));
		Integer commentLevel = new Integer(ParamData.getInt(requestParam.getBodyNode(),"commentLevel",-1));
		String commentTime = new String(ParamData.getString(requestParam.getBodyNode(),"commentTime",""));
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		
		ObjectMapper objectMapper = new ObjectMapper();
		UsUserComment usUserComment = new UsUserComment();
		
		if(userId != -1)
			usUserComment.setUserId(userId);
		if(shopId != -1)
			usUserComment.setShopId(shopId);
		if(itemsId != -1)
			usUserComment.setItemsId(itemsId);
		if(commentLevel != -1)
			usUserComment.setCommentLevel(commentLevel);
		if(!"".equals(commentTime))
		{
			try
			{
				usUserComment.setCommentTime(simpleDateFormat.parse(commentTime));
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		List<UsUserComment> usUserCommentList = userService.getUsUserCommentList(usUserComment);
		responseBodyJson.writeFieldName("comment");
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"));
		objectMapper.writeValue(responseBodyJson, usUserCommentList);
	}
}
