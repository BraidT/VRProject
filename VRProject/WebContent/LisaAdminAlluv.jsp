<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="stiil.css">
		<title>Lisa admin alluvus!</title>
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
    Lisa admin alluvusi:<p>
    <form method='POST' action='' accept-charset="UTF-8">
			<c:forEach var="yksus" items="${yksused}">
				<input type="checkbox" name="alluv_id" value="<c:out value='${yksus.id}' />">
				<a href="V2?ID=<c:out value='${yksus.id}' />">
					<c:out value="${yksus.nimetus}" />
				</a>
				<br>
				<input type="hidden" name="id" value="<c:out value='${yksus.id}' />">
			</c:forEach>
			<c:if test="${empty yksused}">
				Ühtegi alluvat ei saa hetkel lisada! :(
			</c:if>
			<c:if test="${not empty yksused}">
				<input type='submit' value='lisa'>
			</c:if>
			
	</form>
	</div>
	</div>
	</body>
</html>