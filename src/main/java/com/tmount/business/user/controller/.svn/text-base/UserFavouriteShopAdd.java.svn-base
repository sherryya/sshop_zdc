package com.tmount.business.user.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.pub.service.PubService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.db.user.dto.UsFavouriteShop;
import com.tmount.db.user.dto.UsFavouriteShopKey;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 4.3.5.9	用户收藏商店
 * @author lugz
 *
 */
@Controller
public class UserFavouriteShopAdd extends ControllerBase {
	@Autowired
	private UserService userService;

	@Autowired
	private PubService pubService;

	@RequestMapping(value = "user.favourite.shop.add")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long userId = ParamData.getLong(requestParam.getBodyNode(), "user_id");
		Long shopId = ParamData.getLong(requestParam.getBodyNode(), "shop_id");
		Long userTagId = ParamData.getLong(requestParam.getBodyNode(), "user_tag_id");

		UsFavouriteShopKey usFavouriteShopKey = new UsFavouriteShopKey();
		usFavouriteShopKey.setUserId(userId);
		usFavouriteShopKey.setShopId(shopId);
		GdShopInfo gdShopInfo = userService.selectUsFavouriteShop(usFavouriteShopKey);
		if (gdShopInfo == null) {
			DbTime dbTime = pubService.getDbTime();
			UsFavouriteShop usFavouriteShop = new UsFavouriteShop();
			usFavouriteShop.setUserId(userId);
			usFavouriteShop.setShopId(shopId);
			usFavouriteShop.setUserTagId(userTagId);
			usFavouriteShop.setCreateTime(dbTime.getDbTime());
			userService.insertUsFavouriteShop(usFavouriteShop);
		}else {
			throw new ShopBusiException(ShopBusiErrorBundle.EXISTS_FAVOURITE_SHOP, new Object[]{gdShopInfo.getShopName()});
		}
	}
}
