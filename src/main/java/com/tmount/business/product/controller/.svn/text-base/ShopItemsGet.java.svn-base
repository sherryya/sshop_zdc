package com.tmount.business.product.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取单个商品的明细信息
 * 
 * @author lugz
 * 
 */
@Controller
public class ShopItemsGet extends ControllerBase {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.items.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long itemsId = new Long(ParamData.getLong(requestParam.getBodyNode(),
				"items_id", 0));
		String barCode = ParamData.getString(requestParam.getBodyNode(),
				"bar_code", null);
		
		GdItemsInfo gdItemsInfo = null;
		List<GdItemsInfo> itemsList = null;
		if (itemsId.longValue() == 0) {
			if (barCode == null) {
				throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ITEM, new Object[]{0});
			} else {
				Map<String, Object> paramIn = new HashMap<String, Object>();
				paramIn.put("barCode", barCode);
				itemsList = goodsService.getGdItemsInfoByMap(paramIn);
				if (itemsList == null) {
					throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ITEM, new Object[]{itemsId});
				} else if (itemsList.size() == 0) {
					throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ITEM, new Object[]{itemsId});
				} else {
					gdItemsInfo = itemsList.get(0);
				}
			}
		} else {
			gdItemsInfo = goodsService.getGdItemsInfoByItemsId(itemsId);
		}
		if (gdItemsInfo == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ITEM, new Object[]{itemsId});
		}
		responseBodyJson.writeNumberField("items_id", gdItemsInfo.getItemsId());
		responseBodyJson.writeNumberField("shop_id", gdItemsInfo.getShopId());
		responseBodyJson.writeNumberField("show_type", gdItemsInfo.getShowType());
		responseBodyJson.writeNumberField("data_type", gdItemsInfo.getDataType());
		responseBodyJson.writeStringField("has_child", gdItemsInfo.getHasChild());
		responseBodyJson.writeStringField("items_name", gdItemsInfo.getItemsName());
		responseBodyJson.writeStringField("name_spr", gdItemsInfo.getNameSpr());
		responseBodyJson.writeStringField("items_intro", gdItemsInfo.getItemsIntro());
		responseBodyJson.writeNumberField("small_pic", gdItemsInfo.getSmallPic());
		responseBodyJson.writeStringField("http_url", gdItemsInfo.getHttpUrl());
		responseBodyJson.writeStringField("web_url", gdItemsInfo.getWebUrl());
		responseBodyJson.writeNumberField("price", gdItemsInfo.getPrice());
		responseBodyJson.writeStringField("aunit", gdItemsInfo.getAunit());
		responseBodyJson.writeNumberField("discount", gdItemsInfo.getDiscount());
		responseBodyJson.writeNumberField("member_price", gdItemsInfo.getMemberPrice());
		responseBodyJson.writeNumberField("discuss_count", gdItemsInfo.getDiscussCount());
		responseBodyJson.writeNumberField("comment_value", gdItemsInfo.getCommentValue());
	}
}
