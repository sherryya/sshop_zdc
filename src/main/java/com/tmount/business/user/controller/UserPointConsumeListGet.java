package com.tmount.business.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.db.user.dao.UsPointConsumeMapper;
import com.tmount.db.user.dto.UsPointConsume;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class UserPointConsumeListGet extends ControllerBase {
	@Autowired
	private UsPointConsumeMapper usPointConsumeMapper;

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		ArrayList<UsPointConsume> userPointConsumeList = (ArrayList<UsPointConsume>) usPointConsumeMapper.selectByUserId(userId);
		Collections.reverse(userPointConsumeList);
		
		responseBodyJson.writeArrayFieldStart("user_point_consume_list");
		if(userPointConsumeList.size()>0){
			UsPointConsume usPointConsume ;
			Iterator<UsPointConsume> it = userPointConsumeList.iterator();
			while(it.hasNext()){
				usPointConsume = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("login_accept", usPointConsume.getLoginAccept());
				responseBodyJson.writeNumberField("user_id", usPointConsume.getUserId());
				responseBodyJson.writeNumberField("mark_way_id", usPointConsume.getMarkWayId());
				responseBodyJson.writeNumberField("ori_id", usPointConsume.getOriId());
				responseBodyJson.writeNumberField("use_point", usPointConsume.getUsePoint());
				responseBodyJson.writeStringField("use_date", requestParam.dateToString(usPointConsume.getUseDate()));
		
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
		

	}

	@RequestMapping(value = "user.point.consume.list.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);

	}

}
