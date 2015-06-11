package com.tmount.business.ptt.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
@Controller
public class GetPersonalInfoByAgentid extends ControllerBase {
	@Autowired
	private PttPersonalInfoByAgentidService pttPersonalInfoByAgentid;
	@RequestMapping(value = "personalInfoByAgentid.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    sendData(request, response);
	}//GprsByPersonalTel
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		Long agentid = ParamData.getLong(	requestParam.getBodyNode(), "agentid");//  座席ID，4位正整数，由应用侧管理
        TItov_personal   tItov_personal =new  TItov_personal();
        tItov_personal=pttPersonalInfoByAgentid.selectByAgentid(Long.valueOf(agentid) );
        if(tItov_personal!=null)
        {
		responseBodyJson.writeStringField("personal_real_name", tItov_personal.getPersonal_real_name());
		responseBodyJson.writeStringField("personal_tel", tItov_personal.getPersonal_tel());
		responseBodyJson.writeStringField("account_id", tItov_personal.getAccount_id().toString());
		responseBodyJson.writeStringField("callid", tItov_personal.getCallid().toString());
		responseBodyJson.writeStringField("num", tItov_personal.getNum().toString());
		SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
		String a1 = dateformat1.format(tItov_personal.getPersion_create_date());// 得到当前时间
		responseBodyJson.writeStringField("persion_create_date", a1);
        }
        else
        {
        	throw new ShopBusiException(
					ShopBusiErrorBundle.VOIP_ERROR,null);
        }
	}
 

}

