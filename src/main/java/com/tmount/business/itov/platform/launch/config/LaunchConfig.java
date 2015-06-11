package com.tmount.business.itov.platform.launch.config;

public class LaunchConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	public static String partner = "5EE7307B-9E3C-4CA3-B70E-2634DA6C7AB1";
	// 商户的私钥
	public static String key = "08875642-FC0D-4221-AE49-956D0ED12A21";
	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	// 签名方式 不需修改
	public static String sign_type = "MD5";
    //public static String  launch_url="http://openapi.lodp.dbscar.com:8082/OpenBizWeb.ashx?";
	public static String launch_url = "http://api.dbscar.com/openapi/?";
	public static String apiversionno = "1";
	public static String deviceuid = "6091e0c2-65dd-e211-8c19-00163e0071c8";// 6091e0c2-65dd-e211-8c19-00163e0071c8
	// 484847a7-a4dc-e211-8c19-00163e0071c8

	// 如下配置是：元征APP融合参数
	
	public static String app_id_iso="2013120200000001";
	public static String app_id_android="2013120200000002";
	public static String develop_id="10001";
	public static String develop_key="abcd*12345678";
	public static String url_app="http://open.api.dbscar.com/?";
	public static String url_app_test="http://golo.test.x431.com:8008/open/?";
	//public static String url_app="http://golo.test.x431.com:8008/open/?";
	

}
