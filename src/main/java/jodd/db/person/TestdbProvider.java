/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.db.person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jodd.db.connection.ConnectionProvider;

/**
 *
 * @author Administrator
 */
class TestdbProvider implements ConnectionProvider{

    Connection conn = null;

    @Override
    public void close() { // 这个方法没有调用到 
        info("close");
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
        info("closeConnection");
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
        info("getConnection");
        // load driver
        try {
            Class.forName(ConnectInfo.driveClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("load driver failed!");
            e.printStackTrace();
        }
        
        // connect db
        try {
            conn = DriverManager.getConnection(ConnectInfo.url,
                    ConnectInfo.user, ConnectInfo.password);
        } catch (SQLException e) {
            System.out.println("connect failed!");
            e.printStackTrace();
        }
        return conn;
    }

    
    @Override
    public void init() { // 这个方法没有调用到
        info("init");
    }

    public void info(String str){
        System.out.println(str);
    }
}
