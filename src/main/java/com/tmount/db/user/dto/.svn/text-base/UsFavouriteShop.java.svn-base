package com.tmount.db.user.dto;

import java.util.Date;

public class UsFavouriteShop extends UsFavouriteShopKey implements Comparable<UsFavouriteShop>{
    private Long userTagId;

    private Date createTime;

    public Long getUserTagId() {
        return userTagId;
    }

    public void setUserTagId(Long userTagId) {
        this.userTagId = userTagId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public int compareTo(UsFavouriteShop o) {
		// TODO Auto-generated method stub
		return this.getCreateTime().compareTo(o.getCreateTime());
	}
}
