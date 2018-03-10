package Pages_Show;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Entity.User;
import JDBC.GetCustomersInfo;
import recapture.VerifyUtils;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Log_In extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LinkedList<Entity.User> userlist;
	GetCustomersInfo getuser;

	/**
	 * @see HttpServlet#HttpServlet()User.java
	 */
	public Log_In() {
		super();
		// TODO Auto-generated constructor stub
		getuser = new GetCustomersInfo();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		boolean allsame = false;
		boolean sameusername = false;

		PrintWriter out = response.getWriter();
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
		// Verify CAPTCHA.
		boolean valid = VerifyUtils.verify(gRecaptchaResponse);
//		if (!valid) {
//		    
//			JSONObject responseJsonObject = new JSONObject();
//			try {
//				responseJsonObject.put("status", "fail");
//				if (sameusername == true) {
//					responseJsonObject.put("message", "incorrect password");
//				} else {
//					responseJsonObject.put("message", "Please Do Recapcha First");
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			response.getWriter().write(responseJsonObject.toString());
//		    return;
//		}
		

		try {
			userlist = getuser.getUser();
			for (int i = 0; i < userlist.size(); i++) {
				System.out.println(userlist.get(i).getEmail());
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// this example only allows username/password to be test/test
		// in the real project, you should talk to the database to verify
		// username/password
		for (int i = 0; i < userlist.size(); i++) {
			if (username.equals(userlist.get(i).getEmail()) & password.equals(userlist.get(i).getPassword())) {
				// set this user into the session
				allsame = true;
				request.getSession().setAttribute("user", new User(username));
				JSONObject responseJsonObject = new JSONObject();
				try {
					responseJsonObject.put("status", "success");
					responseJsonObject.put("message", "success");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				response.getWriter().write(responseJsonObject.toString());
			} else if (username.equals(userlist.get(i).getEmail()) & !password.equals(userlist.get(i).getPassword())) {
				sameusername = true;
			}
		}

		// if (username.equals("anteater") && password.equals("123456")) {
		// // login success:
		//
		// // set this user into the session
		// request.getSession().setAttribute("user", new User(username));
		//
		//
		// JSONObject responseJsonObject = new JSONObject();
		// try {
		// responseJsonObject.put("status", "success");
		// responseJsonObject.put("message", "success");
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// response.getWriter().write(responseJsonObject.toString());
		// } else {
		// login fail
		if (allsame == false) {
			request.getSession().setAttribute("user", new User(username));
			JSONObject responseJsonObject = new JSONObject();
			try {
				responseJsonObject.put("status", "fail");
				if (sameusername == true) {
					responseJsonObject.put("message", "incorrect password");
				} else {
					responseJsonObject.put("message", "user " + username + " doesn't exist");
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			response.getWriter().write(responseJsonObject.toString());
		}
	}
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}