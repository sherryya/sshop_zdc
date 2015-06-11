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
import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class UserOrderGet extends ControllerBase {
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "user.order.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Integer orderState = ParamData.getInt(requestParam.getBodyNode(), "state", -1);
		Long userId = new Long(requestParam.getRequestDataHeader().getUserId());
		
		Map<String, Object> paramIn = new HashMap<String, Object>();
		paramIn.put("userId", userId);
		paramIn.put("orderValid", "Y");
		if (orderState != -1) {
			paramIn.put("state", orderState);
		}
		paramIn.put("rowCount", 50);
		
		List<ReUserOrder> reUserOrderList = 
				orderService.getReUserOrderListByUserId(paramIn);
		
		ReUserOrder reUserOrder = null;
		responseBodyJson.writeArrayFieldStart("order_list");
		if (reUserOrderList.size() > 0) {
			ReUserOrderAdd reUserOrderAdd;
			Iterator<ReUserOrder> it = reUserOrderList.iterator();
			while(it.hasNext()) {
				reUserOrder = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("order_no", reUserOrder.getOrderNo());
				responseBodyJson.writeNumberField("shop_id", reUserOrder.getShopId());
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
				reUserOrderAdd = orderService.getReUserOrderAddByOrderNo(reUserOrder.getOrderNo());
				if (reUserOrderAdd != null) {
					responseBodyJson.writeStringField("mark", reUserOrderAdd.getMark());
				}
				ReOrderDeliver reOrderDeliver = orderService.getReOrderDeliverByOrderNo(reUserOrder.getOrderNo());
				if (reOrderDeliver != null) {
					responseBodyJson.writeStringField("user_name", reOrderDeliver.getUserName());
					responseBodyJson.writeStringField("contact_phone", reOrderDeliver.getContactPhone());
				}
				responseBodyJson.writeEndObject();
			}
		}
		
		responseBodyJson.writeEndArray();
	}
}
