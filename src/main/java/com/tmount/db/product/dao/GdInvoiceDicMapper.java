package com.tmount.db.product.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.product.dto.GdInvoiceDic;
import com.tmount.db.product.dto.GdInvoiceDicKey;


public interface GdInvoiceDicMapper {
	List<GdInvoiceDic> selectByShopId(Long shopId);
    Integer selectMaxIdByShopId(Long shopId);
    List<GdInvoiceDic> selectItemList(Map<String, Object> paramIn);
    int selectItemListCount(Map<String, Object> paramIn);
    
    int deleteByPrimaryKey(GdInvoiceDicKey key);

    int insert(GdInvoiceDic record);

    int insertSelective(GdInvoiceDic record);

    GdInvoiceDic selectByPrimaryKey(GdInvoiceDicKey key);

    int updateByPrimaryKeySelective(GdInvoiceDic record);

    int updateByPrimaryKey(GdInvoiceDic record);
}