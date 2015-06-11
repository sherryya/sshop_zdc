package com.tmount.business.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.business.product.vo.ProductMonthConsumer;
import com.tmount.db.order.dao.ReUserOrderDetailMapper;
import com.tmount.db.order.vo.ProductMonthConsumerDetail;
import com.tmount.db.product.dao.GdCompanyDicMapper;
import com.tmount.db.product.dao.GdInvoiceDicMapper;
import com.tmount.db.product.dao.GdItemsDeliverMapper;
import com.tmount.db.product.dao.GdItemsDetailMapper;
import com.tmount.db.product.dao.GdItemsExpInfoMapper;
import com.tmount.db.product.dao.GdItemsInfoMapper;
import com.tmount.db.product.dao.GdItemsItemRelMapper;
import com.tmount.db.product.dao.GdItemsTypeDicMapper;
import com.tmount.db.product.dao.GdItemstypeRelMapper;
import com.tmount.db.product.dao.GdPicResRelMapper;
import com.tmount.db.product.dao.GdResouceDicMapper;
import com.tmount.db.product.dao.GdShopDeliverDetailMapper;
import com.tmount.db.product.dao.GdShopFloorRelMapper;
import com.tmount.db.product.dao.GdShopInfoMapper;
import com.tmount.db.product.dao.GdShopItemsRelMapper;
import com.tmount.db.product.dao.GdShopItemstypeDicMapper;
import com.tmount.db.product.dao.GdShopRoomRelMapper;
import com.tmount.db.product.dao.SyFloorDicMapper;
import com.tmount.db.product.dao.SyFoodTypeMapper;
import com.tmount.db.product.dao.SyNormalDicsMapper;
import com.tmount.db.product.dao.SyServDicMapper;
import com.tmount.db.product.dto.GdCompanyDic;
import com.tmount.db.product.dto.GdInvoiceDic;
import com.tmount.db.product.dto.GdItemsDeliver;
import com.tmount.db.product.dto.GdItemsDetail;
import com.tmount.db.product.dto.GdItemsExpInfo;
import com.tmount.db.product.dto.GdItemsInfo;
import com.tmount.db.product.dto.GdPicResRel;
import com.tmount.db.product.dto.GdResouceDic;
import com.tmount.db.product.dto.GdShopDeliverDetail;
import com.tmount.db.product.dto.GdShopFloorRelInfo;
import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.db.product.dto.GdShopItemstypeDic;
import com.tmount.db.product.dto.GdShopRoomRel;
import com.tmount.db.product.dto.GdSubItemInfo;
import com.tmount.db.product.dto.SyFloorDic;
import com.tmount.db.product.dto.SyFoodType;
import com.tmount.db.product.dto.SyNormalDics;
import com.tmount.db.product.dto.SyNormalDicsKey;
import com.tmount.db.product.dto.SyServDic;
import com.tmount.db.product.vo.GdItemDeliverInfo;
import com.tmount.db.product.vo.GdItemDetailInfo;
import com.tmount.db.product.vo.GdItemInputInfo;
import com.tmount.db.product.vo.GdItemListInfo;
import com.tmount.db.product.vo.GdItemsItemRelConditions;
import com.tmount.db.product.vo.GdItemstypeDicInfo;
import com.tmount.db.product.vo.GdItemstypeRelInfo;
import com.tmount.db.product.vo.GdShopInfoExpl;
import com.tmount.db.product.vo.GdShopItemsRelInfo;
import com.tmount.db.product.vo.SyNormalDicsInfo;
import com.tmount.db.product.vo.SyNormalDicsList;
import com.tmount.db.pub.dao.DbSequenceMapper;
import com.tmount.db.user.dao.UsUserCommentMapper;
import com.tmount.db.user.vo.UsUserCommentUser;

@Service
public class GoodsService {
	@Autowired
	private GdCompanyDicMapper gdCompanyDicMapper;

