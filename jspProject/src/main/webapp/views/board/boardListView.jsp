<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.common.vo.PageInfo, java.util.ArrayList, com.kh.board.model.vo.Board" %>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background-color: black;
        color: white;
        width: 1000px;
        height: 500px;
        margin: auto;
        margin-top: 50px;
    }

    .list-area{
        border: 1px solid white;
        text-align: center;
    }

    .list-area>tbody>tr:hover{
        background-color: gray; 
        cursor: pointer;
    }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <br>
        <div class="outer">

            <br>
            <h2 align="center">일반게시판</h2>
            <br>
    
            <% if (loginUser != null) {%>
                <!-- 현재 로그인한 사용자일 때 -->
                <div align="right" style="width: 850px; margin-bottom: 4px;">
                    <a href="<%=contextPath %>/enrollForm.bo" class="btn btn-sm btn-secondary">글쓰기</a>
                </div>
            <%}%>
            <table class="list-area" align="center">
                <thead>
                    <th width="70">글번호</th>
                    <th width="80">카테고리</th>
                    <th width="300">글제목</th>
                    <th width="100">작성자</th>
                    <th width="50">조회수</th>
                    <th width="100">작성일</th>
                </thead>
                <tbody>
                <!-- 페이징 처리 : 10개 20개씩 끊는 것 -->
                    <!-- 인피니티 스크롤 방식 : 어플에서 주로 사용-->
                    <!-- 마지막 것을 치면 정렬,  -->
                    <!-- 공지사항이 없을 경우 -->
                <% if(list.isEmpty()) { %>
                    <!-- 게시글이 없을 경우 -->
                    <tr>
                        <td colspan="6">존재하는 게시글이 없습니다.</td> 
                    </tr> 
                    <% } else { %>
                    <!-- 게시글 있는 경우 -->
						<%for(Board b : list) {%>
	                      <tr>
	                          <td><%=b.getBoardNo() %></td>
	                          <td><%=b.getCategoryNo() %></td>
	                          <td><%=b.getBoardTitle() %></td>
	                          <td><%=b.getBoardWriter() %></td>
	                          <td><%=b.getCount() %></td>
	                          <td><%=b.getCreateDate() %></td>
	                      </tr>
	                  	<% } %>
                     <% } %>     

                </tbody>
            </table>
            <script>
                // kh/detail.bo?bno=
                const trList = document.querySelectorAll(".list-area > tbody > tr"); // 배열로 나옴
                // 배열에 담긴 tr 중 하나를 선택할 것
                // tr을 꺼내서 각각 onclick 핸들러 달아주기
                for(const tr of trList) {
                    tr.onclick = function(){
                        // tr 안의 td 중 boardNo을 꺼내와야 그걸 보내줄 수 있음
                        // const boardNo = tr.childNodes[1].innerText;
                        const boardNo = tr.children[0].innerText;

                        // 강사님
                        // this -> 클릭된 객체
                        // .children -> 객체의 자식 노드 list를 전부 가져옴
                        
                        // console.log(this.children[0].innerText);
                        // console.log(boardNo);

                        // const boardNo = this.children[0].innerText;
                        // boardNo 꺼냈으므로 보내줘야 함 어떻게?
                        // 주소로 보내는 문법
                        location.href = "<%=contextPath%>/detail.bo?bno=" + boardNo;
                    }
                }
                

            </script>
            
            <br><br>
            <div class="paging-area" align="center">
            <!-- 화살표 눌렀을 때 옆으로 가는 버튼 제어하는 if -->
            	<%if(currentPage != 1) {%>
                	<button onclick="location.href='<%=contextPath%>/list.bo?cpage=<%=currentPage - 1%>'">&lt;</button>
                <% } %>
                <%for(int p = startPage; p <= endPage; p++){%>
	                <%if(p == currentPage) { %>
	                <!-- 현재 페이지는 클릭 되지 않도록 -->
	                	<button disabled><%=p %></button>
	                <%} else {%>
	                    <button onclick="location.href='<%=contextPath%>/list.bo?cpage=<%=p %>'"><%=p %></button>
	                <% } %>
                <% } %>
                <%if(currentPage != maxPage) {%>
                	<button onclick="location.href='<%=contextPath%>/list.bo?cpage=<%=currentPage + 1%>'">&gt;</button>
                <% } %>
            </div>
            
        </div>
    </div>
</body>
</html>