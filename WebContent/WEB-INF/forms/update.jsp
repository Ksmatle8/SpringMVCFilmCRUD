<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<title>update page</title>
</head>
<body>
	<h3>Edit Film</h3>
	
	<h1>${film.title}</h1>
		<ul>
		<li>Description: ${film.description}</li>
		<li>Release year: ${film.releaseYear}</li>
		<li>Rating: ${film.rating}</li>
		</ul>
		<br>
	<form action="update.do" method="POST">
        <label for="title">Title:</label><input type="text" name="title" value="${film.title}">
        <br> <label for="description">Description:</label>${film.description} <input
            type="text" name="description"> <br> 
            <label for="releaseYear">Release Year:</label> <input type="number"
            name="releaseYear" value="${film.releaseYear}"> <br>
        <h3>Choose a rating:</h3>
        <label><input type="radio" name=" rating" value="G">G</label> <label><input
            type="radio" name="rating" value="PG"> PG  </label> <label><input
            type="radio" name="rating" value="PG13">PG13</label> <label><input
            type="radio" name="rating" value="R">R</label> <label><input
            type="radio" name="rating" value="NC17">"NC17"</label> <br> 
            <input type="submit" value="Add ">
    </form>
</body>
</html>