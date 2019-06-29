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
	<li>Language: ${film.languageId}</li>
	<li>Rental Duration: ${film.rentalDuration}</li>
	<li>Rental Rate: ${film.rentalRate}</li>
	<li>Length: ${film.length}</li>
	<li>Replacement Cost: ${film.replacementCost}</li>
	<li>Rating: ${film.rating}</li>
	<li>Special Features: ${film.specialFeatures}</li>
	</ul>
	</c:when>
	<c:otherwise>
	<p>No Films Found</p>
	</c:otherwise>
  </c:choose>
  <a href="update.jsp">Edit Film Information</a><br>
  <a href="index.html">Go Home</a>
</body>
</html>