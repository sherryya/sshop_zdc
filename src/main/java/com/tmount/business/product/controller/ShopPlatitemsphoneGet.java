package com.tmount.business.product.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wltea.analyzer.processor.IKProcessor;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.SyServDic;
import com.tmount.db.product.vo.GdItemInputInfo;
import com.tmount.db.product.vo.GdItemListInfo;
import com.tmount.exception.ShopException;
import com.tmount.redis.product.dao.GdItemRedis;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 检索获取商品的列表信息
 * 
 * @author rendi
 * 
 */
@Controller
public class ShopPlatitemsphoneGet extends ControllerBase {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GdItemRedis gdItemRedis;

	@RequestMapping(value = "shop.platitems.phone.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		/*起止价格*/
		Double priceBegin=ParamData.getDouble(requestParam.getBodyNode(), "priceBegin",-1.0);
		Double priceEnd=ParamData.getDouble(requestParam.getBodyNode(), "priceEnd",-1.0);
		/*每页条数*/
		Integer pageSize = ParamData.getInt(requestParam.getBodyNode(), "pageSize",10);
		/*起始页数*/
		Integer pageNo = ParamData.getInt(requestParam.getBodyNode(), "pageNo",1);
		/*免邮标志*/
		String deliverFlag = ParamData.getString(requestParam.getBodyNode(),"deliverFlag",null);
		/*城市和省份代码*/
		Integer cityCode = ParamData.getInt(requestParam.getBodyNode(), "cityCode",0);
		Integer provinceCode = ParamData.getInt(requestParam.getBodyNode(), "provinceCode",0);
		/*按照目录检索，目录id*/
		Integer itemsType = ParamData.getInt(requestParam.getBodyNode(), "itemsType",0);
		
		/*属性是一个json节点，此节点下是各属性的key-value，需要单独解析
		 * "props":{"pack_id":2,"quality_id:1,"stand_id":2,"pack_id":3}
		 * */
		Integer qualityId = 0;
		Integer standId = 0;
		Integer textureId = 0;
		Integer packId = 0;
		JsonNode jsonNode = ParamData.getJsonNode(requestParam.getBodyNode(), "props",null);
		if(jsonNode!=null)
		{
			/*是否按照属性检索*/
			qualityId = ParamData.getInt(jsonNode, "quality_id",0);
			/*是否按照属性检索*/
			standId = ParamData.getInt(jsonNode, "stand_id",0);
			/*是否按照属性检索*/
			textureId = ParamData.getInt(jsonNode, "texture_id",0);
			/*是否按照属性检索*/
			packId = ParamData.getInt(jsonNode, "pack_id",0);
		}
		
		Integer orderPrice=0;
		Integer orderShopLevel=0;
		Integer orderValue=0;
		Integer orderSaleCount=0;
		
		/*取得排序依据，有热度、商店等级、价格、销量*/
		String orderName = ParamData.getString(requestParam.getBodyNode(),"orderName","order_value");
		if(orderName.equals("price"))
		{
			/*是否按照价格排序*/
			orderPrice = 1;
		}
		if(orderName.equals("shop_level"))
		{
			/*是否按照商店等级排序*/
			orderShopLevel = 1;
		}
		if(orderName.equals("order_value"))
		{
			/*是否按照热度排序*/
			orderValue = 1;
		}
		if(orderName.equals("sale_count"))
		{
			/*是否按照销量排序*/
			orderSaleCount = 1;
		}
		/*排序顺序依据,只有为1 的时候，才是desc倒排序*/
		Integer orderByFlag=0;
		if(ParamData.getString(requestParam.getBodyNode(),"orderValue","desc").equals("desc"))
			orderByFlag=1;
		
		
		String dataFrom = "";
		List<GdItemListInfo> gdItemListInfoForJson = new ArrayList<GdItemListInfo>();
		Long itemCount = null;
		String keyWord = ParamData.getString(requestParam.getBodyNode(), "keyword","*");
		String keyForDataBase = "%";
		String keyForRedis = "*";
		IKProcessor  processor = new IKProcessor();
		ArrayList<String> wordList= processor.analyze(keyWord,true);
		Iterator<String> wordIterator = wordList.iterator();
        while(wordIterator.hasNext())
        {
        	String wordT = wordIterator.next();
        	keyForDataBase = keyForDataBase + wordT + "%";
        	keyForRedis = keyForRedis + wordT + "*";
        }
		
		GdItemInputInfo gdItemInputInfo = new GdItemInputInfo();
		/*对入参进行解析后赋值*/
		if(priceBegin>0.0)
			gdItemInputInfo.setPriceBegin(priceBegin);
		if(priceEnd>0.0)
			gdItemInputInfo.setPriceEnd(priceEnd);
		if(cityCode>0)
			gdItemInputInfo.setCityCode(cityCode);
		if(provinceCode>0)
			gdItemInputInfo.setProvinceCode(provinceCode);
		if(deliverFlag!=null)
			gdItemInputInfo.setDeliverFlag(deliverFlag);
		if(orderPrice>0)
			gdItemInputInfo.setOrderPrice(orderPrice);
		if(orderShopLevel>0)
			gdItemInputInfo.setOrderShopLevel(orderShopLevel);
		if(orderValue>0)
			gdItemInputInfo.setOrderValue(orderValue);
		if(orderSaleCount>0)
			gdItemInputInfo.setOrderSaleCount(orderSaleCount);
		if(qualityId>0)
			gdItemInputInfo.setQualityId(qualityId);
		if(standId>0)
			gdItemInputInfo.setStandId(standId);
		if(textureId>0)
			gdItemInputInfo.setTextureId(textureId);
		if(packId>0)
			gdItemInputInfo.setPackId(packId);
		if(itemsType>0)
			gdItemInputInfo.setItemsType(itemsType);
		gdItemInputInfo.setOrderByFlag(orderByFlag);
		gdItemInputInfo.setStartLimit((pageNo-1)*pageSize);
		gdItemInputInfo.setPageNo(pageNo);
		gdItemInputInfo.setPageSize(pageSize);
		
		ObjectMapper objectMapper = new ObjectMapper();
		/*先获取redis中的数据，如果redis服务器出现故障在从数据库中获取*/
		/*应该在redis中校验，先判断redis是否存在可用连接*/
		Boolean cRedis = gdItemRedis.checkRedis();
		if(cRedis)
		{
			gdItemInputInfo.setItemsName(keyForRedis);
			dataFrom="redis";
			Long keySeq = goodsService.getKeySeqForRedis("keyset");
			String key = "Iset"+keySeq;
			/*通过关键词检索生成set集合*/
			gdItemRedis.createSetByInputInfo(key,gdItemInputInfo);
			/*按排序依据将set集合对应的各商品json串取出*/
			List<String> redisStrList = gdItemRedis.getItemBySort(key,gdItemInputInfo);
			Iterator<String> redisStrIterator = redisStrList.iterator();
	        while(redisStrIterator.hasNext())
	        {
	        	String redisStr = redisStrIterator.next();
	        	GdItemListInfo gdItemListInfo = objectMapper.readValue(redisStr, GdItemListInfo.class);
	        	//redisStrTmp = redisStrTmp + "=-=-=" + redisStr;
	        	gdItemListInfoForJson.add(gdItemListInfo);
	        }
	        itemCount=gdItemRedis.getSetSizeByKey(key);
	        gdItemRedis.delSetByKey(key);
		}
		/*数据从数据库中获取*/
		else
		{
			/*需要将空的*做次转义，转义为%*/
			gdItemInputInfo.setItemsName(keyForDataBase);
			dataFrom="database";
			/*获取本次检索满足条件的记录数*/
			itemCount = new Long(goodsService.getGdItemsCountForSearch(gdItemInputInfo));
			/*通过商品名称的模糊查询后，获取商品的列表信息、商店相关信息及服务保障相关信息*/
			List<GdItemListInfo> gdItemList = goodsService.getGdItemsListForSearch(gdItemInputInfo);
			Iterator<GdItemListInfo> gdItemIterator = gdItemList.iterator();
	        while(gdItemIterator.hasNext())
	        {
	        	GdItemListInfo gdItemListInfo = gdItemIterator.next();
	        	/*通过商店id获取服务保障相关信息*/
	        	List<SyServDic> syServDicList = goodsService.getSyServDicByShopId(gdItemListInfo.getShopId());
	        	gdItemListInfo.setSyServDicList(syServDicList);
	        	/*用一个中间List最后生成JSON串，否则不好在JSON串中追加信息*/
	        	gdItemListInfoForJson.add(gdItemListInfo);
	        }
		}
		responseBodyJson.writeStringField("return_str", cRedis.toString());
        System.out.println("dataFrom is ==================="+dataFrom);
		responseBodyJson.writeNumberField("itemTotal", itemCount);
		responseBodyJson.writeFieldName("itemNode");
		objectMapper.writeValue(responseBodyJson, gdItemListInfoForJson);
	}
}
