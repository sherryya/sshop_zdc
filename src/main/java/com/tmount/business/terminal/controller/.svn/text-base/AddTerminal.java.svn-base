package com.tmount.business.terminal.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.manage.service.TItov_shop4sService;
import com.tmount.business.terminal.service.TermianlService;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.db.manage.dto.TItov_shop4s_user;
import com.tmount.db.terminal.dto.TZdcTerminal;
import com.tmount.exception.ShopBusiException;
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
public class AddTerminal extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TermianlService termianlService;
	@Autowired
	private TerminalInfoService terminalService;
	@RequestMapping(value = "terminal.saveinfo")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String name = ParamData.getString(requestParam.getBodyNode(), "account_name");
		String terminal_name = ParamData.getString(requestParam.getBodyNode(), "terminal_name");
		String terminal_imei = ParamData.getString(requestParam.getBodyNode(), "terminal_imei");
		String terminal_device_type = ParamData.getString(requestParam.getBodyNode(), "terminal_device_type");
		String terminal_producer = ParamData.getString(requestParam.getBodyNode(), "terminal_producer");
		String terminal_binding_date = ParamData.getString(requestParam.getBodyNode(), "terminal_binding_date");
		
		List<TerminalInfo> list = terminalService.selectUserIdByImei(terminal_imei);
		if(list!=null && list.size()>0){
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
					new Object[] { "设备号已存在,无法添加" });
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dDate = null;
		try {
			dDate = format.parse(terminal_binding_date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		
		TZdcTerminal tZdcTerminal=new TZdcTerminal();
		tZdcTerminal.setTerminal_name(terminal_name);
		tZdcTerminal.setTerminal_imei(terminal_imei);
		tZdcTerminal.setTerminal_device_type(terminal_device_type);
		tZdcTerminal.setTerminal_producer(terminal_producer);
		tZdcTerminal.setTerminal_binding_date(dDate);
		
		
		try{
			logger.info("insertTerminalInfo.add 更新设备信息开始");
			int count = termianlService.insert(tZdcTerminal);
			logger.info("updateShop4sinfo.upd 更新设备信息结束");
			responseBodyJson.writeNumberField("result", count);
		}catch(Exception e)
		{	
			logger.info("myfault------->start");
			e.printStackTrace();
			logger.info("myfault------->end");
			logger.info("saveShop4sUser");
		}
		
	}
}
