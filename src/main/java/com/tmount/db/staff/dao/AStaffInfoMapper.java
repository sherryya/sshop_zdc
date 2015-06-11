package com.tmount.db.staff.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.staff.dto.AStaffInfo;

public interface AStaffInfoMapper {
    int deleteByPrimaryKey(Integer staffId);

    int insert(AStaffInfo record);

    int insertSelective(AStaffInfo record);

    AStaffInfo selectByPrimaryKey(Integer staffId);

    int updateByPrimaryKeySelective(AStaffInfo record);

    int updateByPrimaryKey(AStaffInfo record);
    
    AStaffInfo selectAStaffInfoByOpnoNoDept(String opNo);
    
    void insertAStaffInfo(AStaffInfo record);
    
    void deleteAStaffInfo(Integer staffId);
    
    AStaffInfo selectAStaffInfo(Integer staffId);
    
    String selectOpnoByOpno(String opNo);
    
    String selectAStaffInfoByStaffId(Integer staffId);
    
    String selectAStaffInfoByOpnoStaffId(AStaffInfo record);
    
    void updateAStaffInfo(AStaffInfo record);
    
    AStaffInfo selectAStaffInfoByOpno(String opNo);
    
    List selectAStaffInfoByDeptCode(int deptCode);
    
    void updateAStaffInfoPassword(AStaffInfo record);
    
    List <AStaffInfo> searchStaffList(Map<String, Object> paramMap);
    
    Integer searchStaffListCount(Map<String, Object> paramMap);
}