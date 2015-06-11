package com.tmount.business.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsUserAccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取用户余额信息
 * @author lugz
 *
 */
@Controller
public class UserAccountGet extends ControllerBase {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "user.account.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		UsUserAccount usUserAccount = 
				userService.getUsUserAccountByUserId(userId);
		
		if (usUserAccount !=  null) {
			responseBodyJson.writeNumberField("balance_money", usUserAccount.getBalanceMoney());
		}
	}
}
