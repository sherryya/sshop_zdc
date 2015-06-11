package com.tmount.business.user.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsUserAddress;
import com.tmount.db.user.vo.UsUserAddressExpl;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取用户地址数据列表。
 * @author lugz
 *
 */
@Controller
public class UserAddressGet extends ControllerBase{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "user.address.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		Date updateTime = ParamData.getDate(requestParam.getBodyNode(), "update_time", null);
		UsUserAddress usUserAddressParam = new UsUserAddress();
		usUserAddressParam.setUserId(userId);
		if (updateTime != null) {
			usUserAddressParam.setUpdateTime(updateTime);
		}
		List<UsUserAddressExpl> usUserAddressList = userService.getUsUserAddressListByUserId(usUserAddressParam);

		responseBodyJson.writeArrayFieldStart("address_list");
		if (usUserAddressList.size() > 0) {
			UsUserAddressExpl usUserAddressExpl;
			Iterator<UsUserAddressExpl> it = usUserAddressList.iterator();
			while (it.hasNext()) {
				usUserAddressExpl = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("orders", usUserAddressExpl.getOrders());
				responseBodyJson.writeStringField("post_addr", usUserAddressExpl.getPostAddr());
				responseBodyJson.writeNumberField("province_code", usUserAddressExpl.getProvinceCode());
				responseBodyJson.writeStringField("province_name", usUserAddressExpl.getProvinceName());
				responseBodyJson.writeNumberField("city_code", usUserAddressExpl.getCityCode());
				responseBodyJson.writeStringField("city_name", usUserAddressExpl.getCityName());
				responseBodyJson.writeNumberField("area_code", usUserAddressExpl.getAreaCode());
				responseBodyJson.writeStringField("area_name", usUserAddressExpl.getAreaName());
				responseBodyJson.writeStringField("address", usUserAddressExpl.getAddress());
				responseBodyJson.writeStringField("post_code", usUserAddressExpl.getPostCode());
				responseBodyJson.writeStringField("content_phone", usUserAddressExpl.getContentPhone());
				responseBodyJson.writeStringField("mobile", usUserAddressExpl.getMobile());
				responseBodyJson.writeStringField("user_name", usUserAddressExpl.getUserName());
				responseBodyJson.writeNumberField("arrival_time", usUserAddressExpl.getArrivalTime());
				responseBodyJson.writeStringField("arrival_name", usUserAddressExpl.getArrivalName());
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
	}
}
