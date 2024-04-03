<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.board.model.vo.Category,
    com.kh.board.model.vo.Board, com.kh.board.model.vo.Attachment" %>
<%
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
	
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
    #update-form table{
        border: 1px solid white;
    }
    #update-form input, #update-form textarea{
        width: 100%;
        box-sizing: border-box;
    }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2 align="center">일반게시판 수정하기</h2>
        <br>

        <form id="update-form" action="<%=contextPath %>/update.bo" method="post" enctype="multipart/form-data">
        <!-- 첨부파일을 보내려면 인코딩이 필요함, 이 파일을 어떤 형식으로 인코딩 할 것인지 -->
        <!-- 원래 인코딩 형식은 텍스트 파일 (text/plain 디폴트) -->
        <!-- url만 보고서도 어떤 내용인지를 알 수 있게 지정해주는 방식 레스테이터? -->
            <input type="hidden" name="bno" value="<%=b.getBoardNo()%>">
            <table>
                <tr>
                    <th width="70">카테고리</th>
                    <td width="500">
                        <select name="category">
                        <!-- category 테이블로부터 조회해오기 -->
                        <% for(Category c : list) {%>
                            <option value="<%=c.getCategoryNo()%>"><%=c.getCategoryName() %></option>
                            <!-- selected 속성이 true인 옵션을 가지고 와야 함 -->
                        <% } %>
                        </select>
                        <script>
                            const optList = document.querySelectorAll("#update-form option");
                            // 가져온 카테고리 데이터와 기존 카테고리 네임을 비교해 동일하면 속성을 true로 바꾸는 것
                            for (let opt of optList){
                                // 우리가 데이터베이스에서 가져온 건 카테고리 네임, 네임으로 비교해야 함
                                if (opt.innerText /*카테고리네임 가져올 것*/ === "<%=b.getCategoryNo()%>"){
                                    opt.selected = true;
                                }
                            }
                        </script>
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" required value=<%=b.getBoardTitle() %>></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" rows="10" style="resize: none;" required><%=b.getBoardContent() %></textarea></td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td>
                    <% if (at != null) { %>
                    	<%=at.getOriginName() %>
                    	<input type="hidden" name="originFileNo" value="<%=at.getFileNo()%>">
                    	<%} %>
                    	<!--  기존 파일이 뭔지 알아야 삭제 가능 -->
                    	<input type="file" name="upfile">
                    </td>
                    
                    
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