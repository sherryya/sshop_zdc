package com.tmount.db.advertise.dto;

import java.util.Date;

public class AdShowcaseInst extends AdShowcaseInstKey {
    private String showContent;

    private Date updateTime;

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent == null ? null : showContent.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}