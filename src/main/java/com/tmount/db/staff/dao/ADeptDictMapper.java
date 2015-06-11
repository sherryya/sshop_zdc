package com.tmount.db.staff.dao;

import java.util.List;

import com.tmount.db.staff.dto.ADeptDict;

public interface ADeptDictMapper {
	/**
	 * 根据公司代码查询出公司下得组织信息。
	 * @param companyId
	 * @return
	 */
	List<ADeptDict> selectDeptList(ADeptDict aDeptDict);
	
    int deleteByPrimaryKey(Integer deptCode);

    int insert(ADeptDict record);

    int insertSelective(ADeptDict record);

    ADeptDict selectByPrimaryKey(Integer deptCode);

    int updateByPrimaryKeySelective(ADeptDict record);

    int updateByPrimaryKey(ADeptDict record);
    
}