package com.tmount.db.sys.dao;

import com.tmount.db.sys.dto.SysAmountDic;

public interface SysAmountDicMapper {
    int deleteByPrimaryKey(String amountCode);

    int insert(SysAmountDic record);

    int insertSelective(SysAmountDic record);

    SysAmountDic selectByPrimaryKey(String amountCode);

    int updateByPrimaryKeySelective(SysAmountDic record);

    int updateByPrimaryKey(SysAmountDic record);
}