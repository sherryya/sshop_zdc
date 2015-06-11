package com.tmount.business.user.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.message.service.MessageService;
import com.tmount.business.ptt.service.PttEmpAgentService;
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.message.dto.MessageRelation;
import com.tmount.db.ptt.dto.TItovPttEmpAgent;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.db.user.dto.UsPersonal;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.system.MD5;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 新用户注册。(个人和企业,客服账号)
 * 
 * @author zsh
 * 
 */
@Controller
public class UserAdd extends ControllerBase {
	Logger logger = Logger.getLogger(UserAdd.class.getName());
	//private Integer platform=11;// ios---10,android----11平台标示
	private int account_id;
	@Autowired
	private UserService userService;
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private PttEmpAgentService pttEmpAgentService;
	@Autowired
	private PttSubaccountService pttSubaccountService;
	@RequestMapping(value = "user.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
	    //platform = requestParam.getRequestDataHeader().getClientPlatform();
		String account_name = new String(ParamData.getString(	requestParam.getBodyNode(), "account_name"));
		String account_password = new String(ParamData.getString(requestParam.getBodyNode(), "account_password"));
		// account_type =1 :个人用户 2：企业用户 3：企业员工用户 4： 客服工号
		String account_type = new String(ParamData.getString(requestParam.getBodyNode(), "account_type", "0"));
		String account_role_id = new String(ParamData.getString(requestParam.getBodyNode(), "account_role_id", "0"));
		String company_id = new String(ParamData.getString(requestParam.getBodyNode(), "company_id"));
		String  nickname ="";
		String real_name ="";
		String person_sex="";
		String person_tel ="";
		String person_email="";
		if (account_type.equals("7")) {// 4S店
			nickname = new String(ParamData.getString(requestParam.getBodyNode(), "nickname"));
			real_name = new String(ParamData.getString(requestParam.getBodyNode(), "real_name"));
			// account_type =1 :个人用户 2：企业用户 3：企业员工用户 4： 客服工号
			person_sex = new String(ParamData.getString(requestParam.getBodyNode(), "person_sex"));
			person_tel = new String(ParamData.getString(requestParam.getBodyNode(), "person_tel"));
			person_email = new String(ParamData.getString(requestParam.getBodyNode(), "person_email"));
		}
		//如果类型是6则需要插入主播类型/频道类型/和房间号
		if("6".equals(account_type))
		{
			String hostType = ParamData.getString(
					requestParam.getBodyNode(), "hostType", "0");
			String channelType = ParamData.getString(
					requestParam.getBodyNode(), "channelType");
			///int roomId = ParamData.getInt(requestParam.getBodyNode(), "roomId");
			//int roomId = carInfoService.getCarId("room_id");
			int value = carInfoService.queryId("room_id")+1;  //查询数据库序列值
			int roomId = value;
			TestUpd testupd = new TestUpd();
			testupd.setName("room_id");
			testupd.setValue(value);
			carInfoService.updtestupd(testupd);    //更新数据库的序列值
			addHostAccount(account_name, account_password, account_type, account_role_id,	company_id,hostType,channelType,roomId);
		}
		else
		{
			userAdd(account_name, account_password, account_type, account_role_id,	company_id,nickname,real_name,person_sex,person_tel,person_email);
		}
		responseBodyJson.writeStringField("account_name", account_name);
		responseBodyJson.writeNumberField("account_id", account_id);
	}
	public String userAdd(String account_name, String account_password,
			String account_type, String account_role_id, String company_id,
			String nickname,String real_name,String person_sex,String person_tel,
			String person_email
			)
			throws ShopBusiException, JsonProcessingException, IOException {
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
		UsAccount usUserInfoOld = userService.getUsUserInfoByUserAccount(account_name);// 判断用户名是否 存在
		if (usUserInfoOld != null) {
			throw new ShopBusiException(ShopBusiErrorBundle.EXISTS_USER,	new Object[] { account_name });
		}
		UsAccount usUserAccount = new UsAccount();
		UsPersonal usPersonal=new UsPersonal();
		//account_id = carInfoService.getCarId("account_id");
	    //////集群 20150408
				int value = carInfoService.queryId("account_id")+1;  //查询数据库序列值
				account_id = value;
				TestUpd testupd = new TestUpd();
				testupd.setName("account_id");
				testupd.setValue(value);
				carInfoService.updtestupd(testupd);    //更新数据库的序列值
        //////end
		usUserAccount.setAccount_id((long) account_id);
		usUserAccount.setNickname(nickname);
		usUserAccount.setAccount_name(account_name);
		usUserAccount.setAccount_password(MD5.getMD5(account_password));
		usUserAccount.setAccount_type(Integer.valueOf(account_type));
		if (account_type.equals("2")||account_type.equals("7"))// 如果注册用户是企业用户，那么企业ID不能为空 //如果是4s用户 那么企业ID也不能为空
		{
			usUserAccount.setCompany_id(Long.parseLong(company_id));
			usPersonal.setPersonal_real_name(real_name);
			usPersonal.setPersonal_sex(person_sex);
			usPersonal.setPersonal_email(person_email);
			usPersonal.setPersonal_tel(person_tel);
		}
		usUserAccount.setAccount_role_id(0);
		usPersonal.setAccount_id((long)account_id);
		userService.insertUsUserAccount(usUserAccount);
		//注册t_itov_personal 表信息
		userService.insertPersonalInfo(usPersonal);
		if ((Integer.parseInt(account_type) == 4)||(Integer.parseInt(account_type) == 1)) {
			String subAccountSid="";
		    String subToken="";
		   // int voipa= carInfoService.getCarId("voipAccount");  //voip
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
			if((Integer.parseInt(account_type) == 4))
			{
				int agentId = userService.getAgentId("agent_id");
			    // 客服工号注册，要申请voip账号，然后，入t_itov_ptt_emp_agent表
				//3.入t_itov_ptt_emp_agent表
				TItovPttEmpAgent tItovPttEmpAgent = new TItovPttEmpAgent();
				tItovPttEmpAgent.setAccountId((long) account_id);
				tItovPttEmpAgent.setAgentId(agentId);
				tItovPttEmpAgent.setSubaccountsid(subAccountSid);
				tItovPttEmpAgent.setSubtoken(subToken);
				tItovPttEmpAgent.setVoipaccount(voipAccount);
				tItovPttEmpAgent.setVoippwd(voipPwd);
				tItovPttEmpAgent.setDatecreated(dateCreated);
				pttEmpAgentService.insert(tItovPttEmpAgent);
			}
			else if((Integer.parseInt(account_type) == 1))
			{
				//手机端用户注册的时候需要分配VOIP号
				//写入t_itov_ptt_subaccount表
				TItovPttSubaccount tItovPttSubaccount = new TItovPttSubaccount();
				tItovPttSubaccount.setAccountId((long) account_id);
				tItovPttSubaccount.setSubaccountsid(subAccountSid);
				tItovPttSubaccount.setSubtoken(subToken);
				tItovPttSubaccount.setVoipaccount(voipAccount);
				tItovPttSubaccount.setVoippwd(voipPwd);
				tItovPttSubaccount.setDatecreated(dateCreated);
				tItovPttSubaccount.setAccount_type(0);
				pttSubaccountService.insert(tItovPttSubaccount);
			}
		}
		// 增加消息设置默认值
		MessageRelation messageRelation = new MessageRelation();
		messageRelation.setAccount_id((long) account_id);
		messageRelation.setIs_valid("1");
		messageService.insertUserAdd(messageRelation);
		return String.valueOf(account_name);
	}
	public String addHostAccount(String account_name, String account_password,
			String account_type, String account_role_id, String company_id,String hostType,String channelType,int roomId)
			throws ShopBusiException, JsonProcessingException, IOException {
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
		UsAccount usUserInfoOld = userService.getUsUserInfoByUserAccount(account_name);// 判断用户名是否 存在
		if (usUserInfoOld != null) {
			throw new ShopBusiException(ShopBusiErrorBundle.EXISTS_USER,	new Object[] { account_name });
		}
		UsAccount usUserAccount = new UsAccount();
		UsPersonal usPersonal=new UsPersonal();
		//account_id = carInfoService.getCarId("account_id");
       	//////集群 20150408
		int value2 = carInfoService.queryId("account_id")+1;  //查询数据库序列值
		account_id = value2;
		TestUpd testupd2 = new TestUpd();
		testupd2.setName("account_id");
		testupd2.setValue(value2);
		carInfoService.updtestupd(testupd2);    //更新数据库的序列值
		//////end
		usUserAccount.setAccount_id((long) account_id);
		usUserAccount.setAccount_name(account_name);
		usUserAccount.setAccount_password(MD5.getMD5(account_password));
		usUserAccount.setAccount_type(Integer.valueOf(account_type));
		usUserAccount.setAccount_role_id(0);
		usPersonal.setAccount_id((long)account_id);
		usPersonal.setPersonal_real_name(account_name);
		usPersonal.setPersonal_tel(account_name);
		userService.insertUsUserAccount(usUserAccount);
		
		
			String subAccountSid="";
		    String subToken="";
		   // int voipa= carInfoService.getCarId("voipAccount");  //voip
		    
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
		
				//手机端用户注册的时候需要分配VOIP号
				//写入t_itov_ptt_subaccount表
				TItovPttSubaccount tItovPttSubaccount = new TItovPttSubaccount();
				tItovPttSubaccount.setAccountId((long) account_id);
				tItovPttSubaccount.setSubaccountsid(subAccountSid);
				tItovPttSubaccount.setSubtoken(subToken);
				tItovPttSubaccount.setVoipaccount(voipAccount);
				tItovPttSubaccount.setVoippwd(voipPwd);
				tItovPttSubaccount.setDatecreated(dateCreated);
				tItovPttSubaccount.setAccount_type(1);
				tItovPttSubaccount.setHostType(hostType);
				tItovPttSubaccount.setChannelType(channelType);
				tItovPttSubaccount.setRoomId(roomId);
				pttSubaccountService.insertAccountByHost(tItovPttSubaccount);
		// 增加消息设置默认值
		MessageRelation messageRelation = new MessageRelation();
		messageRelation.setAccount_id((long) account_id);
		messageRelation.setIs_valid("1");
		messageService.insertUserAdd(messageRelation);
		return String.valueOf(account_name);
	}
}
