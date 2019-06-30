package com.skilldistillery.film.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Film;


@Component
public interface FilmDAO {

	public Film findFilmById(int filmId);
	public List<Film> findFilmByKeyword(String filmKeyword);
	public int addFilm(Film film);
	public int updateFilm(Film film);
	public int deleteFilm(Integer filmId) throws Exception;
	
}
