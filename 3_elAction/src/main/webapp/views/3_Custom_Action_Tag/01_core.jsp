<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.model.vo.Person"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 접두어 설정, 이 taglib를 사용할 때 접두어를 c로 사용하겠다는 뜻 -->
<!-- jstl의 core라는 것을 c라는 접두어로 사용하겠다 -->
<!-- url : 도메인 주소 -->
<!-- uri : 실제 IP 주소 -->
<!-- url에 도메인 넣어도 IP로 바꿔주기 때문에 상관 없음 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Core Library</h1>
    <!-- library는 남이 만든 코드를 내가 가져와서 쓰는 것 -->
    <!-- jar : 객체를 압축 시켜 놓은 파일 -->
    <!-- jar 파일을 넣어주면 그 안에 있는 객체들을 가져와 사용하는 것 -->

    <h3>1. 변수(속성 == attribute)</h3>

    <pre>
        *변수 선언과 동시에 초기화(c:set var="변수명" value="값" [scope="저장 객체"])
        - 변수를 선언하고 초기값을 대입하는 기능 제공
        - 해당 변수를 어떤 scope의 setAttribute를 통해서 담아둘지 결정할 수 있음
            -> c:set으로 선언한 변수는 접근 시 무조건 EL로 접근해야 함
            =num1 (% 스크립틀릿 X)
            ${num1} (O)
    </pre>

    <c:set var="num1" value="10"/> <!-- pageContext.setAttribute("num1", 10); -->
    <!-- 스크립틀릿을 사용하면서 <%%> 등이 들어가 복잡해짐 -->
    <!-- 스크립틀릿을 사용해야 하는 자바 코드를 전부 태그 형식으로 바꿔주는 역할을 함 -->
    
    <c:set var="num2" value="20" scope="request"/> <!-- request.setAttribute("num2", 20); -->
    num1 변수 : ${num1} <br>
    num2 변수 : ${num2} <br>

    <c:set var="result" value="${num1 + num2}" scope="session"/>

    result 변수 : ${result} <br><br>
    <!-- 다 다른 지역에 담겨 있는데 알아서 찾아서 가져옴 -->

    ${pageScope.num1} <br>
    ${requestScope.result} <br>

    <c:set var="result" scope="request">
        9999
    </c:set>
    ${requestScope.result} <br>

    <pre>
        *변수 삭제(c:remove var="제거하고 싶은 변수명" [scope=""])
        - 해당 scope 영역의 해당 변수를 찾아서 제거하는 태그
        - scope 지정 생략 시 모든 scope에서 해당 변수를 다 찾아서 제거
            -> 즉 해당 scope에 .removeAttribute를 통해 제거
    </pre>

    삭제 전 : ${result} <br><br>
    <!-- 좁은 영역부터 하나씩 찾아나감, 따라서 출력되는 값은 9999 -->

    1) 특정 scope 지정해서 삭제
    <c:remove var="result" scope="request"/>
    request에서 삭제 후 result : ${result} <br><br> <!-- 30 출력 -->

    2) 모든 scope에서 삭제
    <c:remove var="result"/> <br>
    모두 삭제 후 result : ${result} <br><br>

    <hr>

    <pre>
        *변수(데이터) 출력 (c:out value="출력하고자 하는 값" [default="기본값"] [escapeXml="true | false"])
        <!-- 닉네임 등을 입력 안 해놔서 없을 때 빈값으로 두는 것보다 미입력 따위의 값을 지정해놓는 게 좋음 -->
        -> 데이터를 출력 하고자 할 때 사용하는 태그
    </pre>

    <c:out value="${result}"/> <br>
    <c:out value="${result}" default="없음"/> <br>

    <!-- escapeXml : 켰다 껐다? -->

    <c:set var="outTest" value="<b>출력 테스트</b>"/> <br>
    <c:out value="${outTest}"/> <br> <!-- escapeXml 생략 시 기본값이 true == 태그 해석 안 됨 -->
    <c:out value="${outTest}" escapeXml="false"/> <br>
    <!-- false면 html 코드까지 해석해서 적용한 텍스트 출력 -->

    <hr>

    <h3>2. 조건문 - if(c:if test="조건식")</h3>
    <pre>
        - JAVA의 if문과 비슷한 역할을 하는 태그
        - 조건식은 test 속성에 작성(단, EL 구문으로 기술해야 함)
    </pre>

    <!-- 강사님 코드 참고 -->

    <c:if test="${num1 > num2}">
        <b>num1이 num2보다 큽니다.</b>
        <!-- 조건을 만족할 시에 이 안의 코드를 실행하라. -->
    </c:if>

    <c:if test="${num1 lt num2}">
        <b>num1이 num2보다 작습니다.</b>
    </c:if>

    <br>

    <c:set var="str" value="안녕"/>

    <c:if test="${str eq '안녕'}">
        <h4>그래 안녕</h4>
    </c:if>

    <c:if test="${str ne '안녕'}">
        <h4>뭐지</h4>
    </c:if>

    <br>

    <h3>3. 조건문 - choose(c:choose, c:when, c:otherwise)</h3>
    <!-- num1 = 10 -->
    <c:choose>
        <c:when test="${num1 gt 30}">
            num1은 30보다 크다
        </c:when>
        <c:when test="${num1 gt 20}">
            num1은 20보다 크다
        </c:when>
        <c:when test="${num1 gt 10}">
            num1은 10보다 크다
        </c:when>
        <c:otherwise>
            모든 조건은 맞지 않다
        </c:otherwise>
    </c:choose>

    <h3>4. 반복문 - forEach</h3>
    <pre>
        for loop문 - (c:forEach var="변수명" begin="초기값" end="끝 값" [step="반복 시 증가값"])
        향상된 for문 - (c:forEach var="변수명" items="순차적으로 접근하고자 하는 객체(배열/컬렉션)" [varStatus="현재 접근한 요소의 상태값"])
    </pre>

    <!--
    <% for(int i = 1; i <= 10; i+=2){%>

    <%}%>
    -->

    <c:forEach var="i" begin="1" end="10" step="2">
        반복 확인 : ${i} <br>
    </c:forEach>
    <c:forEach var="i" begin="1" end="5">
        <h${i}>태그 안에서 사용</h${i}>
    </c:forEach>
    <c:set var="colors">
        red, yellow, blue, pink, green
    </c:set>

    colors 변수 : ${colors} <br>

    <ul>
        <c:forEach var="c" items="${colors}">
            <li style="color: ${c}">${c}</li>
        </c:forEach>
        <!-- db에 배열을 넣을 수도 있는데 불편해서 그냥 나눠서 넣음, json도 파일 객체 자체로 저장할 수 있는데 불편 -->
        <!-- 배열을 넣으면 검색하기 힘들어짐 / 구조를 가져와서 쓸 때 빈공간이 있으면 프론트에서 힘들어짐, 백 프론트 양쪽에게 다 편한 방법이 쪼개는 것 -->
    </ul>
    <c:forEach var="c" begin=></c:forEach>


    <%
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("홍길동", 18, "남자"));
        list.add(new Person("전은지", 30, "여자"));
        list.add(new Person("윤현지", 28, "여자"));
    %>

    <table>
        <thead>
            <th>번호</th>
            <th>이름</th>
            <th>나이</th>
            <th>성별</th>
        </thead>
        <tbody>
            <!--
            <%if(list.isEmpty()) {%>
                <tr><td colspan="4">조회된 사람이 없습니다.</td></tr>
            <%} else {%>
                <%for(Person p : list){%>
                    <tr>
                        <td><%=p.getName()%></td>
                        <td><%=p.getAge()%></td>
                        <td><%=p.getGender()%></td>
                    </tr>
                <%}%>
            <%}%>
            -->

            <c:choose>
                <c:when test="${empty pList}">
                    <tr><td colspan="3">조회된 사람이 없습니다.</td></tr>
                </c:when>
            </c:choose>

            <!-- <c:set var="plist" value="<%=list%>" scope="request">

            </c:set>
            <c:if test="${empty list}">
                <tr><td></td></tr>
            </c:if>
            <c:forEach var="c" items="${list}">
                <tr>
                    <td>${c.name}</td>
                    <td>${c.age}</td>
                    <td>${c.gender}</td>
                </tr>
            </c:forEach> -->

            <!-- status로 상태를 찍어줄 수 있음 -->
            
            <!-- forTokens : java의 split과 비슷 -->
            <!-- 구분자를 여러 개 사용하는 것이 가능 -->

            <!-- url에 들어가는 get 요청 시 보내는 키워드 -->
            <!-- 필터링 같은 거 해서 넘길 때 페이징 하는 건 옮겨다니되 필터링 데이터는 그대로 유지해야 함 -->

            <!-- java server page : jsp -->
            <!-- 자바에서 서버로 만들어주는 페이지가 jsp -->
            

        </tbody>
    </table>
</body>
</html>