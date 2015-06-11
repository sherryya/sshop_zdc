package com.tmount.db.advertise.dao;

import com.tmount.db.advertise.dto.AdShowcaseBusiSale;
import com.tmount.db.advertise.dto.AdShowcaseBusiSaleKey;

public interface AdShowcaseBusiSaleMapper {
    int deleteByPrimaryKey(AdShowcaseBusiSaleKey key);

    int insert(AdShowcaseBusiSale record);

    int insertSelective(AdShowcaseBusiSale record);

    AdShowcaseBusiSale selectByPrimaryKey(AdShowcaseBusiSaleKey key);

    int updateByPrimaryKeySelective(AdShowcaseBusiSale record);

    int updateByPrimaryKey(AdShowcaseBusiSale record);
}