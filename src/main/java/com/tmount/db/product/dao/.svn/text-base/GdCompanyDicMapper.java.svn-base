package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.GdCompanyDic;

public interface GdCompanyDicMapper {
    List<GdCompanyDic> selectAll();
    int selectAllCount();
    List<GdCompanyDic> selectAllCompany(String companyName);
    int selectAllCompanyCount(String companyName);
    int deleteByPrimaryKey(Integer companyId);

    int insert(GdCompanyDic record);

    int insertSelective(GdCompanyDic record);

    GdCompanyDic selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(GdCompanyDic record);

    int updateByPrimaryKey(GdCompanyDic record);
}
