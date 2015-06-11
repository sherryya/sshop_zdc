package com.tmount.db.product.vo;

import java.util.List;

public class SyCityInfo {

	private Integer cityCode;

    private String cityName;

    private Integer parentCode;
    
    private List<SyCityInfo> childCity;
    
    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }
    
    public List<SyCityInfo> getChildCity() {
		return childCity;
	}

	public void setChildCity(List<SyCityInfo> childCity) {
		this.childCity = childCity;
	}
}
