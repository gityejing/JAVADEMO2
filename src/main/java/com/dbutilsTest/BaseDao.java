package com.dbutilsTest;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class BaseDao {
	
	protected String tableName;

	public BaseDao(String tableName) {
		super();
		this.tableName = tableName;
	}
	
	public BaseDao() {
		super();
	}
	
	public int insert() throws SQLException{
		Connection conn = C3p0ConnectionFactory.getConnection();
		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();
		// 执行SQL插入
		int n = qRunner.update(conn,
				" insert into tblUser(userId,userName,passWord,userType,qxzId,lasttime,ctime) " +
				" values('000000000008','hello','9b4b5ac88c4aacc0df2dae07ebb0794e','0','000000000001','2013-10-17 08:10:34.233','1900-01-01 12:00:00.000')");
		System.out.println("成功插入" + n + "条数据！");
		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
		return n;
	}
	
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
