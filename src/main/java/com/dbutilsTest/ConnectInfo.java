package com.dbutilsTest;
/*<Resource  name="jdbc/SLbaoming"  auth="Container"   
type="javax.sql.DataSource"  driverClassName="net.sourceforge.jtds.jdbc.Driver"  
url="jdbc:jtds:sqlserver://localhost:1433/SLbaoming;SelectMethod=Cursor"  
username="sa"  password="ezoa"  maxActive="100"  maxIdle="10"  
maxWait="5000"/>*/

/*<Resource  name="jdbc/SLbaoming"  auth="Container"   
	type="javax.sql.DataSource"  driverClassName="net.sourceforge.jtds.jdbc.Driver"  
	url="jdbc:jtds:sqlserver://192.168.0.121:1433/SLbaoming;SelectMethod=Cursor"  
	username="sa"  password="201012"  maxActive="100"  maxIdle="10"  
	maxWait="5000"/> */
@SuppressWarnings("unused")
public class ConnectInfo {
	public static String driveClassName = "net.sourceforge.jtds.jdbc.Driver";
	public static String url = "jdbc:jtds:sqlserver://192.168.0.121:1433/SLbaoming;SelectMethod=Cursor";
	public static String user = "sa";
	public static String password = "201012";
	
//	public static String driveClassName = "net.sourceforge.jtds.jdbc.Driver";
//	public static String url = "jdbc:jtds:sqlserver://localhost:1433/SLbaoming;SelectMethod=Cursor";
//	public static String user = "sa";
//	public static String password = "ezoa";
//	
	
}
