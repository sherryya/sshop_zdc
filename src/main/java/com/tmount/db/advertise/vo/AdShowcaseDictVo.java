package com.tmount.db.advertise.vo;

import com.tmount.db.advertise.dto.AdShowcaseDict;

public class AdShowcaseDictVo extends AdShowcaseDict {
	private String typeName;
	private String showareaName; 
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getShowareaName() {
		return showareaName;
	}

	public void setShowareaName(String showareaName) {
		this.showareaName = showareaName;
	}
	
}
