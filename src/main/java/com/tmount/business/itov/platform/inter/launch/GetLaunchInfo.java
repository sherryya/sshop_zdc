package com.tmount.business.itov.platform.inter.launch;



import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.tmount.business.itov.platform.launch.config.LaunchConfig;
import com.tmount.business.itov.platform.launch.util.LaunchNotify;
import com.tmount.business.itov.platform.launch.util.UtilDate;


public class GetLaunchInfo {
	
	/**
	 * 根据应用ID号查询获得对应设备列表信息  
	 * @return
	 */
	public static String getDevicesList() {
		String strDate = UtilDate.getOrderNum();
		Map<String, String> params = new HashMap<String, String>();
		String apiversionno=LaunchConfig.apiversionno;
		params.put("methodname", "getdeviceslist");
		params.put("appid", LaunchConfig.partner);
		//params.put("apiversionno", apiversionno);
		//params.put("datetimestamp", strDate);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		System.out.println(md5);
		System.out.println(System.currentTimeMillis());
		//String url = LaunchConfig.launch_url+ "apiversionno="+LaunchConfig.apiversionno+"&appid="+LaunchConfig.partner+"&datetimestamp=" + strDate + "&methodname=GetDevicesList&sign=" + md5;
		String url = LaunchConfig.launch_url+"appid="+LaunchConfig.partner+"&methodname=getdeviceslist&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		System.out.println(json);
		return json;
	}
	/**
	 * 根据应用ID号，设备序列号和设备类型查询获得对应设备信息  ok
	 * @return
	 */
	public static String getDevicesInfo(Map params) {
	/*	params.put("methodname", "GetDevicesInfo");
		params.put("appid", LaunchConfig.partner);
		String devicesn = (String)params.get("devicesn");
		String devicetype = (String)params.get("devicetype");
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "appid="+LaunchConfig.partner+"&devicesn="+devicesn+"&devicetype="+devicetype+"&methodname=GetDevicesInfo&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		*/
		params.put("methodname", "getdevicesinfo");
		params.put("appid", LaunchConfig.partner);
		String devicesn = (String)params.get("devicesn");
		String devicetype = (String)params.get("devicetype");
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "appid="+LaunchConfig.partner+"&devicesn="+devicesn+"&devicetype="+devicetype+"&methodname=getdevicesinfo&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
		//{"Status":"1","Message":"","ErrorCode":"","Data":{"DeviceUID":"04581e04-7fdd-e211-8c19-00163e0071c8","DeviceType":"RCU-G","DeviceSNAndType":"966290001369(RCU-G)","DeviceSN":"966290001369","DeviceTimeZone":"E8","CarSeriesCode":null}}
	}
	/**
	 * 根据应用ID号，设备UID查询设备在线信息  ok
	 * @return
	 */
	public static String getDevicesOnlineInfo(Map params) {
		
		/*String methodname="GetDevicesOnlineInfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		//String deviceuid=LaunchConfig.deviceuid;;
		String deviceuid =(String) params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		params.put("deviceuid",deviceuid );
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&datetimestamp="+datetimestamp+"&methodname=GetDevicesOnlineInfo&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		*/
		String methodname="getdevicesonlineinfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String deviceuid =(String) params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("deviceuid",deviceuid );
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&methodname=getdevicesonlineinfo&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
		//{"Status":"1","Message":"","ErrorCode":"","Data":""}
	}
	/**
	 * 根据设备ID号以及日期（不早于30天）和首尾时刻(一天内，不跨日)，获取该设备的GPS历史轨迹信息   no
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getGPSInfo(Map params) throws UnsupportedEncodingException {
/*		String methodname="GetGPSInfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		//String deviceuid=LaunchConfig.deviceuid;;
		String deviceuid=(String)params.get("deviceuid");

		String querydate=(String)params.get("date");
		String starttime=(String)params.get("starttime");
		String endtime=(String)params.get("endtime");

		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		params.put("deviceuid", deviceuid);
		params.put("date", querydate);
		params.put("starttime", starttime);
		params.put("endtime", endtime);
		String md5 = LaunchNotify.getSignVeryfy1(params);//java.net.URLEncoder.encode("m=中文&b=....","utf-8")
		String url = LaunchConfig.launch_url+ "endtime="+endtime+"&starttime="+starttime+"&date="+querydate+
				    "&apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&datetimestamp="+
				datetimestamp+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);*/
		String methodname="getgpsinfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String deviceuid=(String)params.get("deviceuid");
		String querydate=(String)params.get("date");
		String starttime=(String)params.get("starttime");
		String endtime=(String)params.get("endtime");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("deviceuid", deviceuid);
		params.put("date", querydate);
		params.put("starttime", starttime);
		params.put("endtime", endtime);
		String md5 = LaunchNotify.getSignVeryfy1(params);//java.net.URLEncoder.encode("m=中文&b=....","utf-8")
		String url = LaunchConfig.launch_url+ "endtime="+endtime+"&starttime="+starttime+"&date="+querydate+
				    "&apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}

