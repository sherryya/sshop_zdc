package com.tmount.business.chatroom.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmount.db.chatroom.dao.ChatroomMapper;
import com.tmount.db.chatroom.dto.TZdcChatroom;
import com.tmount.db.chatroom.dto.TZdcChatroomMembers;
import com.tmount.db.chatroom.dto.TZdcChatroomid;
@Service
public class ChatroomService {

	@Autowired
	private ChatroomMapper chatroomMapper;

	// 添加聊天室成员
	public int insert_chatroom_members(TZdcChatroomMembers tZdcChatroomMembers) {
		return chatroomMapper.insert_chatroom_members(tZdcChatroomMembers);
	}

	// 添加聊天室
	public int insert_chatroom(TZdcChatroom tZdcChatroom) {
		return chatroomMapper.insert_chatroom(tZdcChatroom);
	}

	// 得到房间号
	public TZdcChatroomid select_chatroomid() {
		return chatroomMapper.select_chatroomid();
	}

	// 修改房间号
	public int update_chatroomid(TZdcChatroomid tZdcChatroomid) {
		return chatroomMapper.update_chatroomid(tZdcChatroomid);
	}
	
	//查询聊天室信息   查询自己创建
	public 	List<TZdcChatroom> select_chatroomByAccountID(long AccountID)
	{
		return chatroomMapper.select_chatroomByAccountID(AccountID);
	}
	
	//查询聊天室信息 查询好友创建
	public 	List<TZdcChatroom> select_chatroomByAccountID1(long AccountID)
	{
		return chatroomMapper.select_chatroomByAccountID1(AccountID);
	}
	
	
	//退出聊天室
	public int delete_chatroom_members(TZdcChatroomMembers tZdcChatroomMembers)
	{
		return chatroomMapper.delete_chatroom_members(tZdcChatroomMembers);
	}
	
	public int  delete_chatroomALL(long id)
	{
		return chatroomMapper.delete_chatroomALL(id);
	}
	public int delete_chatroom_membersALL(long chatroom_id)
	{
		return chatroomMapper.delete_chatroom_membersALL(chatroom_id);
	}
}
