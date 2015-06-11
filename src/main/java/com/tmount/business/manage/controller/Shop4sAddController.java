package com.tmount.business.manage.controller;

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
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 插入4s店信息
 * 
 * @author
 * 
 */
@Controller
public class Shop4sAddController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItov_shop4sService tItov_shop4sService;

	@RequestMapping(value = "shop4sInfomation.add")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String shop4s_name = ParamData.getString(requestParam.getBodyNode(), "shop4s_name");
		String shop4s_address = ParamData.getString(requestParam.getBodyNode(), "shop4s_address");
		String shop4s_proId = ParamData.getString(requestParam.getBodyNode(), "shop4s_proId");
		String shop4s_cId = ParamData.getString(requestParam.getBodyNode(), "shop4s_cId");
		String shop4s_dId = ParamData.getString(requestParam.getBodyNode(), "shop4s_dId");
		String shop4s_principal = ParamData.getString(requestParam.getBodyNode(), "shop4s_principal");
		String shop4s_tel = ParamData.getString(requestParam.getBodyNode(), "shop4s_tel");
		String shop4s_isuse = ParamData.getString(requestParam.getBodyNode(), "shop4s_isuse");
		String shop4s_note = ParamData.getString(requestParam.getBodyNode(), "shop4s_note");
		
		TItov_shop4s_manage tItov_shop4s_manage=new TItov_shop4s_manage();
		tItov_shop4s_manage.setShop4s_name(shop4s_name);
		tItov_shop4s_manage.setShop4s_address(shop4s_address);
		tItov_shop4s_manage.setShop4s_province_id(shop4s_proId);
		tItov_shop4s_manage.setShop4s_city_id(shop4s_cId);
		tItov_shop4s_manage.setShop4s_country_id(shop4s_dId);
		tItov_shop4s_manage.setShop4s_principal(shop4s_principal);
		tItov_shop4s_manage.setShop4s_tel(shop4s_tel);
		tItov_shop4s_manage.setShop4s_isuse(shop4s_isuse);
		tItov_shop4s_manage.setShop4s_note(shop4s_note);
		
		
		//插入主播信息
		int count = tItov_shop4sService.insertShops4(tItov_shop4s_manage);
		responseBodyJson.writeNumberField("result", count);
		
		
	}

}
