package com.tmount.db.message.dao;

import java.util.List;

import com.tmount.db.message.dto.TItovMessage;

public interface TItovMessageMapper {
	List<TItovMessage> getNewMessage(Long account_id);
	
	void updateIsNew(Long id);
	
	
	List<TItovMessage> getMessage(TItovMessage titovMessage);
	
	
	void insert(TItovMessage titovMessage);
}
