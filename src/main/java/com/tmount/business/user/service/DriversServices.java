package com.tmount.business.user.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.user.dao.UsDriversMapper;
import com.tmount.db.user.dto.UsDriversInfo;
import com.tmount.db.user.dto.UsDriversSign;
@Service
public class DriversServices {
	@Autowired
	private UsDriversMapper usDriversMapper;
	/**
	 * 驾驶员签到 签退
	 * @param usDriversSign
	 */
	public void insertUsDriversMapper(UsDriversSign usDriversSign){
		usDriversMapper.driversSign_Insert(usDriversSign);
	}
    /**
     * 判断人车是否对应
     * @param usDriversSign
     * @return
     */
	public UsDriversInfo getDriversIsExists(UsDriversSign usDriversSign)
	{
		return usDriversMapper.getDriversIsExists(usDriversSign);
	}
	public Integer getCommonTableID(String id)
	{
		return usDriversMapper.getCommonTableID(id);
	}
	public UsDriversSign getDriversSignTime(UsDriversSign usDriversSign)
	{
		return usDriversMapper.getDriversSignTime(usDriversSign);
	}
}

