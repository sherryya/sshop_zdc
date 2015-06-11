package com.tmount.business.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.car.dao.WarningMessageMapper;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.DrivingField;
import com.tmount.db.car.dto.WarningMessage;

@Service
public class WarningMessageService {
	@Autowired
	private WarningMessageMapper warningMessageMapper;
	
	
	public void insertWarningMessage(CarInfo carinfo,DrivingField drivingFieldId){
		WarningMessage warningMessage = new WarningMessage();
		warningMessage.setCarId(carinfo.getId());
		warningMessage.setWarntype(drivingFieldId.getType());
		warningMessage.setContent(carinfo.getCarnum()+carinfo.getCarname()+"触发围栏告警,告警类型"+drivingFieldId.getType());
		warningMessageMapper.insertWarningMessage(warningMessage);
		
	}

}
