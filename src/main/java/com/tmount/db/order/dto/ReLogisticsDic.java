package com.tmount.db.order.dto;

import java.util.Date;

public class ReLogisticsDic {
    private Integer logisticsId;

    private String logisticsName;

    private String clientId;

    private String logisticProviderId;

    private Double payRate;

    private Date updateTime;

    private Integer updateNo;

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName == null ? null : logisticsName.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getLogisticProviderId() {
        return logisticProviderId;
    }

    public void setLogisticProviderId(String logisticProviderId) {
        this.logisticProviderId = logisticProviderId == null ? null : logisticProviderId.trim();
    }

    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(Integer updateNo) {
        this.updateNo = updateNo;
    }
}