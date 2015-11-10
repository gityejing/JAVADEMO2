package com.dbutilsTest;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class WebInfoDao {
	public static void main(String[] args) {
		try {
//			insert_test();
			update_test();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insert_test() throws SQLException {
		Connection conn = ConnectDB.Connect();
		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();
		// 执行SQL插入
		int n = qRunner.update(conn,
				" insert into tblWebinfo(fid,fkey,fvalue,ftype,flable) " +
				" values(17,'NIANDU',NULL,'systemStuep','2014')");
		System.out.println("成功插入" + n + "条数据！");
		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}
	
	static void update_test() throws SQLException {
		Connection conn = ConnectDB.Connect();

		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();
		// 执行SQL插入
		String sql = "update tblWebinfo set ftype = 'systemStuep' where fid = 17";
		sql = "update tblWebinfo set flable = '2015' where fid = 17";
		int n = qRunner.update(conn,sql);
		System.out.println("成功更新" + n + "条数据！");

		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}
}
