<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 스크립틀릿(scriptlet) == html 문서 내에서 자바 코드를 쓸 수 있는 영역
		// jsp 문서의 특징
		
		String name = (String)request.getAttribute("name");
	// attribute에 한 번 들어갔다 반환된 데이터의 유형은 무조건 Object, 다운캐스팅 해줘야 함
	%>
	
	<h1>개인정보 응답화면 - POST</h1>
	<span id="name"><%=name%></span>
	<!-- 자바의 변수를 그대로 출력해서 보여줄게 -->
	<!-- jsp를 이용해 좀 더 동적으로 요청에 응답할 수 있게 됨 -->
</body>
</html>