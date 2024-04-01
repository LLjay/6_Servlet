<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
            <% if (loginUser != null && loginUser.getUserId().equals("admin")) {%>
                <!-- 현재 로그인한 사용자가 관리자일 때 -->
                <div align="right" style="width: 850px; margin-bottom: 4px;">
                    <a href="" class="btn btn-sm btn-secondary">글쓰기</a>
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
                    <!-- 공지사항이 없을 경우
                    <tr>
                        <td colspan="6">존재하는 게시글이 없습니다.</td> 
                    </tr> 
                    -->
                    
                    <!-- 게시글 있는 경우 -->
                        <table>
                            <tr>
                                <td>1</td>
                                <td>운동</td>
                                <td>같이 운동 하실 분?</td>
                                <td>user01</td>
                                <td>47</td>
                                <td>2024-03-31</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>운동</td>
                                <td>같이 운동 하실 분?</td>
                                <td>user01</td>
                                <td>47</td>
                                <td>2024-03-31</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>운동</td>
                                <td>같이 운동 하실 분?</td>
                                <td>user01</td>
                                <td>47</td>
                                <td>2024-03-31</td>
                            </tr>
                        </table>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>