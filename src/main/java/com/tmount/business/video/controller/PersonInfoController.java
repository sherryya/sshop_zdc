package com.tmount.business.video.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.ptt.service.PttPersonalInfoByAgentidService;
import com.tmount.db.car.dto.TItovCar;
import com.tmount.db.ptt.dto.TItov_personal;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *车品牌数据同步接口
 * 
 * @author 
 * 
 */
@Controller
public class PersonInfoController extends ControllerBase {
	@Autowired
	private PttPersonalInfoByAgentidService pttPersonService;
	
	
	@RequestMapping(value = "PersonInfoByVoipAccount.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		String voipAccount = ParamData.getString(requestParam.getBodyNode(),"voipAccount");
		//String voipAccount="80888100000034";
		List<TItov_personal> personList = pttPersonService.selectAccountByVoipAccount(voipAccount);
		if(personList !=null && personList.size()>0)
		{
			TItov_personal person = personList.get(0);
			if(person != null)
			{
				double accountId = person.getAccount_id();
				responseBodyJson.writeNumberField("account_id", accountId);
				responseBodyJson.writeStringField("callid", person.getCallid());
				responseBodyJson.writeStringField("personal_real_name", person.getPersonal_real_name());
				responseBodyJson.writeStringField("personal_tel", person.getPersonal_tel());
				SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
				String a1 = dateformat1.format(person.getPersion_create_date());// 得到当前时间
				responseBodyJson.writeStringField("persion_create_date", a1);
				responseBodyJson.writeStringField("num", person.getNum() !=null?person.getNum().toString():"");
			}
		}
		
		
	}
}
