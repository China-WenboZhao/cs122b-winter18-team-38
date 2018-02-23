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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import JDBC.GetTable;

@SuppressWarnings("serial")
@WebServlet("/metadata")
public class metadata extends HttpServlet {

	GetTable GetT;
	LinkedList<Entity.Table> tablelist;

	public metadata() {
		// TODO Auto-generated constructor stub
		GetT = new GetTable();
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        
		JSONArray tablearray = new JSONArray();
		try {
			tablelist=GetT.getTable();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < tablelist.size(); i++) {
			JSONObject resultobj = new JSONObject();
			try {
				resultobj.put("Name", tablelist.get(i).gettablename());
				resultobj.put("Field", tablelist.get(i).getField());
				resultobj.put("Type", tablelist.get(i).getType());
				resultobj.put("Null", tablelist.get(i).getNull());
				resultobj.put("Key", tablelist.get(i).getKey());
				resultobj.put("Default", tablelist.get(i).getDefault());
				resultobj.put("Extra", tablelist.get(i).getExtra());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tablearray.put(resultobj);
		}
		
		
		
		
		
		PrintWriter pw = response.getWriter();
//		System.out.println("hello");
//		System.out.println("array"+array.toString());
		pw.write(tablearray.toString());
		pw.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
