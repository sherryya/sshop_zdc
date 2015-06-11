package com.tmount.business.InsertInfoByImei.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.InsertInfoByImei.service.TZdcImeiterRelationService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.user.service.UserService;
import com.tmount.db.terminalAccount.dto.TZdcDevices;
import com.tmount.db.terminalAccount.dto.TZdcImeiterRelation;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 判断终端和车机 是否有绑定关系，如果没有就添加，如果有返回 终端ID
 * 
 * @author
 * 
 */
@Controller
public class InsertImeiTerRelationController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TZdcImeiterRelationService tZdcImeiterRelationService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "InsertImeiTerRelation.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String imei = ParamData.getString(requestParam.getBodyNode(), "imei");//
		String ter = ParamData.getString(requestParam.getBodyNode(), "ter");//
		TZdcImeiterRelation tZdcImeiterRelation = new TZdcImeiterRelation();
		try {
			List<TZdcDevices> tZdcDevices = tZdcImeiterRelationService.selectByDeviceId(ter);
			if (tZdcDevices.isEmpty()) {
				responseBodyJson.writeNumberField("return", 2);// 设备号不存在
				// 1 :判断ter 是否存在
			} else {
				List<TZdcImeiterRelation> devices = tZdcImeiterRelationService.selectByTerminal(ter);
				if (!devices.isEmpty()) {
					responseBodyJson.writeNumberField("return", 3);
				} else {

					List<TZdcImeiterRelation> terminalList = tZdcImeiterRelationService.selectByIMEI(imei);
					// 2:判断IMEI 是否已经添加
					if (null == terminalList || (null != terminalList && terminalList.size() <= 0)) {
						tZdcImeiterRelation.setImei(imei);
						tZdcImeiterRelation.setTerminal(ter);
						// 删除原来可能存的设备关系（不用IMEI绑定同一个设备） 这样就需要删除原来的
						tZdcImeiterRelationService.deleteByPrimaryKey(tZdcImeiterRelation);
						int ret = tZdcImeiterRelationService.insert(tZdcImeiterRelation);
						// 3:添加关系
						if (ret > 0) {
							responseBodyJson.writeNumberField("return", 1);
							responseBodyJson.writeStringField("ter", ter);
							UsAccount sAccount=new UsAccount();
							sAccount.setAccount_name(ter);
							sAccount=userService.getUsUserInfo(sAccount);
							if(sAccount!=null)
							{
								responseBodyJson.writeNumberField("account_id", sAccount.getAccount_id());
							}
							
						} else {
							responseBodyJson.writeNumberField("return", 0);
						}
					} else {
						// 4:修改终端对应的IMEI号 保证一个终端对应一个IMEI号 换车机可以用
						if (!terminalList.get(0).getTerminal().equalsIgnoreCase(ter))
						{
							tZdcImeiterRelationService.updateByTerID(ter);
						}
						responseBodyJson.writeNumberField("return", 1);
						responseBodyJson.writeStringField("ter", ter);
						UsAccount sAccount=new UsAccount();
						sAccount.setAccount_name(ter);
						sAccount=userService.getUsUserInfo(sAccount);
						if(sAccount!=null)
						{
							responseBodyJson.writeNumberField("account_id", sAccount.getAccount_id());
						}
					}
				}
			}
		} catch (Exception e) {
			responseBodyJson.writeNumberField("return", 0);
		}
	}
}
