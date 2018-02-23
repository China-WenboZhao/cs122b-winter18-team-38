package Pages_Show;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import JDBC.DashBoardJDBC;

@SuppressWarnings("serial")
@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet {

	DashBoardJDBC dbjdbc;

	public DashBoard() {
		// TODO Auto-generated constructor stub
		dbjdbc = new DashBoardJDBC();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int status = 3;
		String title = request.getParameter("title");
		int year = Integer.parseInt(request.getParameter("movieyear"));
		String director = request.getParameter("director");
		String star_name = request.getParameter("star_name");
		int birth_year = Integer.parseInt(request.getParameter("birth_year"));
		String genre = request.getParameter("genre");

		try {
			 status = dbjdbc.addmovie(title, year, director, star_name, birth_year, genre);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JSONObject responseJsonObject = new JSONObject();
		try {
			if(status==0) {
				responseJsonObject.put("status","Success! you added such connections");
			}else if(status==1) {
				responseJsonObject.put("status","Fail! already has such connections");
			}else if(status==2) {
				responseJsonObject.put("status","Fail! repeat movie title");
			}else{
				responseJsonObject.put("status","Fail! can't access database");
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		response.getWriter().write(responseJsonObject.toString());

	}
	// }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}