package com.tmount.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmount.db.ptt.dao.PttSubaccountLogMapper;
import com.tmount.db.ptt.dto.TItovPttSubaccountLog;
@Service
public class PttSubLogService {
	@Autowired
	PttSubaccountLogMapper pttSubaccountLogMapper;
	public void updateByPrimaryKeySelective(TItovPttSubaccountLog tItovPttSubaccountLog) {
		pttSubaccountLogMapper.updateByPrimaryKeySelective(tItovPttSubaccountLog);
	}
	public int  insert(TItovPttSubaccountLog tItovPttSubaccountLog) {
		return pttSubaccountLogMapper.insert(tItovPttSubaccountLog);
	}
	public List<TItovPttSubaccountLog> selectByAccountId(int value1,
			int value2, int value3) {
		return	pttSubaccountLogMapper.selectRadiobyAccount(value1, value2, value3);
	}
	public int  insertAll(TItovPttSubaccountLog tItovPttSubaccountLog) {
		return pttSubaccountLogMapper.insertAll(tItovPttSubaccountLog);
	}
}
