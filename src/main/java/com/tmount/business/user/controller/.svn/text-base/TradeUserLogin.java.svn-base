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
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.system.MD5;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 行业级用户登录
 * @author 
 *
 */
@Controller
public class TradeUserLogin  extends ControllerBase {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "tradeuser.login")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String userAccount = new String(ParamData.getString(requestParam.getBodyNode(),
				"account_name"));//账户名称
		String password = new String(ParamData.getString(requestParam.getBodyNode(),
				"account_password"));//账号密码
		//1.如果是企业的账号、先根据账号名称直接去找T_ITOV_ACCOUNT表中、如果不存在，则找t_itov_account_auxiliary
		UsAccount usAccount = userService.getTradeUserInfoByUserAccount(Integer.parseInt(userAccount));
		if (usAccount != null) {
			if (usAccount.getAccount_password().equals(MD5.getMD5(password))) {
				responseBodyJson.writeNumberField("account_id", usAccount.getAccount_id());
				responseBodyJson.writeNumberField("account_role_id",usAccount.getAccount_role_id());
			} else {
				throw new ShopBusiException(ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
			}
		} 
	}

}
