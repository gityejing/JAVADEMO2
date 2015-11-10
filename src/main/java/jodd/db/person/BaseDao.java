package jodd.db.person;

import java.sql.Connection;

import javax.naming.InitialContext;

import jodd.db.pool.CoreConnectionPool;

public class BaseDao {
	private static CoreConnectionPool pool = new CoreConnectionPool();
	static{
		pool.setDriver(ConnectInfo.driveClassName);
		pool.setUrl(ConnectInfo.url);
		pool.setUser(ConnectInfo.user);
		pool.setPassword(ConnectInfo.password);
		pool.setMinConnections(100);
		pool.setMaxConnections(200);
		pool.setWaitIfBusy(true);// 设置忙时等待，如果没有足够的链接，就一直等待
		pool.init();
	}
	
	private static ThreadLocal<Connection> connections = new ThreadLocal<Connection>(){
		protected synchronized Connection initialValue() {  
			Connection connection = pool.getConnection();
			connections.set(connection);
			return connection;
		}  
	};

	public static CoreConnectionPool getPool(){
		return pool;
	}
	
	public Connection getConnection(){
		return connections.get();
	}
	
	public Object execute(ConnectionCallback action) {
		Connection con = getConnection();
		try {
			return action.doInConnection(con);
		} finally {
			pool.closeConnection(con);
		}
	}
	
	public void closePool(){
    	if(pool != null) pool.close();
    }
}
