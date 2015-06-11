package com.tmount.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tmount.business.order.service.OrderService;
import com.tmount.config.SyDataTypeDicStatic;
import com.tmount.db.order.dto.ReUserOrder;
import com.tmount.db.pub.dao.DbSequenceMapper;
import com.tmount.db.pub.dao.DbTimeMapper;
import com.tmount.exception.ShopBusiException;

/**
 * 订单类的后台处理程序
 * @author lugz
 *
 */
public class AutoOrderDeal {
	private ApplicationContext context = null;
	private OrderService orderService = null;
	private DbTimeMapper dbTimeMapper = null;
	private DbSequenceMapper dbSequenceMapper;
	
	public AutoOrderDeal()
	{
		context = new ClassPathXmlApplicationContext("springAppContext.xml");
		orderService = (OrderService) context.getBean("orderService");
		dbTimeMapper = (DbTimeMapper) context.getBean("dbTimeMapper");
		dbSequenceMapper = (DbSequenceMapper) context.getBean("dbSequenceMapper");
	}
	
	public static void main(String[] args) {
		AutoOrderDeal autoOrderDeal = new AutoOrderDeal();

		if(args[0].equals("confirm"))	{/*15天外，发货的订单，改成订单结束*/
			autoOrderDeal.AutoOrderConfirmReceipt();
		}else if(args[0].equals("cancel"))	{/*未付款的订单3天后，自动取消*/
			autoOrderDeal.AutoOrderCancel();
		}{
			System.out.println("您输入的入参不合规范!!!!!!!"+args[0]);
		}
	}
	
	/*自动确认收货*/
	public void AutoOrderConfirmReceipt()
	{
		Long startT=System.currentTimeMillis();
		System.out.println("AutoOrderConfirmReceipt start------------------------------------------");
		Date opTime = dbTimeMapper.selectDbTime().getDbTime();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(opTime);
		calendar.add(Calendar.DATE, -15);	//15天之前。
		Date endUpdateTime = calendar.getTime();
				
		Map<String, Object> paramIn = new HashMap<String, Object>();
		paramIn.put("state", "30");
		paramIn.put("payStatus", "Y");
		paramIn.put("rowCount", 200);
		paramIn.put("endUpdateTime", endUpdateTime);

		while(true) {
			List<ReUserOrder> reUserOrderList = orderService.getReUserOrderListByUserId(paramIn);
			if (reUserOrderList != null) {
				for(ReUserOrder reUserOrder: reUserOrderList) {
					try {
						orderService.confirmReceipt(reUserOrder.getOrderNo(), "后台自动确认处理");
					} catch (ShopBusiException e) {
						System.out.println("confirmReceipt orderNo:" + reUserOrder.getOrderNo() + ", errCode:" + e.getFullErrorCode() + ", errMsg:" + e.getErrorMessage());
					}
				}
				
				if (reUserOrderList.size() < 1) {
					break;
				}
			} else {
				break;
			}
			
		}
    	Long endT=System.currentTimeMillis();
    	System.out.println("AutoOrderConfirmReceipt end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
	}	

	/*未付款的订单3天后，自动取消*/
	public void AutoOrderCancel()
	{
		Long startT=System.currentTimeMillis();
		System.out.println("AutoOrderCancel start------------------------------------------");
		Date opTime = dbTimeMapper.selectDbTime().getDbTime();
		Long loginAccept = dbSequenceMapper.selectSeqByName(SyDataTypeDicStatic.LoginAcceptSeq).getSeqValue();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(opTime);
		calendar.add(Calendar.DATE, -3);	//3天之前。
		Date endUpdateTime = calendar.getTime();
				
		Map<String, Object> paramIn = new HashMap<String, Object>();
		paramIn.put("state", "10");	//订单状态为等待买家付款。
		paramIn.put("payStatus", "N");
		paramIn.put("rowCount", 200);
		paramIn.put("endUpdateTime", endUpdateTime);

		while(true) {
			List<ReUserOrder> reUserOrderList = orderService.getReUserOrderListByUserId(paramIn);
			if (reUserOrderList != null) {
				for(ReUserOrder reUserOrder: reUserOrderList) {
					try {
						orderService.removeUserOrder(reUserOrder.getOrderNo(), "后台自动取消处理", opTime, loginAccept);
					} catch (ShopBusiException e) {
						System.out.println("confirmReceipt orderNo:" + reUserOrder.getOrderNo() + ", errCode:" + e.getFullErrorCode() + ", errMsg:" + e.getErrorMessage());
					}
				}
				
				if (reUserOrderList.size() < 1) {
					break;
				}
			} else {
				break;
			}
			
		}
    	Long endT=System.currentTimeMillis();
    	System.out.println("AutoOrderCancel end----------------------------run time is "+(endT-startT)/1000.0+" seconds");
	}	
}



