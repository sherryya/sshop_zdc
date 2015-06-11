package com.tmount.business.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopParamErrorBundle;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.user.vo.UsUserCommentUser;
import com.tmount.exception.ShopException;
import com.tmount.exception.ShopParamException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 得到用户对商品的评论情况，按照时间倒序排列。
 * 
 * @author lugz
 * 
 */
@Controller
public class ProductUserCommentGet extends ControllerBase {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "product.user.comment.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long itemsId = new Long(ParamData.getLong(requestParam.getBodyNode(), "items_id", -1));
		Long shopId = new Long(ParamData.getLong(requestParam.getBodyNode(), "shop_id", -1));
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(), "user_id", -1));
		Integer commentLevel = new Integer(ParamData.getInt(requestParam.getBodyNode(), "comment_level", -1));
		Integer fetchRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "fetch_rows"));
		Integer startRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "start_rows"));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		boolean bValid = false;
		if (itemsId != -1) {
			paramMap.put("itemsId", itemsId);
			bValid = true;
		}
		if (shopId != -1) {
			paramMap.put("shopId", shopId);
			bValid = true;
		}
		if (userId != -1) {
			paramMap.put("userId", userId);
			bValid = true;
		}
		if (commentLevel != -1) {
			paramMap.put("commentLevel", commentLevel);
			bValid = true;
		}
		
		if (!bValid) {
			throw new ShopParamException(ShopParamErrorBundle.LOST_OPTION_IN_PARAM, new Object[]{});
		}
		paramMap.put("start", startRows);
		paramMap.put("end", fetchRows);

		Integer commentCount = goodsService.getProductUserCommentCount(paramMap);
		List<UsUserCommentUser> commentList = goodsService.getProductUserComment(paramMap);
		responseBodyJson.writeNumberField("total_rows", commentCount);
		

		responseBodyJson.writeArrayFieldStart("item_list");
		if (commentList != null) {
			if (commentList.size() > 0) {
				UsUserCommentUser usUserCommentUser;
				Iterator<UsUserCommentUser> it = commentList.iterator();
				while (it.hasNext()) {
					usUserCommentUser = it.next();
					responseBodyJson.writeStartObject();
					responseBodyJson.writeNumberField("comment_id", usUserCommentUser.getCommentId());
					responseBodyJson.writeNumberField("user_id", usUserCommentUser.getUserId());
					responseBodyJson.writeNumberField("shop_id", usUserCommentUser.getShopId());
					responseBodyJson.writeNumberField("items_id", usUserCommentUser.getItemsId());
					responseBodyJson.writeStringField("items_name", usUserCommentUser.getItemsName());
					responseBodyJson.writeNumberField("comment_level", usUserCommentUser.getCommentLevel());
					responseBodyJson.writeNumberField("items_desc", usUserCommentUser.getItemsDesc());
					responseBodyJson.writeNumberField("delive_speed", usUserCommentUser.getDeliveSpeed());
					responseBodyJson.writeNumberField("server_attitude", usUserCommentUser.getServerAttitude());
					responseBodyJson.writeNumberField("goods_speed", usUserCommentUser.getGoodsSpeed());
					responseBodyJson.writeNumberField("average_value", usUserCommentUser.getAverageValue());
					responseBodyJson.writeStringField("comment_content", usUserCommentUser.getCommentContent());
					responseBodyJson.writeStringField("comment_time", requestParam.dateToString(usUserCommentUser.getCommentTime()));
					responseBodyJson.writeNumberField("orderNo", usUserCommentUser.getOrderNo()==null?0:usUserCommentUser.getOrderNo());
					responseBodyJson.writeStringField("end_time", usUserCommentUser.getEndTime()==null?"":requestParam.dateToString(usUserCommentUser.getEndTime()));
					responseBodyJson.writeStringField("user_name", usUserCommentUser.getUserName());
					responseBodyJson.writeStringField("user_account", usUserCommentUser.getUserAccount());
					responseBodyJson.writeNumberField("user_avatar", usUserCommentUser.getUserAvatar());
					responseBodyJson.writeEndObject();
				}
			}
		}

		responseBodyJson.writeEndArray();
	}
}
