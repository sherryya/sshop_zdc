package com.tmount.db.sys.dao;

import com.tmount.db.sys.dto.SyErrLog;

public interface SyErrLogMapper {
    int deleteByPrimaryKey(Long loginAccept);

    int insert(SyErrLog record);

    int insertSelective(SyErrLog record);

    SyErrLog selectByPrimaryKey(Long loginAccept);

    int updateByPrimaryKeySelective(SyErrLog record);

    int updateByPrimaryKey(SyErrLog record);
}