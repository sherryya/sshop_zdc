package com.tmount.business.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.driver.service.DriverService;
import com.tmount.business.user.service.DriversServices;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.user.dto.UsDriversInfo;
import com.tmount.db.user.dto.UsDriversSign;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class TradeDriversSignAdd extends ControllerBase {
	@Autowired
	private DriversServices driversService;
	@Autowired
	private DriverService driverService;
	@RequestMapping(value = "DriversSign.Add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String ds_drivers_id = new String(ParamData.getString(
				requestParam.getBodyNode(), "ds_drivers_id"));// 驾驶员ID
		String ds_car_id = new String(ParamData.getString(
				requestParam.getBodyNode(), "ds_car_id"));// 驾驶车量ID
		String ds_type = new String(ParamData.getString(
				requestParam.getBodyNode(), "ds_type"));// 类型 1：签到 2： 签退
		String ds_note = new String(ParamData.getString(
				requestParam.getBodyNode(), "ds_note"));// 备注
		String ds_flag = new String(ParamData.getString(
				requestParam.getBodyNode(), "ds_flag"));// 是否继续签到 首次签到时默认传0
														// 如果error_code=10016005（车不对应，是否继续签到）
														// 那么传1
		String driverSign_id = driversSign(ds_drivers_id, ds_car_id, ds_type, ds_note,
				ds_flag);
		if (driverSign_id.equals("-1")) {
			throw new ShopBusiException(ShopBusiErrorBundle.DRIVERS_CAR_EQUALS,
					new Object[] {});
		}
		 responseBodyJson.writeStringField("result", "OK");
		 UsDriversSign d=new UsDriversSign();
		 d.setDs_id( Integer.valueOf(driverSign_id) );
		 UsDriversSign ds=driversService.getDriversSignTime(d);//得到签到时间
		 CarInfo car=  driverService.getCarInfoByCarID( String.valueOf(ds_car_id));
		 responseBodyJson.writeNumberField("car_id", Integer.valueOf(ds_car_id) );
		 responseBodyJson.writeStringField("car_num", car.getCarnum());
		 responseBodyJson.writeStringField("car_name", car.getCarname());
		 responseBodyJson.writeStringField("sign_time",ds.getDs_time().toLocaleString() );//应该取数据库里的签到时间
	}

	public String driversSign(String ds_drivers_id, String ds_car_id,
			String ds_type, String ds_note, String ds_flag)
			throws ShopBusiException {
		if (StringUtils.isEmpty(ds_drivers_id)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "驾驶员信息" });
		}
		if (StringUtils.isEmpty(ds_car_id)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "驾驶车量" });
		}
		if (StringUtils.isEmpty(ds_type)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "类别" });
		}
		//GuidCreator myGUID = new GuidCreator();
		//String ds_id = myGUID.createNewGuid(GuidCreator.FormatString);
		UsDriversSign usDriversSign = new UsDriversSign();
		usDriversSign.setDs_car_id(Integer.valueOf(ds_car_id));
		usDriversSign.setDs_drivers_id(Integer.valueOf(ds_drivers_id));
		usDriversSign.setDs_type(Integer.valueOf(ds_type));
		usDriversSign.setDs_note(ds_note);
		if (ds_type.equals("1"))// 只有签到的时候才判断人车是否对应
		{
			if (ds_flag.equals("0"))// 首次签到
			{
				UsDriversInfo usDriversInfo = driversService.getDriversIsExists(usDriversSign);// 判断人车是否对应
				if (usDriversInfo == null) {
					return "-1";
				}
			}
		}
		Integer driverSign_id=0;
		driverSign_id=driversService.getCommonTableID("driversign_id");//得到签到名ID
		System.out.println(driverSign_id+":driverSign_i");
		usDriversSign.setDs_id(driverSign_id);
		driversService.insertUsDriversMapper(usDriversSign);// 添加签到表
		return String.valueOf(driverSign_id);
	}

}
