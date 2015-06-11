package com.tmount.business.friends.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.tmount.business.user.service.UserService;
import com.tmount.db.friend.dto.TItovFriend;
import com.tmount.db.message.dto.TItovMessage;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *删除好友信息
 * 
 * @author 
 * 
 */
@Controller
public class DeleteFriendMessage extends ControllerBase {
	@Autowired
	private FriendService friendService;
	
	
	@RequestMapping(value = "friendmessage.delete")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		Long account_id = ParamData.getLong(requestParam.getBodyNode(),
				"account_id");//账号id
		
		Long friend_account_id = ParamData.getLong(requestParam.getBodyNode(),
		"friend_account_id");//好友账号id
		
		if (StringUtils.isEmpty(String.valueOf(friend_account_id))) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "好友账号id" });
		}
		if (StringUtils.isEmpty(String.valueOf(account_id))) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "账号id" });
		}
		TItovFriend tItovFriend = new TItovFriend();
		tItovFriend.setAccountId(account_id);
		tItovFriend.setFriendAccountId(friend_account_id);
		
		
		TItovFriend tItovFriend1 = new TItovFriend();
		tItovFriend1.setAccountId(friend_account_id);
		tItovFriend1.setFriendAccountId(account_id);
		
		
		friendService.delete(tItovFriend);
		
		

		friendService.delete(tItovFriend1);
	}
}
