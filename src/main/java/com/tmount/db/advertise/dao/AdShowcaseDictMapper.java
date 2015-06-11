package com.tmount.db.advertise.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.advertise.dto.AdShowcaseDict;
import com.tmount.db.advertise.vo.AdShowcaseDictVo;

public interface AdShowcaseDictMapper {
    int deleteByPrimaryKey(Long showId);

    int insert(AdShowcaseDict record);

    int insertSelective(AdShowcaseDict record);

    AdShowcaseDict selectByPrimaryKey(Long showId);

    int updateByPrimaryKeySelective(AdShowcaseDict record);

    int updateByPrimaryKey(AdShowcaseDict record);
    
    List <AdShowcaseDictVo> selectShowCaseDictList(Map<String, Object> paramMap);
    
    Integer selectShowCaseDictListCount(Map<String, Object> paramMap);
    
    List<AdShowcaseDict> selectAll(int showareaId);
}