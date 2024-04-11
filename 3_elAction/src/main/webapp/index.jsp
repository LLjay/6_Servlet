<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>* EL(Expression)</h1>
	
	<p>
		기존에 사용했던 표현식(출력식) &lt;= name &gt;와 같이 <br>
		JSP 상에서 표현 하고자 하는 값을 \${name}의 형식으로 표현 해서 작성 하는 것
	</p>
	
	<h4><a href="el.do">01_EL의 기본 구문</a></h4>
	<h4><a href="operation.do">02_EL의 연산자</a></h4>

	<h1>JSP Action tag</h1>
	<!-- 
		*JSP를 이루는 구성
		1. 스크립팅 원소 : jsp 페이지 내에서 자바 코드를 직접 기술할 수 있게 하는 기술
			ex) 스크립틀릿, 표현식...
		2. 지시어 : JSP 페이지 정보에 대한 내용을 표현 한다거나 또 다른 페이지를 포함할 때 사용
					JSP 기능을 확장 시키는 라이브러리를 등록할 때 사용
		3. 액션 태그 : jsp 페이지에서 어떤 동작을 하도록 지시하는 태그
						// 그냥 태그인데 기능이 있다, html 같이 태그마다 기능이 있는 것
					xml 기술을 이용하여 기존의 jsp 문법을 확장하는 기술을 제공하는 태그
						// sql을 외부 문서로 뺄 때 사용했던 파일 확장자
						// xml : 태그 형식의 문서 포맷
						// 어떠한 정보를 저장하기 위해 쓰는 것
						// HttpRequest 앞에 붙은 xml -> xml을 이용한 요청 방법
						// 페이지 이동이 아닌 데이터만 받기 위해 ajax, 그걸 위해 xml 형식으로 데이터를 주고 받고 하는 것
						// 하지만 요즘은 그걸 xml을 json 방식으로 최신화 되어 사용하는 것
						// 액션 태그는 xml을 사용해 동작하도록 되어 있는 것
			>> 표준 액션 태그 (Standard Action Tag) : jsp 페이지에서 바로 사용 가능 (별도의 연동 필요 없음)
				모든 태그명 앞에 jsp: 접두어를 붙여서 사용
						// 이렇게 시작하는 건 모두 표준 액션 태그라고 생각
			>> 커스텀 액션 태그 (Custom Action Tag) : jsp 페이지에서 바로 사용 불가능 (별도의 라이브러리 연동 필요)
				jsp:를 제외한 모든 접두어가 붙은 태그
				가장 대표적이고 유용한 라이브러리 jstl
						// 가져올 땐 taglib 사용
	 -->

	 <h3>* 표준 액션 태그 (Standard Action Tag)</h3>
	 <a href="views/2_Standard_Action_Tag/01_include.jsp">01_jsp:include</a><br>
	 <!-- 표준이니까 01_jsp:로 선언해준 것 -->
	 <!-- 변수나 객체 사용할 때는 언더바_ 넣으면 안 됨 -->
	 <a href="views/2_Standard_Action_Tag/02_forward.jsp">02_jsp:forward</a>

	 <h1>JSTL</h1>

	 <p>
		JSP에서 사용되는 커스텀 액션 태그로 <br>
		공통적으로 자주 사용되는 코드들을 집합 하여 보다 쉽게 사용할 수 있도록 태그화 하여 표준으로 제공하는 라이브러리
	 </p>

	 <h3>라이브러리 추가 필요</h3>
	 1) 톰캣 사이트 접속 <br>
	 2) 메뉴바 Taglib 메뉴 접속 <br>
	 3) 4개의 라이브러리 전부 다운로드 <br>
	 4) WEB-INF/lib/() 추가 <br>

	 <h3>JSTL 선언 방법</h3>
	 <p>
		JSTL을 사용 하고자 하는 해당 jsp 페이지 상단에 <br>
		taglib 지시어를 사용해서 선언 <br><br>

		&lt;%@ taglib prefix="접두어" uri="라이브러리 파일 상의 uri 주소" %&gt;
	 </p>
	 <h3>* Custom Action Tag</h3>
	 <h4>1. JSTL Core Library</h4>
	 <p>변수와 조건문, 반복문 등 로직과 관련된 문법을 제공</p>
	 <a href="views/3_Custom_Action_Tag/01_core.jsp">01_core</a>

	 <h4>2. JSTL Formatting Library</h4>
	 <p>숫자, 날짜 및 시간 데이터의 출력 형식을 지정할 때 사용하는 문법을 제공</p>
	 <a href="views/3_Custom_Action_Tag/02_fmt.jsp">fmt lib</a>

	 <h4>3. JSTL function Library</h4>
	 <p>EL 안에서 사용할 수 있는 메소드 제공</p>
	 <a href="views/3_Custom_Action_Tag/03_fn.jsp">fn library</a>
</body>
</html>