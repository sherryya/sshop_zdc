package com.tmount.db.staff.dao;

import com.tmount.db.staff.dto.LogLoginAccept;

public interface LogLoginAcceptMapper {
    int deleteByPrimaryKey(Long loginAccept);

    int insert(LogLoginAccept record);

    int insertSelective(LogLoginAccept record);

    LogLoginAccept selectByPrimaryKey(Long loginAccept);

    int updateByPrimaryKeySelective(LogLoginAccept record);

    int updateByPrimaryKey(LogLoginAccept record);
}