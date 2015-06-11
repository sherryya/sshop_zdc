package com.tmount.db.product.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.db.product.vo.GdShopInfoExpl;

public interface GdShopInfoMapper {
	List<GdShopInfo> selectAll();
    List<GdShopInfo> selectByCompanyIdUpdateTime(GdShopInfo gdShopInfo);
    List<GdShopInfo> selectByCompanyId(Integer companyId);
    List<GdShopInfoExpl> selectShopList(Map<String, Object> paramMap);
    List<GdShopInfoExpl> selectShopListByLevel(Map<String, Object> paramMap);
    Integer selectShopListCount(Map<String, Object> paramMap);
    GdShopInfoExpl selectByPKExpl(Long shopId);

    int deleteByPrimaryKey(Long shopId);

    int insert(GdShopInfo record);

    int insertSelective(GdShopInfo record);

    GdShopInfo selectByPrimaryKey(Long shopId);

    int updateByPrimaryKeySelective(GdShopInfo record);

    int updateByPrimaryKey(GdShopInfo record);
    
    int updateLevelByPrimaryKey(GdShopInfo record);
}