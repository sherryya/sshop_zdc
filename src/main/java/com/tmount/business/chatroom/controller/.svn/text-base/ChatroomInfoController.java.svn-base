package com.tmount.business.chatroom.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.chatroom.service.ChatroomService;
import com.tmount.db.chatroom.dto.TZdcChatroom;
import com.tmount.db.chatroom.dto.TZdcChatroomMembers;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
//得到聊天室信息
/**
 * 包括两部分
 * 1：自己创建的
 * 2:好友创建的
 * @author dell
 *
 */
@Controller
public class ChatroomInfoController extends ControllerBaseByLogin {
	@Autowired
	private ChatroomService chatroomService;
	
	@RequestMapping(value = "Chatroom.Get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		long account_id = ParamData.getLong(requestParam.getBodyNode(), "account_id");//用户ID
		List<TZdcChatroom> list=new ArrayList<TZdcChatroom>();
		List<TZdcChatroom> list1=new ArrayList<TZdcChatroom>();
		List<TZdcChatroom> listALL=new ArrayList<TZdcChatroom>();
		try {
			list=chatroomService.select_chatroomByAccountID(account_id);
			if(list!=null)
			{
				for(TZdcChatroom chat:list)
				{
					listALL.add(chat);
				}
			}
			list1=chatroomService.select_chatroomByAccountID1(account_id);
			if(list1!=null)
			{
				for(TZdcChatroom chat:list1)
				{
					listALL.add(chat);
				}
			}
			responseBodyJson.writeFieldName("Object");
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(responseBodyJson, listALL);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseBodyJson.writeNumberField("result", 0);
			return;
		}
	}
}
