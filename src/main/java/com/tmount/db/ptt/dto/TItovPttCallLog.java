package com.tmount.db.ptt.dto;

import java.util.Date;
public class TItovPttCallLog {
	private Long id;

	private String callid;

	private String callfrom;

	private String callto;

	private String direction;

	private Date calldatetime;

	private String callduration;

	private String errorcode;

	private Integer agentid;

	private Integer agentstate;

	private Date starttime;

	private Date endettime;
	
	private	 String recordurl;
	private  String recordid;

	private Integer pageSize;
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartLimit() {
		return startLimit;
	}

	public void setStartLimit(Integer startLimit) {
		this.startLimit = startLimit;
	}

	private Integer startLimit;
	public String getRecordurl() {
		return recordurl;
	}

	public void setRecordurl(String recordurl) {
		this.recordurl = recordurl;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCallid() {
		return callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

	public String getCallfrom() {
		return callfrom;
	}

	public void setCallfrom(String callfrom) {
		this.callfrom = callfrom;
	}

	public String getCallto() {
		return callto;
	}

	public void setCallto(String callto) {
		this.callto = callto;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Date getCalldatetime() {
		return calldatetime;
	}

	public void setCalldatetime(Date calldatetime) {
		this.calldatetime = calldatetime;
	}

	public String getCallduration() {
		return callduration;
	}

	public void setCallduration(String callduration) {
		this.callduration = callduration;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public Integer getAgentstate() {
		return agentstate;
	}

	public void setAgentstate(Integer agentstate) {
		this.agentstate = agentstate;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndettime() {
		return endettime;
	}

	public void setEndettime(Date endettime) {
		this.endettime = endettime;
	}

}
