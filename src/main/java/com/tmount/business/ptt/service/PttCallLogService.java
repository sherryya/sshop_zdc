package com.tmount.business.ptt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.ptt.dao.PttCallLogMapper;
import com.tmount.db.ptt.dto.TItovPttCallLog;

@Service
public class PttCallLogService {
	@Autowired
	private PttCallLogMapper pttCallLogMapper;

	/**
	 * 
	 * @param userAccount
	 * @return
	 */
	public void insert(TItovPttCallLog tItovPttCallLog){
		pttCallLogMapper.insert(tItovPttCallLog);
	}
	
	public TItovPttCallLog selectByCallId(TItovPttCallLog tItovPttCallLog){
		return pttCallLogMapper.selectByCallId(tItovPttCallLog);
	}
	
	public void update(TItovPttCallLog tItovPttCallLog){
		 pttCallLogMapper.update(tItovPttCallLog);
	}		 	
	

	public int selectCountByCallFrom(TItovPttCallLog tItovPttCallLog)
	{
		return pttCallLogMapper.selectCountByCallFrom(tItovPttCallLog);
	}
	public List<TItovPttCallLog> selectListByCallFrom(TItovPttCallLog tItovPttCallLog)
	{
		return pttCallLogMapper.selectListByCallFrom( tItovPttCallLog);
	}
}
