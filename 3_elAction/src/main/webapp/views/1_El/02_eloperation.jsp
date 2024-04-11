<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>1. 산술 연산</h3>
    기존 : 10 + 3 = <%=(int)request.getAttribute("big") + (int)request.getAttribute("small")%>
    <br><br>

    EL방식 : <br>
    10 + 3 = ${big + small} <br>
    <!-- 알아서 형 변환까지 해줌 -->
    <!-- big이라는 이름의 키와 small이라는 이름의 키 밸류를 가져와 알아서 해줌 -->
    10 - 3 = ${big - small} <br>
    10 * 3 = ${big * small} <br>
    10 / 3 = ${big / small} 또는 ${big div small}<br>
    10 % 3 = ${big % small} 또는 ${big mod small}<br>

    <h3>2. 대소 비교</h3>
    10 &gt; 3 = ${big > small} 또는 ${big gt small} <br> <!-- great then -->
    10 &lt; 3 = ${big < small} 또는 ${big lt small} <br> <!-- less then -->
    10 &gt; 3 = ${big >= small} 또는 ${big ge small} <br> <!-- great equals -->
    10 &lt; 3 = ${big <= small} 또는 ${big le small} <br> <!-- less equals -->

    <h3>3. 동등비교</h3>
    <!-- el에서 == 비교는 자바에서의 equals()와 같은 동작 -->
        <!-- toString 내용을 비교해줬음 -->
    sOne과 sTwo가 일치합니까? ${sOne == sTwo} 또는 ${sOne eq sTwo} <br>
    sOne과 sTwo가 일치합니까? ${sOne != sTwo} 또는 ${sOne ne sTwo} <br> <!-- not equals -->

    big에 담긴 값이 10과 일치 합니까? ${big == 10} 또는 ${big eq 10} <br>

    <!-- 리터럴로 저장하면 상수형 리터럴로 저장, 저장하면 하나의 문자열을 가리킴 -->

    sThree에 담긴 값이 '안녕'과 일치 합니까? ${sThree == 'HI'} <br><br>
    <!-- el 구문 안에서 문자열 리터럴 값은 홀따옴표 쌍따옴표를 가리지 않음 -->

    <h3>4. 객체가 null인지 아닌지 리스트가 비어 있는지 비교</h3>
    pTwo가 null입니까? ${pTwo == null} 또는 ${pTwo eq null} 또는 ${empty pTwo} <br>
    pOne이 null입니까? ${pOne == null} 또는 ${pOne eq null} 또는 ${empty pOne} <br>
    pOne이 null이 아닙니까? ${pOne != null} 또는 ${pOne ne null} 또는 ${not empty pOne} <br>

    aOne이 텅 비어 있습니까? ${empty aOne} <br>
    aTwo가 텅 비어 있습니까? ${empty aTwo} <br>
    <!-- ArrayList는 empty로 검사 해주면 됨 -->

    <h3>5. 논리 연산자</h3>
    true && true : ${true && true} 또는 ${true and true} <br>
    <!-- and로 그냥 말로 써도 됨 -->
    true && false : ${true && false} 또는 ${true and false} <br><br>

    true || false : ${true || false} 또는 ${true or false} <br>

    <!-- big이 small보다 크고 aOne이 텅 비어 있냐? -->
    ${big > small && empty aOne}
    ${big gt small and empty aOne}

    <!-- 객체에 담아 포워딩한 값들을 쉽게 꺼내올 때 사용하는 것 EL -->
    <!-- session과 request 객체는 구분 해줘야 함 -->
    
</body>
</html>