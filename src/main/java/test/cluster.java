package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cluster {
	public Connection getConnection() {
		Connection conn = null;
		try {
			//conn = DriverManager.getConnection("jdbc:mysql:loadbalance://218.8.127.3:10094/test?roundRobinLoadBalance=true","zdc", "zdc@123");
			
			conn = DriverManager.getConnection("jdbc:mysql:loadbalance://192.168.230.224:3306/test?roundRobinLoadBalance=true","zdc", "zdc@123");
			return conn;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void getDBParameterMetaData() throws SQLException {
		Connection conn = getConnection(); // id,name
		PreparedStatement pre = conn.prepareStatement("SELECT * FROM `test`.`city` where id = ?");
		pre.setInt(1, 2);
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1) + "___" + rs.getString(2));
		}
		rs.close();
	}
	public static void main(String[] args) throws SQLException {
	
		new cluster().getDBParameterMetaData();
	}
}
