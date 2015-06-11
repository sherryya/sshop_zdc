package com.tmount.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexInfo {
    // 账号注册规则： 4-16个字符,可使用小写字母、数字、下划线 需要以字母开头
	public static  final String REG_USERNAME="^[a-z][a-z0-9_]{4,16}$";
    // 密码规则：  长度 6-16个字符 （字母、数字、特殊字符） 不能包含空格 字母区分大小写 
	public static final String REG_PASSWORD="^[0-9a-zA-Z!@$#%^&*()-_+=,<.>/?|\\{}`~]{6,16}$";
	//除空格以外的所有字符
	//public static final String REG_PASSWORD="^[\\S]{6,16}$";
    /**
     * 正则表达式验证
     * @param parms
     * @param reg
     * @return
     */
	public static boolean Validation(String parms,String reg)
	{
		Pattern p=Pattern.compile(reg);
		Matcher m=p.matcher(parms);
		boolean result=m.find();
		return result;
	}
}
