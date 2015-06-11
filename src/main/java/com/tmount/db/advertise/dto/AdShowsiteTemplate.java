package com.tmount.db.advertise.dto;

public class AdShowsiteTemplate {
    private Integer showsiteCode;

    private Integer showTemplateId;

    private Double showLineAxis;

    private Double showRowAxis;

    private Double showsiteWidth;

    private Double showsiteHigh;

    private Double showsitePicWidth;

    private Double showsitePicHigh;

    private String showsiteCombineDef;

    private Integer showsiteShowType;

    public Integer getShowsiteCode() {
        return showsiteCode;
    }

    public void setShowsiteCode(Integer showsiteCode) {
        this.showsiteCode = showsiteCode;
    }

    public Integer getShowTemplateId() {
        return showTemplateId;
    }

    public void setShowTemplateId(Integer showTemplateId) {
        this.showTemplateId = showTemplateId;
    }

    public Double getShowLineAxis() {
        return showLineAxis;
    }

    public void setShowLineAxis(Double showLineAxis) {
        this.showLineAxis = showLineAxis;
    }

    public Double getShowRowAxis() {
        return showRowAxis;
    }

    public void setShowRowAxis(Double showRowAxis) {
        this.showRowAxis = showRowAxis;
    }

    public Double getShowsiteWidth() {
        return showsiteWidth;
    }

    public void setShowsiteWidth(Double showsiteWidth) {
        this.showsiteWidth = showsiteWidth;
    }

    public Double getShowsiteHigh() {
        return showsiteHigh;
    }

    public void setShowsiteHigh(Double showsiteHigh) {
        this.showsiteHigh = showsiteHigh;
    }

    public Double getShowsitePicWidth() {
        return showsitePicWidth;
    }

    public void setShowsitePicWidth(Double showsitePicWidth) {
        this.showsitePicWidth = showsitePicWidth;
    }

    public Double getShowsitePicHigh() {
        return showsitePicHigh;
    }

    public void setShowsitePicHigh(Double showsitePicHigh) {
        this.showsitePicHigh = showsitePicHigh;
    }

    public String getShowsiteCombineDef() {
        return showsiteCombineDef;
    }

    public void setShowsiteCombineDef(String showsiteCombineDef) {
        this.showsiteCombineDef = showsiteCombineDef == null ? null : showsiteCombineDef.trim();
    }

    public Integer getShowsiteShowType() {
        return showsiteShowType;
    }

    public void setShowsiteShowType(Integer showsiteShowType) {
        this.showsiteShowType = showsiteShowType;
    }
}