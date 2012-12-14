<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="stiil.css">
		<title>Vaata admin üksus!</title>
	</head>
	<body>	
 <div id="sisu">
   <a href='./'><img src='pildid/ITK.png' align='right'></a>
   <div id="vorm">
	      <ul>
			<li><a href='VaataAdminAlluvusi'>VaataAdminAlluvusi</a> uus!</li>
			<li><a href='VaataRiigiAdminYksusi'>VaataRiigiAdminYksusi</a> uus!</li>
			<li><a href='VaataRiigiAdminYksuseLiike'>VaataRiigiAdminYksuseLiike</a> uus!</li>
			<li><a href='VaataVoimalikeAlluvusi'>VaataVoimalikeAlluvusi</a> uus!</li>
		</ul>
    Vaata riigi admin üksusi:<p>
		<table width='880px'>
			<tr class="pealkiri">
				<td>id</td>
				<td>avaja</td>
				<td>avatud</td>
				<td>muutja</td>
				<td>muudetud</td>
				<td>sulgeja</td>
				<td>suletud</td>
				<td>kood</td>
				<td>nimetus</td>
				<td>kommentaar</td>
				<td>alates</td>
				<td>kuni</td>
				<td>ID1</td>
			</tr>
			<c:forEach var="yksus" items="${yksused}">
			<tr>
				<td><a href="V1?ID=<c:out value='${yksus.id}' />"><c:out value='${yksus.id}' /></a></td>
				<td><c:out value="${yksus.avaja}" /></td>
				<td><c:out value="${yksus.avatud}" /></td>
				<td><c:out value="${yksus.muutja}" /></td>
				<td><c:out value="${yksus.muudetud}" /></td>
				<td><c:out value="${yksus.sulgeja}" /></td>
				<td><c:out value="${yksus.suletud}" /></td>
				<td><a href="V1?ID=<c:out value='${yksus.id}' />"><c:out value="${yksus.kood}" /></a></td>
				<td><a href="V1?ID=<c:out value='${yksus.id}' />"><c:out value="${yksus.nimetus}" /></a></td>
				<td><c:out value="${yksus.kommentaar}" /></td>
				<td><c:out value="${yksus.alates}" /></td>
				<td><c:out value="${yksus.kuni}" /></td>
				<td><c:out value="${yksus.riigi_admin_yksuse_liik_id}" /></td>				
			</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	</body>
</html>