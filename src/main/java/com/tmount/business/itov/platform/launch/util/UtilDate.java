
package com.tmount.business.itov.platform.launch.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class UtilDate {
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
	
    
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	
	
	public static boolean compareToDate(String date,String starttime,String endtime){
		boolean flag =false;
		try{
			SimpleDateFormat sdf_all = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			sdf_all.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			Date startTime = sdf_all.parse(date + " " + starttime);
			Date endTime = sdf_all.parse(date + " " + endtime);
			if(startTime.before(endTime)){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public static Date StringToDate(String dateStr){
		String formatStr="yyyy-MM-dd HH:mm:ss";
		DateFormat dd=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	   * 获取现在时间
	   * 
	   * @return 返回短时间字符串格式yyyy-MM-dd
	   */
	public static String getStringDateShort(String date) {
	   Date currentTime = new Date();
	   currentTime=StringToDate(date);
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	   String dateString = formatter.format(currentTime);
	   return dateString;
	}
	/**
	   * 获取时间 小时:分;秒 HH:mm:ss
	   * 
	   * @return
	   */
	public static String getTimeShort(String date) {
	   SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	   Date currentTime = new Date();
	   currentTime=StringToDate(date);
	   String dateString = formatter.format(currentTime);
	   return dateString;
	}
	
}
