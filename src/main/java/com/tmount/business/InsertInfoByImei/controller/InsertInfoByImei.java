package com.tmount.business.InsertInfoByImei.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.business.cloopen.restAPI.RestExamples;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
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
 * 插入terminal信息
 * 
 * @author
 * 
 */
@Controller
public class InsertInfoByImei extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TerminalInfoService terminalInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private PttSubaccountService pttSubAccountService;
	@Autowired
	private CarInfoService carInfoService;

	@RequestMapping(value = "insertInfomation.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String imei = ParamData.getString(requestParam.getBodyNode(), "terminal_imei");// 车机imei
		//根据imei查询terminal是否存在此imei信息
		List<TerminalInfo> terminalList = terminalInfoService.selectUserIdByImei(imei);
		if(null==terminalList || (null!=terminalList && terminalList.size()<=0))
		{
			//往terminal表插入数据
			TerminalInfo terminalInfo = new  TerminalInfo();
			//String imei = "123456789";
			terminalInfo.setTerminal_imei(imei);
			terminalInfo.setTerminal_name("OBD");
			//terminalInfo.setTerminal_status(1);
			try
			{
				//往terminal表中插入数据
				 terminalInfoService.insertTerminalByImei(terminalInfo);
			}catch(Exception e)
			{
				logger.info("insertInfomation.get 往terminal表中插入数据失败");
				//responseBodyJson.writeStringField("result", "0");
				throw new ShopBusiException(ShopBusiErrorBundle.TERMINAL_INSERT_FAILURE,null);
			}
			//int accountId  = carInfoService.getCarId("account_id");  //获得accountid
			//////集群 20150408
			int value = carInfoService.queryId("account_id")+1;  //查询数据库序列值
			int accountId = value;
			TestUpd testupd = new TestUpd();
			testupd.setName("account_id");
			testupd.setValue(value);
			carInfoService.updtestupd(testupd);    //更新数据库的序列值
			//////end
			Long accid = userService.getAccountIdByName(imei);
			if(accid !=null)
			{
				logger.info("insertInfomation.get 往account表中已经存在此terminalimei信息");
			}else
			{
				//创建account对象
				UsAccount usUserAccount = new UsAccount();
				
				usUserAccount.setAccount_id((long)accountId);
				usUserAccount.setAccount_name(imei);
				usUserAccount.setAccount_type(1);
				usUserAccount.setAccount_password(MD5.getMD5("111111"));
				try
				{
					//往account表中插入数据
					userService.insertUsUserAccount(usUserAccount);
				
				}catch(Exception e)
				{
					logger.info("insertInfomation.get 往account表中插入数据失败");
					//responseBodyJson.writeStringField("result", "0");
					throw new ShopBusiException(ShopBusiErrorBundle.ACCOUNT_INSERT_FAILURE,null);
				}
			}
			
			//根据terminal_imei查询user_id
			
				List<TerminalInfo> terList = terminalInfoService.selectUserIdByImei(imei);
				if(null != terList && terList.size()>0)
				{
					UsUser usUser = new UsUser();
					usUser.setUser_id(terList.get(0).getUser_id());
					usUser.setAccount_id(accountId);
					try
					{
					  userService.insertUserInfo(usUser);
					}catch(Exception e)
					{
						logger.info("insertInfomation.get 往user表中插入数据失败");
						//responseBodyJson.writeStringField("result", "0");
						throw new ShopBusiException(ShopBusiErrorBundle.USER_INSERT_FAILURE,null);
					}
					
				}
				
			//根据imei查询账号id
			//int account_id =  userService.getAccountIdByName(imei);
			
			UsPersonal usPersonal = new UsPersonal();
			usPersonal.setAccount_id((long)accountId);
			usPersonal.setPersonal_real_name(imei);
			try
			{
				userService.insertPersonalInfo(usPersonal);
			}catch(Exception e)
			{
				logger.info("insertInfomation.get 往person表中插入数据失败");
				//responseBodyJson.writeStringField("result", "0");
				throw new ShopBusiException(ShopBusiErrorBundle.PERSON_INSERT_FAILURE,null);
			}
			/*// 1.调用创建子账号接口
			String ret_xml = new RestExamples().CreateSubAccount(imei);
			// 2.解析xml
			Document doc = null;
			try {
					doc = DocumentHelper.parseText(ret_xml.toString());
				} catch (DocumentException e) {
						// TODO Auto-generated catch block
					//responseBodyJson.writeStringField("result", "0");
					logger.info("doc解析异常");
					throw new ShopBusiException(ShopBusiErrorBundle.DOCUMENTEXCEPTION,null);
				}
			Element root = doc.getRootElement();
			String statusCode = root.elementTextTrim("statusCode");
			if("000000".equals(statusCode)){
				Element SubAccount = root.element("SubAccount");
				String dateCreated = SubAccount.elementText("dateCreated");
				String subAccountSid = SubAccount.elementText("subAccountSid");
				String subToken = SubAccount.elementText("subToken");
				String voipAccount = SubAccount.elementText("voipAccount");
				String voipPwd = SubAccount.elementText("voipPwd");
					//手机端用户注册的时候需要分配VOIP号
					//写入t_itov_ptt_subaccount表
				TItovPttSubaccount tItovPttSubaccount = new TItovPttSubaccount();
				tItovPttSubaccount.setAccountId((long) (accountId));
				tItovPttSubaccount.setSubaccountsid(subAccountSid);
				tItovPttSubaccount.setSubtoken(subToken);
				tItovPttSubaccount.setVoipaccount(voipAccount);
				tItovPttSubaccount.setVoippwd(voipPwd);
				tItovPttSubaccount.setDatecreated(dateCreated);
				try
				{
					pttSubAccountService.insert(tItovPttSubaccount);
				}catch(Exception e)
				{
					logger.info("insertInfomation.get 往subAccount表中插入数据失败");
					//responseBodyJson.writeStringField("result", "0");
					throw new ShopBusiException(ShopBusiErrorBundle.SUBACCOUNT_INSERT_FAILURE,null);
				}
				
			}else{
				responseBodyJson.writeStringField("result", "0");
				throw new ShopBusiException(ShopBusiErrorBundle.VOIP_SUB_CEATE_ERROR,null);
			}*/
			//手机端用户注册的时候需要分配VOIP号
			//int voipa= carInfoService.getCarId("voipAccount");  //voip
	       	//////集群 20150408
			int value1 = carInfoService.queryId("voipAccount")+1;  //查询数据库序列值
			int voipa = value1;
			TestUpd testupd1 = new TestUpd();
			testupd1.setName("voipAccount");
			testupd1.setValue(value1);
			carInfoService.updtestupd(testupd1);    //更新数据库的序列值
			//////end
		    String voipAccount=String.valueOf(voipa);
		    Random r = new Random();
		    int voipPwdint =  r.nextInt(899999)+100000;   //create random 6 wei shu
		    String voipPwd=String.valueOf(voipPwdint);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String dateCreated=sdf.format(new Date());
			//写入t_itov_ptt_subaccount表
			TItovPttSubaccount tItovPttSubaccount = new TItovPttSubaccount();
			tItovPttSubaccount.setAccountId((long) accountId);
			tItovPttSubaccount.setSubaccountsid("");
			tItovPttSubaccount.setSubtoken("");
			tItovPttSubaccount.setVoipaccount(voipAccount);
			tItovPttSubaccount.setVoippwd(voipPwd);
			tItovPttSubaccount.setDatecreated(dateCreated);
			tItovPttSubaccount.setAccount_type(0);
			pttSubAccountService.insert(tItovPttSubaccount);
			//responseBodyJson.writeStringField("result", "1");
			responseBodyJson.writeNumberField("account_id", accountId);  //返回accountId
		}else
		{
			logger.info("terminal信息重复,已经存在此imei号信息");
			//如果imei号已经存在则查询accountId
			long accountId = userService.getAccountIdByName(imei);
			//responseBodyJson.writeStringField("result", "-1");
			responseBodyJson.writeNumberField("account_id", accountId);  //返回accountId
			//throw new ShopBusiException(ShopBusiErrorBundle.TERMINAL_DUPILACATE,null);
		}
		
	}

}
