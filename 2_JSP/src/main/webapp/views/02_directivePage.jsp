<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- errorPage="error500.jsp" 오류 페이지를 직접 지정해줄 수 있음-->
<%@ page import="java.util.ArrayList, java.util.Date"%>
<!-- java 클래스 import는 여기서 써줘야 함 -->
<!-- 원래 속성에는 값이 하나만 들어가는 게 기본적이나, import는 여러 개 들어갈 수 있기 때문에 따로 빼줌 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>page 지시어</h1>
	
	<%
		ArrayList<String> list = new ArrayList<>();
		Date today = new Date();
		list.add("Servlet");
		list.add("JSP");
	%>
	
	현재 날짜 및 시간 : <%= today%> <br>
	0번째 인덱스 : <%= list.get(0) %> <br>
</body>
</html>