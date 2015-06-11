package com.tmount.business.car.controller;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.business.itov.platform.inter.launch.App_FusionServices;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.car.dto.UserRelationCarInfo;
import com.tmount.db.user.dto.UsUser;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 新增账号与车辆的绑定关系
 * 
 * @author
 * 
 */
@Controller
public class AccountAndCarRelationAddNew extends ControllerBaseByLogin {
	@Autowired
	private UserService userService;
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private TerminalInfoService terminalInfoService;

	@RequestMapping(value = "accountandcarrelation.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		int account_id = ParamData.getInt(requestParam.getBodyNode(),"account_id");// 账号id
		String car_name = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_name"));// 车名字
		String car_brands = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_brands"));// 车品牌 汉字
		String car_model ="1";// new String(ParamData.getString(	requestParam.getBodyNode(), "car_model"));// 车型号 汉字
		String car_style ="1";// new String(ParamData.getString(		requestParam.getBodyNode(), "car_style"));// 车款 汉字
		String car_number = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_number"));// 车牌号
		String car_main_number = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_main_number"));// 车架编号
		String device_sn = new String(ParamData.getString(
				requestParam.getBodyNode(), "device_id"));// 设备sn
		String device_pwd = new String(ParamData.getString(
				requestParam.getBodyNode(), "device_pwd"));// 设备激活码
		String brand_id = new String(ParamData.getString(
				requestParam.getBodyNode(), "brand_id"));// 品牌ID  //元征品牌返回数据
		String platform = new String(ParamData.getString(
				requestParam.getBodyNode(), "platform"));// 平台ID android 11
		String access_id = "";// access_id
		String access_token = "";// access_token
		String server_url = "http://" + request.getServerName() + ":"+ request.getServerPort() +  "/";
		//接头不为空
		if (!device_sn.equalsIgnoreCase("")) {

			// 1.根据device_sn去t_itov_terminal表中找到对应的usrid
			String user_id_str = terminalInfoService.getUserIdByDeviceSn(device_sn);
			if (StringUtils.isNotEmpty(user_id_str)) {// 设备存在
				String device_pwd_t = terminalInfoService.getPwdByDeviceSn(device_sn);//判断密码是否正确
				if(device_pwd_t.equalsIgnoreCase(device_pwd))
				{
					int user_id = Integer.parseInt(user_id_str);
					
					// 2.根据user_id去t_itov_terminal_car，看看是否已经存在绑定关系，如果存在，提示。否则，继续
					String car_id_flag = userService.getRelationCarInfoCompare(user_id);// 提示设备是否存在
					if (StringUtils.isEmpty(car_id_flag)) {// 该设备ID已经被绑定
						// 3 调用元征注册接口
						String ret=App_FusionServices.getReg_User(Integer.valueOf(platform));
					    if(ret.length()>2)
					    {
					    	String [] arr=ret.split(",");
					    	if(arr.length>1)
					    	{
					    		access_id=arr[0];
					    		access_token=arr[1];
					    	}
					    }
					  // 4    元征数据接口 激活接头
						String ret1 =App_FusionServices.save_car(Integer.valueOf(platform), car_main_number,car_number, device_sn, access_id, access_token,device_pwd, brand_id);
						if (ret1.equalsIgnoreCase("-2"))// 接头已经激活
						{
							throw new ShopBusiException(	ShopBusiErrorBundle.OBD_REG,		new Object[] { device_sn });
						} 
						else if (ret1.equalsIgnoreCase("0"))// 接口激活成功
						{
					   // 5修改设备激活状态 和激活时间	
					    TerminalInfo terminalInfo =new TerminalInfo();
					    terminalInfo.setUser_id(user_id);
					    terminalInfo.setTerminal_binding_date(new Date());
					    terminalInfo.setTerminal_status(1);
					    terminalInfoService.updateTerminalStatus(terminalInfo);
					   //   6  添加车辆
						String carInfo=	AddCar( car_name, car_brands, car_model, car_style, car_number, car_main_number,	 brand_id, platform, user_id, account_id, device_sn,access_id,access_token);
						responseBodyJson.writeStringField("access_id",	access_id);
						responseBodyJson.writeStringField("access_token",	access_token);
						responseBodyJson.writeNumberField("carid", Integer.valueOf(carInfo));
						responseBodyJson.writeNumberField("account_id",account_id);
						responseBodyJson.writeArrayFieldStart("Data");
						List<CommonBean> list = userService.getRetMessageList(Long.valueOf(account_id));
						for (CommonBean commonBean : list) {
							responseBodyJson.writeStartObject();
							responseBodyJson.writeStringField("deviceuid",	commonBean.getTerminal_deviceuid());
							responseBodyJson.writeNumberField("cardid",	commonBean.getCar_id());
							responseBodyJson.writeStringField("cardname",	commonBean.getCar_name());
							responseBodyJson.writeStringField("cardNum",commonBean.getCar_plate_number());
							responseBodyJson.writeStringField("cardpictureurl",	server_url + commonBean.getPicture_url());
							responseBodyJson.writeStringField("cardpicturename", commonBean.getPicture_name());
							responseBodyJson.writeStringField("isChecked",commonBean.getIs_default());
							responseBodyJson.writeStringField("access_id",commonBean.getAccess_id());
							responseBodyJson.writeStringField("access_token",commonBean.getAccess_token());
							responseBodyJson.writeEndObject();
						}
						responseBodyJson.writeEndArray();
						} else {
							throw new ShopBusiException(	ShopBusiErrorBundle.OBD_REG_ERROR,	new Object[] { device_sn });
						}
					} else {
						throw new ShopBusiException(ShopBusiErrorBundle.IS_EXIST_CAR,new Object[] { device_sn });
					}
				}
				else
				{
					throw new ShopBusiException(ShopBusiErrorBundle.OBD_PWD,new Object[] { device_pwd });
				}
			} else {
				throw new ShopBusiException(	ShopBusiErrorBundle.IS_NOTEXIST_TERMINAL,	new Object[] { device_sn });
			}
		} else {
			//添加车辆
		
			String carInfo=	AddCar( car_name, car_brands, car_model, car_style, car_number, car_main_number,	 brand_id, platform, 0, account_id, device_sn,access_id,access_token);
			
			responseBodyJson.writeNumberField("carid", Integer.valueOf(carInfo));
			responseBodyJson.writeNumberField("account_id",account_id);
			responseBodyJson.writeArrayFieldStart("Data");
			List<CommonBean> list = userService.getRetMessageList(Long.valueOf(account_id));
			for (CommonBean commonBean : list) {
				responseBodyJson.writeStartObject();
				responseBodyJson.writeStringField("deviceuid",	commonBean.getTerminal_deviceuid());
				responseBodyJson.writeNumberField("cardid",	commonBean.getCar_id());
				responseBodyJson.writeStringField("cardname",	commonBean.getCar_name());
				responseBodyJson.writeStringField("cardNum",commonBean.getCar_plate_number());
				responseBodyJson.writeStringField("cardpictureurl",	server_url + commonBean.getPicture_url());
				responseBodyJson.writeStringField("cardpicturename", commonBean.getPicture_name());
				responseBodyJson.writeStringField("isChecked",commonBean.getIs_default());
				responseBodyJson.writeStringField("access_id",commonBean.getAccess_id());
				responseBodyJson.writeStringField("access_token",commonBean.getAccess_token());
				responseBodyJson.writeEndObject();
			}
			responseBodyJson.writeEndArray();
		}
	}
	@SuppressWarnings("unused")
	private String AddCar(String car_name,String car_brands,String car_model,String car_style,String car_number,String car_main_number,
			String brand_id,String platform,Integer user_id,Integer account_id,String device_sn,String access_id,String access_token) throws NumberFormatException, JsonProcessingException, IOException
	{
		// 1.添加车辆信息
		CarInfo carinfo = new CarInfo();
		carinfo.setCar_name(car_name);
		carinfo.setCar_brands(car_brands);
		carinfo.setCar_model(car_model);
		carinfo.setCar_style(car_style);
		carinfo.setCar_plate_number(car_number);
		carinfo.setCar_carcase_num(car_main_number);
		carinfo.setCar_type(Integer.valueOf(brand_id));
		//int car_id = carInfoService.getCarId("car_id");
		//////集群 20150408
		int value = carInfoService.queryId("car_id")+1;  //查询数据库序列值
		int car_id = value;
		TestUpd testupd = new TestUpd();
		testupd.setName("car_id");
		testupd.setValue(value);
		carInfoService.updtestupd(testupd);    //更新数据库的序列值
		//////end
		carinfo.setCar_id(car_id);
		carinfo.setAccount_id(account_id);
		if(!device_sn.equalsIgnoreCase(""))
		{
		    carinfo.setAccess_id(access_id);
			carinfo.setAccess_token(access_token);
		}
		carInfoService.insert(carinfo);
		if(!device_sn.equalsIgnoreCase(""))
		{
			// 2.根据account_id来查询t_itov_user表，看看之前是否绑定过设备（车）
			String is_default = userService.getRelationUserInfoByAccountId(account_id);
			// 3.插入t_itov_user表，其中包含accout和userid的关系。
			UsUser usUser = new UsUser();
			usUser.setUser_id(user_id);
			usUser.setAccount_id(account_id);
			userService.insertUserInfo(usUser);
			// 4.添加t_itov_terminal_car表,将userid和carid进行绑定
			UserRelationCarInfo userRelationCarInfo = new UserRelationCarInfo();
			userRelationCarInfo.setCar_id(car_id);
			userRelationCarInfo.setUser_id(user_id);
			if (StringUtils.isNotEmpty(is_default)) {
				// 说明已经绑定过车，故默认设置为0
				userRelationCarInfo.setIs_default("0");
			} else {
				userRelationCarInfo.setIs_default("1");
			}
			userService.insertRelationUserAndCar(userRelationCarInfo);
		}
		return String.valueOf(car_id);
	}
	

}
