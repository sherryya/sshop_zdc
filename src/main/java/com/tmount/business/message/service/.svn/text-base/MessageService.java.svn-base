package com.tmount.business.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.message.dao.MessageMapper;
import com.tmount.db.message.dao.TItovMessageMapper;
import com.tmount.db.message.dto.MessageRelation;
import com.tmount.db.message.dto.TItovMessage;

@Service
public class MessageService {
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private TItovMessageMapper titovMessageMapper;
	
 
	public int deleteRelation(Long account_id){
		return messageMapper.deleteRelation(account_id);
	}
	
	public void insert(MessageRelation messageRelation){
		messageMapper.insert(messageRelation);
	}
	
	public List <MessageRelation> getListByAccountId(Long account_id){
		return messageMapper.getListByAccountId(account_id);
	}
	public void insertUserAdd(MessageRelation messageRelation){
		messageMapper.insertUserAdd(messageRelation);
	}
	
	public List<TItovMessage> getNewMessage(Long account_id){
		return titovMessageMapper.getNewMessage(account_id);
	}
	
	public void updateIsNew(Long account_id){
		 titovMessageMapper.updateIsNew(account_id);
	}
	public List<TItovMessage> getMessage(TItovMessage titovMessage){
		return titovMessageMapper.getMessage(titovMessage);
	}
	public void insertMessage(TItovMessage titovMessage){
		titovMessageMapper.insert(titovMessage);
	}
	
	
	
}
