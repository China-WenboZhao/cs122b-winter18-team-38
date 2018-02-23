package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Entity.Employee;

public class GetEmployeeInfo {

	static LinkedList<Entity.Employee> employeelist;
	Statement select;
	String query;
	ResultSet result;

	public GetEmployeeInfo() {
		// TODO Auto-generated constructor stub
	}

	public LinkedList<Employee> getEmployee()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Incorporate mySQL driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
				"root", "Wenbo");
		// Create an execute an SQL statement to select all of table"rating" records

		select = connection.createStatement();
		query = "Select * from employees";
		result = select.executeQuery(query);
		employeelist = new LinkedList<Entity.Employee>();
		while (result.next()) {
			String email = result.getString(1);
			String password = result.getString(2);
			String fullname = result.getString(3);

			Entity.Employee new_record = new Entity.Employee(email, password, fullname);
			employeelist.add(new_record);
		}
		return employeelist;
	}

}