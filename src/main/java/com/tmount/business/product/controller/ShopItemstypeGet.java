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
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.GdShopItemstypeDic;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取商店的商品分类列表
 * 
 * @author lugz
 * 
 */
@Controller
public class ShopItemstypeGet extends ControllerBase {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.itemstype.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long shopId = new Long(ParamData.getLong(requestParam.getBodyNode(),
				"shop_id"));
		Integer dataType = new Integer(ParamData.getInt(requestParam.getBodyNode(), "data_type", -1));
		GdShopItemstypeDic gdShopItemstypeDic = new GdShopItemstypeDic();
		gdShopItemstypeDic.setShopId(shopId);
		if (dataType != -1) {
			gdShopItemstypeDic.setDataType(dataType);
		}
		List<GdShopItemstypeDic> gdShopItemstypeDicList = goodsService
				.getGdItemsTypeDicListSelective(gdShopItemstypeDic);

		responseBodyJson.writeArrayFieldStart("itemtype_list");
		if (gdShopItemstypeDicList.size() > 0) {
			Iterator<GdShopItemstypeDic> it = gdShopItemstypeDicList.iterator();
			while (it.hasNext()) {
				gdShopItemstypeDic = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("sitems_type", gdShopItemstypeDic.getSitemsType());
				responseBodyJson.writeNumberField("orders", gdShopItemstypeDic.getOrders());
				responseBodyJson.writeNumberField("parent_id", gdShopItemstypeDic.getParentId());
				responseBodyJson.writeNumberField("show_type", gdShopItemstypeDic.getShowType());
				responseBodyJson.writeNumberField("data_type", gdShopItemstypeDic.getDataType());
				responseBodyJson.writeStringField("has_child", gdShopItemstypeDic.getHasChild());
				responseBodyJson.writeStringField("type_name", gdShopItemstypeDic.getTypeName());
				responseBodyJson.writeStringField("type_desc", gdShopItemstypeDic.getTypeDesc());
				responseBodyJson.writeStringField("type_descexp", gdShopItemstypeDic.getTypeDescexp());
				responseBodyJson.writeNumberField("small_pic", gdShopItemstypeDic.getSmallPic());
				responseBodyJson.writeEndObject();
			}
		}

		responseBodyJson.writeEndArray();
	}
}
