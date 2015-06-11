package com.tmount.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatatimeUtil {
	 /**
	    * 取得系统当前时间
	    * @return String yyyy-mm-dd
	    */
	   public static String getCurrentDate() {
	     Calendar rightNow = Calendar.getInstance();
	     int year = rightNow.get(rightNow.YEAR);
	     int month = rightNow.get(rightNow.MONTH) + 1;
	     int day = rightNow.get(rightNow.DATE);
	     return year + "-" + month + "-" + day;
	   }
	   /**
	    * 取得系统当前时间
	    * @return String yyyy年mm月dd日
	    */
	   public static String getCurrentDate1() {
	     Calendar rightNow = Calendar.getInstance();
	     int year = rightNow.get(rightNow.YEAR);
	     int month = rightNow.get(rightNow.MONTH) + 1;
	     int day = rightNow.get(rightNow.DATE);
	     return year + "年" + month + "月" + day + "日";
	   }
	   /**
	    * 取得系统当前时间
	    * @return String yyyymmdd
	    */
	   public String getCurrentDate2() {
	     Calendar rightNow = Calendar.getInstance();
	     int year = rightNow.get(rightNow.YEAR);
	     int month = rightNow.get(rightNow.MONTH) + 1;
	     int day = rightNow.get(rightNow.DATE);
	     return year + "" + month + "" + day;
	   }
	   /**
	    * 取得系统当前时间
	    * @return String yyyy-mm
	    */
	   public String getCurrentDate3() {
	     Calendar rightNow = Calendar.getInstance();
	     int year = rightNow.get(rightNow.YEAR);
	     int month = rightNow.get(rightNow.MONTH) + 1;
	     //int day = rightNow.get(rightNow.DATE);
	     return year + "-" + month;
	   }
	   /**
	    * 取得系统当前时间
	    * @return String yyyyMMddhhmmss
	    */
	   public String getCurrentDate4() {
	      Calendar c = Calendar.getInstance();
	          c.add(c.SECOND, 0);
	          return "" + c.get(c.YEAR) + "" + (c.get(c.MONTH) + 1) + "" + c.get(c.DATE)+""+c.get(c.HOUR_OF_DAY)+""+c.get(c.MINUTE)+""+c.get(c.SECOND);

	}
	   /**
	    * 取得系统当前时间
	    * @return String yyyy-MM-dd hh:mm:ss
	    */
	/* public String getCurrentDate5() {
	     SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	     java.util.Date date = new java.util.Date();
	     String time = simpleDateFormat.format(date);
	     return time;
	   }
	*/
	/**
	* 取得系统当前时间
	* @return String yyyy-MM-dd hh:mm:ss
	*/
	     public String getCurrentDate5(){
	         Calendar c = Calendar.getInstance();
	         c.add(c.SECOND, 0);
	         return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE)+" "+c.get(c.HOUR_OF_DAY)+":"+c.get(c.MINUTE)+":"+c.get(c.SECOND);
	       }
	       /**
	        * 取得系统当前时间 常用于订单号
	        * @return String yyyyMMddhhmmss
	        */
	       public String getCurrentDate6() {
	          Calendar c = Calendar.getInstance();
	              c.add(c.SECOND, 0);
	              String year=c.get(c.YEAR)+"";
	              return "" + year.subSequence(2,year.length())+ "" + (c.get(c.MONTH) + 1) + "" + c.get(c.DATE)+"-"+c.get(c.HOUR_OF_DAY)+""+c.get(c.MINUTE)+""+c.get(c.SECOND)+"-"+c.get(c.MILLISECOND);

	     }
	   /**
	    * 取得系统当前时间前n个月的相对应的一天
	    * @param n int
	    * @return String yyyy-mm-dd
	    */
	   public String getNMonthBeforeCurrentDay(int n) {
	     Calendar c = Calendar.getInstance();
	     c.add(c.MONTH, -n);
	     return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);

	   }

	   /**
	    * 取得系统当前时间后n个月的相对应的一天
	    * @param n int
	    * @return String   yyyy-mm-dd
	    */
	   public String getNMonthAfterCurrentDay(int n) {
	     Calendar c = Calendar.getInstance();
	     c.add(c.MONTH, n);
	     return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);

	   }

	   /**
	    * 取得系统当前时间前n天
	    * @param n int
	    * @return String yyyy-mm-dd
	    */
	   public String getNDayBeforeCurrentDate(int n) {
	     Calendar c = Calendar.getInstance();
	     c.add(c.DAY_OF_MONTH, -n);
	     return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);
	   }

	   /**
	    * 取得系统当前时间后n天
	    * @param n int
	    * @return String yyyy-mm-dd
	    */
	   public String getNDayAfterCurrentDate(int n) {
	     Calendar c = Calendar.getInstance();
	     c.add(c.DAY_OF_MONTH, n);
	     return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);
	   }

	   //---------------------------------------------------------------------
	   //取过去一个时间对应的系统当年的一天
	   public String getCurrentDateAfterPastDate(String sPastDate) {
	     if (sPastDate != null && !sPastDate.equals("")) {
	       Date date = switchStringToDate(sPastDate);
	       Calendar c = Calendar.getInstance();
	       c.setTime(date);
	       int iPastYear = c.get(c.YEAR);
	       Calendar c1 = Calendar.getInstance();
	       int iCurrentYear = c1.get(c1.YEAR);
	       c.add(c.YEAR, iCurrentYear - iPastYear);
	       return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" +
	           c.get(c.DATE);
	     }
	     else {
	       return null;
	     }
	   }

	   /**
	    * 将一个日期字符串转化成日期
	    * @param sDate String
	    * @return Date yyyy-mm-dd
	    */
	   public Date switchStringToDate(String sDate) {
	     Date date = null;
	     try {
	       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	       date = df.parse(sDate);

	     }
	     catch (Exception e) {
	       System.out.println("日期转换失败:" + e.getMessage());
	     }
	     return date;
	   }

	   /**
	    * 输入两个字符串型的日期，比较两者的大小
	    * @param fromDate String
	    * @param toDate String
	    * @return boolean before为true
	    */
	   public boolean compareTwoDateBigOrSmall(String fromDate, String toDate) {
	     Date dateFrom = this.switchStringToDate(fromDate);
	     Date dateTo = this.switchStringToDate(toDate);
	     if (dateFrom.before(dateTo)) {
	       return true;
	     }
	     else {
	       return false;
	     }
	   }

	   /**
	    * 将一个日期字符串转化成Calendar
	    * @param sDate String
	    * @return Calendar
	    */
	   public Calendar switchStringToCalendar(String sDate) {
	     Date date = switchStringToDate(sDate);
	     Calendar c = Calendar.getInstance();
	     c.setTime(date);
	     return c;
	   }

	   /**
	    * 将一个日期转化成Calendar
	    * @param date Date
	    * @return Calendar
	    */
	   public Calendar switchStringToCalendar(Date date) {
	     Calendar c = Calendar.getInstance();
	     c.setTime(date);
	     return c;
	   }

	   public   String string2Date(String dateString)
	   throws java.lang.Exception {
	     /*DateFormat dateFormat;
	     dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	     dateFormat.setLenient(false);
	     java.util.Date timeDate = dateFormat.parse(dateString);//util类型
	     //java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql类型
	     java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp类型
	     */
	     SimpleDateFormat newk = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
	    SimpleDateFormat old = new SimpleDateFormat("MMM dd hh:mm:ss yyyy");

	    String strDate = "Mar 22 00:42:00 2002";
	    Date d = old.parse(strDate);
	    System.out.println(newk.format(d));

	     return newk.format(d);
	   }



	   /**
	    * 取得某个时间前n个月的相对应的一天
	    * @param sDate String
	    * @param n int
	    * @return String yyyy-mm-dd
	    */
	   public String getNMonthBeforeOneDay(String sDate, int n) {
	     Calendar c = switchStringToCalendar(sDate);
	     c.add(c.MONTH, -n);
	     return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);

	   }

	   //取得某个时间后n个月的相对应的一天
	   public String getNMonthAfterOneDay(String sDate, int n) {
	     Calendar c = switchStringToCalendar(sDate);
	     c.add(c.MONTH, n);
	     return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);

	   }

	   //取得某个时间前n天,格式为yyyy-mm-dd
	   public String getNDayBeforeOneDate(String sDate, int n) {
	     Calendar c = switchStringToCalendar(sDate);
	     c.add(c.DAY_OF_MONTH, -n);
	     return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);
	   }

	   //取得某个时间后n天,格式为yyyy-mm-dd
	   public String getNDayAfterOneDate(String sDate, int n) {
	     Calendar c = switchStringToCalendar(sDate);
	     c.add(c.DAY_OF_MONTH, n);
	     return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);
	   }

	   //判断系统当前时间是不是润年
	   public boolean isRunNian() {
	     java.util.Calendar rightNow = java.util.Calendar.getInstance();
	     int year = rightNow.get(rightNow.YEAR);
	     if (0 == year % 4 && (year % 100 != 0 || year % 400 == 0)) {
	       return true;
	     }
	     else {
	       return false;
	     }

	   }

	   public static void main(String args[]) {
	    //DateTime a = new DateTime();
		   DatatimeUtil a=new DatatimeUtil();
		 System.out.println(a.getCurrentDate1());  ;
	     try{
	     //  System.out.println(a.getCurrentDate6());
	     //  System.out.println(a.getCurrentDate6());
	     }catch(Exception e){System.err.println();}
	   }


}
