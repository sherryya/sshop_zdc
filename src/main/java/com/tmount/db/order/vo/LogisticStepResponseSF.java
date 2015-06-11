package com.tmount.db.order.vo;

import java.util.Date;

public class LogisticStepResponseSF {
	private Date acceptTime;
	
	private String acceptAddress;
	
	private String remark;
	
	public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }
	
	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
	
	public String getAcceptAddress() {
        return acceptAddress;
    }

    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress == null ? null : acceptAddress.trim();
    }
}
