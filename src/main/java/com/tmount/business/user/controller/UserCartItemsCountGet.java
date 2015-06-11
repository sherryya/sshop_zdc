package com.tmount.business.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsShopCartKey;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 查询用户购物车中的商品数量。
 * @author lugz
 *
 */
@Controller
public class UserCartItemsCountGet extends ControllerBase{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "user.cart.items.count.get")
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
		Integer itemsCount = userService.getItemsCount(usShopCartKey);
		responseBodyJson.writeNumberField("items_count", itemsCount);
	}
}
