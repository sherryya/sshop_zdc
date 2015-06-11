package com.tmount.business.order.controller;

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
import com.tmount.business.order.service.OrderService;
import com.tmount.db.order.dto.ReOrderDeliver;
import com.tmount.db.order.dto.ReStateDic;
import com.tmount.db.order.dto.ReUserOrder;
import com.tmount.db.order.dto.ReUserOrderAdd;
import com.tmount.db.order.vo.ReUserOrderDetailInfo;
import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取订单待评论信息
 * @author lugz
 *
 */
@Controller
public class UserCommentLogGet extends ControllerBase {
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "user.comment.log.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long userId = ParamData.getLong(requestParam.getBodyNode(), "user_id");
		Integer orderState = ParamData.getInt(requestParam.getBodyNode(), "state", -1);
		String orderEnd = ParamData.getString(requestParam.getBodyNode(), "order_end", null);
		String orderValid = ParamData.getString(requestParam.getBodyNode(), "order_valid", null);
		Integer fetchRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "fetch_rows", -1));
		Integer startRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "start_rows", -1));
		ReUserOrder reUserOrder = null;

		Map<String, Object> paramIn = new HashMap<String, Object>();
		paramIn.put("userId", userId);
		paramIn.put("orderEnd", orderEnd);
		paramIn.put("orderValid", orderValid);
		if (orderState != -1) {
			paramIn.put("state", orderState);
		}
		if (startRows != -1) {
			paramIn.put("start", startRows);
			paramIn.put("end", fetchRows);
		}

		List<ReUserOrder> reUserOrderList = 
				orderService.getReCommentLogListByUserId(paramIn);
		List<ReUserOrderDetailInfo> gdGdItemsItemRelList;
		int commentCount = orderService.getReCommentLogCountByUserId(paramIn);
		responseBodyJson.writeNumberField("total_rows", commentCount);
		responseBodyJson.writeArrayFieldStart("order_list");
		if (reUserOrderList.size() > 0) {
			ReUserOrderAdd reUserOrderAdd;
			Iterator<ReUserOrder> it = reUserOrderList.iterator();
			while(it.hasNext()) {
				reUserOrder = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("order_no", reUserOrder.getOrderNo());
				responseBodyJson.writeNumberField("shop_id", reUserOrder.getShopId());
				responseBodyJson.writeNumberField("acount", reUserOrder.getAcount());
				GdShopInfo gdShopInfo = orderService.getGdShopInfoByShopId(reUserOrder.getShopId());
				if (gdShopInfo == null) {
					responseBodyJson.writeStringField("shop_name", "未知");
				} else {
					responseBodyJson.writeStringField("shop_name", gdShopInfo.getShopName());
				}
				responseBodyJson.writeStringField("create_date", requestParam.dateToString(reUserOrder.getCreateDate()));
				responseBodyJson.writeNumberField("order_money", reUserOrder.getOrderMoney());
				responseBodyJson.writeNumberField("pay_money", reUserOrder.getPayMoney());
				responseBodyJson.writeNumberField("state", reUserOrder.getState());
				ReStateDic reStateDic = orderService.getReOrderStateByState(reUserOrder.getState());
				if (reStateDic == null) {
					responseBodyJson.writeStringField("user_state_name", "未知");
				} else {
					responseBodyJson.writeStringField("user_state_name", reStateDic.getUserStateName());
				}
				
				responseBodyJson.writeStringField("order_valid", reUserOrder.getOrderValid());
				responseBodyJson.writeStringField("order_end", reUserOrder.getOrderEnd());
				responseBodyJson.writeStringField("fee_date", requestParam.dateToString(reUserOrder.getFeeDate()));
				responseBodyJson.writeNumberField("price", reUserOrder.getPrice());
				responseBodyJson.writeNumberField("freight_fee", reUserOrder.getFreightFee());
				reUserOrderAdd = orderService.getReUserOrderAddByOrderNo(reUserOrder.getOrderNo());
				if (reUserOrderAdd != null) {
					responseBodyJson.writeStringField("mark", reUserOrderAdd.getMark());
				}
				ReOrderDeliver reOrderDeliver = orderService.getReOrderDeliverByOrderNo(reUserOrder.getOrderNo());
				if (reOrderDeliver != null) {
					responseBodyJson.writeStringField("user_name", reOrderDeliver.getUserName());
					responseBodyJson.writeStringField("contact_phone", reOrderDeliver.getContactPhone());
				}
				
				responseBodyJson.writeArrayFieldStart("item_list");
				gdGdItemsItemRelList = orderService.getReUserOrderDetailListByOrderNo(reUserOrder.getOrderNo());
				if (gdGdItemsItemRelList.size() > 0) {
					ReUserOrderDetailInfo reUserOrderDetailInfo;
					Iterator<ReUserOrderDetailInfo> itItem = gdGdItemsItemRelList.iterator();
					while (itItem.hasNext()) {
						reUserOrderDetailInfo = itItem.next();
						responseBodyJson.writeStartObject();
						responseBodyJson.writeNumberField("items_id", reUserOrderDetailInfo.getItemsId());
						responseBodyJson.writeNumberField("acount", reUserOrderDetailInfo.getAcount());
						responseBodyJson.writeNumberField("shop_id", reUserOrderDetailInfo.getShopId());
						responseBodyJson.writeNumberField("show_type", reUserOrderDetailInfo.getShowType());
						responseBodyJson.writeNumberField("data_type", reUserOrderDetailInfo.getDataType());
						responseBodyJson.writeStringField("has_child", reUserOrderDetailInfo.getHasChild());
						responseBodyJson.writeStringField("items_name", reUserOrderDetailInfo.getItemsName());
						responseBodyJson.writeStringField("name_spr", reUserOrderDetailInfo.getNameSpr());
						responseBodyJson.writeStringField("items_intro", reUserOrderDetailInfo.getItemsIntro());
						responseBodyJson.writeNumberField("small_pic", reUserOrderDetailInfo.getSmallPic());
						responseBodyJson.writeStringField("http_url", reUserOrderDetailInfo.getHttpUrl());
						responseBodyJson.writeStringField("web_url", reUserOrderDetailInfo.getWebUrl());
						responseBodyJson.writeNumberField("price", reUserOrderDetailInfo.getPrice());
						responseBodyJson.writeStringField("aunit", reUserOrderDetailInfo.getAunit());
						responseBodyJson.writeNumberField("state", reUserOrderDetailInfo.getState());
						responseBodyJson.writeNumberField("discuss_count", reUserOrderDetailInfo.getDiscussCount());
						responseBodyJson.writeNumberField("comment_value", reUserOrderDetailInfo.getCommentValue());
						responseBodyJson.writeEndObject();
					}
				}
				responseBodyJson.writeEndArray();
				responseBodyJson.writeEndObject();
			}
		}
		
		responseBodyJson.writeEndArray();
	}
}
