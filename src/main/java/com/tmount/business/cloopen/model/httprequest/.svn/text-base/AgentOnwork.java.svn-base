package com.tmount.business.cloopen.model.httprequest;

public class AgentOnwork {
	//坐席上班
String Appid;//必选	 应用id节点
String number;//必选	 座席号码，手机号或座机号或voip帐号
String agentid;//必选	 座席ID，4位正整数，由应用侧管理
String agenttype;//可选	 座席类型,与队列类型一致，默认值为0，可以有多个值，使用逗号分隔。
String agentstate;//可选	 座席状态：0准备中 ，1：准备就绪，2：用户锁定，3：咨询通话中，默认值为0
public String getAppid() {
	return Appid;
}
public void setAppid(String appid) {
	Appid = appid;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getAgentid() {
	return agentid;
}
public void setAgentid(String agentid) {
	this.agentid = agentid;
}
public String getAgenttype() {
	return agenttype;
}
public void setAgenttype(String agenttype) {
	this.agenttype = agenttype;
}
public String getAgentstate() {
	return agentstate;
}
public void setAgentstate(String agentstate) {
	this.agentstate = agentstate;
}
public AgentOnwork(String appid, String number, String agentid, String agentstate) {
	super();
	Appid = appid;
	this.number = number;
	this.agentid = agentid;
	this.agentstate = agentstate;
}
public AgentOnwork()
{
	super();
}
}
