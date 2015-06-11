package com.tmount.system;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cmb.netpayment.Settle;

import com.tmount.business.order.service.OrderService;
import com.tmount.business.pub.service.PubService;
import com.tmount.db.order.dto.ReBankAccept;
import com.tmount.db.order.dto.ReBankAcceptKey;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopParamException;

public class CMBSettleOrder {
	static private int iRet;
	static Settle settle;
	
	private ApplicationContext context = null;
	private SqlSessionTemplate sqlSessionTemplate = null;
	private FreeMarkerConfigurer freemarkerConfig = null;
	private OrderService orderService;
	private PubService pubService;
	
	public CMBSettleOrder(){
		
		context = new ClassPathXmlApplicationContext("springAppContext.xml");  
		/*获取mybatis工厂类*/
		sqlSessionTemplate = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
		/*获取freemaker工厂类*/
		freemarkerConfig = (FreeMarkerConfigurer) context.getBean("freemarkerConfig");
		
		orderService = (OrderService) context.getBean("orderService");
		
		pubService = (PubService)context.getBean("pubService");
	}
	
	 private  void querySettledOrder()
    {
		DbTime totalTime =  pubService.getDbTime();
		String beginDate = "";
		String endDate = pubService.getTotalDateStr();
		//招商银行订单的有效期是到第二天的1点（这里防止有遗漏，设置到两点之前）
		if(totalTime.getDbTime().getHours()<=1){
			beginDate = pubService.getYesterdayStr();
		}else{
			beginDate = pubService.getTotalDateStr();
		}
		 
		
    	StringBuffer strbuf = new StringBuffer();
    	settle.PageReset();
    	while(!settle.m_bIsLastPage){
	    	iRet = settle.QuerySettledOrderByPage(beginDate,endDate,50,strbuf);
	    	if (iRet == 0)
			{
				System.out.println("QuerySettledOrder ok");
				System.out.println("@:***strbuf***begin***\n"+strbuf+"\n@:***strbuf***end***");
				String[] strArr = strbuf.toString().split("\n");
				System.out.println("strArr.length:["+strArr.length+"]");
				if(strArr.length<5){
					System.out.println("...have no settled orders...");
					return ;
				}
				for(int i = 0 ;i<strArr.length;i=i+5){
					String billData = strArr[i+0];//交易日期
					String dealData = strArr[i+1];//处理日期
					String strAmount = strArr[i+2];//金额
					String strBillOrder = strArr[i+3];//定单号
					String orderStatus = strArr[i+4];//定单状态 “0”为已结帐，“1”为已撤销，“2”为部分结帐，“3”为退款记录
					
					System.out.println("billData:["+billData+"]");
					System.out.println("dealData:["+dealData+"]");
					System.out.println("strAmount:["+strAmount+"]");
					System.out.println("strBillOrder:["+strBillOrder+"]");
					System.out.println("orderStatus:["+orderStatus+"]");
					
					ReBankAcceptKey reBankAcceptKey = new ReBankAcceptKey();
					reBankAcceptKey.setBankAccept(Long.parseLong(strBillOrder));
					reBankAcceptKey.setOrgId(1);
					ReBankAccept reBankAccept = sqlSessionTemplate.selectOne("com.tmount.db.order.dao.ReBankAcceptMapper.selectByPrimaryKey",reBankAcceptKey);
					if(reBankAccept==null){
						System.out.println("can find info of this order !");
						continue;
					}
					//判断银行接口流水表的支付状态
					if("Y".equals(reBankAccept.getPayType())){
						
					}else if("N".equals(reBankAccept.getPayType())){
						
						int payCount = sqlSessionTemplate.selectOne("com.tmount.db.order.dao.ReBankOrderAcceptMapper.countPayStatusByBankAccept",Long.parseLong(strBillOrder));
						if(payCount>0){//银行流水对应的商城订单有支付完成的
							
						}else{//银行流水对应的商城订单没有支付完成的,对该未结账订单进行 支付（商城的支付服务逻辑）、结账
							OrderService orderservice  = new OrderService();
							
							long payId = 0;
							try {
								payId = orderService.userBankOrderPay(Long.parseLong(strBillOrder),1,"通过对账程序对本地的订单进行缴费");
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
							reBankAccept.setPayType("Y");
							reBankAccept.setPayCommitTime(pubService.getDbTime().getDbTime());
							reBankAccept.setPayId(payId);
							
						}
					}
					
					reBankAccept.setCheckType("Y");	
					sqlSessionTemplate.update("com.tmount.db.order.dao.ReBankAcceptMapper.updateByPrimaryKeySelective", reBankAccept);
				}
	    	}
	    	else
			{
				System.out.println(settle.GetLastErr(iRet));
			}
    	}
    }
	
	public static void main(String[] args) {
		
		System.out.println("......enter......");
		
		
		CMBSettleOrder cmbSettleOrder = new CMBSettleOrder();
		while(true){
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
				System.out.println("LoginC ok");
		    }
	    	else
			{
				System.out.println(settle.GetLastErr(iRet));
				return;
			}
	    	
	    	cmbSettleOrder.querySettledOrder();
	
	        settle.Logout();
	        
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
}
