package com.tmount.bundle;

import java.util.ListResourceBundle;

public class ShopParamErrorBundle extends ListResourceBundle {

	public static final String LOST_IN_PARAM ="1000";
	public static final String FORMAT_ERROR_IN_PARAM ="1001";
	public static final String LOST_OPTION_IN_PARAM ="1002";
	public static final String INVALID_SIGN_PARAM ="1003";
	public static final String INVALID_PARAM_VALUE ="1004";
	static final Object[][] contents = {
		{LOST_IN_PARAM, "缺少输入参数%。"},
		{FORMAT_ERROR_IN_PARAM, "输入参数%格式错误错误，检查是否为%类型。"},
		{LOST_OPTION_IN_PARAM, "必须输入几个可选参数之一。"},
		{INVALID_SIGN_PARAM, "不合法的输入校验码%。"},
		{INVALID_PARAM_VALUE, "参数%的值%不合法。"}
	};
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
