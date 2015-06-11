package com.tmount.business.product.controller;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.GdItemsInfo;
import com.tmount.db.product.vo.GdItemInputInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 商店内商品人气检索，按照商品人气排序，返回指定的前N条信息
 * 
 * @author rendi
 * 
 */
@Controller
public class ShopItemsCollectGet extends ControllerBase {
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "shop.itemscollect.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		
		Long shopId = ParamData.getLong(requestParam.getBodyNode(), "shopId");
		Integer getLimit = ParamData.getInt(requestParam.getBodyNode(), "limitCount",5);
		
		GdItemInputInfo gdItemInputInfo = new GdItemInputInfo();
		gdItemInputInfo.setShopId(shopId);
		gdItemInputInfo.setPageSize(getLimit);
		responseBodyJson.writeArrayFieldStart("itemNode");
		/*获取商店内人气排行信息*/
		List<GdItemsInfo> gdItemList = goodsService.getItemsByCollect(gdItemInputInfo);
		Iterator<GdItemsInfo> gdItemIterator = gdItemList.iterator();
        while(gdItemIterator.hasNext())
        {
        	GdItemsInfo gdItemsInfo = gdItemIterator.next();
        	responseBodyJson.writeStartObject();
        	responseBodyJson.writeNumberField("itemsId", gdItemsInfo.getItemsId());
        	responseBodyJson.writeNumberField("smallPic", gdItemsInfo.getSmallPic());
        	responseBodyJson.writeStringField("itemsName", gdItemsInfo.getItemsName());
        	responseBodyJson.writeNumberField("collectCount", gdItemsInfo.getCollectCout());
        	responseBodyJson.writeNumberField("price", gdItemsInfo.getPrice());
        	responseBodyJson.writeEndObject();
        }
        responseBodyJson.writeEndArray();
        if (gdItemList.size() == 0) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_FOUND_ITEM, new Object[]{gdItemInputInfo.getItemsName()});
		}
	}
}

