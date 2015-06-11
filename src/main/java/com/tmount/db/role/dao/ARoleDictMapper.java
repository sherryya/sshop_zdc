package com.tmount.db.role.dao;

import java.util.List;

import com.tmount.db.role.dto.ARoleDict;

public interface ARoleDictMapper {
    int deleteByPrimaryKey(Integer roleCode);

    int insert(ARoleDict record);

    int insertSelective(ARoleDict record);

    ARoleDict selectByPrimaryKey(Integer roleCode);

    void updateByPrimaryKeySelective(ARoleDict record);

    int updateByPrimaryKey(ARoleDict record);
    
    int queryCountByRoleCode(Integer roleCode);
    
    List<ARoleDict> queryChildRole(Integer roleCode);
    List<ARoleDict> queryRoleForList(Integer staffId);
}