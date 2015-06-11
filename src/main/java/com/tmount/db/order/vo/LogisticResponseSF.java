package com.tmount.db.order.vo;

public class LogisticResponseSF {
	private String reasoncode;
	
	private String remark;
	
	public String getReasonCode() {
        return reasoncode;
    }

    public void setReasonCode(String reasoncode) {
        this.reasoncode = reasoncode == null ? null : reasoncode.trim();
    }
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
