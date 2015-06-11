package com.tmount.db.order.vo;

import java.util.Date;

import com.tmount.db.order.dto.ReUserOrderDetail;

public class ReUserOrderDetailInfo extends ReUserOrderDetail {
    private Long shopId;

    private Integer showType;

    private Integer newtypeType;

    private Integer itemType;

    private String hasChild;

    private String itemsName;

    private String nameSpr;

    private String itemsIntro;

    private Long smallPic;

    private Long bigPic;

    private String httpUrl;

    private String webUrl;

    private Integer itemsCount;

    private Double price;

    private String aunit;

    private Double discount;

    private Double memberPrice;

    private String state;

    private Integer saleCount;

    private Integer discuss30Count;

    private Integer discussCount;

    private Integer collectCout;

    private String barCode;

    private String serchUp;

    private Integer creditPercent;

    private Integer credit;

    private String deliverFlag;

    private String outCode;

    private Long productId;

    private Integer orderValue;

    private String invoiceFlag;

    private String backOrderFlag;

    private Date updateTime;

    private Integer updateNo;

    private Integer cityCode;

    private Integer provinceCode;

    private Integer commentValue;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getNewtypeType() {
        return newtypeType;
    }

    public void setNewtypeType(Integer newtypeType) {
        this.newtypeType = newtypeType;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild == null ? null : hasChild.trim();
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName == null ? null : itemsName.trim();
    }

    public String getNameSpr() {
        return nameSpr;
    }

    public void setNameSpr(String nameSpr) {
        this.nameSpr = nameSpr == null ? null : nameSpr.trim();
    }

    public String getItemsIntro() {
        return itemsIntro;
    }

    public void setItemsIntro(String itemsIntro) {
        this.itemsIntro = itemsIntro == null ? null : itemsIntro.trim();
    }

    public Long getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(Long smallPic) {
        this.smallPic = smallPic;
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

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAunit() {
        return aunit;
    }

    public void setAunit(String aunit) {
        this.aunit = aunit == null ? null : aunit.trim();
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getDiscuss30Count() {
        return discuss30Count;
    }

    public void setDiscuss30Count(Integer discuss30Count) {
        this.discuss30Count = discuss30Count;
    }

    public Integer getDiscussCount() {
        return discussCount;
    }

    public void setDiscussCount(Integer discussCount) {
        this.discussCount = discussCount;
    }

    public Integer getCollectCout() {
        return collectCout;
    }

    public void setCollectCout(Integer collectCout) {
        this.collectCout = collectCout;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getSerchUp() {
        return serchUp;
    }

    public void setSerchUp(String serchUp) {
        this.serchUp = serchUp == null ? null : serchUp.trim();
    }

    public Integer getCreditPercent() {
        return creditPercent;
    }

    public void setCreditPercent(Integer creditPercent) {
        this.creditPercent = creditPercent;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getDeliverFlag() {
        return deliverFlag;
    }

    public void setDeliverFlag(String deliverFlag) {
        this.deliverFlag = deliverFlag == null ? null : deliverFlag.trim();
    }

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode == null ? null : outCode.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    public String getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
        this.invoiceFlag = invoiceFlag == null ? null : invoiceFlag.trim();
    }

    public String getBackOrderFlag() {
        return backOrderFlag;
    }

    public void setBackOrderFlag(String backOrderFlag) {
        this.backOrderFlag = backOrderFlag == null ? null : backOrderFlag.trim();
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

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

	public Integer getCommentValue() {
		return commentValue;
	}

	public void setCommentValue(Integer commentValue) {
		this.commentValue = commentValue;
	}
}
