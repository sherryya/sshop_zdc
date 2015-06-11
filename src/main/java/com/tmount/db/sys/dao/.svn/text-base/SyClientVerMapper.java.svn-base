package com.tmount.db.sys.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.sys.dto.SyClientVer;

public interface SyClientVerMapper {
    List<SyClientVer> selectItemList(Map<String, Object> paramIn);
    
    int selectItemListCount(Map<String, Object> paramIn);
	Integer selectMaxId();
    int deleteByPrimaryKey(Integer clientId);

    int insert(SyClientVer record);

    int insertSelective(SyClientVer record);

    SyClientVer selectByPrimaryKey(Integer clientId);

    int updateByPrimaryKeySelective(SyClientVer record);

    int updateByPrimaryKey(SyClientVer record);
}