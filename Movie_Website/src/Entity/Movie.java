package Entity;

import java.util.LinkedList;

public class Movie {

	public Movie(String id) {
		// TODO Auto-generated constructor stub
		movieid = id;
	}

	private float rate;
	private String movieid;
	private String title;
	private int year;
	private String director;
	//private String[] list_of_genres = new String[5];
	private LinkedList<String> list_of_genres = new LinkedList<String>();
	private int num_genres = 0;
	//private String[] list_of_stars = new String[10];
	private LinkedList<String> list_of_stars = new LinkedList<String>();
	private int num_stars = 0;
	private int total_pages;
//
	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int a) {
		total_pages = a;
	}

	public float getrate() {
		return rate;
	}
	public void setrate(float r) {
		rate = r;
	}

	public String getid() {
		return movieid;
	}

	public void settitle(String t) {
		title = t;
	}

	public String gettitle() {
		return title;
	}

	public void setyear(int y) {
		year = y;
	}

	public int getyear() {
		return year;
	}

	public void setdirector(String d) {
		director = d;
	}

	public String getdirector() {
		return director;
	}

	public void setgenreslist(String s, int n) {
		list_of_genres.add(s);
		//list_of_genres[n] = s;
		num_genres = n + 1;
	}

	public LinkedList<String> getgenres() {
		return list_of_genres;
	}

	public int getnum_genres() {
		return num_genres;
	}

	public void setstarslist(String s, int n) {
		list_of_stars.add(s);
		//list_of_stars[n] = s;
		num_stars = n + 1;
	}

	public LinkedList<String> getstars() {
		return list_of_stars;
	}

	public int getnum_stars() {
		return num_stars;
	}

}
