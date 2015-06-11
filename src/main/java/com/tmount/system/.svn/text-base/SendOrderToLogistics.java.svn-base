package com.tmount.system;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.tmount.bundle.LogisticReason;
import com.tmount.db.order.dto.ReOrderDeliver;
import com.tmount.db.order.dto.ReOrderDeliverAdd;
import com.tmount.db.order.dto.ReOrderDeliverAddHis;
import com.tmount.db.order.vo.LogisticInfo;
import com.tmount.db.order.vo.LogisticResponse;
import com.tmount.db.order.vo.ReUserOrderDetailInfo;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.db.sys.dto.SysAmountDic;
import com.tmount.db.sys.vo.StatisTime;
import com.tmount.util.HttpSend;
import com.tmount.util.LogisticTransXML;
import com.tmount.util.TransXML;

import freemarker.template.Template;

public class SendOrderToLogistics{

	private ApplicationContext context = null;
	private SqlSessionTemplate sqlSessionTemplate = null;
	private FreeMarkerConfigurer freemarkerConfig = null;
	
	public SendOrderToLogistics()
	{
		context = new ClassPathXmlApplicationContext("springAppContext.xml");  
		/*获取mybatis工厂类*/
		sqlSessionTemplate = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
		/*获取freemaker工厂类*/
		freemarkerConfig = (FreeMarkerConfigurer) context.getBean("freemarkerConfig");
	}
	
	public static void main(String[] args) {
		SendOrderToLogistics sendOrderToLogistics = new SendOrderToLogistics();
		StatisTime statisTime = new StatisTime();
		sendOrderToLogistics.toLogistics(statisTime);
	}
	
