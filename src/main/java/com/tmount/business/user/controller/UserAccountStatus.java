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
import com.tmount.business.user.service.AccountStatusService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.db.user.dto.UsAccountStatus;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.system.MD5;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class UserAccountStatus extends ControllerBase {
	@Autowired
	private AccountStatusService accountStatusService;

	@RequestMapping(value = "accountstatus.login")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String account_id = new String(ParamData.getString(requestParam.getBodyNode(), "account_id"));
		String num = new String(ParamData.getString(requestParam.getBodyNode(),	"num"));
		UsAccountStatus usAccountStatus = new UsAccountStatus();
		usAccountStatus.setAccount_id(Long.valueOf(account_id));
		usAccountStatus.setNum(num);
		try {
			String usAccount = accountStatusService.getUsUserAccountByUserId(Long.valueOf(account_id));
			if (usAccount != null) {
				accountStatusService.updateByPrimaryKey(usAccountStatus);
			} else {
				accountStatusService.insert(usAccountStatus);
			}
			 responseBodyJson.writeStringField("result", "0");
		} catch (Exception e) {
			// TODO: handle exception
			 responseBodyJson.writeStringField("result", "-1");
		}
	}
	/*
	 * responseBodyJson.writeNumberField("account_id",
	 * usAccount.getAccount_id());
	 * responseBodyJson.writeNumberField("account_role_id"
	 * ,usAccount.getAccount_role_id());
	 */
}
