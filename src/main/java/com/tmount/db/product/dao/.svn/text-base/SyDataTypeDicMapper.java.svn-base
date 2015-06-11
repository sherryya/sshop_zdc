package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.SyDataTypeDic;
import com.tmount.db.product.dto.SyDataTypeDicKey;

public interface SyDataTypeDicMapper {
	/**
	 * 通过数据类型的用途得到可用数据类型列表
	 * @param syDataTypeDic
	 * @return
	 */
    List<SyDataTypeDic> selectByUseType(SyDataTypeDic syDataTypeDic);
	
    int deleteByPrimaryKey(SyDataTypeDicKey key);

    int insert(SyDataTypeDic record);

    int insertSelective(SyDataTypeDic record);

    SyDataTypeDic selectByPrimaryKey(SyDataTypeDicKey key);

    int updateByPrimaryKeySelective(SyDataTypeDic record);

    int updateByPrimaryKey(SyDataTypeDic record);
}