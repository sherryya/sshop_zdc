package com.tmount.business.carhot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.car.dto.UserRelationCarInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class CarHotInsertCarInfo extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "carHotInsert.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		long account_id = ParamData.getLong(requestParam.getBodyNode(), "account_id");  //用户account_id
		String car_color = ParamData.getString(requestParam.getBodyNode(), "car_color");  //车辆颜色
		int car_type = ParamData.getInt(requestParam.getBodyNode(), "car_type");   //车辆类型
		String car_plate_number = ParamData.getString(requestParam.getBodyNode(), "car_plate_number");  //车牌号
		String car_carcase_num = ParamData.getString(requestParam.getBodyNode(), "car_carcase_num");   //车架号
		String car_engine_num = ParamData.getString(requestParam.getBodyNode(), "car_engine_num");  //发动机号
		String car_driving_license_date = ParamData.getString(requestParam.getBodyNode(), "car_driving_license_date");  //车检日期
		String city_code = ParamData.getString(requestParam.getBodyNode(), "city_code");   //车辆所在城市
		CarInfo carinfo = new CarInfo();
		carinfo.setAccount_id(account_id);
		carinfo.setCar_color(car_color);
		carinfo.setCar_driving_license_date(car_driving_license_date);
		carinfo.setCar_type(car_type);
		carinfo.setCar_plate_number(car_plate_number);
		if(StringUtils.isNotBlank(car_carcase_num))
		{
			carinfo.setCar_carcase_num(car_carcase_num);
		}
		if(StringUtils.isNotBlank(city_code))
		{
			carinfo.setCity_code(city_code);
		}
		carinfo.setCar_engine_num(car_engine_num);
		Long user_id = userService.getUserMessage(account_id);    //根据account_id查询user_id
		if(null!=user_id)
		{
			List<CarInfo> carList = carInfoService.getCarInfoByPlateNum(carinfo);
			if(carList==null ||(carList!=null && carList.size()<=0))
			{
				
				List<UserRelationCarInfo> list = userService.getRelationCarInfo(user_id);  //根据user_id 查询是否存在绑定关系
				int i=0;
				for(UserRelationCarInfo terminalCar:list)
				{
					if("1".equals(terminalCar.getIs_default()))
					{
						i++;
					}
				}
				UserRelationCarInfo userRelationCarInfo = new UserRelationCarInfo();
			    //////集群 20150421
				int car_id = carInfoService.queryId("car_id")+1;  //查询数据库序列值
				TestUpd testupd = new TestUpd();
				testupd.setName("car_id");
				testupd.setValue(car_id);
				carInfoService.updtestupd(testupd);    //更新数据库的序列值			
				/////end
				carinfo.setCar_id(car_id);
				carInfoService.carhotInsert(carinfo);
				userRelationCarInfo.setCar_id(car_id);
				userRelationCarInfo.setUser_id(user_id.intValue());
				if(i>0)
				{
					userRelationCarInfo.setIs_default("0");
					userService.insertRelationUserAndCar(userRelationCarInfo);   //terminal_car表插入数据
				}else
				{
					userRelationCarInfo.setIs_default("1");
					userService.insertRelationUserAndCar(userRelationCarInfo);
				}
				responseBodyJson.writeNumberField("result", 1);  //表示插入成功
			
				
			}else
			{
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
						new Object[] { "此车牌号已存在" });
			}
		
		}else
		{
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
					new Object[] { "不存在此用户" });
		}
		
		
		
	}
}
