package com.tmount.db.product.dto;

import java.util.Date;

public class GdShopInfo {
    private Long shopId;

    private Integer cityCode;

    private Integer showType;

    private Integer companyId;

    private String shopName;

    private String nameSpr;

    private String shopIntro;

    private Double optionX;

    private Double optionY;

    private Long scanPic;

    private Long bigPic;

    private String httpUrl;

    private String webUrl;

    private Date updateTime;

    private String state;

    private Integer updateNo;

    private Date createTime;

    private Integer picSpace;

    private Integer shopLevel;

    private Integer levelGood;

    private Integer levelNomer;

    private Integer levelBad;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getNameSpr() {
        return nameSpr;
    }

    public void setNameSpr(String nameSpr) {
        this.nameSpr = nameSpr == null ? null : nameSpr.trim();
    }

    public String getShopIntro() {
        return shopIntro;
    }

    public void setShopIntro(String shopIntro) {
        this.shopIntro = shopIntro == null ? null : shopIntro.trim();
    }

    public Double getOptionX() {
        return optionX;
    }

    public void setOptionX(Double optionX) {
        this.optionX = optionX;
    }

    public Double getOptionY() {
        return optionY;
    }

    public void setOptionY(Double optionY) {
        this.optionY = optionY;
    }

    public Long getScanPic() {
        return scanPic;
    }

    public void setScanPic(Long scanPic) {
        this.scanPic = scanPic;
    }

    public Long getBigPic() {
        return bigPic;
    }

    public void setBigPic(Long bigPic) {
        this.bigPic = bigPic;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl == null ? null : httpUrl.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(Integer updateNo) {
        this.updateNo = updateNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPicSpace() {
        return picSpace;
    }

    public void setPicSpace(Integer picSpace) {
        this.picSpace = picSpace;
    }

    public Integer getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(Integer shopLevel) {
        this.shopLevel = shopLevel==null?0:shopLevel;
    }

    public Integer getLevelGood() {
        return levelGood;
    }

    public void setLevelGood(Integer levelGood) {
        this.levelGood = levelGood==null?0:levelGood;
    }

    public Integer getLevelNomer() {
        return levelNomer;
    }

    public void setLevelNomer(Integer levelNomer) {
        this.levelNomer = levelNomer==null?0:levelNomer;
    }

    public Integer getLevelBad() {
        return levelBad;
    }

    public void setLevelBad(Integer levelBad) {
        this.levelBad = levelBad==null?0:levelBad;
    }
}