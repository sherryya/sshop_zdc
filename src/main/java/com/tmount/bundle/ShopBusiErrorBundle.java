package com.tmount.bundle;

import java.util.ListResourceBundle;

public class ShopBusiErrorBundle extends ListResourceBundle {

	public static final String ERROR_REQUEST_PACK ="1000";
	public static final String NOT_EXISTS_COMPANY ="2000";
	public static final String NOT_EXISTS_USER ="2001";
	public static final String ERR_USER_PASSWORD ="2002";
	public static final String EXISTS_USER ="2003";
	public static final String NOT_EXISTS_ROOM ="2004";
	public static final String NOT_EXISTS_ITEM ="2005";
	public static final String NOT_RIGHT_PASSWORD ="2006";
	public static final String EXISTS_FAVOURITE_ITEMS ="2007";
	public static final String EXISTS_FAVOURITE_SHOP ="2008";
	public static final String NOT_FOUND_ITEM ="2009";
	public static final String NOT_FOUND_SHOP ="2010";
	public static final String MAIL_HAS_VERIFY ="2011";
	public static final String INPUT_VERIFY_IS_NULL ="2012";
	public static final String INPUT_VERIFY_IS_ERR = "2013";
	public static final String MAIL_HAS_NOT_VERIFY = "2014";
	public static final String CHECK_CODE_IS_NULL = "2015";
	public static final String CHECK_CODE_IS_EXP = "2016";
	public static final String MAIL_HAS_VERIFY_BY_OTHER = "2017";
	public static final String NOT_EXISTS_ORDER ="3001";
	public static final String ORDER_STATE_DELETE ="3002";
	public static final String ORDER_STATE_ERROR ="3003";
	public static final String ORDER_NOT_EXISTS_ITEMS ="3004";
	public static final String ORDER_NOT_SUPPLY_ITEMS ="3005";
	public static final String ORDER_BANK_PAY_ERROR ="3006";
	public static final String ORDER_DETAIL_GET_ERROR ="3007";
	public static final String NOT_EXISTS_AD ="4000";
	public static final String NOT_SUPPERT_STORE_TYPE ="4001";
	public static final String NOT_EXISTS_CLIENTID ="5000";
	public static final String LAUNCH_INTERFACE_WRONG="6000";
	public static final String LAUNCH_INTERFACE_WRONG_DATA_ISEMPTY="6001";
	public static final String NO_NULL_VALUES="6003";
	public static final String NOT_EQUALS="6004";
	public static final String DRIVERS_CAR_EQUALS="6005";
	public static final String NOT_SELECT="6006";
	public static final String NOT_EXITS="6007";
	public static final String CAR_NOT_EXITS="6008";
	public static final String 	IS_EXIST_CAR="6009";
	public static final String 	IS_NOTEXIST_TERMINAL="6010";
	public static final String 	IS_EXIST_CAR_TER="6011";		
	public static final String 	WRONGMESSAGEBYDEVICEID="6012";
	public static final String 	USERNAME_VAL="6013";
	public static final String 	PASSWORD_VAL="6014";
	public static final String NOT_EXISTS_AGENTUSER = "6015";
	public static final String NOT_VOIP_LOGIN = "6016";
	public static final String ERR_PTT_CALLID="6017";
	public static final String VOIP_SUB_CEATE_ERROR="6018";
	public static final String VOIP_ERROR="6019";
	public static final String VOIP_ACCOUNT_NAME_ERROR="6020";
	public static final String NOT_EXISTS_DEFAULT_CAR="6021";
	public static final String DISCUZ_UCENTER_ERROR="6022";
	public static final String SEND_MESSAGE_ERROR ="6023";
	public static final String RESERVER_ORDER_ISEXIT="6024";
	public static final String OBD_REG="6075";
	public static final String OBD_REG_ERROR="6026";
	public static final String BREAKRULESPlATFORM_ERROR="6025";
	public static final String OBD_PWD="6076";
	
	public static final String VMR_NULL="6077";
	public static final String VMR_ERROR="6078";
	public static final String FRIEND_RELATION_ISEXIST="6079";
	public static final String ISEXIST="6080";
	
