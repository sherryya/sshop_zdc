package com.tmount.business.integration.controller;

import java.io.IOException;
import java.util.Date;
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
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.business.itov.platform.inter.launch.App_FusionServices;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.TItov_car_brand_golo3;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.car.dto.UserRelationCarInfo;
import com.tmount.db.user.dto.UsUser;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/*
 * 
 * 激活元征用户接口
 */
@Controller
public class ActiveLaunchController extends ControllerBaseByLogin {
	private Integer platform = 11;// ios---10,android----11平台标示
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private TerminalInfoService terminalInfoService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "launch.active")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@SuppressWarnings("unused")
	@Override
	protected void doService(RequestParam requestParam,JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		int car_id = ParamData.getInt(requestParam.getBodyNode(), "car_id");// carid
		int account_id = ParamData.getInt(requestParam.getBodyNode(),	"account_id");
		String device_sn = ParamData.getString(requestParam.getBodyNode(),	"device_sn");// device_sn
		String device_pwd = ParamData.getString(requestParam.getBodyNode(),	"device_pwd");// device_pwd
		String server_url = "http://" + request.getServerName() + ":"	+ request.getServerPort() + "/";
		platform = requestParam.getRequestDataHeader().getClientPlatform();
		System.out.println("device_sn:"+device_sn);
		String user_id_str = terminalInfoService.getUserIdByDeviceSn(device_sn);
		System.out.println("user_id_str:"+user_id_str);
		if (StringUtils.isNotEmpty(user_id_str)) {
			int user_id = Integer.parseInt(user_id_str);
			// 2.根据user_id去t_itov_terminal_car，看看是否已经存在绑定关系，如果存在，提示。否则，继续
			String car_id_flag = userService.getRelationCarInfoCompare(user_id);// 提示设备是否存在
			if (StringUtils.isEmpty(car_id_flag)) {// 该设备ID未被绑定
				String ret = App_FusionServices.getReg_User(platform);
				if (ret.length() > 2) {
					String[] arr = ret.split(",");
					if (arr.length > 1) {
						String access_id = arr[0];
						String access_token = arr[1];
						CarInfo car = new CarInfo();
						car = carInfoService.getItovCarMesssageByCarId(car_id);
						TItov_car_brand_golo3 golo = new TItov_car_brand_golo3();
						golo = carInfoService.getCodeByCarID(car_id);// 得到元征的车品牌编码
						String ret_code = App_FusionServices.save_car(Integer.valueOf(platform), car.getCar_carcase_num(), car.getCar_plate_number(), device_sn, access_id,access_token, device_pwd, golo.getCarSeriesId() + "");
						if (ret_code.equalsIgnoreCase("-2")) {// 接头已经激活	
							throw new ShopBusiException(	ShopBusiErrorBundle.OBD_REG,	new Object[] { device_sn });
						} else if (ret_code.equalsIgnoreCase("0")) {
							
							
							// 修改设备激活状态 和激活时间	
						    TerminalInfo terminalInfo =new TerminalInfo();
						    terminalInfo.setUser_id(user_id);
						    terminalInfo.setTerminal_binding_date(new Date());
						    terminalInfo.setTerminal_status(1);
						    terminalInfoService.updateTerminalStatus(terminalInfo);
						    
							// 接口激活成功
							// 2.1根据device_sn去t_itov_terminal查询useid
							// 根据userid和carid查看是否已经在t_itof_termianl_user中，如果没有，则需要判断是否是carid绑定的第一个车，第一个设置成默认，
							// 否则，简单的insert就行
							UserRelationCarInfo userRelationCarInfo = new UserRelationCarInfo();
							userRelationCarInfo.setCar_id(car_id);
							List<UserRelationCarInfo> list = carInfoService.getuserRelationCarInfo(userRelationCarInfo);
							int count = 0;
							if (list.size() == 0 && list == null) {
								userRelationCarInfo.setUser_id(Integer.parseInt(user_id_str));
								userRelationCarInfo.setIs_default("1");// 默认
							} else {
								for (UserRelationCarInfo userrci : list) {
									if (user_id_str.equals(String.valueOf(userrci.getUser_id()))) {
										count++;
										break;
									}
								}
							}
							if (count == 0) {
								userRelationCarInfo.setUser_id(Integer.parseInt(user_id_str));
								userRelationCarInfo.setIs_default("0");// 默认
							}
							userService.insertRelationUserAndCar(userRelationCarInfo);
							UsUser usUser = new UsUser();
							usUser.setUser_id(Integer.parseInt(user_id_str));
							usUser.setAccount_id(account_id);
							userService.insertUserInfo(usUser);
							
							
						
							car.setCar_id(car_id);
							car.setAccess_id(access_id);
							car.setAccess_token(access_token);
							carInfoService.updatecarmessage(car);// 更新 car 表里的// access_id和access_token
							
							
							responseBodyJson.writeStringField("access_id",	access_id);
							responseBodyJson.writeStringField("access_token",	access_token);
							responseBodyJson.writeNumberField("car_id", car_id);
							responseBodyJson.writeNumberField("account_id",	account_id);
							responseBodyJson.writeArrayFieldStart("Data");
							List<CommonBean> list1 = userService.getRetMessageList(Long.valueOf(account_id));
							for (CommonBean commonBean : list1) {
								responseBodyJson.writeStartObject();
								responseBodyJson.writeStringField("deviceuid",commonBean.getTerminal_deviceuid());
								responseBodyJson.writeNumberField("cardid",commonBean.getCar_id());
								responseBodyJson.writeStringField("cardname",	commonBean.getCar_name());
								responseBodyJson.writeStringField("cardNum",commonBean.getCar_plate_number());
								responseBodyJson.writeStringField(	"cardpictureurl", server_url	+ commonBean.getPicture_url());
								responseBodyJson.writeStringField(	"cardpicturename",commonBean.getPicture_name());
								responseBodyJson.writeStringField("isChecked",commonBean.getIs_default());
								responseBodyJson.writeEndObject();
							}
							responseBodyJson.writeEndArray();
						} else {
							throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK,	new Object[] { null });
						}
					}
				} else {
					throw new ShopBusiException(ShopBusiErrorBundle.OBD_REG_ERROR,new Object[] { car_id });
				}
			} else {
				throw new ShopBusiException(ShopBusiErrorBundle.IS_EXIST_CAR,new Object[] { device_sn });
			}
		} else {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXITS,new Object[] { "车辆信息device_sn" });
		}
	}
}
