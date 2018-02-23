package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entity.User;

public class GetCustomersInfo {

	static LinkedList<Entity.User> userlist;
	Statement select;
	String query;
	ResultSet result;

	public GetCustomersInfo() {
		// TODO Auto-generated constructor stub
	}

	public LinkedList<User> getUser()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Incorporate mySQL driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
				"root", "Wenbo");
		// Create an execute an SQL statement to select all of table"rating" records

		select = connection.createStatement();
		query = "Select * from customers";
		result = select.executeQuery(query);
		userlist = new LinkedList<Entity.User>();
		while (result.next()) {
			String email = result.getString(6);
			String password = result.getString(7);
			String id = result.getString(1);
			String firstName = result.getString(2);
			String lastName = result.getString(3);
			String ccId = result.getString(4);
			String address = result.getString(5);
			Entity.User new_record = new Entity.User(email, password, id, firstName, lastName, ccId, address);
			userlist.add(new_record);
		}
		return userlist;
	}

}
