package Pages_Show;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/cart")
public class My_Cart extends HttpServlet {

	public My_Cart() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		JSONArray array = new JSONArray();
		ArrayList<String> ids, titles, amounts;
		synchronized (session) {
			ids = (ArrayList<String>) session.getAttribute("ids");
			titles = (ArrayList<String>) session.getAttribute("titles");
			amounts = (ArrayList<String>) session.getAttribute("amounts");
			if (ids == null) {
				ids = new ArrayList<String>();
				session.setAttribute("ids", ids);
			}
			if (titles == null) {
				titles = new ArrayList<String>();
				session.setAttribute("titles", titles);
			}
			if (amounts == null) {
				amounts = new ArrayList<String>();
				session.setAttribute("amounts", amounts);
			}
		}

		if (request.getParameter("id") != null) {

			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String amount = request.getParameter("amount");

			synchronized (ids) {
				if (id != null) {
					ids.add(id);
					titles.add(title);
					amounts.add(amount);
				}
			}

		} else if (request.getParameter("index") != null) {
			String index = request.getParameter("index");
			String amount = request.getParameter("amount");
			System.out.println("inx"+index);
			System.out.println("size" + ids.size());
			int index_num = Integer.parseInt(index);
			int amount_num = Integer.parseInt(amount);
			synchronized (amounts) {
				if (index != null & amount_num != 0) {
					amounts.set(index_num, amount);
				} else if (index != null & amount_num == 0) {
					amounts.remove(index_num);
					ids.remove(index_num);
					titles.remove(index_num);
					// array.remove(index_num);
				}
			}
		}

		session.setAttribute("ids", ids);
		session.setAttribute("titles", titles);
		session.setAttribute("amounts", amounts);

		for (int i = 0; i < ids.size(); i++) {
			JSONObject resultobj = new JSONObject();
			try {
				resultobj.put("id", ids.get(i));
				resultobj.put("title", titles.get(i));
				resultobj.put("amount", amounts.get(i));
				// System.out.println( ids.get(i));
				// System.out.println( titles.get(i));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			array.put(resultobj);
		}
		PrintWriter pw = response.getWriter();
		pw.write(array.toString());
		pw.flush();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
