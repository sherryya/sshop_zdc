package com.tmount.db.user.vo;

import com.tmount.db.user.dto.UsUserComment;

public class UsUserCommentUser extends UsUserComment {
    private String userAccount;

	private String userName;
	private Long userAvatar;

    public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(Long userAvatar) {
		this.userAvatar = userAvatar;
	}

}
