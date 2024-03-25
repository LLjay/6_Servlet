<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String userName = (String)request.getAttribute("userName");
	String[] genre = (String[])request.getAttribute("genre");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 성공</h1>
    <h2><%= userName %> 님, 가입을 환영합니다.</h2>
    
    <% if (genre != null) { %>
    	<%=userName %> 님의 취향 :
    	<%= String.join(", ", genre) %>
    <% } %>
</body>
</html>