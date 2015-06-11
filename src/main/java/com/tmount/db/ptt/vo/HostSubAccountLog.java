package com.tmount.db.ptt.vo;

public class HostSubAccountLog {
	
	private Integer type;
	
	private String caller;

	private String called;   // =voipaccount
	
	private String voipaccount;
	
	private String account_name;  //用户名

	private String byetype;

	private String starttime;

	private String endtime;

	private String recordurl;

	private Long accountId;
	
	private String hostName;
	
	private String sex;
	
	private String telphone;
	
	private String email;
	
	private String voip_status;  //状态
	
	private int startLimit;  //第几页

	private int pageSize;   //每页多少条
	
	private String onlinetime;  //主播在线时长
	
	private String voipPwd;   
	
	private String account_password;  //user password
	
	private String hostType; //主播类型
    private String channelType;  //频道类型
    private Integer roomId;  //房间号
	
	private String resultStatus;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getCalled() {
		return called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public String getByetype() {
		return byetype;
	}

	public void setByetype(String byetype) {
		this.byetype = byetype;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getRecordurl() {
		return recordurl;
	}

	public void setRecordurl(String recordurl) {
		this.recordurl = recordurl;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public int getStartLimit() {
		return startLimit;
	}

	public void setStartLimit(int startLimit) {
		this.startLimit = startLimit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVoip_status() {
		return voip_status;
	}

	public void setVoip_status(String voip_status) {
		this.voip_status = voip_status;
	}

	public String getVoipaccount() {
		return voipaccount;
	}

	public void setVoipaccount(String voipaccount) {
		this.voipaccount = voipaccount;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}

	public String getVoipPwd() {
		return voipPwd;
	}

	public void setVoipPwd(String voipPwd) {
		this.voipPwd = voipPwd;
	}

	public String getAccount_password() {
		return account_password;
	}

	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	

}
