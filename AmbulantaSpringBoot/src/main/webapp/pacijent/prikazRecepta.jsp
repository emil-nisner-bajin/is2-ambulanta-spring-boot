<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prikaz Recepata</title>
</head>
<body>
	<c:if test="${empty recepti }">
		<h3>Nema recepta za prikaz</h3>
	</c:if>
	
	<c:if test="${! empty recepti }">
	 	<table>
	 		<tr><th>Doktor</th><th>Lek</th><th>Proizvodjac Leka</th></tr>
	 		<c:forEach items="${recepti }" var="r">
	 			<tr><td>${r.radnik.ime } ${r.radnik.prezime }</td><td>${r.lek.naziv }</td><td>${r.lek.proizvodjacLeka.naziv }</td></tr>
			</c:forEach>
	 	</table>
	</c:if>
	
	<a href="../pacijent/pacijentMenu.jsp">Back</a>
</body>
</html>