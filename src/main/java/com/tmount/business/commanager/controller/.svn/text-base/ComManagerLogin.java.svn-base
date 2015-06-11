package com.tmount.business.commanager.controller;
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
import com.tmount.business.user.controller.UserLogin;
import com.tmount.db.commanager.dao.ComManager;
import com.tmount.db.commanager.dto.User;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class ComManagerLogin  extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(UserLogin.class.getName());
	@Autowired
	private ComManager comManager;
	@RequestMapping(value = "companymanagerlogin.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson,HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		String username = new String(ParamData.getString(requestParam.getBodyNode(),"username"));//账户名称
		String password = new String(ParamData.getString(requestParam.getBodyNode(),	"password"));//账号密码
		User user1=new User();
		user1.setUsername(username);
		User user  = comManager.comManagerLogin(user1);
		if (user != null) {
			 if(user.getPassword().equals(password))
			 {
				responseBodyJson.writeNumberField("id", user.getId());
				responseBodyJson.writeNumberField("flag", 1);
			 }
			 else
			 {
				 throw new ShopBusiException(ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
			 }
			} else {
				throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_USER, new Object[]{username});
			}
	}
}

