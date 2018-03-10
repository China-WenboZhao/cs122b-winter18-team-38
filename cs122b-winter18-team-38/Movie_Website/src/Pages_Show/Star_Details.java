package Pages_Show;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Entity.Star;
import JDBC.GetStarDetails;

@SuppressWarnings("serial")
@WebServlet("/get_star_details")
public class Star_Details extends HttpServlet {
	
	Star star;
	
	public Star_Details() {
		super();
		star = new Star();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String star_name = request.getParameter("star_name");
		GetStarDetails gsd= new GetStarDetails();
		try {
			 star= gsd.getStar(star_name);	
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject resultobj = new JSONObject();
		try {
			
			resultobj.put("star_name", star.getStar_name());
			resultobj.put("birth_year", star.getBirth_year());
			resultobj.put("movies_acted", star.getMovies_acted());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		PrintWriter pw = response.getWriter();
		pw.write(resultobj.toString());
		pw.flush();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
