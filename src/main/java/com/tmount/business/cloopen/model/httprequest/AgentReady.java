package com.tmount.business.cloopen.model.httprequest;

public class AgentReady {
	String Appid;//必选	 应用id节点
	String agentid;//必选	 座席ID，4位正整数，由应用侧管理
	
	public String getAppid() {
		return Appid;
	}
	public void setAppid(String appid) {
		Appid = appid;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public AgentReady(String appid, String agentid) {
		super();
		Appid = appid;
		this.agentid = agentid;
	}

	public AgentReady()
	{
		
	}
}
