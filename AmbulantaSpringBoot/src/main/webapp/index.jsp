<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ambulanta</title>
	<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
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
	
	<c:if test="${empty reloaded}">
		<div class="login-register-option-container">
			<a href="./Autentifikacija/Prijava" class="button">Prijava</a>
			<a href="./Autentifikacija/Registracija" class="button">Registracija</a>
		</div>
	</c:if>
	
	<c:if test="${!empty reloaded or prijava ne null or registracija ne null }">
		<div class="login-register-option-container">
			<a href="./Prijava" class="button">Prijava</a>
			<a href="./Registracija" class="button">Registracija</a>
		</div>
	</c:if>
	
	
	<c:if test="${prijava ne null}">
		<div class="login-container">
			<form class="login-form" method="post" action="../Autentifikacija/Login">
				<label>Korisnicko Ime:</label>
				<input type="text" name="username" required>
				<label>Lozinka:</label>
				<input type="password" name="password" required>
				<input type="submit" name="login" value="Prijava">
			</form>
		</div>
	</c:if>
	
	<c:if test="${registracija ne null}">
		<div class="register-container">
			<form class="register-form" method="post" action="./Register" required>
				<label>Register</label>
				<label>Ime:</label>
				<input type="text" name="pacijentIme" required>
				<label>Prezime:</label>
				<input type="text" name="pacijentPrezime" required>
				<label>JMBG:</label>
				<input type="text" name="pacijentJmbg" required>
				<label>Datum rodjenja</label>
				<input type="date" name="pacijentDatumRodjenja" required>
				<label>Korisnicko ime:</label>
				<input type="text" name="pacijentUsername" required>
				<label>Lozinka:</label>
				<input type="password" name="pacijentPassword" required>
				<label>Ponovi Lozinku:</label>
				<input type="password" name="pacijentPasswordRepeat" required>
				<input type="submit" name="register" value="Registruj se">
			</form>
		</div>
	</c:if>
	
</body>
</html>