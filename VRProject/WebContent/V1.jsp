<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<div id="vorm">
			<%-- <jsp:include page="pealdis.jsp"/> --%>
			Vaata riigi admin üksuste liike:
			<p>
			<table width="880px">
				<c:forEach var="yksuseLiik" items="${yksusteLiigid}">
					<tr>
						<td width="50%">
							<form method='POST' action='' accept-charset="UTF-8">
								<table width='400px'>
									<tr>
										<td>Kood</td>
										<td><input type="text"
											value="<c:out value='${yksuseLiik.kood}' />" name="kood"></td>
									</tr>
									<tr>
										<td>Nimetus</td>
										<td><input name="nimetus" type="text"
											value="<c:out value='${yksuseLiik.nimetus}' />"></td>
									</tr>
									<tr>
										<td>Kommentaar</td>
										<td><textarea rows="4" cols="30" name="kommentaar">
										<c:out value="${yksuseLiik.kommentaar}" />
									</textarea></td>
									</tr>
									<tr>
										<td>Allub</td>
										<td><select name='allub'
											title='Näitab ainult admin üksusi millele on võimalik alluda'>
												<c:forEach var="allub" items="${ylemused}">
													<option value="<c:out value='${ylemus.nimetus}' />">
													</option>
												</c:forEach>
										</select></td>
									</tr>
								</table>
								</
								<div align="center">
									<input type='submit' value='salvesta vorm' />
								</div>
							</form>

						</td>
						<td width="50%"></td>
					</tr>
				</c:forEach>
			</table>

			<table>
				<c:forEach var="alluvus" items="${alluvad}">
					<tr>
						<td><input name="alluv"
							value="<c:out value='${alluvus.nimetus}' />"> <input
							type='submit' value='eemalda'></td>
					</tr>
				</c:forEach>
			</table>


			</p>
		</div>
	</div>
</body>

</html>