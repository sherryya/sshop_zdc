package com.tmount.business.user.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
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

@Controller
public class updatePwdController extends ControllerBase {
	@Autowired
	private UserService userService;
	Logger logger = Logger.getLogger(UserAdd.class.getName());

	@RequestMapping(value = "updatePwd.upd")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {

		String account_password = new String(ParamData.getString(requestParam.getBodyNode(), "account_password"));
		String account_name = new String(ParamData.getString(requestParam.getBodyNode(), "account_name"));
		UsAccount usUserInfoOld = userService.getUsUserInfoByUserAccount(account_name);// 判断用户名是否
																				// 存在
		if (usUserInfoOld != null) {
			UsAccount usAccount = new UsAccount();
			usAccount.setAccount_name(account_name);
			usAccount.setAccount_password( MD5.getMD5(account_password));
			int ret = userService.updatePwd(usAccount);// 修改密码
			if (ret > 0) {
				responseBodyJson.writeStringField("return", "1");
			} else {
				responseBodyJson.writeStringField("return", "0");
			}
		} else {
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "帐号【" + account_name + "】不存在" });
		}
	}
}
