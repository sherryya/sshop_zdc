package com.tmount.business.order.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.order.vo.OrderCondition;
import com.tmount.config.ReOrderOperDictStatic;
import com.tmount.config.SyDataTypeDicStatic;
import com.tmount.db.mark.dao.ReOrderMarkAddMapper;
import com.tmount.db.order.dao.ReArrivalTimeDicMapper;
import com.tmount.db.order.dao.ReBankAcceptMapper;
import com.tmount.db.order.dao.ReBankOrderAcceptMapper;
import com.tmount.db.order.dao.ReCommentLogMapper;
import com.tmount.db.order.dao.ReLogisticsDicMapper;
import com.tmount.db.order.dao.ReLogisticsLogMapper;
import com.tmount.db.order.dao.ReOrderDeliverAddHisMapper;
import com.tmount.db.order.dao.ReOrderDeliverAddMapper;
import com.tmount.db.order.dao.ReOrderDeliverMapper;
import com.tmount.db.order.dao.ReOrderInvoiceMapper;
import com.tmount.db.order.dao.ReOrderPayMapper;
import com.tmount.db.order.dao.ReOrderStateRelMapper;
import com.tmount.db.order.dao.RePayLogMapper;
import com.tmount.db.order.dao.RePayTypeMapper;
import com.tmount.db.order.dao.ReStateDicMapper;
import com.tmount.db.order.dao.ReUserOrderAddMapper;
import com.tmount.db.order.dao.ReUserOrderDetailMapper;
import com.tmount.db.order.dao.ReUserOrderLogMapper;
import com.tmount.db.order.dao.ReUserOrderMapper;
import com.tmount.db.order.dao.SyPayorgDictMapper;
import com.tmount.db.order.dto.ReArrivalTimeDic;
import com.tmount.db.order.dto.ReBankAccept;
import com.tmount.db.order.dto.ReBankAcceptKey;
import com.tmount.db.order.dto.ReBankOrderAccept;
import com.tmount.db.order.dto.ReCommentLogKey;
import com.tmount.db.order.dto.ReLogisticsDic;
import com.tmount.db.order.dto.ReLogisticsLog;
import com.tmount.db.order.dto.ReOrderDeliver;
import com.tmount.db.order.dto.ReOrderDeliverAdd;
import com.tmount.db.order.dto.ReOrderDeliverAddHis;
import com.tmount.db.order.dto.ReOrderInvoice;
import com.tmount.db.order.dto.ReOrderPay;
import com.tmount.db.order.dto.ReOrderStateRel;
import com.tmount.db.order.dto.RePayLog;
import com.tmount.db.order.dto.RePayType;
import com.tmount.db.order.dto.ReStateDic;
import com.tmount.db.order.dto.ReUserOrder;
import com.tmount.db.order.dto.ReUserOrderAdd;
import com.tmount.db.order.dto.ReUserOrderDetail;
import com.tmount.db.order.dto.ReUserOrderLog;
import com.tmount.db.order.dto.SyPayorgDict;
import com.tmount.db.order.vo.LogisticInfo;
import com.tmount.db.order.vo.OrderStateStat;
import com.tmount.db.order.vo.ReBankAcceptPayOrg;
import com.tmount.db.order.vo.ReUserOrderDetailInfo;
import com.tmount.db.product.dao.GdItemsExpRiceMapper;
import com.tmount.db.product.dao.GdItemsInfoMapper;
import com.tmount.db.product.dao.GdShopInfoMapper;
import com.tmount.db.product.dao.SyCityDictMapper;
import com.tmount.db.product.dao.SyNormalDicsMapper;
import com.tmount.db.product.dto.GdItemsExpRice;
import com.tmount.db.product.dto.GdItemsInfo;
import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.db.product.dto.SyCityDict;
import com.tmount.db.product.dto.SyNormalDics;
import com.tmount.db.product.dto.SyNormalDicsKey;
import com.tmount.db.pub.dao.DbSequenceMapper;
import com.tmount.db.pub.dao.DbTimeMapper;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.db.sys.dao.ReMegSendMapper;
import com.tmount.db.user.dao.UsBuyItemsMapper;
import com.tmount.db.user.dao.UsShopCartMapper;
import com.tmount.db.user.dao.UsUserCommentMapper;
import com.tmount.db.user.dao.UsUserInfoMapper;
import com.tmount.db.user.dto.UsShopCartKey;
import com.tmount.db.user.dto.UsUserComment;
import com.tmount.db.user.vo.UsUserCommentUser;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopParamException;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.yeepay.Configuration;
import com.yeepay.PaymentForOnlineService;

@Service
public class OrderService {
	@Autowired
	private ReUserOrderMapper reUserOrderMapper;

	@Autowired
	private UsUserInfoMapper usUserInfoMapper;
	
	@Autowired
	private ReUserOrderAddMapper reUserOrderAddMapper;
	
	@Autowired
	private ReUserOrderDetailMapper reUserOrderDetailMapper;

	@Autowired
	private ReOrderDeliverMapper reOrderDeliverMapper;
	
	@Autowired
	private ReUserOrderLogMapper reUserOrderLogMapper;
	
	@Autowired
	private ReStateDicMapper reStateDicMapper;
	
	@Autowired
	private GdShopInfoMapper gdShopInfoMapper;

	@Autowired
	private DbTimeMapper dbTimeMapper;

	@Autowired
	private GdItemsInfoMapper gdItemsInfoMapper;

	@Autowired
	private DbSequenceMapper dbSequenceMapper;
	
	@Autowired
	private ReCommentLogMapper reCommentLogMapper;
	
	@Autowired
	private UsUserCommentMapper usUserCommentMapper;
	
	@Autowired
	private UsShopCartMapper usShopCartMapper;

	@Autowired
	private ReOrderInvoiceMapper reOrderInvoiceMapper;
	
	@Autowired
	private ReOrderDeliverAddMapper reOrderDeliverAddMapper;
	
	@Autowired
	private ReOrderDeliverAddHisMapper reOrderDeliverAddHisMapper;
	
	@Autowired
	private ReArrivalTimeDicMapper reArrivalTimeDicMapper;
	
	@Autowired
	private ReLogisticsDicMapper reLogisticsDicMapper;	

	@Autowired
	private RePayLogMapper rePayLogMapper;
	
	@Autowired
	private ReOrderPayMapper reOrderPayMapper;
	
	@Autowired
	private ReLogisticsLogMapper reLogisticsLogMapper;
	
	@Autowired
	private ReOrderStateRelMapper reOrderStateRelMapper;
	
	@Autowired
	private RePayTypeMapper rePayTypeMapper;
	
	@Autowired
	private GdItemsExpRiceMapper gdItemsExpRiceMapper;
	
	@Autowired
	private SyNormalDicsMapper syNormalDicsMapper;
	
	@Autowired
	private ReBankAcceptMapper reBankAcceptMapper;
	
	@Autowired
	private ReBankOrderAcceptMapper reBankOrderAcceptMapper; 
	
	@Autowired
	private SyPayorgDictMapper syPayorgDictMapper;
	
	@Autowired
	private UsBuyItemsMapper usBuyItemsMapper;
	
	@Autowired
	private UsUserInfoMapper uUsUserInfoMapper;
	
	@Autowired
	private ReOrderMarkAddMapper reOrderMarkAddMapper;
	
	@Autowired
	private SyCityDictMapper syCityDictMapper;
	
	@Autowired
	private ReMegSendMapper reMegSendMapper;
	
	
	
