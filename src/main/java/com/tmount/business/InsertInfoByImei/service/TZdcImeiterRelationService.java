package com.tmount.business.InsertInfoByImei.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.terminalAccount.dao.TZdcDevicesMapper;
import com.tmount.db.terminalAccount.dao.TZdcImeiterRelationMapper;
import com.tmount.db.terminalAccount.dto.TZdcDevices;
import com.tmount.db.terminalAccount.dto.TZdcImeiterRelation;
@Service
public class TZdcImeiterRelationService {	
	@Autowired
	private TZdcImeiterRelationMapper tZdcImeiterRelationMapper;
	
	@Autowired
	private TZdcDevicesMapper tZdcDevicesMapper;
	
    public int insert(TZdcImeiterRelation tZdcImeiterRelation)
    {
    	return tZdcImeiterRelationMapper.insert(tZdcImeiterRelation);
    }
    public List<TZdcImeiterRelation> selectByIMEI(String imei)
    {
    	return tZdcImeiterRelationMapper.selectByIMEI(imei);
    }
    
    public List<TZdcDevices> selectByDeviceId(String deviceId)
    {
    	return tZdcDevicesMapper.selectByDeviceId(deviceId);
    }
    
    public int updateByTerID(String terminal)
    {
    	return tZdcImeiterRelationMapper.updateByTerID(terminal);
    }
    
    public 	int deleteByPrimaryKey(TZdcImeiterRelation tZdcImeiterRelation)
    {
    	return tZdcImeiterRelationMapper.deleteByPrimaryKey(tZdcImeiterRelation);
    }
    
    public List<TZdcImeiterRelation>  selectByTerminal(String terminal)
    {
    	return tZdcImeiterRelationMapper.selectByTerminal(terminal);
    }
    
}
