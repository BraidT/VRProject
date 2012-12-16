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
   <a href='./'><img src='pildid/ITK.png' align='right'></a>
   <div id="vorm">
      <ul>
      		<li><a href='VaataAdminAlluvusi'>VaataAdminAlluvusi</a> uus!</li>
			<li><a href='VaataRiigiAdminYksusi'>VaataRiigiAdminYksusi</a> uus!</li>
			<li><a href='VaataRiigiAdminYksuseLiike'>VaataRiigiAdminYksuseLiike</a> uus!</li>
			<li><a href='VaataVoimalikeAlluvusi'>VaataVoimalikeAlluvusi</a> uus!</li>
		</ul>
		 Vaata admin üksuste alluvusraport<p>
		 <form method='POST' action='' accept-charset="UTF-8">
		 <table width="300px">
		 	<tr>
		 		<td>
		 			Kuupäev
		 		</td>
		 		<td>
		 			Liik
		 		</td>
		 		<td>
		 			&nbsp;
		 		</td>
		 	</tr>
		 	<tr>
		 		<td><input type="text" value=""></td>
		 		<td>
					<select name='liik'>
						<c:forEach var="liik" items="${liigid}">
						 	<option value="<c:out value='${liik.id}' />">
						  		<c:out value="${liik.nimetus}" />
						  	</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<input type="submit" value="Värskenda">
				</td>
			</tr>
		</table>
		</form>
		<p>
		<table border="1" width="300px">
			<c:forEach var="yksus" items="${yksused}">
			<tr class="rida" onmouseover="this.className='rida1'" onmouseout="this.className='rida'">
				<td>
				<form method='POST' action='' accept-charset="UTF-8">
					<a href="V2?ID=<c:out value='${yksus.id}' />"><c:out value="${yksus.nimetus}" /></a>
				<input type="hidden" name="alluv" value="<c:out value='${yksus.id}' />">
				<input type="submit" value="Vaata">
				</form>
				</td>				
			</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	</body>
</html>