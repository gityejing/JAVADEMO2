package jodd.db.person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jodd.db.DbQuery;
import jodd.db.DbSession;
import jodd.db.DbThreadSession;
import jodd.db.DbTransactionMode;
import jodd.db.ThreadDbSessionProvider;
import jodd.db.oom.DbOomManager;
import jodd.db.oom.DbOomQuery;
import jodd.db.oom.sqlgen.DbSqlBuilder;
import jodd.db.pool.CoreConnectionPool;

public class DBoomTest {
	private static Logger logger = Logger.getLogger(DBoomTest.class);
    public static void main(String[] args) {
        testQury();
    }
    
    
    CoreConnectionPool pool = null;
    @Before
    public void initPool(){
    	pool = new CoreConnectionPool();
    	pool.setDriver(ConnectInfo.driveClassName);
    	pool.setUrl(ConnectInfo.url);
    	pool.setUser(ConnectInfo.user);
    	pool.setPassword(ConnectInfo.password);
    	pool.setMinConnections(100);
    	pool.setMaxConnections(200);
    	pool.setWaitIfBusy(true);//设置忙时等待，如果没有足够的链接，就一直等待
    	pool.init();
    }
    
    @After
    public void closePool(){
    	if(pool != null) pool.close();
    }
    
    @Test
    public void testPool(){
    	for (int i = 0; i < 300; i++) {
    		Connection connection = pool.getConnection();
    		System.out.println("初始化数据库链接池成功！"+pool.getConnectionsCount());
			pool.closeConnection(connection);// 用完就关闭，放回到连接池中，可以重复的使用连接池中的链接
		}
    }
    
    public Object execute(ConnectionCallback action){  
        Connection con = pool.getConnection();  
        try {  
             return action.doInConnection(con);  
         } finally {  
             pool.closeConnection(con);
         }  
    }  
    
