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
				<a href="findfilmbyid.do" name="fid" value="${film.id }">${film.title}</a>
				<br>
				<li>${film.id}</li>
				<br>
				<li>${film.description}</li>
				<br>
				<form action="findfilmbyid.do">
					<input type="hidden" name="FID" value="${film.id }" /> <input
						type="submit" action="findfilmbyid.do" value="Single Page"></>
				</form>
				<p>==========================</p>
			</c:forEach>

		</c:when>
		<c:otherwise>
			<p>No Films Found</p>
		</c:otherwise>
	</c:choose>
<a href="index.html">Go Home</a>
</body>
</html>