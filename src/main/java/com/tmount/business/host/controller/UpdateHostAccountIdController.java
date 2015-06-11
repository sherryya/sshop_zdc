package com.tmount.business.host.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.user.service.UserService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 更新主播账户信息
 * 
 * @author
 * 
 */
@Controller
public class UpdateHostAccountIdController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItovHostUserService zdcHostService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "updateHostAccountId")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String name = ParamData.getString(requestParam.getBodyNode(), "account_name");
		int id = ParamData.getInt(requestParam.getBodyNode(), "id");
		try{
			UsAccount usUserInfoOld = userService.getUsUserInfoByUserAccount(name);
			if(null !=usUserInfoOld )
			{
				Long accountid = usUserInfoOld.getAccount_id();
				TZdcHostUser tzdcHost = new TZdcHostUser();
				tzdcHost.setId(id);
				tzdcHost.setAccountid(accountid);
				logger.info("updateHostAccountId 更新accountid开始");
				int count = zdcHostService.updateAccountId(tzdcHost);
				logger.info("updateHostAccountId 更新accountidid结束");
				responseBodyJson.writeNumberField("result", count);
			}else
			{
				responseBodyJson.writeNumberField("result", 0);
			}
			
		}catch(Exception e)
		{
			logger.info("updateHostAccountId更新主播异常accountid");
		}
		
		
	}

}
