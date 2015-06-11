package com.tmount.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.db.product.dto.GdItemsExpRice;
import com.tmount.db.product.dto.GdItemsInfo;
import com.tmount.db.product.dto.GdItemsTypeFlat;
import com.tmount.db.product.dto.GdShopDeliver;
import com.tmount.db.product.dto.GdShopDeliverDetail;
import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.db.product.dto.SyNormalDicsKey;
import com.tmount.db.product.dto.SyServDic;
import com.tmount.db.product.vo.GdItemDeliverInfo;
import com.tmount.db.product.vo.GdItemDetailInfo;
import com.tmount.db.product.vo.GdItemInputInfo;
import com.tmount.db.product.vo.GdItemListInfo;
import com.tmount.db.product.vo.GdItemstypeDicInfo;
import com.tmount.db.product.vo.SyNormalDicsInfo;
import com.tmount.db.product.vo.SyNormalDicsList;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.db.sys.dto.SysAmountDic;
import com.tmount.db.sys.vo.StatisTime;
import com.tmount.redis.util.RedisClientTemplate;

public class transDataToRedis {
	private ApplicationContext context = null;
	private SqlSessionTemplate sqlSessionTemplate = null;
	private RedisClientTemplate redisClientTemplate = null;
	
	public transDataToRedis()
	{
		context = new ClassPathXmlApplicationContext("springAppContext.xml");  
		/*获取mybatis工厂类*/
		sqlSessionTemplate = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
		/*获取redis工厂类*/
		redisClientTemplate = (RedisClientTemplate) context.getBean("redisMasterClientTemplate");
	}
	
