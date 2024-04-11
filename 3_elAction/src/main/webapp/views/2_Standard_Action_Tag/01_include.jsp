<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>jsp:include</h3>
    <!-- 지시어의 include와 기능이 대부분 같으나 조금 다른 부분이 있음 -->
    <p>또 다른 페이지를 포함 하고자 할 때 쓰는 태그</p>

    <h4>1. 기존의 include 지시어를 이용한 방식 (정적 include 방식 == 컴파일 시 애초에 포함 되어 있는 형태)</h4>
    <!-- 정적으로 움직이지 않는 코드 방식 -->
    <!-- 정적 코드는 컴파일 시 실행, 런타임 시 실행하지 않음 -->
    <!-- 이걸 동적으로 include가 가능해짐 -->
    <!-- 표준 액션 태그를 이용하면 런타임 시 동적 코드(include)를 만났을 때 반영함 -->
    <!-- <%int year = 2023; %> -->
    <!-- footer include 하기 이전에 변수를 넣으면 없는 변수라고 에러 남? -->
    <!-- <%@ include file="footer.jsp"%>
    <br>

    특징 : include 하고 있는 페이지에 선언 되어 있는 변수를 현재 페이지에서도 사용 가능 <br>
    include 한 페이지의 year 변수 : <%=year%> <br><br>

    -> 단 현재 이 페이지에서 동일한 이름의 변수를 선언할 수 없음 <br>
    int year = 2023;
    컴파일 할 때 이미 모든 변수를 다 가져왔기 때문에 -->

    <h4>2. JSP 표준 액션 태그를 이용한 방식 (동적 include 방식 == 런타임 시 포함 되는 형태)</h4>
    <jsp:include page="footer.jsp" />
    <!-- 단일 태그로도 사용 가능 -->
    <br>

    특징 1 : include 하고 있는 페이지에 선언된 변수를 공유하지 않음 <br>
    -> 동일한 이름의 변수로 재선언 가능 <br><br>
    <!-- 그 페이지 자체를 새로 만들어서 변수를 재선언 해서 사용 -->

    int year = 2022; <!-- 스크립틀릿에 넣을 시 에러 X -->

    특징 2 : 포함 시 include 하는 페이지로 값을 전달할 수 있음
    <!-- 함수처럼 값이 넘어가서 몰라... -->

    <jsp:include page="footer.jsp">
        <!-- 값 전달하고 싶을 때 단일 X -->
        <jsp:param name="test" value="hi"/>
        <!-- 표준 액션 태그는 Parameter 영역에 담아서 넘김 -->
        <!-- header css 파일 넘길 때 상대 경로로 잘 못 찾으니까 쓰면 좋음 -->
    </jsp:include>
</body>
</html>