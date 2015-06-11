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
public class UpdItovUser4sController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItov_shop4sService tItov_shop4sService;

	@RequestMapping(value = "updateUser4sinfo.upd")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String name = ParamData.getString(requestParam.getBodyNode(), "account_name");
		int id = ParamData.getInt(requestParam.getBodyNode(), "id");
		String account_name = ParamData.getString(requestParam.getBodyNode(), "account_name");
		String account_password = ParamData.getString(requestParam.getBodyNode(), "account_password");
		String nickname = ParamData.getString(requestParam.getBodyNode(), "nickname");
		String real_name = ParamData.getString(requestParam.getBodyNode(), "real_name");
		String person_sex = ParamData.getString(requestParam.getBodyNode(), "person_sex");
		String person_tel = ParamData.getString(requestParam.getBodyNode(), "person_tel");
		String person_email = ParamData.getString(requestParam.getBodyNode(), "person_email");
		long company_id = ParamData.getLong(requestParam.getBodyNode(), "company_id");

		TItov_shop4s_user tItov_shop4s_user=new TItov_shop4s_user();
		tItov_shop4s_user.setAccount_id(id);
		tItov_shop4s_user.setAccount_name(account_name);
		tItov_shop4s_user.setAccount_password(account_password);
		tItov_shop4s_user.setNickname(nickname);
		tItov_shop4s_user.setReal_name(real_name);
		tItov_shop4s_user.setPerson_sex(person_sex);
		tItov_shop4s_user.setPerson_tel(person_tel);
		tItov_shop4s_user.setPerson_email(person_email);
		tItov_shop4s_user.setCompany_id(company_id);
		
		try{
			logger.info("updateUser4sinfo.upd 更新4s用户开始");
			int count = tItov_shop4sService.updateUser4sId(tItov_shop4s_user);
			logger.info("updateUser4sinfo.upd 更新4s用户结束");
			//这里特殊情况  需要同时更新两张表 account personal 故count正确结果应该返回2, 
			//在下面返回报文的result中-1是为避免itov中的Controller取得的result不为1而报错
			responseBodyJson.writeNumberField("result", count-1);
		}catch(Exception e)
		{	
			logger.info("myfault------->start");
			e.printStackTrace();
			logger.info("myfault------->end");
			logger.info("updateUser4s更新4s用户");
		}
		
	}
}
