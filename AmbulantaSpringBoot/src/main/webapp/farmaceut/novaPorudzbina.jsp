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
	<form action="./poruciLek" method="post">
		<select name="idLek">
			<c:forEach items="${result}" var="lek">
				<option value="${lek.idLek }">${lek.naziv} (${lek.proizvodjacLeka.naziv})</option>
			</c:forEach>
		</select>
		<input type="number" name="kolicina">
		<input type="submit" value="Dodaj Lek">
	</form>
	
	<c:if test="${!empty porudzbinaLekova }">
		<table><tr><th>Lek</th><th>Proizvodjac</th><th>Kolicina</th><th>Cena(kom)</th></tr>
		<c:forEach items="${porudzbinaLekova}" var="lek">
			<tr><td>${lek.naziv }</td><td>${lek.proizvodjacLeka.naziv }</td><td>${lek.kolicina }</td><td>${lek.cenaPoKomadu }</td></tr>
		</c:forEach>
		</table>
	</c:if>
	<form action="../Reports/izvestajPorudzbine">
		<input type="submit" value="Generisi izvestaj o porudzbini">
	</form>
	<a href="../farmaceut/farmaceutMenu.jsp">Povratak na meni</a>
</body>
</html>