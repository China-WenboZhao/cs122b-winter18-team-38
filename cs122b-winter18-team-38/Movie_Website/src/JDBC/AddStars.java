package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.mysql.jdbc.PreparedStatement;
 
public class AddStars {

	static LinkedList<String> starnamelist;
	Statement select;
	String query;
	ResultSet result;
	Connection connection;

	public AddStars() {
		// TODO Auto-generated constructor stub
	}

	public LinkedList<String> getStars()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Incorporate mySQL driver
		 connection = DataBaseConnect.getconn();
		// Create an execute an SQL statement to select all of table"rating" records

		select = connection.createStatement();
		query = "Select * from stars";
		result = select.executeQuery(query);
		starnamelist = new LinkedList<String>();
		while (result.next()) {
			String name = result.getString(2);
			starnamelist.add(name);
			System.out.println(name);
		}
		return starnamelist;
	}
	
	public boolean addStar(String starname, int birthdate)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	
        //if exsit
		select = connection.createStatement();
		query = "Select * from stars";
		result = select.executeQuery(query);
		starnamelist = new LinkedList<String>();
		while (result.next()) {
			String name = result.getString(2);
			starnamelist.add(name);
		}
		

		for(int i=0;i<starnamelist.size();i++)
		{

			if(starname.equals(starnamelist.get(i)))
			{
				System.out.println(starname);
				return false;
			}
		}
		
		//max id
		String maxstarid="";
		String letters="";
		query = "select max(id) from stars";
		result = select.executeQuery(query);
		while (result.next()) {
			maxstarid = result.getString(1);
		}
		letters = maxstarid.substring(0, 2);
		int index=2;
		while(maxstarid.substring(index,index+1).equals("0")) {
			letters = letters+"0";
			index++;
		}
		int num = Integer.parseInt(maxstarid.substring(2, 9)) + 1;
		maxstarid = letters+"" + num;
		
		
		//insert
		String sql = "INSERT INTO stars(id,name,birthYear)"
	                +"values("+"?,?,?)";

	    PreparedStatement ptmt = (PreparedStatement) connection.prepareStatement(sql);


	    ptmt.setString(1, maxstarid);
	    ptmt.setString(2, starname);
	    ptmt.setInt(3, birthdate);

	    ptmt.execute();
		
		
		return true;
	}

}
