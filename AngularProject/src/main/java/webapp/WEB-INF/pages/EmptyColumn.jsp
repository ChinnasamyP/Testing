<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Empty Column</title>
</head>
<body>
		<form action="/showNullColumn" method ="GET">
		<c:forEach var="Values">  
   		column <c:out value="${Values}"/><p>  
		</c:forEach>
		</form>
</body>
</html>