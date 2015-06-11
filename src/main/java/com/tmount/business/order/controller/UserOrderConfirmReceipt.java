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
 * 用户确认接收订单
 * @author lugz
 *
 */
@Controller
public class UserOrderConfirmReceipt extends ControllerBase {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "user.order.confirm.receipt")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long orderNo = ParamData.getLong(requestParam.getBodyNode(), "order_no");
		String mark = ParamData.getString(requestParam.getBodyNode(), "mark");
		orderService.confirmReceipt(orderNo, mark);
	}
}
