package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.Star;

public class GetStarDetails {

	Star star = new Star();
	Statement select;
	String query;
	ResultSet result;

	public Star getStar(String star_name)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Connection connection = DataBaseConnect.getconn();

		select = connection.createStatement();
		query = "Select title,name,birthYear from movies inner join stars_in_movies on movies.id = stars_in_movies.movieId inner join stars on stars_in_movies.starId = stars.id where";
		query = query + " stars.name like " + "'" + star_name + "%'";
		result = select.executeQuery(query);
		while (result.next()) {
			star.addMovies_acted(result.getString(1));
			star.setStar_name(result.getString(2));
			star.setBirth_year(result.getInt(3));

		}

		return star;
	}
}
