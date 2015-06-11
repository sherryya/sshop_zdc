package com.tmount.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.wutka.jox.JOXBeanInputStream;
import com.wutka.jox.JOXBeanOutputStream;

public class TransXML {
	/**
	 * Retrieves a bean object for the received XML and matching bean class
	 */
	public static Object xml2OBJ(String xml, Class className) {
		System.setProperty("com.oce.wutka.dateFormat","yyyy-MM-dd HH:mm:ss");
		System.out.println("sys=================="+System.getProperty("com.oce.wutka.dateFormat"));
		ByteArrayInputStream xmlData = new ByteArrayInputStream(xml.getBytes());
		JOXBeanInputStream joxIn = new JOXBeanInputStream(xmlData);
		try {
			return (Object) joxIn.readObject(className);
		} catch (IOException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			try {
				xmlData.close();
				joxIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Retrieves a bean object for the received XML and matching bean class
	 */
	public static Object xml2OBJ(String xml, Class className,String dateFormate) {
		System.setProperty("com.oce.wutka.dateFormat",dateFormate);
		ByteArrayInputStream xmlData = new ByteArrayInputStream(xml.getBytes());
		JOXBeanInputStream joxIn = new JOXBeanInputStream(xmlData);
		try {
			return (Object) joxIn.readObject(className);
		} catch (IOException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			try {
				xmlData.close();
				joxIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Retrieves a string json for the received XML and matching bean class
	 */
	public static String xml2JSON(String xml, Class className) {
		ByteArrayInputStream xmlData = new ByteArrayInputStream(xml.getBytes());
		JOXBeanInputStream joxIn = new JOXBeanInputStream(xmlData);
		Json json = new Json();
		try {
			return json.readAsString((Object) joxIn.readObject(className));
		} catch (IOException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			try {
				xmlData.close();
				joxIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns an XML document.nbspString for the received bean
	 */
	public static String obj2XML(Object bean) {
		ByteArrayOutputStream xmlData = new ByteArrayOutputStream();
		JOXBeanOutputStream joxOut = new JOXBeanOutputStream(xmlData);
		try {
			joxOut.writeObject(beanName(bean), bean);
			return xmlData.toString();
		} catch (IOException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			try {
				xmlData.close();
				joxOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Find out the bean class name
	 */
	private static String beanName(Object bean) {
		String fullClassName = bean.getClass().getName();
		String classNameTemp = fullClassName.substring(
				fullClassName.lastIndexOf(".") + 1, fullClassName.length());
		return classNameTemp.substring(0, 1) + classNameTemp.substring(1);
	}

}
