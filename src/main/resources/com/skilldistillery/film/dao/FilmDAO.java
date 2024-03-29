package com.skilldistillery.film.dao;

import java.sql.SQLException;
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
	public List<Actor> findActorsByFilmId(int fId)throws SQLException;
	public String findCategory(int fID);
	public int deleteFilm(Integer filmId) throws Exception;
	
}
