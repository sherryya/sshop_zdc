package com.tmount.db.sys.dto;

public class SyClientVer {
    private Integer clientId;

    private Integer companyId;

    private String clientName;

    private String clientType;

    private Integer clientVer;

    private String verDesc;

    private String updateNow;

    private String updateUrl;

    private String updateDesc;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
    }

    public Integer getClientVer() {
        return clientVer;
    }

    public void setClientVer(Integer clientVer) {
        this.clientVer = clientVer;
    }

    public String getVerDesc() {
        return verDesc;
    }

    public void setVerDesc(String verDesc) {
        this.verDesc = verDesc == null ? null : verDesc.trim();
    }

    public String getUpdateNow() {
        return updateNow;
    }

    public void setUpdateNow(String updateNow) {
        this.updateNow = updateNow == null ? null : updateNow.trim();
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl == null ? null : updateUrl.trim();
    }

    public String getUpdateDesc() {
        return updateDesc;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc == null ? null : updateDesc.trim();
    }
}