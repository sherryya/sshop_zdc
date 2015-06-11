package com.tmount.business.friends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.friend.dao.TItovFriendMapper;
import com.tmount.db.friend.dto.TItovFriend;
import com.tmount.db.user.dto.UsUser;

@Service
public class FriendService {
	@Autowired
	private TItovFriendMapper tItovFriendMapper;
	/**
	 * 手机端好友查询
	 * @param account_id
	 * @return
	 */
	public List<TItovFriend> getFriendMessage(long account_id){
		return tItovFriendMapper.getFriendMessage(account_id);
	}
	/**
	 * 车机端 好友查询
	 * @param terminal_imei
	 * @return
	 */
	public List<TItovFriend> getFriendMessageByImei(String terminal_imei){
		return tItovFriendMapper.getFriendMessageByImei(terminal_imei);
	}
	public TItovFriend getFriendMessage(TItovFriend tItovFriend){
		return tItovFriendMapper.getFriendMessageByAll(tItovFriend);
	}
	
	public void insert(TItovFriend tItovFriend){
		tItovFriendMapper.insert(tItovFriend);
	} 
	public void delete(TItovFriend tItovFriend){
		tItovFriendMapper.delete(tItovFriend);
	} 
}
