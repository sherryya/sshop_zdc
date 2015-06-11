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
import com.tmount.db.user.dto.UsFavouriteItemsKey;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 用户收藏商品删除，单个删除
 * @author lugz
 *
 */
@Controller
public class UserFavouriteItemsDelete extends ControllerBase {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "user.favourite.items.delete")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long userId = ParamData.getLong(requestParam.getBodyNode(), "user_id");
		Long shopId = ParamData.getLong(requestParam.getBodyNode(), "shop_id");
		Long itemsId = ParamData.getLong(requestParam.getBodyNode(), "items_id");
		UsFavouriteItemsKey usFavouriteItemsKey = new UsFavouriteItemsKey();
		usFavouriteItemsKey.setUserId(userId);
		usFavouriteItemsKey.setShopId(shopId);
		usFavouriteItemsKey.setItemsId(itemsId);
		userService.deleteUsFavouriteItems(usFavouriteItemsKey);
	}
}
