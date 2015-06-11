package com.tmount.db.friend.dto;

import java.util.Date;
public class TItovFriend {
	private Long accountId;

	private Long friendAccountId;

	private Date opTime;
	
	private String nickname;
	
	private String voipAccount;
	
	private String phone;
	
	private String personal_sex;
	
	private String voip_status;

	private String pic_name;
	public String getPic_name() {
		return pic_name;
	}

	public void setPic_name(String pic_name) {
		this.pic_name = pic_name;
	}

	public String getVoip_status() {
		return voip_status;
	}

	public void setVoip_status(String voip_status) {
		this.voip_status = voip_status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPersonal_sex() {
		return personal_sex;
	}

	public void setPersonal_sex(String personal_sex) {
		this.personal_sex = personal_sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getVoipAccount() {
		return voipAccount;
	}

	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getFriendAccountId() {
		return friendAccountId;
	}

	public void setFriendAccountId(Long friendAccountId) {
		this.friendAccountId = friendAccountId;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

}
