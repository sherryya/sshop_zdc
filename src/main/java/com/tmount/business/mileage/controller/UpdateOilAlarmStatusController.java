package com.tmount.business.mileage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.mileage.service.ZdcOilAlarmService;

import com.tmount.db.mileage.dto.TZdcFaultCodeLog;
import com.tmount.db.mileage.dto.TZdcOilAlarm;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 得到最新CAN数据流 也就是设备的最后那条
 * 
 * @author
 * 
 */
@Controller
public class UpdateOilAlarmStatusController extends ControllerBase {
	@Autowired
	private ZdcOilAlarmService oilService;

	@RequestMapping(value = "updateOilAlarmStatus.upd")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {

		String id = ParamData.getString(requestParam.getBodyNode(), "id");//id
		String status = ParamData.getString(requestParam.getBodyNode(), "status");  //状态
		TZdcOilAlarm tzdcOil = new TZdcOilAlarm();
		tzdcOil.setId(Long.valueOf(id));
		tzdcOil.setStatus(status);
		int count = oilService.updateByPrimaryKeySelective(tzdcOil);//更新是否已读的状态
		responseBodyJson.writeNumberField("return", count);//如果返回1修改成功否则修改状态失败
	}
}
