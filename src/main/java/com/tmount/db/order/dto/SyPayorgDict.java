package com.tmount.db.order.dto;

public class SyPayorgDict {
    private Integer orgId;

    private String orgCode;

    private String orgName;

    private String branchId;

    private String coNo;

    private Integer changeType;

    private Double onesFee;

    private Double onesScan;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    public String getCoNo() {
        return coNo;
    }

    public void setCoNo(String coNo) {
        this.coNo = coNo == null ? null : coNo.trim();
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Double getOnesFee() {
        return onesFee;
    }

    public void setOnesFee(Double onesFee) {
        this.onesFee = onesFee;
    }

    public Double getOnesScan() {
        return onesScan;
    }

    public void setOnesScan(Double onesScan) {
        this.onesScan = onesScan;
    }
}