<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jsp:forward</h1>
    <!-- 바로 다른 페이지를 포워딩 가능 -->
    <!-- a 태그는 페이지 url을 다시 왔다가 가는 것, 이 forward는 서버에 페이지를 요청하는 것 -->
    <jsp:forward page="footer.jsp"/>
</body>
</html>