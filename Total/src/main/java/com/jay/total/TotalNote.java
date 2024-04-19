package com.jay.total;

public class TotalNote {
	/**
	 * ### Servlet
	 * 		- 클라이언트의 요청을 받아 되돌려주는 Class
	 * 		- contextPath 뒤에 경로 작성
	 * 
	 * 		* @어노테이션
	 * 			- 컴파일러가 객체에 대한 정보를 인지하기 위해 쓰는 것
	 * 		* 매핑한다
	 * 			- 해당 값이 다른 값을 가리키도록 하는 것
	 * 
	 * #contextPath
	 * 		- 해당 프로젝트 파일의 webapp 까지의 경로를 url로 나타낸 것
	 * 		- 서버가 시작되면 webapp > WEB-INF > web.xml 파일의 welcome-file을 확인해 그 파일을 처음으로 실행하게 함
	 * 		
	 * 		* url / uri
	 * 			- url : 도메인 주소
	 * 			- uri : IP 주소
	 * 			- 단 url 자리에 도메인 주소를 넣어도 IP 주소로 변경 되어 전송됨
	 * 
	 * # HttpServletRequest
	 * 		- 
	 * 
	 * # HttpServletResponse
	 * 		
	 * <div id="rest-review">
            <c:if test="${detailPage eq 1}">
                <%@ include file="restMainViewReview.jsp" %>
            </c:if>
      </div>
      -> eq은 EL 태그 안에서 사용해야 함
      <jsp:forward> 태그는 페이지 전체를 포워딩 해버리는 것이므로 일부를 가져올 수 없음
      
      jQuery, jstl 전부 추가 안 해서 안 먹었었음
      
      SELECT COUNT(*)
		FROM REVIEW
		WHERE REST_NO = #{restNo}
		AND REVIEW_STATUS = 'Y'
		<choose>
		<!-- number 형태라서 ratingCount가 맞는 게 없어서 AND 조건이 실행이 하나도 안 돼서 다 7개 뜬 것 -->
			<when test="ratingCount == 1">
				AND REVIEW_RATING = 1
			</when>
			<when test="ratingCount == 2">
				AND REVIEW_RATING = 2
			</when>
			<when test="ratingCount == 3">
				AND REVIEW_RATING = 3
			</when>
			<when test="ratingCount == 4">
				AND REVIEW_RATING = 4
			</when>
			<when test="ratingCount == 5">
				AND REVIEW_RATING = 5
			</when>
		</choose>
		
		
		js 파일을 빼지 않으면 EL 태그는 서버에서 먼저 읽기 때문에 돔으로 그려주지 못한 변수명은 인식하지 못해 빼버림
		<!--
        EL 구문은 서버를 다녀오므로 이미 한 번 서버를 다녀온 이상 데이터가 바뀔 수 없음
        EL로 include 하고 싶다면 비동기를 포기해야 함, 서버를 다시 다녀와야 하므로 페이지가 바뀌기 때문에
        -->
	 */
}
