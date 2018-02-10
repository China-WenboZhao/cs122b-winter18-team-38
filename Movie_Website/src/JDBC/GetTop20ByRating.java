package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;



public class GetTop20ByRating {

	static LinkedList<Entity.Movie> toplist;
	Statement select;
	String query,query2;
	ResultSet result;
	
	public GetTop20ByRating() {
		// TODO Auto-generated constructor stub
	}

	public LinkedList<Entity.Movie> getMovie(int page,String sort) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Incorporate mySQL driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
				"root", "Wenbo");
		// Create an execute an SQL statement to select all of table"rating" records
		
		 select = connection.createStatement();
		 query = "Select * from ratings order by rating desc limit 20 offset "+ (page-1)*20;
		 query2 = "Select * from ratings order by rating desc limit 200 offset 0";
		
		 
		 Set_para();
		 return toplist;
	}
	
	public LinkedList<Entity.Movie> getMovie(int page,String sort,String genre) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Incorporate mySQL driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
				"root", "Wenbo");
		// Create an execute an SQL statement to select all of table"rating" records
		
		 select = connection.createStatement();
		 query = "Select * from movies inner join genres_in_movies on movies.id = genres_in_movies.movieId inner join genres on genres_in_movies.genreId = genres.id where genres.name like "+"'"+genre+"'";
		System.out.println("sort "+sort);
		if(sort.equals("yearasc")) {
			 query = query+"order by movies.year asc";
		 }else if(sort.equals("yeardesc") ) {
			 query = query+"order by movies.year desc";
		 }else if(sort.equals("titleasc")) {
			 query = query+"order by movies.title asc";
		 }else if(sort.equals("titledesc") ) {
			 query = query+"order by movies.title desc";
		 }
		 query2 = query;
		 System.out.println(query2);
		 query = query+ " limit 20 offset "+ (page-1)*20;
		
		Set_para();
		return toplist;
	}
	
	public LinkedList<Entity.Movie> getMovie(int page,String sort,String title_first_character,int i) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Incorporate mySQL driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
				"root", "Wenbo");
		// Create an execute an SQL statement to select all of table"rating" records
		
		 select = connection.createStatement();
		 query = "Select * from movies where title like"+"'"+title_first_character+"%'";
		 if(sort.equals("yearasc")) {
			 query = query+"order by movies.year asc";
		 }else if(sort.equals("yeardesc") ) {
			 query = query+"order by movies.year desc";
		 }else if(sort.equals("titleasc")) {
			 query = query+"order by movies.title asc";
		 }else if(sort.equals("titledesc") ) {
			 query = query+"order by movies.title desc";
		 }
		 query2 = query;
		 query = query+ " limit 20 offset "+ (page-1)*20;
	
		Set_para();
		return toplist;
	}
	
	public LinkedList<Entity.Movie> getMovie(int page,String sort, String title, String year, String director, String star_name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Incorporate mySQL driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
				"root", "Wenbo");
		// Create an execute an SQL statement to select all of table"rating" records
		
		select = connection.createStatement();
		query = "Select * from movies where";
		boolean already_has_condition = false;
		
		if(star_name == null) {
			query = "Select * from movies where";
		}else {
			query = "Select * from movies inner join stars_in_movies on movies.id = stars_in_movies.movieId inner join stars on stars_in_movies.starId = stars.id where";
			query = query + " stars.name like "+"'%"+star_name+"%'";
			already_has_condition = true;
		}
		
		if(title!= null) {
			if(already_has_condition == true) {
				query = query + " and title like "+ "'%"+title+"%'" ;
			}else {
				query = query + " title like "+ "'%"+title+"%'";
			}
			already_has_condition = true;
		}
		if(year!= null) {
			if(already_has_condition == true) {
				query = query + " and year like "+ "'%"+year+"%'" ;
			}else {
				query = query + " year like "+ "'%"+year+"%'";
			}
			already_has_condition = true;
		}
		if(director!= null) {
			if(already_has_condition == true) {
				query = query + " and director like "+ "'%"+director+"%'";
			}else {
				query = query + " director like "+"'%"+director+"%'";
			}
			already_has_condition = true;
		}
		
		
		if(sort.equals("yearasc")) {
			 query = query+"order by movies.year asc";
		 }else if(sort.equals("yeardesc") ) {
			 query = query+"order by movies.year desc";
		 }else if(sort.equals("titleasc")) {
			 query = query+"order by movies.title asc";
		 }else if(sort.equals("titledesc") ) {
			 query = query+"order by movies.title desc";
		 }
		
		 query2 = query;
		 query = query+ " limit 20 offset "+ (page-1)*20;
		 
		Set_para();
		return toplist;
	}
	
	public void Set_para() throws SQLException {
		
		ResultSet result2 = select.executeQuery(query2);
		result2.last();
		int Total_Movie_Count = result2.getRow();
		int Total_Pages = (int) Math.ceil(Total_Movie_Count/20.0);
		
		result = select.executeQuery(query);
		toplist = new LinkedList<Entity.Movie>();
		while (result.next()) {
			String id = result.getString(1);
			Entity.Movie new_record = new Entity.Movie(id);
			toplist.add( new_record);
		}
		
		// show related records
		for (int i = 0; i < toplist.size(); i++) {
			
			toplist.get(i).setTotal_pages(Total_Pages);
			
			
			ResultSet result_movies = select
					.executeQuery("Select * from movies where id='" + toplist.get(i).getid() + "'");
			while (result_movies.next()) {
				toplist.get(i).settitle(result_movies.getString(2));
				toplist.get(i).setyear(result_movies.getInt(3));
				toplist.get(i).setdirector(result_movies.getString(4));
				
			} 
			//set list of ratings
			ResultSet result_rating_in_movies = select
					.executeQuery("Select * from ratings where movieId='" + toplist.get(i).getid() + "'");
			while (result_rating_in_movies.next()) {
				toplist.get(i).setrate(result_rating_in_movies.getFloat(2));
			} 

			

			// set list of genres(from genres_in_movies to genres)
			ResultSet result_genres_in_movies = select
					.executeQuery("Select * from genres_in_movies where movieId='" + toplist.get(i).getid() + "'");
			int[] genresid = new int[5];
			int count = 0;
			while (result_genres_in_movies.next()) {
				genresid[count] = result_genres_in_movies.getInt(1);
				count++;
			}
			for (int j = 0; j < count; j++) {
				ResultSet result_genres = select.executeQuery("Select * from genres where id = " + genresid[j]);
				while (result_genres.next()) {
					toplist.get(i).setgenreslist(result_genres.getString(2), j);
				}
			}

			// set list of stars(from stars_in_movies to stars)
			ResultSet result_stars_in_movies = select
					.executeQuery("Select * from stars_in_movies where movieId='" + toplist.get(i).getid() + "'");
			String[] starsid = new String[30];
			int count2 = 0;
			while (result_stars_in_movies.next()) {
				starsid[count2] = result_stars_in_movies.getString(1);
				count2++;
			}
			for (int j = 0; j < count2; j++) {
				ResultSet result_stars = select.executeQuery("Select * from stars where id = '" + starsid[j] + "'");
				while (result_stars.next()) {
					toplist.get(i).setstarslist(result_stars.getString(2), j);
				}
			}
		}
	}
	
	
//	public static void sort(Entity.Movie new_record) {
//		int n = toplist.size();
//		boolean if_insert = false;
//		if (toplist.size() != 0) {
//			for (int i = 0; i < n; i++) {
//				if (toplist.get(i).getrate() < new_record.getrate()) {
//					toplist.add(i, new_record);
//					if_insert = true;
//					break;
//				}
//			}
//		} else {
//			toplist.addLast(new_record);
//			if_insert = true;
//		}
//		if (if_insert == false) {
//			toplist.addLast(new_record);
//		}
//	}


}

	
