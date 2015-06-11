package com.tmount.business.terminal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.manage.service.TItov_shop4sService;
import com.tmount.business.terminal.service.TermianlService;
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
 * 删除设备信息
 * 
 * @author
 * 
 */
@Controller
public class DelTerminalController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TermianlService terminalService;

	@RequestMapping(value = "deleteTerminalInfo.upd")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String name = ParamData.getString(requestParam.getBodyNode(), "account_name");
		int id = ParamData.getInt(requestParam.getBodyNode(), "id");

		int count=terminalService.selectCountByStatus(id);
		if(count>0){
			responseBodyJson.writeNumberField("result",-99);
		}else{
			try{
				logger.info("delTerminalInfo.upd 更新设备信息开始");
				int count2 = terminalService.delTerminal(id);
				logger.info("delTerminalInfo.upd 更新设备信息结束");
				//这里特殊情况  需要同时更新两张表 account personal 故count正确结果应该返回2, 
				//在下面返回报文的result中-1是为避免itov中的Controller取得的result不为1而报错
				responseBodyJson.writeNumberField("result", count2);
			}catch(Exception e)
			{	
				e.printStackTrace();
				logger.info("deleteTerminalInfo更新设备");
				responseBodyJson.writeNumberField("result", 0);  //表示删除失败
			}
		}
	}
}
