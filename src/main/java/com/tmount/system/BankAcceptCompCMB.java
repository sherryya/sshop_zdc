package com.tmount.system;


import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cmb.netpayment.Settle;

import com.tmount.business.order.service.OrderService;
import com.tmount.business.pub.service.PubService;
import com.tmount.db.order.dto.BkBankChangeAccept;
import com.tmount.db.order.dto.ReBankAccept;
import com.tmount.db.order.dto.ReBankAcceptHis;
import com.tmount.db.order.dto.ReBankAcceptKey;
import com.tmount.db.order.dto.ReBankOrderAccept;
import com.tmount.db.order.dto.ReBankOrderAcceptHis;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopParamException;

public class BankAcceptCompCMB {
	static private int iRet;
	static Settle settle;
	
	private ApplicationContext context = null;
	private SqlSessionTemplate sqlSessionTemplate = null;
	private FreeMarkerConfigurer freemarkerConfig = null;
	private OrderService orderService;
	private PubService pubService;
	
	private BankAcceptCompCMB(){
		context = new ClassPathXmlApplicationContext("springAppContext.xml");  
		/*获取mybatis工厂类*/
		sqlSessionTemplate = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
		/*获取freemaker工厂类*/
		freemarkerConfig = (FreeMarkerConfigurer) context.getBean("freemarkerConfig");
		
		orderService = (OrderService) context.getBean("orderService");
		
		pubService = (PubService)context.getBean("pubService");
	}
	
