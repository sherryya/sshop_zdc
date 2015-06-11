package com.tmount.db.message.dto;

import java.util.Date;
public class TItovMessage {
	private Long accountId;

	private String messageCode;

	private Date messageTime;

	private String messageContent;

	private String isNew;
	
	private String start_time;

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	private String messageOption;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}
	public int message_account;

	public int getMessage_account() {
		return message_account;
	}

	public void setMessage_account(int message_account) {
		this.message_account = message_account;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getMessageOption() {
		return messageOption;
	}

	public void setMessageOption(String messageOption) {
		this.messageOption = messageOption;
	}

}
