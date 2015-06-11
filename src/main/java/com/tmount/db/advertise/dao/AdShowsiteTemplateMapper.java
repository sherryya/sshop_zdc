package com.tmount.db.advertise.dao;

import java.util.List;

import com.tmount.db.advertise.dto.AdShowsiteTemplate;

public interface AdShowsiteTemplateMapper {
    int deleteByPrimaryKey(Integer showsiteCode);

    int insert(AdShowsiteTemplate record);

    int insertSelective(AdShowsiteTemplate record);

    AdShowsiteTemplate selectByPrimaryKey(Integer showsiteCode);

    int updateByPrimaryKeySelective(AdShowsiteTemplate record);

    int updateByPrimaryKey(AdShowsiteTemplate record);
    
    List<AdShowsiteTemplate> selectByShowTemplateId(Integer showTemplateId);
}