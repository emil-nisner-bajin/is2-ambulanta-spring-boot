<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pretraga pacijenata</title>
</head>
<body>
	<form action="../Doktor/nadjiPacijenta" method="get">
		JMBG:
		<input type="text" name="jmbg">
		<input type="submit" value="Pretraga">
	</form>
</body>
</html>