<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.model.vo.Board, com.kh.board.model.vo.Attachment"%>
<%
	Board b = (Board)request.getAttribute("board");
// 글 번호, 카테고리명, 제목, 내용, 작성자, 작성일

	Attachment at = (Attachment)request.getAttribute("attachment");
	// 없을 수도 있음 -> null
	// 있다면 파일 번호, 원본명, 수정명, 경로
	// common에 넣는 게 나음, 다른 게시판들에서도 똑같이 쓰니까
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
                <% if(at == null) { %>
                    <!-- case 1. 첨부파일 없을 때 -->
                    첨부파일이 없습니다.
				<% } else {%>
                    <!-- case 2. 첨부파일 있을 때 8001/kh/resources/board_upfile/-->
                    <a download="<%=at.getOriginName() %>" href="<%=contextPath%>/<%=at.getFilePath() + at.getChangeName()%>">
                    <!-- 이걸 누르면 다운로드 이름으로 다운 받아질 것 -->
                    <!-- originName으로 저장해야 받을 때 원본 파일 명으로 저장됨 -->
                    <!-- kh 컨텍스트루트 파일은 webapp 폴더의 경로 -->
                        <%=at.getOriginName() %>
                    </a>
                    <%}%>
                </td>
            </tr>
        </table>
        <br>

        <div align="center">
            <a href="<%=contextPath %>/list.bo?cpage=1" class="btn btn-sm btn-secondary">목록 가기</a>
            <% if (loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())) {%>
            <a href="<%=contextPath %>/updateForm.bo?bno=<%=b.getBoardNo() %>" class="btn btn-sm btn-warning">수정하기</a>
            <!-- 서버에서 데이터를 가져와야 하므로 프라이머리 키 전해줘야 함 -->
            <a href="<%=contextPath %>/delete.bo?bno=<%=b.getBoardNo() %>" class="btn btn-sm btn-danger">삭제하기</a>
            <%} %>
        </div>
    </div>
</body>
</html>