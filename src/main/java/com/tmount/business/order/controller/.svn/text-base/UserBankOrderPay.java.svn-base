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
 * 用户银行订单付款
 * @author lugz
 *
 */
@Controller
public class UserBankOrderPay extends ControllerBase {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "user.bank.order.pay")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long bankAccept = ParamData.getLong(requestParam.getBodyNode(), "bank_accept");	//银行流水
		String succeed = ParamData.getString(requestParam.getBodyNode(), "succeed");	//Y(成功)或N(失败)）
		String coNo = ParamData.getString(requestParam.getBodyNode(), "coNo");	//商户号
		Double amount = ParamData.getDouble(requestParam.getBodyNode(), "amount");	//实际支付金额
		String date = ParamData.getString(requestParam.getBodyNode(), "date");	//交易日期
		String msg = ParamData.getString(requestParam.getBodyNode(), "msg");	//银行通知用户的支付结果消息。
		String merchantPara = ParamData.getString(requestParam.getBodyNode(), "merchantPara");	//商户自己的参数
		String signature = ParamData.getString(requestParam.getBodyNode(), "signature");	//银行用自己的Private Key对通知命令的签名
		String baMessage = ParamData.getString(requestParam.getBodyNode(), "baMessage");	//从银行返回的信息
		Integer payType = ParamData.getInt(requestParam.getBodyNode(), "pay_type");	//付款方式ID
		String mark = ParamData.getString(requestParam.getBodyNode(), "mark");	//本次缴费备注

		Long payId = orderService.userBankOrderPay(bankAccept, succeed.charAt(0), coNo, amount, 
				date, msg, merchantPara, signature, baMessage, payType, mark);
		responseBodyJson.writeNumberField("pay_id", payId);
	}
}
