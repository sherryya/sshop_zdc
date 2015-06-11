package com.tmount.business.user.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.pub.service.PubService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.db.user.dto.UsUserAddress;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 用户地址增加
 * @author lugz
 *
 */
@Controller
public class UserAddressAdd extends ControllerBase {
	@Autowired
	private UserService userService;

	@Autowired
	private PubService pubService;

	@RequestMapping(value = "user.address.add")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		DbTime dbTime = pubService.getDbTime();
		UsUserAddress usUserAddress = new UsUserAddress();
		usUserAddress.setUserId(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		usUserAddress.setPostAddr(ParamData.getString(requestParam.getBodyNode(), "post_addr"));
		usUserAddress.setProvinceCode(ParamData.getInt(requestParam.getBodyNode(), "province_code"));
		usUserAddress.setCityCode(ParamData.getInt(requestParam.getBodyNode(), "city_code"));
		usUserAddress.setAreaCode(ParamData.getInt(requestParam.getBodyNode(), "area_code"));
		usUserAddress.setAddress(ParamData.getString(requestParam.getBodyNode(), "address"));
		usUserAddress.setPostCode(ParamData.getString(requestParam.getBodyNode(), "post_code"));
		usUserAddress.setContentPhone(ParamData.getString(requestParam.getBodyNode(), "content_phone"));
		usUserAddress.setMobile(ParamData.getString(requestParam.getBodyNode(), "mobile"));
		usUserAddress.setUserName(ParamData.getString(requestParam.getBodyNode(), "user_name"));
		usUserAddress.setArrivalTime(ParamData.getInt(requestParam.getBodyNode(), "arrival_time"));
		usUserAddress.setCreateTime(dbTime.getDbTime());
		usUserAddress.setUpdateTime(dbTime.getDbTime());

		userService.insertUsUserAddress(usUserAddress);
	}
}
