package com.tmount.business.sys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.sys.service.SysService;
import com.tmount.config.AppPropertiesConfig;
import com.tmount.db.sys.dto.SyClientVer;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 查询客户端版本表。
 * @author lugz
 *
 */
@Controller
public class SyClientVerGet extends ControllerBase {
	@Autowired
	private SysService sysService;

	@Autowired
	AppPropertiesConfig appPropertiesConfig;

	@RequestMapping(value = "sys.client.ver.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		int clientId = ParamData.getInt(requestParam.getBodyNode(),"client_id");
		SyClientVer syClientVer = sysService.selectSyClientVerByPK(clientId);
		if (syClientVer != null) {
			StringBuilder urlName = new StringBuilder();
			urlName.append(appPropertiesConfig.getAppPropertiesConfig().getProperty("progUrlHome"));
			urlName.append("/");
			urlName.append(syClientVer.getUpdateUrl());

			responseBodyJson.writeNumberField("client_id", syClientVer.getClientId());
			responseBodyJson.writeNumberField("company_id", syClientVer.getCompanyId());
			responseBodyJson.writeStringField("client_name", syClientVer.getClientName());
			responseBodyJson.writeStringField("client_type", syClientVer.getClientType());
			responseBodyJson.writeNumberField("client_ver", syClientVer.getClientVer());
			responseBodyJson.writeStringField("ver_desc", syClientVer.getVerDesc());
			responseBodyJson.writeStringField("update_now", syClientVer.getUpdateNow());
			responseBodyJson.writeStringField("update_url", urlName.toString());
			responseBodyJson.writeStringField("update_desc", syClientVer.getUpdateDesc());
		}
	}
}
