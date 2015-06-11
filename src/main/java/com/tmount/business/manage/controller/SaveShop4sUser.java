package com.tmount.business.manage.controller;

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
import com.tmount.business.manage.service.TItov_shop4sService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.db.manage.dto.TItov_shop4s_user;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 更新主播信息
 * 
 * @author
 * 
 */
@Controller
public class SaveShop4sUser extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItov_shop4sService tItov_shop4sService;

	@RequestMapping(value = "shop4s.user.save")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String name = ParamData.getString(requestParam.getBodyNode(), "account_name");
		int accountId = ParamData.getInt(requestParam.getBodyNode(), "accountId");
		int companyId = ParamData.getInt(requestParam.getBodyNode(), "companyId");


		TItov_shop4s_user tItov_shop4s_user=new TItov_shop4s_user();
		tItov_shop4s_user.setAccount_id(accountId);
		tItov_shop4s_user.setCompany_id(companyId);
		
		
		try{
			logger.info("updateShop4sinfo.upd 更新4s开始");
			int count = tItov_shop4sService.saveShop4sUser(tItov_shop4s_user);
			logger.info("updateShop4sinfo.upd 更新4s结束");
			responseBodyJson.writeNumberField("result", count);
		}catch(Exception e)
		{	
			logger.info("myfault------->start");
			e.printStackTrace();
			logger.info("myfault------->end");
			logger.info("saveShop4sUser");
		}
		
	}
}
