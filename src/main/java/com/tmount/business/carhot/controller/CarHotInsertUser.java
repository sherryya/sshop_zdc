package com.tmount.business.carhot.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.db.user.dto.UsPersonal;
import com.tmount.db.user.dto.UsUser;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.system.MD5;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *车品牌数据同步接口
 * 
 * @author 
 * 
 */
@Controller
public class CarHotInsertUser extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private TerminalInfoService terminalService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "carHotInsertUser.insert")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String telphone = ParamData.getString(requestParam.getBodyNode(),"telphone");//手机号
		String imei = ParamData.getString(requestParam.getBodyNode(),"imei");//设备号
		List<TerminalInfo> list = terminalService.selectUserIdByImei(imei);
		if(null !=list && list.size()>0)
		{
			TerminalInfo ter = list.get(0);
			if(0==ter.getTerminal_status())
			{
				UsAccount usAccout = terminalService.selectAccountIDByIMEI(telphone);   //根据手机号查询是否存在此用户
				if(usAccout !=null)
				{
					//responseBodyJson.writeStringField("result", );
					throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
							new Object[] { "用户已存在" });
				}
				UsUser usUser = new UsUser();
				//////集群 20150421
				int account_id = carInfoService.queryId("account_id")+1;  //查询数据库序列值
				TestUpd testupd2 = new TestUpd();
				testupd2.setName("account_id");
				testupd2.setValue(account_id);
				carInfoService.updtestupd(testupd2);    //更新数据库的序列值			
				/////end
				UsAccount usUserAccount = new UsAccount();
				usUserAccount.setAccount_id((long)account_id);
				usUserAccount.setAccount_name(telphone);
				usUserAccount.setAccount_password(MD5.getMD5("111111"));
				usUserAccount.setAccount_type(8);  //表示车热了吗用户
				userService.insertUsUserAccount(usUserAccount); //插入手机用户信息
				usUser.setAccount_id(account_id);
				usUser.setUser_id(ter.getUser_id());
				userService.insertUserInfo(usUser);     //插入user信息
				/*person信息插入 20150512*/
				UsPersonal usPersonal = new UsPersonal();
				usPersonal.setAccount_id((long)account_id);
				usPersonal.setPersonal_real_name(telphone);
				userService.insertPersonalInfo(usPersonal);
				/*person信息插入 20150512*/
				TerminalInfo terminalInfo = new TerminalInfo();
				terminalInfo.setTerminal_status(1);
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(new Date());
				try {
					terminalInfo.setTerminal_binding_date(sdf.parse(date));
					terminalInfo.setTerminal_imei(imei);
					terminalService.updateTerminalStatus(terminalInfo);   //更新车辆状态
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				responseBodyJson.writeNumberField("result",1);  //操作成功
			}else
			{
				throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
						new Object[] { "此设备号已经被绑定" });
			}
		}else
		{
			responseBodyJson.writeNumberField("result",1);  //操作成功
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
					new Object[] { "此设备号不存在" });
		}
		
	}
}
