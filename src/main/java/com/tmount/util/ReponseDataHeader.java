package com.tmount.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;


public class ReponseDataHeader {
	public ReponseDataHeader() {
		commandId = 0;
		timestamp = 0;
		errorCode = "0";
		errorMessage = "";
		ver = "1.0";
		signMethod = "md5";
		appKey ="";
		sign = "";
	}

	
	public void toJsonStr(JsonGenerator responseBodyJson) throws JsonGenerationException, IOException {
		responseBodyJson.writeFieldName("head");
		responseBodyJson.writeStartObject();
		responseBodyJson.writeNumberField("command_id", commandId);
		responseBodyJson.writeNumberField("timestamp", timestamp);
		responseBodyJson.writeNumberField("error_code", new Integer(errorCode));
		responseBodyJson.writeStringField("error_msg", errorMessage);
		responseBodyJson.writeStringField("ver", ver);
		responseBodyJson.writeStringField("sign_m", signMethod);
		responseBodyJson.writeStringField("app_key", appKey);
		responseBodyJson.writeStringField("sign", sign);
		responseBodyJson.writeEndObject();	
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

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 命令标识。具体命令见下面定义。命令标识用于唯一来标识客户端请求的命令。
	 */
	private int commandId;
	
	/**
	 * 时间戳，用于标识客户端的一次调用。服务器响应时需要返回同样的时间戳。
	 */
	private long timestamp;

	/**
	 * 命令标识。具体命令见下面定义。命令标识用于唯一来标识客户端请求的命令。
	 */
	private String errorCode;
	
	/**
	 * 时间戳，用于标识客户端的一次调用。服务器响应时需要返回同样的时间戳。
	 */
	private String errorMessage;

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
}
