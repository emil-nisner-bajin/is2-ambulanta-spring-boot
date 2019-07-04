<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Zakazivanje pregleda</title>
</head>
<body>
	<h1>Zakazivanje pregleda</h1>
	<h2>Doktor: ${d.ime } ${d.prezime }</h2>
	<form action="./zakazi" method="post">
		<input type="date" name="datum">
		<input type="time" name="time">
		<input type="submit" value="Zakazi Pregled">
	</form>
</html>