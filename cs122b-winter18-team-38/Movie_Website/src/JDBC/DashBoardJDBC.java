package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashBoardJDBC {

	Statement select;
	String query;
	ResultSet result;

	public DashBoardJDBC() {
		// TODO Auto-generated constructor stub
	}

	public int addmovie(String title, int year, String director, String star_name, int birth_year, String genre)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
				"root", "Wenbo");

		
		String maxmovieid = "";
		String maxstarid = "";
		String letters;
		int num;
		int status;
		
		// read max movieid
		select = connection.createStatement();
		query = "select max(id) from movies";
		result = select.executeQuery(query);
		while (result.next()) {
			maxmovieid = result.getString(1);
		}
		letters = maxmovieid.substring(0, 2);
		int index=2;
		while(maxmovieid.substring(index,index+1).equals("0")) {
			letters = letters+"0";
			index++;
		}
		num = Integer.parseInt(maxmovieid.substring(2, 9)) + 1;
		maxmovieid = letters+"" + num;

		// read max starid
		query = "select max(id) from stars";
		result = select.executeQuery(query);
		while (result.next()) {
			maxstarid = result.getString(1);
		}
		letters = maxstarid.substring(0, 2);
		index=2;
		while(maxstarid.substring(index,index+1).equals("0")) {
			letters = letters+"0";
			index++;
		}
		num = Integer.parseInt(maxstarid.substring(2, 9)) + 1;
		maxstarid = letters +""+ num;

		 System.out.println("call add_movie("+"'"+title+"',"+year+","+"'"+director+"',"+"'"+star_name+"',"+birth_year+","+"'"+genre+"',"+"'"+maxmovieid+"',"+"'"+maxstarid+"',"+"@result_status"+")");
		 
		 //execute procedure
		 select.addBatch("set @result_status=0");
		 select.addBatch("call add_movie("+"'"+title+"',"+year+","+"'"+director+"',"+"'"+star_name+"',"+birth_year+","+"'"+genre+"',"+"'"+maxmovieid+"',"+"'"+maxstarid+"',"+"@result_status"+")");
		 select.executeBatch();	
		 query = "Select @result_status";
		 result = select.executeQuery(query);
		 String temp="";
		 while (result.next()) {
				temp = result.getString(1);
		}
		 status = Integer.parseInt(temp);
		 System.out.println(status);
		return status;
	}
}