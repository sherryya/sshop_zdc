package com.tmount.db.advertise.dao;

import java.util.List;

import com.tmount.db.advertise.dto.AdShowsiteDict;
import com.tmount.db.advertise.vo.AdShowPageSiteVo;

public interface AdShowsiteDictMapper {
    int deleteByPrimaryKey(Integer showsiteId);

    int insert(AdShowsiteDict record);

    int insertSelective(AdShowsiteDict record);

    AdShowsiteDict selectByPrimaryKey(Integer showsiteId);

    int updateByPrimaryKeySelective(AdShowsiteDict record);

    int updateByPrimaryKey(AdShowsiteDict record);
    
    List<AdShowPageSiteVo> getShowPageSiteList(AdShowPageSiteVo adShowPageSiteVo);
    
    int deleteShowSiteByPageId(Long pageId);
    
    List<AdShowsiteDict> selectSiteByPageId(Long pageId);
}