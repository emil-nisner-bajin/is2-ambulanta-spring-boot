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
	<form action="./odaberiLekara" method="post">
		<select name="idLekar">
		<c:forEach items="${radnici }" var="r">
			<option value="${r.idRadnik }">${r.ime } ${r.prezime }</option>
		</c:forEach>
	</select>
	<input type="submit" value="Odaberi">
	</form>
</body>
</html>