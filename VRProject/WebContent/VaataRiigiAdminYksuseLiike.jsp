<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="stiil.css">
		<title>Admin üksus!</title>
	</head>
	<body>	
 <div id="sisu">
  	<div id="vorm">
      <jsp:include page="pealdis.jsp"/>
		 Vaata admin üksuse liike:<p>
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
			</tr>
			<c:forEach var="liik" items="${liigid}">
			<tr class="rida" onmouseover="this.className='rida1'" onmouseout="this.className='rida'">
				<td><c:out value="${liik.id}" /></td>
				<td><c:out value="${liik.avaja}" /></td>
				<td><c:out value="${liik.avatud}" /></td>
				<td><c:out value="${liik.muutja}" /></td>
				<td><c:out value="${liik.muudetud}" /></td>
				<td><c:out value="${liik.sulgeja}" /></td>
				<td><c:out value="${liik.suletud}" /></td>
				<td><c:out value="${liik.kood}" /></td>
				<td><c:out value="${liik.nimetus}" /></td>
				<td><c:out value="${liik.kommentaar}" /></td>
				<td><c:out value="${liik.alates}" /></td>
				<td><c:out value="${liik.kuni}" /></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	</body>
</html>