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
import com.tmount.db.ptt.dto.TItov_personal;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 修改用户的基本信息
 * @author dell
 *
 */
@Controller
public class updPersonalInfo extends ControllerBase {
	@Autowired
	private PttPersonalInfoByAgentidService pttPersonalInfoByAgentidService;
	@RequestMapping(value = "updPersonalInfo.upd")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String account_id = ParamData.getString(requestParam.getBodyNode(), "account_id");//  账号ID
		String personal_real_name = ParamData.getString(requestParam.getBodyNode(), "personal_real_name");//  
		String personal_sex = ParamData.getString(requestParam.getBodyNode(), "personal_sex");//  
		String personal_email = ParamData.getString(requestParam.getBodyNode(), "personal_email");//  
		String personal_qq = ParamData.getString(requestParam.getBodyNode(), "personal_qq");//  
		String personal_age = ParamData.getString(requestParam.getBodyNode(), "personal_age");//  
		TItov_personal tTItov_personal=new TItov_personal();
		tTItov_personal.setAccount_id(Long.valueOf(account_id));
		tTItov_personal.setPersonal_age( Integer.valueOf(personal_age));
		tTItov_personal.setPersonal_email(personal_email);
		tTItov_personal.setPersonal_qq(personal_qq);
		tTItov_personal.setPersonal_real_name(personal_real_name);
		tTItov_personal.setPersonal_sex(personal_sex);
		try
		{
		    pttPersonalInfoByAgentidService.update(tTItov_personal);
        	responseBodyJson.writeStringField("return", "0");
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK,null);
		}
	}
}
