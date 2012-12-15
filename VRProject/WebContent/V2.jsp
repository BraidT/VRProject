<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="stiil.css">
		<title>Vaata admin üksust!</title>
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
   
    <form method='POST' action='' accept-charset="UTF-8">
    <c:forEach var="yksus" items="${yksused}">
		<table width='400px'>
			<tr>
				<td>
					Kood
				</td>
				<td>
					<input type="text" value="<c:out value='${yksus.kood}' />" name="kood">
				</td>
			</tr>
			<tr>
				<td>
					Nimetus
				</td>
				<td>
					<input name="nimetus" type="text" value="<c:out value='${yksus.nimetus}' />">
				</td>
			</tr>	
			<tr>
				<td>
					Kommentaar
				</td>
				<td>
					<textarea rows="4" cols="50" name="kommentaar"><c:out value="${yksus.kommentaar}" />
					</textarea>
				</td>
			</tr>
			<tr>
				<td>
					Liik
				</td>
				<td>
					<select name='liik'>
					 	<option value="<c:out value='${yksus.riigi_admin_yksuse_liik}' />">
					  		<c:out value="${yksus.riigi_admin_yksuse_liik}" />
					  	</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					Allub
				</td>
				<td>
					<select name='allub'>
					 	<option value="<c:out value='${yksus.riigi_admin_yksuse_liik_id}' />">
					  		<c:out value="${yksus.riigi_admin_yksuse_liik_nimetus}" />
					  	</option>
					</select>
				</td>
			</tr>
		</table>
	<input type="hidden" name="id" value="<c:out value='${yksus.id}' />">
	<div align="center">
		<input type='submit' value='salvesa vorm' />
	</div>
	</c:forEach>
	</form>
	<p>&nbsp;<p>&#187; PS! <a href='VaataRiigiAdminYksusi'>Vaata kõiki riigi admin üksusi</a>!
	</div>
	</div>
	</body>
</html>