<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.model.vo.Person" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 
	<h3>1. 기존 방식대로 스크립틀릿과 표현식을 이용하여 각 scope에 담겨 있는 값을 화면에 출력</h3>
	<%
		// request scope 값 가져오기
		String classRoom = (String)request.getAttribute("classRoom");
	// 가져올 때 Object이므로
		Person student = (Person)request.getAttribute("student");
		
		// session scope 값 가져오기
		String academy = (String)session.getAttribute("academy");
		Person teacher = (Person)session.getAttribute("teacher");
	%>
	
	<p>
		학원ㅁㅇ : <%=academy %>
		강의장 : <%=classRoom %>
		강사 : <%=teacher.getName() %>, <%=teacher.getAge() %>, <%=teacher.getGender() %> <br><br>
		수강생 정보
		<ul>
			<li>이름 : <%=student.getName() %></li>
			<li>이름 : <%=student.getAge() %></li>
			<li>이름 : <%=student.getGender() %></li>
		</ul>
		<!-- 이 함수 자체를 다 쓰려니 귀찮음, 실수 가능성도 높아짐 -->
	</p>
	--%>
	
	<h3>2. EL을 이용하여 보다 쉽게 scope의 값을 출력하기</h3>
	<p>
		EL을 이용하여 getxxx을 통해 값을 가져올 필요 없이 EL 구문 내의 키 값만 제시하면 바로 접근 가능 <br>
		기본적으로 EL은 JSP 내장 객체를 구분하지 않고 자동으로 모든 내장 객체의 키 값을 검색하여 존재하는 경우 그 값을 가져옴
	</p>
	<p>
		학원명 : ${academy}
		<!-- 알아서 academy라는 키를 검색해서 조회해서 가지고 오는 것, 특정 객체를 지정해서 꺼내서 담아줄 필요가 없어짐 -->
		강의장 : ${classRoom }
		강사 : ${teacher.name}, ${teacher.age}, ${teacher.gender} <br><br>
		<!-- name이라고만 써도 가져와주지만 이건 실제 getter 메소드가 내부적으로 작동하는 것 -->
		<!-- getter 메소드 생성 안 하면 오류 나는 것 -->
		수강생 정보
		<ul>
			<li>이름 : ${teacher.name}</li>
			<li>이름 : ${teacher.age}</li>
			<li>이름 : ${teacher.gender}</li>
		</ul>
		<!-- 이 함수 자체를 다 쓰려니 귀찮음, 실수 가능성도 높아짐 -->
	</p>
	
	<h3>3. scope의 키 값이 동일한 경우</h3>
	scope 값 : ${scope} <br>
	<!-- 
		EL은 공유 범위가 제일 작은 scope부터 해당 키 값을 검색
		page -> request -> session -> application
	 -->
	 test 값 : ${test} <!-- 공백으로 뜸 / 브라우저니까 바로 보여주니까 null보다는 없애는 게 낫겠다 -->
	
	<h4>4. 직접 scope를 지정하여 접근하기</h4>
	<%
		// page scope에 담기
		pageContext.setAttribute("scope", "page");
	%>
	
	pageScope 값 : ${scope} 또는 ${pageScope.scope} <br>
	requestScope 값 : ${requestScope.scope} <br>
	sessionScope 값 : ${sessionScope.scope} <br>
	applicationScope 값 : ${applicationScope.scope} <br>
</body>
</html>