    public static void testQury() {
        Connection connection = ConnectDB.Connect();
        DbQuery query = new DbQuery(connection, " select * from tblproject ");
//	    query.executeUpdate();
//	    query.close();              // or just: query.autoClose().executeUpdate();
//	    query = new DbQuery(connection, " select * from tblproject ");
//	    query.setString(1, "param1");
        ResultSet rs = query.execute();
        try {
            int rowCount = 0;
            while (rs.next()) {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                rowCount++;
            }
            System.out.println(rowCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query.closeResultSet(rs);   // not needed, but still nice to have
        query.close();
    }
    
    @Test
    public void testQury2() {
//    	Connection connection = ConnectDB.Connect();
    	Connection connection = pool.getConnection();
    	System.out.println("使用链接池中的链接对象："+pool.getConnectionsCount());
    	// Example #1:
//        DbOomQuery q = new DbOomQuery(connection,"insert into Foo(id,remark) values(10000,'data')");
//        q.setGeneratedColumns();            // indicate some auto-generated columns
//        q.executeUpdate();
//        // get the first auto-genereted column, i.e. usually IDlong key = q.getGeneratedKey();
//        q.close();
        // Example #2:
        DbOomQuery q = new DbOomQuery(connection,"insert into FOO(id,remark) values(5000,'data5')");
        q.setGeneratedColumns("id");        // indicate auto-generated column
        q.executeUpdate();
        ResultSet rs = q.getGeneratedColumns();
        q.closeResultSet(rs);
        q.close();
    }

    public static void testSession() {
        DbSession session = new DbSession(new TestdbProvider());
        session.beginTransaction();
        try {
            DbQuery query = new DbQuery(session, "select count(*) from tblproject");
            long count = query.executeCount();
            System.out.println(count);
            query = new DbQuery(session, "select * from tblproject");
            ResultSet result = query.execute();
            while (result.next()) {
                System.out.println(result.getString(1));
            }
        } catch (SQLException ex) {
        }
        session.commitTransaction();
        session.closeSession();                // only session is explicitly closed :)
    }
   
   
    

    public static void testSessionWithTransaction() {
        DbSession session = new DbSession(new TestdbProvider());
        session.beginTransaction(new DbTransactionMode().setReadOnly(true));
        try {
            DbQuery query = new DbQuery(session, "select count(*) from tblproject");
            long count = query.executeCount();
            query = new DbQuery(session, "select * from tblproject");
            ResultSet result = query.execute();
            while (result.next()) {
                System.out.println(result.getString(1));
            }
            session.commitTransaction();
        } catch (Exception ex) {
            session.rollbackTransaction();
        }
        session.isSessionClosed();
        session.isSessionOpen();
        session.isTransactionActive();
        System.out.println(session.isTransactionActive());
    }

    public static void testDbOomQuery() {
        DbSession session = new DbSession(new TestdbProvider());
        session.beginTransaction(new DbTransactionMode().setReadOnly(true));
        try {
//            DbOomQuery q = new DbOomQuery(session, "select * from person where id = 1");
//            Person girl = q.find(Person.class);
//            Console.info(girl.toString());
//            DbOomQuery q = new DbOomQuery(session, "select * from person");
//            List<Person> list = q.list(Person.class);
//            for (Person p : list) {
//                Console.info(p.toString());
//            }
//            Console.info("===================================");
//            DbOomQuery q = new DbOomQuery(session, "select * from person");
//            List<Person> list = q.list(5, Person.class);
//            for (Person p : list) {
//                Console.info(p.toString());
//            }
//            Console.info("===================================");
//            DbOomQuery q = new DbOomQuery(session, "select * from person");
//            List<Person> list = q.list(new PersonQueryMapper());
//            for (Person p : list) {
//                Console.info(p.toString());
//            }
            DbOomQuery q = new DbOomQuery(session, "select * from person");
            List<Person> list = q.list(Person.class);
            for (Person p : list) {
            	logger.info(p.toString());
            }
            session.commitTransaction();
        } catch (Exception ex) {
            session.rollbackTransaction();
        }
        System.out.println(session.isTransactionActive());
    }
    
    @Test
    public void testDbOomQuery2() {
//        DbSession session = new DbSession(new TestdbProvider());
//        DbSession session = new DbSession(BaseDao.getPool());
        DbSession session = new DbThreadSession(BaseDao.getPool());
        session.beginTransaction(new DbTransactionMode().setReadOnly(true));
        try {
            DbOomQuery q = new DbOomQuery(session, "select * from FOO");
            List<Foo> list = q.list(Foo.class);
            for (Foo p : list) {
                logger.info(p.toString());
            }
            session.commitTransaction();
        } catch (Exception ex) {
            session.rollbackTransaction();
        }
    }
    
    @Test
    public void testDbSession() {
        DbSession session = new DbThreadSession(new TestdbProvider());
        session.beginTransaction(new DbTransactionMode().setReadOnly(true));
        try {
        	DbQuery query = new DbQuery("select * from FOO");
            ResultSet rs = query.execute();
            while(rs.next()){
            	logger.info(rs.getString("remark"));
            }
            session.commitTransaction();
        } catch (Exception ex) {
            session.rollbackTransaction();
        }
    }
    
    public static void testDbOomQuery3() {
        DbSession session = new DbSession(new TestdbProvider());
        session.beginTransaction();
        try {
            // sql 生成器的一个实现类
            Person person = new Person("hello111","1234563",22);
            DbSqlBuilder b = DbSqlBuilder.sql().insert(Person.class, person);
            DbOomQuery q = new DbOomQuery(session,b);
            q.executeUpdate();
            session.commitTransaction();
            session.closeSession();
        } catch (Exception ex) {
            session.rollbackTransaction();
        }
    }
    
    public static void testDbOomQuery4() {
        DbSession session = new DbSession(new TestdbProvider());
        session.beginTransaction();
        try {
            // sql 生成器的一个实现类
            DbSqlBuilder b = DbSqlBuilder.sql()._("select * from person");
            b.toString();
            DbOomQuery q = new DbOomQuery(session,b);
            List<Person> list = q.list(Person.class);
            for (Person p : list) {
                logger.info(p.toString());
            }
            session.commitTransaction();
            session.closeSession();
        } catch (Exception ex) {
            session.rollbackTransaction();
        }
    }
    
    public static void testDbOomQuery5() {
        DbOomManager m = DbOomManager.getInstance(); // 用来对框架进行配置
        DbSession session = new DbSession(new TestdbProvider());
        session.beginTransaction();
        try {
            // sql 生成器的一个实现类
            DbSqlBuilder b = DbSqlBuilder.sql()
                ._("select * from person")
                ;
            
//          DbEntitySql 底层用 DbSqlBuilder 实现
            DbOomQuery q = new DbOomQuery(session,b);
            List<Person> list = q.list(Person.class);
            for (Person p : list) {
                logger.info(p.toString());
            }
            session.commitTransaction();
            session.closeSession();
        } catch (Exception ex) {
            session.rollbackTransaction();
        }
    }
}
