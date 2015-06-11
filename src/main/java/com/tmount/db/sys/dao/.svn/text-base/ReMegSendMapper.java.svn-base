package com.tmount.db.sys.dao;

import java.util.List;

import com.tmount.db.sys.dto.ReMegSend;
import com.tmount.db.sys.vo.StatisTime;

public interface ReMegSendMapper {
    int deleteByPrimaryKey(Long mesgId);

    int insert(ReMegSend record);

    int insertSelective(ReMegSend record);

    ReMegSend selectByPrimaryKey(Long mesgId);

    int updateByPrimaryKeySelective(ReMegSend record);

    int updateByPrimaryKey(ReMegSend record);
    
    int selectCount();
    
    List<ReMegSend> selectSendMsgList(StatisTime statisTime);
}