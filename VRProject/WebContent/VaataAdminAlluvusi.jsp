<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
   <div id="vorm">
      <jsp:include page="pealdis.jsp"/>
		<div id='andmed'>
   Vaata admin alluvusi:<p>
		<table width='850px'>
			<tr class="pealkiri">
				<td>avaja</td>
				<td>avatud</td>
				<td>muutja</td>
				<td>muudetud</td>
				<td>sulgeja</td>
				<td>suletud</td>
				<td>alates</td>
				<td>kuni</td>
				<td title="riigi_admin_yksuse_id">ID1</td>
				<td title="riigi_admin_yksuse_alluva_id">ID2</td>
				<td>komm</td>
			</tr>
			<c:forEach var="alluvus" items="${alluvused}">
			<tr class="rida" onmouseover="this.className='rida1'" onmouseout="this.className='rida'">
				<td><c:out value="${alluvus.avaja}" /></td>
				<td><c:out value="${alluvus.avatud}" /></td>
				<td><c:out value="${alluvus.muutja}" /></td>
				<td><c:out value="${alluvus.muudetud}" /></td>
				<td><c:out value="${alluvus.sulgeja}" /></td>
				<td><c:out value="${alluvus.suletud}" /></td>
				<td><c:out value="${alluvus.alates}" /></td>
				<td><c:out value="${alluvus.kuni}" /></td>
				<td><c:out value="${alluvus.riigi_admin_yksuse_id}" /></td>
				<td><c:out value="${alluvus.riigi_admin_yksuse_alluva_id}" /></td>
				<td><c:out value="${alluvus.kommentaar}" /></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	</div>
	</body>
</html>