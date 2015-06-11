package com.tmount.db.ptt.dto;

import java.util.Date;

public class TItovPttSubaccountLog {
	private Long id;
	
	private int searchID;
	private Integer type;

	private String orderid;

	private String subid;

	private String caller;

	private String called;

	private String byetype;

	private String starttime;

	private String endtime;

	private String billdata;

	private String callsid;

	private String recordurl;

	private String subtype;

	private Date crtdate;

	private Integer pageSize;
	private Integer startLimit;

	public TItovPttSubaccountLog() {
	}

	public TItovPttSubaccountLog(int searchID, Integer pageSize,
			Integer startLimit) {
		this.searchID = searchID;
		this.pageSize = pageSize;
		this.startLimit = startLimit;
	}

	public int getSearchID() {
		return searchID;
	}

	public void setSearchID(int searchID) {
		this.searchID = searchID;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getSubid() {
		return subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
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

	public String getBilldata() {
		return billdata;
	}

	public void setBilldata(String billdata) {
		this.billdata = billdata;
	}

	public String getCallsid() {
		return callsid;
	}

	public void setCallsid(String callsid) {
		this.callsid = callsid;
	}

	public String getRecordurl() {
		return recordurl;
	}

	public void setRecordurl(String recordurl) {
		this.recordurl = recordurl;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public Date getCrtdate() {
		return crtdate;
	}

	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}

}
