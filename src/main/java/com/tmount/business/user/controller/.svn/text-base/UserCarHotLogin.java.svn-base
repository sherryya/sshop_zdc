package com.tmount.business.user.controller;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.sun.xml.internal.bind.v2.TODO;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.cloopen.restAPI.RestAPI_AgentOnwork;
import com.tmount.business.cloopen.restAPI.RestExamples;
import com.tmount.business.integral.service.AddIntegralService;
import com.tmount.business.message.service.MessageService;
import com.tmount.business.ptt.service.PttEmpAgentService;
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.integral.dto.TItovIntegralTotal;
import com.tmount.db.message.dto.MessageRelation;
import com.tmount.db.ptt.dto.TItovPttEmpAgent;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.system.MD5;
import com.tmount.tools.GuidCreator;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 用户登录  热车
 * 
 * @author
 * 
 */
@Controller
public class UserCarHotLogin extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(UserCarHotLogin.class.getName());

	@Autowired
	private UserService userService;
	@Autowired
	private PttEmpAgentService pttEmpAgentService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private PttSubaccountService   pttSubaccountService;
	@Autowired
	AddIntegralService addIntegralService;
	private CarInfoService carInfoService;
	@RequestMapping(value = "userCarHot.login")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		//String server_url = "http://" + request.getServerName() + ":"
		//		+ request.getServerPort() + request.getContextPath() + "/";
		String server_url = "http://" + request.getServerName() + ":"+ request.getServerPort() +  "/";
		logger.info("~~~~~~~~~~~~~~~~server_url~~~~~~~~~~~~~~~~~~~~"+server_url);
		int platform = requestParam.getRequestDataHeader().getClientPlatform();// ios---10,android----11平台标示
		// 0.根据平台标示，获取最新版本号和url路径
		//CommonBean commonBeanVersion = userService.getVersion(platform);

		String account_type = new String(ParamData.getString(requestParam.getBodyNode(), "account_type"));
		String userAccount = new String(ParamData.getString(requestParam.getBodyNode(), "account_name"));// 账户名称
		String password = new String(ParamData.getString(requestParam.getBodyNode(), "account_password"));// 账号密码
		if ("1".equals(account_type)) {
			
			CommonBean commonBean1 =new CommonBean();
			commonBean1.setPlatform(platform);
			commonBean1.setVer_type(Integer.valueOf(2));
			CommonBean commonBeanVersion = userService.getVersion_ter(commonBean1);
			responseBodyJson.writeStringField("version", commonBeanVersion.getVersion());
			responseBodyJson.writeStringField("version_url", server_url+ commonBeanVersion.getVersionurl());
			responseBodyJson.writeNumberField("ver_important", commonBeanVersion.getVer_important());
		
		}

		// 账号类型 1：个人 2： 企业 3: 企业员工,4:客服人员,5:系统用户，6：只点江山用户
		// 1.如果是企业的账号、先根据账号名称直接去找T_ITOV_ACCOUNT表中、如果不存在，则找t_itov_account_auxiliary
		if (StringUtils.isNotEmpty(account_type)) {
			if ("2".equals(account_type)) {
				UsAccount usAccount = userService
						.getUsUserInfoByUserAccount(userAccount);
				if (usAccount != null) {
					if (usAccount.getAccount_password().equals(	MD5.getMD5(password))) {
						// 1.1去t_itov_user表中找是否有数据，没有则返回提示，没有绑定车，否则，返回车列表
						Long account_id = usAccount.getAccount_id();
						// 1.2根据account_id去查询所有要返回的值
						responseBodyJson.writeNumberField("account_id",	account_id);
						responseBodyJson.writeArrayFieldStart("Data");
						List<CommonBean> list = userService.getRetMessageList(account_id);
						for (CommonBean commonBean : list) {
							responseBodyJson.writeStartObject();
							responseBodyJson.writeStringField("deviceuid",	commonBean.getTerminal_deviceuid());
							responseBodyJson.writeNumberField("cardid",	commonBean.getCar_id());
							responseBodyJson.writeStringField("cardname",	commonBean.getCar_name());
							responseBodyJson.writeStringField("cardNum",	commonBean.getCar_plate_number());
							responseBodyJson.writeStringField("cardpictureurl",	server_url + commonBean.getPicture_url()	);
							responseBodyJson.writeStringField("cardpicturename", commonBean.getPicture_name());
							responseBodyJson.writeStringField("isChecked",commonBean.getIs_default());
							responseBodyJson.writeEndObject();
						}
						responseBodyJson.writeEndArray();
					} else {
						throw new ShopBusiException(
								ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
					}
				} else {
					throw new ShopBusiException(
							ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
				}
			} else if ("1".equals(account_type)) {
				// 1.如果是个人的账号、先根据账号名称直接去找T_ITOV_ACCOUNT表中、如果不存在，则找t_itov_user表
				UsAccount usAccount = userService.getUsUserInfoByUserAccount(userAccount);
				if (usAccount != null) {
					if (usAccount.getAccount_password().equals(MD5.getMD5(password))) {
						// 1.1去t_itov_user表中找是否有数据，没有则返回提示，没有绑定车，否则，返回车列表
						Long account_id = usAccount.getAccount_id();
						UsAccount us=new UsAccount();
						if(usAccount.getIs_login()==0)//如果未登录，需要将用户设置为登录状态，
						{   
							us.setAccount_id(account_id);
							us.setIs_login(1);
							userService.updateLoginStatus(us);
						}
						// 1.2根据account_id去查询所有要返回的值
						responseBodyJson.writeNumberField("account_id",account_id);
						responseBodyJson.writeStringField("account_name",	usAccount.getAccount_name());
						responseBodyJson.writeStringField("nickname",	usAccount.getNickname());
						responseBodyJson.writeStringField("pic_name",	usAccount.getPic_name());
						responseBodyJson.writeNumberField("Integral",getIntegralTotal(account_id));
						responseBodyJson.writeNumberField("is_login",usAccount.getIs_login());
						/*返回VOIP账号信息       start*/
					    TItovPttSubaccount tItovPttSubaccount = pttSubaccountService.selectByAccount_id(account_id);
						if (tItovPttSubaccount != null) {
							responseBodyJson.writeStringField("subaccountsid",tItovPttSubaccount.getSubaccountsid());
							responseBodyJson.writeStringField("subtoken",	tItovPttSubaccount.getSubtoken());
							responseBodyJson.writeStringField("voipaccount",	tItovPttSubaccount.getVoipaccount());
							responseBodyJson.writeStringField("voippwd",	tItovPttSubaccount.getVoippwd());
						}
						/*返回IMEI 串码*/
					    TItovPttSubaccount tItovPttSubaccountIMEA = pttSubaccountService.selectIMEAByAccount_id(account_id);
						if (tItovPttSubaccountIMEA != null) {
							responseBodyJson.writeStringField("terminal_imei",	tItovPttSubaccountIMEA.getTerminal_imei());
						}
						/*返回VOIP账号信息       end*/
						responseBodyJson.writeArrayFieldStart("Data");
						List<CommonBean> list = userService.getRetMessageList(account_id);
						for (CommonBean commonBean : list) {
							responseBodyJson.writeStartObject();
							responseBodyJson.writeStringField("deviceuid",	commonBean.getTerminal_deviceuid());
							responseBodyJson.writeNumberField("cardid",	commonBean.getCar_id());
							responseBodyJson.writeStringField("cardname",	commonBean.getCar_name());
							responseBodyJson.writeStringField("cardNum",commonBean.getCar_plate_number());
							logger.info("url=" + server_url	+ commonBean.getPicture_url()	+ commonBean.getPicture_name());
							responseBodyJson.writeStringField("cardpictureurl",	server_url + commonBean.getPicture_url());
							responseBodyJson.writeStringField("cardpicturename", commonBean.getPicture_name());
							responseBodyJson.writeStringField("isChecked",commonBean.getIs_default());
							responseBodyJson.writeStringField("access_id",commonBean.getAccess_id());
							responseBodyJson.writeStringField("access_token",commonBean.getAccess_token());
							responseBodyJson.writeEndObject();
						}
						responseBodyJson.writeEndArray();
					} else {
						throw new ShopBusiException(
								ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
					}
				} else {
					 throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_USER,	 new Object[]{userAccount});
					// 调用uccente的接口
					
				/*	logger.info("~~~~~~~~~~~~~~uccente~~~~~~~~~~~~~start~");
					Client e = new Client();
					String result = e.uc_user_login(userAccount, password);
					LinkedList<String> rs = XMLHelper.uc_unserialize(result);
					if (rs.size() > 0) {
						int $uid = Integer.parseInt(rs.get(0));
						String $username = rs.get(1);
						String $password = rs.get(2);
						String $email = rs.get(3);
						logger.info("~~~~~~~~~~~~~~uid~~~~~~~~~~~~~~" + $uid);
						if ($uid > 0) {
							System.out.println("登录成功");
							System.out.println("$username" + $username);
							System.out.println("$password" + $password);
							System.out.println("$email" + $email);
							String $ucsynlogin = e.uc_user_synlogin($uid);
							// 同步本系统信息
							String account_role_id = "0";
							String company_id = "company_id";
							userAdd($username, $password, account_type,	account_role_id, company_id);

							System.out.println("登录成功" + $ucsynlogin);
							// 本地登陆代码 //TODO ... ....
						} else if ($uid == -1) {
							throw new ShopBusiException(
									ShopBusiErrorBundle.NOT_EXISTS_USER,
									new Object[] { userAccount });
						} else if ($uid == -2) {
							throw new ShopBusiException(
									ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
						} 
					} else {
								throw new ShopBusiException(ShopBusiErrorBundle.DISCUZ_UCENTER_ERROR,
										new Object[] { "未定义" });
					}
					logger.info("~~~~~~~~~~~~~~uccente~~~~~~~~~~~~~end~");*/
					
					
				}
			} else if ("4".equals(account_type)) {
				// 1.如果是个人的账号、先根据账号名称直接去找T_ITOV_ACCOUNT表中、如果不存在，则找t_itov_user表
				UsAccount usAccountMessage = new UsAccount();
				usAccountMessage.setAccount_name(userAccount);
				usAccountMessage.setAccount_type(Integer.parseInt(account_type));
				UsAccount usAccount = userService.getUsUserInfo(usAccountMessage);
				if (usAccount != null) {
					if (usAccount.getAccount_password().equals(	MD5.getMD5(password))) {
						// 1.1去t_itov_user表中找是否有数据，没有则返回提示，没有绑定车，否则，返回车列表
						Long account_id = usAccount.getAccount_id();
						// 1.2根据account_id去查询t_itov_ptt_emp_agent表所有要返回的值
						responseBodyJson.writeNumberField("account_id",	account_id);
						TItovPttEmpAgent tItovPttEmpAgent = pttEmpAgentService.selectByPrimaryKey(account_id);
						if (tItovPttEmpAgent != null) {
							responseBodyJson.writeStringField("voipid",tItovPttEmpAgent.getVoipaccount());
							responseBodyJson.writeNumberField("agent_id",	tItovPttEmpAgent.getAgentId());
							responseBodyJson.writeStringField("subAccountSid",	tItovPttEmpAgent.getSubaccountsid());
							responseBodyJson.writeStringField("subToken",	tItovPttEmpAgent.getSubtoken());
							responseBodyJson.writeStringField("agentType",	tItovPttEmpAgent.getAgentType());
							// 将坐席改为上班状态
							Integer voipState = updateVoipState(	tItovPttEmpAgent.getVoipaccount().toString(), tItovPttEmpAgent.getAgentId().toString(),tItovPttEmpAgent.getAgentType());
							System.out.println("voipState:" + voipState);
							if ((voipState == 108026) || (voipState == 0)) {
								// 108026 已上班
								// 0 上班成功
							} else {
								throw new ShopBusiException(ShopBusiErrorBundle.NOT_VOIP_LOGIN,	null);
							}
						} else {
							throw new ShopBusiException(	ShopBusiErrorBundle.NOT_EXISTS_AGENTUSER,	new Object[] { userAccount });
						}

					} else {
						throw new ShopBusiException(ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
					}
				} else {
					throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_USER,new Object[] { userAccount });
				}
			}
			else if ("5".equals(account_type)) {  //系统用户
				UsAccount usAccountMessage = new UsAccount();
				usAccountMessage.setAccount_name(userAccount);
				usAccountMessage.setAccount_type(Integer.parseInt(account_type));
				UsAccount usAccount = userService.getUsUserInfo(usAccountMessage);
				if (usAccount != null) {
					if (usAccount.getAccount_password().equals(	MD5.getMD5(password))) {
						responseBodyJson.writeNumberField("account_id",	usAccount.getAccount_id());//用户ID
						responseBodyJson.writeNumberField("account_role_id",	usAccount.getAccount_role_id());//用户角色
					} else {
						throw new ShopBusiException(ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
					}
				} else {
					throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_USER,new Object[] { userAccount });
				}
			}else if ("6".equals(account_type)) {  //只点江山用户
				UsAccount usAccountMessage = new UsAccount();
				usAccountMessage.setAccount_name(userAccount);
				usAccountMessage.setAccount_type(Integer.parseInt(account_type));
				UsAccount usAccount = userService.getUsUserInfo(usAccountMessage);
				if (usAccount != null) {
					if (usAccount.getAccount_password().equals(	MD5.getMD5(password))) {
						responseBodyJson.writeNumberField("account_id",	usAccount.getAccount_id());//用户ID
						responseBodyJson.writeNumberField("account_role_id",	usAccount.getAccount_role_id());//用户角色
						List<CommonBean> list = userService.getRetMessageList(usAccount.getAccount_id());
						for (CommonBean commonBean : list) {
							if(commonBean.getIs_default()!=null)//只返回默认车辆
							{				
								if(commonBean.getIs_default().equalsIgnoreCase("1"))
								{
								   responseBodyJson.writeStringField("deviceuid",	commonBean.getTerminal_deviceuid());
								}
							}
						}
					} else {
						throw new ShopBusiException(ShopBusiErrorBundle.ERR_USER_PASSWORD, null);
					}
				} else {
					throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_USER,new Object[] { userAccount });
				}
			}
		}
	}
	
	/**
	 * 得到用户的积分
	 * @param account_id
	 * @return
	 */
	private Integer getIntegralTotal(Long account_id)
	{
		TItovIntegralTotal tItovIntegralTotal = new TItovIntegralTotal();
		tItovIntegralTotal.setAccount_id(account_id);
		TItovIntegralTotal tItovIntegralTotal1 = new TItovIntegralTotal();
		try {
			tItovIntegralTotal1 = addIntegralService.selectTotalByAccount(tItovIntegralTotal);
			if(tItovIntegralTotal1==null)
			{
				return 0;
			}
			else
			{
				return  tItovIntegralTotal1.getIntegral();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	

	
	/**
	 * 改上班状态
	 * 
	 * @param voipid
	 * @param agent_id
	 * @return
	 */
	private Integer updateVoipState(String voipid, String agent_id,
			String agentType) {
		RestAPI_AgentOnwork restAPI_AgentOnwork = new RestAPI_AgentOnwork();
		String xml = null;
		try {
			xml = restAPI_AgentOnwork.getAgentOnWork(voipid, agent_id
					.toString(), "0", agentType);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ret = getXml(xml);
		return Integer.valueOf(ret);
	}

	/**
	 * 解析返回值
	 * 
	 * @param xml
	 * @return
	 */
	private String getXml(String xml) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml.toString());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		Integer statusCode;
		statusCode = Integer.valueOf(root.elementTextTrim("statusCode"));
		System.out.println("statusCode:" + statusCode);
		return statusCode.toString();
	}

	public String userAdd(String account_name, String account_password,
			String account_type, String account_role_id, String company_id)
			throws ShopBusiException {
		if (StringUtils.isEmpty(account_name)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "用户名" });
		}
		if (StringUtils.isEmpty(account_password)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "密码" });
		}
		if (StringUtils.isEmpty(account_type)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "用户类别" });
		}
		if (account_type.equals("2"))// 如果注册用户是企业用户，那么企业ID不能为空
		{
			if (StringUtils.isEmpty(company_id)) {
				throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
						new Object[] { "企业信息" });
			}
		}
		if (account_password.equals(account_name)) {// 用户名和密码 不能相同
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EQUALS,
					new Object[] { "用户名", "密码" });
		}
		UsAccount usUserInfoOld = userService
				.getUsUserInfoByUserAccount(account_name);// 判断用户名是否 存在
		if (usUserInfoOld != null) {
			throw new ShopBusiException(ShopBusiErrorBundle.EXISTS_USER,
					new Object[] { account_name });
		}
		GuidCreator myGUID = new GuidCreator();
		// String userId = myGUID.createNewGuid(GuidCreator.FormatString);
		UsAccount usUserAccount = new UsAccount();
		//int account_id = carInfoService.getCarId("account_id");
       	//////集群 20150408
		int value = carInfoService.queryId("account_id")+1;  //查询数据库序列值
		int account_id = value;
		TestUpd testupd = new TestUpd();
		testupd.setName("account_id");
		testupd.setValue(value);
		carInfoService.updtestupd(testupd);    //更新数据库的序列值
		//////end
		usUserAccount.setAccount_id((long) account_id);
		usUserAccount.setAccount_name(account_name);
		usUserAccount.setAccount_password(MD5.getMD5(account_password));
		usUserAccount.setAccount_type(Integer.valueOf(account_type));
		if (account_type.equals("2"))// 如果注册用户是企业用户，那么企业ID不能为空
		{
			usUserAccount.setCompany_id(Long.parseLong(company_id));
		}
		usUserAccount.setAccount_role_id(0);
		userService.insertUsUserAccount(usUserAccount);
		if (Integer.parseInt(account_type) == 4) {
			// 客服工号注册，要申请voip账号，然后，入t_itov_ptt_emp_agent表
			// 1.调用创建子账号接口
			String ret_xml = new RestExamples().CreateSubAccount(account_name);
			// 2.解析xml
			Document doc = null;
			String ret;
			try {
				doc = DocumentHelper.parseText(ret_xml.toString());
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element root = doc.getRootElement();
			String statusCode = root.elementTextTrim("statusCode");
			if ("000000".equals(statusCode)) {
				Element SubAccount = root.element("SubAccount");
				String dateCreated = SubAccount.elementText("dateCreated");
				String subAccountSid = SubAccount.elementText("subAccountSid");
				String subToken = SubAccount.elementText("subToken");
				String voipAccount = SubAccount.elementText("voipAccount");
				String voipPwd = SubAccount.elementText("voipPwd");
				int agentId = userService.getAgentId("agent_id");
				// 3.入t_itov_ptt_emp_agent表
				TItovPttEmpAgent tItovPttEmpAgent = new TItovPttEmpAgent();
				tItovPttEmpAgent.setAccountId((long) account_id);
				tItovPttEmpAgent.setAgentId(agentId);
				tItovPttEmpAgent.setSubaccountsid(subAccountSid);
				tItovPttEmpAgent.setSubtoken(subToken);
				tItovPttEmpAgent.setVoipaccount(voipAccount);
				tItovPttEmpAgent.setVoippwd(voipPwd);
				tItovPttEmpAgent.setDatecreated(dateCreated);
				pttEmpAgentService.insert(tItovPttEmpAgent);
			} else {
				throw new ShopBusiException(
						ShopBusiErrorBundle.VOIP_SUB_CEATE_ERROR, null);
			}

		}
		// 增加消息设置默认值
		MessageRelation messageRelation = new MessageRelation();
		messageRelation.setAccount_id((long) account_id);
		messageRelation.setIs_valid("1");
		messageService.insertUserAdd(messageRelation);
		return String.valueOf(account_name);
	}
}
