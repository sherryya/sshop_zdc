package com.tmount.business.itov.platform.launch.test;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
public class test {
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException  {
		// TODO Auto-generated method stub
		//查询用户所管辖的所有设备列表
/*		String str1=GetLaunchInfo.getDevicesList();
		System.out.println(URLDecoder.decode(str1,"utf-8"));*/
		//13. 根据应用ID查询设备组信息  
	/*	String str1=GetLaunchInfo.getdevicegrouplistbyappid();
		System.out.println(URLDecoder.decode(str1,"utf-8"));*/
		//根据设备序列号获取设备信息
/*    Map paramMap = new HashMap();//传递body内容
		paramMap.put("devicesn","967790006571");
		paramMap.put("devicetype","golo3CU");	
		String str=GetLaunchInfo.getDevicesInfo(paramMap);
		System.out.println(URLDecoder.decode(str,"utf-8"));*/
	  /*  Map paramMap = new HashMap();//传递body内容
		paramMap.put("deviceuid","7CD54BFA-7847-D032-66A3-A2DD50E1B85A");
		String str=GetLaunchInfo.getDevicesOnlineInfo(paramMap);
		System.out.println(URLDecoder.decode(str,"utf-8"));*/
		//根据设备ID号以及日期（不早于30天）和首尾时刻(一天内，不跨日)，获取该设备的GPS历史轨迹信息   no

/*	    SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//yyyyMMddHHmmssSSS
		String datetime = tempDate.format(new java.util.Date());
		System.out.println(datetime);
	    Map paramMap = new HashMap();//传递body内容
		paramMap.put("deviceuid","57FBCB08-45D5-0F6D-63C0-D57900CD8E13");
		paramMap.put("date","2014-07-16");
		paramMap.put("starttime","00:23:15");
		paramMap.put("endtime","23:36:15");
		String str=GetLaunchInfo.getGPSInfo(paramMap);
		System.out.println(str);
		SimpleDateFormat tempDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String datetime1 = tempDate1.format(new java.util.Date());
		System.out.println(datetime1);*/
		
		

		
		

	   //获取具体某天的统一数据流信息
  	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//yyyyMMddHHmmssSSS
		String datetime = tempDate.format(new java.util.Date());
		System.out.println(datetime);
		Map paramMap = new HashMap();//传递body内容
		paramMap.put("deviceuid","E1ECEF16-27F4-A6E4-6C87-7994DF417CB1");
		paramMap.put("date","2014-05-29");
		paramMap.put("starttime","01:00:01");
		paramMap.put("endtime","23:20:20");
		String str=GetLaunchInfo.GetDFDataStreamInfo(paramMap);
		System.out.println(str);
		SimpleDateFormat tempDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String datetime1 = tempDate1.format(new java.util.Date());
		System.out.println(datetime1);
	
		
		//根据设备ID号查询获取设备最新实时GPS信息  GprsByAccountId.get
/*		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//yyyyMMddHHmmssSSS
		String datetime = tempDate.format(new java.util.Date());
		System.out.println(datetime);
		Map paramMap = new HashMap();//传递body内容
		paramMap.put("deviceuid","57FBCB08-45D5-0F6D-63C0-D57900CD8E13");
		String str=GetLaunchInfo.getCurrentGPSInfo(paramMap);
		System.out.println(str);
		SimpleDateFormat tempDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");*/

		
		
		//获取指定设备当前实时统一数据流信息
/*	    SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//yyyyMMddHHmmssSSS
		String datetime = tempDate.format(new java.util.Date());
		System.out.println(datetime);
		Map paramMap = new HashMap();//传递body内容
		paramMap.put("deviceuid","E1ECEF16-27F4-A6E4-6C87-7994DF417CB1");
		String str=GetLaunchInfo.GetCurrentDFDataStreamInfo(paramMap);
		System.out.println(str);
		SimpleDateFormat tempDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String datetime1 = tempDate1.format(new java.util.Date());
		System.out.println(datetime1);*/
		//获取具体某天的故障码信息
		/*Map paramMap = new HashMap();//传递body内容
		paramMap.put("deviceuid","E1ECEF16-27F4-A6E4-6C87-7994DF417CB1");
		paramMap.put("date","2014-04-30");
		paramMap.put("starttime","01:01:01");
		paramMap.put("endtime","23:20:20");
		String str=GetLaunchInfo.getTroubleCodeInfo(paramMap);
		System.out.println(URLDecoder.decode(str,"utf-8"));*/
		
		//获取指定设备当前实时故障码信息
/*		Map paramMap = new HashMap();//传递body内容
		paramMap.put("deviceuid","E1ECEF16-27F4-A6E4-6C87-7994DF417CB1");
		String str=GetLaunchInfo.getCurrentTroubleCodeInfo(paramMap);
		System.out.println(URLDecoder.decode(str,"utf-8"));*/
		
		//获取里程数据列表  OK

 

/*       Map paramMap = new HashMap();//传递body内容
		paramMap.put("deviceuid","57FBCB08-45D5-0F6D-63C0-D57900CD8E13");
		paramMap.put("date","2014-06-21");

		paramMap.put("starttime","00:00:00");
		paramMap.put("endtime","23:59:59");
		paramMap.put("pagesize","13");
		paramMap.put("targetpage","1");
		String str=GetLaunchInfo.getlistbypage(paramMap);
		System.out.println(str);
		*/
		
		
	//12、根据设备组UID，应用ID 查询设备实时gps信息	  OK
/*		Map paramMap = new HashMap();//传递body内容  dc4d644a-9eb7-11e3-923e-40f2e994e0ba
		paramMap.put("devicegroupuid","dc4d644a-9eb7-11e3-923e-40f2e994e0ba");
		paramMap.put("devicestatus","1");
		paramMap.put("pagesize","10");
		paramMap.put("targetpage","1");
		String str=GetLaunchInfo.getmulitcurrentgpsinfo(paramMap);
		System.out.println(URLDecoder.decode(str,"utf-8"));*/
		
	//14. 根据应用ID,设备组ID查询设备信息	OK
/*		Map paramMap = new HashMap();//传递body内容  dc4d644a-9eb7-11e3-923e-40f2e994e0ba
		paramMap.put("devicegroupuid","dc4d644a-9eb7-11e3-923e-40f2e994e0ba");
		paramMap.put("pagesize","20");
		paramMap.put("targetpage","1");
		String str=GetLaunchInfo.getdevicelistbygroupid(paramMap);
		System.out.println(URLDecoder.decode(str,"utf-8"));*/
		
	//15. 根据设备组UID，应用ID ,设备运行状态查询设备实时统一数据流信息  OK
		/*Map paramMap = new HashMap();//传递body内容  dc4d644a-9eb7-11e3-923e-40f2e994e0ba
		paramMap.put("devicegroupuid","dc4d644a-9eb7-11e3-923e-40f2e994e0ba");
		paramMap.put("apiversionno","1");
		paramMap.put("pagesize","20");
		paramMap.put("targetpage","1");
		paramMap.put("devicestatus","1");
		String str=GetLaunchInfo.getmulitcurrentdfdatastream(paramMap);
		System.out.println(URLDecoder.decode(str,"utf-8"));*/
	}



}
