package com.tmount.business.message.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.message.service.MessageService;
import com.tmount.db.message.dto.TItovMessage;
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
public class GetCurrentMessage extends ControllerBase {
	@Autowired
	private MessageService messageService;
	
	
	@RequestMapping(value = "newshowmessage.get")
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
		//1.从t_itov_message表中查询is_new=0的数据
		responseBodyJson.writeArrayFieldStart("Data");
		List<TItovMessage> list = messageService.getNewMessage(account_id);
		for(TItovMessage message:list){
			responseBodyJson.writeStartObject();
			responseBodyJson.writeStringField("message_code",message.getMessageCode());
			Date date = message.getMessageTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String message_time = sdf.format(date);
			responseBodyJson.writeStringField("message_time",message_time);
			responseBodyJson.writeStringField("message_content",message.getMessageContent());
			responseBodyJson.writeStringField("is_new","0");
			responseBodyJson.writeNumberField("message_account", message.getMessage_account());
			responseBodyJson.writeEndObject();
			Long id = message.getId();
			//2.根据id，将is_new更新为1
			messageService.updateIsNew(id);
		}
		responseBodyJson.writeEndArray();
		
	}
}