	@Autowired
	private GdShopInfoMapper gdShopInfoMapper;

	@Autowired
	private GdItemsInfoMapper gdItemsInfoMapper;
	
	@Autowired
	private GdPicResRelMapper gdPicResRelMapper;

	@Autowired
	private GdItemsDetailMapper gdItemsDetailMapper;

	@Autowired
	private GdShopRoomRelMapper gdShopRoomRelMapper;

	@Autowired
	private GdShopFloorRelMapper gdShopFloorRelMapper;

	@Autowired
	private SyFloorDicMapper syFloorDicMapper;

	@Autowired
	private GdItemsExpInfoMapper gdItemsExpInfoMapper;

	@Autowired
	private SyFoodTypeMapper syFoodTypeMapper;

	@Autowired
	private GdShopItemsRelMapper gdShopItemsRelMapper;

	@Autowired
	private GdShopItemstypeDicMapper gdShopItemstypeDicMapper;
	
	@Autowired
	private GdResouceDicMapper gdResouceDicMapper;
	
	@Autowired
	private GdItemsItemRelMapper gdItemsItemRelMapper;
	
	@Autowired
	private SyServDicMapper syServDicMapper;
	
	@Autowired
	private GdItemsTypeDicMapper gdItemsTypeDicMapper;
	
	@Autowired
	private SyNormalDicsMapper syNormalDicsMapper;
	
	@Autowired
	private ReUserOrderDetailMapper reUserOrderDetailMapper;
	
	@Autowired
	private UsUserCommentMapper usUserCommentMapper;
	
	@Autowired
	private GdShopDeliverDetailMapper gdShopDeliverDetailMapper;
	
	@Autowired
	private GdItemsDeliverMapper gdItemsDeliverMapper;
	
	@Autowired
	private GdInvoiceDicMapper gdInvoiceDicMapper;
	
	@Autowired
	private DbSequenceMapper dbSequenceMapper;
	
	@Autowired
	private GdItemstypeRelMapper gdItemstypeRelMapper;

	public GdCompanyDic getGdCompanyDicByCompanyId(Integer companyId){
		return gdCompanyDicMapper.selectByPrimaryKey(companyId);
	}

	public List<GdShopInfo> getGdShopInfoByCompanyId(GdShopInfo gdShopInfo){
		return gdShopInfoMapper.selectByCompanyIdUpdateTime(gdShopInfo);
	}

	public GdItemsInfo getGdItemsInfoByItemsId(Long itemsId){
		return gdItemsInfoMapper.selectByPrimaryKey(itemsId);
	}

	public List<GdItemsInfo> getGdItemsInfoByMap(Map<String, Object> paramIn){
		return gdItemsInfoMapper.selectItemList(paramIn);
	}

	public List<GdPicResRel> getGdPicResRelBySelective(GdPicResRel record){
		return gdPicResRelMapper.selectSelective(record);
	}

	public GdItemsDetail getGdItemsDetailByItemsId(Long itemsId){
		return gdItemsDetailMapper.selectByPrimaryKey(itemsId);
	}

	public GdShopRoomRel getGdShopRoomRelByItemsId(Long itemsId){
		return gdShopRoomRelMapper.selectByPrimaryKey(itemsId);
	}

	public GdShopFloorRelInfo getGdShopFloorRelByFoodTypeId(Integer shopFloorId){
		return gdShopFloorRelMapper.selectByPrimaryKey(shopFloorId);
	}

	public List<GdShopFloorRelInfo> getGdShopFloorRelByShopId(Long shopId){
		return gdShopFloorRelMapper.selectByShopId(shopId);
	}

	public List<GdInvoiceDic> getGdInvoiceDicByShopId(Long shopId){
		return gdInvoiceDicMapper.selectByShopId(shopId);
	}

	public SyFloorDic getSyFloorDicByFloorId(Integer floorId){
		return syFloorDicMapper.selectByPrimaryKey(floorId);
	}

