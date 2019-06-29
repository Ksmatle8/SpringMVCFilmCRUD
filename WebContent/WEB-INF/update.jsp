<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Edit Film</h3>
	<form action="update.do" method="Get">
		<table>
			<tr>
				<td>Title</td>
				<td>${film.title}</td>
				<td>Edit Title: <input type="text" name="title" /></td>
			</tr>
			<tr>
				<td>Description</td>
				<td>${film.description}</td>
				<td>Edit Description: <input type="text" name="description" /></td>
			</tr>
			<tr>
				<td>Release Year</td>
				<td>${film.releaseYear}</td>
				<td>Edit Release Year: <input type="text" name="releaseYear" /></td>
			</tr>
			<tr>
				<td>Rating</td>
				<td>${film.rating}</td>
				<td>Edit Rating: <select name="rating">
						<option value="G">G</option>
						<option value="PG">PG</option>
						<option value="PG13">PG13</option>
						<option value="R">R</option>
						<option value="NC17">NC17</option>
				</select>
				</td>
			</tr>
		</table>
		<input type="hidden" name="id" value="${film.id}" />
		<input type="submit" name="Edit Film" />
		
	</form>

</body>
</html>