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
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 用户退出登陆
 * @author 
 *
 */
@Controller
public class UserLogout  extends ControllerBase {
	@Autowired
	private UserService userService;
	@RequestMapping(value = "user.logout")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		long account_id = ParamData.getLong(requestParam.getBodyNode(), "account_id");
		UsAccount us=new UsAccount();
		us.setAccount_id(account_id);
		us.setIs_login(0);
		userService.updateLoginStatus(us);
		responseBodyJson.writeStringField("return", "0");
	}
}
