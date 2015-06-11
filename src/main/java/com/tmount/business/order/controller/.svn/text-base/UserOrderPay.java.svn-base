package com.tmount.business.order.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.order.service.OrderService;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 用户订单付款
 * @author lugz
 *
 */
@Controller
public class UserOrderPay extends ControllerBase {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "user.order.pay")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long orderNo = ParamData.getLong(requestParam.getBodyNode(), "order_no");
		Long userId = ParamData.getLong(requestParam.getBodyNode(), "user_id");
		Integer payWayId = ParamData.getInt(requestParam.getBodyNode(), "pay_way_id");
		String mark = ParamData.getString(requestParam.getBodyNode(), "mark");
		Integer payType = ParamData.getInt(requestParam.getBodyNode(), "pay_type");
		Double payMoney = ParamData.getDouble(requestParam.getBodyNode(), "pay_money");
		Double payEvkMoney = ParamData.getDouble(requestParam.getBodyNode(), "pay_evk_money");
		String payOtherId = ParamData.getString(requestParam.getBodyNode(), "pay_other_id");
		Long payId = orderService.userOrderPay(orderNo, userId, payWayId,
				mark, payType, payMoney, payEvkMoney, payOtherId, 0, null, null);
		responseBodyJson.writeNumberField("pay_id", payId);
	}
}
