package com.tmount.db.ptt.dao;

import java.util.List;

import com.tmount.db.ptt.dto.TItovPttSubaccountLog;

public interface PttSubaccountLogMapper {

	void updateByPrimaryKeySelective(TItovPttSubaccountLog tItovPttSubaccountLog);

	int insert(TItovPttSubaccountLog tItovPttSubaccountLog);

	List<TItovPttSubaccountLog> selectRadiobyAccount(int value1, int value2, int value3);
	int insertAll(TItovPttSubaccountLog tItovPttSubaccountLog);

}