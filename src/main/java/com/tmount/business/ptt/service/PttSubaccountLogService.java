package com.tmount.business.ptt.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmount.db.ptt.dao.PttSubaccountLogMapper;
import com.tmount.db.ptt.dto.TItovPttSubaccountLog;
@Service
public class PttSubaccountLogService {
	@Autowired
	PttSubaccountLogMapper pttSubaccountLogMapper;
	public void updateByPrimaryKeySelective(TItovPttSubaccountLog tItovPttSubaccountLog) {
		pttSubaccountLogMapper.updateByPrimaryKeySelective(tItovPttSubaccountLog);
	}
	public void insert(TItovPttSubaccountLog tItovPttSubaccountLog) {
		pttSubaccountLogMapper.insert(tItovPttSubaccountLog);
	}
	public List<TItovPttSubaccountLog> selectByAccountId(int value1,
			int value2, int value3) {
		return	pttSubaccountLogMapper.selectRadiobyAccount(value1, value2, value3);
	}
}