	private void toLogistics(StatisTime statisTime)
	{
		String dataxml = "<?xml version=\"1.0\" encoding=\"gbk\"?>";
		/*取程序开始执行时间*/
		Long startT=System.currentTimeMillis();
		/*取当前系统时间*/
		DbTime dbTime = new DbTime();
		dbTime = sqlSessionTemplate
				.selectOne("com.tmount.db.pub.dao.DbTimeMapper.selectDbTime",new Long(1));
		/*先要从sys_amount_dic表中取出itemsTrans上次提取时间*/
		SysAmountDic sysAmountDic = getSysAmount("toLogistics");
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic==null)
		{
			return;
		}
		/*读取订单接口表，获取要发送的订单数据*/
		List<LogisticInfo> logisticInfoList = sqlSessionTemplate.selectList(
				"com.tmount.db.order.dao.ReOrderDeliverAddMapper.selectSendOrderList", statisTime);
		Iterator<LogisticInfo> logisticInfoIterator = logisticInfoList.iterator();
		while(logisticInfoIterator.hasNext())
		{
			/*物流发送接口相关信息*/
			LogisticInfo logisticInfo = logisticInfoIterator.next();
			System.out.println("toLogistics ===== deal orderno begin "+logisticInfo.getOrderNo()+" statType is "+logisticInfo.getStateType());
			/*物流接口返回相关信息*/
			LogisticResponse logisticResponse = new LogisticResponse();
			try{
				/*拼报文*/
				Map<String,String> sendXmlMap = createSendData(logisticInfo);
				if(sendXmlMap.size()==0){/*如果sendXml为空的话，证明无法拼装xml，说明有问题，需要查明原因*/
					System.out.println("toLogistics ===== error orderNo is "+logisticInfo.getOrderNo());
					/*如果发送报文为空，也需要更新状态，需要手工拼装返回信息结构体*/
					logisticResponse.setsuccess(false);
					logisticResponse.setReasonDetail("拼装发送报文失败！");
				}else{
					/*调用接口发送*/
					String recvXml = sendData(sendXmlMap,logisticInfo);
					System.out.println("recvXml is =========================="+recvXml);
					if(recvXml.trim().equals("")){/*如果返回报文为空，默认超时*/
						/*如果报文没有返回，也需要更新状态，需要手工拼装返回信息结构体*/
						logisticResponse.setsuccess(false);
						logisticResponse.setReasonDetail("接口返回报文为空，默认超时！");
					}else{
						logisticResponse = (LogisticResponse) TransXML.xml2OBJ(dataxml+recvXml, LogisticResponse.class);
						logisticResponse.setReasonDetail(LogisticReason.getSysErrMsg(logisticResponse.getReason()));
					}
				}
				System.out.println("logisticResponse.getSuccess() is ======="+logisticResponse.getSuccess());
				System.out.println("logisticResponse.getReason() is ======="+logisticResponse.getReason());
				System.out.println("logisticResponse.getReasonDetail() is ======="+logisticResponse.getReasonDetail());
				/*从这里开始处理数据，判断返回对象中的sucess标识，如果为true则为成功，否则即为失败*/
				/*发送成功，只需要修改增量表中的状态即可*/
				/*发送失败，只需要修改增量表中的状态以及原因即可*/
				ReOrderDeliverAdd reOrderDeliverAdd = new ReOrderDeliverAdd();
				reOrderDeliverAdd.setOrderNo(logisticInfo.getOrderNo());
				reOrderDeliverAdd.setStateType(logisticInfo.getStateType());
				reOrderDeliverAdd.setSendCommand(logisticInfo.getSendCommand());
				if(logisticResponse.getSuccess()){/*如果为true则发送成功并且对方处理成功，否则即为失败*/
					/*针对普通下单和取消订单，针对返回报文的解析不同*/
					if(logisticInfo.getStateType()==0){/*申请*/
						/*申请包含两类，订单申请以及运单号通知*/
						if(logisticInfo.getSendCommand()==0)/*申请*/
							reOrderDeliverAdd.setStateCode(1);
						else
							reOrderDeliverAdd.setStateCode(2);/*运单号通知*/
					}else if(logisticInfo.getStateType()==1){/*取消*/
						reOrderDeliverAdd.setStateCode(2);
					}else{
						System.out.println("toLogistics ===== logisticInfo.getStateType() is="+logisticInfo.getStateType());
					}
				}else{
					reOrderDeliverAdd.setStateCode(3);
					reOrderDeliverAdd.setReStateCode(logisticResponse.getReason());
					reOrderDeliverAdd.setReStateDesc(logisticResponse.getReasonDetail());
				}
				/*发送程序只负责修改增量表相关状态，不删除增量表*/
				int updResult = sqlSessionTemplate.update(
						"com.tmount.db.order.dao.ReOrderDeliverAddMapper.updateByPrimaryKeySelective",reOrderDeliverAdd);
				System.out.println("toLogistics ===== upd re_order_deliver_add result is "+updResult);
				if(reOrderDeliverAdd.getStateCode()==2 && logisticInfo.getStateType()==1){/*代表取消成功*/
					/*修改订单发货表，将状态改为确认成功*/
					ReOrderDeliver reOrderDeliver = new ReOrderDeliver();
					reOrderDeliver.setOrderNo(reOrderDeliverAdd.getOrderNo());
					reOrderDeliver.setStateCode(reOrderDeliverAdd.getStateCode());
					int upddeliver=sqlSessionTemplate.update(
							"com.tmount.db.order.dao.ReOrderDeliverMapper.updateByPrimaryKeySelective",reOrderDeliver);
					System.out.println("toLogistics ===== update ReOrderDeliver is "+upddeliver);
					/*当发送的取消订单，并且返回为成功的话，证明取消成功，需要删除增量表，记录历史*/
					/*订单取消，将此订单号所有记录全部删除，记录历史表，因为上面已经执行过订单取消的修改，所以可以直接删除*/
					List<ReOrderDeliverAdd> reOrderDeliverAddList = sqlSessionTemplate.selectList(
							"com.tmount.db.order.dao.ReOrderDeliverAddMapper.selectByPrimaryKey", reOrderDeliverAdd.getOrderNo());
					Iterator<ReOrderDeliverAdd> reOrderDeliverAddIterator = reOrderDeliverAddList.iterator();
					while(reOrderDeliverAddIterator.hasNext())
					{
						ReOrderDeliverAdd reOrderDeliverAddInfo = reOrderDeliverAddIterator.next();
						/*删除增量表*/
						int delResult=sqlSessionTemplate.delete(
								"com.tmount.db.order.dao.ReOrderDeliverAddMapper.deleteByPrimaryKey",reOrderDeliverAddInfo);
						System.out.println("toLogistics ===== del re_order_deliver_add stateType is "+
								reOrderDeliverAddInfo.getStateType()+" result is "+delResult);
						/*记录历史表*/
						ReOrderDeliverAddHis reOrderDeliverAddHis = new ReOrderDeliverAddHis();
						reOrderDeliverAddHis.setOrderNo(reOrderDeliverAddInfo.getOrderNo());
						reOrderDeliverAddHis.setShopId(reOrderDeliverAddInfo.getShopId());
						reOrderDeliverAddHis.setLogisticsId(reOrderDeliverAddInfo.getLogisticsId());
						if(reOrderDeliverAddInfo.getReStateCode()!=null)
							reOrderDeliverAddHis.setReStateCode(reOrderDeliverAddInfo.getReStateCode());
						if(reOrderDeliverAddInfo.getReStateDesc()!=null)
							reOrderDeliverAddHis.setReStateDesc(reOrderDeliverAddInfo.getReStateDesc());
						if(reOrderDeliverAddInfo.getCreateTime()!=null)
							reOrderDeliverAddHis.setCreateTime(reOrderDeliverAddInfo.getCreateTime());
						if(reOrderDeliverAddInfo.getStaffId()!=null)
							reOrderDeliverAddHis.setStaffId(reOrderDeliverAddInfo.getStaffId());
						reOrderDeliverAddHis.setSendCommand(reOrderDeliverAddInfo.getSendCommand());
						reOrderDeliverAddHis.setSendOnline(reOrderDeliverAddInfo.getSendOnline());
						reOrderDeliverAddHis.setStateCode(reOrderDeliverAddInfo.getStateCode());
						reOrderDeliverAddHis.setStateType(reOrderDeliverAddInfo.getStateType());
						int insResult=sqlSessionTemplate.insert(
								"com.tmount.db.order.dao.ReOrderDeliverAddHisMapper.insertSelective", reOrderDeliverAddHis);
						System.out.println("toLogistics ===== insert reOrderDeliverAddHis result is "+insResult);
					}
				}
				if(reOrderDeliverAdd.getStateCode()==2 && logisticInfo.getStateType()==0){/*状态通知*/
					/*正常流程中，在申请状态下将订单标识改为确认成功的，只有是状态通知接口*/
					ReOrderDeliverAdd reOrderDeliverAddStatus = sqlSessionTemplate.selectOne(
							"com.tmount.db.order.dao.ReOrderDeliverAddMapper.selectByStateType", reOrderDeliverAdd);
					/*记录历史表*/
					ReOrderDeliverAddHis reOrderDeliverAddHis = new ReOrderDeliverAddHis();
					reOrderDeliverAddHis.setOrderNo(reOrderDeliverAddStatus.getOrderNo());
					reOrderDeliverAddHis.setShopId(reOrderDeliverAddStatus.getShopId());
					reOrderDeliverAddHis.setLogisticsId(reOrderDeliverAddStatus.getLogisticsId());
					if(reOrderDeliverAddStatus.getReStateCode()!=null)
						reOrderDeliverAddHis.setReStateCode(reOrderDeliverAddStatus.getReStateCode());
					if(reOrderDeliverAddStatus.getReStateDesc()!=null)
						reOrderDeliverAddHis.setReStateDesc(reOrderDeliverAddStatus.getReStateDesc());
					if(reOrderDeliverAddStatus.getCreateTime()!=null)
						reOrderDeliverAddHis.setCreateTime(reOrderDeliverAddStatus.getCreateTime());
					if(reOrderDeliverAddStatus.getStaffId()!=null)
						reOrderDeliverAddHis.setStaffId(reOrderDeliverAddStatus.getStaffId());
					reOrderDeliverAddHis.setSendCommand(reOrderDeliverAddStatus.getSendCommand());
					reOrderDeliverAddHis.setSendOnline(reOrderDeliverAddStatus.getSendOnline());
					/*记录历史表的同时将状态更新即可*/
					reOrderDeliverAddHis.setStateCode(reOrderDeliverAdd.getStateCode());
					reOrderDeliverAddHis.setStateType(reOrderDeliverAddStatus.getStateType());
					int insResultStatus=sqlSessionTemplate.insert(
							"com.tmount.db.order.dao.ReOrderDeliverAddHisMapper.insertSelective", reOrderDeliverAddHis);
					System.out.println("toLogistics ===== insert reOrderDeliverAddHis insResultStatus is "+insResultStatus);
					
					/*删除增量表*/
					int delResultStatus=sqlSessionTemplate.delete(
							"com.tmount.db.order.dao.ReOrderDeliverAddMapper.deleteByPrimaryKey",reOrderDeliverAddStatus);
					System.out.println("toLogistics ===== delete reOrderDeliverAdd delResultStatus is "+delResultStatus);
				}
			}catch(Exception e)
			{
				System.out.println("toLogistics ===== error msg is="+e.getMessage());
				System.out.println("toLogistics ===== deal orderno end with error "+logisticInfo.getOrderNo()+" statType is "+logisticInfo.getStateType());
				e.printStackTrace();
				continue;
			}finally{
				System.out.println("toLogistics ===== deal orderno end "+logisticInfo.getOrderNo()+" statType is "+logisticInfo.getStateType());
			}
		}
		/*最后需要在更新此进程在表中的记录，主要更新本次修改的截止时间*/
		sysAmountDic.setAmountDate(dbTime.getDbTime());
		sysAmountDic.setStartStatus(0);
        int updDone=setSysAmount(sysAmountDic,0);
    	System.out.println("toLogistics updDone is "+updDone);
    	Long endT=System.currentTimeMillis();
    	System.out.println("toLogistics ===== deal count is "+logisticInfoList.size());
    	System.out.println("toLogistics ===== run time is "+(endT-startT)/1000.0+" seconds");
	}
	/*按照不同的物流公司，拼装不同的报文*/
	private Map<String,String> createSendData(LogisticInfo logisticInfo) throws Exception
	{
		Map<String,String> sendXmlMap = new HashMap<String,String>();
		if(logisticInfo.getLogisticProviderId().equals("YTO"))
			sendXmlMap=createYuanTong(logisticInfo);
		else
			System.out.println("toLogistics ===== error logisticInfo.getLogisticProviderId() is "+logisticInfo.getLogisticProviderId());
		return sendXmlMap;
	}
	/*发送接口*/
	private String sendData(Map<String,String> sendXmlMap,LogisticInfo logisticInfo) throws Exception
	{
		String returnData="";
		String sendUrl="";
		String charset="";
		String type="offline";/*我们默认是线下流程*/
		if(logisticInfo.getSendOnline().equals("Y"))/*判断增量表中的线上线下标识*/
			type="online";
		sendXmlMap.put("type",type);
		sendXmlMap.put("clientId",logisticInfo.getClientId());
		if(logisticInfo.getLogisticProviderId().equals("YTO")){/*圆通发送接口*/
			sendUrl="http://116.228.70.232/eop/CommonOrderServlet.action";
//			sendUrl="http://localhost:8080/sshop/logistic.order.receive";
			charset="UTF-8";
			returnData=HttpSend.doPost(sendUrl, sendXmlMap, charset);
		}
		System.out.println("logistics_interface is ===================="+sendXmlMap.get("logistics_interface"));
		System.out.println("data_digest is ===================="+sendXmlMap.get("data_digest"));
		return returnData;
	}
	
	private Map<String,String> createYuanTong(LogisticInfo logisticInfo) throws Exception
	{
		Map<String,String> sendXmlMap = new HashMap<String,String>();
		String data="";
		/*包含两部分 0 申请  1 取消*/
		if(logisticInfo.getStateType()==1)
		{
			Map<String,Object> cancelMap = new HashMap<String,Object>();
			cancelMap.put("txLogisticID", logisticInfo.getLogisticsNo());
			cancelMap.put("infoType", "INSTRUCTION");
			cancelMap.put("infoContent", "WITHDRAW");
			cancelMap.put("remark", "");
			Template t = freemarkerConfig.getConfiguration().getTemplate("YTOCancel.ftl");
			StringWriter out = new StringWriter();
			t.process(cancelMap, out);
			data = out.toString();
			out.flush();
			sendXmlMap.put("logistics_interface",data);
			sendXmlMap.put("data_digest",LogisticTransXML.transStringYTO(data,"123456"));
		}else if(logisticInfo.getStateType()==0)
		{
			if(logisticInfo.getSendCommand()==0){/*申请报文*/
				Map<String,Object> createMap = new HashMap<String,Object>();
				createMap.put("clientID",logisticInfo.getClientId());
				createMap.put("logisticProviderID",logisticInfo.getLogisticProviderId());
				createMap.put("customerId", logisticInfo.getShopId().toString());
				System.out.println("logisticInfo.getTxLogisticID() ======="+logisticInfo.getTxLogisticID());
				createMap.put("txLogisticID",logisticInfo.getTxLogisticID()==null?"":logisticInfo.getTxLogisticID());
				createMap.put("tradeNo", "");
				createMap.put("mailNo", logisticInfo.getLogisticsNo()==null?"":logisticInfo.getLogisticsNo());
				/*需要根据线上流程实际填写，需要根据是否货到付款填写*/
				if(logisticInfo.getOrderStatus()==2){/*货到付款*/
					createMap.put("totalServiceFee", 0);
					createMap.put("codSplitFee", 0);
					createMap.put("orderType", 0);
					/*货到付款，itemsvalue必填*/
					createMap.put("itemsValue", 0);
				}else{/*非货到付款*/
					createMap.put("totalServiceFee", 0);
					createMap.put("codSplitFee", 0);
					createMap.put("orderType", "1");
					/*货到付款，itemsvalue必填*/
					createMap.put("itemsValue", 0);
				}
				createMap.put("serviceType", "1");
				createMap.put("flag", "0");
				createMap.put("senderUserName",logisticInfo.getSendUserName());
				createMap.put("senderPostCode",logisticInfo.getSendPostCode());
				createMap.put("senderPhone",logisticInfo.getSendContactPhone());
				createMap.put("senderMobile",logisticInfo.getSendMobile());
				createMap.put("senderPostProv",logisticInfo.getSendProv());
				createMap.put("senderPostCity",logisticInfo.getSendCity());
				createMap.put("senderPostArea",logisticInfo.getSendArea());
				createMap.put("senderAddress",logisticInfo.getSendAddress());
				createMap.put("receiverUserName",logisticInfo.getReceivUserName());
				createMap.put("receiverPostCode",logisticInfo.getReceivPostCode());
				createMap.put("receiverPhone",logisticInfo.getReceivContactPhone());
				createMap.put("receiverMobile",logisticInfo.getReceivMobile());
				createMap.put("receiverPostProv",logisticInfo.getReceivProv());
				createMap.put("receiverPostCity",logisticInfo.getReceivCity());
				createMap.put("receiverPostArea",logisticInfo.getReceivArea());
				createMap.put("receiverAddress",logisticInfo.getReceivAddress());
				/*格式化日期格式，按照dataFormat格式化*/
				String dataFormat = "yyyy-MM-dd HH:mm:ss.S z";
				SimpleDateFormat dFormat = new SimpleDateFormat(dataFormat,Locale.US);
				String sendStartTime="";
				String sendEndTime="";
				if(logisticInfo.getSendStartTime() != null)
					sendStartTime=dFormat.format(logisticInfo.getSendStartTime());
				if(logisticInfo.getSendEndTime() != null)
					sendEndTime=dFormat.format(logisticInfo.getSendEndTime());
				createMap.put("sendStartTime",sendStartTime);
				createMap.put("sendEndTime",sendEndTime);
				createMap.put("goodsValue", 0);
				
				/*获取订单下商品信息*/
				List<ReUserOrderDetailInfo> itemsList = new ArrayList<ReUserOrderDetailInfo>();
				createMap.put("itemsList", itemsList);
				List<ReUserOrderDetailInfo> orderItemsList = sqlSessionTemplate.selectList(
						"com.tmount.db.order.dao.ReUserOrderDetailMapper.selectDetailByOrderNo",logisticInfo.getOrderNo());
				/*必须使用add方法，freemakerxml模版才可以识别*/
				Iterator<ReUserOrderDetailInfo> itemsIterator = orderItemsList.iterator();
				while(itemsIterator.hasNext())
				{
					ReUserOrderDetailInfo reUserOrderDetailInfo = itemsIterator.next();
					itemsList.add(reUserOrderDetailInfo);
				}
				createMap.put("insuranceValue", 0);
				createMap.put("special", "");
				createMap.put("remark", "");
				Template t = freemarkerConfig.getConfiguration().getTemplate("YTOCreate.ftl");
				StringWriter out = new StringWriter();
				t.process(createMap, out);
				data = out.toString();
				out.flush();
				sendXmlMap.put("logistics_interface",data);
				sendXmlMap.put("data_digest",LogisticTransXML.transStringYTO(data,"123456"));
			}else{/*为1就是更新运单号*/
				Map<String,Object> updateMap = new HashMap<String,Object>();
				updateMap.put("clientID",logisticInfo.getClientId());
				updateMap.put("logisticProviderID",logisticInfo.getLogisticProviderId());
				updateMap.put("txLogisticID", logisticInfo.getTxLogisticID()==null?"":logisticInfo.getTxLogisticID());
				updateMap.put("mailNo", logisticInfo.getLogisticsNo());
				updateMap.put("remark","");
				Template t = freemarkerConfig.getConfiguration().getTemplate("YTOUpdate.ftl");
				StringWriter out = new StringWriter();
				t.process(updateMap, out);
				data = out.toString();
				out.flush();
				sendXmlMap.put("logistics_interface",data);
				sendXmlMap.put("data_digest",LogisticTransXML.transStringYTO(data,"123456"));
			}
				
		}else{/*非申请和取消，证明前台传值有问题，需要手工处理*/
			System.out.println("toLogistics ===== error logisticInfo.getOrderStatus() is "+logisticInfo.getOrderStatus());
		}
		return sendXmlMap;
	}
	
	/*
	 * 完成SysAmountDic表的校验及更新操作
	 * flag为0代表检查表中对应的程序是否启动，如果没有启动则将标识位置为已启动，返回成功
	 * flag为1代表是程序结束，将标识位置为0，正常状态
	 * */
	public SysAmountDic getSysAmount(String proName)
	{
		/*先要从sys_amount_dic表中取出itemsTrans上次提取时间*/
		SysAmountDic sysAmountDic = new SysAmountDic();
		sysAmountDic =  sqlSessionTemplate
				.selectOne("com.tmount.db.sys.dao.SysAmountDicMapper.selectByPrimaryKey",proName);
		/*如果start_status不为0，证明有此程序在执行，本次操作直接退出*/
		if(sysAmountDic.getStartStatus()!=0)
		{
			System.out.println(proName+" ========================= 有其他进程正在运行，程序退出");
			return null;
		}
		/*如果没有进程在执行，则将运行标识置为1,开始程序运行*/
		setSysAmount(sysAmountDic,1);
		return sysAmountDic;
	}
	public int setSysAmount(SysAmountDic sysAmountDic,int flag)
	{
		/*修改SysAmountDic表*/
		SysAmountDic sysAmountDicUpd = sysAmountDic;
		sysAmountDicUpd.setStartStatus(flag);
		int updCode = sqlSessionTemplate.update(
        		"com.tmount.db.sys.dao.SysAmountDicMapper.updateByPrimaryKeySelective",sysAmountDicUpd);
		System.out.println(sysAmountDic.getAmountCode()+" ========================== updCode is "+updCode);
		return updCode;
	}
}
