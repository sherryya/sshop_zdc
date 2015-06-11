package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.SyNormalDics;
import com.tmount.db.product.dto.SyNormalDicsKey;
import com.tmount.db.product.vo.SyNormalDicsInfo;
import com.tmount.db.product.vo.SyNormalDicsList;

public interface SyNormalDicsMapper {
    /**
     * 根据tableName和columnName得到字典表信息。
     * @param key
     * @return
     */
	List<SyNormalDics> selectValues(SyNormalDicsKey key);

    int deleteByPrimaryKey(SyNormalDicsKey key);

    int insert(SyNormalDics record);

    int insertSelective(SyNormalDics record);

    SyNormalDics selectByPrimaryKey(SyNormalDicsKey key);

    int updateByPrimaryKeySelective(SyNormalDics record);

    int updateByPrimaryKey(SyNormalDics record);
    
    List<SyNormalDicsInfo> selectItemsExpByPrimaryKey(SyNormalDicsKey syNormalDicsKey);
    
    List<SyNormalDicsList> selectDicsByColumnName(SyNormalDicsKey syNormalDicsKey);
}