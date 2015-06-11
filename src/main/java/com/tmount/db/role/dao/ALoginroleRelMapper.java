package com.tmount.db.role.dao;

import java.util.List;

import com.tmount.db.role.dto.ALoginroleRel;

public interface ALoginroleRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ALoginroleRel record);

    int insertSelective(ALoginroleRel record);

    ALoginroleRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ALoginroleRel record);

    int updateByPrimaryKey(ALoginroleRel record);
    
    void insertALoginRoleRel(ALoginroleRel record);
    
    void deleteALoginRolRel(Integer staffId);
    
    List<ALoginroleRel> selectALoginRoleRel(Integer staffId);
    
    void deleteALoginRolRelByRoleCode(Integer staffId);
    
    List<ALoginroleRel> selectALoginRoleRelByStaffid(Integer staffId);
    
    List<ALoginroleRel> selectALoginRoleRelByOpno(String opNo);
    
    List<ALoginroleRel> selectALoginRoleRelByRoleCode(Integer roleCode);
    
}