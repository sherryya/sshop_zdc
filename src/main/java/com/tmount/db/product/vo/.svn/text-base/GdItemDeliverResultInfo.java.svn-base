package com.tmount.db.product.vo;


public class GdItemDeliverResultInfo {

	private Integer deliverType;
	
	private String deliverName;
	
	private Double deliverFee;
	
	public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName == null ? null : deliverName.trim();
    }
    
    public Integer getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
    }
	
	public Double getDeliverFee() {
        return deliverFee;
    }

    public void setDeliverFee(Double deliverFee) {
    	java.text.NumberFormat   formate   =   java.text.NumberFormat.getNumberInstance();
		formate.setMaximumFractionDigits(2);//设定小数最大为数   ，那么显示的最后会四舍五入的 
        this.deliverFee = new Double(formate.format(deliverFee));
    }
	
}
