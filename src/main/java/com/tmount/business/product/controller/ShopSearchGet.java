package com.tmount.business.product.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wltea.analyzer.processor.IKProcessor;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.SyServDic;
import com.tmount.db.product.vo.GdShopInfoExpl;
import com.tmount.exception.ShopException;
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
public class ShopSearchGet extends ControllerBase {
	
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.search.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		
		
		Integer shopCount = 0;
		String keyWord = ParamData.getString(requestParam.getBodyNode(), "keyword","%");
		/*每页条数*/
		Integer pageSize = ParamData.getInt(requestParam.getBodyNode(), "pageSize",10);
		/*起始页数*/
		Integer pageNo = ParamData.getInt(requestParam.getBodyNode(), "pageNo",1);
		
		Map<String,Object> shopMap = new HashMap<String,Object>();
		shopMap.put("start", (pageNo-1)*pageSize);
		shopMap.put("end", pageSize);
		String keyForDataBase = "%";
		IKProcessor  processor = new IKProcessor();
		ArrayList<String> wordList= processor.analyze(keyWord,true);
		Iterator<String> wordIterator = wordList.iterator();
        while(wordIterator.hasNext())
        {
        	String wordT = wordIterator.next();
        	keyForDataBase = keyForDataBase + wordT + "%";
        }
        System.out.println("-------data ========"+keyForDataBase);
        ObjectMapper objectMapper = new ObjectMapper();
		
		/*数据从数据库中获取*/
		shopMap.put("shopName", keyForDataBase);
		/*获取本次检索满足条件的记录数*/
		shopCount = goodsService.selectShopListCountByName(shopMap);
		responseBodyJson.writeNumberField("shopTotal",shopCount);
		responseBodyJson.writeArrayFieldStart("shopNode");
		/*通过商品名称的模糊查询后，获取商品的列表信息、商店相关信息及服务保障相关信息*/
		List<GdShopInfoExpl> gdShopList = goodsService.selectShopListByName(shopMap);
		Iterator<GdShopInfoExpl> gdShopIterator = gdShopList.iterator();
        while(gdShopIterator.hasNext())
        {
        	GdShopInfoExpl gdShopListInfo = gdShopIterator.next();
        	responseBodyJson.writeStartObject();
        	responseBodyJson.writeNumberField("shopId",gdShopListInfo.getShopId());
        	responseBodyJson.writeStringField("shopName",gdShopListInfo.getShopName());
        	responseBodyJson.writeStringField("shopIntro",gdShopListInfo.getShopIntro());
        	responseBodyJson.writeStringField("cityName",gdShopListInfo.getCityName());
        	responseBodyJson.writeNumberField("spicId",gdShopListInfo.getScanPic());
        	responseBodyJson.writeStringField("httpUrl",gdShopListInfo.getHttpUrl());
        	responseBodyJson.writeStringField("webUrl",gdShopListInfo.getWebUrl());
        	System.out.println("gdShopListInfo.getLevelBad() is =================="+gdShopListInfo.getLevelBad());
        	if(gdShopListInfo.getLevelBad()==0 && gdShopListInfo.getLevelGood()==0 && gdShopListInfo.getLevelNomer()==0)
        		responseBodyJson.writeNumberField("goodCom",0);
        	else
        		responseBodyJson.writeNumberField("goodCom",gdShopListInfo.getLevelGood()/gdShopListInfo.getLevelBad()
        				+gdShopListInfo.getLevelGood()+gdShopListInfo.getLevelNomer());
        	responseBodyJson.writeFieldName("servList");
        	/*通过商店id获取服务保障相关信息*/
        	List<SyServDic> syServDicList = goodsService.getSyServDicByShopId(gdShopListInfo.getShopId());
        	objectMapper.writeValue(responseBodyJson,syServDicList);
        	responseBodyJson.writeEndObject();
        }
        responseBodyJson.writeEndArray();
	}
}
