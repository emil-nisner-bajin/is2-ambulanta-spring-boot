<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav>
	<ul id="menu">
		<li><a href="${pageContext.request.contextPath}/">Home</a></li>
       <sec:authorize access="hasRole('admin')">
       <li><a href="${pageContext.request.contextPath}/admin/administracijaRadnika">Administracija Radnika</a></li>
       <li><a href="${pageContext.request.contextPath}/Reports/izvestajPacijenata">Izvestaj Pacijenata</a></li>
       </sec:authorize>
       <sec:authorize access="hasRole('doktor')">
         <li><a href="${pageContext.request.contextPath}/Doktor/danasnjiPregledi">Danasnji Raspored</a></li>
         <li><a href="${pageContext.request.contextPath}/doktor/pretragaDatuma.jsp">Pretraga Pregleda</a></li>
         <li><a href="${pageContext.request.contextPath}/doktor/pretragaPacijenta.jsp">Pacijenti</a></li>
       </sec:authorize>
       <sec:authorize access="hasRole('farmaceut')">
       <li><a href="${pageContext.request.contextPath}/Farmaceut/popuniProizvodjace">Dodavanje Leka</a>
       <li><a href="${pageContext.request.contextPath}/Farmaceut/popuniLekove">Napravi Porudzbinu</a>
       <li><a href="${pageContext.request.contextPath}/farmaceut/dodajProizvodjaca">Dodavanje Proizvodjaca</a>
       </sec:authorize>
    	</ul>
</nav>