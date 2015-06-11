package com.tmount.db.chatroom.dao;
import java.util.List;

import com.tmount.db.chatroom.dto.TZdcChatroom;
import com.tmount.db.chatroom.dto.TZdcChatroomMembers;
import com.tmount.db.chatroom.dto.TZdcChatroomid;
public interface ChatroomMapper {
	//添加聊天室成员
	int insert_chatroom_members(TZdcChatroomMembers tZdcChatroomMembers);
	//添加聊天室
	int insert_chatroom(TZdcChatroom tZdcChatroom);
	//得到房间号
	TZdcChatroomid select_chatroomid();
	//修改房间号
	int update_chatroomid(TZdcChatroomid tZdcChatroomid);
	//查询聊天室信息   查询自己创建
	List<TZdcChatroom> select_chatroomByAccountID(long AccountID);
	
	//查询聊天室信息  查询好友创建
	List<TZdcChatroom> select_chatroomByAccountID1(long AccountID);
	
	//退出聊天室
	int delete_chatroom_members(TZdcChatroomMembers tZdcChatroomMembers);
	
	int delete_chatroomALL(long id);
    int delete_chatroom_membersALL(long chatroom_id);
}
