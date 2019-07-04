<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dodavanje Radnika</title>
</head>
<body>
	<form action ="./dodajRadnika" method="post">
		Ime:
		<input type="text" name="imeRadnika" />
		Prezime:
		<input type="text" name="prezime" />
		Passsword:
		<input type="password" name="passwordRadnik" />
		Username:
		<input type="text" name="usernameRadnik"/>
		Kategorija:
		<select name="kategorijaId">
			<c:forEach items="${kategorije}" var="k">
				<option value="${ k.id}">${k.kategorijaNaziv}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Dodaj Radnika">
	</form>
</body>
</html>