<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prikaz Profila</title>
</head>
<body>
	<table>
		<tr><td>Ime:</td><td>${pacijent.ime }</td></tr>
		<tr><td>Prezime:</td><td>${pacijent.prezime }</td></tr>
		<tr><td>Datum rodjenja:</td><td>${pacijent.datumRodjenja}</td></tr>
		<tr><td>JMBG</td><td>${pacijent.jmbg }</td></tr>
		<tr><td>Odabrani Lekar</td>
		<c:if test="${! empty pacijent.radnik}">
			<td>${pacijent.radnik.ime } ${pacijent.radnik.prezime }</td></tr>
		</c:if>
		<c:if test="${ empty pacijent.radnik}">
			<td>/</td></tr>
		</c:if>
	</table>
	<a class="button" href="./napisiReceptAkcija?pacijentId=${pacijent.idPacijent }">Napisi Recept</a>
	<a class="button" href="./zakaziPregledAkcija?pacijentId=${pacijent.idPacijent }"">Zakazi Pregled</a>
	<a class="button" href="./popuniPregledAkcija?pacijentId=${pacijent.idPacijent }"">Unesi pregled</a>
	<a class="button" href="../doktor/doktorMenu.jsp">Povratak na meni</a>
	
	<c:if test="${! empty napisiRecept }">
		<form action="./izdajRecept" method="post">
			<input type="hidden" name="pacijentId" value="${pacijent.idPacijent }">
			<select name="lekId">
				<c:forEach items="${lekovi }" var="lek">
					<option value="${lek.idLek}">${lek.naziv } (${lek.proizvodjacLeka.naziv })</option>
				</c:forEach>
			</select>
			<input type="submit" value="Napisi Recept">
		</form>
	</c:if>
	<c:if test="${! empty zakaziPregled }">
		<form action="./zakaziPregled" method="post">
			Datum:<input type="date" name="datumPregleda">
			Vreme:<input type="time" name="vreme">
			<input type="hidden" name="pacijentId" value="${pacijent.idPacijent }">
			<input type="submit" value="Zakazi Pregled">
		</form>
	</c:if>
	<c:if test="${! empty unesiPregled }">
		<form action="./napisiPregled" method="post">
			<input type="hidden" name="pacijentId" value="${pacijent.idPacijent }">
			Opis Pregleda:<input type="textArea" name="opisPregleda">
			<input type="submit" value="unesi Pregled">
		</form>
	</c:if>
</body>
</html>