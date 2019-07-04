<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Danasnji Raspored</title>
</head>
<body>
	<h1>Danasnji Raspored</h1>
	<c:if test="${! empty result }">
		<table>
			<tr><th>Pacijent</th><th>JMBG</th><th>Vreme</th></tr>
			<c:forEach items="${result }" var="pregled">
					<tr><td>${pregled.pacijent.ime } ${pregled.pacijent.prezime }</td><td>${pregled.pacijent.jmbg }</td><td>${pregled.vreme }</td></tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty result }">
		<h2>Nema pregleda za danas</h2>
	</c:if>
	<a class="button" href="../doktor/doktorMenu.jsp">Povratak na meni</a>
</body>	
</html>