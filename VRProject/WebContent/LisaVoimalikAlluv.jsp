<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="sisu">
		<div id="vorm">
			<%-- <jsp:include page="pealdis.jsp"/> --%>
			Lisa alluvusi:
			<p>
			<form method='POST' action='' accept-charset="UTF-8">
				<c:forEach var="yksuseLiik" items="${yksuseLiigid}">
					<input type="checkbox" name="alluv_id"
						value="<c:out value='${yksuseLiik.id}' />">
					<a href="V2?ID=<c:out value='${yksuseLiik.id}' />"> <c:out
							value="${yksuseLiik.nimetus}" />
					</a>
					<input type="textarea" name="kommentaar"
						value="<c:out value='${yksuseLiik.kommentaar}' />">
					<br>
					<input type="hidden" name="id"
						value="<c:out value='${yksuseLiik.id}' />">

					<c:if test="${empty yksuseLiik}">
						Ühtegi alluvat ei saa hetkel lisada! :(
					</c:if>
					<c:if test="${not empty yksuseLiik}">
						<input type='submit' value='lisa'>
					</c:if>
				</c:forEach>
			</form>
		</div>
	</div>
</body>
</html>