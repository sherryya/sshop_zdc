package com.tmount.bundle;

import java.util.HashMap;
import java.util.Map;

public class LogisticReason{
	
	public static String getSysErrMsg(String errCode)
	{
		Map<String,String> map = new HashMap<String,String>();
		map.put("B99","非法的物流订单号");
		map.put("B01","不能进行操作，当前状态是：等待确认");
		map.put("B02","不能进行操作，当前状态是：接单");
		map.put("B03","不能进行操作，当前状态是：不接单");
		map.put("B04","不能进行操作，当前状态是：揽收成功");
		map.put("B05","不能进行操作，当前状态是：揽收失败");
		map.put("B06","不能进行操作，当前状态是：签收成功");
		map.put("B07","不能进行操作，当前状态是：签收失败");
		map.put("B08","不能进行操作，当前状态是：订单已取消");
		map.put("B09","不能进行操作，运单号为空");
		map.put("B10","不能进行操作，签收信息为空(包括运单号、签收姓名、签收时间不能为空)");
		map.put("S01","非法的XML格式");
		map.put("S02","非法的数字签名");
		map.put("S03","非法的物流公司");
		map.put("S04","非法的通知类型");
		map.put("S05","非法的通知内空");
		map.put("S07","系统异常，请重试");
		map.put("S08","非法的电商平台");
		return map.get(errCode);
	}
	
	public static String getErrMsg(String errCode,String infoType)
	{
		Map<String,String> UNACCEPTMap = new HashMap<String,String>();
		UNACCEPTMap.put("R01","揽收地超服务范围");
		UNACCEPTMap.put("R02","派送地超服务范围");
		UNACCEPTMap.put("R03","揽收预约时间超范围，无法协商");
		UNACCEPTMap.put("R04","虚假揽货电话（客户电话与联系人不符）");
		UNACCEPTMap.put("R05","用户取消投递");
		UNACCEPTMap.put("R06","托寄物品为禁限寄品");
		UNACCEPTMap.put("R07","用户恶意下单");
		UNACCEPTMap.put("R25","黑名单客户");
		UNACCEPTMap.put("R99","其他原因");
		
		Map<String,String> NOTSENDMap = new HashMap<String,String>();
		NOTSENDMap.put("R01","取货地超服务范围");
		NOTSENDMap.put("R02","派送地超服务范围");
		NOTSENDMap.put("R08","揽收地址错误");
		NOTSENDMap.put("R04","虚假揽货电话（客户电话与联系人不符）");
		NOTSENDMap.put("R09","上门后用户不接受价格");
		NOTSENDMap.put("R06","托寄物品为禁限寄品");
		NOTSENDMap.put("R10","用户取消投递（非包装问题）");
		NOTSENDMap.put("R11","托寄物品超规格");
		NOTSENDMap.put("R12","用户拒绝开箱验货");
		NOTSENDMap.put("R13","多次联系，无法联系上发货方");
		NOTSENDMap.put("R14","用户要求延时揽收（需用户网上重新发货）");
		NOTSENDMap.put("R07","用户恶意下单");
		NOTSENDMap.put("R15","用户包装问题，取消投递");
		NOTSENDMap.put("R99","其他原因");
		
		Map<String,String> FAILEDMap = new HashMap<String,String>();
		FAILEDMap.put("R16","收件人拒收（未验货）");
		FAILEDMap.put("R17","收件人拒收（验货，货不对款）");
		FAILEDMap.put("R18","收件人拒付或仅愿意部分支付");
		FAILEDMap.put("R19","收件人拒收（因拖寄物品破损）");
		FAILEDMap.put("R20","收件人拒收（代收货款价格不对）");
		FAILEDMap.put("R21","超时无法投递");
		FAILEDMap.put("R22","托寄物品丢失");
		FAILEDMap.put("R23","无法联系上收件人");
		FAILEDMap.put("R24","错误收件人联系方式及地址");
		FAILEDMap.put("R99","其他原因");
		
		
		if(infoType.equals("NOT_SEND"))
			return NOTSENDMap.get(errCode);
		else if(infoType.equals("UNACCEPT"))
			return UNACCEPTMap.get(errCode);
		else if(infoType.equals("FAILED"))
			return FAILEDMap.get(errCode);
		else
			return "";
	}
	
	public static String getStatusMsg(String status)
	{
		Map<String,String> map = new HashMap<String,String>();
		map.put("CONFIRM","等待确认");
		map.put("ACCEPT","接单");
		map.put("UNACCEPT","不接单");
		map.put("GOT","揽收成功");
		map.put("NOT_SEND","揽收失败");
		map.put("SENT_SCAN","派件扫描");
		map.put("TRACKING","流转信息");
		map.put("SIGNED","签收成功");
		map.put("FAILED","签收失败");
		map.put("WITHDRAW","订单已取消");
		return map.get(status);
	}
	
	public static String getSFErrMsg(String status)
	{
		Map<String,String> map = new HashMap<String,String>();
		map.put("0101","请求数据缺少必选项");
		map.put("0102","请求参数中，关键字段的规则校验不合法");
		map.put("0103","字段长度超");
		map.put("0201","查询单号超过最大限制");
		map.put("1001","IP未授权");
		map.put("1002","服务（功能）未授权");
		map.put("1003","校验字段不合法");
		map.put("1004","无权访问相关数据");
		map.put("1005","查询次数超限制");
		map.put("1006","订单不存在");
		map.put("1007","订单已终结");
		map.put("1101","系统发生数据错误或运行时异常");
		map.put("1102"," 运单号申请失败");
		return map.get(status);
	}
}
