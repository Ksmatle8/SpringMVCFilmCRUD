package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO dao;

	@RequestMapping(path = "findfilmbyid.do", method = RequestMethod.GET)
	public ModelAndView getFilmByID(@RequestParam(name = "FID") int fid) {
		ModelAndView mv = new ModelAndView();
		Film f;
		List<Actor> actors;
		try {
			f = dao.findFilmById(fid);
			mv.addObject("film", f);
			actors = dao.findActorsByFilmId(fid);
			mv.addObject("actors", actors);
			mv.setViewName("WEB-INF/filmbyid.jsp");
		} catch  (SQLException e) {
			mv.setViewName("error");
			e.printStackTrace();
		}
		return mv;

//		System.out.println(fid);
//		Film film = dao.findFilmById(fid);
//		mv.addObject("film", film);
//		mv.setViewName("WEB-INF/filmbyid.jsp");
//		
//		return mv;
	}
	

	@RequestMapping(path = "AddFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilmByID(Film film) {
		int newId = dao.addFilm(film);
		System.out.println(newId);
		Film newFilm = dao.findFilmById(newId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", newFilm);
		mv.setViewName("WEB-INF/displayNewFilm.jsp");
		return mv;
	}

	@RequestMapping(path="filmbykeyword.do", method = RequestMethod.GET)
    public ModelAndView findFilmByKeyword(String filmKeyword) {
		System.out.println(filmKeyword);
		List<Film> films = dao.findFilmByKeyword(filmKeyword);
		System.out.println("through the DAO" + films.size());
		ModelAndView mv = new ModelAndView();
		mv.addObject("films", films);
		mv.setViewName("WEB-INF/filmList.jsp");
		return mv;
	}

	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film filmToUpdate) {
		System.out.println(filmToUpdate);
		int newId = dao.updateFilm(filmToUpdate);
		System.out.println(newId);
		Film film = dao.findFilmById(filmToUpdate.getId());
		System.out.println(film);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/displayNewFilm.jsp");
		return mv;
	}

	@RequestMapping(path = "updatePage.do", method = RequestMethod.POST)
	public ModelAndView updatePage(@RequestParam("FID") int fid) {
		ModelAndView mv = new ModelAndView();
		Film film = dao.findFilmById(fid);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/forms/update.jsp");
		return mv;
	}

	@RequestMapping(path = "delete.do", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("FID")int  filmId) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			dao.deleteFilm(filmId);
			mv.setViewName("WEB-INF/deleteFilm.jsp");
		} catch (SQLException e) {
			mv.setViewName("error");
			e.printStackTrace();
		}
		return mv;

	}
	
	
//	@RequestMapping(path="DeleteFilm.do", method=RequestMethod.POST)
//	public ModelAndView deleteFilmById(int filmId) {
//	try {
//		dao.
//	}catch(){
//		
//	}
//  }
}
