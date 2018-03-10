package JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Entity.Table;


// server endpoint URL
@WebServlet("/hero-suggestion")
public class AutoComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * populate the Marvel heros and DC heros hash map.
	 * Key is hero ID. Value is hero name.
	 */
	public static HashMap<Integer, String> Movie = new HashMap<>();
	public static HashMap<Integer, String> Star = new HashMap<>();
	
	static {
//		Movie.put(1, "Blade");
//		Movie.put(2, "Ghost Rider");
//		Movie.put(3, "Luke Cage");
//		Movie.put(4, "Silver Surfer");
//		Movie.put(5, "Beast");
//		Movie.put(6, "Thing");
//		Movie.put(7, "Black Panther");
//		Movie.put(8, "Invisible Woman");
//		Movie.put(9, "Nick Fury");
//		Movie.put(10, "Storm");
//		Movie.put(11, "Iron Man");
//		Movie.put(12, "Professor X");
//		Movie.put(13, "Hulk");
//		Movie.put(14, "Cyclops");
//		Movie.put(15, "Thor");
//		Movie.put(16, "Jean Grey");
//		Movie.put(17, "Wolverine");
//		Movie.put(18, "Daredevil");
//		Movie.put(19, "Captain America");
//		Movie.put(20, "Spider-Man");
	}
	
	static {
//		Star.put(101, "Superman");
//		Star.put(102, "Batman");
//		Star.put(103, "Wonder Woman");
//		Star.put(104, "Flash");
//		Star.put(105, "Green Lantern");
//		Star.put(106, "Catwoman");
//		Star.put(107, "Nightwing");
//		Star.put(108, "Captain Marvel");
//		Star.put(109, "Aquaman");
//		Star.put(110, "Green Arrow");
//		Star.put(111, "Martian Manhunter");
//		Star.put(112, "Batgirl");
//		Star.put(113, "Supergirl");
//		Star.put(114, "Black Canary");
//		Star.put(115, "Hawkgirl");
//		Star.put(116, "Cyborg");
//		Star.put(117, "Robin");
	}
    
    public AutoComplete() {
        super();
    }

    /*
     * 
     * Match the query against Marvel and DC heros and return a JSON response.
     * 
     * For example, if the query is "super":
     * The JSON response look like this:
     * [
     * 	{ "value": "Superman", "data": { "category": "dc", "heroID": 101 } },
     * 	{ "value": "Supergirl", "data": { "category": "dc", "heroID": 113 } }
     * ]
     * 
     * The format is like this because it can be directly used by the 
     *   JSON auto complete library this example is using. So that you don't have to convert the format.
     *   
     * The response contains a list of suggestions.
     * In each suggestion object, the "value" is the item string shown in the dropdown list,
     *   the "data" object can contain any additional information.
     * 
     * 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Statement select;
			String querym,querys;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to the test database
			Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
					"root", "Wenbo");
			// Create an execute an SQL statement to select all of table"rating" records
			
			 select = connection.createStatement();
			 querym = "select * from movies";
			 querys = "select * from stars";
			
			ResultSet movielist = select.executeQuery(querym);
			int i=1;
			while (movielist.next()) {
				String name = movielist.getString(2);
				Movie.put(i,name);
				i++;
			}
			
			ResultSet starlist = select.executeQuery(querys);
			int j=1;
			while (starlist.next()) {
				String name = starlist.getString(2);
				
				Star.put(j,name);
				j++;
			}
			
			
			// setup the response json arrray
			JsonArray jsonArray = new JsonArray();
			
			// get the query string from parameter
			String query = request.getParameter("query");
			System.out.println(query);
			
			// return the empty json array if query is null or empty
			if (query == null || query.trim().isEmpty()) {
				response.getWriter().write(jsonArray.toString());
				return;
			}	
			
			// search on marvel heros and DC heros and add the results to JSON Array
			// this example only does a substring match
			// TODO: in project 4, you should do full text search with MySQL to find the matches on movies and stars
			
			String isin = null;
			isin="SELECT * FROM movies WHERE MATCH (title) AGAINST ('"+query.toLowerCase()+"*' IN BOOLEAN MODE) LIMIT 5 OFFSET 1;";
			ResultSet fullm = select.executeQuery(isin);
			int num=0;
			while (fullm.next()) {
				String name = fullm.getString(2);
				jsonArray.add(generateJsonObject(num, name, "----------Movie----------"));
				num++;
			}
			
			isin="SELECT * FROM stars WHERE MATCH (name) AGAINST ('"+query.toLowerCase()+"*' IN BOOLEAN MODE) LIMIT 5 OFFSET 1;";
			ResultSet fulls = select.executeQuery(isin);
			int nums=0;
			while (fulls.next()) {
				String name = fulls.getString(2);
				jsonArray.add(generateJsonObject(nums, name, "----------Star----------"));
				nums++;
			}
			
			
			response.getWriter().write(jsonArray.toString());
			return;
		} catch (Exception e) {
			System.out.println(e);
			response.sendError(500, e.getMessage());
		}
	}
	
	/*
	 * Generate the JSON Object from hero and category to be like this format:
	 * {
	 *   "value": "Iron Man",
	 *   "data": { "category": "marvel", "heroID": 11 }
	 * }
	 * 
	 */
	private static JsonObject generateJsonObject(Integer heroID, String heroName, String categoryName) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("value", heroName);
		
		JsonObject additionalDataJsonObject = new JsonObject();
		additionalDataJsonObject.addProperty("category", categoryName);
		additionalDataJsonObject.addProperty("heroID", heroID);
		
		jsonObject.add("data", additionalDataJsonObject);
		return jsonObject;
	}


}

