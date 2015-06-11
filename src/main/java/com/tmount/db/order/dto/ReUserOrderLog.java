package com.tmount.db.order.dto;

import java.util.Date;

public class ReUserOrderLog extends ReUserOrderLogKey {
    private Integer operId;

    private Integer oldState;

    private Integer newState;

    private String dealMark;

    private String sysMark;

    private Date dealTime;

    private Integer dealType;

    private Integer dealNo;

    private String dealName;

    public Integer getOperId() {
        return operId;
    }

    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    public Integer getOldState() {
        return oldState;
    }

    public void setOldState(Integer oldState) {
        this.oldState = oldState;
    }

    public Integer getNewState() {
        return newState;
    }

    public void setNewState(Integer newState) {
        this.newState = newState;
    }

    public String getDealMark() {
        return dealMark;
    }

    public void setDealMark(String dealMark) {
        this.dealMark = dealMark == null ? null : dealMark.trim();
    }

    public String getSysMark() {
        return sysMark;
    }

    public void setSysMark(String sysMark) {
        this.sysMark = sysMark == null ? null : sysMark.trim();
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getDealType() {
        return dealType;
    }

    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }

    public Integer getDealNo() {
        return dealNo;
    }

    public void setDealNo(Integer dealNo) {
        this.dealNo = dealNo;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName == null ? null : dealName.trim();
    }
}