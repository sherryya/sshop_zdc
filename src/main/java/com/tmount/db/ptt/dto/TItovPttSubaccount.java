package com.tmount.db.ptt.dto;

public class TItovPttSubaccount {
	private Long id;

	private Long accountId;

	private String subaccountsid;

	private String subtoken;

	private String voipaccount;

	private String voippwd;

	private String datecreated;

	private String agentstate;

	private String lastLoginTime;

	private Integer isDelete;

	private Integer isUse;

	private Integer subtype;
	
	private Integer account_type;
	/* IMEI串号 */
	private String terminal_imei;
	
	//座席的状态
    private Integer voip_status;

	private String account_name;
	private String hostType; //主播类型
    private String channelType;  //频道类型
    private Integer roomId;  //房间号
	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getSubaccountsid() {
		return subaccountsid;
	}

	public void setSubaccountsid(String subaccountsid) {
		this.subaccountsid = subaccountsid;
	}

	public String getSubtoken() {
		return subtoken;
	}

	public void setSubtoken(String subtoken) {
		this.subtoken = subtoken;
	}

	public String getVoipaccount() {
		return voipaccount;
	}

	public void setVoipaccount(String voipaccount) {
		this.voipaccount = voipaccount;
	}

	public String getVoippwd() {
		return voippwd;
	}

	public void setVoippwd(String voippwd) {
		this.voippwd = voippwd;
	}

	public String getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}

	public String getAgentstate() {
		return agentstate;
	}

	public void setAgentstate(String agentstate) {
		this.agentstate = agentstate;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Integer getSubtype() {
		return subtype;
	}

	public void setSubtype(Integer subtype) {
		this.subtype = subtype;
	}

	public String getTerminal_imei() {
		return terminal_imei;
	}

	public void setTerminal_imei(String terminal_imei) {
		this.terminal_imei = terminal_imei;
	}

	public Integer getAccount_type() {
		return account_type;
	}

	public void setAccount_type(Integer account_type) {
		this.account_type = account_type;
	}

	public Integer getVoip_status() {
		return voip_status;
	}

	public void setVoip_status(Integer voip_status) {
		this.voip_status = voip_status;
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
