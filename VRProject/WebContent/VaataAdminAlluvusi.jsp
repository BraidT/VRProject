<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="stiil.css">
		<title>Admin Alluvus!</title>
	</head>
	<body>	
 <div id="sisu">
   <a href='./'><img src='pildid/ITK.png' align='right'></a>
   <div id="vorm" style='top:50px'>
   Vaata admin alluvusi:<p>
		<table width='680px'>
			<tr class="pealkiri">
				<td>id</td>
				<td>avaja</td>
				<td>avatud</td>
				<td>muutja</td>
				<td>muudetud</td>
				<td>sulgeja</td>
				<td>suletud</td>
				<td>kommentaar</td>
				<td>alates</td>
				<td>kuni</td>
			</tr>
			<c:forEach var="alluvus" items="${alluvused}">
			<tr>
				<td><c:out value="${alluvus.id}" /></td>
				<td><c:out value="${alluvus.avaja}" /></td>
				<td><c:out value="${alluvus.avatud}" /></td>
				<td><c:out value="${alluvus.muutja}" /></td>
				<td><c:out value="${alluvus.muudetud}" /></td>
				<td><c:out value="${alluvus.sulgeja}" /></td>
				<td><c:out value="${alluvus.suletud}" /></td>
				<td><c:out value="${alluvus.kommentaar}" /></td>
				<td><c:out value="${alluvus.alates}" /></td>
				<td><c:out value="${alluvus.kuni}" /></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	</body>
</html>