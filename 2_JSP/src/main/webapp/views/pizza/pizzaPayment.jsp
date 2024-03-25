<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String)request.getAttribute("name");
	String phone = (String)request.getAttribute("phone");
	String address = (String)request.getAttribute("address");
	String message = (String)request.getAttribute("message");

	String pizza = (String)request.getAttribute("pizza");
	String[] topping = (String[])request.getAttribute("topping");
	String[] side = (String[])request.getAttribute("side");
	String payment = (String)request.getAttribute("payment");
	payment = payment.equals("cash") ? "현금" : "카드";
	int price = (int)request.getAttribute("price");
	// 이 페이지 내에서 이 변수를 사용할 수 있다
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>피자 결제 페이지</h1>
	<h2>주문 내역</h2>
	<h4>[ 주문자 정보 ]</h4>
	<ul>
		<li>성함 : <%=name%></li>
		<li>전화번호 : <%=phone%></li>
		<li>주소 : <%=address%></li>
		<li>요청사항 : <%=message%></li>
	</ul>

	<br>

	<h4>[ 주문 정보 ]</h4>
	<ul>
		<li>피자 : <%=pizza%></li>
		<% if(topping == null) {%>
			<li>토핑 : 선택 안 함</li>
		<% } else { %>
			<li> 토핑 : <%= String.join(", ", topping) %></li>
		<% } %>

		<% if(side == null) {%>
			<li>사이드 : 선택 안 함</li>
		<% } else { %>
			<li> 토핑 : <%= String.join(", ", side) %></li>
		<% } %>

		<li>결제 방식 : <%=payment%></li>
	</ul>

	<h3>위와 같이 주문하셨습니다.</h3>
	<h2>총 가격 : <%=price%> 원</h2>
</body>
</html>