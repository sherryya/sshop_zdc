package com.tmount.business.product.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.SyServDic;
import com.tmount.db.product.vo.GdShopInfoExpl;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取商店详细信息
 * 
 * @author rendi
 * 
 */
@Controller
public class ShopGet extends ControllerBase {
	
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		
		Long shopId =ParamData.getLong(requestParam.getBodyNode(),"shopId");
		GdShopInfoExpl gdShopInfoExpl = new GdShopInfoExpl();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		/*通过商品id，获取商品的详细信息、商店相关信息及服务保障相关信息*/
		gdShopInfoExpl = goodsService.getGdShopInfoExplByShoId(shopId);
	    /*通过商店id获取服务保障相关信息*/
	    List<SyServDic> syServDicList = goodsService.getSyServDicByShopId(shopId);

        if (gdShopInfoExpl.getShopId() == 0) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_FOUND_SHOP, new Object[]{shopId.toString()});
		}
		responseBodyJson.writeFieldName("shopInfo");
		objectMapper.writeValue(responseBodyJson, gdShopInfoExpl);
		responseBodyJson.writeFieldName("ServNode");
		objectMapper.writeValue(responseBodyJson, syServDicList);
	}
}
