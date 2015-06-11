package com.tmount.business.terminal.controller;

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
import com.tmount.business.terminal.service.TermianlService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.db.manage.dto.TItov_shop4s_user;
import com.tmount.db.terminal.dto.TZdcTerminal;
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
public class UpdTerminalController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TermianlService terminalService;

	@RequestMapping(value = "updateTerminalInfo.upd")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String name = ParamData.getString(requestParam.getBodyNode(), "account_name");
		long id = ParamData.getInt(requestParam.getBodyNode(), "id");
		String terminal_name = ParamData.getString(requestParam.getBodyNode(), "terminal_name");
		String terminal_imei = ParamData.getString(requestParam.getBodyNode(), "terminal_imei");
		String terminal_device_type = ParamData.getString(requestParam.getBodyNode(), "terminal_device_type");
		String terminal_producer = ParamData.getString(requestParam.getBodyNode(), "terminal_producer");

		TZdcTerminal tZdcTerminal=new TZdcTerminal();
		tZdcTerminal.setUser_id(id);
		tZdcTerminal.setTerminal_name(terminal_name);
		tZdcTerminal.setTerminal_imei(terminal_imei);
		tZdcTerminal.setTerminal_device_type(terminal_device_type);
		tZdcTerminal.setTerminal_producer(terminal_producer);
		int counts=terminalService.selectCountByStatus((int)id);
		if(counts>0){
			responseBodyJson.writeNumberField("result",-99);
		}else{
			try{
				logger.info("updateTerminalInfo.upd 更新设备信息开始");
				int count = terminalService.updateTerminal(tZdcTerminal);
				logger.info("updateTerminalInfo.upd 更新设备信息结束");
				//这里特殊情况  需要同时更新两张表 account personal 故count正确结果应该返回2, 
				//在下面返回报文的result中-1是为避免itov中的Controller取得的result不为1而报错
				responseBodyJson.writeNumberField("result", count);
			}catch(Exception e)
			{	
				logger.info("myfault------->start");
				e.printStackTrace();
				logger.info("myfault------->end");
				logger.info("updateUser4s更新4s用户");
			}
		}
	}
}
