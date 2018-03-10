package Entity;

import java.util.LinkedList;

public class Star {
	private String star_name;
	private int birth_year;
	private LinkedList<String> movies_acted;
	private int movies_num;

	public Star() {
		super();
		setMovies_num(0);
		movies_acted = new LinkedList<String>();
	}

	public String getStar_name() {
		return star_name;
	}

	public void setStar_name(String star_name) {
		this.star_name = star_name;
	}

	public int getBirth_year() {
		return birth_year;
	}

	public void setBirth_year(int birth_year) {
		this.birth_year = birth_year;
	}

	public LinkedList<String> getMovies_acted() {
		return movies_acted;
	}

	public void addMovies_acted(String movie_name) {
		movies_acted.add(movie_name);
		setMovies_num(getMovies_num() + 1);
	}

	public int getMovies_num() {
		return movies_num;
	}

	public void setMovies_num(int movies_num) {
		this.movies_num = movies_num;
	}

}
