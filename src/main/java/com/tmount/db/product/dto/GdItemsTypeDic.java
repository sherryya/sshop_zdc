package com.tmount.db.product.dto;

import java.util.Date;
import java.util.List;

public class GdItemsTypeDic {
    private Integer itemsType;

    private Integer companyId;

    private Integer orders;

    private Integer parentId;

    private Integer levelNo;

    private Integer showType;

    private Integer dataType;

    private String typeName;

    private String typeExp;

    private String typeDesc;

    private Long smallPic;

    private Long bigPic;

    private Date updateTime;

    private String state;

    private Integer updateNo;

    private Integer itemsCount;

    private String itemsTag;
    
    private List<GdItemsTypeDic> childItemsType;

    public Integer getItemsType() {
        return itemsType;
    }

    public void setItemsType(Integer itemsType) {
        this.itemsType = itemsType;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeExp() {
        return typeExp;
    }

    public void setTypeExp(String typeExp) {
        this.typeExp = typeExp == null ? null : typeExp.trim();
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc == null ? null : typeDesc.trim();
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

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public String getItemsTag() {
        return itemsTag;
    }

    public void setItemsTag(String itemsTag) {
        this.itemsTag = itemsTag == null ? null : itemsTag.trim();
    }

	public List<GdItemsTypeDic> getChildItemsType() {
		return childItemsType;
	}

	public void setChildItemsType(List<GdItemsTypeDic> childItemsType) {
		this.childItemsType = childItemsType;
	}
}