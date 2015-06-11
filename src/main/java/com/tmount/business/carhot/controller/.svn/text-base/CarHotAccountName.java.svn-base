package com.tmount.business.carhot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *车品牌数据同步接口
 * 
 * @author 
 * 
 */
@Controller
public class CarHotAccountName extends ControllerBase {
	@Autowired
	private UserService userService;
	@RequestMapping(value = "carHotAccountName.upd")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		String new_account_name = ParamData.getString(requestParam.getBodyNode(),"new_account_name");//新的手机号
		String account_name = ParamData.getString(requestParam.getBodyNode(),"account_name");//原来的手机号
		UsAccount usAccout = userService.getUsUserInfoByUserAccount(account_name);
		if(usAccout ==null)
		{
			//responseBodyJson.writeStringField("result", );
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
					new Object[] { "用户不存在" });
		}
		UsAccount usAccout2 = userService.getUsUserInfoByUserAccount(new_account_name);
		if(usAccout2 !=null)
		{
			//responseBodyJson.writeStringField("result", );
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
					new Object[] { "此用户已经存在" });
		}
		UsAccount usAccoutnew = new UsAccount();
		usAccoutnew.setAccount_name(account_name);
		usAccoutnew.setNickname(new_account_name);
		int t = userService.updateAccountName(usAccoutnew);
		responseBodyJson.writeNumberField("result",t);   //1 表示成功，否则失败
		
		
	}
}
