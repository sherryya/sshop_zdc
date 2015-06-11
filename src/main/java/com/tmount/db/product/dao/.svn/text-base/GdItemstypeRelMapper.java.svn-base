package com.tmount.db.product.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.product.dto.GdItemstypeRel;
import com.tmount.db.product.dto.GdItemstypeRelKey;
import com.tmount.db.product.vo.GdItemstypeRelInfo;

public interface GdItemstypeRelMapper {
	List<GdItemstypeRel> selectBySelective(GdItemstypeRel gdItemstypeRel);
	int deleteByShopId(Long shopId);
	int deleteByItemsId(Long itemsId);
	GdItemstypeRelInfo selectByPKExpl(GdItemstypeRelKey key);
    List <GdItemstypeRelInfo> selectItemstypeRelList(Map<String, Object> paramMap);
    Integer selectItemstypeRelListCount(Map<String, Object> paramMap);

    int deleteByPrimaryKey(GdItemstypeRelKey key);

    int insert(GdItemstypeRel record);

    int insertSelective(GdItemstypeRel record);

    GdItemstypeRel selectByPrimaryKey(GdItemstypeRelKey key);

    int updateByPrimaryKeySelective(GdItemstypeRel record);

    int updateByPrimaryKey(GdItemstypeRel record);
}