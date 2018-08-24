package Pages_Show;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import JDBC.GetTop20ByRating;

@SuppressWarnings("serial")
@WebServlet("/movie")
public class Movie_List extends HttpServlet {

	GetTop20ByRating GetMovie;
	LinkedList<Entity.Movie> mlist;

	public Movie_List() {
		// TODO Auto-generated constructor stub
		GetMovie = new GetTop20ByRating();
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long query_startTime = System.nanoTime();

		JSONArray array = new JSONArray();
		String temp= request.getParameter("page");
		int page = Integer.parseInt(temp);
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String director =  request.getParameter("director");
		String star_name =  request.getParameter("star_name");
		String genre = request.getParameter("genre");
		String title_first_character=request.getParameter("title_first_character");
		String sort = request.getParameter("sort");
		System.out.println("sort:"+sort);
		
		long JDBC_startTime = System.nanoTime();
		try {
			if(title ==null& year == null& director ==null& star_name == null & genre == null & title_first_character ==null) {
				mlist = GetMovie.getMovie(page,sort);
			}else if(genre!=null) {
				mlist = GetMovie.getMovie(page,sort,genre);
			}else if(title_first_character!=null) {
				mlist = GetMovie.getMovie(page,sort,title_first_character,1);
			}else {
				mlist = GetMovie.getMovie(page,sort,title,year,director,star_name);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long JDBC_endTime = System.nanoTime();
		for (int i = 0; i < mlist.size(); i++) {
			JSONObject resultobj = new JSONObject();
			try {
				resultobj.put("rate", mlist.get(i).getrate());
				resultobj.put("movieid", mlist.get(i).getid());
				resultobj.put("title", mlist.get(i).gettitle());
				resultobj.put("year", mlist.get(i).getyear());
				resultobj.put("director", mlist.get(i).getdirector());
				resultobj.put("genres", mlist.get(i).getgenres());
				resultobj.put("stars", mlist.get(i).getstars());
				resultobj.put("total_pages", mlist.get(i).getTotal_pages());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			array.put(resultobj);
		}
		PrintWriter pw = response.getWriter();
//		System.out.println("hello");
//		System.out.println("array"+array.toString());
		pw.write(array.toString());
		pw.flush();
		long query_endTime = System.nanoTime();
		long query_elapsedTime = query_endTime - query_startTime;
		long JDBC_elapsedTime = JDBC_endTime - JDBC_startTime;
		
		ServletContext sc= this.getServletContext();
		String Path = sc.getRealPath("/");
		Path = Path+"Log.txt";
		System.out.println(Path);
		FileWriter out = new FileWriter(Path,true);
		BufferedWriter bw = new BufferedWriter(out); 
		String str = query_elapsedTime+","+JDBC_elapsedTime+"\n";
		bw.append(str);
		bw.close();
		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
