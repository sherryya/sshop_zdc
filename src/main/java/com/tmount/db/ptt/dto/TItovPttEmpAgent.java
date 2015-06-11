package com.tmount.db.ptt.dto;

public class TItovPttEmpAgent {
	private Long id;

	private Integer agentId;

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
	
	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	private String agentType;
    private String agent_name;
	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
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

}
