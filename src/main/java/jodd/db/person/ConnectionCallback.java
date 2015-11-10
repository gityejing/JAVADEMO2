package jodd.db.person;

import java.sql.Connection;

public interface ConnectionCallback {
	public Object doInConnection(Connection connection);

}
