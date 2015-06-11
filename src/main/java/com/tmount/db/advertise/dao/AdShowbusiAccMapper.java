package com.tmount.db.advertise.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.advertise.dto.AdShowbusiAcc;
import com.tmount.db.advertise.vo.AdShowbusiAccVo;

public interface AdShowbusiAccMapper {
    int deleteByPrimaryKey(Long showbusiId);

    int deleteBySieArea(Map map);
    
    int insert(AdShowbusiAcc record);

    int insertSelective(AdShowbusiAcc record);

    AdShowbusiAcc selectByPrimaryKey(Long showbusiId);

    int updateByPrimaryKeySelective(AdShowbusiAcc record);

    int updateByPrimaryKey(AdShowbusiAcc record);
    
    List<AdShowbusiAcc> selectByShowSiteId(Integer showsiteId);
    
    List<AdShowbusiAccVo> selectAccList(Map map);
    
    int selectCheckAccDate(AdShowbusiAcc adShowbusiAcc);
    
    List<AdShowbusiAcc> selectAccByPageNo(AdShowbusiAcc adShowbusiAcc);
    
    List<AdShowbusiAcc> selectAccByShopShowId(AdShowbusiAcc adShowbusiAcc);
}