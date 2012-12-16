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
		 <u>&#187; V4 - Admin üksuse liigi struktuur</u><p>
		 <c:set var="i" value="1"/>
			<c:forEach var="liik" items="${liigid}">
				<c:forEach var="num" begin="0" end="${liik.alluv_id}" step="1">&#187;&#187;</c:forEach>
				&nbsp;<c:out value="${liik.nimetus}" /><br>
			</c:forEach>
	</div>
	</div>
	</body>
</html>