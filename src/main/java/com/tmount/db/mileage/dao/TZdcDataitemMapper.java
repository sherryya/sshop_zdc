package com.tmount.db.mileage.dao;

import com.tmount.db.mileage.dto.TZdcDataitem;

public interface TZdcDataitemMapper {
	/**
	 * 根据字典名称查询字典值
	 * @param itemId
	 * @return
	 */
	TZdcDataitem selectByPrimaryKey(String itemId);

}
