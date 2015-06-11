package com.tmount.db.role.dao;

import com.tmount.db.role.dto.ARolefunctionRel;

public interface ARolefunctionRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ARolefunctionRel record);

    int insertSelective(ARolefunctionRel record);

    ARolefunctionRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ARolefunctionRel record);

    int updateByPrimaryKey(ARolefunctionRel record);
    
    int deleteARoleFunctionRelByFunctionCode(String functionCode);
    
    int deleteARoleFunctionRelByRoleCode(Integer roleCode);
}