package jodd.db.person;

import java.sql.Connection;
import java.sql.ResultSet;

import jodd.db.oom.DbOomQuery;

import org.junit.Test;

public class BaseDaoTest {
	@Test
	public void testQury3() {
		BaseDao baseDao = new BaseDao();
		baseDao.execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection connection) {
				DbOomQuery q = new DbOomQuery(connection,
						"insert into FOO(id,remark) values(9000,'data9')");
				q.setGeneratedColumns("id"); // indicate auto-generated column
				q.executeUpdate();
				ResultSet rs = q.getGeneratedColumns();
				q.closeResultSet(rs);
				q.close();
				return null;
			}
		});
	}
}
