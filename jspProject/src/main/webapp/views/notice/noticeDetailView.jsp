<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.notice.model.vo.Notice"%>
<% 
	Notice n = (Notice)request.getAttribute("notice");
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
    <%@ include file="../common/menubar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2 align="center">공지사항 상세보기</h2>
        <br>
        <table>
            <tr>
                <th width="70">제목</th>
                <td colspan="3" width="430"><%=n.getNoticeTitle()%></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%=n.getNoticeWriter()%></td>
                <th>작성일</th>
                <td><%=n.getCreateDate()%></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 150px"><%=n.getNoticeContent()%></p>
                </td>
            </tr>
        </table>
        <br><br>
        <div align="center">
            <a href="<%=contextPath%>/list.no>" class="btn btn-sm btn-secondary">목록 가기</a>
            <% if (loginUser != null && loginUser.getUserId().equals(n.getNoticeWriter())) {%>
            <!-- 현재 로그인한 사용자가 해당 글을 쓴 본인 일  때에만 -->
            <a href="<%=contextPath %>/updateForm.no?num=<%=n.getNoticeNo()%>" class="btn btn-sm btn-warning">수정하기</a>
            <!-- 이 공지에 대한 정보를 넘겨줘야 찾을 수 있음, 세션에 넘기는 건 별로 좋지 않으므로 어차피 넘버는 가지고 있으니까 프라이머리 키로 넘겨줄 것 -->
            <a href="<%=contextPath %>/delete.no?num=<%=n.getNoticeNo() %>" class="btn btn-sm btn-danger">삭제하기</a>
            <% } %>
        </div>
    </div>
</body>
</html>