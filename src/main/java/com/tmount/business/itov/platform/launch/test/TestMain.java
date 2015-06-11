package com.tmount.business.itov.platform.launch.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

import com.tmount.business.itov.platform.inter.json.JieXiJson;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.business.itov.platform.launch.util.LaunchCore;



public class TestMain {
	/**
	 * 得到设备列表
	 * @return
	 */
	public static boolean TestDevicesList()
	{
		Service sv=new Service();
		String json= GetLaunchInfo.getDevicesList();
		System.out.println(System.currentTimeMillis());
	   // System.out.println(URLDecoder.decode(json,"utf-8"));
		Boolean b=false;
		try {
			b = sv.addDeviceList( JieXiJson.getDevicelist(URLDecoder.decode(json,"utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			LaunchCore.logResult(URLDecoder.decode(json,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return b;
	}
	
}
