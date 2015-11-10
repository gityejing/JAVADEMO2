package com.dbutilsTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	public static Connection Connect() {
		Connection conn = null;
		// load driver
		try {
			Class.forName(ConnectInfo.driveClassName);
		} catch (ClassNotFoundException e) {
			System.out.println("load driver failed!");
			e.printStackTrace();
		}

		// connect db
		try {
			conn = DriverManager.getConnection(ConnectInfo.url, ConnectInfo.user, ConnectInfo.password);
		} catch (SQLException e) {
			System.out.println("connect failed!");
			e.printStackTrace();
		}
		return conn;
	}
}
