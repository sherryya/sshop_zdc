package com.tmount.db.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmount.db.order.dto.ReBankAccept;
import com.tmount.db.order.dto.ReBankAcceptExample;
import com.tmount.db.order.dto.ReBankAcceptKey;
import com.tmount.db.order.vo.ReBankAcceptPayOrg;

public interface ReBankAcceptMapper {
    int countByExample(ReBankAcceptExample example);

    int deleteByExample(ReBankAcceptExample example);

    int deleteByPrimaryKey(ReBankAcceptKey key);

    int insert(ReBankAccept record);

    int insertSelective(ReBankAccept record);

    List<ReBankAccept> selectByExample(ReBankAcceptExample example);

    ReBankAccept selectByPrimaryKey(ReBankAcceptKey key);

    int updateByExampleSelective(@Param("record") ReBankAccept record, @Param("example") ReBankAcceptExample example);

    int updateByExample(@Param("record") ReBankAccept record, @Param("example") ReBankAcceptExample example);

    int updateByPrimaryKeySelective(ReBankAccept record);

    int updateByPrimaryKey(ReBankAccept record);
    
    ReBankAcceptPayOrg selectBankPayOrgByAccept(long bankAccept);
    
    List<ReBankAccept> selectBySelective(ReBankAccept reBankAccept);
    
    
}