package com.dbutilsTest;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class TblPeixunItemDao {
	public static void main(String[] args) {
		try {
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
		String sql = "update TblPeixunItem set fitemName = '2015年全国建设工程造价员(水利)继续教育培训' where fsn = 9";
		int n = qRunner.update(conn,sql);
		System.out.println("成功更新" + n + "条数据！");

		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}
}
