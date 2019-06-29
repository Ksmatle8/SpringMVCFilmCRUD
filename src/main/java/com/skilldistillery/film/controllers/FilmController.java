package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO dao;

	@RequestMapping(path = "findfilmbyid.do", method = RequestMethod.GET)
	public ModelAndView getFilmByID(@RequestParam("FID") int fid) {
		ModelAndView mv = new ModelAndView();
		System.out.println(fid);

		Film film = dao.findFilmById(fid);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/filmbyid.jsp");

		return mv;
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

	@RequestMapping(path="filmbykeyword.do", method=RequestMethod.GET)
    public ModelAndView findFilmByKeyword(String filmKeyword) {
		System.out.println(filmKeyword);
        List<Film> films = dao.findFilmByKeyword(filmKeyword);
        System.out.println("through the DAO" + films.size());
        ModelAndView mv = new ModelAndView();
        mv.addObject("films", films);
        mv.setViewName("WEB-INF/filmList.jsp");
        return mv;
    }
	@RequestMapping(path="update.do", method=RequestMethod.GET)
	public ModelAndView updateFilm(int filmId) {
		Film film = dao.findFilmById(filmId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/update.jsp");
		mv.addObject("film", film);
		
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
