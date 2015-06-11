package com.tmount.business.user.controller;

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
import com.tmount.business.user.service.UserService;
import com.tmount.db.product.dto.GdItemsExpRice;
import com.tmount.db.user.dto.UsShopCartKey;
import com.tmount.db.user.vo.UsShopCartItems;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取用户购物车中的信息。
 * @author lugz
 *
 */
@Controller
public class UserCartGet extends ControllerBase{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "user.cart.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		Long shopId = new Long(ParamData.getLong(requestParam.getBodyNode(), "shop_id", -1));
		UsShopCartKey usShopCartKey = new UsShopCartKey();
		usShopCartKey.setUserId(userId);
		if (shopId != -1) {
			usShopCartKey.setShopId(shopId);
		}
		List<UsShopCartItems> usShopCartItemsList = userService.getUsShopCart(usShopCartKey);

		responseBodyJson.writeArrayFieldStart("item_list");
		GdItemsExpRice gdItemsExpRice;
		if (usShopCartItemsList.size() > 0) {
			UsShopCartItems usShopCartItems;
			Iterator<UsShopCartItems> it = usShopCartItemsList.iterator();
			while (it.hasNext()) {
				usShopCartItems = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("items_id", usShopCartItems.getItemsId());
				responseBodyJson.writeNumberField("shop_id", usShopCartItems.getShopId());
				responseBodyJson.writeStringField("shop_name", usShopCartItems.getShopName());
				responseBodyJson.writeStringField("shop_web_url", usShopCartItems.getShopWebUrl());
				responseBodyJson.writeStringField("shop_http_url", usShopCartItems.getShopHttpUrl());
				responseBodyJson.writeNumberField("acount", usShopCartItems.getAcount());
				responseBodyJson.writeNumberField("show_type", usShopCartItems.getShowType());
				responseBodyJson.writeNumberField("data_type", usShopCartItems.getDataType());
				responseBodyJson.writeStringField("has_child", usShopCartItems.getHasChild());
				responseBodyJson.writeStringField("items_name", usShopCartItems.getItemsName());
				responseBodyJson.writeStringField("name_spr", usShopCartItems.getNameSpr());
				responseBodyJson.writeStringField("items_intro", usShopCartItems.getItemsIntro());
				responseBodyJson.writeNumberField("small_pic", usShopCartItems.getSmallPic());
				responseBodyJson.writeStringField("http_url", usShopCartItems.getHttpUrl());
				responseBodyJson.writeStringField("web_url", usShopCartItems.getWebUrl());
				responseBodyJson.writeNumberField("price", usShopCartItems.getPrice());
				responseBodyJson.writeStringField("aunit", usShopCartItems.getAunit());
				responseBodyJson.writeStringField("state", usShopCartItems.getState());
				responseBodyJson.writeStringField("create_time",requestParam.dateToString(usShopCartItems.getCreateTime()));
				responseBodyJson.writeNumberField("items_weight", usShopCartItems.getItemsWeight());
				
				gdItemsExpRice = userService.getGdItemsExpRiceByItemsId(usShopCartItems.getItemsId());
				if (gdItemsExpRice == null) {
					responseBodyJson.writeNumberField("stand_id", 0);
				} else {
					responseBodyJson.writeNumberField("stand_id", gdItemsExpRice.getStandId());
				}
				responseBodyJson.writeNumberField("discuss_count", usShopCartItems.getDiscussCount());
				responseBodyJson.writeNumberField("comment_value", usShopCartItems.getCommentValue());
				responseBodyJson.writeNumberField("discount", usShopCartItems.getDiscount());
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
	}
}
