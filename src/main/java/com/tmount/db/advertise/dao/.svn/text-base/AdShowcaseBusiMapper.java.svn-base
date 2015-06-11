package com.tmount.db.advertise.dao;

import java.util.List;

import com.tmount.db.advertise.dto.AdShowcaseBusi;
import com.tmount.db.advertise.vo.AdvertiseInfo;

public interface AdShowcaseBusiMapper {
    int deleteByPrimaryKey(Long showsiteId);

    int insert(AdShowcaseBusi record);

    int insertSelective(AdShowcaseBusi record);

    AdShowcaseBusi selectByPrimaryKey(Long showsiteId);

    int updateByPrimaryKeySelective(AdShowcaseBusi record);

    int updateByPrimaryKey(AdShowcaseBusi record);
    
    List<AdvertiseInfo> selectAdListByShopShowId(AdShowcaseBusi adShowcaseBusi);
    
    List<AdShowcaseBusi> selectAdPageCountByShopShowId(AdShowcaseBusi adShowcaseBusi);
}