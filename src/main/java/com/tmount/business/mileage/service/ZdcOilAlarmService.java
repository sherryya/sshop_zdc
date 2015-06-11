package com.tmount.business.mileage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.mileage.dao.TZdcOilAlarmMapper;
import com.tmount.db.mileage.dto.TZdcOilAlarm;

@Service
public class ZdcOilAlarmService {
	
	@Autowired
	private TZdcOilAlarmMapper oilAlarmMapper;
	
	/**
	 * 当推送消息后点击是或否改变状态
	 * @param tzdcOil
	 * @return
	 */
	public int updateByPrimaryKeySelective(TZdcOilAlarm tzdcOil)
	{
		return oilAlarmMapper.updateByPrimaryKeySelective(tzdcOil);
	}
	

}