	/**
	 * 根据设备ID号查询获取设备最新实时GPS信息  ok
	 * @return
	 */
	public static String getCurrentGPSInfo(Map params) {
		String methodname="GetCurrentGPSInfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		//String deviceuid=LaunchConfig.deviceuid;;
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		params.put("deviceuid", deviceuid);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&datetimestamp="+datetimestamp+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		/*
		String methodname="getcurrentgpsinfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("deviceuid", deviceuid);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		*/
		return json;
	}

    /**
     * 根据设备ID号，以及指定日期（不早于30天）和首尾时刻 (一天内，不跨日)首尾时刻，获取该设备的历史故障码信息列表   no
     * @return
     */
	public static String getTroubleCodeInfo(Map params) {
/*		String methodname="GetTroubleCodeInfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String querydate=(String)params.get("date");
		String starttime=(String)params.get("starttime");
		String endtime=(String)params.get("endtime");
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		params.put("deviceuid", deviceuid);
		params.put("date", querydate);
		params.put("starttime", starttime);
		params.put("endtime", endtime);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "endtime="+endtime+"&starttime="+starttime+"&date="+querydate+
				    "&apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&datetimestamp="+
				datetimestamp+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);*/
		String methodname="gettroublecodeinfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String querydate=(String)params.get("date");
		String starttime=(String)params.get("starttime");
		String endtime=(String)params.get("endtime");
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("deviceuid", deviceuid);
		params.put("date", querydate);
		params.put("starttime", starttime);
		params.put("endtime", endtime);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "endtime="+endtime+"&starttime="+starttime+"&date="+querydate+
				    "&apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
		//{"Status":"1","Message":"","ErrorCode":"","Data":""}
	}
    /**
     * 根据设备ID号查询获取指定设备最新故障码信息    ok
     * @return
     */
	public static String getCurrentTroubleCodeInfo(Map params) {
/*		String methodname="GetCurrentTroubleCodeInfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		params.put("deviceuid", deviceuid);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&datetimestamp="+datetimestamp+"&methodname="+methodname+"&sign=" + md5;
		String json = LaunchNotify.checkUrl(url);*/
		String methodname="getcurrenttroublecodeinfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("deviceuid", deviceuid);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&methodname="+methodname+"&sign=" + md5;
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
	/**
	 * 根据设备ID号以及应用ID号查询获得对应设备支持系统数据流列表  ok
	 * @return
	 */
	public static String getDeviceSysDataStreamList(Map params) {
		String methodname="GetDeviceSysDataStreamList";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		params.put("deviceuid", deviceuid);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&datetimestamp="+datetimestamp+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
		//{"Status":"1","Message":"","ErrorCode":"","Data":""}
	}
	/**
	 * 根据设备ID号以及应用ID号查询获得对应设备支持系统列表  ok
	 * @return
	 */
	public static String getsyscansupportedbydevice(Map params) {
		String methodname="getsyscansupportedbydevice";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		params.put("deviceuid", deviceuid);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&datetimestamp="+datetimestamp+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
		//{"Status":"1","Message":"","ErrorCode":"","Data":""}
	}
	
	/**
	 * 根据设备ID号，以及指定日期（不早于30天）和首尾时刻 (一天内，不跨日)首尾时刻，查询获得对应设备的里程信息列表   ok
	 * @return
	 */
	public static String getlistbypage(Map params) {
/*		String methodname="triprecord.getlistbypage";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String querydate=(String)params.get("date");
		String starttime=(String)params.get("starttime");
		String endtime=(String)params.get("endtime");
		String deviceuid=(String)params.get("deviceuid");
		String pagesize=(String)params.get("pagesize");
		String targetpage=(String)params.get("targetpage");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "pagesize="+pagesize+"&targetpage="+targetpage+"&endtime="+endtime+"&starttime="+starttime+"&date="+querydate+
				    "&apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&datetimestamp="+
				datetimestamp+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);*/
		
		String methodname="triprecord.getlistbypage";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String querydate=(String)params.get("date");
		String starttime=(String)params.get("starttime");
		String endtime=(String)params.get("endtime");
		String deviceuid=(String)params.get("deviceuid");
		String pagesize=(String)params.get("pagesize");
		String targetpage=(String)params.get("targetpage");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "pagesize="+pagesize+"&targetpage="+targetpage+"&endtime="+endtime+"&starttime="+starttime+"&date="+querydate+
				    "&apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
	
	/**
	 *   ok
	 * @return
	 */
	public static String getdatelistin30days(Map params) {
		String methodname="triprecord.getlistbypage";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("deviceuid", deviceuid);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&methodname="+methodname+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
	
	
	/**
	 * 根据应用ID号查询设备组信息
	 * @return
	 */
	public static String getdevicegrouplistbyappid() {
/*		Map<String, String> params = new HashMap<String, String>();
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp = UtilDate.getOrderNum();
		String methodname="getdevicegrouplistbyappid";
		params.put("methodname", methodname);
		params.put("appid", LaunchConfig.partner);
		params.put("apiversionno",apiversionno);
		params.put("datetimestamp",datetimestamp);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "methodname="+methodname+"&appid="+LaunchConfig.partner+"&apiversionno="+apiversionno+"&datetimestamp="+datetimestamp+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);*/
		Map<String, String> params = new HashMap<String, String>();
		String methodname="getdevicegrouplistbyappid";
		params.put("methodname", methodname);
		params.put("appid", LaunchConfig.partner);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "methodname="+methodname+"&appid="+LaunchConfig.partner+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
	/**
	 * 根据应用ID号和设备组ID号查询获得对应设备列表信息（支持分页）
	 * @return
	 */
	public static String getdevicelistbygroupid(Map params) {
/*		String devicegroupuid  = (String)params.get("devicegroupuid");
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp = UtilDate.getOrderNum();
		String methodname="getdevicelistbygroupid";
		params.put("methodname", methodname);
		params.put("appid", LaunchConfig.partner);
		params.put("devicegroupuid",devicegroupuid);
		params.put("apiversionno",apiversionno);
		params.put("datetimestamp",datetimestamp);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "methodname="+methodname+"&appid="+LaunchConfig.partner+"&devicegroupuid="+devicegroupuid+"&apiversionno="+apiversionno+"&datetimestamp="+datetimestamp+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);*/
		
		String devicegroupuid  = (String)params.get("devicegroupuid");
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp = UtilDate.getOrderNum();
		String methodname="getdevicelistbygroupid";
		String pagesize=(String)params.get("pagesize");
		String targetpage=(String)params.get("targetpage");
		params.put("methodname", methodname);
		params.put("appid", LaunchConfig.partner);
		params.put("devicegroupuid",devicegroupuid);
		params.put("pagesize",pagesize);
		params.put("targetpage",targetpage);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "methodname="+methodname+"&appid="+LaunchConfig.partner+"&devicegroupuid="+devicegroupuid+"&targetpage="+targetpage+"&pagesize="+pagesize+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
	
	
	/**
	 * 根据设备组ID号，应用ID号和设备运行状态批量查询获取设备最新实时GPS信息（支持分页）
	 * @return
	 */
	public static String getmulitcurrentgpsinfo(Map params) {	
/*		String methodname="getmulitcurrentgpsinfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String devicegroupuid=(String)params.get("devicegroupuid");
		String pagesize=(String)params.get("pagesize");
		String targetpage=(String)params.get("targetpage");
		String devicestatus = (String)params.get("devicestatus");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+ "pagesize="+pagesize+"&targetpage="+targetpage+"&apiversionno="+apiversionno+"&appid="+appid+"&devicegroupuid="+devicegroupuid+"&datetimestamp="+
				datetimestamp+"&methodname="+methodname+"&devicestatus="+devicestatus+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);*/
		String methodname="getmulitcurrentgpsinfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		
		String devicegroupuid=(String)params.get("devicegroupuid");
		String pagesize=(String)params.get("pagesize");
		String targetpage=(String)params.get("targetpage");
		String devicestatus = (String)params.get("devicestatus");
		if (devicestatus.equalsIgnoreCase("0")) {
			params.remove("devicestatus");
			devicestatus = "";
		}
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		
		String url = LaunchConfig.launch_url+ "pagesize="+pagesize+"&targetpage="+targetpage+"&apiversionno="+apiversionno+"&appid="+appid+"&devicegroupuid="+devicegroupuid+
				"&methodname="+methodname+"&devicestatus="+devicestatus+"&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
	
	/**
	 * 根据设备组ID号，应用ID号和设备运行状态批量查询获取设备最新实时GPS信息（支持分页）
	 * @return
	 */
	public static String GetDFDataStreamInfo(Map params) {	
/*		String methodname="GetDFDataStreamInfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String querydate=(String)params.get("date");
		String starttime=(String)params.get("starttime");
		String endtime=(String)params.get("endtime");
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+"date="+querydate+"&apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+"&datetimestamp="+
				datetimestamp+"&methodname="+methodname+"&starttime="+starttime+"&endtime="+endtime+"&sign="+md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);*/
		String methodname="getdfdatastreaminfo";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String querydate=(String)params.get("date");
		String starttime=(String)params.get("starttime");
		String endtime=(String)params.get("endtime");
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+"date="+querydate+"&apiversionno="+apiversionno+"&appid="+appid+"&deviceuid="+deviceuid+
				"&methodname="+methodname+"&starttime="+starttime+"&endtime="+endtime+"&sign="+md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
	
	public static String GetCurrentDFDataStreamInfo(Map params) {
/*		String strDate = UtilDate.getOrderNum();
		String apiversionno=LaunchConfig.apiversionno;
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname", "GetCurrentDFDataStreamInfo");
		params.put("appid", LaunchConfig.partner);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", strDate);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		System.out.println(md5);
		String url = LaunchConfig.launch_url+"apiversionno="+LaunchConfig.apiversionno+"&appid="+LaunchConfig.partner+"&datetimestamp="
				+strDate+"&deviceuid="+deviceuid+"&methodname=GetCurrentDFDataStreamInfo&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);*/
		String strDate = UtilDate.getOrderNum();
		String apiversionno=LaunchConfig.apiversionno;
		String deviceuid=(String)params.get("deviceuid");
		params.put("methodname", "getcurrentdfdatastreaminfo");
		params.put("appid", LaunchConfig.partner);
		params.put("apiversionno", apiversionno);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		System.out.println(md5);
		String url = LaunchConfig.launch_url+"apiversionno="+LaunchConfig.apiversionno+"&appid="+LaunchConfig.partner+"&deviceuid="+deviceuid+"&methodname=getcurrentdfdatastreaminfo&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		System.out.println(json);
		return json;
	}
	
	
	
	public static String getmulitcurrentdfdatastream (Map params) {	
/*		String methodname="getmulitcurrentdfdatastream";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String datetimestamp=UtilDate.getOrderNum();
		String devicegroupuid=(String)params.get("devicegroupuid");
		String devicestatus=(String)params.get("devicestatus");
		String pagesize=(String)params.get("pagesize");
		String targetpage=(String)params.get("targetpage");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		params.put("datetimestamp", datetimestamp);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+"devicegroupuid="+devicegroupuid+"&apiversionno="+apiversionno+"&appid="+appid+"&devicestatus="+devicestatus+"&datetimestamp="+
				datetimestamp+"&methodname="+methodname+"&pagesize="+pagesize+"&targetpage="+targetpage+"&sign="+md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);*/
		
		String methodname="getmulitcurrentdfdatastream";
		String appid=LaunchConfig.partner;
		String apiversionno=LaunchConfig.apiversionno;
		String devicegroupuid=(String)params.get("devicegroupuid");
		String pagesize=(String)params.get("pagesize");
		String targetpage=(String)params.get("targetpage");
		String devicestatus=(String)params.get("devicestatus");
		params.put("methodname",methodname);
		params.put("appid", appid);
		params.put("apiversionno", apiversionno);
		String md5 = LaunchNotify.getSignVeryfy1(params);
		String url = LaunchConfig.launch_url+"devicegroupuid="+devicegroupuid+"&apiversionno="+apiversionno+"&appid="+appid+"&methodname="+methodname+"&pagesize="+pagesize+"&targetpage="+targetpage+"&devicestatus="+devicestatus+"&sign="+md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
	public static void main(String[] args) {
		new GetLaunchInfo().getDevicesList();
	}
}
