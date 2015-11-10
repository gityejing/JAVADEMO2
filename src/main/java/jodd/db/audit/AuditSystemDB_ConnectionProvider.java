/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.db.audit;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jodd.db.connection.ConnectionProvider;

/**
 * @author 叶静
 * 省财数据库链接
 */
public class AuditSystemDB_ConnectionProvider implements ConnectionProvider {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AuditSystemDB_ConnectionProvider.class);
    
    public static String driveClassName = "net.sourceforge.jtds.jdbc.Driver";
    public static String url = "jdbc:jtds:sqlserver://192.168.0.128:1433/AuditSystemDB;instance=DEV";
    public static String user = "dev";
    public static String password = "ezoa";
    
    Connection conn = null;

    @Override
    public void close() { // 这个方法没有调用到 
    	logger.info("close");
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection(Connection connection) {
    	logger.info("closeConnection");
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
    	logger.info("getConnection");
        // load driver
        try {
            Class.forName(driveClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("load driver failed!");
            e.printStackTrace();
        }

        // connect db
        try {
            conn = DriverManager.getConnection(url,user, password);
        } catch (SQLException e) {
            System.out.println("connect failed!");
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void init() { // 这个方法没有调用到
    	logger.info("init");
    }

    public void info(String str) {
        System.out.println(str);
    }

}
