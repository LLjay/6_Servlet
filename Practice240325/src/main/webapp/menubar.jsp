<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String contextPath = request.getContextPath();
	// contextPath에는 이 프로젝트의 contextPath, 즉 경로가 들어있음
	Member loginUser = (Member)session.getAttribute("loginUser");
	// 이게 가능한 이유는 Member 객체의 loginUser 키를 session의 attribute로 넣어주기 때문에
	// 로그인 시도 전 => session에 아무것도 넣어주지 않았으므로 null값
	// 로그인 시도 후 => session에 로그인한 회원 정보가 담긴 Member 객체를 넣어주었으므로 로그인 성공한 회원의 정보
	String alertMsg = session.getAttribute("alertMsg");
	// alertMsg는 명령어 아님
	// 그냥 session.setAttribute("alertMsg", "메세지 내용") 으로
	// alertMsg라는 Key 값을 가지고 value 값이 "메세지 내용"인 데이터를 session에 저장하고
	// session에서 alertMsg라는 키 값으로 메세지 내용을 꺼내 String 변수에 담아준 것
	// 서비스 요청 전에는 null, 요청 후에는 alert를 띄워줄 메세지
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<
</body>
</html>