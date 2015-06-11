package com.tmount.business.express.vo;

import java.util.Date;
import java.util.List;

import com.tmount.db.order.dto.ReOrderDeliver;
import com.tmount.db.order.vo.ReUserOrderDetailInfo;

public class OrderInfoVo {
	private String clientID;
	private String logisticProviderID;
	private String customerId;
	private String txLogisticID;
	private String tradeNo;
	private String mailNo;
	private int Type;
	private int orderType;
	private long serviceType;
	private int flag;
	private Date sendStartTime;
	private Date sendEndTime;
	private double goodsValue;
	private double itemsValue;
	private double insuranceValue;
	private int special;
	private String remark;
	private ReOrderDeliver sender;
	private ReOrderDeliver receiver;
	private List<ReUserOrderDetailInfo> items;
	private double totalServiceFee;
	private double codSplitFee;
	
	public double getCodSplitFee() {
		return codSplitFee;
	}
	public void setCodSplitFee(double codSplitFee) {
		this.codSplitFee = codSplitFee;
	}
	public double getTotalServiceFee() {
		return totalServiceFee;
	}
	public void setTotalServiceFee(double totalServiceFee) {
		this.totalServiceFee = totalServiceFee;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getLogisticProviderID() {
		return logisticProviderID;
	}
	public void setLogisticProviderID(String logisticProviderID) {
		this.logisticProviderID = logisticProviderID;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getTxLogisticID() {
		return txLogisticID;
	}
	public void setTxLogisticID(String txLogisticID) {
		this.txLogisticID = txLogisticID;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getMailNo() {
		return mailNo;
	}
	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public long getServiceType() {
		return serviceType;
	}
	public void setServiceType(long serviceType) {
		this.serviceType = serviceType;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Date getSendStartTime() {
		return sendStartTime;
	}
	public void setSendStartTime(Date sendStartTime) {
		this.sendStartTime = sendStartTime;
	}
	public Date getSendEndTime() {
		return sendEndTime;
	}
	public void setSendEndTime(Date sendEndTime) {
		this.sendEndTime = sendEndTime;
	}
	public double getGoodsValue() {
		return goodsValue;
	}
	public void setGoodsValue(double goodsValue) {
		this.goodsValue = goodsValue;
	}
	public double getItemsValue() {
		return itemsValue;
	}
	public void setItemsValue(double itemsValue) {
		this.itemsValue = itemsValue;
	}
	public double getInsuranceValue() {
		return insuranceValue;
	}
	public void setInsuranceValue(double insuranceValue) {
		this.insuranceValue = insuranceValue;
	}
	public int getSpecial() {
		return special;
	}
	public void setSpecial(int special) {
		this.special = special;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public ReOrderDeliver getSender() {
		return sender;
	}
	public void setSender(ReOrderDeliver sender) {
		this.sender = sender;
	}
	public ReOrderDeliver getReceiver() {
		return receiver;
	}
	public void setReceiver(ReOrderDeliver receiver) {
		this.receiver = receiver;
	}
	public List<ReUserOrderDetailInfo> getItems() {
		return items;
	}
	public void setItems(List<ReUserOrderDetailInfo> items) {
		this.items = items;
	}
	
}
