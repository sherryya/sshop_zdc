package com.tmount.db.sys.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.sys.dto.SyHotWords;

public interface SyHotWordsMapper {
	Integer selectMaxId();
    List<SyHotWords> selectItemList(Map<String, Object> paramIn);
    
    int selectItemListCount(Map<String, Object> paramIn);
	/**
	 * 查询所有的热词。
	 * @return
	 */
    List<SyHotWords> selectAll();
    
    int deleteByPrimaryKey(Integer hotwordsId);

    int insert(SyHotWords record);

    int insertSelective(SyHotWords record);

    SyHotWords selectByPrimaryKey(Integer hotwordsId);

    int updateByPrimaryKeySelective(SyHotWords record);

    int updateByPrimaryKey(SyHotWords record);
}