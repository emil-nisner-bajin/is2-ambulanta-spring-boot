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
	<form action="../Farmaceut/dodajProizvodjaca" method="post">
		Naziv Proizvodjaca:<input type="text" name="nazivProizvodjaca">
		<input type="submit" value="Dodaj Proizvodjaca"> 
	</form>
	<a href="../farmaceut/farmaceutMenu.jsp">Povratak na meni</a>
</body>
</html>