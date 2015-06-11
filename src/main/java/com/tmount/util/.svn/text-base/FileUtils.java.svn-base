/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.cloopen.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.tmount.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * <p>
 * Title: FileUtils
 * </p>
 * <p>
 * Description: file工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: hisunsray
 * </p>
 * <p>
 * Date: 2013-07-09
 * </p>
 * 
 * @version 1.0
 */
public class FileUtils {
	private static final Logger log = Logger.getLogger(FileUtils.class
			.getName());
	/**
	 * 判断初始文件是否存在
	 * @param fileName 文件名
	 */
	public static void isExists(String fileName) {
		
		try {
			File file= new File(fileName);
			if (!file.exists()) {
				if(file.createNewFile()){
					//初始化数据文件
					initFile(fileName);
				}else{
					//创建失败
					log.error(new Exception("create file failure"));
				}
			}
		} catch (Exception e) {
			log.error(" *** Exception ***", e);
		}
	}
	/**
	 * 初始化文件
	 * @param fileName 文件名
	 */
	private static void initFile(String fileName){
		
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("auth");
		Element billdata = root.addElement("balance");
		billdata.setText("100");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileWriter(new File(
					fileName)), format);
			writer.write(document); // 输出到文件
			writer.close();
		} catch (IOException e) {
			log.info(" *** IOException *** ",e);
		}
		
	}
	
	/**
	 * 打印日志 
	 * @param fileName 文件名
	 */
	public static void log(String fileName) {
		
		InputStream in;
		try {
			in = new FileInputStream(new File(fileName));
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			String str = null;
			StringBuffer xmlfile = new StringBuffer();
			while ((str = bf.readLine()) != null) {
				xmlfile.append(str);
			}
			log.info(xmlfile.toString());
		} catch (FileNotFoundException e) {
			log.error(" *** FileNotFoundException ***", e);
		} catch (IOException e) {
			log.error(" *** IOException ***", e);
		}
	}
	
	
	public static String convert(String utfString){
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;
		while((i=utfString.indexOf("\\u", pos)) != -1){
			sb.append(utfString.substring(pos, i));
			if(i+5 < utfString.length()){
				pos = i+6;
				sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));
			}
		}
		return sb.toString();
	}
}
