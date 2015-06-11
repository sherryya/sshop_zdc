package com.tmount.db.advertise.dto;

public class AdShowpageTemplate {
    private Integer showTemplateId;

    private String showTemplateType;

    private Integer showsiteNum;

    private String showTemplateValid;

    private Double showWidth;

    private Double showHigh;

    private Integer showsiteShowType;

    private Integer showareaId;

    public Integer getShowTemplateId() {
        return showTemplateId;
    }

    public void setShowTemplateId(Integer showTemplateId) {
        this.showTemplateId = showTemplateId;
    }

    public String getShowTemplateType() {
        return showTemplateType;
    }

    public void setShowTemplateType(String showTemplateType) {
        this.showTemplateType = showTemplateType == null ? null : showTemplateType.trim();
    }

    public Integer getShowsiteNum() {
        return showsiteNum;
    }

    public void setShowsiteNum(Integer showsiteNum) {
        this.showsiteNum = showsiteNum;
    }

    public String getShowTemplateValid() {
        return showTemplateValid;
    }

    public void setShowTemplateValid(String showTemplateValid) {
        this.showTemplateValid = showTemplateValid == null ? null : showTemplateValid.trim();
    }

    public Double getShowWidth() {
        return showWidth;
    }

    public void setShowWidth(Double showWidth) {
        this.showWidth = showWidth;
    }

    public Double getShowHigh() {
        return showHigh;
    }

    public void setShowHigh(Double showHigh) {
        this.showHigh = showHigh;
    }

    public Integer getShowsiteShowType() {
        return showsiteShowType;
    }

    public void setShowsiteShowType(Integer showsiteShowType) {
        this.showsiteShowType = showsiteShowType;
    }

    public Integer getShowareaId() {
        return showareaId;
    }

    public void setShowareaId(Integer showareaId) {
        this.showareaId = showareaId;
    }
}