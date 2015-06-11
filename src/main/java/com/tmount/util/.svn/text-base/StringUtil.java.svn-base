package com.tmount.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class StringUtil {
	public static String lpadFormat(Object oStr, int length, String format) {
		String vStr = String.valueOf(oStr);
		int len = vStr.length();
		StringBuffer retStr = new StringBuffer();
		for (int i = 0; i < length - len; i++) {
			retStr.append(format);
		}
		retStr.append(vStr);
		return retStr.toString();
	}
	
	
	public static String getParmsFromXml(String xml)
	  {
		  Document doc = null;
			try {
				doc = DocumentHelper.parseText(xml.toString());
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element root = doc.getRootElement();
			Integer statusCode;
			statusCode=  Integer.valueOf(root.elementTextTrim("statusCode")) ;
			System.out.println("statusCode:"+statusCode);
			return statusCode.toString();
	  }
}
