<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- TODO: Add the @taglib for form -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find Film By Id</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty film }">
			<h1>${film.title}</h1>
			<ul>
				<li>Description: ${film.description}</li>
				<li>Release year: ${film.releaseYear}</li>
				<li>Rating: ${film.rating}</li>
				<li>Cast:</li>
        <c:forEach items="${film.actors}" var="actor">
				<p>${actor.getFirstName()} ${actor.getLastName()}</p>
				</c:forEach>
        <li>${film.category}</li>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No Films Found</p>
		</c:otherwise>
	</c:choose>
</body>
</html>