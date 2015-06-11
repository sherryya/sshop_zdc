package com.tmount.db.sys.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.sys.dto.SySearchWord;
import com.tmount.db.sys.vo.StatisTime;

public interface SySearchWordMapper {
	Integer selectMaxId();
    List<SySearchWord> selectItemList(Map<String, Object> paramIn);
    
    List<SySearchWord> selectItemListByLimit(StatisTime statisTime);
    
    int selectItemListCount(Map<String, Object> paramIn);
	/**
	 * 查询所有的搜索词。
	 * @return
	 */
    List<SySearchWord> selectAll();

    int deleteByPrimaryKey(Integer searchId);

    int insert(SySearchWord record);

    int insertSelective(SySearchWord record);

    SySearchWord selectByPrimaryKey(Integer searchId);

    int updateByPrimaryKeySelective(SySearchWord record);

    int updateByPrimaryKey(SySearchWord record);
}