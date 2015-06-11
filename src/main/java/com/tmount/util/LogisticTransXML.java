package com.tmount.util;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class LogisticTransXML {

	public static String transStringYTO(String inputStr,String passDept) throws Exception
	{
		Map<String,String> sendXmlMap = new HashMap<String,String>();
		String charset = "UTF8";
		String inPassStr = inputStr+passDept;
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(inPassStr.getBytes(charset));
		byte md5Byte[] = md5.digest();
        String passStr = new String(Base64.encodeBase64(md5Byte));
		sendXmlMap.put("logistics_interface",inputStr);
		sendXmlMap.put("data_digest",passStr);
		return passStr;
	}
}
