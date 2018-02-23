package Pages_Show;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import JDBC.GetCreditCardInfo;

@SuppressWarnings("serial")
@WebServlet("/Checkout")
public class Check_out extends HttpServlet {


	LinkedList<Entity.CreditCardInfo> ccinfos;
	GetCreditCardInfo gccinfos;

	public Check_out() {
		// TODO Auto-generated constructor stub
		gccinfos = new GetCreditCardInfo();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String cc_number = request.getParameter("cc_number");
		String cc_expiration = request.getParameter("cc_expiration");

		boolean allsame = false;

		try {
			ccinfos = gccinfos.getCreditCardInfo();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		for (int i = 0; i < ccinfos.size(); i++) {
			if (firstName.equals(ccinfos.get(i).getFirstName()) & lastName.equals(ccinfos.get(i).getLastName())
					& cc_number.equals(ccinfos.get(i).getCc_number())
					& cc_expiration.equals(ccinfos.get(i).getCc_expiration())) {
				// set this user into the session
				allsame = true;
				JSONObject responseJsonObject = new JSONObject();
				try {
					responseJsonObject.put("status", "success");
					responseJsonObject.put("message", "success");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}

				response.getWriter().write(responseJsonObject.toString());
			}
		}

		// login fail
		if (allsame == false) {
			JSONObject responseJsonObject = new JSONObject();
			try {
				responseJsonObject.put("status", "fail");
				responseJsonObject.put("message", "incorrect information");

			} catch (JSONException e) {
				e.printStackTrace();
			}

			response.getWriter().write(responseJsonObject.toString());
		}
	}
	// }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
