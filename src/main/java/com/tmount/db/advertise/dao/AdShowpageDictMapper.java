package com.tmount.db.advertise.dao;

import java.util.List;

import com.tmount.db.advertise.dto.AdShowpageDict;
import com.tmount.db.advertise.dto.AdShowpageDictKey;
import com.tmount.db.advertise.vo.AdShowpageDictVo;

public interface AdShowpageDictMapper {
    int deleteByPrimaryKey(AdShowpageDictKey key);
    
    int deleteByPageId(Long PageId);

    int insert(AdShowpageDict record);
    
    int insertAll(AdShowpageDict record);

    int insertSelective(AdShowpageDict record);

    AdShowpageDict selectByPrimaryKey(AdShowpageDictKey key);
    
    AdShowpageDict selectByPageId(Long pageId);
    
    int updateByPrimaryKeySelective(AdShowpageDict record);

    int updateByPrimaryKey(AdShowpageDict record);
    
    int selectByPrimaryKeyCount(Long showId);
    
    List<AdShowpageDictVo> selectPageByShowId (AdShowpageDictVo adShowpageDictVo);
    
    int selectCheckDate(AdShowpageDict adShowpageDict);
}