package com.tmount.business.order.controller;


import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.tmount.bundle.LogisticReason;
import com.tmount.business.order.service.OrderService;
import com.tmount.db.order.dto.ReLogisticsLog;
import com.tmount.db.order.dto.ReOrderDeliver;
import com.tmount.db.order.dto.ReOrderDeliverAdd;
import com.tmount.db.order.dto.ReOrderDeliverAddHis;
import com.tmount.db.order.dto.ReUserOrder;
import com.tmount.db.order.vo.LogisticReceivInfo;
import com.tmount.util.LogisticTransXML;
import com.tmount.util.TransXML;

import freemarker.template.Template;

/**
 * 检索获取商品的列表信息
 * 
 * @author rendi
 * 
 */
@Controller
public class LogisticReceiv {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;
	
	@RequestMapping(value = "logistic.order.receive")

    public void doRequest(HttpServletRequest request, HttpServletResponse response) {
		String reason="";
		String txLogisticID="";
		String sucessFlag="false";/*初始化为失败，在成功时会重置此标志*/
		try{
			request.setCharacterEncoding("UTF8");
			/*明文xml报文*/
			String xmlData = request.getParameter("logistics_interface");
			/*加密xml报文*/
			String xmlPassData = request.getParameter("data_digest");
			/*线上线下标识*/
			String sendType = request.getParameter("type");
			
			String dataxml = "<?xml version=\"1.0\" encoding=\"gbk\"?>";
			
			System.out.println("xmlData==============="+xmlData);
			System.out.println("xmlDatagbk==============="+new String(xmlData.getBytes("gbk")));
			System.out.println("xmlPassData==============="+xmlPassData);
			System.out.println("sendType==============="+sendType);
			/*由于需要返回报文，报文中需要有订单编号所以先进行对象转换*/
			LogisticReceivInfo logisticReceivInfo=(LogisticReceivInfo) TransXML.xml2OBJ(dataxml+xmlData, LogisticReceivInfo.class);
			txLogisticID=logisticReceivInfo.getTxLogisticID();
			/*先加密，然后做校验，校验通过*/
			String transXmlPassData = LogisticTransXML.transStringYTO(xmlData, "123456");
			if(transXmlPassData.equals(xmlPassData)){
				
				System.out.println("校验通过======= transXmlPassData is "+transXmlPassData+" xmlPassData is "+xmlPassData);
				
				/*需要判断是订单状态信息，还是订单流转信息*/
				ReOrderDeliver reOrderDeliver = new ReOrderDeliver();
				if(logisticReceivInfo.getInfoType().equals("STATUS")){/*圆通定义的通知类型*/
					/*
					 * ACCEPT		接单
					 * UNACCEPT		不接单
					 * GOT			揽收成功
					 * NOT_SEND		揽收失败
					 * SENT_SCAN	派件扫描
					 * TRACKING		流转信息
					 * SIGNED		签收成功
					 * FAILED		签收失败
					 * */
					ReOrderDeliverAddHis reOrderDeliverAddHis = new ReOrderDeliverAddHis();
					if(logisticReceivInfo.getInfoContent().equals("GOT")){/*揽收成功*/
						/*揽收成功需要删除增量表记录历史表，更新订单表状态*/
						/*更新订单发货表状态为确认成功，订单状态更新，到此结束,删除增量，入历史*/
						/*处理增量表及增量历史表以及用户订单表*/
						/*获取增量表中数据*/
						reOrderDeliverAddHis = orderService.getReOrderDeliverAddByTxLogisticID(
								logisticReceivInfo.getTxLogisticID());
						/*历史表中需要修改的值*/
						/*确认成功*/
						reOrderDeliverAddHis.setStateCode(2);
						/*删除订单发货表*/
						ReOrderDeliverAdd reOrderDeliverAdd = new ReOrderDeliverAdd();
						reOrderDeliverAdd.setOrderNo(reOrderDeliverAddHis.getOrderNo());
						reOrderDeliverAdd.setStateType(reOrderDeliverAddHis.getStateType());
						orderService.removeReOrderDeliverAdd(reOrderDeliverAdd);
						/*记录历史表*/
						orderService.insertReOrderDeliverAddHis(reOrderDeliverAddHis);
						/*修改订单发货表*/
						reOrderDeliver.setOrderNo(reOrderDeliverAddHis.getOrderNo());
						reOrderDeliver.setStateCode(2);
						orderService.updateReOrderDeliverStateByOrderNo(reOrderDeliver);
						/*修改用户订单状态*/
						ReUserOrder reUserOrder = new ReUserOrder();
						reUserOrder.setOrderNo(reOrderDeliverAddHis.getOrderNo());
						orderService.updateOrderStatusByLogis(reUserOrder);
					}else if(logisticReceivInfo.getInfoContent().equals("NOT_SEND") || logisticReceivInfo.getInfoContent().equals("UNACCEPT")){
						/*修改订单发货表状态为确认失败，订单状态到此结束更新，增量删除，入历史*/
						reOrderDeliverAddHis = orderService.getReOrderDeliverAddByTxLogisticID(
								logisticReceivInfo.getTxLogisticID());
						/*删除订单发货表*/
						ReOrderDeliverAdd reOrderDeliverAdd = new ReOrderDeliverAdd();
						reOrderDeliverAdd.setOrderNo(reOrderDeliverAddHis.getOrderNo());
						reOrderDeliverAdd.setStateType(reOrderDeliverAddHis.getStateType());
						orderService.removeReOrderDeliverAdd(reOrderDeliverAdd);
						/*记录历史表*/
						/*历史表中需要修改的值*/
						reOrderDeliverAddHis.setStateCode(3);/*确认失败*/
						/*提取失败原因*/
						reOrderDeliverAddHis.setReStateCode(logisticReceivInfo.getRemark());
						reOrderDeliverAddHis.setReStateDesc(LogisticReason.getErrMsg(logisticReceivInfo.getRemark(),
								logisticReceivInfo.getInfoContent()));
						orderService.insertReOrderDeliverAddHis(reOrderDeliverAddHis);
						/*修改订单发货表*/
						reOrderDeliver.setOrderNo(reOrderDeliverAddHis.getOrderNo());
						reOrderDeliver.setStateCode(3);
						orderService.updateReOrderDeliverStateByOrderNo(reOrderDeliver);
					}else{
						/*其他都入日志表即可，无特殊处理逻辑*/
					}
					/*无论是什么标识，都需要记录日志表*/
					ReLogisticsLog reLogisticsLog = new ReLogisticsLog();
					reLogisticsLog.setLogisticsLoginId(orderService.getLogisticsSeq("logistics_login_id"));
					reLogisticsLog.setTxLogisticsId(logisticReceivInfo.getTxLogisticID());
					reLogisticsLog.setInfoType(logisticReceivInfo.getInfoType());
					reLogisticsLog.setInfoContent(logisticReceivInfo.getInfoContent());
					reLogisticsLog.setAcceptTime(logisticReceivInfo.getAcceptTime());
					if(logisticReceivInfo.getRemark()!=null){
						if(!logisticReceivInfo.getRemark().trim().equals("")){
						/*如果remak不为空，证明有错误，需要提取错误原因*/
							reLogisticsLog.setRemark(LogisticReason.getErrMsg(logisticReceivInfo.getRemark(),
								logisticReceivInfo.getInfoContent()));
						}
					}
					if(logisticReceivInfo.getMailNo()!=null)
						reLogisticsLog.setLogisticsNo(logisticReceivInfo.getMailNo());
					if(logisticReceivInfo.getCurrentCity()!=null)
						reLogisticsLog.setCurrentCity(logisticReceivInfo.getCurrentCity());
					if(logisticReceivInfo.getNextCity()!=null)
						reLogisticsLog.setNextCity(logisticReceivInfo.getNextCity());
					if(logisticReceivInfo.getContactInfo()!=null)
						reLogisticsLog.setContactInfo(logisticReceivInfo.getContactInfo());
					if(logisticReceivInfo.getName()!=null)
						reLogisticsLog.setOpName(logisticReceivInfo.getName());
					if(logisticReceivInfo.getFacility()!=null)
						reLogisticsLog.setFacility(logisticReceivInfo.getFacility());
					if(logisticReceivInfo.getWeight()!=null)
						reLogisticsLog.setWeight(logisticReceivInfo.getWeight());
					if(logisticReceivInfo.getTrackingInfo()!=null)
						reLogisticsLog.setTrackingInfo(logisticReceivInfo.getTrackingInfo());
					int insLogResult=orderService.insertReLogisticsLog(reLogisticsLog);
					System.out.println("insLogResult is ==============="+insLogResult);
				}else{
					System.out.println("logisticReceivInfo.getInfoType() is ==="+logisticReceivInfo.getInfoType());
				}
				sucessFlag="true";
			}else{
				reason="S01";/*如果明文加密后与传递的密文不匹配，证明xml报文非法*/
			}
		}catch(Exception e){
			reason="S07";/*默认失败全部为系统异常*/
			e.printStackTrace();
		}finally{
			try{
				Map<String,Object> responseMap = new HashMap<String,Object>();
				responseMap.put("txLogisticID",txLogisticID);
				responseMap.put("sucessFlag",sucessFlag);
				responseMap.put("reason",reason);
				Template t = freemarkerConfig.getConfiguration().getTemplate("YTOResponse.ftl");
				StringWriter out = new StringWriter();
				t.process(responseMap, out);
				String data = out.toString();
				out.flush();
				response.getWriter().print(data);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
