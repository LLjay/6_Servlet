<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Include 지시어</h1>
	<div style="border: 1px solid black">
		<%@ include file="01_ScriptingElement.jsp"%>
	</div>
	
	포함한 jsp 상에 선언되어 있는 변수를 가져다 사용 가능 <br>
	1부터 100까지의 총 합계 : <%= sum %>
	
	<!-- 이벤트나 라이브러리가 헤더에도 파일에도 포함되어 있으면 중복해서 들어오게 됨 -->
	<!-- 만약 클릭하고 꺼지면 따닥 하고 두 번 실행되어 제대로 안 됨 -->
</body>
</html>