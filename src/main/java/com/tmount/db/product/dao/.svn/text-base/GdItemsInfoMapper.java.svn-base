package com.tmount.db.product.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.product.dto.GdItemsInfo;
import com.tmount.db.product.vo.GdItemDetailInfo;
import com.tmount.db.product.vo.GdItemInputInfo;
import com.tmount.db.product.vo.GdItemListInfo;
import com.tmount.db.sys.vo.StatisTime;

public interface GdItemsInfoMapper {
    int deleteByPrimaryKey(Long itemsId);

    int insert(GdItemsInfo record);

    int insertSelective(GdItemsInfo record);

    GdItemsInfo selectByPrimaryKey(Long itemsId);

    int updateByPrimaryKeySelective(GdItemsInfo record);

    int updateByPrimaryKey(GdItemsInfo record);
    
    long selectSequence();
    
    List<GdItemsInfo> selectByShopId(Long shopId);
    List<GdItemsInfo> selectByCompanyId(Integer companyId);
    List<GdItemsInfo> selectItemList(Map<String, Object> paramIn);
    
    int selectItemListCount(Map<String, Object> paramIn);
    /**
     * 鏇存柊灏忓浘id
     * @param record
     */
    void updateSmallPicIdByPrimaryKey(GdItemsInfo record);
    
    void updateHtmlUrl(GdItemsInfo record);
    
    List<GdItemListInfo> selectItemSerchList(GdItemInputInfo gdItemInputInfo);
    
    int selectItemSerchListCount(GdItemInputInfo gdItemInputInfo);
    
    int selectSearchWordItemCount(String itemsName);
    
    GdItemDetailInfo selectItemDetail(Long itemsId);
    
    List<GdItemsInfo> selectItemsListByCollect(GdItemInputInfo gdItemInputInfo);
    
    List<GdItemsInfo> selectItemsListBySaleCount(GdItemInputInfo gdItemInputInfo);
    
    int updateDiscussForStatis(GdItemsInfo record);
    
    int updateCommentForStatis(GdItemsInfo record);
    
    GdItemDetailInfo selectMaxForStatis();
    
    int selectItemsCountStatis();
    
    List<GdItemsInfo> selectItemsListForStatis(StatisTime statisTime);
    
    int updateBrowserForStatis(GdItemsInfo record);
    
    GdItemDetailInfo selectByFlashTime(StatisTime statisTime);
    
    List<GdItemsInfo> selectDelItemList();
    
    List<GdItemListInfo> selectItemSerchListForShop(GdItemInputInfo gdItemInputInfo);
    
    int selectItemSerchListCountForShop(GdItemInputInfo gdItemInputInfo);
}