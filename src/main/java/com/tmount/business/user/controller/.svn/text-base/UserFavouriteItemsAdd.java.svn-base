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
import com.tmount.db.product.dto.GdItemsInfo;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.db.user.dto.UsFavouriteItems;
import com.tmount.db.user.dto.UsFavouriteItemsKey;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 4.3.5.9	用户收藏商品
 * @author lugz
 *
 */
@Controller
public class UserFavouriteItemsAdd extends ControllerBase {
	@Autowired
	private UserService userService;

	@Autowired
	private PubService pubService;

	@RequestMapping(value = "user.favourite.items.add")
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
		Long userTagId = ParamData.getLong(requestParam.getBodyNode(), "user_tag_id");
		Double discount = ParamData.getDouble(requestParam.getBodyNode(), "discount");

		UsFavouriteItemsKey usFavouriteItemsKey = new UsFavouriteItemsKey();
		usFavouriteItemsKey.setUserId(userId);
		usFavouriteItemsKey.setShopId(shopId);
		usFavouriteItemsKey.setItemsId(itemsId);
		GdItemsInfo gdItemsInfo =  userService.selectUsFavouriteItems(usFavouriteItemsKey);
		if (gdItemsInfo == null) {
			DbTime dbTime = pubService.getDbTime();
			UsFavouriteItems usFavouriteItems = new UsFavouriteItems();
			usFavouriteItems.setUserId(userId);
			usFavouriteItems.setShopId(shopId);
			usFavouriteItems.setItemsId(itemsId);
			usFavouriteItems.setUserTagId(userTagId);
			usFavouriteItems.setDiscount(discount);
			usFavouriteItems.setCreateTime(dbTime.getDbTime());
			userService.insertUsFavouriteItems(usFavouriteItems);
		}else {
			throw new ShopBusiException(ShopBusiErrorBundle.EXISTS_FAVOURITE_ITEMS, new Object[]{gdItemsInfo.getItemsName()});
		}
	}
}
