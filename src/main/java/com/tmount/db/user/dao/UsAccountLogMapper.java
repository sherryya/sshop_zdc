package com.tmount.db.user.dao;

import com.tmount.db.user.dto.UsAccountLog;
import com.tmount.db.user.dto.UsAccountLogKey;

public interface UsAccountLogMapper {
    int deleteByPrimaryKey(UsAccountLogKey key);

    int insert(UsAccountLog record);

    int insertSelective(UsAccountLog record);

    UsAccountLog selectByPrimaryKey(UsAccountLogKey key);

    int updateByPrimaryKeySelective(UsAccountLog record);

    int updateByPrimaryKey(UsAccountLog record);
}