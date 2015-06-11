package com.tmount.business.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.car.dao.DrivingFieldMapper;
import com.tmount.db.car.dto.DrivingField;

@Service
public class DrivingFieldService {
	@Autowired
	private DrivingFieldMapper drivingFieldMapper;

	
	
	/**
	 * 获取用户余额信息
	 * @param userid
	 * @return
	 */
	public List<DrivingField> getWarning(){
		return drivingFieldMapper.getWarning();
	}

}
