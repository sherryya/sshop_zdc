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
import com.tmount.db.user.dao.UsPointGenMapper;
import com.tmount.db.user.dto.UsPointGen;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class UserPointGenListGet extends ControllerBase {
	@Autowired
	private UsPointGenMapper usPointGenMapper;

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		ArrayList<UsPointGen> userPointGenList = (ArrayList<UsPointGen>) usPointGenMapper.selectByUserId(userId);
		Collections.reverse(userPointGenList);
		
		
		responseBodyJson.writeArrayFieldStart("user_point_gen_list");
		if(userPointGenList.size()>0){
			UsPointGen usPointGen ;
			Iterator<UsPointGen> it = userPointGenList.iterator();
			while(it.hasNext()){
				usPointGen = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("login_accept", usPointGen.getLoginAccept());
				responseBodyJson.writeNumberField("user_id", usPointGen.getUserId());
				responseBodyJson.writeNumberField("mark_way_id", usPointGen.getMarkWayId());
				responseBodyJson.writeNumberField("ori_id", usPointGen.getOriId());
				responseBodyJson.writeStringField("ori_name", usPointGen.getOriName());
				responseBodyJson.writeNumberField("gen_point", usPointGen.getGenPoint());
				responseBodyJson.writeStringField("gen_date", requestParam.dateToString(usPointGen.getGenDate()));
				responseBodyJson.writeStringField("elf_date", requestParam.dateToString(usPointGen.getElfDate()));
				
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
		

	}

	@RequestMapping(value = "user.point.gen.list.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);

	}

}
