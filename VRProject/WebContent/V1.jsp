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
			<jsp:include page="pealdis.jsp"/>
			<p>
				<c:forEach var="yksuseLiik" items="${yksusteLiigid}">

					<table width="880px">
						<tr>
							<td width="50%">
								<form method='POST' action='' accept-charset="UTF-8">
									<table width='400px'>
										<tr>
											<td>Kood</td>
											<td><input type="text"
												value="<c:out value='${yksuseLiik.kood}' />" name="kood">
											</td>
										</tr>
										<tr>
											<td>Nimetus</td>
											<td><input name="nimetus" type="text"
												value="<c:out value='${yksuseLiik.nimetus}' />"></td>
										</tr>
										<tr>
											<td>Kommentaar</td>
											<td><textarea rows="4" cols="30" name="kommentaar"><c:out value="${yksuseLiik.kommentaar}" /></textarea></td>
										</tr>
										<tr>
											<td>Allub</td>
											<td><select name='ylemus'>
													<c:forEach var="ylemused" items="${ylemused}">

														<option value="<c:out value='${ylemused.id}' />">
															<c:out value="${ylemused.nimetus}" />
														</option>
													</c:forEach>
											</select></td>
										</tr>
									</table>
									<input type="hidden" name="id"
										value="<c:out value='${yksuseLiik.id}' />">

								</form>

							</td>
							<td width="50%">
								<form method='POST' action='' accept-charset="UTF-8">

									<table width="300px">
										<tr class="pealkiri">
											<td colspan="2">Alluvad</td>
										</tr>
										<c:forEach var="alluvus" items="${alluvad}">
											<tr>
												<td><a href="V1?ID=<c:out value='${alluvus.id}' />">
														<c:out value='${alluvus.nimetus}' />
												</a></td>
												<td align='right'><input type="hidden" name="alluv"
													value="<c:out value='${alluvus.id}' />"> <input
													type="hidden" name="ylemus"
													value="<c:out value='${yksuseLiik.id}' />"><input
													type='submit' value='eemalda'></td>
											</tr>
										</c:forEach>
										<tr>
											<td colspan="2" align='right'><input type="button"
												value="lisa"
												onclick='window.location = "LisaVoimalikAlluv?id=<c:out value='${yksuseLiik.id}' />"'>
											</td>
									</table>
								</form>
							</td>
						</tr>
					</table>

				</c:forEach>
			<p>
			<div align="center">
				<input type='submit' value='salvesta' /> <input type="button"
					value="loobu" onclick='window.location = "index.jsp"'>
			</div>
			<p>&#187; PS! <a href='VaataRiigiAdminYksuseLiike'>Vaata kõiki riigi admin üksuse liike</a>!
	
		</div>
	</div>

</body>

</html>