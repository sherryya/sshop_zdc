package com.tmount.business.itov.platform.inter.launch;

import java.io.BufferedReader;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;

import com.tmount.business.util.ClientHelper;

public class GetBreakRulesService {

	/**
	 * 查询车辆违章情况
	 * 
	 * @return
	 */
	public static String getBreakRulesInfo(Map params) {
		String result = "";
		BufferedReader in = null;
		try {
			String carNumber = (String) params.get("carNumber");
			String bphm = carNumber.substring(2, carNumber.length());
			String xzqh = carNumber.substring(1, 2);
			String dy = carNumber.substring(0, 1);
			String clsbdh = (String) params.get("carSn");
			String carType = (String) params.get("carType");
			String paymentFlag = (String) params.get("paymentFlag");
			String paramsString = "hpzl=" + carType + "&dy=" + dy + "&xzqh="
					+ xzqh + "&hphm=" + bphm + "&clsbdh=" + clsbdh + "&jkbj="
					+ paymentFlag + "&ts=138266163101";
			String url = "http://www.hljjj.gov.cn:8081/Home/getWfcx";
			ClientHelper ch = new ClientHelper("", 0);
			NameValuePair v1 = new NameValuePair("hpzl",carType);
			NameValuePair v2 = new NameValuePair("dy", dy);
			NameValuePair v3 = new NameValuePair("xzqh", xzqh);
			NameValuePair v4 = new NameValuePair("hphm", bphm);
			NameValuePair v5 = new NameValuePair("clsbdh",clsbdh);
			NameValuePair v6 = new NameValuePair("jkbj", "%");
			NameValuePair v7 = new NameValuePair("ts", "138266163101");

			result = ch.postToGetString(ch, url, new NameValuePair[] { v1, v2,
					v3, v4, v5, v6, v7 });
			System.out.println("result"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		GetBreakRulesService getbreakrulesservice = new GetBreakRulesService();
		getbreakrulesservice.getBreakRulesInfo(null);
	}
}
