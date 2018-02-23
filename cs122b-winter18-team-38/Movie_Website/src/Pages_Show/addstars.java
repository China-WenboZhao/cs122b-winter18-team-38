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

import JDBC.AddStars;

@SuppressWarnings("serial")
@WebServlet("/adds")
public class addstars extends HttpServlet {

	AddStars addstarsJDBC;

	public addstars() {
		// TODO Auto-generated constructor stub
		addstarsJDBC = new AddStars();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean ifexsit = false;
		String star_name = request.getParameter("name");
		int birth_year = Integer.parseInt(request.getParameter("year"));


		try {
			 ifexsit = addstarsJDBC.addStar(star_name, birth_year);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JSONObject responseJsonObject = new JSONObject();
		try {
			if(ifexsit==false) {
				responseJsonObject.put("status","Fail! The star has already exsited!");
			}else{
				responseJsonObject.put("status","Success!");
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