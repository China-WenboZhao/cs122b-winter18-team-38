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

import Entity.Employee;
import JDBC.GetEmployeeInfo;

/**
 * Servlet implementation class Login
 */
@WebServlet("/employee_Login")
public class employee_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LinkedList<Entity.Employee> employeelist;
	GetEmployeeInfo getemployee;

	/**
	 * @see HttpServlet#HttpServlet()User.java
	 */
	public employee_login() {
		super();
		// TODO Auto-generated constructor stub
		getemployee = new GetEmployeeInfo();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("asdasdasd");
		System.out.println(email);
		System.out.println(password);
		boolean allsame = false;
		boolean sameusername = false;	

		try {
			employeelist = getemployee.getEmployee();
			for (int i = 0; i < employeelist.size(); i++) {
				System.out.println(employeelist.get(i).getEmail());
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// this example only allows username/password to be test/test
		// in the real project, you should talk to the database to verify
		// username/password
		for (int i = 0; i < employeelist.size(); i++) {
			if (email.equals(employeelist.get(i).getEmail()) & password.equals(employeelist.get(i).getPassword())) {
				// set this user into the session
				allsame = true;
				request.getSession().setAttribute("employee", new Employee(email));
				JSONObject responseJsonObject = new JSONObject();
				try {
					responseJsonObject.put("status", "success");
					responseJsonObject.put("message", "success");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				response.getWriter().write(responseJsonObject.toString());
			} else if (email.equals(employeelist.get(i).getEmail()) & !password.equals(employeelist.get(i).getPassword())) {
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
			request.getSession().setAttribute("employee", new Employee(email));
			JSONObject responseJsonObject = new JSONObject();
			try {
				responseJsonObject.put("status", "fail");
				if (sameusername == true) {
					responseJsonObject.put("message", "incorrect password");
				} else {
					responseJsonObject.put("message", "employee " + email + " doesn't exist");
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