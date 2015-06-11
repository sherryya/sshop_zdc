package com.tmount.business.order.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.tmount.config.ReOrderOperDictStatic;
import com.tmount.db.order.dto.ReOrderDeliver;
import com.tmount.db.order.dto.ReOrderInvoice;
import com.tmount.db.order.dto.ReUserOrder;
import com.tmount.db.order.dto.ReUserOrderAdd;
import com.tmount.db.order.dto.ReUserOrderDetail;
import com.tmount.db.order.dto.ReUserOrderLog;
import com.tmount.exception.ShopParamException;
import com.tmount.util.ParamData;

/**
 * 用来保存一条新订单的报文数据。
 * @author lugz
 *
 */
public class OneOrder {
	private ReUserOrder reUserOrder;
	
	private ReUserOrderLog reUserOrderLog;
	
	private ReUserOrderAdd reUserOrderAdd;
	
	private ReOrderDeliver reOrderDeliver;
	
	private ReOrderInvoice reOrderInvoice;

	private List<ReUserOrderDetail> reUserOrderDetailList;

	public ReUserOrder getReUserOrder() {
		return reUserOrder;
	}

	public void setReUserOrder(ReUserOrder reUserOrder) {
		this.reUserOrder = reUserOrder;
	}

	public ReUserOrderLog getReUserOrderLog() {
		return reUserOrderLog;
	}

	public void setReUserOrderLog(ReUserOrderLog reUserOrderLog) {
		this.reUserOrderLog = reUserOrderLog;
	}

	public ReUserOrderAdd getReUserOrderAdd() {
		return reUserOrderAdd;
	}

	public void setReUserOrderAdd(ReUserOrderAdd reUserOrderAdd) {
		this.reUserOrderAdd = reUserOrderAdd;
	}

	public ReOrderDeliver getReOrderDeliver() {
		return reOrderDeliver;
	}

	public void setReOrderDeliver(ReOrderDeliver reOrderDeliver) {
		this.reOrderDeliver = reOrderDeliver;
	}

	public List<ReUserOrderDetail> getReUserOrderDetailList() {
		return reUserOrderDetailList;
	}

	public void setReUserOrderDetailList(List<ReUserOrderDetail> reUserOrderDetailList) {
		this.reUserOrderDetailList = reUserOrderDetailList;
	}

