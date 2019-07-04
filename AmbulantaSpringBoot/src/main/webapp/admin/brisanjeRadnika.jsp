<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Brisanje Ranika</title>
</head>
<body>
	<form action="./obrisiRadnika" method="post">
		<label>Radnik:</label>
		<select name="idRadnik">
			<c:forEach items="${radnici }" var="radnik">
				<option value="${radnik.idRadnik}">${radnik.ime } ${radnik.prezime } (${radnik.kategorija.kategorijaNaziv})</option>
			</c:forEach>
		</select>
		<input type="submit" value="Obrisi"/>
	</form>
</body>
</html>