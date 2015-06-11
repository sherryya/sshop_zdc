package com.tmount.business.friends.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.friends.service.FriendService;
import com.tmount.db.friend.dto.TItovFriend;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *好友信息查询
 * 
 * @author 
 * 
 */
@Controller
public class GetFriendMessage extends ControllerBase {
	@Autowired
	private FriendService friendService;
	
	
	@RequestMapping(value = "friendmessage.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		Long account_id = ParamData.getLong(requestParam.getBodyNode(),"account_id");//账号id
		//String terminal_imei=ParamData.getString(requestParam.getBodyNode(), "terminal_imei");
		if (StringUtils.isEmpty(String.valueOf(account_id))) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "账号id" });
		}
		responseBodyJson.writeArrayFieldStart("Data");
		List<TItovFriend> list = friendService.getFriendMessage(account_id);
		for(TItovFriend message:list){
			responseBodyJson.writeStartObject();
			responseBodyJson.writeNumberField("friend_account_id", message.getAccountId());
			responseBodyJson.writeStringField("nickname", StringUtils.isNotEmpty(message.getNickname())?message.getNickname() : "");
			responseBodyJson.writeStringField("voipAccount", message.getVoipAccount());
			responseBodyJson.writeStringField("phone", message.getPhone());
			responseBodyJson.writeStringField("personal_sex", message.getPersonal_sex());//1--男 0--女
			responseBodyJson.writeStringField("voip_status", message.getVoip_status());
			responseBodyJson.writeStringField("pic_name", message.getPic_name());
			responseBodyJson.writeEndObject();
		}
		responseBodyJson.writeEndArray();
		
	}
}
