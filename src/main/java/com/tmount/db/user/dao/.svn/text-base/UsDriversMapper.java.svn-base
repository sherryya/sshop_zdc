package com.tmount.db.user.dao;

import com.tmount.db.user.dto.UsDriversInfo;
import com.tmount.db.user.dto.UsDriversSign;

public interface UsDriversMapper {
	/**
	 * 驾驶员签到 签退
	 * 
	 * @param record
	 * @return
	 */
	int driversSign_Insert(UsDriversSign record);// 驾驶员签到信息
   /**
    * 判断人车是否对应
    * @param record
    * @return
    */
	UsDriversInfo getDriversIsExists(UsDriversSign record);
	public Integer getCommonTableID(String id);
	public UsDriversSign getDriversSignTime(UsDriversSign usDriversSign);
}
