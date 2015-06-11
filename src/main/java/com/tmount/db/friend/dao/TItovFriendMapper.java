package com.tmount.db.friend.dao;

import java.util.List;

import com.tmount.db.friend.dto.TItovFriend;

public interface TItovFriendMapper {

	List<TItovFriend> getFriendMessage(long accountId);
	
	List<TItovFriend> getFriendMessageByImei(String terminal_imei);
	
	TItovFriend getFriendMessageByAll(TItovFriend tItovFriend);
	
	void insert(TItovFriend tItovFriend);
	
	void delete(TItovFriend tItovFriend);
}
