package com.tmount.db.sys.dto;

import java.util.Date;

public class ReMegSend {
    private Long mesgId;

    private String sendPhone;

    private String mesgContent;

    private String state;

    private Integer staffId;

    private Date createTime;

    public Long getMesgId() {
        return mesgId;
    }

    public void setMesgId(Long mesgId) {
        this.mesgId = mesgId;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone == null ? null : sendPhone.trim();
    }

    public String getMesgContent() {
        return mesgContent;
    }

    public void setMesgContent(String mesgContent) {
        this.mesgContent = mesgContent == null ? null : mesgContent.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}