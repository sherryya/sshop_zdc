package com.tmount.business.mileage.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.mileage.dao.ZdcFaultOriginalMapper;
import com.tmount.db.mileage.dto.TZdcFaultCodeLog;
import com.tmount.db.mileage.dto.TZdcFaultOriginal;
@Service
public class ZdcFaultOriginalService{
	@Autowired
	private ZdcFaultOriginalMapper  zdcFaultOriginalMapper;
	public void insert(TZdcFaultOriginal tZdcFaultOriginal)
	{
		zdcFaultOriginalMapper.insert(tZdcFaultOriginal);
	}
	public void insert_fault_log(TZdcFaultCodeLog tZdcFaultCodeLog)
	{
		zdcFaultOriginalMapper.insert_fault_log(tZdcFaultCodeLog);
	}
	public List<TZdcFaultCodeLog> selectFaultCode(TZdcFaultCodeLog tZdcFaultCodeLog)
	{
	return	zdcFaultOriginalMapper.selectFaultCode(tZdcFaultCodeLog);
	}
	public 	void updateByPrimaryKey(TZdcFaultCodeLog tZdcFaultCodeLog)
	{
		zdcFaultOriginalMapper.updateByPrimaryKey(tZdcFaultCodeLog);
	}
	public 	TZdcFaultCodeLog selectByInto(TZdcFaultCodeLog tZdcFaultCodeLog)
	{
		return zdcFaultOriginalMapper.selectByInto(tZdcFaultCodeLog);
	}
}
