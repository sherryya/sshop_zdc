package com.tmount.business.reserve.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.manage.service.TItov_shop4sService;
import com.tmount.business.reserve.service.ReserveService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.db.manage.dto.TItov_shop4s_user;
import com.tmount.db.reserve.dto.TItovReservePlan;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 更新主播信息
 * 
 * @author
 * 
 */
@Controller
public class ReservePlanAdd extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private ReserveService reserveService;

	@RequestMapping(value = "reservePlan.add")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String name = ParamData.getString(requestParam.getBodyNode(), "account_name");

				
		String begin_time = ParamData.getString(requestParam.getBodyNode(), "begin_time");
		String end_time = ParamData.getString(requestParam.getBodyNode(), "end_time");
		int num = ParamData.getInt(requestParam.getBodyNode(), "num");
		String content = ParamData.getString(requestParam.getBodyNode(), "content");


		TItovReservePlan tItovReservePlan=new TItovReservePlan();
		tItovReservePlan.setBegin_time(begin_time);
		tItovReservePlan.setEnd_time(end_time);
		tItovReservePlan.setNum(num);
		tItovReservePlan.setContent(content);
		
		
		try{
			logger.info("reservePlan.add 更新预约计划开始");
			int count = reserveService.saveReservePlan(tItovReservePlan);
			logger.info("reservePlan.add 更新预约计划结束");
			responseBodyJson.writeNumberField("result", count);
		}catch(Exception e)
		{	
			logger.info("myfault------->start");
			e.printStackTrace();
			logger.info("myfault------->end");
			logger.info("savereservePlan");
		}
		
	}
}