	public static final String TERMINAL_DUPILACATE = "6081";
	public static final String TERMINAL_INSERT_FAILURE = "6082";
	public static final String USER_INSERT_FAILURE = "6083";
	public static final String PERSON_INSERT_FAILURE = "6084";
	public static final String ACCOUNT_INSERT_FAILURE = "6085";
	public static final String SUBACCOUNT_INSERT_FAILURE = "6086";
	public static final String DOCUMENTEXCEPTION = "6087";
	public static final String INSERT_FAILURE ="6088";
	public static final String COMMON="6089";
	
	
	static final Object[][] contents = {
		{INSERT_FAILURE,"插入%信息失败"},
		{TERMINAL_DUPILACATE,"terminal信息重复已存在imei"},
		{TERMINAL_INSERT_FAILURE,"插入terminal信息失败"},
		{USER_INSERT_FAILURE,"插入user信息失败"},
		{PERSON_INSERT_FAILURE,"插入person信息失败"},
		{ACCOUNT_INSERT_FAILURE,"插入account信息失败"},
		{SUBACCOUNT_INSERT_FAILURE,"插入subaccount信息失败"},
		{DOCUMENTEXCEPTION,"解析doc异常"},
		{ERROR_REQUEST_PACK, "传入的请求报文错误。"},
		{NOT_EXISTS_COMPANY, "不存在的公司信息，请检查公司代码%是否正确。"},
		{NOT_EXISTS_USER, "用户%不存在，请检查用户账号输入是否正确。"},
		{ERR_USER_PASSWORD, "用户账号密码输入错误，检查是否大写键锁定。"},
		{EXISTS_USER, "用户账号%已经存在。"},
		{NOT_EXISTS_ROOM, "不存在的酒店包间%。"},
		{NOT_EXISTS_ITEM, "不存在的商品ID%。"},
		{NOT_RIGHT_PASSWORD, "用户%的密码不正确，请检查用户账号输入是否正确。"},
		{EXISTS_FAVOURITE_ITEMS, "商品%已收藏"},
		{EXISTS_FAVOURITE_SHOP, "商店%已收藏"},
		{NOT_EXISTS_ORDER, "不存在的订单号%。"},
		{ORDER_STATE_DELETE, "订单号%的状态不允许取消。"},
		{NOT_FOUND_ITEM, "抱歉，没有找到与%相关的商品"},
		{NOT_FOUND_SHOP, "抱歉，没有找到与%相关的商店"},
		{ORDER_STATE_ERROR, "订单号%的状态不正确"},
		{ORDER_NOT_EXISTS_ITEMS, "订购的商品%不存在"},
		{ORDER_NOT_SUPPLY_ITEMS, "订购的商品%数量已经不足"},
		{ORDER_BANK_PAY_ERROR, "银行缴费失败，银行缴费流水号%。"},
		{NOT_EXISTS_AD, "抱歉，没有找到与%相关的区域内容"},
		{MAIL_HAS_VERIFY,"用户已经验证邮箱%"},
		{INPUT_VERIFY_IS_NULL,"输入的校验码为空"},
		{INPUT_VERIFY_IS_ERR,"校验码错误"},
		{MAIL_HAS_NOT_VERIFY,"用户没有验证邮箱"},
		{CHECK_CODE_IS_NULL,"请先获取校验码"},
		{CHECK_CODE_IS_EXP,"校验码已经过期，请重新获取"},
		{MAIL_HAS_VERIFY_BY_OTHER,"邮箱%已经被其他账户绑定"},
		{ORDER_DETAIL_GET_ERROR,"物流流转信息尚未生成，请耐心等待!"},
		{NOT_SUPPERT_STORE_TYPE,"不支持的上传图片种类%!"},
		{NOT_EXISTS_CLIENTID,"不存在的客户端标识%!"},
		{LAUNCH_INTERFACE_WRONG,"调用元征接口出错"},
		{LAUNCH_INTERFACE_WRONG_DATA_ISEMPTY,"调用元征接口返回DATA为空"},
		{NOT_EXISTS_CLIENTID,"不存在的客户端标识%!"},
		{NO_NULL_VALUES,"%不能为空值"},
		{NOT_EQUALS,"%和%不能相同"},
		{DRIVERS_CAR_EQUALS,"人车不对应，是否继续签到"},
		{NOT_SELECT,"请选择%"},
		{NOT_EXITS,"%不存在"},
		{CAR_NOT_EXITS,"请添加车辆"},
		{IS_EXIST_CAR,"%该设备ID已经被绑定"},
		{IS_NOTEXIST_TERMINAL,"不存在%该设备ID"},
		{IS_EXIST_CAR_TER,"车辆没有有绑定终端"},
		{WRONGMESSAGEBYDEVICEID,"根据该设备ID没有找到对应的数据"},
		{USERNAME_VAL,"账号注册规则不正确( 4-16个字符,可使用小写字母、数字、下划线 需要以字母开头)"},
		{PASSWORD_VAL,"密码规则不正确（长度 6-16个字符 ，可使用大小写字母、数字、特殊字符，不能包含空格， 字母区分大小写 ）"},
		{NOT_EXISTS_AGENTUSER, "%对应坐席用户不存在，请检查用户账号输入是否正确。"},
		{NOT_VOIP_LOGIN, "无法登录坐席，请联系管理员。"},
		{ERR_PTT_CALLID, "没有找到对应的来电ID%。"},
		{VOIP_SUB_CEATE_ERROR,"调用voip创建子账号接口出错"},
		{VOIP_ERROR,"VOIP账号出现异常"},
		{VOIP_ACCOUNT_NAME_ERROR,"没有找到voip账号名称%"},
		{NOT_EXISTS_DEFAULT_CAR,"没有找到默认的车辆"},
		{DISCUZ_UCENTER_ERROR,"调用DISCUZ出错，%"},
		{SEND_MESSAGE_ERROR,"短信下发接口出错"},
		{RESERVER_ORDER_ISEXIT,"预约订单已经存在"},
		{OBD_REG,"【%】已被其他人使用"},
		{OBD_REG_ERROR,"%激活失败"},
		{BREAKRULESPlATFORM_ERROR,"%"},
		{OBD_PWD,"% 不正确"},
		{VMR_NULL,"没有数据"},
		{VMR_ERROR,"车辆检测报告查询错误"},
		{FRIEND_RELATION_ISEXIST,"此%好友已经绑定"},
		{ISEXIST,"% %  已经存在"},
		{COMMON,"%"}
	};
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
