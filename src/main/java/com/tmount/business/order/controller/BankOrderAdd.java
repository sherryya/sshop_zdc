package com.tmount.business.order.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.tmount.business.order.service.OrderService;
import com.tmount.business.pub.service.PubService;
import com.tmount.db.order.dto.ReBankAccept;
import com.tmount.db.order.dto.ReBankOrderAccept;
import com.tmount.db.order.dto.SyPayorgDict;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.yeepay.Configuration;
import com.yeepay.PaymentForOnlineService;
@Controller
public class BankOrderAdd extends ControllerBase {

	@Autowired PubService pubService;

	@Autowired OrderService orderService;
	
	String formatString(String text){ 
		if(text == null) {
			return ""; 
		}
		return text;
	}
	
	@RequestMapping(value = "order.bank.accept.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
		

	}
	
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		Long userId = ParamData.getLong(requestParam.getBodyNode(), "user_id");
		int orgId = ParamData.getInt(requestParam.getBodyNode(), "org_id");
		Long bankAccept = pubService.updateSeqByName("payorg_accept");
		Double price  = ParamData.getDouble(requestParam.getBodyNode(), "price");
		String strDate = ParamData.getString(requestParam.getBodyNode(), "date");
		String strMerchantPara = ParamData.getString(requestParam.getBodyNode(), "merchant_para");
		String strMerchantUrl = ParamData.getString(requestParam.getBodyNode(), "merchant_url");
		String strClientIP =  ParamData.getString(requestParam.getBodyNode(), "client_ip");
		
		SyPayorgDict syPayorgDict = orderService.getSyPayorgDict(orgId);
		
		String branchId = syPayorgDict.getBranchId();
		String coNo = syPayorgDict.getCoNo();
		String orgCode = syPayorgDict.getOrgCode();
		
		String requestUrl = "";
		String responseUrl = "";
		
		
		String strVerifyCode = null ;
		if("CMB".equals(orgCode)){
			cmb.MerchantCode mc = new cmb.MerchantCode();
			strVerifyCode = mc.genMerchantCode("Z2M0S1C21c0s2m3z",strDate,branchId,coNo,bankAccept.toString(),price.toString(),strMerchantPara,strMerchantUrl,userId.toString(),"ZMSC",strClientIP,"54011600","");
			
			requestUrl = formatString(Configuration.getInstance().getValue("CMBRequestURL"));
			responseUrl = formatString(Configuration.getInstance().getValue("CMBResponseURL")); 
		}else if("YEEPAY".equals(orgCode)){
			coNo = formatString(Configuration.getInstance().getValue("p1_MerId")); 
			String keyValue   		     	= formatString(Configuration.getInstance().getValue("keyValue"));   		// 商家密钥
			String nodeAuthorizationURL  	= formatString(Configuration.getInstance().getValue("yeepayCommonReqURL"));  	// 交易请求地址
			
			String p0_Cmd = formatString("Buy");   
			String p1_MerId = coNo;//商户编号
			String p2_Order  = bankAccept.toString();//商户订单号
			String p3_Amt = price.toString();//支付金额
			String p4_Cur = formatString("CNY");//交易币种
			String p5_Pid = formatString("");//商品名称
			String p6_Pcat = formatString("");//商品种类
			String p7_Pdesc = formatString("");//商品描述
			String p8_Url = formatString(strMerchantUrl);//商户接收支付成功数据的地址
			String p9_SAF = formatString("0");//送货地址 为“1”: 需要用户将送货地址留在易宝支付系统;为“0”: 不需要，默认为 ”0”.
			String pa_MP = formatString(strMerchantPara);
			String pd_FrpId = formatString(ParamData.getString(requestParam.getBodyNode(), "frp_id"));//支付通道编码
			// 银行编号必须大写
			pd_FrpId = pd_FrpId.toUpperCase();
			String pr_NeedResponse = formatString("1");    // 默认为"1"，需要应答机制
			String hmac = formatString("");
			hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(p0_Cmd,
					p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,
					p8_Url,p9_SAF,pa_MP,pd_FrpId,pr_NeedResponse,keyValue);
			strVerifyCode = hmac;
			
			requestUrl = nodeAuthorizationURL;
			responseUrl = formatString(Configuration.getInstance().getValue("yeepayResponseURL"));
		}
		
		
		JsonNode orderList = requestParam.getBodyNode().get("order_list");
		ReBankOrderAccept reBankOrderAccept;
		for(JsonNode order:orderList){
			long orderNo = order.get("order_no").asLong();
			reBankOrderAccept = new ReBankOrderAccept();
			reBankOrderAccept.setBankAccept(bankAccept);
			reBankOrderAccept.setOrderNo(orderNo);
			reBankOrderAccept.setOrgId(orgId);
			
			orderService.insertBankOrderAccept(reBankOrderAccept);
		}
		
		ReBankAccept reBankAccept  = new ReBankAccept();
		reBankAccept.setBankAccept(bankAccept);
		reBankAccept.setOrgId(orgId);
		reBankAccept.setPrice(price);
		reBankAccept.setPayType("N");
		reBankAccept.setPayTime(pubService.getDbTime().getDbTime());
		reBankAccept.setCheckType("N");
		
		orderService.insertBankAccept(reBankAccept);
		responseBodyJson.writeNumberField("bank_accept",bankAccept);
		responseBodyJson.writeStringField("verify_code", strVerifyCode);
		responseBodyJson.writeStringField("branch_id", branchId);
		responseBodyJson.writeStringField("co_no", coNo);
		responseBodyJson.writeStringField("request_url", requestUrl);
		responseBodyJson.writeStringField("response_url", responseUrl);
		
	}

}
