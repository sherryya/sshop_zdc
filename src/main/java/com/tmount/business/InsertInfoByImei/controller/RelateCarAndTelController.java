package com.tmount.business.InsertInfoByImei.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.InsertInfoByImei.service.TerminalAccountService;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.TItovCar;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.car.dto.UserRelationCarInfo;
import com.tmount.db.terminalAccount.dto.TZdcTerminalAccount;
import com.tmount.db.user.dto.UsUser;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class RelateCarAndTelController extends ControllerBaseByLogin  {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TerminalInfoService terminalInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private TerminalAccountService tAccountService;
	@RequestMapping(value = "insertcarAccount.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson, HttpServletRequest request)	throws ShopException, JsonGenerationException, IOException  {
		String server_url = "http://" + request.getServerName() + ":"+ request.getServerPort()+"/zdc" ;
		String imei = ParamData.getString(requestParam.getBodyNode(), "terminal_imei");// 车imei号
		String car_carcase_num = ParamData.getString(requestParam.getBodyNode(), "car_carcase_num");// 车架号
		String car_plate_number = ParamData.getString(requestParam.getBodyNode(), "car_plate_number");// 车牌号
		String car_style = ParamData.getString(requestParam.getBodyNode(), "car_style");// 车型
		String car_name = ParamData.getString(requestParam.getBodyNode(), "car_name");// 车名
		String tel_account_id = ParamData.getString(requestParam.getBodyNode(), "tel_account_id");// 手机对应的account_id
		String car_engine_num="12345";// ParamData.getString(requestParam.getBodyNode(), "car_engine_num");//发动机号
		String city_code="123450"; //ParamData.getString(requestParam.getBodyNode(), "city_code");//车辆所在城市代码:用于查违章
		String province_code="123460";// ParamData.getString(requestParam.getBodyNode(), "province_code");//车辆所在省代码
		// 根据imei查询terminal是否存在此imei信息
		List<TerminalInfo> terminalList = terminalInfoService.selectUserIdByImei(imei);
		if (null != terminalList && terminalList.size() > 0) {
			long account_id = userService.getAccountIdByName(imei);
			logger.info("terminalAccount插入begin ");
			List<TZdcTerminalAccount> accountList = tAccountService.selectByterImei(account_id);
			// 如果已经已经存在了记录则不插入
			if (accountList == null || accountList.size() <= 0) {
				
				
				for(TerminalInfo ta:terminalList)
				{
					UsUser usUser = new UsUser();
					usUser.setUser_id(ta.getUser_id());
					usUser.setAccount_id(Integer.valueOf(tel_account_id));
					userService.insertUserInfo(usUser);
				}
				
				// insert terminal_account table begin
				TZdcTerminalAccount terminalAccount = new TZdcTerminalAccount();
				terminalAccount.setAccountIdTel(Long.valueOf(tel_account_id));
				terminalAccount.setAccountIdTer(account_id);
				tAccountService.insert(terminalAccount);
				
			
				
				int user_id = 0;
				user_id = terminalList.get(0).getUser_id(); // 得到userid
				//int car_id = carInfoService.getCarId("car_id");
				int value = carInfoService.queryId("car_id")+1;  //查询数据库序列值
				int car_id = value;
				TestUpd testupd = new TestUpd();
				testupd.setName("car_id");
				testupd.setValue(value);
				carInfoService.updtestupd(testupd);    //更新数据库的序列值
				// 根据account_id判断是否存在车辆信息如果存在则不插入
				List<TItovCar> carlist = carInfoService.isExsitList(Long.valueOf(tel_account_id));
				if (carlist == null || carlist.size() <= 0) {

					CarInfo carinfo = new CarInfo();
					carinfo.setCar_id(car_id);
					carinfo.setCar_carcase_num(car_carcase_num);
					carinfo.setCar_name(car_name);
					carinfo.setCar_plate_number(car_plate_number);
					carinfo.setCar_type(Integer.valueOf(car_style));
					carinfo.setAccount_id(Long.valueOf(tel_account_id));
					carinfo.setCar_engine_num(car_engine_num);
					carinfo.setCity_code(city_code);
					carinfo.setProvince_code(province_code);
					try {
						// 插入car信息
						carInfoService.insert(carinfo);
					} catch (Exception e) {
						logger.info("insertcarAccount.get 往car表中插入数据失败");
						Object[] params = new Object[1];
						params[0] = "账户车辆信息";
						throw new ShopBusiException(ShopBusiErrorBundle.INSERT_FAILURE, params);
					}
				} else {
					Object[] params = new Object[2];
					params[0] = "此";
					params[1] = "车辆信息";
					throw new ShopBusiException(ShopBusiErrorBundle.ISEXIST, params);
				}
				try {
					// query terminal_car whether or not exsit by user_id
					String carid = userService.getRelationCarInfoCompare(user_id);
					// 如果不存在则往表里面插入数据
					if (null == carid || "".equals(carid)) {
						UserRelationCarInfo userRelationCarInfo = new UserRelationCarInfo();
						userRelationCarInfo.setCar_id(car_id);
						userRelationCarInfo.setIs_default("1");
						userRelationCarInfo.setUser_id(user_id);
						// 往terminal_car表中插入数据
						userService.insertRelationUserAndCar(userRelationCarInfo);
					}
				} catch (Exception e) {
					logger.info("insertInfomation.get 往terminal_car表中插入数据失败");
					Object[] params = new Object[1];
					params[0] = "用户车辆信息";
					throw new ShopBusiException(ShopBusiErrorBundle.INSERT_FAILURE, params);
				}
				responseBodyJson.writeStringField("result", "1");
				responseBodyJson.writeNumberField("carid", car_id);
				responseBodyJson.writeArrayFieldStart("Data");
				List<CommonBean> list = userService.getRetMessageList(Long.valueOf(tel_account_id));
				for (CommonBean commonBean : list) {
					responseBodyJson.writeStartObject();
					responseBodyJson.writeStringField("deviceuid",	commonBean.getTerminal_deviceuid());
					responseBodyJson.writeNumberField("cardid",	commonBean.getCar_id());
					responseBodyJson.writeStringField("cardname",	commonBean.getCar_name());
					responseBodyJson.writeStringField("cardNum",commonBean.getCar_plate_number());
					responseBodyJson.writeStringField("cardpictureurl",	server_url + commonBean.getPicture_url());
					responseBodyJson.writeStringField("cardpicturename", commonBean.getPicture_name());
					responseBodyJson.writeStringField("isChecked",commonBean.getIs_default());
					responseBodyJson.writeEndObject();
				}
				responseBodyJson.writeEndArray();
			} else {
				Object[] params = new Object[1];
				params[0] = "此设备imei已经被绑定";
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON, params);
			}
		} else {
			Object[] params = new Object[1];
			params[0] = "设备imei号";
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXITS, params);
		}
	}
}
