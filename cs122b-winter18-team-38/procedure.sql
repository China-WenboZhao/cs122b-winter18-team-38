DELIMITER $$ 

CREATE PROCEDURE add_movie ( IN movie_title varchar(100), IN movie_year int(11), IN movie_director varchar(100), IN star_name varchar(100), IN star_birthYear int,  IN genre_name varchar(32), IN next_movie_id varchar(10), IN next_star_id varchar(10), OUT result_status int)
BEGIN
  

    declare star_count int default 0;
    declare genre_count int default 0;
    declare movie_count int default 0;
    declare  temp_movieId varchar(10);
    declare  temp_starId varchar(10);
    declare temp_genreId int(11);
    declare repeat_movie_title int;
    declare already_has int;

	set already_has=1;
    set repeat_movie_title=0;
    
	IF not exists (select  * from stars where name=star_name) Then
		insert into stars(id,name,birthYear) values(next_star_id,star_name,star_birthYear);
        set already_has=0;
	END IF;
    
    IF not exists(select  * from genres where name=genre_name) Then
		insert into genres(name) values(genre_name);
       set already_has=0;
	END IF;
    
    IF not exists (select  * from movies where title=movie_title) Then
		insert into movies(id,title,year,director) values(next_movie_id,movie_title,movie_year,movie_director);
		set already_has=0;
	ELSE
		set repeat_movie_title=1;
	END IF;
    
    
	set temp_movieId=( select  id from movies where title=movie_title);
    set temp_starId=(select  id from stars where name=star_name);
    set temp_genreId=(select  id from genres where name=genre_name);
    
	IF not exists( select * from stars_in_movies where movieId = temp_movieId and starId=temp_starId) Then
		insert into stars_in_movies(starId,movieId) values (temp_starId,temp_movieId);
         set already_has=0;
	END IF;
    
	IF not exists( select * from genres_in_movies where movieId = temp_movieId and genreId=temp_genreId) Then
		insert into genres_in_movies(genreId,movieId) values (temp_genreId,temp_movieId);
		set already_has=0;
	END IF;
   
   IF (already_has=1) Then
		set result_status=1;
	ELSEIF (repeat_movie_title=1) Then
		set result_status=2;
	ELSE 
		set result_status=0;
	END IF;

END
$$
DELIMITER ; 


