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
	<c:choose>
		<c:when test="${! empty films }">
			<c:forEach items="${films}" var="film">
				<li>${film.title}</li>
				<br>
				<li>${film.id}</li>
				<br>
				<li>${film.description}</li>
				<br>
				<p>==========================</p>
			</c:forEach>

		</c:when>
		<c:otherwise>
			<p>No Films Found</p>
		</c:otherwise>
	</c:choose>

</body>
</html>