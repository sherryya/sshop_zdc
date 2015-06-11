package com.tmount.db.product.vo;

import java.util.List;

import com.tmount.db.product.dto.SyServDic;


public class GdItemListInfo {
	private Long itemsId;

    private Long shopId;

    private Integer itemType;

    private String itemsName;

    private Long smallPic;

    private Integer itemsCount;

    private Double price;

    private Double discount;

    private Double memberPrice;

    private Integer discuss30Count;

    private Integer collectCout;
    
    private String deliverFlag;
    
    private Integer cityCode;
    
    private String cityName;

    private Integer provinceCode;
    
    private String provinceName;
    
    private Integer showType;

    private Integer dataType;
    
    private String httpUrl;
    
    private String hasChild;
    
    private String nameSpr;

    private String itemsIntro;
    
    private Integer discussCount;
    
    private Integer commentValue;
    
    public Integer getDiscussCount() {
		return discussCount;
	}

	public void setDiscussCount(Integer discussCount) {
		this.discussCount = discussCount;
	}

	public Integer getCommentValue() {
		return commentValue;
	}

	public void setCommentValue(Integer commentValue) {
		this.commentValue = commentValue;
	}

	/*商家信息*/
    private Integer shopLevel;
    
    private String shopName;
    
    /*商家保障信息*/
    List<SyServDic> syServDicList;
    
    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName == null ? null : itemsName.trim();
    }

    public Long getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(Long smallPic) {
        this.smallPic = smallPic;
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

    public Integer getDiscuss30Count() {
        return discuss30Count;
    }

    public void setDiscuss30Count(Integer discuss30Count) {
        this.discuss30Count = discuss30Count;
    }

    public Integer getCollectCout() {
        return collectCout;
    }

    public void setCollectCout(Integer collectCout) {
        this.collectCout = collectCout;
    }
    
    public String getDeliverFlag() {
        return deliverFlag;
    }

    public void setDeliverFlag(String deliverFlag) {
        this.deliverFlag = deliverFlag == null ? null : deliverFlag.trim();
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
    
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }
    
    public Integer getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(Integer shopLevel) {
        this.shopLevel = shopLevel;
    }
    
    public List<SyServDic> getSyServDicList() {
        return syServDicList;
    }

    public void setSyServDicList(List<SyServDic> syServDicList) {
        this.syServDicList = syServDicList;
    }
    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
    
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }
    
    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
    
    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild == null ? null : hasChild.trim();
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
    
    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl == null ? null : httpUrl.trim();
    }
    
}
