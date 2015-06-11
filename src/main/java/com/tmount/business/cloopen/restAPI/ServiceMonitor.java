package com.tmount.business.cloopen.restAPI;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class ServiceMonitor {
	public static void main(String[] args) {
	/*	String args1 = args[0];// 服务器IP
		String args2 = args[1];// 手机号
*/
		String args1 ="0001";// 服务器IP    
		String args2 = "15945150563";// 手机号
		String ip = "";
		if (args1.equalsIgnoreCase("0001")) {
			ip = "172.1.16.13";
		} else if (args1.equalsIgnoreCase("0002")) {
			ip = "172.1.16.14";
		}
		RestAPI rest = new RestAPI();
		try {
			String result = rest.SendSMS(args2, ip, "0", "3");
			System.out.println(result);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
