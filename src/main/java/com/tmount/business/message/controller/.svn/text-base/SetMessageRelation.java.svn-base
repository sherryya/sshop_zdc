package com.tmount.business.message.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.tmount.business.message.service.MessageService;
import com.tmount.db.message.dto.MessageRelation;
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
public class SetMessageRelation extends ControllerBase {
	@Autowired
	private MessageService messageService;
	Logger logger = Logger.getLogger(SetMessageRelation.class.getName());
	
	@RequestMapping(value = "messageoption.set")
	@Override
	@ResponseBody
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		/*JsonNode jsonNode = requestParam.getBodyNode();
		Long account_id = jsonNode.get("account_id").longValue();
		System.out.println(account_id);
		JsonNode Data =  jsonNode.get("Data");
		Iterator dataIterator  = Data.fieldNames();
		while(dataIterator.hasNext()){
			System.out.println(dataIterator.next());
		}
		
*/
		
		Long account_id = ParamData.getLong(requestParam.getBodyNode(),
				"account_id");//账号id
		//1.根据账号id，删除全部设置
		messageService.deleteRelation(account_id);
		JsonNode jsonNode = ParamData.getJsonNode(requestParam.getBodyNode(),
				"Data");
		Iterator dataIterator = jsonNode.elements();
		while(dataIterator.hasNext()){
			logger.info("startData");
			JsonNode message = (JsonNode)dataIterator.next();
			Long message_id = message.get("message_id").longValue();
			int is_valid_value =message.get("is_valid").intValue();
			String is_valid = String.valueOf(is_valid_value);
			//2.添加
			MessageRelation messageRelation = new MessageRelation();
			messageRelation.setMessage_id(message_id);
			messageRelation.setAccount_id(account_id);
			messageRelation.setIs_valid(is_valid);
			messageService.insert(messageRelation);
			logger.info("endData");
		}
		
	}
}
