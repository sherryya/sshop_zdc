package com.tmount.business.cloopen.util;

import org.apache.http.message.AbstractHttpMessage;

public class HttpHeader {
	@SuppressWarnings("unused")
	public static void setHttpHeader(AbstractHttpMessage httpMessage) {
		if(!JsonUtils.JSON_ENABLE) {
			// 构造请求头信息
			httpMessage.setHeader("Accept", "application/xml");
			httpMessage.setHeader("Content-Type", "application/xml;charset=utf-8");
		} else {
			// 构造请求头信息
			httpMessage.setHeader("Accept", "application/json");
			httpMessage.setHeader("Content-Type", "application/json;charset=utf-8");
		}
	}
}