	public static void main(String[] args) {
		transDataToRedis transBean = new transDataToRedis();
		
		if(args[0].equals("0"))/*商品信息同步*/
		{
//			transBean.itemsTransBak();
		}else if(args[0].equals("1")){/*每次更新完商品列表和详细信息后，判断属性信息是否存在，如果不存在则加入*/
			transBean.propTrans();
		}else if(args[0].equals("2")){/*更新店铺运费模板信息*/
			transBean.shopDeliverTrans();
		}else if(args[0].equals("3")){/*删除redis中已经无效的商品数据*/
			transBean.itemsDataDel();
		}else
		{
			System.out.println("您输入的入参不合规范!!!!!!!"+args[0]);
		}
		
	}
	/*商品信息同步redis*/
	public void itemsTransBak()
	{
		Jedis jedis = null;
		/*取程序开始执行时间*/
		Long startT=System.currentTimeMillis();
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*先要从sys_amount_dic表中取出itemsTrans上次提取时间*/
		SysAmountDic sysAmountDic = getSysAmount("itemsTrans");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic==null)
		{
			return;
		}
		/*设置起始时间*/
		StatisTime statisTime = new StatisTime();
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		
		try{
			/*获取jedis对象*/
			jedis = redisClientTemplate.getConnection();				
			List<GdItemsInfo> GdItemsInfoList = sqlSessionTemplate
					.selectList("com.tmount.db.product.dao.GdItemsInfoMapper.selectByFlashTime", statisTime);
			System.out.println("itemsTrans ===== GdItemsInfoList size is ="+GdItemsInfoList.size());
			System.out.println("itemsTrans ===== begintime is ="+statisTime.getBeginTime());
			System.out.println("itemsTrans ===== endtime is ="+statisTime.getEndTime());
			/*遍历list,循环处理每一个商品,加入到redis缓存中*/
			GdItemInputInfo gdItemInputInfo = new GdItemInputInfo();
			Iterator<GdItemsInfo> gdItemsInfoIterator = GdItemsInfoList.iterator();
	        while(gdItemsInfoIterator.hasNext())
	        {
	        	/*商品保存到redis中，对应的key字符串和value字符串*/
	        	String itemsKeyStr = "";
	        	String itemsValueStr="";
	        	GdItemsInfo gdItemsInfoToRedis = gdItemsInfoIterator.next();
	        	System.out.println("itemsTrans ===== gditemListInfo is ="+gdItemsInfoToRedis.getItemsId());
	        	/*取出商品对应的json串保存信息，商店、商品相关信息*/
	        	gdItemInputInfo.setItemsId(gdItemsInfoToRedis.getItemsId());
	        	GdItemListInfo gdItemListInfo = sqlSessionTemplate.selectOne("com.tmount.db.product.dao.GdItemsInfoMapper.selectItemSerchList", gdItemInputInfo);
	        	System.out.println("itemsTrans ===== gditemListInfo is ="+gdItemListInfo.getItemsId());
	        	/*通过商店id获取服务保障相关信息*/
	        	/*由于商店服务保障可以没有，因此需要先查询此商店下是否有服务保障，如果没有不取*/
	        	int syServDicCount = 0;
	        	List<SyServDic> syServDicList = new ArrayList<SyServDic>();
	        	System.out.println("itemsTrans ===== gdItemListInfo is ="+gdItemListInfo.getShopId());
	        	syServDicCount = sqlSessionTemplate.selectOne(
	        			"com.tmount.db.product.dao.SyServDicMapper.selectCountByShopId",gdItemListInfo.getShopId());
	        	if(syServDicCount!=0)
	        	{
		        	syServDicList = sqlSessionTemplate.selectList(
		        			"com.tmount.db.product.dao.SyServDicMapper.selectSyServDicByShopId",
		        			gdItemListInfo.getShopId());
		        	gdItemListInfo.setSyServDicList(syServDicList);
	        	}
	        	System.out.println("itemsTrans ===== syServDicCount is ="+syServDicCount);
	        	System.out.println("itemsTrans ===== itemsid is ="+gdItemListInfo.getItemsId());
	        	/*取出产品对应的属性信息*/
	        	GdItemsExpRice gdItemsExpRice = sqlSessionTemplate.selectOne("com.tmount.db.product.dao.GdItemsExpRiceMapper.selectByPrimaryKey", gdItemsInfoToRedis.getItemsId());
	        	System.out.println("itemsTrans ===== brand is ="+gdItemsExpRice.getBrand());
	        	/*取出商品对应的目录结构信息*/
	        	List<GdItemsTypeFlat> gdItemsTypeFlatList = sqlSessionTemplate.selectList("com.tmount.db.product.dao.GdItemsTypeFlatMapper.selectByItemId",gdItemsInfoToRedis.getItemsId());
	        	System.out.println("itemsTrans ===== catelog is ="+gdItemsTypeFlatList.size());
	        	/*开始拼接商品搜索key字符串*/
	        	/*判定当前商品是否在redis中已经存在，如果存在，则先删除，后增加，只删除搜索key即可，其余都已商品id为索引会重复的更新*/
	        	Set<String> keysSet = jedis.keys("pId:"+gdItemListInfo.getItemsId()+"*");
	        	if(keysSet.size()!=0)/*不等于0证明存在此商品id的数据，需要删除*/
	        	{
	        		Iterator<String> keyIterator = keysSet.iterator();
	    	        while(keyIterator.hasNext())
	    	        {
	    	        	String itemDelKey = keyIterator.next();
	    	        	jedis.del(itemDelKey);
	    	        }
	        	}
	        	/*删除已经存在商品数据结束*/
	        	/*商品id*/
	        	itemsKeyStr = "pId:"+gdItemListInfo.getItemsId();
	        	/*商店id*/
	        	itemsKeyStr = itemsKeyStr+"-"+"shopId:"+gdItemListInfo.getShopId();
	        	/*属性*/
	        	itemsKeyStr = itemsKeyStr+"-"+"packId:"+gdItemsExpRice.getPackId();
	        	itemsKeyStr = itemsKeyStr+"-"+"qualityId:"+gdItemsExpRice.getQualityId();
	        	itemsKeyStr = itemsKeyStr+"-"+"standId:"+gdItemsExpRice.getStandId();
	        	itemsKeyStr = itemsKeyStr+"-"+"textureId:"+gdItemsExpRice.getTextureId();
	        	/*商城目录*/
	        	itemsKeyStr = itemsKeyStr+"-"+"cid:";
	        	Iterator<GdItemsTypeFlat> gdItemsTypeFlatListIterator = gdItemsTypeFlatList.iterator();
	        	while(gdItemsTypeFlatListIterator.hasNext())
	        	{
	        		GdItemsTypeFlat gdItemsTypeFlat = gdItemsTypeFlatListIterator.next();
	        		itemsKeyStr = itemsKeyStr+gdItemsTypeFlat.getItemsType()+":";
	        	}
	        	/*商店目录*/
	        	itemsKeyStr = itemsKeyStr+"-"+"csid:";
	        	List<GdItemstypeDicInfo> gdItemstypeDicInfoList = sqlSessionTemplate.selectList("com.tmount.db.product.dao.GdShopItemstypeDicMapper.selectItemTypeListByShopItemId",gdItemsInfoToRedis);
	        	System.out.println("itemsTrans ===== shop catelog is ="+gdItemstypeDicInfoList.size());
	        	Iterator<GdItemstypeDicInfo> gdItemstypeDicInfoListIterator = gdItemstypeDicInfoList.iterator();
	        	while(gdItemstypeDicInfoListIterator.hasNext())
	        	{
	        		GdItemstypeDicInfo gdItemstypeDicInfo = gdItemstypeDicInfoListIterator.next();
	        		if(gdItemstypeDicInfo.getParentId()!=-1)
	        			itemsKeyStr = itemsKeyStr+gdItemstypeDicInfo.getParentId()+":";
	        		itemsKeyStr = itemsKeyStr+gdItemstypeDicInfo.getItemsType()+":";
	        	}
	        	/*省份、城市代码*/
	        	itemsKeyStr = itemsKeyStr+"-"+"Pcode:"+gdItemListInfo.getProvinceCode();
	        	itemsKeyStr = itemsKeyStr+"-"+"Ccode:"+gdItemListInfo.getCityCode();
	        	/*免邮标识*/
	        	itemsKeyStr = itemsKeyStr+"-"+"Dflag:"+gdItemListInfo.getDeliverFlag();
	        	/*商品描述信息*/
	        	itemsKeyStr = itemsKeyStr+"-"+"keyword:"+gdItemListInfo.getItemsName();
	        	/*开始拼接商品搜索value字符串*/
	        	ObjectMapper objectMapper = new ObjectMapper();
	        	itemsValueStr = gdItemListInfo.getItemsId().toString();
	        	jedis.set(itemsKeyStr, itemsValueStr);
	        	System.out.println("itemsTrans ===== itemsKeyStr is =="+itemsKeyStr);
	        	System.out.println("itemsTrans ===== itemsValueStr is =="+itemsValueStr);
	        	
	        	String redisJsonKey="";
	        	String redisJsonValue="";
	        	/*商品列表信息*/
	        	redisJsonKey = "Jitem_"+gdItemListInfo.getItemsId();
	        	redisJsonValue = objectMapper.writeValueAsString(gdItemListInfo);
	        	/*将商品对应的列表信息json串压入redis*/
	        	jedis.set(redisJsonKey, redisJsonValue);
	        	
	        	String redisDetailJsonKey="";
	        	String redisDetailJsonValue="";
	        	/*获取商品详细信息*/
	        	GdItemDetailInfo gdItemDetailInfo = sqlSessionTemplate.selectOne("com.tmount.db.product.dao.GdItemsInfoMapper.selectItemDetail", gdItemsInfoToRedis.getItemsId());
	        	redisDetailJsonKey="JItemDetail_"+gdItemDetailInfo.getItemsId();
	        	if(syServDicCount!=0)
	        		gdItemDetailInfo.setSyServDicList(syServDicList);
	        	redisDetailJsonValue = objectMapper.writeValueAsString(gdItemDetailInfo);
	        	/*将商品对应的详细信息json串压入redis*/
	        	jedis.set(redisDetailJsonKey, redisDetailJsonValue);
	        	/*开始插入排序依据到rendis中*/
	        	/*价格*/
	        	jedis.set("item_price_"+gdItemsInfoToRedis.getItemsId(),gdItemsInfoToRedis.getPrice().toString());
	        	/*热度*/
	        	jedis.set("item_order_"+gdItemsInfoToRedis.getItemsId(),gdItemsInfoToRedis.getOrderValue().toString());
	        	/*销量*/
	        	jedis.set("item_scount_"+gdItemsInfoToRedis.getItemsId(),gdItemsInfoToRedis.getSaleCount().toString());
	        	GdShopInfo gdShopInfo = sqlSessionTemplate.selectOne(
	        			"com.tmount.db.product.dao.GdShopInfoMapper.selectByPrimaryKey", gdItemsInfoToRedis.getShopId());
	        	System.out.println("itemsTrans ===== shop gdShopInfo is ="+gdShopInfo.getShopName());
	        	/*商店等级*/
	        	jedis.set("item_slevel_"+gdItemsInfoToRedis.getItemsId(),gdShopInfo.getShopLevel().toString());
	        	System.out.println("itemsTrans ===== shop gdItemListInfo.getItemsName() is ="+gdItemListInfo.getItemsName());
	        }
			/*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
			sysAmountDic.setAmountDate(dbTime.getDbTime());
			sysAmountDic.setStartStatus(0);
	        int updDone=setSysAmount(sysAmountDic,0);
	    	System.out.println("itemsTrans updDone is "+updDone);
	    	Long endT=System.currentTimeMillis();
	    	System.out.println("itemsTrans ===== deal count is "+GdItemsInfoList.size());
	    	System.out.println("itemsTrans ===== run time is "+(endT-startT)/1000.0+" seconds");

	        redisClientTemplate.closeConnection(jedis);
		}catch(Exception e) {   
			e.printStackTrace();
		}finally{
			/*测试使用
			 * Set rediskeys = jedis.keys("pId*");
			Iterator redisIterator = rediskeys.iterator();
			while(redisIterator.hasNext())
			{
				System.out.println("redis get is "+redisIterator.next());
			}*/
			redisClientTemplate.closeConnection(jedis);
		}
	}
	/*属性信息同步redis*/
	public void propTrans()
	{
		Jedis jedis = null;
		/*取程序开始执行时间*/
		Long startT=System.currentTimeMillis();
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*先要从sys_amount_dic表中取出itemsTrans上次提取时间*/
		SysAmountDic sysAmountDic = getSysAmount("propTrans");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic==null)
		{
			return;
		}
		try{
			/*获取jedis对象*/
			jedis = redisClientTemplate.getConnection();
        	/*添加属性信息*/
        	/*提取属性集*/
        	SyNormalDicsKey syNormalDicsKey = new SyNormalDicsKey();
        	List<SyNormalDicsInfo> syNormalDicsInfoList = new ArrayList<SyNormalDicsInfo>();
	        syNormalDicsKey.setTableName("gd_items_exp_rice");
	        List<SyNormalDicsInfo> syNormalListByKey = sqlSessionTemplate.selectList("com.tmount.db.product.dao.SyNormalDicsMapper.selectItemsExpByPrimaryKey",syNormalDicsKey);
	        /*循环获取属性集下各枚举值*/
	        Iterator<SyNormalDicsInfo> syNormalDicsIterator = syNormalListByKey.iterator();
	        while(syNormalDicsIterator.hasNext())
	        {
	        	SyNormalDicsInfo syNormalDicsInfo = syNormalDicsIterator.next();
	        	syNormalDicsKey.setColumnName(syNormalDicsInfo.getColumnName());
	        	List<SyNormalDicsList> syNormalDicsList = sqlSessionTemplate.selectList("com.tmount.db.product.dao.SyNormalDicsMapper.selectDicsByColumnName",syNormalDicsKey);
	        	syNormalDicsInfo.setSyNormalDicsList(syNormalDicsList);
	        	syNormalDicsInfoList.add(syNormalDicsInfo);
	        }
	        ObjectMapper objectMapper = new ObjectMapper();
	        String redisPropValue = objectMapper.writeValueAsString(syNormalDicsInfoList);
	        jedis.set("propKey", redisPropValue);
	        System.out.println("propTrans ===== propKey-value =="+redisPropValue);
	        /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
	        sysAmountDic.setAmountDate(dbTime.getDbTime());
	        sysAmountDic.setStartStatus(0);
	        int updDone=setSysAmount(sysAmountDic,0);
	    	System.out.println("propTrans updDone is "+updDone);
	        Long endT=System.currentTimeMillis();
	        System.out.println("propTrans ===== deal count is "+syNormalDicsInfoList.size());
	    	System.out.println("propTrans ===== run time is "+(endT-startT)/1000.0+" seconds");
		}catch(Exception e) {   
			e.printStackTrace();
		}finally{
			redisClientTemplate.closeConnection(jedis);
		}
	}
	
	/*商店运费模板信息同步redis*/
	public void shopDeliverTrans()
	{
		Jedis jedis = null;
		/*取程序开始执行时间*/
		Long startT=System.currentTimeMillis();
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*先要从sys_amount_dic表中取出itemsTrans上次提取时间*/
		SysAmountDic sysAmountDic = getSysAmount("shopDeliverTrans");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic==null)
		{
			return;
		}
		/*设置起始时间*/
		StatisTime statisTime = new StatisTime();
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		try{
			/*获取jedis对象*/
			jedis = redisClientTemplate.getConnection();
			ObjectMapper objectMapper = new ObjectMapper();
	        /*提取修改的模板列表*/
			List<GdShopDeliver> gdShopDeliverList = sqlSessionTemplate.selectList("com.tmount.db.product.dao.GdShopDeliverMapper.selectListByUpdTime",statisTime);
		    Iterator<GdShopDeliver> gdShopDeliverIterator = gdShopDeliverList.iterator();
		    while(gdShopDeliverIterator.hasNext())
		    {
		    	Map<String,String> cityDeliverMap = new HashMap<String,String>();
		    	GdShopDeliver gdShopDeliver = gdShopDeliverIterator.next();
		    	/*每个模板取出后，在取出该模板对应的区域*/
		    	List<Integer> cityList = sqlSessionTemplate.selectList(
		    		"com.tmount.db.product.dao.GdShopDeliverDetailMapper.selectCityCodeByDeliverId",gdShopDeliver);
		    	Iterator<Integer> cityIterator = cityList.iterator();
		    	while(cityIterator.hasNext())
		    	{
		    		Integer cityCode = cityIterator.next();
		    		/*取出每个区域对应的运送类型*/
		    		GdShopDeliverDetail gdShopDeliverDetail = new GdShopDeliverDetail();
		    		gdShopDeliverDetail.setShopId(gdShopDeliver.getShopId());
		    		gdShopDeliverDetail.setDeliverId(gdShopDeliver.getDeliverId());
		    		gdShopDeliverDetail.setCityCode(cityCode);
		    		List<GdItemDeliverInfo> gdItemDeliverInfoList = new ArrayList<GdItemDeliverInfo>();
		    		gdItemDeliverInfoList = sqlSessionTemplate.selectList(
				    	"com.tmount.db.product.dao.GdShopDeliverDetailMapper.selectByCityCode",gdShopDeliverDetail);
		    		String deliverInfoJson = objectMapper.writeValueAsString(gdItemDeliverInfoList);
		    		cityDeliverMap.put(cityCode.toString(), deliverInfoJson);
		    	}
		    	System.out.println("shopDeliverTrans ===== gdShopDeliver.getShopId() is "+gdShopDeliver.getShopId()+
		    			" gdShopDeliver.getDeliverId() is "+gdShopDeliver.getDeliverId()+" cityList.size() is "+cityList.size());
		    	String deliverKey = "deliver:"+gdShopDeliver.getShopId().toString()+":"+gdShopDeliver.getDeliverId().toString();
		    	if(cityDeliverMap.size()!=0)
		    		jedis.hmset(deliverKey, cityDeliverMap);
		    }
		    /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
	        sysAmountDic.setAmountDate(dbTime.getDbTime());
	        sysAmountDic.setStartStatus(0);
	        int updDone=setSysAmount(sysAmountDic,0);
	    	System.out.println("shopDeliverTrans updDone is "+updDone);
	    	Long endT=System.currentTimeMillis();
	    	System.out.println("shopDeliverTrans ===== deal count is "+gdShopDeliverList.size());
	    	System.out.println("shopDeliverTrans ===== shopDeliverTrans end-run time is "+(endT-startT)/1000.0+" seconds");
		}catch(Exception e) {   
			e.printStackTrace();
		}finally{
			redisClientTemplate.closeConnection(jedis);
		}
	}
	
	/*从redis中删除已经失效的商品数据*/
	public void itemsDataDel()
	{
		Jedis jedis = null;
		/*取程序开始执行时间*/
		Long startT=System.currentTimeMillis();
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*先要从sys_amount_dic表中取出itemsTrans上次提取时间*/
		SysAmountDic sysAmountDic = getSysAmount("itemsDataDel");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic==null)
		{
			return;
		}
		try{
			/*获取jedis对象*/
			jedis = redisClientTemplate.getConnection();
			List<GdItemsInfo> GdItemsInfoList = sqlSessionTemplate
					.selectList("com.tmount.db.product.dao.GdItemsInfoMapper.selectDelItemList");
	        /*循环获取属性集下各枚举值*/
	        Iterator<GdItemsInfo> itemsDelIterator = GdItemsInfoList.iterator();
	        while(itemsDelIterator.hasNext())
	        {
	        	GdItemsInfo gdItemsInfo = itemsDelIterator.next();
	        	/*开始删除数据*/
	        	/*删除搜索key，搜索key删除后，此商品不会再被检索到*/
	        	Set<String> keysSet = jedis.keys("pId:"+gdItemsInfo.getItemsId()+"*");
	        	if(keysSet.size()!=0)/*不等于0证明存在此商品id的数据，需要删除*/
	        	{
	        		Iterator<String> keyIterator = keysSet.iterator();
	    	        while(keyIterator.hasNext())
	    	        {
	    	        	String itemDelKey = keyIterator.next();
	    	        	jedis.del(itemDelKey);
	    	        }
	        	}
	        	/*删除列表信息*/
	        	jedis.del("Jitem_"+gdItemsInfo.getItemsId());
	        	/*删除详细信息*/
	        	jedis.del("JItemDetail_"+gdItemsInfo.getItemsId());
	        	/*删除价格信息*/
	        	jedis.del("item_price_"+gdItemsInfo.getItemsId());
	        	/*删除热度信息*/
	        	jedis.del("item_order_"+gdItemsInfo.getItemsId());
	        	/*删除等级信息*/
	        	jedis.del("item_slevel_"+gdItemsInfo.getItemsId());
	        	/*删除销量信息*/
	        	jedis.del("item_scount_"+gdItemsInfo.getItemsId());
	        }
	        Long endT=System.currentTimeMillis();
	        System.out.println("itemsDataDel ===== deal count is "+GdItemsInfoList.size());
	    	System.out.println("itemsDataDel ===== run time is "+(endT-startT)/1000.0+" seconds");
		}catch(Exception e) {   
			e.printStackTrace();
		}finally{
			redisClientTemplate.closeConnection(jedis);
		}
	}
	
	/*
	 * 完成SysAmountDic表的校验及更新操作
	 * flag为0代表检查表中对应的程序是否启动，如果没有启动则将标识位置为已启动，返回成功
	 * flag为1代表是程序结束，将标识位置为0，正常状态
	 * */
	public SysAmountDic getSysAmount(String proName)
	{
		/*先要从sys_amount_dic表中取出itemsTrans上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey",proName);
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println(proName+" ========================= 有其他进程正在运行，程序退出");
			return null;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		setSysAmount(sysAmountDic,1);
		return sysAmountDic;
	}
	public int setSysAmount(SysAmountDic sysAmountDic,int flag)
	{
		/*修改SysAmountDic表*/
		SysAmountDic sysAmountDicUpd = sysAmountDic;
		sysAmountDicUpd.setStartStatus(flag);
		int updCode = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(sysAmountDic.getAmountCode()+" ========================== updCode is "+updCode);
		return updCode;
	}
}



