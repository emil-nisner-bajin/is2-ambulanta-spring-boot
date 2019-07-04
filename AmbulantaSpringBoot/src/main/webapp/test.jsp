<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ambulanta</title>
</head>
<body>
	<center><h1>Dobro dosli u online ambulantu<h1></h1></center>
	<div class="message-container">
		<c:if test="${! empty messages}">
			<c:forEach items="${messages}" var="msg">
				<label class="message">${msg}</label>
			</c:forEach>
		</c:if>
	</div>
	
	<div class="login-register-option-container">
		<a href="./index.jsp?prijava" class="button">Prijava</a>
		<a href="./?registracija" class="button">Registracija</a>
	</div>
	
	<c:if test="${param.prijava ne null}">
		<div class="login-container">
			<form class="login-form" method="post" action="./Autentifikacija/Login">
				<label>Korisnicko Ime:</label>
				<input type="text" name="username">
				<label>Lozinka:</label>
				<input type="password" name="password">
				<input type="submit" name="login" value="Prijava">
			</form>
		</div>
	</c:if>
	
	<c:if test="${param.registracija ne null}">
		<div class="register-container">
			<form class="register-form" method="post" action="./Autentifikacija/Register">
				<label>Register</label>
				<label>Ime:</label>
				<input type="text" name="pacijentIme">
				<label>Prezime:</label>
				<input type="text" name="pacijentPrezime">
				<label>JMBG:</label>
				<input type="text" name="pacijentJmbg">
				<label>Datum rodjenja</label>
				<input type="date" name="pacijentDatumRodjenja">
				<label>Korisnicko ime:</label>
				<input type="text" name="pacijentUsername">
				<label>Lozinka:</label>
				<input type="password" name="pacijentPassword">
				<label>Ponovi Lozinku:</label>
				<input type="password" name="pacijentPasswordRepeat">
				<input type="submit" name="register" value="Registruj se">
			</form>
		</div>
	</c:if>
	
</body>
</html>