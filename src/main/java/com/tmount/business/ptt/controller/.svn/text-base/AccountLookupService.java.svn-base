package com.tmount.business.ptt.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.ptt.service.PttEmpAgentService;
import com.tmount.business.ptt.service.PttSubaccountLogService;
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.db.ptt.dto.CallAuthen;
import com.tmount.db.ptt.dto.CallEstablish;
import com.tmount.db.ptt.dto.CallHangup;
import com.tmount.db.ptt.dto.TItovPttEmpAgent;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.ptt.dto.TItovPttSubaccountLog;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByHttp;
import com.tmount.util.ResponseData;
/*
 * VOIP
 *   1. 手机端打电话前需要先判断被叫号码的状态  ：  0：可以呼叫  1：占线  2：  欠费   
 *   2.鉴权（CallAuth）如果是收费的话，需要在这里判断 是否欠费
 *   3.鉴权（CallEstablish） 需要修改主被叫号码的当前状态  ，由在线改为占线 (0->1)  
 *   4.鉴权（Hangup） 需要修改主被叫号码的当前状态，由占线改为在线（1->0）
 * 
 */
/**
 * 
 * 
 * @author
 * 
 */
@Controller
public class AccountLookupService extends ControllerBaseByHttp {
	Logger logger = Logger.getLogger(AccountLookupService.class.getName());
	String voipaccount ="";//主叫号
	String voipaccount1 ="";//被叫号
	String agentstate ="";//修改的状态
	String type="";////呼叫类型   0:普通呼叫 1:回拨 2 :VoIP网络通话
	@Autowired
	private PttEmpAgentService pttEmpAgentService;
	@Autowired
	private PttSubaccountService pttSubaccountService;
	@Autowired
	private PttSubaccountLogService pttSubaccountLogService;
	
	
	@RequestMapping(value = "accountLookup")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected String doService(HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~accountLookupService is bigining");
		Document doc = null;
		String xml ="";
		try {
			InputStream in = request.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			String str = null;
			StringBuffer xmlfile = new StringBuffer();
			while ((str = bf.readLine()) != null) {
				xmlfile.append(str);
			}
			logger.info(" --- xml body --- :" + xmlfile);
			doc = DocumentHelper.parseText(xmlfile.toString());
			Element root = doc.getRootElement();
			String action = root.elementTextTrim("action");
			if (action.equals("AccountLookup")) {
				//VOIP 账号反查
				xml=parseLookup(root);
			}
			else if (action.equals("CallAuth")) {
				// 解析呼叫鉴权
				xml = parseCallAuth(root);
			}
			 else if (action.equals("CallEstablish")) {
					// 解析摘机请求
				 xml = parseCallEstablish(root);
				} else if (action.equals("Hangup")) {
					// 解析挂断请求
					xml = parseHangup(root);
				}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("XML="+xml);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~accountLookupService is end");
		return xml;
	}
	@Override
	protected ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * VOIP 账号反查
	 * @param root
	 * @return
	 */
	private String parseLookup(Element root)
	{
		String xml="";
		String subToken = root.elementTextTrim("id");
		logger.info("~~~~~~~~~~~~~~~~~~~subToken~~~~~~~~~~~~~~~~~~~"+subToken);
		//1.根据账号名称去t_itov_account和t_itov_ptt_emp_agent找到voipaccount voipPwd
		TItovPttEmpAgent tItovPttEmpAgent=pttEmpAgentService.selectBySubToken(subToken);
		if(tItovPttEmpAgent!=null){
			 xml= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
					+ "<Response>" 
					+"<dname>"+subToken+"</dname>"
					+"<voipid>"+tItovPttEmpAgent.getVoipaccount()+"</voipid>"
					+"<voippwd>"+tItovPttEmpAgent.getVoippwd()+"</voippwd>"
					+"<hash>"+subToken+"</hash>"
					+"</Response>";
		}else{
			try {
				throw new ShopBusiException(ShopBusiErrorBundle.VOIP_ACCOUNT_NAME_ERROR,new Object[] { subToken });
			} catch (ShopBusiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return xml;
	}
	/**
	 * 解析呼叫鉴权
	 * @param e Element
	 * @return result
	 */
	private String parseCallAuth(Element e) {
		logger.info("--- parseCallAuth   start ---");
		//返回的数据
		String result = "<?xml version='1.0' encoding='UTF-8' ?><response><statuscode>0000</statuscode><statusmsg>Success</statusmsg><record>1</record><sessiontime>86400</sessiontime></response>";
		CallAuthen call = new CallAuthen();
		call.setType(e.elementTextTrim("type"));//呼叫类型   0:普通呼叫 1:回拨 2 :VoIP网络通话
		call.setOrderId(e.elementTextTrim("orderid"));//订单id
		call.setSubId(e.elementTextTrim("subid"));//子账号id	 Type 为1时提供
		call.setCaller(e.elementTextTrim("caller"));//主叫号码
		call.setCalled(e.elementTextTrim("called"));//被叫号码
		call.setCallSid(e.elementTextTrim("callSid"));//（仅回拨存在）与回拨功能调用返回callSid对应，唯一标示一路呼叫
		call.setSubtype(e.elementTextTrim("subtype"));//外呼显号标示      0:不显号 1:一方显号 2:双方均显号（回拨功能）
		logger.info(" --- parseCallAuth --- :"+call.toString());

		
		voipaccount =e.elementTextTrim("caller");
		voipaccount1 =e.elementTextTrim("called");
		agentstate = "1";//占线状态
		type=e.elementTextTrim("type");
		updState(e,voipaccount,voipaccount1,agentstate,type);
		
		//添加日志
		TItovPttSubaccountLog tItovPttSubaccountLog =new TItovPttSubaccountLog();
		tItovPttSubaccountLog.setCalled(call.getCalled());
		tItovPttSubaccountLog.setCaller(call.getCaller());
		tItovPttSubaccountLog.setCallsid(call.getCallSid());
		tItovPttSubaccountLog.setOrderid(call.getOrderId());
		tItovPttSubaccountLog.setSubid(call.getSubId());
		tItovPttSubaccountLog.setType( Integer.valueOf(call.getType()));
		
		System.out.println(tItovPttSubaccountLog);
		
		pttSubaccountLogService.insert(tItovPttSubaccountLog);
				
		logger.info("--- parseCallAuth   end ---");
		return result;
	}
	/**
	 * 解析摘机请求
	 * @param e Element
	 * @return result
	 */
	private String parseCallEstablish(Element e) {
		logger.info("--- parseCallEstablish   start   ");
		// 返回的数据
		String result = "<?xml version='1.0' encoding='UTF-8' ?><response><statuscode>0000</statuscode><statusmsg>Success</statusmsg><sessiontime>300000</sessiontime><billdata>ok</billdata></response>";
		CallEstablish call = new CallEstablish();
		call.setType(e.elementTextTrim("type"));//呼叫类型   0:普通呼叫 1:回拨 2 :VoIP网络通话
		call.setOrderId(e.elementTextTrim("orderid"));//订单id
		call.setSubId(e.elementTextTrim("subid"));//子账号id	 Type 为1时提供
		call.setCaller(e.elementTextTrim("caller"));//主叫号码
		call.setCalled(e.elementTextTrim("called"));//被叫号码
		call.setCallSid(e.elementTextTrim("callSid"));//（仅回拨存在）与回拨功能调用返回callSid对应，唯一标示一路呼叫
		
		
		
		
		//修改状态
		voipaccount =e.elementTextTrim("caller");
		voipaccount1 =e.elementTextTrim("called");
		agentstate = "2";//通话中状态
		type=e.elementTextTrim("type");
		updState(e,voipaccount,voipaccount1,agentstate,type);
      
		
		logger.info(" --- CallEstablish --- : " + call.toString());
		logger.info("--- parseCallEstablish   end ---");
		return result;
	}
	/**
	 * 解析挂断请求
	 * @param e Element
	 * @return result
	 */
	private String parseHangup(Element e) {
		logger.info("---parseHangup   start---");
		//返回的数据
		String result = "<?xml version='1.0' encoding='UTF-8'?><response><statuscode>0000</statuscode><statusmsg>Success</statusmsg><totalfee>0.120000</totalfee></response>";
		// 封装 CallHangup
		CallHangup call = new CallHangup();
		call.setType(e.elementTextTrim("type"));//呼叫类型   0:普通呼叫 1:回拨 2 :VoIP网络通话
		call.setOrderId(e.elementTextTrim("orderid"));//订单id
		call.setSubId(e.elementTextTrim("subid"));//子账号id	 Type 为1时提供
		call.setCaller(e.elementTextTrim("caller"));//主叫号码
		call.setCalled(e.elementTextTrim("called"));//被叫号码
		call.setByetype(e.elementTextTrim("byetype"));//通话挂机类型
		call.setStarttime(e.elementTextTrim("starttime"));//通话开始时间
		call.setEndtime(e.elementTextTrim("endtime"));//通话结束时间
		call.setBilldata(e.elementTextTrim("billdata"));//呼叫的计费私有数据
		call.setCallSid(e.elementTextTrim("callSid"));//（仅回拨存在）与回拨功能调用返回callSid对应，唯一标示一路呼叫
		call.setRecordurl(e.elementTextTrim("recordurl"));//通话录音完整下载地址
		call.setSubtype(e.elementTextTrim("subtype"));//外呼显号标示
		logger.info(" --- CallHangup --- : " + call.toString());
		/*Long start = Long.parseLong(call.getStarttime());
		Long end = Long.parseLong(call.getEndtime());*/
	
		
		voipaccount =e.elementTextTrim("caller");
		voipaccount1 =e.elementTextTrim("called");
		agentstate = "0";//改成在线
		type=e.elementTextTrim("type");
		updState(e,voipaccount,voipaccount1,agentstate,type);
		
		//修改日志参数
		TItovPttSubaccountLog tItovPttSubaccountLog =new TItovPttSubaccountLog();
		tItovPttSubaccountLog.setBilldata(call.getBilldata());
		tItovPttSubaccountLog.setByetype(call.getByetype());
		tItovPttSubaccountLog.setEndtime(call.getEndtime());
		tItovPttSubaccountLog.setStarttime(call.getStarttime());
		tItovPttSubaccountLog.setRecordurl(call.getRecordurl());
		tItovPttSubaccountLog.setSubtype(call.getSubtype());
		tItovPttSubaccountLog.setOrderid(call.getOrderId());
		
		System.out.println(tItovPttSubaccountLog);
		
		pttSubaccountLogService.updateByPrimaryKeySelective(tItovPttSubaccountLog);
		
		return result;
	}
	
	
	private void updState(Element e,String voipaccount,String voipaccount1 ,String agentstate,String type)
	{
		//以下代码实现将主被叫号码的当前状态改为占线状态
	    TItovPttSubaccount  tItovPttSubaccount =new TItovPttSubaccount();
	    tItovPttSubaccount.setVoipaccount(voipaccount);
	    tItovPttSubaccount.setAgentstate(agentstate);
	    System.out.println("voipaccount:"+voipaccount+",voipaccount1:"+voipaccount1+",agentstate="+agentstate+",type="+type);
		pttSubaccountService.updateByPrimaryKeySelective(tItovPttSubaccount);
		//if(type.equalsIgnoreCase("2"))
		//{
		    TItovPttSubaccount  tItovPttSubaccount1 =new TItovPttSubaccount();
		    tItovPttSubaccount1.setVoipaccount(voipaccount1);
		    tItovPttSubaccount1.setAgentstate(agentstate);
			pttSubaccountService.updateByPrimaryKeySelective(tItovPttSubaccount1);
		//}
	}

}
