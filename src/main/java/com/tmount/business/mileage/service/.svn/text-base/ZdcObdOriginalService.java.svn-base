package com.tmount.business.mileage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.mileage.dao.TZdcObdOriginalMapper;
import com.tmount.db.mileage.dto.TZdcObdOriginal;

@Service
public class ZdcObdOriginalService{
	@Autowired
	private TZdcObdOriginalMapper  zdcObdOriginalMapper;
	
	/**
	 * 插入obd原始数据
	 * @param tZdcObd
	 * @return
	 */
	public int insert(TZdcObdOriginal tZdcObd)
	{
		return zdcObdOriginalMapper.insert(tZdcObd);
	}
	
}
