package JDBC;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseConnect {

	private static Connection conn=null;

	public DataBaseConnect() {
	}

	@SuppressWarnings("unused")
	public static Connection getconn()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{

		// Class.forName("com.mysql.jdbc.Driver").newInstance();
		// // Connect to the test database
		// conn =DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
		// "root", "Wenbo");

		Context initCtx;
		try {
			initCtx = new InitialContext();
			if (initCtx == null)
				System.out.println("initCtx is NULL");

			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			if (envCtx == null)
				System.out.println("envCtx is NULL");

			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			if (ds == null)
				System.out.println("ds is null.");
			
			conn = ds.getConnection();
			if (conn == null)
				System.out.println("dbcon is null.");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

}
