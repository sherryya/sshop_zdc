package com.tmount.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tmount.db.order.vo.ReUserOrderStatis;
import com.tmount.db.product.dto.GdItemsInfo;
import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.db.sys.dto.SySearchWord;
import com.tmount.db.sys.dto.SysAmountDic;
import com.tmount.db.sys.vo.StatisTime;
import com.tmount.db.user.vo.UsUserStatis;


public class StatisDataTools {
	private ApplicationContext context = null;
	private SqlSessionTemplate sqlSessionTemplate = null;
	
	public StatisDataTools()
	{
		context = new ClassPathXmlApplicationContext("springAppContext.xml");  
		/*获取mybatis工厂类*/
		sqlSessionTemplate = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
	}
	
	public static void main(String[] args) {
		StatisDataTools statisTools = new StatisDataTools();
		
		if(args[0].equals("0"))	{/*销量统计*/
			statisTools.saleCountS();
		}else if(args[0].equals("1")){/*30天销量统计*/
			statisTools.saleCount30S();
		}else if(args[0].equals("2")){/*统计评论数及评分总值*/
			statisTools.commentS();
		}else if(args[0].equals("3")){/*统计人气-收藏*/
			statisTools.favouriteS();
		}else if(args[0].equals("4")){/*统计商店评价*/
			statisTools.shopCommentS();
		}else if(args[0].equals("5")){/*统计商品浏览量*/
			statisTools.browserCountS(args[1]);
		}else if(args[0].equals("6")){/*统计商品热度*/
			statisTools.itemsOrderS();
		}else if(args[0].equals("7")){/*统计热词商品数量*/
			statisTools.searchWordItemsCountS();
		}else
		{
			System.out.println("您输入的入参不合规范!!!!!!!"+args[0]);
		}
	}
	