	public static void main(String[] args) {
			
		System.out.println("......enter......");
		BankAcceptCompCMB bankAcceptCompCMB = new BankAcceptCompCMB();
		
		String logPath = args[0];
		
		while(true){
			
			//输出重定向！
			Date    date = new Date();
			SimpleDateFormat df  = new SimpleDateFormat("yyyyMMdd");
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date); 
			String  totalDate=df.format(gc.getTime());
			
			PrintStream console = System.out;
			PrintStream out = null;
			try {
				out = new PrintStream(new BufferedOutputStream(new FileOutputStream(logPath+"BankAcceptCompCMB.log."+totalDate,true)),true);
				System.setOut(out);
				System.setErr(out);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			settle=new Settle();
	    	iRet = settle.SetOptions("netpay.cmbchina.com");
	   		if (iRet == 0)
			{
				System.out.println("SetOptions ok");
	    	}
	    	else
			{
				System.out.println(settle.GetLastErr(iRet));
				System.out.println(settle.GetLastErr(iRet));
				return;
			}
	
	    	iRet = settle.LoginC("0451","001088","205634");
	    	if (iRet == 0)
			{
				System.out.println("Login ok");
		    }
	    	else
			{
				System.out.println(settle.GetLastErr(iRet));
				return;
			}
	    	
	    	iRet = bankAcceptCompCMB.doComp();
	    	if (iRet != 0)
			{
				System.out.println("COMP ERR");
		    }
	    	
	    	iRet = settle.Logout();
	    	if (iRet == 0)
			{
				System.out.println("Login OUT ok");
		    }
	    	else
			{
				System.out.println(settle.GetLastErr(iRet));
				return;
			}
	    	
	    	//关闭重定向！
	    	out.close();
	    	System.setOut(console);
			
			try {
	        	System.out.println("......sleeping......");
				Thread.sleep(1000*60*10) ;
				System.out.println("......wake up......");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		
		
		System.out.println("......exit......");
			
	}

	private int moveHis(ReBankAccept aReBankAccept) {
		// TODO Auto-generated method stub
		List<ReBankOrderAccept> reBankOrderAcceptList = sqlSessionTemplate.selectList("com.tmount.db.order.dao.ReBankOrderAcceptMapper.selectByBankAccept",aReBankAccept.getBankAccept()); 
		
		for(ReBankOrderAccept reBankOrderAccept:reBankOrderAcceptList){
			sqlSessionTemplate.delete("com.tmount.db.order.dao.ReBankOrderAcceptMapper.deleteByBankAndOrder", reBankOrderAccept);
			ReBankOrderAcceptHis reBankOrderAcceptHis  = new ReBankOrderAcceptHis();
			reBankOrderAcceptHis.setBankAccept(reBankOrderAccept.getBankAccept());
			reBankOrderAcceptHis.setOrderNo(reBankOrderAccept.getOrderNo());
			reBankOrderAcceptHis.setOrgId(reBankOrderAccept.getOrgId());
			
			sqlSessionTemplate.insert("com.tmount.db.order.dao.ReBankOrderAcceptHisMapper.insertSelective", reBankOrderAcceptHis);
			
		}
		
		ReBankAcceptKey reBankAcceptKey = new ReBankAcceptKey();
		reBankAcceptKey.setBankAccept(aReBankAccept.getBankAccept());
		reBankAcceptKey.setOrgId(aReBankAccept.getOrgId());	
		sqlSessionTemplate.delete("com.tmount.db.order.dao.ReBankAcceptMapper.deleteByPrimaryKey", reBankAcceptKey);
		
		ReBankAcceptHis reBankAcceptHis  = new ReBankAcceptHis ();
		reBankAcceptHis.setBankAccept(aReBankAccept.getBankAccept());
		reBankAcceptHis.setChangeTime(pubService.getDbTime().getDbTime());
		reBankAcceptHis.setCheckType(aReBankAccept.getCheckType());
		reBankAcceptHis.setOrgId(aReBankAccept.getOrgId());
		reBankAcceptHis.setPayCommitTime(aReBankAccept.getPayCommitTime());
		reBankAcceptHis.setPayId(aReBankAccept.getPayId());
		reBankAcceptHis.setPayTime(aReBankAccept.getPayTime());
		reBankAcceptHis.setPayType(aReBankAccept.getPayType());
		reBankAcceptHis.setPrice(aReBankAccept.getPrice());
		reBankAcceptHis.setRetAccept(aReBankAccept.getRetAccept());
		reBankAcceptHis.setRetMsg(aReBankAccept.getRetMsg());
		
		sqlSessionTemplate.insert("com.tmount.db.order.dao.ReBankAcceptHisMapper.insertSelective", reBankAcceptHis);
		
		return 0;
	}

	private int doComp() {
		// TODO Auto-generated method stub
		DbTime totalTime =  pubService.getDbTime();
		String beginDate = "";
		String endDate = pubService.getTotalDateStr();
		String yesterdayStr = pubService.getYesterdayStr();
		
		ReBankAccept reBankAccept = new ReBankAccept();
		reBankAccept.setOrgId(1);
		reBankAccept.setCheckType("N");
		List<ReBankAccept> reBankAcceptList= sqlSessionTemplate.selectList("com.tmount.db.order.dao.ReBankAcceptMapper.selectBySelective",reBankAccept);
		System.out.println("...records..."+reBankAcceptList.size()+"...");
		for(ReBankAccept aReBankAccept:reBankAcceptList){
			
			String billOrder = aReBankAccept.getBankAccept()+"";
			Date payTime = aReBankAccept.getPayTime();
			SimpleDateFormat df  = new SimpleDateFormat("yyyyMMdd");
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(payTime); 
			String  payDate=df.format(gc.getTime());
			
			
			System.out.println("payDate = "+ payDate);
			System.out.println("billOrder = "+ billOrder);
			
			StringBuffer strbuf = new StringBuffer();
			iRet = settle.QuerySingleOrder(payDate,billOrder,strbuf);
			if (iRet == 0)
			{
				//System.out.println(strbuf);
				String[] strArr = strbuf.toString().split("\n");
				if(strArr.length!=4){
					System.out.println("...strbuf format error...");
					return -1;
				}
				String billData = strArr[0];//交易日期
				String dealData = strArr[1];//处理日期
				String orderStatus = strArr[2];//定单状态 0－已结帐，1－已撤销，2－部分结帐，3－退款，4－未结帐，5-无效状态，6－未知状态
				String strAmount = strArr[3];//定单金额
				
				System.out.println("billData:["+billData+"]");
				System.out.println("dealData:["+dealData+"]");
				System.out.println("orderStatus:["+orderStatus+"]");
				System.out.println("strAmount:["+strAmount+"]");
				
				if("0".equals(orderStatus)){
					if("Y".equals(aReBankAccept.getPayType())){//已经是支付状态
						
					}else{
						int payCount = sqlSessionTemplate.selectOne("com.tmount.db.order.dao.ReBankOrderAcceptMapper.countPayStatusByBankAccept",aReBankAccept.getBankAccept());
						if(payCount>0){//银行流水对应的商城订单有支付完成的
							
						}else{//银行流水对应的商城订单没有支付完成的,对该未结账订单进行 支付（商城的支付服务逻辑）、结账
							OrderService orderservice  = new OrderService();
							
							long payId = 0;
							try {
								payId = orderService.userBankOrderPay(aReBankAccept.getBankAccept(),1,"通过对账程序对本地的订单进行缴费");
								System.out.println("@:***payId***["+payId+"]");
							} catch (ShopBusiException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ShopParamException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//此处调用封装支付服务的函数，并将函数返回值赋给payId
							aReBankAccept.setPayType("Y");
							aReBankAccept.setPayCommitTime(pubService.getDbTime().getDbTime());
							aReBankAccept.setPayId(payId);
							
						}
					}
					
					aReBankAccept.setCheckType("Y");	
					sqlSessionTemplate.update("com.tmount.db.order.dao.ReBankAcceptMapper.updateByPrimaryKeySelective", aReBankAccept);
					
					//入银行结算表 对账成功写结算表
					BkBankChangeAccept bkBankChangeAccept = new BkBankChangeAccept();
					bkBankChangeAccept.setBankAccept(aReBankAccept.getBankAccept());
					bkBankChangeAccept.setOrgId(aReBankAccept.getOrgId());
					bkBankChangeAccept.setPrice(aReBankAccept.getPrice());
					bkBankChangeAccept.setPayTime(aReBankAccept.getPayTime());
					bkBankChangeAccept.setPayId(aReBankAccept.getPayId());
					sqlSessionTemplate.insert("com.tmount.db.order.dao.BkBankChangeAcceptMapper.insertSelective",bkBankChangeAccept);
					
				}else{
					continue;
				}
				
				
				
				
			}else{
				System.out.println("...iRet..."+iRet);
				System.out.println(settle.GetLastErr(iRet));
				
				System.out.println("...totalTime.getDbTime().getHours()..."+totalTime.getDbTime().getHours());
				System.out.println("...Long.parseLong(payDate)..."+Long.parseLong(payDate));
				System.out.println("...Long.parseLong(pubService.getYesterdayStr())..."+Long.parseLong(pubService.getYesterdayStr()));
				//招商银行订单的有效期是到第二天的1点（这里防止有遗漏，设置到两点之后失效）
				if(totalTime.getDbTime().getHours()>1&&Long.parseLong(payDate)<= Long.parseLong(pubService.getYesterdayStr())){
					
				}else{
					continue;
				}
				
			}
			moveHis(aReBankAccept);
			
			
			
		}
		
		return 0;
	}
}
