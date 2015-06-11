package com.tmount.business.car.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.db.car.dto.CarDelete;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class UserCarSetDefault  extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;

	@RequestMapping(value = "defaultcar.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		int car_id_int = ParamData.getInt(requestParam.getBodyNode(), "car_id");
		String car_id = String.valueOf(car_id_int);
		String account_id = String.valueOf((ParamData.getInt(requestParam.getBodyNode(), "account_id")));
		CarDelete carDelete = new CarDelete();
		carDelete.setAccount_id(Integer.valueOf(account_id));
		carDelete.setCar_id(Integer.valueOf(car_id));
		carInfoSetDefault(carDelete);
		responseBodyJson.writeStringField("result", "OK");
	}
	public void carInfoSetDefault(CarDelete carDelete)
			throws ShopBusiException {
		CarDelete cd = carInfoService.getIsExistCarInfoByCarIDAndAccount(carDelete);// 判断车辆是否 存在
		if (cd == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXITS,new Object[] { "车辆信息" });
		} else {
			carInfoService.deleteDefaultCarByUserID(carDelete);//取消默认车辆 
			carInfoService.updateDefaultCarAccountByCarID(cd);//设置默认车辆信息
		}
	}
}

