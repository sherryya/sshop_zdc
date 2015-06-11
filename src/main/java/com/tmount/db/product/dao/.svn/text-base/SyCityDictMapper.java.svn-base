package com.tmount.db.product.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.product.dto.GdShopDeliverDetail;
import com.tmount.db.product.dto.SyCityDict;
import com.tmount.db.product.vo.SyCityInfo;

public interface SyCityDictMapper {
	/**
	 *
	 */
	List<SyCityDict> selectProvinceList();

	/**
	 *
	 * @return
	 */
	List<SyCityDict> selectCityList(Map<String, Object> paramMap);
	
	
	/**
	 * 获取区县列表
	 * @return
	 */
	List<SyCityDict> selectDistrictList(Map<String, Object> paramMap);
	
    int deleteByPrimaryKey(Integer cityCode);

    int insert(SyCityDict record);

    int insertSelective(SyCityDict record);

    SyCityDict selectByPrimaryKey(Integer cityCode);

    int updateByPrimaryKeySelective(SyCityDict record);

    int updateByPrimaryKey(SyCityDict record);
    
    List<SyCityInfo> selectCityListByDeliverId(GdShopDeliverDetail gdShopDeliverDetail);

	List<SyCityDict> selectChildCityList(int parentCode);
}
