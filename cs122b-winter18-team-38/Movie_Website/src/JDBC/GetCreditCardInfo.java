package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


public class GetCreditCardInfo {

	static LinkedList<Entity.CreditCardInfo> ccinfos;
	Statement select;
	String query;
	ResultSet result;

	public GetCreditCardInfo() {
		// TODO Auto-generated constructor stub
	}

	public LinkedList<Entity.CreditCardInfo> getCreditCardInfo()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Connection connection = DataBaseConnect.getconn();
	

		select = connection.createStatement();
		query = "Select * from creditcards";
		result = select.executeQuery(query);
		ccinfos = new LinkedList<Entity.CreditCardInfo>();
		while (result.next()) {

			String firstName = result.getString(2);
			String lastName = result.getString(3);
			String cc_number = result.getString(1);
			String cc_expiration = result.getString(4);

			Entity.CreditCardInfo new_record = new Entity.CreditCardInfo(firstName, lastName, cc_number, cc_expiration);
			ccinfos.add(new_record);
		}
		return ccinfos;
	}

}
