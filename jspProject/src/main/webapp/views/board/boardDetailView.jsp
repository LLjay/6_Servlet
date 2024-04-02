<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.model.vo.Board"%>
<%
	Board b = (Board)request.getAttribute("board");
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
    
    .outer table{
        border: 1px solid white;
        border-collapse: collapse;
        /* 표라서 선이 두 개 나오는 것을 합쳐줌 */
    }

    .outer > table tr, .outer > table td{
        border: 1px solid white;
    }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp"%>
    <div class="outer">
        <br>
        <h2 align="center">일반 게시판 상세 보기</h2>
        <br>

        <table id="detail-area" border="1" align="center">
            <tr>
                <th width="70">카테고리</th>
                <td width="70"><%=b.getCategoryNo() %></td>
                <th width="70">제목</th>
                <td width="350"><%=b.getBoardTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%=b.getBoardWriter() %></td>
                <th>작성일</th>
                <td><%=b.getBoardContent() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 200px;">
                        <%=b.getBoardContent() %>
                    </p>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                    <!-- case 1. 첨부파일 없을 때 -->
                    <!-- 첨부파일이 없습니다. -->

                    <!-- case 2. 첨부파일 있을 때 -->
                    <a download="첨부파일1" href="data:image/jpeg;base64,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">
                        <!-- 이걸 누르면 다운로드 이름으로 다운 받아질 것 -->
                        file20240401
                    </a>
                </td>
            </tr>
        </table>
        <br>

        <div align="center">
            <a href="<%=contextPath %>/list.bo?cpage=1" class="btn btn-sm btn-secondary">목록 가기</a>
            <% if (loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())) {%>
            <a href="" class="btn btn-sm btn-warning">수정하기</a>
            
            <a href="" class="btn btn-sm btn-danger">목록 가기</a>
            <%} %>
        </div>
    </div>
</body>
</html>