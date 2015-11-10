package com.dbutilsTest;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0ConnectionFactory {

	private C3p0ConnectionFactory() {}

	private static ComboPooledDataSource ds = null;

	static {
		try {
			ds = new ComboPooledDataSource();
			// 设置JDBC的Driver类
			ds.setDriverClass(ConnectInfo.driveClassName); // 参数由 Config
			// 设置JDBC的URL
			ds.setJdbcUrl(ConnectInfo.url);
			// 设置数据库的登录用户名
			ds.setUser(ConnectInfo.user);
			// 设置数据库的登录用户密码
			ds.setPassword(ConnectInfo.password);
			// 设置连接池的最大连接数
			ds.setMaxPoolSize(200);
			// 设置连接池的最小连接数
			ds.setMinPoolSize(20);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public static synchronized Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return con;
	}
	// C3P0 end
}