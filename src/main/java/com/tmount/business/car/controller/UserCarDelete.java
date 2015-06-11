package com.tmount.business.car.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.InsertInfoByImei.service.TerminalAccountService;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.db.car.dto.CarDelete;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.db.user.dto.UsUser;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class UserCarDelete extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private TerminalInfoService terminalInfoService;
	@Autowired
	private TerminalAccountService tAccountService;

	@RequestMapping(value = "accountcar.delete")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String car_id = new String(String.valueOf(ParamData.getInt(requestParam.getBodyNode(), "car_id")));
		String account_id = new String(String.valueOf(ParamData.getInt(requestParam.getBodyNode(), "account_id")));// 手机端ID
		String imei = ParamData.getString(requestParam.getBodyNode(), "terminal_imei");// 车imei号
		CarInfo carInfo = new CarInfo();
		carInfo = carInfoService.getItovCarMesssageByCarId(Integer.valueOf(car_id));
		if (carInfo == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXITS, new Object[] { "车辆信息" });
		} else {
			CarDelete carDelete = new CarDelete();
			List<TerminalInfo> terminalList = terminalInfoService.selectUserIdByImei(imei);
			if (null != terminalList && terminalList.size() > 0) {
				for (TerminalInfo ta : terminalList) {
					carDelete.setUser_id(ta.getUser_id());
				}
			}
			UsAccount usAccount = terminalInfoService.selectAccountIDByIMEI(imei);
			if(usAccount != null)
			{
				carDelete.setAccount_id(usAccount.getAccount_id().intValue());
			}
			carDelete.setAccoutn_id_tel(Long.valueOf(account_id));
			carDelete.setCarPlateNum(carInfo.getCar_plate_number());
			
			carDelete.setCar_id(Integer.valueOf(car_id));
			carDelete.setImei(imei);
			carInfoDelete(carDelete);
			responseBodyJson.writeStringField("result", "OK");
		}
	}
	public void carInfoDelete(CarDelete carDelete) throws ShopBusiException {
		//CarDelete cd = carInfoService.getIsExistCarInfoByCarIDAndAccount(carDelete);// 判断车辆是否
																					// 存在
		//if (cd == null) {
		//	throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXITS, new Object[] { "车辆信息" });
		//} else {
			TerminalInfo terminalInfo = new TerminalInfo();
			terminalInfo.setUser_id(carDelete.getUser_id());
			terminalInfo.setTerminal_binding_date(new Date());
			terminalInfo.setTerminal_status(0);
			terminalInfoService.updateTerminalStatus(terminalInfo);  //更改车辆的绑定状态
			try{
			carInfoService.deleteCarAccountByCarID(carDelete);// 删除车辆绑定关系信息
			}catch(Exception e){
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "删除车辆绑定关系信息失败" });
			}
			try{											// t_itov_terminal_car
			carInfoService.deleteCarInfoByCarID(carDelete);// 删除车辆基本信息 t_itov_car
			}catch(Exception e){
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "删除车辆基本信息失败" });
			}
			try{
			carInfoService.deleteUserByUserIDAccountID(carDelete);// t_itov_user
			}catch(Exception e){
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "删除用户基本信息失败" });
			}
			tAccountService.delete(carDelete.getAccoutn_id_tel());// t_zdc_terminal_account
			
			/////20150415 begin
			try{
			carInfoService.deleteAccountByAccountName(carDelete.getImei());
			}catch(Exception e){
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "删除账户信息失败" });
			}
			try{
			carInfoService.deleteBreakListBycarPlateNum(carDelete.getCarPlateNum());
		    }catch(Exception e){
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "删除违章信息失败" });
		    }
			try{
				if(null != carDelete.getAccount_id())
				{
			         carInfoService.deleteBySubAccountId(Long.valueOf(carDelete.getAccount_id()));
				}
			}catch(Exception e){
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "删除子账户信息失败" });
			}
			try{
			carInfoService.deleteOnlineByAccountId(carDelete.getImei());
			}catch(Exception e){
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "删除车辆在线信息失败" });
			}
			try{
				if(null != carDelete.getAccount_id()){
			         carInfoService.deletePersonByAccountId(Long.valueOf(carDelete.getAccount_id()));
				}
			}catch(Exception e){
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "删除个人信息失败" });
			}
		//}
	}

}
