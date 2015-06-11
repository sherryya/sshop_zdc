package com.tmount.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.tmount.exception.ShopException;

public class RequestDataHeader {
	public RequestDataHeader() {
		appid = 0;
		clientPlatform = 0;
		clientWidth = 0;
		clientHeight = 0;
		termId = "";
		commandId = 0;
		timestamp = 0;
		userId = 0;
		ver = "1.0";
		signMethod = "md5";
		appKey ="";
		sign = "";
	}
	
	public void init(JsonNode headNode) throws ShopException {
		appid = ParamData.getInt(headNode, "appid", 0);
		clientPlatform = ParamData.getInt(headNode, "platform", 0);
		clientWidth = ParamData.getInt(headNode, "width", 0);
		clientHeight = ParamData.getInt(headNode, "height", 0);
		termId = ParamData.getString(headNode, "term_id", "");
		commandId = ParamData.getInt(headNode, "command_id", 0);
		timestamp = ParamData.getLong(headNode, "timestamp", 0);
		userId = ParamData.getLong(headNode, "user_id", 0);
		ver = ParamData.getString(headNode, "ver", "1.0");
		signMethod = ParamData.getString(headNode, "ver", "md5");
		appKey =ParamData.getString(headNode, "app_key");
		sign = ParamData.getString(headNode, "sign");
	}
	
	/**
	 * 公司标识，公司标识也是不同客户端的唯一标识，我们将为不同的公司开发出不同的客户端应用。
	 */
	private int appid;
	
	/**
	 * 平台标识，见上面的平台定义
	 */
	private int clientPlatform;
	
	
	/**
	 * 分辨率宽度
	 */
	private int clientWidth;
	
	/**
	 * 分辨率高度
	 */
	private int clientHeight;
	
	/**
	 * 终端的唯一标识
	 */
	private String termId;
	
	/**
	 * 命令标识。具体命令见下面定义。命令标识用于唯一来标识客户端请求的命令。
	 */
	private int commandId;
	
	/**
	 * 时间戳，用于标识客户端的一次调用。服务器响应时需要返回同样的时间戳。
	 */
	private long timestamp;
	
	/**
	 * 用户标识。本字段可选。只用在需要用户验证的命令上
	 */
	private long userId;

	/**
	 * 版本号，默认1.0
	 */
	private String ver;

	/**
	 * 签名方法，默认md5
	 */
	private String signMethod;

	/**
	 * 应用KEY
	 */
	private String appKey;

	/**
	 * 验证串。
	 */
	private String sign;

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public int getClientPlatform() {
		return clientPlatform;
	}

	public void setClientPlatform(int clientPlatform) {
		this.clientPlatform = clientPlatform;
	}

	public int getClientWidth() {
		return clientWidth;
	}

	public void setClientWidth(int clientWidth) {
		this.clientWidth = clientWidth;
	}

	public int getClientHeight() {
		return clientHeight;
	}

	public void setClientHeight(int clientHeight) {
		this.clientHeight = clientHeight;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public int getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
