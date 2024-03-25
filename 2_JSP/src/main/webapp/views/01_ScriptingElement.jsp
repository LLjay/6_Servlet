<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>스크립팅 원소</h1>

    <!-- HTML 주석 : 개발자 도구 탭에 노출됨 -->
    <%-- JSP 주석 : 개발자 도구 탭에 노출되지 않음 --%>
    <!-- 파서... html을 2D로, 돔 요소로 바꿔줄 때 HTML 주석도 실행됨 -->
    
    <%
    	// 스크립틀릿 : 해당 영역에 일반적인 자바 코드 작성 (변수 선언 및 초기화, 제어문 등)
    	int sum = 0;
    	for (int i= 1; i <= 100; i++){
    		sum += 1;
    	}
    	// 이 결과값을 출력하고자 한다면 따로 출력하는 html 작성 필요
    %>
    
    <p>
    	화면으로 출력하고자 한다면 <br>
    	스크립틀릿 이용해서 출력 가능 : <% out.println(sum); %>
    	<!-- out : html로 보내주던 역할 -->
    	표현식(출력식) 이용해서 출력 가능 : <%= sum %>
    </p>
    
    <%
    	String[] nameArr = {"이지수", "전은지", "윤현지"};
    %>
    
    <h5>배열의 길이 : <%= nameArr.length %></h5>
    <h5>배열의 길이 : <%= String.join(", ", nameArr) %></h5>
    
    <h4>반복문을 통해서 html 요소를 반복적으로 화면에 출력할 수 있음</h4>
    
    <!-- 스크립틀릿 안에도 java 코드 사용 가능 -->
    
    <ul>
    	<% for(int i = 0; i < nameArr.length; i++) { %>
    		<li><%= nameArr[i] %></li>
    	<%}%>
    	
    	<% for(String name : nameArr) { %>
    		<li><%= nameArr[i] %></li>
    	<%}%>
    </ul>
    <!-- 
    ctrl+shift+R 로 브라우저 새로고침 할 것 
    url이 안 바뀌면 이전 url에서 요청한 정보가 이미 있으므로 서버의 수정 반영하지 않고 이전 정보를 보여줌 => 캐싱 때문
    그걸 방지하는 게 컨트롤쉬프트알
    혹은 시크릿 창 켜서 하기
    -->
    
    

</body>
</html>