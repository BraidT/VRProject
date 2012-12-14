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
    <c:forEach var="yksus" items="${yksused}">
		<table width='400px'>
			<tr>
				<td>
					Kood
				</td>
				<td>
					<input type="text" value="<c:out value='${yksus.kood}' />" id="kood">
				</td>
			</tr>
			<tr>
				<td>
					Nimetus
				</td>
				<td>
					<input type="text" value="<c:out value='${yksus.nimetus}' />" id="nimetus">
				</td>
			</tr>	
			<tr>
				<td>
					Kommentaar
				</td>
				<td>
					<textarea rows="4" cols="50" name="kommentaar">
						<c:out value="${yksus.kommentaar}" />
					</textarea>
				</td>
			</tr>
			<tr>
				<td>
					Allub
				</td>
				<td>
					<select>
					 	<option value="<c:out value='${yksus.riigi_admin_yksuse_liik_id}' />">
					  		<c:out value="${yksus.riigi_admin_yksuse_liik_id}" />
					  	</option>
					</select>
				</td>
			</tr>
		</table>
	</c:forEach>
	</div>
	</div>
	</body>
</html>