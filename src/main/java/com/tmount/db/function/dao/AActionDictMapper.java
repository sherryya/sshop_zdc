package com.tmount.db.function.dao;

import java.util.List;

import com.tmount.db.function.dto.AActionDict;
import com.tmount.db.role.dto.ALoginroleRel;

public interface AActionDictMapper {
    int deleteByPrimaryKey(Integer actionId);

    int insert(AActionDict record);

    int insertSelective(AActionDict record);

    AActionDict selectByPrimaryKey(Integer actionId);

    int updateByPrimaryKeySelective(AActionDict record); 

    int updateByPrimaryKey(AActionDict record);
    
    List<AActionDict>  selectActionDictListByFunctionCode(String functionCode);
    
    AActionDict selectActionDictListByActionId(Integer actionId);
    
    List<AActionDict> selectActionFilterListByRoleCodeShow(int roleCode);
    
    int deleteAActionDictByFunctionCode(String functionCode);
    
    List<AActionDict> selectActiondictListByRoleCode(List<ALoginroleRel> list);
}