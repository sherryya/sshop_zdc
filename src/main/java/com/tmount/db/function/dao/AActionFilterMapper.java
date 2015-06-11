package com.tmount.db.function.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.function.dto.AActionFilter;

public interface AActionFilterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AActionFilter record);

    int insertSelective(AActionFilter record);

    AActionFilter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AActionFilter record);

    int updateByPrimaryKey(AActionFilter record);
    
    List<AActionFilter> selectActionFilterList(Integer roleCode);
    
    int deleteAActionFilterByRoleCodeAndFunctionCode(Map<String, Object> paramMap);
    
    int deleteAActionFilterByRoleCode(Integer roleCode);
    
    int deleteAActionFilterByFunctionCode(String functionCode);
}