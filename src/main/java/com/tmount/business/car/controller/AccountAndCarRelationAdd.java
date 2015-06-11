package com.tmount.business.car.controller;

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
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.car.dto.UserRelationCarInfo;
import com.tmount.db.user.dto.UsUser;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.tmount.business.itov.platform.inter.launch.*;
/**
 * 新增账号与车辆的绑定关系
 * 
 * @author 
 * 
 */
@Controller
public class AccountAndCarRelationAdd extends ControllerBaseByLogin {
	@Autowired
	private UserService userService;
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private TerminalInfoService terminalInfoService;
	@RequestMapping(value = "accountandcarrelationOld.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson,HttpServletRequest request) throws ShopException,
			JsonGenerationException, IOException {	
		int account_id = ParamData.getInt(
				requestParam.getBodyNode(), "account_id");//账号id
		String car_name = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_name"));//车名字
		String car_brands = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_brands"));//车品牌 汉字
		String car_model = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_model"));//车型号 汉字
		String car_style = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_style"));//车款  汉字
		String car_number = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_number"));//车牌号
		String car_main_number = new String(ParamData.getString(
				requestParam.getBodyNode(), "car_main_number"));//车架编号
		String device_sn = new String(ParamData.getString(
				requestParam.getBodyNode(), "device_id"));//设备sn
		String device_pwd = new String(ParamData.getString(
				requestParam.getBodyNode(), "device_pwd"));//设备激活码
		String brand_id = new String(ParamData.getString(
				requestParam.getBodyNode(), "brand_id"));//品牌ID
		String model_id = new String(ParamData.getString(
				requestParam.getBodyNode(), "model_id"));//车型ID 
		String style_id = new String(ParamData.getString(
				requestParam.getBodyNode(), "style_id"));//车款ID
		String platform = new String(ParamData.getString(
				requestParam.getBodyNode(), "platform"));//平台ID  android 11  
		String access_id = new String(ParamData.getString(
				requestParam.getBodyNode(), "access_id"));//access_id
		String access_token = new String(ParamData.getString(
				requestParam.getBodyNode(), "access_token"));//access_token
		
		//String server_url = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		//1.根据device_sn去t_itov_terminal表中找到对应的usrid
		String user_id_str= terminalInfoService.getUserIdByDeviceSn(device_sn);
		if(StringUtils.isNotEmpty(user_id_str)){//                                               设备存在
			int user_id = Integer.parseInt(user_id_str);
			//2.根据user_id去t_itov_terminal_car，看看是否已经存在绑定关系，如果存在，提示。否则，继续
			String car_id_flag = userService.getRelationCarInfoCompare(user_id);//               提示设备是否存在
			if(StringUtils.isEmpty(car_id_flag)){//该设备ID已经被绑定
				//链接元征数据接口  激活接头
				String ret=App_FusionServices.save_car(Integer.valueOf(platform),car_main_number,car_number,device_sn,access_id,access_token,device_pwd,brand_id);
				if(ret.equalsIgnoreCase("-2"))//接头已经激活
				{
					throw new ShopBusiException(ShopBusiErrorBundle.OBD_REG,new Object[]{device_sn});
				}
				else if(ret.equalsIgnoreCase("0"))//接口激活成功
				{
					//3.添加车辆信息
					CarInfo carinfo = new CarInfo();
					carinfo.setCar_name(car_name);
					carinfo.setCar_brands(car_brands);
					carinfo.setCar_model(car_model);
					carinfo.setCar_style(car_style);
					carinfo.setCar_plate_number(car_number);
					carinfo.setCar_carcase_num(car_main_number);
					carinfo.setCar_type(Integer.valueOf(style_id));
					//int car_id =carInfoService.getCarId("car_id");
					int value = carInfoService.queryId("car_id")+1;  //查询数据库序列值
					int car_id = value;
					TestUpd testupd = new TestUpd();
					testupd.setName("car_id");
					testupd.setValue(value);
					carInfoService.updtestupd(testupd);    //更新数据库的序列值
					carinfo.setCar_id(car_id);
					carInfoService.insert(carinfo);
					//4.根据account_id来查询t_itov_user表，看看之前是否绑定过设备（车）
					String is_default = userService.getRelationUserInfoByAccountId(account_id);
					//5.插入t_itov_user表，其中包含accout和userid的关系。
					UsUser usUser = new UsUser();
					usUser.setUser_id(user_id);
					usUser.setAccount_id(account_id);
					userService.insertUserInfo(usUser);
					//6.添加t_itov_terminal_car表,将userid和carid进行绑定
					UserRelationCarInfo  userRelationCarInfo = new UserRelationCarInfo();
					userRelationCarInfo.setCar_id(car_id);
					userRelationCarInfo.setUser_id(user_id);
					if(StringUtils.isNotEmpty(is_default)){
						//说明已经绑定过车，故默认设置为0
						userRelationCarInfo.setIs_default("0");
					}else{
						userRelationCarInfo.setIs_default("1");
					}
					userService.insertRelationUserAndCar(userRelationCarInfo);
					//7.根据car_brands去找对应的图片及url
					/*CommonBean commonBean =  userService.getPictureMessage(car_brands);
					responseBodyJson.writeNumberField("carid",car_id);
					responseBodyJson.writeStringField("cardpictureurl", server_url+commonBean.getPicture_url()+commonBean.getPicture_name());
					responseBodyJson.writeStringField("cardpicturename", commonBean.getPicture_name());		*/
					responseBodyJson.writeNumberField("carid",car_id);
				}
				else
				{
					throw new ShopBusiException(ShopBusiErrorBundle.OBD_REG_ERROR,new Object[]{device_sn});
				}
			}else{
				throw new ShopBusiException(ShopBusiErrorBundle.IS_EXIST_CAR,new Object[]{device_sn});
			}	
		}else{
			throw new ShopBusiException(ShopBusiErrorBundle.IS_NOTEXIST_TERMINAL,new Object[]{device_sn});
		}
		
	}
}
