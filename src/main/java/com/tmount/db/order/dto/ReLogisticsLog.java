package com.tmount.db.order.dto;

import java.util.Date;

public class ReLogisticsLog {
    private Long logisticsLoginId;

    private String txLogisticsId;

    private String logisticsNo;

    private String infoType;

    private String infoName;

    private String infoContent;

    private String remark;

    private String opName;

    private Date acceptTime;

    private String currentCity;

    private String nextCity;

    private String facility;

    private String contactInfo;

    private Double weight;

    private String trackingInfo;

    public Long getLogisticsLoginId() {
        return logisticsLoginId;
    }

    public void setLogisticsLoginId(Long logisticsLoginId) {
        this.logisticsLoginId = logisticsLoginId;
    }

    public String getTxLogisticsId() {
        return txLogisticsId;
    }

    public void setTxLogisticsId(String txLogisticsId) {
        this.txLogisticsId = txLogisticsId == null ? null : txLogisticsId.trim();
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo == null ? null : logisticsNo.trim();
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType == null ? null : infoType.trim();
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName == null ? null : infoName.trim();
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent == null ? null : infoContent.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity == null ? null : currentCity.trim();
    }

    public String getNextCity() {
        return nextCity;
    }

    public void setNextCity(String nextCity) {
        this.nextCity = nextCity == null ? null : nextCity.trim();
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility == null ? null : facility.trim();
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getTrackingInfo() {
        return trackingInfo;
    }

    public void setTrackingInfo(String trackingInfo) {
        this.trackingInfo = trackingInfo == null ? null : trackingInfo.trim();
    }
}