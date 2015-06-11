package com.tmount.db.user.dao;

import java.util.List;

import com.tmount.db.user.dto.UsInvoiceCfg;
import com.tmount.db.user.dto.UsInvoiceCfgKey;

public interface UsInvoiceCfgMapper {
    int deleteByPrimaryKey(UsInvoiceCfgKey key);

    int insert(UsInvoiceCfg record);

    int insertSelective(UsInvoiceCfg record);

    UsInvoiceCfg selectByPrimaryKey(UsInvoiceCfgKey key);

    int updateByPrimaryKeySelective(UsInvoiceCfg record);

    int updateByPrimaryKey(UsInvoiceCfg record);

	List<UsInvoiceCfg> selectByUserId(Long userId);

	Integer selectUserInvoiceOrders(Long userId);
	
	
}