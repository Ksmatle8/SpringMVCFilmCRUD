package com.skilldistillery.film.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;


@Component
public interface FilmDAO {

	public Film findFilmById(int filmId);
	public List<Film> findFilmByKeyword(String filmKeyword);
	public int addFilm(Film film);
	public int updateFilm(Film film);
<<<<<<< HEAD
	public List<Actor> findActorsByFilmId(int fId);
=======
	public int deleteFilm(Integer filmId) throws Exception;
	
>>>>>>> 9c041ea3cfae859f9ba5dc891fe67dda05255ac3
}