	/*统计销量*/
	public void saleCountS()
	{
		Long startT=System.currentTimeMillis();
		System.out.println("saleCountS start------------------------------------------");
		StatisTime statisTime = new StatisTime();
		/*先要从sys_amount_dic表中取出saleCountS上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey","saleCountS");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println("saleCountS 有其他进程正在运行，程序退出");
			return;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		SysAmountDic sysAmountDicUpd = new SysAmountDic();
		sysAmountDicUpd.setAmountCode(sysAmountDic.getAmountCode());
		sysAmountDicUpd.setStartStatus(1);
		int updStart = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(" saleCountS updStart is "+updStart);
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*设置起始时间*/
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		/*提取订单数据*/
		List<ReUserOrderStatis> reUserOrderStatisList = sqlSessionTemplate
				.selectList("com.tmount.db.order.dao.ReUserOrderDetailMapper.selectByOrderTimeForStatis",statisTime);
		Iterator<ReUserOrderStatis> reUserOrderStatisIterator = reUserOrderStatisList.iterator();
        while(reUserOrderStatisIterator.hasNext())
        {
        	ReUserOrderStatis reUserOrderStatis = reUserOrderStatisIterator.next();
        	GdItemsInfo gdItemsInfo = new GdItemsInfo();
        	/*赋值商品id以及商品数量*/
        	gdItemsInfo.setItemsId(reUserOrderStatis.getItemsId());
        	gdItemsInfo.setSaleCount(reUserOrderStatis.getAcount());
        	int res=sqlSessionTemplate.update("com.tmount.db.product.dao.GdItemsInfoMapper.updateSaleCountForStatis",gdItemsInfo);
        	System.out.println("saleCountS res is "+res);
        }
        /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
        sysAmountDicUpd.setAmountDate(dbTime.getDbTime());
        sysAmountDicUpd.setStartStatus(0);
        int updDone=sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
    	System.out.println("saleCountS updDone is "+updDone);
    	Long endT=System.currentTimeMillis();
    	System.out.println("saleCountS end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
	}
	
	/*统计30天销量*/
	public void saleCount30S()
	{
		Long startT=System.currentTimeMillis();
		System.out.println("saleCount30S start------------------------------------------");
		StatisTime statisTime = new StatisTime();
		/*先要从sys_amount_dic表中取出saleCount30S上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey","saleCount30S");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println("saleCount30S 有其他进程正在运行，程序退出");
			return;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		SysAmountDic sysAmountDicUpd = new SysAmountDic();
		sysAmountDicUpd.setAmountCode(sysAmountDic.getAmountCode());
		sysAmountDicUpd.setStartStatus(1);
		int updStart = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(" saleCount30S updStart is "+updStart);
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*设置起始时间*/
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		/*提取订单数据*/
		List<ReUserOrderStatis> reUserOrderStatisList = sqlSessionTemplate
				.selectList("com.tmount.db.order.dao.ReUserOrderDetailMapper.selectByOrderTimeForStatis",statisTime);
		Iterator<ReUserOrderStatis> reUserOrderStatisIterator = reUserOrderStatisList.iterator();
        while(reUserOrderStatisIterator.hasNext())
        {
        	ReUserOrderStatis reUserOrderStatis = reUserOrderStatisIterator.next();
        	GdItemsInfo gdItemsInfo = new GdItemsInfo();
        	/*赋值商品id以及商品数量*/
        	gdItemsInfo.setItemsId(reUserOrderStatis.getItemsId());
        	/*统计此商品id30天内的销售数量*/
        	int sales30Count=0;
        	statisTime.setItemsId(reUserOrderStatis.getItemsId());
        	sales30Count=sqlSessionTemplate
    				.selectOne("com.tmount.db.order.dao.ReUserOrderDetailMapper.selectAcountByDays",statisTime);
        	/*赋值对象30天销售数量*/
        	gdItemsInfo.setDiscuss30Count(sales30Count);
        	/*修改商品30天销售数量*/
        	int res=sqlSessionTemplate.
        			update("com.tmount.db.product.dao.GdItemsInfoMapper.updateByPrimaryKeySelective",gdItemsInfo);
        	System.out.println("saleCount30S res is "+res);
        }
        /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
        sysAmountDicUpd.setAmountDate(dbTime.getDbTime());
        sysAmountDicUpd.setStartStatus(0);
        int updDone=sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
    	System.out.println("saleCount30S updDone is "+updDone);
    	Long endT=System.currentTimeMillis();
    	System.out.println("saleCount30S end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
	}
	
	/*统计评论数量以及评分总值*/
	public void commentS()
	{
		Long startT=System.currentTimeMillis();
		System.out.println("commentS start------------------------------------------");
		StatisTime statisTime = new StatisTime();
		/*先要从sys_amount_dic表中取出commentS上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey","commentS");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println("commentS 有其他进程正在运行，程序退出");
			return;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		SysAmountDic sysAmountDicUpd = new SysAmountDic();
		sysAmountDicUpd.setAmountCode(sysAmountDic.getAmountCode());
		sysAmountDicUpd.setStartStatus(1);
		int updStart = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(" commentS updStart is "+updStart);
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*设置起始时间*/
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		/*提取评论数据*/
		List<UsUserStatis> usUserCommentStatisList = sqlSessionTemplate
				.selectList("com.tmount.db.user.dao.UsUserCommentMapper.selectCommentByTime",statisTime);
		Iterator<UsUserStatis> usUserCommentStatisIterator = usUserCommentStatisList.iterator();
        while(usUserCommentStatisIterator.hasNext())
        {
        	UsUserStatis usUserCommentStatis = usUserCommentStatisIterator.next();
        	GdItemsInfo gdItemsInfo = new GdItemsInfo();
        	/*赋值商品id以及商品数量*/
        	gdItemsInfo.setItemsId(usUserCommentStatis.getItemsId());
        	/*赋值对象评论数量*/
        	gdItemsInfo.setDiscussCount(usUserCommentStatis.getCommentCount());
        	/*赋值评分值*/
        	gdItemsInfo.setCommentValue(usUserCommentStatis.getCommentLevelSum());
        	/*修改商品评论数量*/
        	int res=sqlSessionTemplate.
        			update("com.tmount.db.product.dao.GdItemsInfoMapper.updateDiscussForStatis",gdItemsInfo);
        	System.out.println("commentS res is "+res);
        }
        /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
        sysAmountDicUpd.setAmountDate(dbTime.getDbTime());
        sysAmountDicUpd.setStartStatus(0);
        int updDone=sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
    	System.out.println("commentS updDone is "+updDone);
    	Long endT=System.currentTimeMillis();
    	System.out.println("commentS end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
	}
	
	/*统计人气(收藏)*/
	public void favouriteS()
	{
		Long startT=System.currentTimeMillis();
		System.out.println("favouriteS start------------------------------------------");
		StatisTime statisTime = new StatisTime();
		/*先要从sys_amount_dic表中取出favouriteS上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey","favouriteS");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println("favouriteS 有其他进程正在运行，程序退出");
			return;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		SysAmountDic sysAmountDicUpd = new SysAmountDic();
		sysAmountDicUpd.setAmountCode(sysAmountDic.getAmountCode());
		sysAmountDicUpd.setStartStatus(1);
		int updStart = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(" favouriteS updStart is "+updStart);
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*设置起始时间*/
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		/*提取商品收藏数据*/
		List<UsUserStatis> usFavouriteItemsList = sqlSessionTemplate
				.selectList("com.tmount.db.user.dao.UsFavouriteItemsMapper.selectFavouriteByTime",statisTime);
		System.out.println("usFavouriteItemsList.size()=================="+usFavouriteItemsList.size());
		Iterator<UsUserStatis> UsFavouriteItemsIterator = usFavouriteItemsList.iterator();
        while(UsFavouriteItemsIterator.hasNext())
        {
        	UsUserStatis usFavouriteItems = UsFavouriteItemsIterator.next();
        	GdItemsInfo gdItemsInfo = new GdItemsInfo();
        	/*赋值商品id*/
        	gdItemsInfo.setItemsId(usFavouriteItems.getItemsId());
        	/*赋值收藏数量*/
        	gdItemsInfo.setCommentValue(usFavouriteItems.getFavouriteCount());
        	/*修改商品收藏数量*/
        	int res=sqlSessionTemplate.
        			update("com.tmount.db.product.dao.GdItemsInfoMapper.updateCommentForStatis",gdItemsInfo);
        	System.out.println("favouriteS res is "+res);
        }
        /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
        sysAmountDicUpd.setAmountDate(dbTime.getDbTime());
        sysAmountDicUpd.setStartStatus(0);
        int updDone=sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
    	System.out.println("favouriteS updDone is "+updDone);
    	Long endT=System.currentTimeMillis();
    	System.out.println("favouriteS end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
	}
	
	/*统计商店评价*/
	public void shopCommentS()
	{
		Long startT=System.currentTimeMillis();
		System.out.println("shopCommentS start------------------------------------------");
		StatisTime statisTime = new StatisTime();
		/*先要从sys_amount_dic表中取出shopCommentS上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey","shopCommentS");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println("shopCommentS 有其他进程正在运行，程序退出");
			return;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		SysAmountDic sysAmountDicUpd = new SysAmountDic();
		sysAmountDicUpd.setAmountCode(sysAmountDic.getAmountCode());
		sysAmountDicUpd.setStartStatus(1);
		int updStart = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(" shopCommentS updStart is "+updStart);
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*设置起始时间*/
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		/*提取商店评价数据*/
		List<UsUserStatis> usFavouriteItemsList = sqlSessionTemplate
				.selectList("com.tmount.db.user.dao.UsUserCommentMapper.selectShopCommentByTime",statisTime);
		Iterator<UsUserStatis> UsFavouriteItemsIterator = usFavouriteItemsList.iterator();
        while(UsFavouriteItemsIterator.hasNext())
        {
        	UsUserStatis usFavouriteItems = UsFavouriteItemsIterator.next();
        	GdShopInfo gdShopInfo = new GdShopInfo();
        	/*赋值商品id*/
        	gdShopInfo.setShopId(usFavouriteItems.getShopId());
        	/*赋值评价数据*/
        	/*好评*/
        	if(usFavouriteItems.getCommentLevel()==1)
        		gdShopInfo.setLevelGood(usFavouriteItems.getCommentCount());
        	/*中评*/
        	if(usFavouriteItems.getCommentLevel()==0)
        		gdShopInfo.setLevelNomer(usFavouriteItems.getCommentCount());
        	/*差评*/
        	if(usFavouriteItems.getCommentLevel()==-1)
        		gdShopInfo.setLevelBad(usFavouriteItems.getCommentCount());
        	/*修改商店评价数据*/
        	int res=sqlSessionTemplate.
        			update("com.tmount.db.product.dao.GdShopInfoMapper.updateLevelByPrimaryKey",gdShopInfo);
        	System.out.println("shopCommentS res is "+res);
        }
        /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
        sysAmountDicUpd.setAmountDate(dbTime.getDbTime());
        sysAmountDicUpd.setStartStatus(0);
        int updDone=sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
    	System.out.println("shopCommentS updDone is "+updDone);
    	Long endT=System.currentTimeMillis();
    	System.out.println("shopCommentS end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
	}
	
	
	/*统计商品热度
	 * (销量/最大销量)*0.2+(收藏量/最大收藏量)*0.2+(商品评分/5)*0.3+(浏览量/最大浏览量)*0.2+商店好评率*0.1
	 * */
	public void itemsOrderS()
	{
		Long startT=System.currentTimeMillis();
		System.out.println("itemsOrderS start------------------------------------------");
		StatisTime statisTime = new StatisTime();
		GdItemsInfo gdItemsUpdInfo = new GdItemsInfo();
		java.text.NumberFormat formate = java.text.NumberFormat.getNumberInstance();
		formate.setMaximumFractionDigits(0);//设定小数最大为数   ，那么显示的最后会四舍五入的 
		/*先要从sys_amount_dic表中取出shopCommentS上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey","itemsOrderS");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println("itemsOrderS 有其他进程正在运行，程序退出");
			return;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		SysAmountDic sysAmountDicUpd = new SysAmountDic();
		sysAmountDicUpd.setAmountCode(sysAmountDic.getAmountCode());
		sysAmountDicUpd.setStartStatus(1);
		int updStart = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(" itemsOrderS updStart is "+updStart);
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*设置起始时间*/
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		statisTime.setCountLimit(100000);
		/*获取最大收藏数量及最大销量、最大收藏量*/
		GdItemsInfo gdItemsInfoMax = sqlSessionTemplate
				.selectOne("com.tmount.db.product.dao.GdItemsInfoMapper.selectMaxForStatis");
		/*获取有效商品数量*/
		int itemsCount = sqlSessionTemplate
				.selectOne("com.tmount.db.product.dao.GdItemsInfoMapper.selectItemsCountStatis");
		if(itemsCount<=statisTime.getCountLimit())
			statisTime.setCountLimit(itemsCount);
		if(itemsCount!=0)
		{
			/*支持分部执行，将自定义的count与商品数量做除，循环操作*/
			for(int i=0;i<(itemsCount==statisTime.getCountLimit()?itemsCount/statisTime.getCountLimit():itemsCount/statisTime.getCountLimit()+1);i++)
			{
				statisTime.setStartLimit(i*statisTime.getCountLimit());
				/*提取所有商品数据*/
				List<GdItemsInfo> gdItemsInfoList = sqlSessionTemplate
						.selectList("com.tmount.db.product.dao.GdItemsInfoMapper.selectItemsListForStatis",statisTime);
				Iterator<GdItemsInfo> GdItemsInfoIterator = gdItemsInfoList.iterator();
		        while(GdItemsInfoIterator.hasNext())
		        {
		        	GdItemsInfo gdItemsInfo = GdItemsInfoIterator.next();
		        	/*(销量/最大销量)*0.2*/
		        	Double saleS = 0.0;
		        	if(gdItemsInfoMax.getSaleCount()!=0)
		        		saleS = gdItemsInfo.getSaleCount()/gdItemsInfoMax.getSaleCount() * 0.2;
		        	/*(收藏量/最大收藏量)*0.2*/
		        	Double collectS=0.0;
		        	if(gdItemsInfoMax.getCollectCout()!=0)
		        		collectS = gdItemsInfo.getCollectCout()/gdItemsInfoMax.getCollectCout() *0.2;
		        	/*(浏览量/最大浏览量)*0.2*/
		        	Double browserS = 0.0;
		        	if(gdItemsInfoMax.getBrowserCount()!=0)
		        		browserS = gdItemsInfo.getBrowserCount()/gdItemsInfoMax.getBrowserCount() *0.2;
		        	/*(商品评分/5)*0.3*/
		        	Double commentS = gdItemsInfo.getCommentValue()/5 * 0.3;
		        	/*获取商店评分*/
		        	GdShopInfo gdShopInfo = sqlSessionTemplate
		    				.selectOne("com.tmount.db.product.dao.GdShopInfoMapper.selectByPrimaryKey",gdItemsInfo.getShopId());
		        	
		        	/*商店好评率*0.1*/
		        	gdItemsUpdInfo.setOrderValue(1);
		        	Double shopLeveS = 0.0;
		        	if(gdShopInfo.getLevelGood()==null)
		        		gdShopInfo.setLevelGood(0);
		        	if(gdShopInfo.getLevelBad()==null)
		        		gdShopInfo.setLevelBad(0);
		        	if(gdShopInfo.getLevelNomer()==null)
		        		gdShopInfo.setLevelNomer(0);
		        		
		        	if(gdShopInfo.getLevelGood()!=0 && gdShopInfo.getLevelBad()!=0 && gdShopInfo.getLevelNomer()!=0)
		        		shopLeveS = gdShopInfo.getLevelGood()/(gdShopInfo.getLevelGood()+gdShopInfo.getLevelBad()
		        			+gdShopInfo.getLevelNomer()) * 0.1;
		        	Double orderValue = (saleS+collectS+browserS+commentS+shopLeveS);
		        	gdItemsUpdInfo.setItemsId(gdItemsInfo.getItemsId());
		        	gdItemsUpdInfo.setOrderValue(new Integer(formate.format(orderValue)));
		        	/*修改商店评价数据*/
		        	int res=sqlSessionTemplate.
		        			update("com.tmount.db.product.dao.GdItemsInfoMapper.updateByPrimaryKeySelective",gdItemsUpdInfo);
		        	System.out.println("itemsOrderS res is "+res);
		        }
			}
		}
        /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
        sysAmountDicUpd.setAmountDate(dbTime.getDbTime());
        sysAmountDicUpd.setStartStatus(0);
        int updDone=sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
    	System.out.println("itemsOrderS updDone is "+updDone);
    	Long endT=System.currentTimeMillis();
    	System.out.println("itemsOrderS end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
	}
	
	/*统计商品浏览量
	 * 获取文件，文件中按行获取items_id，每行代表浏览一次
	 * */
	public void browserCountS(String FileDir)
	{
		Long startT=System.currentTimeMillis();
		System.out.println("browserCountS start------------------------------------------");
		StatisTime statisTime = new StatisTime();
		/*先要从sys_amount_dic表中取出shopCommentS上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey","browserCountS");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println("browserCountS 有其他进程正在运行，程序退出");
			return;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		SysAmountDic sysAmountDicUpd = new SysAmountDic();
		sysAmountDicUpd.setAmountCode(sysAmountDic.getAmountCode());
		sysAmountDicUpd.setStartStatus(1);
		int updStart = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(" browserCountS updStart is "+updStart);
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*设置起始时间*/
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		try
		{
			GdItemsInfo gdItemsUpdInfo = new GdItemsInfo();
			File file = new File (FileDir+"/statis.log");
			FileInputStream in=new FileInputStream(file); 
			InputStreamReader inReader=new InputStreamReader(in); 
			BufferedReader bufReader=new BufferedReader(inReader); 
			String data=null;
			while((data = bufReader.readLine())!=null) 
			{
				/*,分割的是商品id以及商品数量*/
				String[] splitStr = data.split(",");
				/*=分割的是商品id       =items_id=*/
				String[] itemsSplitStr = splitStr[0].split("=");
				
				Long itemsId = new Long(itemsSplitStr[1]);
				Integer itemsBrowserCount=new Integer(splitStr[1]);
				System.out.println("--itemsId="+itemsId+"--itemsBrowserCount="+itemsBrowserCount);
				gdItemsUpdInfo.setItemsId(itemsId);
				gdItemsUpdInfo.setBrowserCount(itemsBrowserCount);
				System.out.println("gdItemsUpdInfo="+gdItemsUpdInfo.getItemsId()+"--"+gdItemsUpdInfo.getBrowserCount());
				/*修改商品浏览量数据*/
	        	int res=sqlSessionTemplate.
	        			update("com.tmount.db.product.dao.GdItemsInfoMapper." +
	        					"updateBrowserForStatis",gdItemsUpdInfo);
	        	System.out.println("browserCountS res is "+res);
			} 
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
	        /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
	        sysAmountDicUpd.setAmountDate(dbTime.getDbTime());
	        sysAmountDicUpd.setStartStatus(0);
	        int updDone=sqlSessionTemplate.update(
	        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
	    	System.out.println("browserCountS updDone is "+updDone);
	    	Long endT=System.currentTimeMillis();
	    	System.out.println("browserCountS end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
		}
	}
	
	/*统计热词对应的商品数量*/
	public void searchWordItemsCountS()
	{
		Long startT=System.currentTimeMillis();
		System.out.println("searchWordItemsCountS start------------------------------------------");
		StatisTime statisTime = new StatisTime();
		/*先要从sys_amount_dic表中取出searchWordItemsCountS上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey","searchWordItemsCountS");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println("searchWordItemsCountS 有其他进程正在运行，程序退出");
			return;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		SysAmountDic sysAmountDicUpd = new SysAmountDic();
		sysAmountDicUpd.setAmountCode(sysAmountDic.getAmountCode());
		sysAmountDicUpd.setStartStatus(1);
		int updStart = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(" searchWordItemsCountS updStart is "+updStart);
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*设置起始时间*/
		statisTime.setBeginTime(sysAmountDic.getAmountDate());
		statisTime.setEndTime(dbTime.getDbTime());
		statisTime.setCountLimit(100000);
		/*获取热词个数*/
		int searchWordCount = sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SySearchWordMapper.selectItemListCount",new HashMap<String,Object>());
		if(searchWordCount<=statisTime.getCountLimit())
			statisTime.setCountLimit(searchWordCount);
		if(searchWordCount!=0)
		{
			/*支持分部执行，将自定义的count与商品数量做除，循环操作*/
			for(int i=0;i<(searchWordCount==statisTime.getCountLimit()?searchWordCount/statisTime.getCountLimit():searchWordCount/statisTime.getCountLimit()+1);i++)
			{
				statisTime.setStartLimit(i*statisTime.getCountLimit());
				/*提取搜索词数据*/
				List<SySearchWord> sySearchWordList = sqlSessionTemplate
						.selectList("com.tmount.db.sys.dao.SySearchWordMapper.selectItemListByLimit",statisTime);
				Iterator<SySearchWord> sySearchWordIterator = sySearchWordList.iterator();
		        while(sySearchWordIterator.hasNext())
		        {
		        	SySearchWord sySearchWord = sySearchWordIterator.next();
		        	SySearchWord sySearchWordUpd = new SySearchWord();
		        	sySearchWordUpd.setSearchId(sySearchWord.getSearchId());
		        	/*提取匹配此名称的商品数量*/
		        	int itemsCount = sqlSessionTemplate
		    				.selectOne("com.tmount.db.product.dao.GdItemsInfoMapper.selectSearchWordItemCount",sySearchWord.getSearchName());
		        	sySearchWordUpd.setAcount(itemsCount);
		        	System.out.println("searchWordItemsCountS searchname is=="+sySearchWord.getSearchName()+",count is=="+itemsCount);
		        	int updResult = sqlSessionTemplate.update("com.tmount.db.sys.dao.SySearchWordMapper.updateByPrimaryKeySelective"
		        			, sySearchWordUpd);
		        	System.out.println("searchWordItemsCountS res is "+updResult);
		        }
			}
		}
        /*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
        sysAmountDicUpd.setAmountDate(dbTime.getDbTime());
        sysAmountDicUpd.setStartStatus(0);
        int updDone=sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
    	System.out.println("searchWordItemsCountS updDone is "+updDone);
    	Long endT=System.currentTimeMillis();
    	System.out.println("searchWordItemsCountS end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
	}
	
	public void testFunc()
	{
		Integer i=5;
		Integer j=4;
		Double d = 4.46;
		
		java.text.NumberFormat   formate   =   java.text.NumberFormat.getNumberInstance();
		formate.setMaximumFractionDigits(0);//设定小数最大为数   ，那么显示的最后会四舍五入的 
	    String   m   =   formate.format(d); 
		
    	System.out.println("j/i is "+j/i+"===="+d.intValue()+"-----"+m);
	}
	
}