	/**
	 * 得到商店信息。
	 * @param shopId
	 * @return
	 */
	public GdShopInfo getGdShopInfoByShopId(Long shopId){
		return gdShopInfoMapper.selectByPrimaryKey(shopId);
	}


	/**
	 * 获取待评论的订单信息
	 * @param userid
	 * @return
	 */
	public List<ReUserOrder> getReCommentLogListByUserId(Map<String, Object> paramIn){
		return reUserOrderMapper.selectUserOrderBySelective(paramIn);
	}


	/**
	 * 获取待评论的订单信息
	 * @param userid
	 * @return
	 */
	public int getReCommentLogCountByUserId(Map<String, Object> paramIn){
		return reUserOrderMapper.selectUserOrderBySelectiveCount(paramIn);
	}

	/**
	 * 获取用户订单列表
	 * @param userid
	 * @return
	 */
	public List<ReUserOrder> getReUserOrderListByUserId(Map<String, Object> paramIn){
		return reUserOrderMapper.selectBySelective(paramIn);
	}

	/**
	 * 获取用户订单列表
	 * @param userid
	 * @return
	 */
	public int getReUserOrderCountByUserId(Map<String, Object> paramIn){
		return reUserOrderMapper.selectBySelectiveCount(paramIn);
	}
	
	/**
	 * 用户订单状态统计
	 * @param userId
	 * @return
	 */
	public List<OrderStateStat> orderStateStat(Long userId) {
		return reUserOrderMapper.selectorderStateStat(userId);
	}
	
	/**
	 * 返回订单状态信息
	 * @param orderState
	 * @return
	 */
	public ReStateDic getReOrderStateByState(Integer orderState){
		return reStateDicMapper.selectByPrimaryKey(orderState);
	}
	
	/**
	 * 获取一个订单
	 * @param userid
	 * @return
	 */
	public ReUserOrder getReUserOrderByOrderNo(Long orderNo){
		return reUserOrderMapper.selectByPrimaryKey(orderNo);
	}

	/**
	 * 获取用户订单附加信息
	 * @param userid
	 * @return
	 */
	public ReUserOrderAdd getReUserOrderAddByOrderNo(Long orderNo){
		return reUserOrderAddMapper.selectByPrimaryKey(orderNo);
	}

	/**
	 * 订单配货信息表
	 * @param userid
	 * @return
	 */
	public ReOrderDeliver getReOrderDeliverByOrderNo(Long orderNo){
		return reOrderDeliverMapper.selectByPrimaryKey(orderNo);
	}

	/**
	 * 获取用户订单明细列表
	 * @param userid
	 * @return
	 */
	public List<ReUserOrderDetailInfo> getReUserOrderDetailListByOrderNo(Long orderNo){
		return reUserOrderDetailMapper.selectDetailByOrderNo(orderNo);
	}
	

	/**
	 * 发送订单明细
	 * @param reUserOrderDetailList 用户订单明细列表
	 * @return
	 * @throws ShopBusiException 
	 */
	public void insertUserOrderDetail(List<ReUserOrderDetail> reUserOrderDetailList, ReUserOrder reUserOrder) throws ShopBusiException{
		Iterator<ReUserOrderDetail> it = reUserOrderDetailList.iterator();
		ReUserOrderDetail reUserOrderDetail;
		UsShopCartKey usShopCartKey = new UsShopCartKey();
		DbTime dbTime = dbTimeMapper.selectDbTime();
		
		while(it.hasNext()) {			
			reUserOrderDetail = it.next();
			
			//锁住商品表
			GdItemsInfo gdItemsInfo = new GdItemsInfo();
			gdItemsInfo.setItemsId(reUserOrderDetail.getItemsId());
			gdItemsInfo.setUpdateTime(dbTime.getDbTime());
			gdItemsInfoMapper.updateByPrimaryKeySelective(gdItemsInfo);
			
			//读取商品的数量
			gdItemsInfo = gdItemsInfoMapper.selectByPrimaryKey(reUserOrderDetail.getItemsId());
			if (gdItemsInfo == null) {
				throw new ShopBusiException(ShopBusiErrorBundle.ORDER_NOT_EXISTS_ITEMS, new Object[]{reUserOrderDetail.getItemsId()});
			} else {
				Integer itemsCount = gdItemsInfo.getItemsCount();
				if(reUserOrderDetail.getAcount() > itemsCount) {
					throw new ShopBusiException(ShopBusiErrorBundle.ORDER_NOT_SUPPLY_ITEMS, new Object[]{gdItemsInfo.getItemsName()});
				} else {
					//扣减商品数量
					gdItemsInfo = new GdItemsInfo();
					gdItemsInfo.setItemsId(reUserOrderDetail.getItemsId());
					gdItemsInfo.setItemsCount(itemsCount - reUserOrderDetail.getAcount());
					gdItemsInfo.setUpdateTime(dbTime.getDbTime());
					gdItemsInfoMapper.updateByPrimaryKeySelective(gdItemsInfo);
				}
			}
			reUserOrderDetailMapper.insert(reUserOrderDetail);
			
			//删除购物车中的内容。
			usShopCartKey.setUserId(reUserOrder.getUserId());
			usShopCartKey.setShopId(reUserOrder.getShopId());
			usShopCartKey.setItemsId(reUserOrderDetail.getItemsId());
			usShopCartMapper.deleteByPrimaryKey(usShopCartKey);
		}
	}

	


	/**
	 * 更新订单明细状态
	 * @param reUserOrderDetailList 用户订单明细列表
	 * @return
	 */
	public void updateUserOrderDetailState(ReUserOrderDetail reUserOrderDetail){
		reUserOrderDetailMapper.updateStatusByOrderNo(reUserOrderDetail);
	}

	/**
	 * 用户订单确认收货
	 * @param requestParam
	 * @throws ShopBusiException
	 * @throws ShopParamException
	 */
	public void confirmReceipt(Long orderNo, String mark) throws ShopBusiException{
		String functionCode = "P50036";//待确认收货
		Long loginAccept = dbSequenceMapper.selectSeqByName(SyDataTypeDicStatic.LoginAcceptSeq).getSeqValue();
		DbTime dbTime = dbTimeMapper.selectDbTime();
		Date opTime = dbTime.getDbTime();
		ReUserOrder reUserOrder = reUserOrderMapper.selectByPrimaryKey(orderNo);
		if (reUserOrder == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ORDER, new Object[]{orderNo});
		}
		
