package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.GdPicResRel;
import com.tmount.db.product.dto.GdResouceDic;

public interface GdResouceDicMapper {
    int deleteByPrimaryKey(Long resId);

    int insert(GdResouceDic record);

    int insertSelective(GdResouceDic record);

    GdResouceDic selectByPrimaryKey(Long resId);

    int updateByPrimaryKeySelective(GdResouceDic record);

    int updateByPrimaryKey(GdResouceDic record);
    /**
     * 鐢熸垚璧勬簮id
     * @return
     */
    long generateResId();
    /**
     * 鑾峰彇鏌愬疄浣撶壒瀹氱被鍨嬬殑璧勬簮 url鍒楄〃
     * @param resRel
     * @return
     */
    List<String> getResourceUrlByType(GdPicResRel resRel);
    /**
     * 鍒犻櫎鏌愬疄浣撶壒瀹氱被鍨嬬殑璧勬簮 
     * @param resRel
     */
    void deleteByPicResRel(GdPicResRel resRel);
}