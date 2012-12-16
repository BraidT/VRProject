<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="stiil.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="v.js"></script>
<title>Veebirakendused 1</title>
</head>
<body onload='javascript:v(1)'>

 <div id="sisu">
   <div id="vorm">
		<jsp:include page="pealdis.jsp"/>
		&#187;Tööülesanded:
		 <a href='V1' onmouseover='javascript:v(1)'>V1</a> | 
		 <a href='V2' onmouseover='javascript:v(2)'>V2</a> | 
		 <a href='V3' onmouseover='javascript:v(3)'>V3</a> | 
		 <a href='V4' onmouseover='javascript:v(4)'>V4</a> | 
		 <a href='V5' onmouseover='javascript:v(5)'>V5</a>
		<div id="v1"></div>
	   	<div id="v2"></div>
	   	<div id="v3"></div>
	   	<div id="v4"></div>
	   	<div id="v5"></div>
		</div>
   </div>
 </div>
</body>
</html>