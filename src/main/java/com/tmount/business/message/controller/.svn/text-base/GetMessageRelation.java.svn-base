package com.tmount.business.message.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.message.service.MessageService;
import com.tmount.db.message.dto.MessageRelation;
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
public class GetMessageRelation extends ControllerBase {
	@Autowired
	private MessageService messageService;
	
	
	@RequestMapping(value = "messageoption.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		Long account_id = ParamData.getLong(requestParam.getBodyNode(),
				"account_id");//账号id
		//1.根据账号id，查询全部信息设置的信息
		List <MessageRelation> list = messageService.getListByAccountId(account_id);
		responseBodyJson.writeArrayFieldStart("Data");
		for(MessageRelation messageRelation:list){
			responseBodyJson.writeStartObject();
			responseBodyJson.writeNumberField("message_id", messageRelation.getMessage_id());
			responseBodyJson.writeNumberField("is_valid", Integer.parseInt(messageRelation.getIs_valid()));
			responseBodyJson.writeStringField("message_name", messageRelation.getMessage_name());
			responseBodyJson.writeEndObject();
		}
		responseBodyJson.writeEndArray();
	}
}