	/**
	 * 生成一条订单报文记录。
	 * 报文结构:
	 *     shop_id 商店ID，必填
	 *     mark 订单说明，必填。
	 *     contact_phone，联系电话，必填。
	 *     contact_name，联系人，必填。
	 *     item_list 订单明细列表，必填
	 *               items_id 商品ID，必填
	 *               acount 订购商品数量，必填
	 *               price 商品单价，必填
	 *               data_type 商品数据标识，必填
	 *               get_point获得积分，可选，默认0
	 *               gift_status 是否赠品，可选，默认0 非赠品
	 *               actions_id 活动ID，可选，默认0
	 *     
	 *     fee_date 消费日期，可选，默认操作时间
	 *     acount 数量，可选，默认1
	 *     order_money 订单金额，可选，默认0
	 *     price 总价，可选，默认0
	 *     activity 有无活动，可选，默认无
	 *     freight_fee 运费，可选，默认0
	 *     order_mode 订购方式，可选，默认1 网上自助
	 *     pay_way_id 支付方式，可选，默认1  网上支付
	 *     secret_buy 隐私购买，可选，默认无0
	 *     take_mode 收货方式，可选，默认:1 自提
	 *     user_level 会员级别，可选，默认0 非注册会员
	 *
	 *     address 邮寄(配送)地址，可选，默认无。
	 *     arrival_time 到货时间段id，可选，默认0
	 *     province_code 省ID，可选，默认0
	 *     city_code 城市ID，可选，默认0
	 *     area_code 区ID，可选，默认0
	 *     logistics_id 物流公司id，可选，默认0
	 *     logistics_no 物流编号，可选，默认0
	 *     mobile 手机，可选，默认0
	 *     post_code 邮政编码，可选，默认000000
	 *     tx_logistics_id 物流订单号,默认0
	 *     state_code 状态,默认0 未发送
	 *     remark 接口返回备注信息，默认无。
	 *     logistics_type 物流接口类型，默认0线上。
	 *     order_id 发货地址id,默认0
	 *     send_online 运单状态，默认Y线上
	 * @param opTime
	 * @param loginAccept
	 * @param orderNo
	 * @param userId
	 * @param orderNode
	 * @return
	 * @throws ShopParamException
	 */
	static public OneOrder addOneOrder(Date opTime, Long loginAccept, Long orderNo, 
			Long userId, JsonNode orderNode) throws ShopParamException {
		OneOrder oneOrder = new OneOrder();
		//填充用户定单
		ReUserOrder reUserOrder = new ReUserOrder();
		reUserOrder.setOrderNo(orderNo);	//取自序列。
		reUserOrder.setLoginAccept(loginAccept);	//一笔流水可以有多笔订单。
		reUserOrder.setUserId(userId);
		if (orderNode.get("acount") == null) {
			reUserOrder.setAcount(1);	//数量
		} else {
			reUserOrder.setAcount(orderNode.get("acount").intValue());
		}
		if (orderNode.get("order_money") == null) {
			reUserOrder.setOrderMoney(0.0);	//订单金额
		} else {
			reUserOrder.setOrderMoney(orderNode.get("order_money").doubleValue());
		}
		
		reUserOrder.setOrderValid("Y");
		reUserOrder.setOrderTime(opTime);	//下单时间
		reUserOrder.setOrderEnd("N");
		reUserOrder.setCreateDate(opTime);
		reUserOrder.setUpdateTime(opTime);
		reUserOrder.setUpdateNo(0);	//工号默认为0，网站无工号.
		reUserOrder.setEndTime(opTime);
		if (orderNode.get("fee_date") == null) {//计划消费日期
			reUserOrder.setFeeDate(opTime);
		} else {
			reUserOrder.setFeeDate(ParamData.getDate(orderNode, "fee_date"));
		}
		if (orderNode.get("activity") == null) {
			reUserOrder.setActivity("0");//0 无, 1 有；只添加有无活动,以后根据活动的属性再设计；只记录和子订单有关的活动。
		} else {
			reUserOrder.setActivity(orderNode.get("activity").textValue());//0 无, 1 有；只添加有无活动,以后根据活动的属性再设计；只记录和子订单有关的活动。
		}
		reUserOrder.setFinalPayStatus("0"); //财务确认付款状态
		if (orderNode.get("freight_fee") == null) {
			reUserOrder.setFreightFee(new Double(0));	//运费;
		} else {
			reUserOrder.setFreightFee(orderNode.get("freight_fee").doubleValue());	//运费;
		}
		if (orderNode.get("order_mode") == null) {//订购方式
			reUserOrder.setOrderMode(1);	//1 网上自助,2 电话订购, 3 合作加盟店订购
		} else {
			reUserOrder.setOrderMode(orderNode.get("order_mode").intValue());
		}
		reUserOrder.setPayMoney(new Double(0));//实际付款金额，订单创建时未交费，为0。
		reUserOrder.setPayStatus("N");	//付费状态
		if (orderNode.get("pay_way_id") == null) {
			reUserOrder.setPayWayId(1);		//1  网上支付	2  货到付款
		} else {
			reUserOrder.setPayWayId(orderNode.get("pay_way_id").intValue());	//1  网上支付	2  货到付款
		}
		if (orderNode.get("price") == null) {
			reUserOrder.setPrice(reUserOrder.getOrderMoney()+reUserOrder.getFreightFee());//总价=order_money（订单金额）+freight_fee（运费）
		} else {
			reUserOrder.setPrice(orderNode.get("price").doubleValue());
		}
		if (orderNode.get("secret_buy") == null) {//隐私购买
			reUserOrder.setSecretBuy(0);	//0 无, 1 有  只添加有无活动,以后根据活动的属性再设计，只记录和子订单有关的活动。
		} else {
			reUserOrder.setSecretBuy(orderNode.get("secret_buy").intValue());
		}
		reUserOrder.setShopId(new Long(ParamData.getLong(orderNode, "shop_id")));	//商店ID
		if (orderNode.get("take_mode") == null) {//收货方式
			reUserOrder.setTakeMode(1);//收货方式:1 自提 , 2 邮政普通包裹,3 邮政特快(EMS),4 快递公司
		} else {
			reUserOrder.setTakeMode(orderNode.get("take_mode").intValue());
		}
		if (orderNode.get("user_level") == null) {//会员级别.
			reUserOrder.setUserLevel(0);//购买时的会员级别 0 非注册会员,1 注册会员, 2 银卡会员, 3 金卡会员
		} else {
			reUserOrder.setUserLevel(orderNode.get("user_level").intValue());
		}
		if (reUserOrder.getPayWayId().equals(2)) {
			reUserOrder.setState(20);	//2（货到付款）,的状态直接变成20（等待卖家发货）。
		} else {
			reUserOrder.setState(10);	//新订单状态
		}
		
		oneOrder.setReUserOrder(reUserOrder);
		
		//订单操作流水表
		ReUserOrderLog reUserOrderLog = new ReUserOrderLog();
		reUserOrderLog.setOrderNo(orderNo);
		reUserOrderLog.setLoginAccept(loginAccept);
		reUserOrderLog.setDealMark(ParamData.getString(orderNode, "mark"));
		reUserOrderLog.setDealNo(userId.intValue());
		reUserOrderLog.setDealName("");//用户名称
		reUserOrderLog.setDealTime(opTime);
		reUserOrderLog.setDealType(1);	//0 系统工号，1用户id
		reUserOrderLog.setNewState(reUserOrder.getState());
		reUserOrderLog.setOldState(0);	//老订单状态
		reUserOrderLog.setOperId(ReOrderOperDictStatic.AddOrder);
		reUserOrderLog.setSysMark("订单下单");
		
		oneOrder.setReUserOrderLog(reUserOrderLog);
		
		//填充订单附加信息
		ReUserOrderAdd reUserOrderAdd = new ReUserOrderAdd();
		reUserOrderAdd.setOrderNo(orderNo);
		reUserOrderAdd.setMark("预留");	//预留字段
		oneOrder.setReUserOrderAdd(reUserOrderAdd);
		
		//订单配货信息表
		ReOrderDeliver reOrderDeliver = new ReOrderDeliver();
		if (orderNode.get("address") == null) {//邮寄(配送)地址
			reOrderDeliver.setAddress("无");	
		} else {
			reOrderDeliver.setAddress(orderNode.get("address").textValue());
		}
		if (orderNode.get("arrival_time") == null) {//到货时间段id
			reOrderDeliver.setArrivalTime(0);	
		} else {
			reOrderDeliver.setArrivalTime(orderNode.get("arrival_time").intValue());
		}
		if (orderNode.get("province_code") == null) {//省ID
			reOrderDeliver.setProvinceCode(0);	
		} else {
			reOrderDeliver.setProvinceCode(orderNode.get("province_code").intValue());
		}
		if (orderNode.get("city_code") == null) {//城市ID
			reOrderDeliver.setCityCode(0);	
		} else {
			reOrderDeliver.setCityCode(orderNode.get("city_code").intValue());
		}
		if (orderNode.get("area_code") == null) {//区县ID
			reOrderDeliver.setAreaCode(0);	
		} else {
			reOrderDeliver.setAreaCode(orderNode.get("area_code").intValue());
		}
		
		reOrderDeliver.setContactPhone(ParamData.getString(orderNode, "contact_phone"));
		reOrderDeliver.setUserName(ParamData.getString(orderNode, "contact_name"));
		reOrderDeliver.setCreateTime(opTime);
		if (orderNode.get("logistics_id") == null) {//物流公司id
			reOrderDeliver.setLogisticsId(0);	
		} else {
			reOrderDeliver.setLogisticsId(orderNode.get("logistics_id").intValue());
		}
		if (orderNode.get("logistics_no") == null) {//物流编号
			reOrderDeliver.setLogisticsNo("");
		} else {
			reOrderDeliver.setLogisticsNo(orderNode.get("logistics_no").textValue());
		}
		
		reOrderDeliver.setMark(ParamData.getString(orderNode, "mark"));
		if (orderNode.get("mobile") == null) {//手机
			reOrderDeliver.setMobile("0");
		} else {
			reOrderDeliver.setMobile(orderNode.get("mobile").textValue());
		}
		reOrderDeliver.setOrderNo(orderNo);
		if (orderNode.get("post_code") == null) {//邮政编码
			reOrderDeliver.setPostCode("000000");
		} else {
			reOrderDeliver.setPostCode(orderNode.get("post_code").textValue());
		}
		if (orderNode.get("mark") == null) {//用户留言
			reOrderDeliver.setMark("无");
		} else {
			reOrderDeliver.setMark(orderNode.get("mark").textValue());
		}
		if (orderNode.get("tx_logistics_id") == null) {//物流订单号
			reOrderDeliver.setTxLogisticsId("0");
		} else {
			reOrderDeliver.setTxLogisticsId(orderNode.get("tx_logistics_id").textValue());
		}
		if (orderNode.get("state_code") == null) {//状态
			reOrderDeliver.setStateCode(0);	//0 未发送
		} else {
			reOrderDeliver.setStateCode(orderNode.get("state_code").intValue());
		}
		if (orderNode.get("remark") == null) {//接口返回备注信息
			reOrderDeliver.setRemark("无");
		} else {
			reOrderDeliver.setRemark(orderNode.get("remark").textValue());
		}
		if (orderNode.get("logistics_type") == null) {//物流接口类型
			reOrderDeliver.setLogisticsType(0);//0 线上
		} else {
			reOrderDeliver.setLogisticsType(orderNode.get("logistics_type").intValue());
		}
		if (orderNode.get("order_id") == null) {//发货地址id
			reOrderDeliver.setOrderId(0);//
		} else {
			reOrderDeliver.setOrderId(orderNode.get("order_id").intValue());
		}
		if (orderNode.get("send_online") == null) {//默认Y线上
			reOrderDeliver.setSendOnline("Y");//
		} else {
			reOrderDeliver.setSendOnline(orderNode.get("send_online").textValue());
		}
		oneOrder.setReOrderDeliver(reOrderDeliver);
		
		if (orderNode.get("order_invoice") == null) {//发票节点
			oneOrder.setReOrderInvoice(null);
		} else {
			ReOrderInvoice reOrderInvoice = new ReOrderInvoice();
			reOrderInvoice.setOrderNo(orderNo);
			reOrderInvoice.setUserId(userId);
			reOrderInvoice.setOpTime(opTime);
			JsonNode orderInvoiceNode = orderNode.get("order_invoice");
			if (orderInvoiceNode.get("invoice_info") == null) {//发票信息
				reOrderInvoice.setInvoiceInfo("");
			} else {
				reOrderInvoice.setInvoiceInfo(orderInvoiceNode.get("invoice_info").textValue());
			}
			if (orderInvoiceNode.get("invoice_code") == null) {//发票号
				reOrderInvoice.setInvoiceCode("");
			} else {
				reOrderInvoice.setInvoiceCode(orderInvoiceNode.get("invoice_code").textValue());
			}
			if (orderInvoiceNode.get("invoice_title") == null) {//发票抬头
				reOrderInvoice.setInvoiceTitle("");
			} else {
				reOrderInvoice.setInvoiceTitle(orderInvoiceNode.get("invoice_title").textValue());
			}
			if (orderInvoiceNode.get("invoice_content") == null) {//发票内容
				reOrderInvoice.setInvoiceContent("");
			} else {
				reOrderInvoice.setInvoiceContent(orderInvoiceNode.get("invoice_content").textValue());
			}
			if (orderInvoiceNode.get("identification") == null) {//税号
				reOrderInvoice.setIdentification("");
			} else {
				reOrderInvoice.setIdentification(orderInvoiceNode.get("identification").textValue());
			}
			oneOrder.setReOrderInvoice(reOrderInvoice);
		}
		

		JsonNode userOrderDetailsJson = ParamData.getJsonNode(orderNode, "item_list");
		
		List<ReUserOrderDetail> reUserOrderDetailList;
		reUserOrderDetailList = new ArrayList<ReUserOrderDetail>();
		Iterator<JsonNode> detailIt = userOrderDetailsJson.elements();
		JsonNode userOrderDetailJson;
		ReUserOrderDetail reUserOrderDetail;
		while(detailIt.hasNext()) {
			userOrderDetailJson = detailIt.next();
			reUserOrderDetail = new ReUserOrderDetail();
			reUserOrderDetail.setOrderNo(orderNo);
			reUserOrderDetail.setItemsId(new Long(ParamData.getLong(userOrderDetailJson, "items_id")));
			//reUserOrderDetail.setItemsType(new Integer(ParamData.getInt(userOrderDetailJson, "items_type")));
			reUserOrderDetail.setItemsType(0);
			reUserOrderDetail.setAcount(new Integer(ParamData.getInt(userOrderDetailJson, "acount")));
			reUserOrderDetail.setPrice(new Double(ParamData.getDouble(userOrderDetailJson, "price")));
			reUserOrderDetail.setDataType(new Integer(ParamData.getInt(userOrderDetailJson, "data_type")));
			reUserOrderDetail.setAllPrice(reUserOrderDetail.getAcount() * reUserOrderDetail.getPrice());
			reUserOrderDetail.setOpTime(opTime);
			if (orderNode.get("get_point") == null) {//获得积分
				reUserOrderDetail.setGetPoint(0);
			} else {
				reUserOrderDetail.setGetPoint(orderNode.get("get_point").intValue());
			}
			if (orderNode.get("gift_status") == null) {//是否赠品:0 非赠品,1 赠品
				reUserOrderDetail.setGiftStatus("0");
			} else {
				reUserOrderDetail.setGiftStatus(orderNode.get("gift_status").textValue());
			}
			if (orderNode.get("actions_id") == null) {	//活动ID
				reUserOrderDetail.setActionsId(new Long(0));
			} else {
				reUserOrderDetail.setActionsId(orderNode.get("actions_id").longValue());
			}
			reUserOrderDetail.setState(1);	//状态:1正常，2退货，3换货
			reUserOrderDetailList.add(reUserOrderDetail);
		}
		oneOrder.setReUserOrderDetailList(reUserOrderDetailList);

		return oneOrder;
	}

	public ReOrderInvoice getReOrderInvoice() {
		return reOrderInvoice;
	}

	public void setReOrderInvoice(ReOrderInvoice reOrderInvoice) {
		this.reOrderInvoice = reOrderInvoice;
	}
}

