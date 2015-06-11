package com.tmount.business.chatroom.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.chatroom.service.ChatroomService;
import com.tmount.db.chatroom.dto.TZdcChatroomMembers;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
//添加聊天室成员
@Controller
public class ChatroomMembersAddController extends ControllerBaseByLogin {
	@Autowired
	private ChatroomService chatroomService;
	
	@RequestMapping(value = "ChatroomMembers.Add")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		long chatroom_id = ParamData.getLong(requestParam.getBodyNode(), "chatroom_id");//房间号
		long account_id = ParamData.getLong(requestParam.getBodyNode(), "account_id");//用户ID
		
		try {
			TZdcChatroomMembers tZdcChatroomMembers=new TZdcChatroomMembers();
			tZdcChatroomMembers.setAccountId(account_id);
			tZdcChatroomMembers.setChatroomId(chatroom_id);
			int ret=chatroomService.insert_chatroom_members(tZdcChatroomMembers);
			responseBodyJson.writeNumberField("result", ret);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseBodyJson.writeNumberField("result", 0);
			return;
		}
	}
}
