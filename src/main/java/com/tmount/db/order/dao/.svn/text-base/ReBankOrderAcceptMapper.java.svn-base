package com.tmount.db.order.dao;

import java.util.List;

import com.tmount.db.order.dto.ReBankOrderAccept;
import com.tmount.db.order.dto.ReBankOrderAcceptKey;

public interface ReBankOrderAcceptMapper {
    int deleteByPrimaryKey(ReBankOrderAcceptKey key);

    int insert(ReBankOrderAccept record);

    int insertSelective(ReBankOrderAccept record);

    ReBankOrderAccept selectByPrimaryKey(ReBankOrderAcceptKey key);

    int updateByPrimaryKeySelective(ReBankOrderAccept record);

    int updateByPrimaryKey(ReBankOrderAccept record);
	
	int countPayStatusByBankAccept(long bankAccept);
    
    List<ReBankOrderAccept> selectByBankAccept(long bankAccept);
    
    int deleteByBankAndOrder(ReBankOrderAccept reBankOrderAccept);
}