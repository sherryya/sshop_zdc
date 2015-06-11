package com.tmount.util;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUitl {
	
	/**
	 * 得到指定字段的值
	 * @param xml
	 * @param filed
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("rawtypes")
	public static String xml(String xml,String filed) throws DocumentException
	{
		   xml=xml.substring(55);
		   xml=xml.replace("\"}", "");
		   Document doc = null;  
		   System.out.println(xml);
	       doc = DocumentHelper.parseText(xml); // 将字符串转为XML  
	       Element rootElt = doc.getRootElement(); // 获取根节点  
	       System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称  
	       String ret="";
	       for (Iterator iter = rootElt.elementIterator(); iter.hasNext();)
	        {
	            Element e = (Element) iter.next();
	            if(e.getName().equalsIgnoreCase(filed))
	            {
	            	ret=e.getData().toString();
	            	break;
	            }
	        }
	       return ret;
	}
}
