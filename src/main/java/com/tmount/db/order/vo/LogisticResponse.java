package com.tmount.db.order.vo;

public class LogisticResponse {

	private String txLogisticID;
	
	private String logisticProviderID;
	
	private Boolean success;
	
	private String reason;
	
	private String reasonDetail;
	
	public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID == null ? null : txLogisticID.trim();
    }
    
    public String getLogisticProviderID() {
        return logisticProviderID;
    }

    public void setLogisticProviderID(String logisticProviderID) {
        this.logisticProviderID = logisticProviderID == null ? null : logisticProviderID.trim();
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
    
    public String getReasonDetail() {
        return reasonDetail;
    }

    public void setReasonDetail(String reasonDetail) {
        this.reasonDetail = reasonDetail == null ? null : reasonDetail.trim();
    }
    
    public Boolean getSuccess() {
        return success;
    }

    public void setsuccess(Boolean success) {
        this.success = success ;
    }
}
