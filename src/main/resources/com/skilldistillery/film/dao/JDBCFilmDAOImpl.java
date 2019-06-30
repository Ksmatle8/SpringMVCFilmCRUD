package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class JDBCFilmDAOImpl implements FilmDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String user = "student";
	private final String pass = "student";

	public JDBCFilmDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {

		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, film.title, film.description, film.release_year, language.name, film.rental_duration, film.rental_rate, film.length, film.replacement_cost,film.rating, film.special_features FROM film Join language on film.language_id = language.id Where film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				String langId = rs.getString("language.name");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> actors = findActorsByFilmId(filmId);
				String category = findCategory(filmId);
				if (category.isEmpty()) {
					category ="Categorys not found";
				}
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, actors, category);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	public String findCategory(int fId) {
		String category = "";
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, category.name FROM film "
					+ "JOIN category WHERE film.id = category.id and film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fId);
			ResultSet catResult = stmt.executeQuery();
			while (catResult.next()) {
				category = catResult.getString(2);
			}
			catResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	public List<Film> findFilmByKeyword(String filmKeyword) {

		List<Film> films = new ArrayList<>();
		System.out.println("In the dao, keyword = " + filmKeyword);
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, film.title, film.description, film.release_year, language.name, "
					+ "film.rental_duration, film.rental_rate, film.length, film.replacement_cost, "
					+ "film.rating, film.special_features"
					+ " FROM film Join language on film.language_id = language.id WHERE film.title LIKE ? OR film.description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + filmKeyword + "%");
			stmt.setString(2, "%" + filmKeyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("film.id"));
				film.setTitle(rs.getString("film.title"));
				film.setDescription(rs.getString("film.description"));
				film.setReleaseYear(rs.getInt("film.release_year"));
				film.setLanguageId(rs.getString("language.name"));
				film.setRentalDuration(rs.getInt("film.rental_Duration"));
				film.setRentalRate(rs.getDouble("film.rental_rate"));
				film.setLength(rs.getInt("film.length"));
				film.setReplacementCost(rs.getDouble("film.replacement_cost"));
				film.setRating(rs.getString("film.rating"));
				film.setSpecialFeatures(rs.getString("film.special_features"));
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public int addFilm(Film film) {
		Film newFilm = null;
		int unusedId = 0;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);

			String sql = "INSERT INTO film (title, description, language_id, release_year, rating) VALUE (?, ?, 1, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setString(4, film.getRating());

			int uc = stmt.executeUpdate();

			ResultSet keys = stmt.getGeneratedKeys();
			while (keys.next()) {
				int newId = keys.getInt(1);
				System.out.println(newId);
				unusedId = newId;
			}
			conn.commit();
			conn.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unusedId;
	}

	public int updateFilm(Film film) {
		Film newFilm = null;
		int unusedId = 0;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);

			String sql = "UPDATE film Set title = ?, description = ?, release_year = ?, rating = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setString(4, film.getRating());
			stmt.setInt(5, film.getId());

			int uc = stmt.executeUpdate();

			ResultSet keys = stmt.getGeneratedKeys();
			while (keys.next()) {
				int newId = keys.getInt(1);
				unusedId = newId;
			}
			conn.commit();
			conn.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unusedId;
	}

	@Override
	public List<Actor> findActorsByFilmId(int fID) throws SQLException {
		List<Actor> actors = new ArrayList<>();
		int unusedId = 0;
		Actor actor = null;
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name "
					+ " From film join film_actor ON film.id = film_actor.film_id"
					+ " Join actor ON film_actor.actor_id = actor.id" + " WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fID);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				actor = new Actor(); 
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
				actors.add(actor);
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public int deleteFilm(Integer filmId) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);

			String sql = "DELETE FROM film WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, filmId);
			int uc = pstmt.executeUpdate();
			conn.commit();
			return uc;
		} catch (SQLException e) {
			System.out.println("Film cannot be deleted");
			conn.rollback();

		} finally {
			conn.close();
		}
		return filmId;
	}
	public String findCategory(int fID) {
		String category = "";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, category.name FROM film "
					+ "JOIN category WHERE film.id = category.id and film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fID);
			ResultSet catResult = stmt.executeQuery();
			while (catResult.next()) {
				category = catResult.getString(2);
			}
			catResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
}
