package com.tmount.business.ptt.controller;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
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
import com.tmount.business.ptt.service.StringUtil;
import com.tmount.business.reserve.service.ReserveService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.reserve.dto.TItovReserve;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class SendSMSService extends ControllerBase {
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	static Logger logger = Logger.getLogger(startService.class.getName());
	@Autowired
	private ReserveService reserveService;
	@Autowired
	private UserService userService;
	@Autowired
	private CarInfoService carInfoService;
	@RequestMapping(value = "sendSMSService.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@SuppressWarnings("unused")
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException,
	JsonGenerationException, IOException {		
		String dest=new String(ParamData.getString(requestParam.getBodyNode(), "phone"));//手机号
		String sms_type=new String(ParamData.getString(requestParam.getBodyNode(), "sms_type"));//短信类型  0:用户注册  1:设备预约  2：下发预约单号
		String brand="";//品牌
		String model="";//型号
		String style="";//款式
		String realname="";//姓名
		String address="";//地址
		String note="";//备注
		if(sms_type.equalsIgnoreCase("22"))
		{
		 brand=new String(ParamData.getString(requestParam.getBodyNode(), "brand"));//品牌
		 model=new String(ParamData.getString(requestParam.getBodyNode(), "model"));//型号
		 style=new String(ParamData.getString(requestParam.getBodyNode(), "style"));//款式
		 realname=new String(ParamData.getString(requestParam.getBodyNode(), "realname"));//姓名
		 address=new String(ParamData.getString(requestParam.getBodyNode(), "address"));//地址
		 note=new String(ParamData.getString(requestParam.getBodyNode(), "note"));//备注
		}
		String result ="";
			if (sms_type.equalsIgnoreCase("1")) {
				int count = reserveService.getCountByPhoneNo(dest);//判断是否已经预约过了
				if (count > 0) {
					throw new ShopBusiException(ShopBusiErrorBundle.RESERVER_ORDER_ISEXIT, null);
				}
			}
			if(sms_type.equalsIgnoreCase("0"))//使用互亿短信    用于用户注册
			{
				UsAccount usUserInfoOld = userService.getUsUserInfoByUserAccount(dest);// 判断用户名是否 存在
				if (usUserInfoOld != null) {
					throw new ShopBusiException(ShopBusiErrorBundle.EXISTS_USER, new Object[] { dest });
				}
				String ret=sendSMS_Reg(dest);
				if(ret.split(",")[0].equalsIgnoreCase("2"))
				{
					result="return_code："+ret.split(",")[1];
					responseBodyJson.writeStringField("result:", ret.split(",")[1]);
				}
				else
				{
					result="error_code："+ret;
					responseBodyJson.writeStringField("error_code:", ret.split(",")[0]);
				}
			}
			else if(sms_type.equalsIgnoreCase("3"))//使用互亿短信    用于用户找回密码
			{
				UsAccount usUserInfoOld = userService.getUsUserInfoByUserAccount(dest);// 判断用户名是否 存在
				if (usUserInfoOld != null) {
					String ret=sendSMS_Reg(dest);
					if(ret.split(",")[0].equalsIgnoreCase("2"))
					{
						result="return_code："+ret.split(",")[1];
						responseBodyJson.writeStringField("result:", ret.split(",")[1]);
					}
					else
					{
						result="error_code："+ret;
						responseBodyJson.writeStringField("result:", ret);
					}
				}
				else
				{
					throw new ShopBusiException(ShopBusiErrorBundle.COMMON, new Object[] { "帐号【"+dest+"】不存在" });
				}
			}
			else if(sms_type.equalsIgnoreCase("2"))  //用于4S店下发短信给指定的用
			{
				sendSMS_4SSHOP(dest);
				responseBodyJson.writeStringField("result:", "1");
			}else if(sms_type.equalsIgnoreCase("22")){ //预约下单通知
				// 0.判断该手机号是否已经预约
				int count = reserveService.getCountByPhoneNo(dest);
				if(count>0){
					throw new ShopBusiException(
							ShopBusiErrorBundle.RESERVER_ORDER_ISEXIT, null);
				}
				// 1.得到对应的预约码
				//int reserver_int_code = carInfoService.getCarId("reserve_code");
				int value = carInfoService.queryId("reserve_code")+1;  //查询数据库序列值
				int reserver_int_code = value;
				TestUpd testupd = new TestUpd();
				testupd.setName("reserve_code");
				testupd.setValue(value);
				carInfoService.updtestupd(testupd);    //更新数据库的序列值
				com.tmount.util.StringUtil stringUtil = new com.tmount.util.StringUtil();
				String reserve_code = com.tmount.util.StringUtil.lpadFormat(reserver_int_code, 8, "0");
				// 2.插入预约表
				TItovReserve tItovReserve = new TItovReserve();
				tItovReserve.setPhoneNo(dest);
				tItovReserve.setReserveCode(reserve_code);
				tItovReserve.setBrand(brand);
				tItovReserve.setModel(model);
				tItovReserve.setStyle(style);
				tItovReserve.setAddress(address);
				tItovReserve.setNote(note);
				tItovReserve.setRealname(realname);
				reserveService.insert(tItovReserve);
				
				//短信通知
				String ret=sendSMS_Reg2(dest,reserve_code);
				if(ret.split(",")[0].equalsIgnoreCase("2"))
				{
					responseBodyJson.writeStringField("result:","00000000");
				}
				else
				{
					result="error_code："+ret;
					responseBodyJson.writeStringField("result:", "0");
				}
			}
			//打印发送短信响应体
		   System.out.println("Response content is: " + result);
		   logger.info("retStr:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+result);
           logger.info("retStr:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+result);
	}	
	private String sendSMS_Reg(String mobile)
	{
		String code="";
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		int mobile_code = (int)((Math.random()*9+1)*100000);
		//您预约车载设备的短信验证码为：【变量】，请于【变量】分钟内正确输入。
		String minute="2";
		 String content = new String("您预约车载设备的短信验证码为："+mobile_code+"，请于"+minute+"分钟内正确输入。"); 
			NameValuePair[] data = {//提交短信
				    new NameValuePair("account", "cf_xiaocai"), 
				    // new NameValuePair("password", "xiaocai"), //密码可以使用明文密码或使用32位MD5加密
				    new NameValuePair("password", StringUtil.MD5Encode("xiaocai")),
				    new NameValuePair("mobile", mobile), //18686704606
				    new NameValuePair("content",  content),
			};		
		method.setRequestBody(data);				
		try {
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();		
			//System.out.println(SubmitResult);
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
		    code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
			if(code.equalsIgnoreCase("2")){
				System.out.println("短信提交成功");
				return code+","+mobile_code;
			}
			else
			{
				return code+","+msg;
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return code;
	}
	
	/**
	 * 发送短消息(预约下单通知)
	 * @param mobile
	 * @return
	 */
	private String sendSMS_Reg2(String mobile,String reserve_code)
	{
		String code="";
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		//String s="您在【变量】预约的车载设备单号为：【变量】。";
		String name="车行网";
		 String content = new String("您在"+name+"预约的车载设备单号为："+reserve_code+"。"); 
			NameValuePair[] data = {//提交短信
				    new NameValuePair("account", "cf_xiaocai"), 
				    // new NameValuePair("password", "xiaocai"), //密码可以使用明文密码或使用32位MD5加密
				    new NameValuePair("password", StringUtil.MD5Encode("xiaocai")),
				    new NameValuePair("mobile", mobile), //18686704606
				    new NameValuePair("content",  content),
			};		
		method.setRequestBody(data);				
		try {
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();		
			//System.out.println(SubmitResult);
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
		    code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
			if(code.equalsIgnoreCase("2")){
				System.out.println("短信提交成功");
				return code+","+reserve_code;
			}
			else
			{
				return code+","+msg;
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return code;
	}
	
	
	
	
	
	
	private String sendSMS_4SSHOP(String mobile)
	{
		String code="";
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		 String content = new String("请登录【http://www.chexing.com/xiazai.html】下载只点手机客户端，用户名:【"+mobile+"】 密码：【111111】。"); 
	    NameValuePair[] data = {//提交短信
				    new NameValuePair("account", "cf_xiaocai"), 
				    // new NameValuePair("password", "xiaocai"), //密码可以使用明文密码或使用32位MD5加密
				    new NameValuePair("password", StringUtil.MD5Encode("xiaocai")),
				    new NameValuePair("mobile", mobile), //18686704606
				    new NameValuePair("content",  content),
			};		
		method.setRequestBody(data);				
		try {
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();		
			//System.out.println(SubmitResult);
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
		    code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
			if(code == "2"){
				System.out.println("短信提交成功");
			}
			return code;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return code;
	}
	
	public static void main(String[] args) {
		
		
		new SendSMSService().sendSMS_4SSHOP("15945150563");
	}
}
