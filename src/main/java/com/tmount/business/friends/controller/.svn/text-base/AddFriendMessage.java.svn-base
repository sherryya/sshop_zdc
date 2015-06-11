package com.tmount.business.friends.controller;

import java.io.IOException;

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
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *添加好友信息
 * 
 * @author 
 * 
 */
@Controller
public class AddFriendMessage extends ControllerBase {
	@Autowired
	private FriendService friendService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "friendmessage.add")
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
		String telno = new String(ParamData.getString(
				requestParam.getBodyNode(), "telno"));
		
		if (StringUtils.isEmpty(telno)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "好友id" });
		}
		if (StringUtils.isEmpty(String.valueOf(account_id))) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "账号id" });
		}
		
		//判断此电话是否已经注册了用户
		UsAccount usUserInfoOld = userService.getUsUserInfoByUserAccount(telno);// 判断用户名是否 存在
		if (usUserInfoOld == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_USER,	new Object[] { telno });
		}
		
		//判断此账号时候已经添加了此手机号码好友
		TItovFriend tItovFriend = new TItovFriend();
		tItovFriend.setAccountId(account_id);
		tItovFriend.setFriendAccountId(usUserInfoOld.getAccount_id());
		TItovFriend  tItovFriends = friendService.getFriendMessage(tItovFriend);
		if (tItovFriends != null) {
			throw new ShopBusiException(ShopBusiErrorBundle.FRIEND_RELATION_ISEXIST,	new Object[] { telno });
		}
		friendService.insert(tItovFriend);
		
		//互填好友
		TItovFriend tItovFriend1 = new TItovFriend();
		tItovFriend1.setAccountId(usUserInfoOld.getAccount_id());
		tItovFriend1.setFriendAccountId(account_id);
		TItovFriend  tItovFriends1 = friendService.getFriendMessage(tItovFriend1);
		if (tItovFriends1 == null) {
			friendService.insert(tItovFriend1);
		}
		
	}
}
