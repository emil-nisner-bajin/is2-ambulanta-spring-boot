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
	<c:if test="${! empty msg }">
		<p>${msg}</p>
		<a href="./popuniLekare">Izaberi Lekara</a>
	</c:if>

	<c:if test="${empty msg }">
		<a href="./popuniLekare">Izaberi Lekara</a>
		<a href="./infoZakazivanjaPregleda">Zakazi Pregled</a>
		<a href="./prikaziRecepte">Prikaz Recepata</a>
	</c:if>
	
	<div class="personal-info">
	</div>
</body>
</html>