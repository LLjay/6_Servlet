<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="menubar.jsp"%>
	
	<br><br>
	
	<h1 aling="center" style="color: red;"><%=request.getAttribute("errorMsg")%></h1>
	<!-- 에러 페이지마다 보여줄 문장 자체를 변수로 받아서 출력하면 페이지 하나에 여러 개 에러를 감당 가능 -->
	
	
</body>
</html>