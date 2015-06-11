package com.tmount.redis.product.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import com.tmount.db.product.dao.SyNormalDicsMapper;
import com.tmount.db.product.dto.GdShopDeliverDetail;
import com.tmount.db.product.dto.SyNormalDics;
import com.tmount.db.product.dto.SyNormalDicsKey;
import com.tmount.db.product.vo.GdItemInputInfo;
import com.tmount.redis.util.RedisClientTemplate;

public class GdItemRedis {
	@Autowired
	private RedisClientTemplate redisClientTemplate;
	
	@Autowired
	private SyNormalDicsMapper syNormalDicsMapper;
	
	public Boolean checkRedis()
	{
		Jedis jedis = null;
		Boolean checkRedis = false;
		/*判断数据库中是否配置了redis读取标识，如果配置了为success，在链接redis
		 * 如果是不是success则直接返回false*/
		SyNormalDicsKey syNormalDicsKey = new SyNormalDicsKey();
		syNormalDicsKey.setTableName("redis");
		syNormalDicsKey.setColumnName("redis");
		syNormalDicsKey.setDicValue(1);
		SyNormalDics syNormalDics = syNormalDicsMapper.selectByPrimaryKey(syNormalDicsKey);
		if(syNormalDics.getDicName().equals("success"))
		{
			jedis = redisClientTemplate.getConnection();
			if(jedis != null)
			{
				redisClientTemplate.closeConnection(jedis);
				checkRedis = true;
			}
		}else{
			checkRedis=false;
		}
		return checkRedis;
	}
	
	public String getItemName(String Itemid){
		Jedis jedis = redisClientTemplate.getConnection();
		String returnStr = jedis.get("product1");
		redisClientTemplate.closeConnection(jedis);
		return returnStr;
	}
	
	public void createSetByInputInfo(String key,GdItemInputInfo gdItemInputInfo){
		Jedis jedis = redisClientTemplate.getConnection();
		String keyword="*";
		/*根据gdItemInputInfo进行关键词的拼接*/
		/*商品id*/
		if(gdItemInputInfo.getItemsId()!=null)
			keyword+="pId:"+gdItemInputInfo.getItemsId()+"*";
		/*商店id*/
		if(gdItemInputInfo.getShopId()!=null)
			keyword+="shopId:"+gdItemInputInfo.getShopId()+"*";
		/*包装*/
		if(gdItemInputInfo.getPackId()!=null)
			keyword+="packId:"+gdItemInputInfo.getPackId()+"*";
		/*产品认证*/
		if(gdItemInputInfo.getQualityId()!=null)
			keyword+="qualityId:"+gdItemInputInfo.getQualityId()+"*";
		/*规格*/
		if(gdItemInputInfo.getStandId()!=null)
			keyword+="standId:"+gdItemInputInfo.getStandId()+"*";
		/*口感*/
		if(gdItemInputInfo.getTextureId()!=null)
			keyword+="textureId:"+gdItemInputInfo.getTextureId()+"*";
		/*目录*/
		if(gdItemInputInfo.getItemsType()!=null)
		{
			/*如果商店id不为空，则是商城目录，否则是商城目录*/
			if(gdItemInputInfo.getShopId()!=null)
				keyword+="csid:"+"*"+gdItemInputInfo.getItemsType()+"*";
			else
				keyword+="cid:"+"*"+gdItemInputInfo.getItemsType()+"*";
		}
		/*省id*/
		if(gdItemInputInfo.getProvinceCode()!=null)
			keyword+="Pcode:"+gdItemInputInfo.getProvinceCode()+"*";
		/*城市id*/
		if(gdItemInputInfo.getCityCode()!=null)
			keyword+="Ccode:"+gdItemInputInfo.getCityCode()+"*";
		/*是否免邮*/
		if(gdItemInputInfo.getDeliverFlag()!=null)
			keyword+="Dflag:"+gdItemInputInfo.getDeliverFlag()+"*";
		/*关键词*/
		if(gdItemInputInfo.getItemsName()!=null)
			keyword+="keyword:"+"*"+gdItemInputInfo.getItemsName()+"*";
		jedis.skeyset(keyword,key);
		redisClientTemplate.closeConnection(jedis);
	}
	
