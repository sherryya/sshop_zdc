package com.tmount.business.itov.platform.launch.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnDB {

	private Connection conn = null;
	public Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			
			
			
		}
		String url = "jdbc:mysql://localhost/test?user=root&password=mysql&useUnicode=true&characterEncoding=gb2312"; // myDBΪ��ݿ���
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
