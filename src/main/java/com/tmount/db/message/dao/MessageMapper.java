package com.tmount.db.message.dao;

import java.util.List;

import com.tmount.db.message.dto.MessageRelation;
import com.tmount.db.message.dto.TItovMessage;

public interface MessageMapper {
	int deleteRelation(Long account_id);

	void insert(MessageRelation messageRelation);

	List<MessageRelation> getListByAccountId(Long account_id);


	void insertUserAdd(MessageRelation messageRelation);
	
	
	void insertMessage(TItovMessage titovMessage);
}