	public Long getSetSizeByKey(String key){
		Jedis jedis = redisClientTemplate.getConnection();
		Long setSize = jedis.scard(key);
		redisClientTemplate.closeConnection(jedis);
		return setSize;
	}
	
	public List<String> getItemBySort(String key,GdItemInputInfo gdItemInputInfo){
		List<String> strList = null;
		Jedis jedis = redisClientTemplate.getConnection();
		int start = gdItemInputInfo.getStartLimit();
		int count = gdItemInputInfo.getPageSize();
		if(gdItemInputInfo.getOrderPrice()!=null && gdItemInputInfo.getOrderPrice()>0)
		{
			if(gdItemInputInfo.getOrderByFlag()>0)
				strList = jedis.sort(key, new SortingParams().by("item_price_*").get("Jitem_*").desc().
						limit(start, count));
			else
				strList = jedis.sort(key, new SortingParams().by("item_price_*").get("Jitem_*").asc().
						limit(start, count));
		}else if(gdItemInputInfo.getOrderSaleCount()!=null && gdItemInputInfo.getOrderSaleCount()>0)
		{
			if(gdItemInputInfo.getOrderByFlag()>0)
				strList = jedis.sort(key, new SortingParams().by("item_scount_*").get("Jitem_*").desc().
						limit(start, count));
			else
				strList = jedis.sort(key, new SortingParams().by("item_scount_*").get("Jitem_*").asc().
						limit(start, count));
		}else if(gdItemInputInfo.getOrderShopLevel()!=null && gdItemInputInfo.getOrderShopLevel()>0)
		{
			if(gdItemInputInfo.getOrderByFlag()>0)
				strList = jedis.sort(key, new SortingParams().by("item_slevel_*").get("Jitem_*").desc().
						limit(start, count));
			else
				strList = jedis.sort(key, new SortingParams().by("item_slevel_*").get("Jitem_*").asc().
						limit(start, count));
		}
		else
		{
			if(gdItemInputInfo.getOrderByFlag()>0)
				strList = jedis.sort(key, new SortingParams().by("item_order_*").get("Jitem_*").desc().
						limit(start, count));
			else
				strList = jedis.sort(key, new SortingParams().by("item_order_*").get("Jitem_*").asc().
						limit(start, count));
		}
		redisClientTemplate.closeConnection(jedis);
		return strList;
	}
	
	public String getPropValue(String propKey){
		Jedis jedis = redisClientTemplate.getConnection();
		String returnStr = jedis.get(propKey);
		redisClientTemplate.closeConnection(jedis);
		return returnStr;
	}
	
	public String getItemDetailValue(Long itemId){
		Jedis jedis = redisClientTemplate.getConnection();
		String returnStr = jedis.get("JItemDetail_"+itemId);
		redisClientTemplate.closeConnection(jedis);
		return returnStr;
	}
	
	public List<String> getItemDeliverByCityCode(GdShopDeliverDetail gdShopDeliverDetail){
		Jedis jedis = redisClientTemplate.getConnection();
		String key = "deliver:"+gdShopDeliverDetail.getShopId().toString()+":"+gdShopDeliverDetail.getDeliverId().toString();
		String cityCode = gdShopDeliverDetail.getCityCode().toString();
		List<String> returnStr = jedis.hmget(key, cityCode);
		redisClientTemplate.closeConnection(jedis);
		return returnStr;
	}
	
	public void delSetByKey(String key)
	{
		Jedis jedis = redisClientTemplate.getConnection();
		jedis.del(key);
		redisClientTemplate.closeConnection(jedis);
		return;
	}
	
	public Set<String> getKeys(String pattern)
	{
		Jedis jedis = redisClientTemplate.getConnection();
		Set<String> set = jedis.keys(pattern);
		redisClientTemplate.closeConnection(jedis);
		return set;
	}

}
