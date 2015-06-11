package com.tmount.db.user.vo;

import java.util.Date;

public class UserInfo {
	private Long userId;

    private Integer companyId;

    private String userAccount;

    private String cellPhone;

    private String userName;

    private String password;

    private String email;

    private String nickName;

    private String checkCode;

    private Long userAvatar;

    private String gender;

    private Date logonTime;

    private Integer userLevel;

    private String state;

    private Date createTime;

    private Date updateTime;
    
    private String userLevelName;
    
    private String mailVerify;
    
    private String phoneVerify;

	public String getMailVerify() {
		return mailVerify;
	}

	public void setMailVerify(String mailVerify) {
		this.mailVerify = mailVerify;
	}

	public String getPhoneVerify() {
		return phoneVerify;
	}

	public void setPhoneVerify(String phoneVerify) {
		this.phoneVerify = phoneVerify;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public Long getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(Long userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getLogonTime() {
		return logonTime;
	}

	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserLevelName() {
		return userLevelName;
	}

	public void setUserLevelName(String userLevelName) {
		this.userLevelName = userLevelName;
	}
}
