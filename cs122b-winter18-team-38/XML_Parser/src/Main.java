import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.GenreInMovie;
import entity.Movie;
import entity.Star;
import entity.StarInMovie;
import parse.ParseActors63;
import parse.ParseCasts124;
import parse.ParseMain243;

public class Main {

	/**
	 * To test 3 parsers For each parser, it can return a list of objects that
	 * Project 3 requires
	 *
	 * Note: There are some error data inside the given 3 xml files. We throw out
	 * those errors and ignore it when we add the object to our list.
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
	 */
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Statement select;
		String query;
		ResultSet result;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
				"root", "Wenbo");
		select = connection.createStatement();

		// Create an instance
		ParseMain243 main243Parser = new ParseMain243();
		System.out.println("Size of movies: " + main243Parser.getMovies().size());
		
		
		for (int i = 0; i < main243Parser.getMovies().size(); i++) {
			Movie movie = (Movie) main243Parser.getMovies().get(i);
			query = "select * from movies where id=\""+movie.getId()+"\"";
			result = select.executeQuery(query);
			if (!result.next()) {
				query = "insert into movies(id,title,year,director) values(\"" + movie.getId() + "\",\"" + movie.getTitle()
						+ "\"," + movie.getYear() + ",\"" + movie.getDirector() + "\")";
				 select.executeUpdate(query);
			}
		}
//		for (int i = 0; i < main243Parser.getGenreInMovies().size(); i++) {
//			GenreInMovie genreinmovie = (GenreInMovie) main243Parser.getGenreInMovies().get(i);
//			query = "select * from genres_in_movies where genreId=\"" + genreinmovie.getGenreId() + "\" and movieId=\""
//					+ genreinmovie.getMovieId()+"\"";
//			result = select.executeQuery(query);
//			if (!result.next()) {
//				query = "insert into genres_in_movies(genreId,movieId) values(\"" + genreinmovie.getGenreId() + "\",\""
//						+ genreinmovie.getMovieId() + "\")";
//				select.executeUpdate(query);
//			}
//		}

		// Create an instance
		ParseActors63 actors63Parser = new ParseActors63();
		for (int i = 0; i < actors63Parser.getStars().size(); i++) {
			Star star = (Star) actors63Parser.getStars().get(i);
			query = "select * from stars where id=\"" + star.getId() + "\"" ;
			 result=select.executeQuery(query);
			if (!result.next()) {
				query = "insert into stars(id,name,birthYear) values(\"" + star.getId() + "\",\"" + star.getName() + "\","
						+ star.getBirthYear() + ")";
				select.executeUpdate(query);
			}
		}

		// Create an instance
		ParseCasts124 casts124Parser = new ParseCasts124();
		
		query="SET FOREIGN_KEY_CHECKS=0";
		result = select.executeQuery(query);
		
		for (int i = 0; i < casts124Parser.getStarInMovies().size(); i++) {
			StarInMovie starinmovie = (StarInMovie) casts124Parser.getStarInMovies().get(i);
			query = "select * from stars_in_movies where starId=\"" + starinmovie.getStarId() + "\" and movieId=\""
					+ starinmovie.getMovieId()+"\"";
			result = select.executeQuery(query);
			if (!result.next()) {
				query = "insert into stars_in_movies(starId,movieId) values(\"" + starinmovie.getStarId() + "\",\""
						+ starinmovie.getMovieId() + "\")";
				select.executeUpdate(query);
			}
		}
		
		query="SET FOREIGN_KEY_CHECKS=1";
		result = select.executeQuery(query);
		System.out.println("success");
	}
}
