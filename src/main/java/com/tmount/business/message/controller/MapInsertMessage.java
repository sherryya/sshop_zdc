package com.tmount.business.message.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.message.service.MessageService;
import com.tmount.db.message.dto.TItovMessage;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *消息设置信息查询
 * 
 * @author 
 * 
 */
@Controller
public class MapInsertMessage extends ControllerBase {
	@Autowired
	private MessageService messageService;
	
	
	@RequestMapping(value = "mapmessage.insert")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String phone_no = ParamData.getString(requestParam.getBodyNode(),
				"phone_no");
		String map_point = ParamData.getString(requestParam.getBodyNode(),
				"map_point");
		Long account_id = ParamData.getLong(requestParam.getBodyNode(),
				"account_id");//账号id

		if("".equals(account_id)&&account_id==null){
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] {"账号id"});
		}
		TItovMessage titovmessage = new TItovMessage();
		titovmessage.setAccountId(account_id);
		titovmessage.setMessageContent(map_point);
		titovmessage.setMessageOption("9");//导航信息
		titovmessage.setIsNew("0");
		messageService.insertMessage(titovmessage);
		responseBodyJson.writeBooleanField("flag", true);
	}
}
