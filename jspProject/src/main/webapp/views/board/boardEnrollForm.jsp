<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.board.model.vo.Category" %>
<%
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("categories");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background: black;
        color: white;
        width: 1000px;
        height: 550px;
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
        <h2 align="center">일반게시판 작성하기</h2>
        <br>

        <form id="enroll-form" action="<%=contextPath %>/insert.bo" method="post" enctype="multipart/form-data">
        <!-- 첨부파일을 보내려면 인코딩이 필요함, 이 파일을 어떤 형식으로 인코딩 할 것인지 -->
        <!-- 원래 인코딩 형식은 텍스트 파일 (text/plain 디폴트) -->
        <!-- url만 보고서도 어떤 내용인지를 알 수 있게 지정해주는 방식 레스테이터? -->
            <input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
            <table>
                <tr>
                    <th width="70">카테고리</th>
                    <td width="500">
                        <select name="category">
                        <!-- category 테이블로부터 조회해오기 -->
                        <% for(Category c : list) {%>
                            	<option value="<%=c.getCategoryNo()%>"><%=c.getCategoryName() %></option>
                        <% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" rows="10" style="resize: none;" required></textarea></td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td><input type="file" name="upfile"></td>
                    <!-- 이미지는 바이너리 타입으로 자동 인코딩 되어서 넘어감 -->
                    
                </tr>
            </table>

            <br>

            <div align="center">
                <button type="submit">작성하기</button>
                <button type="reset">취소하기</button>
            </div>
        </form>
    </div>

</body>
</html>