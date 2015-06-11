package com.tmount.business.driver.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.driver.service.DriverService;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.driver.dto.User;
import com.tmount.db.user.dto.UsDriversSign;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 驾驶 员登陆
 * 
 * @author dell
 * 
 */
@Controller
public class DriverLogin extends ControllerBase {
	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "dirveruser.login")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String username = new String(ParamData.getString(	requestParam.getBodyNode(), "username"));// 账户名称
		String password = new String(ParamData.getString(	requestParam.getBodyNode(), "password"));// 账号密码
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		List<User> usAccount = driverService.getUser(user);
		if (usAccount.size() != 0) {
			if (usAccount.get(0).getPassword().equals(password)) {
				responseBodyJson.writeStringField("result", "OK");
				responseBodyJson.writeNumberField("id", usAccount.get(0).getId());
				responseBodyJson.writeStringField("username", usAccount.get(0).getUsername());
				user.setDriver_id(usAccount.get(0).getDriver_id());
				List<UsDriversSign> ds = driverService.getDriverSignInfo(user);
				responseBodyJson.writeStringField("driver_id", usAccount.get(0).getDriver_id().toString());
				SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
				Date d=new Date();
				if (ds.size() != 0) {
					Iterator<UsDriversSign> it = ds.iterator();
					Integer i = 0;
					while (it.hasNext()) {
						UsDriversSign usDriversSign;
						usDriversSign = it.next();
						String a1 = dateformat1.format(new Date());// 得到当前时间
					    d = usDriversSign.getDs_time();// 得到签到时间
						String a2 = dateformat1.format(d);
						if (a1.equals(a2))// 两个进行比较
						{
							i = 1;
							Integer ds_type=usDriversSign.getDs_type();
							//得到最后操作的记录，判断状态
							if(ds_type==1)//签到状态
							{
							 responseBodyJson.writeStringField("is_sign","OK");
							 responseBodyJson.writeStringField("sign_time", usDriversSign.getDs_time().toLocaleString());
							 CarInfo car=  driverService.getCarInfoByCarID( String.valueOf(usDriversSign.getDs_car_id()));
							 responseBodyJson.writeNumberField("car_id", usDriversSign.getDs_car_id());
							 responseBodyJson.writeStringField("car_num", car.getCarnum());
							 responseBodyJson.writeStringField("car_name", car.getCarname());
							}
							else
							{
								responseBodyJson.writeStringField("is_sign", "NO");
								responseBodyJson.writeStringField("sign_time", "");
							}
							break;
						}
					}
					if (i == 0) {
						responseBodyJson.writeStringField("is_sign", "NO");
						responseBodyJson.writeStringField("sign_time", "");
					}
				} else {
					responseBodyJson.writeStringField("is_sign", "NO");
					responseBodyJson.writeStringField("sign_time", "");
				}
			} else {
				throw new ShopBusiException(
						ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
			}
		} else {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_USER,
					new Object[] { username });
		}
	}
}
