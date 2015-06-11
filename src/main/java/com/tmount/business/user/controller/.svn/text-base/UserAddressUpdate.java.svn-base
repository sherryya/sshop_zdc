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
 * 用户地址修改
 * @author lugz
 *
 */
@Controller
public class UserAddressUpdate extends ControllerBase {
	@Autowired
	private UserService userService;

	@Autowired
	private PubService pubService;

	@RequestMapping(value = "user.address.update")
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
		usUserAddress.setOrders(ParamData.getInt(requestParam.getBodyNode(), "orders"));
		usUserAddress.setPostAddr(ParamData.getString(requestParam.getBodyNode(), "post_addr", "0"));	//0 不默认, 1 默认
		if (requestParam.getBodyNode().get("province_code") != null) {
			usUserAddress.setProvinceCode(ParamData.getInt(requestParam.getBodyNode(), "province_code"));
		}
		if (requestParam.getBodyNode().get("city_code") != null) {
			usUserAddress.setCityCode(ParamData.getInt(requestParam.getBodyNode(), "city_code"));
		}
		if (requestParam.getBodyNode().get("area_code") != null) {
			usUserAddress.setAreaCode(ParamData.getInt(requestParam.getBodyNode(), "area_code"));
		}
		if (requestParam.getBodyNode().get("address") != null) {
			usUserAddress.setAddress(ParamData.getString(requestParam.getBodyNode(), "address", null));
		}
		if (requestParam.getBodyNode().get("post_code") != null) {
			usUserAddress.setPostCode(ParamData.getString(requestParam.getBodyNode(), "post_code", null));
		}
		if (requestParam.getBodyNode().get("content_phone") != null) {
			usUserAddress.setContentPhone(ParamData.getString(requestParam.getBodyNode(), "content_phone", null));
		}
		if (requestParam.getBodyNode().get("mobile") != null) {
			usUserAddress.setMobile(ParamData.getString(requestParam.getBodyNode(), "mobile", null));
		}
		if (requestParam.getBodyNode().get("user_name") != null) {
			usUserAddress.setUserName(ParamData.getString(requestParam.getBodyNode(), "user_name", null));
		}
		if (requestParam.getBodyNode().get("arrival_time") != null) {
			usUserAddress.setArrivalTime(ParamData.getInt(requestParam.getBodyNode(), "arrival_time"));
		}
		usUserAddress.setCreateTime(dbTime.getDbTime());
		usUserAddress.setUpdateTime(dbTime.getDbTime());

		userService.updateUsUserAddress(usUserAddress);
	}
}
