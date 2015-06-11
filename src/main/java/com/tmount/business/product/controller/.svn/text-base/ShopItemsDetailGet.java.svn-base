package com.tmount.business.product.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.SyServDic;
import com.tmount.db.product.vo.GdItemDetailInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.redis.product.dao.GdItemRedis;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 检索获取商品的详细信息
 * 
 * @author rendi
 * 
 */
@Controller
public class ShopItemsDetailGet extends ControllerBase {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GdItemRedis gdItemRedis;

	@RequestMapping(value = "shop.itemsdetail.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		
		Long itemId = ParamData.getLong(requestParam.getBodyNode(), "itemsId");
		GdItemDetailInfo gdItemDetailInfo = new GdItemDetailInfo();
		Logger logger = Logger.getLogger(ShopItemsDetailGet.class);
		ObjectMapper objectMapper = new ObjectMapper();
		logger.debug("items  browse count add ="+itemId+"=");
		/*先获取redis中的数据，如果redis服务器出现故障在从数据库中获取*/
		/*只要redis无故障，或者是数据库系统表中没有定义不读取redis，就取redis的数据*/
		Boolean cRedis = gdItemRedis.checkRedis();
		if(cRedis)
		{
	        String redisItemDetailValue = gdItemRedis.getItemDetailValue(itemId);
	        if(redisItemDetailValue!=null)
	        	gdItemDetailInfo = objectMapper.readValue(redisItemDetailValue, GdItemDetailInfo.class);
	        else
	        	cRedis=false;
		}
		/*数据从数据库中获取*/
		else
		{
			/*通过商品id，获取商品的详细信息、商店相关信息及服务保障相关信息*/
			gdItemDetailInfo = goodsService.getItemsDetailByItemId(new Long(itemId));
	        /*通过商店id获取服务保障相关信息*/
	        List<SyServDic> syServDicList = goodsService.getSyServDicByShopId(gdItemDetailInfo.getShopId());
	        gdItemDetailInfo.setSyServDicList(syServDicList);
		}    
        if ((gdItemDetailInfo.getShopId()==null?0:gdItemDetailInfo.getShopId())== 0) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ITEM, new Object[]{""});
		}
        responseBodyJson.writeFieldName("itemNode");
        objectMapper.writeValue(responseBodyJson, gdItemDetailInfo);
	}
}
