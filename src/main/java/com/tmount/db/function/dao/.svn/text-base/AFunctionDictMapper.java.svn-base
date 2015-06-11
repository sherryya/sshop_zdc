package com.tmount.db.function.dao;

import java.util.List;

import com.tmount.db.function.dto.AFunctionDict;
import com.tmount.db.role.dto.ALoginroleRel;

public interface AFunctionDictMapper {
    int deleteByPrimaryKey(String functionCode);

    int insert(AFunctionDict record);

    int insertSelective(AFunctionDict record);
  
    AFunctionDict selectByPrimaryKey(String functionCode);

    int updateByPrimaryKeySelective(AFunctionDict record);

    int updateByPrimaryKey(AFunctionDict record);
    
    List<AFunctionDict> queryFunctionForListByRoleCode(Integer roleCode);
    
    List<AFunctionDict> queryChildFunction(String functionCode);
    
    String selectParentFunctionCode(String functionCode);
    
    List<AFunctionDict> queryFunctionListForLogin(List<ALoginroleRel> roleList);
    
    List<AFunctionDict> queryFunctionForList();
    
    List<AFunctionDict> queryFunctionForListByOpStaffId(Integer staffId);
}