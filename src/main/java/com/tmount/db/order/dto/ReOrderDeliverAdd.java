package com.tmount.db.order.dto;

import java.util.Date;

public class ReOrderDeliverAdd {
    private Long orderNo;

    private Integer logisticsId;

    private Long shopId;

    private Integer stateType;

    private Integer stateCode;

    private String reStateCode;

    private String reStateDesc;

    private Date createTime;

    private Long staffId;
    
    private String sendOnline;
    
    private Integer sendCommand;
    
    public String getSendOnline() {
        return sendOnline;
    }

    public void setSendOnline(String sendOnline) {
        this.sendOnline = sendOnline == null ? null : sendOnline.trim();
    }
    
    public Integer getSendCommand() {
        return sendCommand;
    }

    public void setSendCommand(Integer sendCommand) {
        this.sendCommand = sendCommand;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getStateType() {
        return stateType;
    }

    public void setStateType(Integer stateType) {
        this.stateType = stateType;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getReStateCode() {
        return reStateCode;
    }

    public void setReStateCode(String reStateCode) {
        this.reStateCode = reStateCode == null ? null : reStateCode.trim();
    }

    public String getReStateDesc() {
        return reStateDesc;
    }

    public void setReStateDesc(String reStateDesc) {
        this.reStateDesc = reStateDesc == null ? null : reStateDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}