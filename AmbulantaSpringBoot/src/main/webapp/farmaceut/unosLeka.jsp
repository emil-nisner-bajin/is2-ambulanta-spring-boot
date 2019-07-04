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
	<form action="./dodajLek" method="post">
		Naziv: <input type="text" name="naziv">
		Kolicina: <input type="number" name="kolicina">
		Cena(kom):<input type="number" name="cena">
		Proizvodjac:
		<select name="idProizvodjac">
			<c:forEach items="${result }" var="p">
				<option value="${p.idProizvodjacLeka }">${p.naziv }</option>
			</c:forEach>
		</select>
		<input type="submit" value="Dodaj Lek">
	</form>
	<a href="../farmaceut/farmaceutMenu.jsp">Povratak na meni</a>
</body>
</html>