	public GdItemsExpInfo getGdItemsExpInfoByItemsId(Long itemsId){
		return gdItemsExpInfoMapper.selectByPrimaryKey(itemsId);
	}

	public SyFoodType getSyFoodTypeByFoodTypeId(Integer foodTypeId){
		return syFoodTypeMapper.selectByPrimaryKey(foodTypeId);
	}

	public List<GdShopItemsRelInfo> selectShopItemsRelList(Map<String, Object> inParam){
		return gdShopItemsRelMapper.selectShopItemsRelList(inParam);
	}

	public List<GdShopItemstypeDic> getGdItemsTypeDicListSelective(GdShopItemstypeDic gdShopItemstypeDic){
		return gdShopItemstypeDicMapper.selectSelective(gdShopItemstypeDic);
	}
	
	public GdResouceDic getGdResouceDicByResourceId(Long resourceId){
		return gdResouceDicMapper.selectByPrimaryKey(resourceId);
	}

	public List<GdSubItemInfo> getGdItemsItemRelByItemsId(GdItemsItemRelConditions gdItemsItemRelConditions){
		return gdItemsItemRelMapper.selectByItemsId(gdItemsItemRelConditions);
	}

	public List<GdSubItemInfo> getGdItemsItemRelByItemsIdLimit(GdItemsItemRelConditions gdItemsItemRelConditions){
		return gdItemsItemRelMapper.selectByItemsIdLimit(gdItemsItemRelConditions);
	}
	
	public List<GdItemListInfo> getGdItemsListForSearch(GdItemInputInfo gdItemInputInfo){
		return gdItemsInfoMapper.selectItemSerchList(gdItemInputInfo);
	}
	
	public List<GdItemListInfo> getGdItemsListForShopSearch(GdItemInputInfo gdItemInputInfo){
		return gdItemsInfoMapper.selectItemSerchListForShop(gdItemInputInfo);
	}
	
	public List<SyServDic> getSyServDicByShopId(Long shopId){
		return syServDicMapper.selectSyServDicByShopId(shopId);
	}
	
	public List<GdItemstypeDicInfo> getGdItemstypeDicByItems(List<GdItemListInfo> gdItemListInfo){
		return gdItemsTypeDicMapper.selectByItemIdList(gdItemListInfo);
	}
	
	public Integer getGdItemsCountForSearch(GdItemInputInfo gdItemInputInfo){
		return gdItemsInfoMapper.selectItemSerchListCount(gdItemInputInfo);
	}
	
	public Integer getGdItemsCountForShopSearch(GdItemInputInfo gdItemInputInfo){
		return gdItemsInfoMapper.selectItemSerchListCountForShop(gdItemInputInfo);
	}
	
	public List<SyNormalDicsInfo> getItemsExpByPrimaryKey(SyNormalDicsKey syNormalDicsKey){
		return syNormalDicsMapper.selectItemsExpByPrimaryKey(syNormalDicsKey);
	}
	
	public List<SyNormalDicsList> getDicsListByColumnName(SyNormalDicsKey syNormalDicsKey){
		return syNormalDicsMapper.selectDicsByColumnName(syNormalDicsKey);
	}
	
	public SyNormalDics getDicsByPrimaryKey(SyNormalDicsKey syNormalDicsKey){
		return syNormalDicsMapper.selectByPrimaryKey(syNormalDicsKey);
	}
	
	public GdItemDetailInfo getItemsDetailByItemId(Long itemsId){
		return gdItemsInfoMapper.selectItemDetail(itemsId);
	}
	
	public List<GdItemstypeDicInfo> getItemTypeListByShopId(Long shopId){
		return gdShopItemstypeDicMapper.selectItemTypeListByShopId(shopId);
	}
	
	public List<GdItemstypeDicInfo> getItemTypeListByShopItemIdList(Map<String,Object> gdItemstypeDicMap){
		return gdShopItemstypeDicMapper.selectItemTypeListByShopItemIdList(gdItemstypeDicMap);
	}
	
