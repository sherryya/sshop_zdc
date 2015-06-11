package com.tmount.business.product.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.GdItemsDeliver;
import com.tmount.db.product.dto.GdItemsInfo;
import com.tmount.db.product.dto.GdShopDeliverDetail;
import com.tmount.db.product.vo.GdItemDeliverInfo;
import com.tmount.db.product.vo.GdItemDeliverResultInfo;
import com.tmount.db.product.vo.GdOrderDeliverComputeInfo;
import com.tmount.db.product.vo.GdOrderDeliverInfo;
import com.tmount.db.product.vo.GdOrderDeliverResult;
import com.tmount.exception.ShopException;
import com.tmount.redis.product.dao.GdItemRedis;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取一个商店下订单中的运费信息
 * 
 * @author rendi
 * 
 */
@Controller
public class ShopOrderDeliverGet extends ControllerBase {
	
	private static int FASTPOSTFLAG = 1;
	private static int POSTFLAG = 2;
	private static int EMSFLAG = 3;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GdItemRedis gdItemRedis;

	@RequestMapping(value = "shop.orderdeliver.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		Integer cityCode = ParamData.getInt(requestParam.getBodyNode(), "cityCode",-1);
		List<GdOrderDeliverResult> resultList = new ArrayList<GdOrderDeliverResult>();
		List<GdOrderDeliverInfo> orderList = new ArrayList<GdOrderDeliverInfo>();
		JsonNode orderNode = ParamData.getJsonNode(requestParam.getBodyNode(), "ordermap");
		String orderStr=orderNode.toString();
		orderList=objectMapper.readValue(orderStr, new TypeReference<List<GdOrderDeliverInfo>>(){});
		/*先循环遍历拆解订单信息*/
		Iterator<GdOrderDeliverInfo> orderIterator = orderList.iterator();
        while(orderIterator.hasNext())
        {
        	GdOrderDeliverInfo gdOrderDeliverInfo = orderIterator.next();
        	/*返回订单运费信息对应的对象*/
        	GdOrderDeliverResult gdOrderDeliverResult = new GdOrderDeliverResult();
        	gdOrderDeliverResult.setOrderNo(gdOrderDeliverInfo.getOrderNo());
        	gdOrderDeliverResult.setShopId(gdOrderDeliverInfo.getShopId());
        	
        	Long shopId = gdOrderDeliverInfo.getShopId();
        	/*循环计算该商店下各商品运费*/
        	/*返回信息*/
        	List<GdItemDeliverResultInfo> gdItemDeliverResultInfoList = new ArrayList<GdItemDeliverResultInfo>();
        	/*订单级别中间变量*/
        	/*订单运费构成
        	 * 不使用模板费用=所有不使用模板商品件数*相应运送模式运费（如快递、平邮、EMS）的合计
        	 * 使用模板费用=使用模板商品总重量*商品列表中存在的模板对应的地域运费（如快递、平邮、EMS）的合计
        	 * 订单运费=不使用模板费用+使用模板费用
        	 * */
        	/*每个商店需要初始化如下内容*/
        	Double itemsTotalKG=0.0;/*此订单下商品总重量*/
        	Double noUseModePostFee=0.0;/*不使用模板商品的平邮费用合计*/
        	Double noUseModeFastPostFee=0.0;/*不使用模板商品快递费用合计*/
        	Double noUseModeEmsFee=0.0;/*不使用模板商品EMS费用合计*/
        	/*定义一个set保存模板id,因为如果有多个模板，需要全部计算取出最高值*/
        	HashSet<Long> deliverIdSet = new HashSet<Long>();
        	/*每个订单需要初始化如下内容结束*/
        	List<GdOrderDeliverComputeInfo> ItemsList = gdOrderDeliverInfo.getItemList();
        	/*遍历该订单下的每个商品，将模板id放入set中，计算商品总重量*/
        	Iterator<GdOrderDeliverComputeInfo> ItemsIterator = ItemsList.iterator();
        	while(ItemsIterator.hasNext())
        	{
        		GdOrderDeliverComputeInfo gdOrderDeliverComputeInfo=ItemsIterator.next();
        		/*先取出商品是否免邮，如果免邮则此商品的运费为0，直接进入下一条商品*/
        		GdItemsInfo gdItemsInfo = goodsService.getGdItemsInfoByItemsId(gdOrderDeliverComputeInfo.getItemsId());
        		if(gdItemsInfo.getDeliverFlag().equals("Y"))
        			continue;
        		/*如果传入的商品重量与实际商品id的商品重量不符，则以实际商品重量为准计算运费*/
        		if(gdItemsInfo.getItemsWeight()!=gdOrderDeliverComputeInfo.getItemsWeight())
        			gdOrderDeliverComputeInfo.setItemsWeight(gdItemsInfo.getItemsWeight());
        		GdItemsDeliver gdItemsDeliver=goodsService.getItemsDeleverByItemsId(gdOrderDeliverComputeInfo.getItemsId());
        		if(gdItemsDeliver.getIsUseMode().equals("N"))/*不使用模板*/
        		{
        			/*不使用模板的商品，只需要将配置表中相应的各种运费对应累加即可*/
        			/*商品数量*商品对应运送类型的运费*/
        			noUseModePostFee+=gdOrderDeliverComputeInfo.getItemsCount()*
        					gdItemsDeliver.getPostFee();
        			noUseModeFastPostFee+=gdOrderDeliverComputeInfo.getItemsCount()*
        					gdItemsDeliver.getFastPostFee();
        			noUseModeEmsFee+=gdOrderDeliverComputeInfo.getItemsCount()*
        					gdItemsDeliver.getEmsFee();
        		}else{/*使用模板*/
        			/*在模版对应的城市运费信息找不到的时候，也按照默认价格进行计算*/
        			GdShopDeliverDetail gdShopDeliverDetailTmp = new GdShopDeliverDetail();
        			gdShopDeliverDetailTmp.setShopId(shopId);
        			gdShopDeliverDetailTmp.setDeliverId(gdItemsDeliver.getDeliverId());
        			gdShopDeliverDetailTmp.setCityCode(cityCode);
        			if(goodsService.getGdShopDeliverByCityCode(gdShopDeliverDetailTmp).size()==0){/*证明无模版信息*/
        				/*商品数量*商品对应运送类型的运费*/
            			noUseModePostFee+=gdOrderDeliverComputeInfo.getItemsCount()*
            					gdItemsDeliver.getPostFee();
            			noUseModeFastPostFee+=gdOrderDeliverComputeInfo.getItemsCount()*
            					gdItemsDeliver.getFastPostFee();
            			noUseModeEmsFee+=gdOrderDeliverComputeInfo.getItemsCount()*
            					gdItemsDeliver.getEmsFee();
        			}else{
	        			/*将模板id放入set中，保证模板id不会重复*/
	        			deliverIdSet.add(gdItemsDeliver.getDeliverId());
	        			/*计算商品总重量*/
	        			itemsTotalKG+=gdOrderDeliverComputeInfo.getItemsCount()*gdOrderDeliverComputeInfo.getItemsWeight();
        			}
        		}
        			
        	}
        	System.out.println("noUseModePostFee is ==============================================================="+noUseModePostFee);
        	System.out.println("noUseModeFastPostFee is ==============================================================="+noUseModeFastPostFee);
        	System.out.println("noUseModeEmsFee is ==============================================================="+noUseModeEmsFee);
        	/*订单运费合计计算
        	 * 将无模板费用合计与有模板订单费用合计相加
        	 * */
        	/*计算有模板费用*/
        	Double useModePostFee=0.0;/*平邮费用*/
        	Double useModeFastPostFee=0.0;/*快递费用*/
        	Double useModeEmsFee=0.0;/*EMS费用*/
        	Iterator<Long> deliverIdIterator = deliverIdSet.iterator();
        	while(deliverIdIterator.hasNext())
        	{
        		Long deliverId = deliverIdIterator.next();
        		/*计算该模板下商品的运费*/
        		/*因为上面无模板的金额都是写死的，如postFee，所以模板中的类型也需要对应不同的金额和判断依据
        		 * 因为要取一个订单下不同模板对应的运送类型的交集，所以需要有变量控制
        		 * */
        		/*定义三个判断依据，在进行计算的时候，碰到一个就将一个标识位true*/
        		Boolean deliverPostFlag=false;
        		Boolean deliverFastPostFlag=false;
        		Boolean deliverEmsFlag=false;
        		/*计算模板费用开始，先从redis取，如果为空或失败，从数据库中取*/
        		List<GdItemDeliverInfo> gdItemDeliverInfoList = null;
        		String deliverStrJson = null;
        		Boolean getFlagFromDb = false;
        		/*从redis中获取*/
        		GdShopDeliverDetail gdShopDeliverDetail = new GdShopDeliverDetail();
        		gdShopDeliverDetail.setShopId(shopId);
        		gdShopDeliverDetail.setDeliverId(deliverId);
        		gdShopDeliverDetail.setCityCode(cityCode);
        		/*判断redis是否可用，如果可用，从redis中提取数据*/
        		if(gdItemRedis.checkRedis())
        		{
	        		List<String> deliverStrList = gdItemRedis.getItemDeliverByCityCode(gdShopDeliverDetail);
	        		Iterator<String> strIterator = deliverStrList.iterator();
	        	    while(strIterator.hasNext())
	        	    {
	        	        deliverStrJson = strIterator.next();
	        	    }
        		}
        		/*只有运费信息特殊，当redis中查找不到运费信息时，需要到数据库中进行二次检索*/
        	    if(deliverStrJson != null)
        	    {
        	    	gdItemDeliverInfoList = (List<GdItemDeliverInfo>)objectMapper.readValue(deliverStrJson, new TypeReference<List<GdItemDeliverInfo>>(){});
        	    }else
        	    {
        	        /*如果redis中没有查到，默认到库中进行二次检索*/
        	        getFlagFromDb=true;
        	    }
        	    /*从数据库中获取*/
        	    if(getFlagFromDb)
        		{
        			gdItemDeliverInfoList = goodsService.getGdShopDeliverByCityCode(gdShopDeliverDetail);
        		}
        		Iterator<GdItemDeliverInfo> gdItemDeliverIterator = gdItemDeliverInfoList.iterator();
        	    while(gdItemDeliverIterator.hasNext())
        	    {
        	        Double fee = new Double(0);
        	        GdItemDeliverInfo gdItemDeliverInfo = gdItemDeliverIterator.next();
        	        /*将各种运送类型对应的判断依据改为true*/
        	        if(gdItemDeliverInfo.getDeliverType()==POSTFLAG)
        	        	deliverPostFlag=true;
        	        if(gdItemDeliverInfo.getDeliverType()==FASTPOSTFLAG)
        	        	deliverFastPostFlag=true;
            		if(gdItemDeliverInfo.getDeliverType()==EMSFLAG)	
            			deliverEmsFlag=true;
        	        if(itemsTotalKG<=gdItemDeliverInfo.getFirstCount())/*如果总重量小于模板中配置的首重*/
        	        {
        	        	fee=gdItemDeliverInfo.getFirstMoney();
        	        }else
        	        {
        	        	/*首重价格+(总重量-首重)/每增加重量*每增加价格*/
        	        	fee+= gdItemDeliverInfo.getFirstMoney();
        	        	if((itemsTotalKG-gdItemDeliverInfo.getFirstCount())%gdItemDeliverInfo.getAddCount()==0)
        	        		fee+=((itemsTotalKG-gdItemDeliverInfo.getFirstCount())/gdItemDeliverInfo.getAddCount())*
        	        				gdItemDeliverInfo.getAddMoney();
        	        	else
        	        		fee+=((itemsTotalKG-gdItemDeliverInfo.getFirstCount())/gdItemDeliverInfo.getAddCount()+1)*
        	        				gdItemDeliverInfo.getAddMoney();
        	        }
        	        /*需要对每一种运送类型做统计，是否有缺失，价格是否最高*/
        	        /*平邮*/
        	        if(gdItemDeliverInfo.getDeliverType()==POSTFLAG)
        	        {
        	        	if(useModePostFee>=0.0 && fee>useModePostFee)
        	        	{
        	        		useModePostFee=fee;
        	        	}
        	        }
        	        /*快递*/
        	        if(gdItemDeliverInfo.getDeliverType()==FASTPOSTFLAG)
        	        {
        	        	if(useModeFastPostFee>=0.0 && fee>useModeFastPostFee)
        	        	{
        	        		useModeFastPostFee=fee;
        	        	}
        	        }
        	        /*EMS*/
            		if(gdItemDeliverInfo.getDeliverType()==EMSFLAG)	
            		{
            			if(useModeEmsFee>=0.0 && fee>useModeEmsFee)
        	        	{
            				useModeEmsFee=fee;
        	        	}
            		}
        	    }
        	    /*每个模版id的各种运送类型全部计算完之后，需要判断下这个模版id下是否全部运送类型都有，如果没有的运送类型，相关运送类型对应的运费不返回前台*/
        	    if(!deliverPostFlag)
        	    	useModePostFee=-1.0;
        	    if(!deliverFastPostFlag)
        	    	useModeFastPostFee=-1.0;
        	    if(!deliverEmsFlag)
        	    	useModeEmsFee=-1.0;
        	}
        	/*将该订单下的金额做统计，然后返回,返回类型应该是List*/
        	/*不论哪个金额只要不小于0，证明需要返回前台压入List中*/
        	if(useModeFastPostFee>=0.0)
        	{
        		GdItemDeliverResultInfo gdItemDeliverResultInfo=new GdItemDeliverResultInfo();
				gdItemDeliverResultInfo.setDeliverType(FASTPOSTFLAG);
    	       	gdItemDeliverResultInfo.setDeliverName("快递费");
    	       	gdItemDeliverResultInfo.setDeliverFee(useModeFastPostFee+noUseModeFastPostFee);
    	       	gdItemDeliverResultInfoList.add(gdItemDeliverResultInfo);
        	}
        	if(useModePostFee>=0.0)
        	{
        		GdItemDeliverResultInfo gdItemDeliverResultInfo=new GdItemDeliverResultInfo();
        		gdItemDeliverResultInfo.setDeliverType(POSTFLAG);
    	       	gdItemDeliverResultInfo.setDeliverName("平邮费");
    	       	gdItemDeliverResultInfo.setDeliverFee(useModePostFee+noUseModePostFee);
    	       	gdItemDeliverResultInfoList.add(gdItemDeliverResultInfo);
        	}
        	if(useModeEmsFee>=0.0)
        	{
        		GdItemDeliverResultInfo gdItemDeliverResultInfo=new GdItemDeliverResultInfo();
        		gdItemDeliverResultInfo.setDeliverType(EMSFLAG);
    	       	gdItemDeliverResultInfo.setDeliverName("EMS费");
    	       	gdItemDeliverResultInfo.setDeliverFee(useModeEmsFee+noUseModeEmsFee);
    	       	gdItemDeliverResultInfoList.add(gdItemDeliverResultInfo);
        	}
        	gdOrderDeliverResult.setDeliverList(gdItemDeliverResultInfoList);
        	resultList.add(gdOrderDeliverResult);
        }
        responseBodyJson.writeFieldName("deliverResult");
		objectMapper.writeValue(responseBodyJson, resultList);
	}
}

