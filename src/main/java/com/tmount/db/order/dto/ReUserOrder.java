package com.tmount.db.order.dto;

import java.util.Date;

import com.tmount.business.util.format.DecimalFormatUtil;

public class ReUserOrder {
    private Long orderNo;

    private Long userId;

    private Long loginAccept;

    private Long shopId;

    private Integer takeMode;

    private Integer orderMode;

    private Integer payWayId;

    private Integer acount;

    private String orderValid;

    private String orderEnd;

    private Integer state;

    private Date createDate;

    private Date feeDate;

    private Date endTime;

    private Integer secretBuy;

    private String activity;

    private Double price;

    private String finalPayStatus;

    private String payStatus;

    private Double freightFee;

    private Double orderMoney;

    private Double payMoney;

    private Integer userLevel;

    private Date orderTime;

    private Date updateTime;

    private Integer updateNo;
    
    private Double favourFee;
    
    private String companyName;
    
    private String shopName;
    
    private String userAccount;
    
    private String confirmCode;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getLoginAccept() {
		return loginAccept;
	}

	public void setLoginAccept(Long loginAccept) {
		this.loginAccept = loginAccept;
	}

	public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getTakeMode() {
        return takeMode;
    }

    public void setTakeMode(Integer takeMode) {
        this.takeMode = takeMode;
    }

    public Integer getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(Integer orderMode) {
        this.orderMode = orderMode;
    }

    public Integer getPayWayId() {
        return payWayId;
    }

    public void setPayWayId(Integer payWayId) {
        this.payWayId = payWayId;
    }

    public Integer getAcount() {
        return acount;
    }

    public void setAcount(Integer acount) {
        this.acount = acount;
    }

    public String getOrderValid() {
        return orderValid;
    }

    public void setOrderValid(String orderValid) {
        this.orderValid = orderValid == null ? null : orderValid.trim();
    }

    public String getOrderEnd() {
        return orderEnd;
    }

    public void setOrderEnd(String orderEnd) {
        this.orderEnd = orderEnd == null ? null : orderEnd.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFeeDate() {
        return feeDate;
    }

    public void setFeeDate(Date feeDate) {
        this.feeDate = feeDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSecretBuy() {
        return secretBuy;
    }

    public void setSecretBuy(Integer secretBuy) {
        this.secretBuy = secretBuy;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity == null ? null : activity.trim();
    }

    public Double getPrice() {
    	if(price!=null)
    	price = Double.parseDouble(DecimalFormatUtil.decimal2String(price));
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFinalPayStatus() {
        return finalPayStatus;
    }

    public void setFinalPayStatus(String finalPayStatus) {
        this.finalPayStatus = finalPayStatus == null ? null : finalPayStatus.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public Double getFreightFee() {
    	if(freightFee!=null)
    	freightFee = Double.parseDouble(DecimalFormatUtil.decimal2String(freightFee));
        return freightFee;
    }

    public void setFreightFee(Double freightFee) {
        this.freightFee = freightFee;
    }

    public Double getOrderMoney() {
    	if(orderMoney!=null)
    		orderMoney = Double.parseDouble(DecimalFormatUtil.decimal2String(orderMoney));
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Double getPayMoney() {
    	if(payMoney!=null)
    		payMoney = Double.parseDouble(DecimalFormatUtil.decimal2String(payMoney));
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Double getFavourFee() {
		if(favourFee!=null)
			favourFee = Double.parseDouble(DecimalFormatUtil.decimal2String(favourFee));
		return favourFee;
	}

	public void setFavourFee(Double favourFee) {
		this.favourFee = favourFee;
	}

	public String getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
    
}