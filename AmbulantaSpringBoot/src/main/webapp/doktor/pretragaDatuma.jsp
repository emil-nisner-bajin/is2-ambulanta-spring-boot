<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Pretraga pregleda za odredjeni datum
	<form action="../Doktor/preglediZaDatum">
		Datum:<input type="date" name="datum">
		<input type="submit" value="Prikazi">
	</form>
	<c:if test="${! empty result }">
		<table>
			<tr><th>Pacijent</th><th>JMBG</th><th>Datum</th><th>Vreme</th></tr>
			<c:forEach items="${result }" var="pregled">
					<tr><td>${pregled.pacijent.ime } ${pregled.pacijent.prezime }</td><td>${pregled.pacijent.jmbg }</td><td>${pregled.datum }</td><td>${pregled.vreme }</td></tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty result }">
		<h2>Nema pregleda za odabrani datum</h2>
	</c:if>
	<a class="button" href="../doktor/doktorMenu.jsp">Povratak na meni</a>
</body>
</html>