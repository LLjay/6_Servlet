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

    #enroll-form table{
        border: 1px solid white;
    }

    #enroll-form input, #enroll-form textarea{
        width: 100%;
        box-sizing: border-box;
    }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2>공지사항 수정하기</h2>
        <br>

        <form action="<%=contextPath%>/update.no" id="update-form" method="POST">
            <input type="hidden" name="num" value="<%=n.getNoticeNo()%>">
            <table>
                <tr>
                    <th width="50">제목</th>
                    <td width="450"><input type="text" name="title" required value="<%=n.getNoticeTitle()%>"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="content" rows="10" resize="none"><%=n.getNoticeContent()%></textarea>
                    </td>
                </tr>
            </table>
            <br><br>
            <div align="center">
                <button type="submit">수정하기</button>
                <button type="reset">초기화</button>
                <button type="button" onclick="history.back()">뒤로가기</button>
                <!-- history 객체가 우리의 과거 정보를 다 가지고 있음 -->
                <!-- 기존 구조를 스택에 쌓아주기 때문에 뒤로가기 하면 이전에 쌓인 공지사항으로 넘어가는 것 -->
                <!-- 뒤로가기는 url이 있어야 할 수 있음, 이전 url로 가는 것 -->

            </div>
        </form>

    </div>
</body>
</html>