package com.tmount.db.advertise.dao;

import java.util.List;

import com.tmount.db.advertise.dto.AdShowpageTemplate;
import com.tmount.db.advertise.vo.AdShowpageTemplateVo;

public interface AdShowpageTemplateMapper {
    int deleteByPrimaryKey(Integer showTemplateId);

    int insert(AdShowpageTemplate record);

    int insertSelective(AdShowpageTemplate record);

    AdShowpageTemplate selectByPrimaryKey(Integer showTemplateId);

    int updateByPrimaryKeySelective(AdShowpageTemplate record);

    int updateByPrimaryKey(AdShowpageTemplate record);
    
    List<AdShowpageTemplateVo>selectByShowId(Long showId);
}