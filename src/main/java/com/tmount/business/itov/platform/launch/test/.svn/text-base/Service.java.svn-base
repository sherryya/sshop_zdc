package com.tmount.business.itov.platform.launch.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tmount.business.itov.platform.inter.beans.DevicesList;
import com.tmount.business.itov.platform.launch.util.ConnDB;



public class Service {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
    /**
     * 添加设备信息列表
     * @param arr_dl
     * @return
     * @throws SQLException
     */
	public Boolean addDeviceList(ArrayList<DevicesList> arr_dl)
			throws SQLException {
		Boolean f = true;
		try {
			conn = new ConnDB().getConn();
			for (DevicesList dl : arr_dl) {
				System.out.println(dl.getDeviceSN()+"---"+dl.getDeviceUID());
				String sql="";
				if(isExists(dl.getDeviceUID(), "deviceslist", "DeviceUID"))
				{
					sql="update  deviceslist  set DeviceType= '"
							+ dl.getDeviceType()
							+ "',DeviceSNAndType='"
							+ dl.getDeviceSNAndType()
							+ "',DeviceSN='"
							+ dl.getDeviceSN()
							+ "',DeviceTimeZone='"
							+ dl.getDeviceTimeZone()
							+ "',CarSeriesCode='"
							+ dl.getCarSeriesCode() + "'  where DeviceUID='"+dl.getDeviceUID()+"'";
				}
				else
				{
					sql="insert into deviceslist  values ('"
							+ dl.getDeviceUID()
							+ "','"
							+ dl.getDeviceType()
							+ "','"
							+ dl.getDeviceSNAndType()
							+ "','"
							+ dl.getDeviceSN()
							+ "','"
							+ dl.getDeviceTimeZone()
							+ "','"
							+ dl.getCarSeriesCode() + "')";
				}
				pstmt = conn.prepareStatement(sql);
				f = pstmt.execute();
				System.out.println(f);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dispose();
		}
		return f;
	}
	/**
	 * 判断是否存在记录
	 * @param id
	 * @param table
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public boolean isExists(String id, String table,String column)
			throws SQLException {
		boolean f = false;
		try {
			conn = new ConnDB().getConn();
			pstmt = conn.prepareStatement("select 1 from "+table+" where "+column+"='"+ id + "'");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				f=true;
			} else {
				f = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return f;
	}

	public void dispose() throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
		if (rset != null) {
			rset.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}
