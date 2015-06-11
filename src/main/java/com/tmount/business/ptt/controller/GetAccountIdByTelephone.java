package com.tmount.business.ptt.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.ptt.service.PttPersonalInfoByAgentidService;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 4s店需要的服务根据用户名即手机号码查询accountId
 * 20150310
 * @author Administrator
 *
 */
@Controller
public class GetAccountIdByTelephone extends ControllerBase {
	@Autowired
	private PttPersonalInfoByAgentidService pttPersonalInfoByAgentid;
	@RequestMapping(value = "getAccountIdByTelephone.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    sendData(request, response);
	}//GprsByPersonalTel
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String personal_tel = ParamData.getString(requestParam.getBodyNode(), "personal_tel");// 注册用户的手机号码
		UsAccount usaccount=pttPersonalInfoByAgentid.selectByPersonTel(personal_tel);
        if(usaccount!=null)
        {
		responseBodyJson.writeStringField("personal_real_name", usaccount.getAccount_name());
		//responseBodyJson.writeNumberField("account_id", usaccount.getAccount_id());
		responseBodyJson.writeStringField("account_id", usaccount.getAccount_id().toString());
        }
        else
        {
        	throw new ShopBusiException(
					ShopBusiErrorBundle.VOIP_ERROR,null);
        }
	}
 

}