		boolean payWayIdVerify = true;
		Integer orderCondition = new Integer(0);
		orderCondition = OrderCondition.generatorOrderCondition(reUserOrder.getPayWayId(), payWayIdVerify, false, false, true, false, true, false);
		
		
	}

	/**
	 * 银行缴费函数，内部调用银行缴费接口。
	 * @param bankAccept 银行缴费流水
	 * @param succeed 银行返回界面的值。Y(成功)或N(失败)）。
	 * @param coNo 银行返回界面的值。 商户号
	 * @param amount 银行返回界面的值。 实际支付金额
	 * @param date 银行返回界面的值。 交易日期
	 * @param msg 银行返回界面的值。 银行通知用户的支付结果消息。
	 *             信息的前38个字符格式为：4位分行号＋6位商户号＋8位银行接受交易的日期＋20位银行流水号；
	 *             可以利用交易日期＋银行流水号＋定单号对该定单进行结帐处理；
	 * @param merchantPara 银行返回界面的值。 商户自己的参数 
	 * @param signature 银行返回界面的值。 银行用自己的Private Key对通知命令的签名
	 * @param baMessage 银行返回界面的值。从银行返回的信息
	 * @param payType 付款方式ID
	 * @param mark 本次缴费备注
	 * @return
	 * @throws ShopBusiException
	 * @throws ShopParamException
	 */
	public Long userBankOrderPay(Long bankAccept, char succeed, String coNo, double amount,
			String date, String msg, String merchantPara, String signature, String baMessage,
			Integer payType, String mark)
					throws ShopBusiException, ShopParamException{
		Long loginAccept = userBankOrderPay(bankAccept, payType, mark);
		if (bankPayTypeUpdate(loginAccept, succeed, coNo, bankAccept, amount, date, msg,
				merchantPara, signature, baMessage) != 0) {
			throw new ShopBusiException(ShopBusiErrorBundle.ORDER_BANK_PAY_ERROR, new Object[]{bankAccept});
		}
		
		return loginAccept;
	}
	
	/**
	 * 使用银行流水号，对本地的订单进行缴费，不再对银行接口进行调用。
	 * @param bankAccept 银行缴费流水
	 * @param payType 付款方式ID
	 * @param mark 本次缴费备注
	 * @return
	 * @throws ShopBusiException
	 * @throws ShopParamException
	 */
	public Long userBankOrderPay(Long bankAccept, Integer payType, String mark)
			throws ShopBusiException, ShopParamException{
		List<ReBankOrderAccept> reBankOrderAcceptList = reBankOrderAcceptMapper.selectByBankAccept(bankAccept);
		ReUserOrder reUserOrder;
		Long loginAccept = dbSequenceMapper.selectSeqByName(SyDataTypeDicStatic.LoginAcceptSeq).getSeqValue();
		
		DbTime dbTime = dbTimeMapper.selectDbTime();
		Date opTime = dbTime.getDbTime();
		
		if (reBankOrderAcceptList != null) {
			if (reBankOrderAcceptList.size() > 0 ) {
				RePayLog rePayLog = new RePayLog();
				rePayLog.setPayId(loginAccept);
				rePayLog.setUserId(new Long(0));
				rePayLog.setPayType(payType);
				rePayLog.setPayMoney(0.0);
				rePayLog.setPayWayId(0);
				rePayLog.setPayEvkMoney(0.0);
				rePayLog.setPayDate(opTime);
				rePayLog.setPayOtherId(bankAccept.toString());
				rePayLog.setOrgId(0);

				for(ReBankOrderAccept reBankOrderAccept:reBankOrderAcceptList) {
					reUserOrder = reUserOrderMapper.selectByPrimaryKey(reBankOrderAccept.getOrderNo());
					insertOrderPay(reUserOrder.getOrderNo(), reUserOrder.getUserId(), reUserOrder.getPayWayId(),
							mark, payType, reUserOrder.getPrice(), 0.0, bankAccept.toString(), reBankOrderAccept.getOrgId(), loginAccept, opTime);

					rePayLog.setUserId(reUserOrder.getUserId());
					rePayLog.setPayMoney(rePayLog.getPayMoney() + reUserOrder.getPayMoney());
					rePayLog.setPayWayId(reUserOrder.getPayWayId());
					rePayLog.setPayEvkMoney(0.0);
					rePayLog.setOrgId(reBankOrderAccept.getOrgId());
				}
				
				rePayLogMapper.insert(rePayLog);
			}
		}
		
		return loginAccept;
	}

	/**
	 * 用户订单付款
	 * @param requestParam
	 * @throws ShopBusiException
	 * @throws ShopParamException
	 */
	public Long userOrderPay(Long orderNo, Long userId, Integer payWayId,
			String mark, Integer payType, Double payMoney, Double payEvkMoney,
			String payOtherId, Integer orgId, Long loginAccept, Date opTime) throws ShopBusiException, ShopParamException{
		Long payId = insertOrderPay(orderNo, userId, payWayId,
				mark, payType, payMoney, payEvkMoney, payOtherId, 0, loginAccept, opTime);

		RePayLog rePayLog = new RePayLog();
		rePayLog.setPayId(payId);
		rePayLog.setUserId(userId);
		rePayLog.setPayType(payType);
		rePayLog.setPayMoney(payMoney);
		rePayLog.setPayWayId(payWayId);
		rePayLog.setPayEvkMoney(payEvkMoney);
		rePayLog.setPayDate(opTime);
		rePayLog.setPayOtherId(payOtherId);
		rePayLog.setOrgId(orgId);
		rePayLogMapper.insert(rePayLog);
		
		return payId;
	}
	
	/**
	 * 得到20位的随机码
	 * @param buff
	 */
    public static void getRand20(StringBuffer buff) {
    	Random rand = new Random();
		Long longValue1 = new Long((long)(rand.nextDouble() * 10000000000L));
		Long longValue2 = new Long((long)(rand.nextDouble() * 10000000000L));
		buff.append(longValue1.toString());
		buff.append(longValue2.toString());
		
		while (true) {
			if (buff.length() < 20) {
				buff.append("0");
			} else {
				break;
			}
		}
    }

	/**
	 * 得到10位的随机码
	 * @param buff
	 */
    public static void getRand10(StringBuffer buff) {
    	Random rand = new Random();
		Long longValue1 = new Long((long)(rand.nextDouble() * 10000000000L));
		buff.append(longValue1.toString());
		
		while (true) {
			if (buff.length() < 10) {
				buff.append("0");
			} else {
				break;
			}
		}
    }

    /**
	 * 用户订单付款，内部调用userOrderPay函数，多生成了re_meg_send表。
	 * @param orderNo
	 * @param userId
	 * @param payWayId
	 * @param mark
	 * @param payType
	 * @param payMoney
	 * @param payEvkMoney
	 * @param payOtherId
	 * @param orgId
	 * @param loginAccept
	 * @param opTime
	 * @return
	 * @throws ShopBusiException
	 * @throws ShopParamException
	 */
	
	
	/**
	 * 用户订单付款
	 * @param requestParam
	 * @throws ShopBusiException
	 * @throws ShopParamException
	 */
	public Long insertOrderPay(Long orderNo, Long userId, Integer payWayId,
			String mark, Integer payType, Double payMoney, Double payEvkMoney,
			String payOtherId, Integer orgId, Long loginAccept, Date opTime) throws ShopBusiException, ShopParamException{
		if (loginAccept == null) {
			loginAccept = dbSequenceMapper.selectSeqByName(SyDataTypeDicStatic.LoginAcceptSeq).getSeqValue();
		}
		if (opTime == null) {
			DbTime dbTime = dbTimeMapper.selectDbTime();
			opTime = dbTime.getDbTime();
		}

		ReUserOrder newReUserOrder = new ReUserOrder();	//加锁本订单。
		newReUserOrder.setOrderNo(orderNo);
		newReUserOrder.setUpdateTime(opTime);
		reUserOrderMapper.updateByPrimaryKeySelective(newReUserOrder);

		ReUserOrder reUserOrder = reUserOrderMapper.selectByPrimaryKey(orderNo);
		if (reUserOrder == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ORDER, new Object[]{orderNo});
		}
		
		boolean isCancelOrder=false;
		boolean cancelOrderVerify=true;
		String functionCode = "P50032";//待付款
		Integer orderCondition = new Integer(0);
		orderCondition = OrderCondition.generatorOrderCondition(0, false, isCancelOrder, cancelOrderVerify, true, false, true, false);
		
		newReUserOrder = new ReUserOrder();
		newReUserOrder.setOrderNo(orderNo);
		newReUserOrder.setUpdateTime(opTime);
		newReUserOrder.setPayMoney(payMoney);	//支付金额。
		newReUserOrder.setPayWayId(payWayId);
		newReUserOrder.setPayStatus("Y");	//付费状态：已付款。
		reUserOrderMapper.updateByPrimaryKeySelective(newReUserOrder);

		ReOrderPay reOrderPay = new ReOrderPay();
		reOrderPay.setPayId(loginAccept);
		reOrderPay.setOrderNo(orderNo);
		reOrderPay.setPayType(payType);
		reOrderPay.setPayMoney(payMoney);
		reOrderPay.setPayEvkMoney(payEvkMoney);
		reOrderPay.setPayDate(opTime);
		reOrderPayMapper.insert(reOrderPay);
		return loginAccept;
	}

	/**
	 * 用户订单退货
	 * @param requestParam
	 * @throws ShopBusiException
	 * @throws ShopParamException
	 */
	public void userOrderBack(RequestParam requestParam) throws ShopBusiException, ShopParamException{
		Long orderNo = ParamData.getLong(requestParam.getBodyNode(), "order_no");
		String mark = ParamData.getString(requestParam.getBodyNode(), "mark");
		String functionCode = "P50037";//退货
		Long loginAccept = dbSequenceMapper.selectSeqByName(SyDataTypeDicStatic.LoginAcceptSeq).getSeqValue();
		DbTime dbTime = dbTimeMapper.selectDbTime();
		Date opTime = dbTime.getDbTime();
		ReUserOrder reUserOrder = reUserOrderMapper.selectByPrimaryKey(orderNo);
		if (reUserOrder == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ORDER, new Object[]{orderNo});
		}

		boolean payWayIdVerify =true;
		Integer orderCondition = new Integer(0);
		orderCondition = OrderCondition.generatorOrderCondition(reUserOrder.getPayWayId(), payWayIdVerify, false, false, true, false, true, false);
		

		//得到订单明细
		List<ReUserOrderDetail> reUserOrderDetailList = reUserOrderDetailMapper.selectByOrderNo(orderNo);
		GdItemsInfo gdItemsInfo;
		for(ReUserOrderDetail reUserOrderDetailIter: reUserOrderDetailList) {
			gdItemsInfo = gdItemsInfoMapper.selectByPrimaryKey(reUserOrderDetailIter.getItemsId());
			if (gdItemsInfo == null) {
				//无此商品，不做处理。
			} else {
				Integer itemsCount = gdItemsInfo.getItemsCount();
				//回退商品数量
				gdItemsInfo = new GdItemsInfo();
				gdItemsInfo.setItemsId(reUserOrderDetailIter.getItemsId());
				gdItemsInfo.setItemsCount(itemsCount + reUserOrderDetailIter.getAcount());
				gdItemsInfo.setUpdateTime(opTime);
				gdItemsInfoMapper.updateByPrimaryKeySelective(gdItemsInfo);
			}
		}
	}

	/**
	 * 用户申请退货
	 * @param requestParam
	 * @throws ShopBusiException
	 * @throws ShopParamException
	 */
	public void userOrderRequestBack(RequestParam requestParam) throws ShopBusiException, ShopParamException{
		Long orderNo = ParamData.getLong(requestParam.getBodyNode(), "order_no");
		String mark = ParamData.getString(requestParam.getBodyNode(), "mark");
		String functionCode = "P50034";//申请退货
		Long loginAccept = dbSequenceMapper.selectSeqByName(SyDataTypeDicStatic.LoginAcceptSeq).getSeqValue();
		DbTime dbTime = dbTimeMapper.selectDbTime();
		Date opTime = dbTime.getDbTime();
		ReUserOrder reUserOrder = reUserOrderMapper.selectByPrimaryKey(orderNo);
		if (reUserOrder == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ORDER, new Object[]{orderNo});
		}

		boolean payWayIdVerify =true;
		Integer orderCondition = new Integer(0);
		orderCondition = OrderCondition.generatorOrderCondition(reUserOrder.getPayWayId(), payWayIdVerify, false, false, true, false, true, false);
		
	}

	
	/**
	 * 取消一个订单。
	 * @param orderNo
	 * @param mark
	 * @param opTime
	 * @param loginAccept
	 * @throws ShopBusiException 
	 */
	public void removeUserOrder(Long orderNo, String mark, Date opTime, Long loginAccept) throws ShopBusiException {
		ReUserOrder oldReUserOrder = reUserOrderMapper.selectByPrimaryKey(orderNo);
		if (oldReUserOrder ==  null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ORDER, new Object[]{orderNo});
		}
		
		boolean isCancelOrder = true;
		boolean cancelOrderVerifyBit = true;
		Integer orderCondition = new Integer(0);
		orderCondition = OrderCondition.generatorOrderCondition(0, false, isCancelOrder, cancelOrderVerifyBit, true, false, true, false);

		Map<String, Object> paramIn = new HashMap<String, Object>();
		paramIn.put("orderState", oldReUserOrder.getState());
		paramIn.put("orderCondition", orderCondition);
		paramIn.put("nextOrderState", new Integer(70));
		List<ReOrderStateRel> reOrderStateRelList = reOrderStateRelMapper.selectStateRelList(paramIn);
		if (reOrderStateRelList == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.ORDER_STATE_DELETE, new Object[]{orderNo});
		} else if (reOrderStateRelList.size() > 1) {
			throw new ShopBusiException(ShopBusiErrorBundle.ORDER_STATE_DELETE, new Object[]{orderNo});
		} else if (reOrderStateRelList.size() == 0) {
			throw new ShopBusiException(ShopBusiErrorBundle.ORDER_STATE_DELETE, new Object[]{orderNo});
		}

		//更新订单状态
		ReUserOrder reUserOrder = new ReUserOrder();
		reUserOrder.setOrderNo(orderNo);
		reUserOrder.setOrderValid("N");
		reUserOrder.setOrderEnd("Y");
		reUserOrder.setState(70);//订单取消状态
		reUserOrder.setUpdateTime(opTime);
		reUserOrder.setEndTime(opTime);	
		reUserOrderMapper.updateByPrimaryKeySelective(reUserOrder);

		//得到订单明细
		GdItemsInfo gdItemsInfo;
		List<ReUserOrderDetail> reUserOrderDetailList = reUserOrderDetailMapper.selectByOrderNo(orderNo);
		for(ReUserOrderDetail reUserOrderDetailIter: reUserOrderDetailList) {
			gdItemsInfo = gdItemsInfoMapper.selectByPrimaryKey(reUserOrderDetailIter.getItemsId());
			if (gdItemsInfo == null) {
				//无此商品，不做处理。
			} else {
				Integer itemsCount = gdItemsInfo.getItemsCount();
				//回退商品数量
				gdItemsInfo = new GdItemsInfo();
				gdItemsInfo.setItemsId(reUserOrderDetailIter.getItemsId());
				gdItemsInfo.setItemsCount(itemsCount + reUserOrderDetailIter.getAcount());
				gdItemsInfo.setUpdateTime(opTime);
				gdItemsInfoMapper.updateByPrimaryKeySelective(gdItemsInfo);
			}
		}

		//订单操作流水表
		ReUserOrderLog reUserOrderLog = new ReUserOrderLog();
		reUserOrderLog.setOrderNo(orderNo);
		reUserOrderLog.setLoginAccept(loginAccept);
		reUserOrderLog.setDealMark(mark);
		reUserOrderLog.setDealName("订单取消");//处理名称
		reUserOrderLog.setDealNo(new Integer(0));
		reUserOrderLog.setDealTime(opTime);
		reUserOrderLog.setDealType(1);	//0 系统工号，1用户id
		reUserOrderLog.setNewState(reUserOrder.getState());
		reUserOrderLog.setOldState(oldReUserOrder.getState());	//老订单状态
		reUserOrderLog.setOperId(ReOrderOperDictStatic.OrderDelete);
		reUserOrderLog.setSysMark("取消订单");

		//订单操作流水表
		reUserOrderLogMapper.insert(reUserOrderLog);
		
		ReUserOrderDetail reUserOrderDetail = new ReUserOrderDetail();
		reUserOrderDetail.setOrderNo(orderNo);
		reUserOrderDetail.setOpTime(opTime);
		reUserOrderDetail.setState(2);	//1正常，2退货，3换货
		reUserOrderDetailMapper.updateStatusByOrderNo(reUserOrderDetail);
	}
	
	/**
	 * 取消订单
	 * @param reUserOrder 用户订单
	 * @return
	 * @throws ShopParamException 
	 * @throws ShopBusiException 
	 */
	public void removeUserOrderRequest(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopParamException, ShopBusiException{
		DbTime dbTime = dbTimeMapper.selectDbTime();
		Date opTime = dbTime.getDbTime();
		Long loginAccept = dbSequenceMapper.selectSeqByName(SyDataTypeDicStatic.LoginAcceptSeq).getSeqValue();
		Long orderNo;
		String mark;

		JsonNode bodyNode = requestParam.getBodyNode();
		JsonNode orderJson = bodyNode;
		;
		if (bodyNode.isArray()) {	//多个订单
			Iterator<JsonNode> detailIt = bodyNode.elements();
			while(detailIt.hasNext()) {
				orderJson = detailIt.next();
				orderNo = new Long(ParamData.getLong(orderJson, "order_no"));
				mark = orderJson.get("mark").textValue();
				removeUserOrder(orderNo, mark, opTime, loginAccept);
			}
		} else {	//单个订单
			orderNo = new Long(ParamData.getLong(orderJson, "order_no"));
			mark = orderJson.get("mark").textValue();
			removeUserOrder(orderNo, mark, opTime, loginAccept);
		}
	}
	
	/**
	 * 记录用户订单评论。
	 */
	public void insertUserOrderItemsComment(RequestParam requestParam) throws ShopBusiException, ShopParamException{
		Iterator<JsonNode> detailIt = requestParam.getBodyNode().get("item_list").elements();
		DbTime dbTime = dbTimeMapper.selectDbTime();
		Date opTime = dbTime.getDbTime();
		JsonNode commentJson;
		UsUserComment usUserComment = null;
		Long commentId;
		ReUserOrder reUserOrder;
		GdItemsInfo gdItemsInfo;
		ReCommentLogKey reCommentLogKey = new ReCommentLogKey();
		while(detailIt.hasNext()) {
			commentJson = detailIt.next();
			usUserComment = new UsUserCommentUser();
			
			commentId = dbSequenceMapper.selectSeqByName(SyDataTypeDicStatic.CommentId).getSeqValue();
			usUserComment.setCommentId(commentId);
			usUserComment.setUserId(ParamData.getLong(commentJson, "user_id"));
			usUserComment.setShopId(ParamData.getLong(commentJson, "shop_id"));
			usUserComment.setItemsId(ParamData.getLong(commentJson, "items_id"));

			gdItemsInfo = gdItemsInfoMapper.selectByPrimaryKey(usUserComment.getItemsId());
			if (gdItemsInfo != null) {
				usUserComment.setItemsName(gdItemsInfo.getItemsName());
			} else {
				usUserComment.setItemsName("空");
			}
			usUserComment.setCommentLevel(ParamData.getInt(commentJson, "comment_level"));
			usUserComment.setItemsDesc(ParamData.getInt(commentJson, "items_desc"));
			usUserComment.setDeliveSpeed(ParamData.getInt(commentJson, "delive_speed"));
			usUserComment.setServerAttitude(ParamData.getInt(commentJson, "server_attitude"));
			usUserComment.setGoodsSpeed(ParamData.getInt(commentJson, "goods_speed"));
			if (ParamData.getInt(commentJson, "average_value", -1) == -1) {
				usUserComment.setAverageValue((usUserComment.getItemsDesc() + usUserComment.getDeliveSpeed() + usUserComment.getServerAttitude() + usUserComment.getGoodsSpeed())/ 4);
			} else {
				usUserComment.setAverageValue(ParamData.getInt(commentJson, "average_value"));
			}
			usUserComment.setCommentContent(ParamData.getString(commentJson, "comment_content"));
			usUserComment.setCommentTime(opTime);
			usUserComment.setOrderNo(ParamData.getLong(commentJson, "order_no"));
			reUserOrder = reUserOrderMapper.selectByPrimaryKey(usUserComment.getOrderNo());
			if (reUserOrder != null) {
				usUserComment.setEndTime(reUserOrder.getEndTime());
			} else {
				usUserComment.setEndTime(opTime);
			}
			
			reCommentLogKey.setItemsId(usUserComment.getItemsId());
			reCommentLogKey.setOrderNo(usUserComment.getOrderNo());
			reCommentLogMapper.deleteByPrimaryKey(reCommentLogKey);
			usUserCommentMapper.insert(usUserComment);
		}
	}
	
	public ReOrderDeliverAddHis getReOrderDeliverAddByTxLogisticID(String txLogisticID){
		return reOrderDeliverAddMapper.selectByTxLogisticID(txLogisticID);
	}
	
	public int removeReOrderDeliverAdd(ReOrderDeliverAdd record){
		return reOrderDeliverAddMapper.deleteByPrimaryKey(record);
	}
	
	public int insertReOrderDeliverAddHis(ReOrderDeliverAddHis reOrderDeliverAddHis){
		return reOrderDeliverAddHisMapper.insert(reOrderDeliverAddHis);
	}
	
	public int updateReOrderDeliverStateByOrderNo(ReOrderDeliver reOrderDeliver){
		return reOrderDeliverMapper.updateByPrimaryKeySelective(reOrderDeliver);
	}
	
	public  Long getLogisticsSeq(String keyName){
		return dbSequenceMapper.selectSeqByName(keyName).getSeqValue();
	}
	
	public int insertReLogisticsLog(ReLogisticsLog reLogisticsLog){
		return reLogisticsLogMapper.insertSelective(reLogisticsLog);
	}
	
	public LogisticInfo getLogisticsInfo(Long orderNo){
		return reOrderDeliverMapper.selectLogisticInfo(orderNo);
	}
	
	/**
	 * 获取用户订购商品详情
	 * @param requestParam
	 * @param responseBodyJson
	 * @throws ShopParamException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public void userOrderDetailGet(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopParamException, JsonGenerationException, IOException {
		Long orderNo = new Long(ParamData.getLong(requestParam.getBodyNode(), "order_no"));

		responseBodyJson.writeFieldName("order_node");	//订单信息
		responseBodyJson.writeStartObject();
		ReUserOrder reUserOrder = getReUserOrderByOrderNo(orderNo);
		if (reUserOrder != null) {
			responseBodyJson.writeNumberField("order_no", reUserOrder.getOrderNo());
			responseBodyJson.writeNumberField("shop_id", reUserOrder.getShopId());
			responseBodyJson.writeNumberField("acount", reUserOrder.getAcount());
			GdShopInfo gdShopInfo = getGdShopInfoByShopId(reUserOrder.getShopId());
			if (gdShopInfo == null) {
				responseBodyJson.writeStringField("shop_name", "未知");
			} else {
				responseBodyJson.writeStringField("shop_name", gdShopInfo.getShopName());
			}
			responseBodyJson.writeStringField("create_date", requestParam.dateToString(reUserOrder.getCreateDate()));
			responseBodyJson.writeNumberField("order_money", reUserOrder.getOrderMoney());
			responseBodyJson.writeNumberField("pay_money", reUserOrder.getPayMoney());
			responseBodyJson.writeNumberField("freight_fee", reUserOrder.getFreightFee());
			responseBodyJson.writeNumberField("state", reUserOrder.getState());
			ReStateDic reStateDic = getReOrderStateByState(reUserOrder.getState());
			if (reStateDic == null) {
				responseBodyJson.writeStringField("user_state_name", "未知");
			} else {
				responseBodyJson.writeStringField("user_state_name", reStateDic.getUserStateName());
			}
			
			responseBodyJson.writeStringField("order_valid", reUserOrder.getOrderValid());
			responseBodyJson.writeStringField("order_end", reUserOrder.getOrderEnd());
			responseBodyJson.writeStringField("fee_date", requestParam.dateToString(reUserOrder.getFeeDate()));
			responseBodyJson.writeNumberField("price", reUserOrder.getPrice());
			ReUserOrderAdd reUserOrderAdd = getReUserOrderAddByOrderNo(reUserOrder.getOrderNo());
			if (reUserOrderAdd != null) {
				responseBodyJson.writeStringField("mark", reUserOrderAdd.getMark());
			}
		}
		responseBodyJson.writeEndObject();
		
		responseBodyJson.writeFieldName("deliver_node");	//邮寄信息
		responseBodyJson.writeStartObject();
		ReOrderDeliver reOrderDeliver = getReOrderDeliverByOrderNo(reUserOrder.getOrderNo());
		if (reOrderDeliver != null) {
			responseBodyJson.writeNumberField("province_code", reOrderDeliver.getProvinceCode());
			SyCityDict syCityDict = syCityDictMapper.selectByPrimaryKey(reOrderDeliver.getProvinceCode());
			if (syCityDict != null) {
				responseBodyJson.writeStringField("province_name", syCityDict.getCityName());
			} else {
				responseBodyJson.writeStringField("province_name", "");
			}
			responseBodyJson.writeNumberField("city_code", reOrderDeliver.getCityCode());
			syCityDict = syCityDictMapper.selectByPrimaryKey(reOrderDeliver.getCityCode());
			if (syCityDict != null) {
				responseBodyJson.writeStringField("city_name", syCityDict.getCityName());
			} else {
				responseBodyJson.writeStringField("city_name", "");
			}
			responseBodyJson.writeNumberField("area_code", reOrderDeliver.getAreaCode());
			syCityDict = syCityDictMapper.selectByPrimaryKey(reOrderDeliver.getAreaCode());
			if (syCityDict != null) {
				responseBodyJson.writeStringField("area_name", syCityDict.getCityName());
			} else {
				responseBodyJson.writeStringField("area_name", "");
			}
			responseBodyJson.writeNumberField("arrival_time", reOrderDeliver.getArrivalTime());
			ReArrivalTimeDic reArrivalTimeDic = reArrivalTimeDicMapper.selectByPrimaryKey(reOrderDeliver.getArrivalTime());
			if (reArrivalTimeDic != null) {
				responseBodyJson.writeStringField("arrival_name", reArrivalTimeDic.getArrivalName());
			} else {
				responseBodyJson.writeStringField("arrival_name", "其他时间段");
			}
			responseBodyJson.writeNumberField("logistics_id", reOrderDeliver.getLogisticsId()==null?0:reOrderDeliver.getLogisticsId());
			responseBodyJson.writeStringField("logistics_no", reOrderDeliver.getLogisticsNo()==null?"":reOrderDeliver.getLogisticsNo());
			ReLogisticsDic reLogisticsDic = reLogisticsDicMapper.selectByPrimaryKey(reOrderDeliver.getLogisticsId());
			if (reLogisticsDic != null) {
				responseBodyJson.writeStringField("logistics_name", reLogisticsDic.getLogisticsName());
			} else {
				responseBodyJson.writeStringField("logistics_name", "其他物流公司");
			}
			responseBodyJson.writeStringField("user_name", reOrderDeliver.getUserName());
			responseBodyJson.writeStringField("mobile", reOrderDeliver.getMobile());
			responseBodyJson.writeStringField("contact_phone", reOrderDeliver.getContactPhone());
			responseBodyJson.writeStringField("address", reOrderDeliver.getAddress());
			responseBodyJson.writeStringField("post_code", reOrderDeliver.getPostCode());
			responseBodyJson.writeStringField("create_time", requestParam.dateToString(reOrderDeliver.getCreateTime()));
			responseBodyJson.writeStringField("mark", reOrderDeliver.getMark());
			responseBodyJson.writeStringField("tx_logistics_id", reOrderDeliver.getTxLogisticsId());
			responseBodyJson.writeNumberField("state_code", reOrderDeliver.getStateCode());
			responseBodyJson.writeStringField("remark", reOrderDeliver.getRemark());
			responseBodyJson.writeNumberField("logistics_type", reOrderDeliver.getLogisticsType());
			responseBodyJson.writeNumberField("order_id", reOrderDeliver.getOrderId());
			responseBodyJson.writeStringField("send_online", reOrderDeliver.getSendOnline()==null?"":reOrderDeliver.getSendOnline());
		}
		responseBodyJson.writeEndObject();
		
		List<ReOrderDeliverAdd> reOrderDeliverAddList = reOrderDeliverAddMapper.selectByPrimaryKey(reUserOrder.getOrderNo());
		responseBodyJson.writeArrayFieldStart("deliver_add_node");	//订单物流发送增量
		if (reOrderDeliverAddList.size() > 0) {
			ReOrderDeliverAdd reOrderDeliverAdd;
			Iterator<ReOrderDeliverAdd> it = reOrderDeliverAddList.iterator();
			while (it.hasNext()) {
				reOrderDeliverAdd = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("logistics_id", reOrderDeliverAdd.getLogisticsId());
				responseBodyJson.writeNumberField("shop_id", reOrderDeliverAdd.getShopId()==null?0:reOrderDeliverAdd.getShopId());
				responseBodyJson.writeNumberField("state_type", reOrderDeliverAdd.getStateType()==null?0:reOrderDeliverAdd.getStateType());
				responseBodyJson.writeNumberField("state_code", reOrderDeliverAdd.getStateCode()==null?0:reOrderDeliverAdd.getStateCode());
				responseBodyJson.writeStringField("re_state_code", reOrderDeliverAdd.getReStateCode()==null?"":reOrderDeliverAdd.getReStateCode());
				responseBodyJson.writeStringField("re_state_desc", reOrderDeliverAdd.getReStateDesc()==null?"":reOrderDeliverAdd.getReStateDesc());
				responseBodyJson.writeStringField("create_time", reOrderDeliverAdd.getCreateTime()==null?"":requestParam.dateToString(reOrderDeliverAdd.getCreateTime()));
				responseBodyJson.writeNumberField("staff_id", reOrderDeliverAdd.getStaffId()==null?0:reOrderDeliverAdd.getStaffId());
				responseBodyJson.writeStringField("send_online", reOrderDeliverAdd.getSendOnline()==null?"":reOrderDeliverAdd.getSendOnline());
				responseBodyJson.writeNumberField("send_command", reOrderDeliverAdd.getSendCommand()==null?0:reOrderDeliverAdd.getSendCommand());
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();

		List<ReOrderPay> reOrderPayList = reOrderPayMapper.selectByOrderNo(reUserOrder.getOrderNo());
		responseBodyJson.writeArrayFieldStart("pay_node");	//订单支付信息
		if (reOrderPayList.size() > 0) {
			ReOrderPay reOrderPay;
			Iterator<ReOrderPay> it = reOrderPayList.iterator();
			while (it.hasNext()) {
				reOrderPay = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("pay_id", reOrderPay.getPayId());
				responseBodyJson.writeNumberField("pay_type", reOrderPay.getPayType());
				responseBodyJson.writeNumberField("pay_money", reOrderPay.getPayMoney());
				responseBodyJson.writeNumberField("pay_evk_money", reOrderPay.getPayEvkMoney());
				responseBodyJson.writeStringField("pay_date", requestParam.dateToString(reOrderPay.getPayDate()));
				
				RePayType rePayType = rePayTypeMapper.selectByPrimaryKey(reOrderPay.getPayType());
				if (rePayType != null) {
					responseBodyJson.writeStringField("pay_name", rePayType.getPayName());
				} else {
					responseBodyJson.writeStringField("pay_name", "其他支付方式");
				}
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();

		responseBodyJson.writeFieldName("invoice_node");	//订单发票
		responseBodyJson.writeStartObject();
		ReOrderInvoice reOrderInvoice = reOrderInvoiceMapper.selectByPrimaryKey(reUserOrder.getOrderNo());
		if (reOrderInvoice != null) {
			responseBodyJson.writeStringField("invoice_info", reOrderInvoice.getInvoiceInfo());
			responseBodyJson.writeStringField("invoice_code", reOrderInvoice.getInvoiceCode());
			responseBodyJson.writeStringField("invoice_title", reOrderInvoice.getInvoiceTitle());
			responseBodyJson.writeStringField("invoice_content", reOrderInvoice.getInvoiceContent());
			responseBodyJson.writeStringField("identification", reOrderInvoice.getIdentification());
			responseBodyJson.writeStringField("mark", reOrderInvoice.getMark());
			responseBodyJson.writeNumberField("user_id", reOrderInvoice.getUserId());
		}
		responseBodyJson.writeEndObject();

		List<ReUserOrderDetailInfo> gdGdItemsItemRelList = reUserOrderDetailMapper.selectDetailByOrderNo(orderNo);
		responseBodyJson.writeArrayFieldStart("item_list");	//订单明细
		if (gdGdItemsItemRelList.size() > 0) {
			ReUserOrderDetailInfo reUserOrderDetailInfo;
			Iterator<ReUserOrderDetailInfo> it = gdGdItemsItemRelList.iterator();
			while (it.hasNext()) {
				reUserOrderDetailInfo = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("items_id", reUserOrderDetailInfo.getItemsId());
				responseBodyJson.writeNumberField("acount", reUserOrderDetailInfo.getAcount());
				responseBodyJson.writeNumberField("shop_id", reUserOrderDetailInfo.getShopId());
				responseBodyJson.writeNumberField("show_type", reUserOrderDetailInfo.getShowType());
				responseBodyJson.writeNumberField("data_type", reUserOrderDetailInfo.getDataType());
				responseBodyJson.writeStringField("has_child", reUserOrderDetailInfo.getHasChild());
				responseBodyJson.writeStringField("items_name", reUserOrderDetailInfo.getItemsName());
				responseBodyJson.writeStringField("name_spr", reUserOrderDetailInfo.getNameSpr());
				responseBodyJson.writeStringField("items_intro", reUserOrderDetailInfo.getItemsIntro());
				responseBodyJson.writeNumberField("small_pic", reUserOrderDetailInfo.getSmallPic());
				responseBodyJson.writeStringField("http_url", reUserOrderDetailInfo.getHttpUrl());
				responseBodyJson.writeStringField("web_url", reUserOrderDetailInfo.getWebUrl());
				responseBodyJson.writeNumberField("price", reUserOrderDetailInfo.getPrice());
				responseBodyJson.writeNumberField("all_price", reUserOrderDetailInfo.getAllPrice());
				responseBodyJson.writeStringField("aunit", reUserOrderDetailInfo.getAunit());
				responseBodyJson.writeNumberField("state", reUserOrderDetailInfo.getState());
				responseBodyJson.writeNumberField("discuss_count", reUserOrderDetailInfo.getDiscussCount());
				responseBodyJson.writeNumberField("comment_value", reUserOrderDetailInfo.getCommentValue());
				
				GdItemsExpRice gdItemsExpRice = gdItemsExpRiceMapper.selectByPrimaryKey(reUserOrderDetailInfo.getItemsId());
				if (gdItemsExpRice != null) {
					responseBodyJson.writeStringField("brand", gdItemsExpRice.getBrand());
					responseBodyJson.writeNumberField("city_code", gdItemsExpRice.getCityCode());
					responseBodyJson.writeNumberField("stand_id", gdItemsExpRice.getStandId());
					responseBodyJson.writeNumberField("pack_id", gdItemsExpRice.getPackId());
					responseBodyJson.writeNumberField("quality_id", gdItemsExpRice.getQualityId());
					responseBodyJson.writeStringField("guarant_date", gdItemsExpRice.getGuarantDate());
					responseBodyJson.writeNumberField("texture_id", gdItemsExpRice.getTextureId());
					responseBodyJson.writeNumberField("province_code", gdItemsExpRice.getProvinceCode());
					
					SyNormalDicsKey syNormalDicsKey = new SyNormalDicsKey();
					syNormalDicsKey.setTableName("gd_items_exp_rice");
					syNormalDicsKey.setColumnName("stand_id");
					syNormalDicsKey.setDicValue(gdItemsExpRice.getStandId());
					SyNormalDics syNormalDics = syNormalDicsMapper.selectByPrimaryKey(syNormalDicsKey);
					if (syNormalDics != null) {
						responseBodyJson.writeStringField("brand_id_name", syNormalDics.getDicName());
					} else {
						responseBodyJson.writeStringField("brand_id_name", "未知");
					}

					syNormalDicsKey.setColumnName("pack_id");
					syNormalDicsKey.setDicValue(gdItemsExpRice.getPackId());
					syNormalDics = syNormalDicsMapper.selectByPrimaryKey(syNormalDicsKey);
					if (syNormalDics != null) {
						responseBodyJson.writeStringField("pack_id_name", syNormalDics.getDicName());
					} else {
						responseBodyJson.writeStringField("pack_id_name", "未知");
					}

					syNormalDicsKey.setColumnName("quality_id");
					syNormalDicsKey.setDicValue(gdItemsExpRice.getQualityId());
					syNormalDics = syNormalDicsMapper.selectByPrimaryKey(syNormalDicsKey);
					if (syNormalDics != null) {
						responseBodyJson.writeStringField("quality_id_name", syNormalDics.getDicName());
					} else {
						responseBodyJson.writeStringField("quality_id_name", "未知");
					}

					syNormalDicsKey.setColumnName("texture_id");
					syNormalDicsKey.setDicValue(gdItemsExpRice.getTextureId());
					syNormalDics = syNormalDicsMapper.selectByPrimaryKey(syNormalDicsKey);
					if (syNormalDics != null) {
						responseBodyJson.writeStringField("texture_id_name", syNormalDics.getDicName());
					} else {
						responseBodyJson.writeStringField("texture_id_name", "未知");
					}
				} else {
					responseBodyJson.writeStringField("brand", "");
					responseBodyJson.writeNumberField("city_code", 0);
					responseBodyJson.writeNumberField("stand_id", 0);
					responseBodyJson.writeNumberField("pack_id", 0);
					responseBodyJson.writeNumberField("quality_id", 0);
					responseBodyJson.writeStringField("guarant_date", "");
					responseBodyJson.writeNumberField("texture_id", 0);
					responseBodyJson.writeNumberField("province_code", 0);
					
					responseBodyJson.writeStringField("brand_id_name", "未知");
					responseBodyJson.writeStringField("pack_id_name", "未知");
					responseBodyJson.writeStringField("quality_id_name", "未知");
					responseBodyJson.writeStringField("texture_id_name", "未知");
				}
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
	}
	/**
	 * 内部函数
	 * 
	 */
	private String formatString(String text){ 
		if(text == null) {
			return ""; 
		}
		return text;
	}
	/**
	 *	支付确认接口函数
	 *
	 */
	public int bankPayTypeUpdate(long payId,char succeed,String coNo,long bankAccept,double amount,String date,String msg,String merchantPara,String signature,String baMessage){
		
		
		System.out.println("payId["+payId+"]");
		System.out.println("succeed["+succeed+"]");
		System.out.println("coNo["+coNo+"]");
		System.out.println("bankAccept["+bankAccept+"]");
		System.out.println("amount["+amount+"]");
		System.out.println("date["+date+"]");
		System.out.println("msg["+msg+"]");
		System.out.println("merchantPara["+merchantPara+"]");
		System.out.println("signature["+signature+"]");
		System.out.println("baMessage["+baMessage+"]");
		
		ReBankAcceptPayOrg reBankAcceptPayOrg = reBankAcceptMapper.selectBankPayOrgByAccept(bankAccept);
		String orgCode = reBankAcceptPayOrg.getOrgCode();
		if("CMB".equals(orgCode)){
			try {
				 String path=this.getClass().getResource("/").getPath();
				 System.out.println(path.substring(1, path.indexOf("WEB-INF/classes")));  
				 path = path+"com/tmount/util/key/public.key";
				 path = path.replace("%20", " "); 
				 System.out.println(path);  
				 
				cmb.netpayment.Security pay = new cmb.netpayment.Security(path);
				byte[] baSig = baMessage.getBytes("GB2312");
				boolean bRet = pay.checkInfoFromBank(baSig);
				System.out.println("checkInfoFromBank: "+bRet);
				if(!bRet){
					System.out.println("数字签名校验失败");
					return -1001;
				}
				
				if(succeed=='Y'){
					if(amount<reBankAcceptPayOrg.getPrice()){
						System.out.println("实际支付金额小于应付金额");
						return -1002;
					}
					ReBankAcceptKey reBankAcceptKey = new ReBankAcceptKey();
					reBankAcceptKey.setBankAccept(bankAccept);
					reBankAcceptKey.setOrgId(reBankAcceptPayOrg.getOrgId());
					ReBankAccept reBankAccept = reBankAcceptMapper.selectByPrimaryKey(reBankAcceptKey);
					
					reBankAccept.setPayType("Y");
					reBankAccept.setPayCommitTime(dbTimeMapper.selectDbTime().getDbTime());
					reBankAccept.setPayId(payId);
					reBankAccept.setRetMsg(msg);
					reBankAccept.setRetAccept(msg.substring(19, 38));
					
					if(reBankAcceptMapper.updateByPrimaryKey(reBankAccept)!=1){
						System.out.println("更新支付状态失败");
						return -1003;
					}
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1000;
			}
		}else if("YEEPAY".equals(orgCode)){
			String keyValue = formatString(Configuration.getInstance().getValue("keyValue"));   // 商家密钥
			String backParamsStr  = "";
			HashMap<String,String>  params = new HashMap<String,String>();
			try {
				backParamsStr = new String(baMessage.getBytes("iso-8859-1"),"gbk");
				String[] backParamsArr = backParamsStr.split("&");
				for(String kvStr:backParamsArr){
					String key = kvStr.substring(0,kvStr.indexOf('='));
					String value = kvStr.substring(kvStr.indexOf('=')+1);
					params.put(key, value);
				}
				String p1_MerId = formatString(params.get("p1_MerId")); //商户编号
				String r0_Cmd = formatString(params.get("r0_Cmd")); // 业务类型
				String r1_Code = formatString(params.get("r1_Code")); // 支付结果
				String r2_TrxId   = formatString(params.get("r2_TrxId"));// 易宝支付交易流水号
				String r3_Amt     = formatString(params.get("r3_Amt"));// 支付金额
				String r4_Cur     = formatString(params.get("r4_Cur"));// 交易币种
				String r5_Pid     = formatString(params.get("r5_Pid"));// 商品名称
				String r6_Order   = formatString(params.get("r6_Order"));// 商户订单号
				String r7_Uid     = formatString(params.get("r7_Uid"));// 易宝支付会员ID
				String r8_MP      = formatString(params.get("r8_MP"));// 商户扩展信息
				String r9_BType   = formatString(params.get("r9_BType"));// 交易结果返回类型
				String hmac       = formatString(params.get("hmac"));// 签名数据
				
				boolean isOK = false;
				// 校验返回数据包
				isOK = PaymentForOnlineService.verifyCallback(hmac,p1_MerId,r0_Cmd,r1_Code, 
						r2_TrxId,r3_Amt,r4_Cur,r5_Pid,r6_Order,r7_Uid,r8_MP,r9_BType,keyValue);
				if(!isOK){
					System.out.println("数字签名校验失败");
					return -2001;
				}
				
				amount = Double.parseDouble(r3_Amt);
				bankAccept = Long.parseLong(r6_Order);
				
				if("1".equals(r1_Code)){
					if(amount<reBankAcceptPayOrg.getPrice()){
						System.out.println("实际支付金额小于应付金额");
						return -2002;
					}
					ReBankAcceptKey reBankAcceptKey = new ReBankAcceptKey();
					reBankAcceptKey.setBankAccept(bankAccept);
					reBankAcceptKey.setOrgId(reBankAcceptPayOrg.getOrgId());
					ReBankAccept reBankAccept = reBankAcceptMapper.selectByPrimaryKey(reBankAcceptKey);
					
					reBankAccept.setPayType("Y");
					reBankAccept.setPayCommitTime(dbTimeMapper.selectDbTime().getDbTime());
					reBankAccept.setPayId(payId);
					reBankAccept.setRetMsg(msg);
					reBankAccept.setRetAccept(r2_TrxId);
					
					if(reBankAcceptMapper.updateByPrimaryKey(reBankAccept)!=1){
						System.out.println("更新支付状态失败");
						return -2003;
					}
					
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -2000;
			}
			
		}
		
		return 0;
	}
	
	public void insertBankOrderAccept(ReBankOrderAccept reBankOrderAccept){
		reBankOrderAcceptMapper.insert(reBankOrderAccept);
	}
	public void insertBankAccept(ReBankAccept reBankAccept){
		reBankAcceptMapper.insertSelective(reBankAccept);
	}
	public SyPayorgDict getSyPayorgDict(Integer orgId){
		return syPayorgDictMapper.selectByPrimaryKey(orgId);
	}
	public int updateOrderStatusByLogis(ReUserOrder reUserOrder){
		return reUserOrderMapper.updateOrderStatusByLogis(reUserOrder);
	}
}
