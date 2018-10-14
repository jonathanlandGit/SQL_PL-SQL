package ss.dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnector {

	public static Connection getConnection() {
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/jdbc/DbmsDS");
			Connection con = ds.getConnection();
			con.setAutoCommit(false);
			return con;
		} catch (Exception e) {
			throw new RuntimeException("Error in getting database connection.", e);
		} 
	}
}
