package com.tmount.business.integral.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.integral.service.AddIntegralService;
import com.tmount.db.integral.dto.TItovIntegral;
import com.tmount.db.integral.dto.TItovIntegralRule;
import com.tmount.db.integral.dto.TItovIntegralTotal;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class AddIntegralController extends ControllerBase {
	@Autowired
	AddIntegralService addIntegralService;
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		int rule_type = new Integer(ParamData.getInt(	requestParam.getBodyNode(), "rule_type"));// 规则类型
		Long account_id = ParamData.getLong(requestParam.getBodyNode(),"account_id");// 用户ID
		try {
			//1 通过积分规则查看积分数  
			TItovIntegralRule ItovIntegralRule = new TItovIntegralRule();
			ItovIntegralRule.setType(rule_type);
			TItovIntegralRule ItovIntegralRule1 = new TItovIntegralRule();
			ItovIntegralRule1 = addIntegralService.selectIntegralByType(ItovIntegralRule);
			Integer integral = 0;// 规则对应的积分数
			String comment = "";
			if (!ItovIntegralRule1.equals(null)) {
				integral = ItovIntegralRule1.getIntegral();
				comment = ItovIntegralRule1.getName();
			}
			//2  添加积分明细表  
			TItovIntegral tItovIntegral = new TItovIntegral();
			tItovIntegral.setAccount_id(account_id);
			tItovIntegral.setComment(comment);
			tItovIntegral.setCrt_date(new java.util.Date());
			tItovIntegral.setIntegral(integral);
			tItovIntegral.setRule_type(rule_type);
			addIntegralService.insert_integral(tItovIntegral);
			//3  添加积分综合
			
			TItovIntegralTotal  tItovIntegralTotal=new TItovIntegralTotal();
			TItovIntegralTotal  tItovIntegralTotal1=new TItovIntegralTotal();
			tItovIntegralTotal1.setAccount_id(account_id);
			tItovIntegralTotal=addIntegralService.selectTotalByAccount(tItovIntegralTotal1);
			tItovIntegralTotal1.setIntegral(integral);
			tItovIntegralTotal1.setRule_type(rule_type);
			if(tItovIntegralTotal==null)
			{
				addIntegralService.insert_integral_total(tItovIntegralTotal1);
			}
			else
			{
				addIntegralService.update_integral_total(tItovIntegralTotal1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseBodyJson.writeNumberField("result", 0);
			return;
		}
		responseBodyJson.writeNumberField("result", 1);
	}
	@RequestMapping(value = "Integral.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);

	}

}