	public GdShopInfoExpl getGdShopInfoExplByShoId(Long shopId){
		return gdShopInfoMapper.selectByPKExpl(shopId);
	}
	
	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	public ProductMonthConsumer getProductMonthConsumer(Map<String, Object> paramMap) {
		ProductMonthConsumer productMonthConsumer = new ProductMonthConsumer();
		Long itemsId = (Long)paramMap.get("itemsId");
		GdItemsInfo gdItemsInfo = gdItemsInfoMapper.selectByPrimaryKey(itemsId);
		
		if (gdItemsInfo == null) {
			productMonthConsumer.setItemsName("");
			productMonthConsumer.setSaleCount(0);
			productMonthConsumer.setDiscuss30Count(0);
			productMonthConsumer.setTotalRows(0);
		} else {
			productMonthConsumer.setItemsName(gdItemsInfo.getItemsName());
			
			productMonthConsumer.setSaleCount(gdItemsInfo.getSaleCount()==null?0:gdItemsInfo.getSaleCount());
			productMonthConsumer.setDiscuss30Count(gdItemsInfo.getDiscuss30Count()==null?0:gdItemsInfo.getDiscuss30Count());
			List<ProductMonthConsumerDetail> itemList = reUserOrderDetailMapper.statConsumerByItemsId(paramMap); 
			productMonthConsumer.setItemList(itemList);
			productMonthConsumer.setTotalRows(reUserOrderDetailMapper.statConsumerByItemsIdCount(paramMap));
		}
		return productMonthConsumer;
	}
	
	/**
	 * 得到产品用户评论信息
	 * @param paramMap
	 * @return
	 */
	public List<UsUserCommentUser> getProductUserComment(Map<String, Object> paramMap) {
		return usUserCommentMapper.selectBySelective(paramMap);
	}
	
	/**
	 * 得到产品用户评论信息数量
	 * @param paramMap
	 * @return
	 */
	public Integer getProductUserCommentCount(Map<String, Object> paramMap) {
		return usUserCommentMapper.selectBySelectiveCount(paramMap);
	}
	
	public  List<GdItemDeliverInfo> getGdShopDeliverByCityCode(GdShopDeliverDetail gdShopDeliverDetail){
		return gdShopDeliverDetailMapper.selectByCityCode(gdShopDeliverDetail);
	}
	
	public  GdItemsDeliver getItemsDeleverByItemsId(Long itemsId){
		return gdItemsDeliverMapper.selectByPrimaryKey(itemsId);
	}
	
	public  List<GdItemsInfo> getItemsByCollect(GdItemInputInfo gdItemInputInfo){
		return gdItemsInfoMapper.selectItemsListByCollect(gdItemInputInfo);
	}
	
	public  List<GdItemsInfo> getItemsBySaleCount(GdItemInputInfo gdItemInputInfo){
		return gdItemsInfoMapper.selectItemsListBySaleCount(gdItemInputInfo);
	}
	
	public  Long getKeySeqForRedis(String keyName){
		return dbSequenceMapper.selectSeqByName(keyName).getSeqValue();
	}
	
	public List<GdItemstypeDicInfo> getAllGdItemstypeDic(){
		return gdItemsTypeDicMapper.selectAllItemsTypeList();
	}
	
	public List<GdItemstypeRelInfo> selectItemstypeRelList(Map<String, Object> paramMap){
		return gdItemstypeRelMapper.selectItemstypeRelList(paramMap);
	}
	
	public Integer selectShopListCountByName(Map<String, Object> paramMap){
		return gdShopInfoMapper.selectShopListCount(paramMap);
	}
	
	public List<GdShopInfoExpl> selectShopListByName(Map<String, Object> paramMap){
		return gdShopInfoMapper.selectShopListByLevel(paramMap);
	}
	
}
