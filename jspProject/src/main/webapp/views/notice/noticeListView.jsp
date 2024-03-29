<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice" %>
<%
    ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
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
    <%@ include file="../common/menubar.jsp"%>
    <div class="outer">

        <br>
        <h2 align="center">공지사항</h2>
        <br>

        <% if (loginUser != null && loginUser.getUserId().equals("admin")) {%>
            <!-- 현재 로그인한 사용자가 관리자일 때 -->
            <div align="right" style="width: 850px; margin-bottom: 4px;">
                <a href="<%=contextPath %>/enroll.no" class="btn btn-sm btn-secondary">글쓰기</a>
            </div>
        <%}%>
        <table class="list-area" align="center">
            <thead>
                <th>글번호</th>
                <th width="400">글제목</th>
                <th width="100">작성자</th>
                <th>조회수</th>
                <th width="100">작성일</th>
            </thead>
            <tbody>
                <!-- 공지사항은 list 안 데이터가 있을 수도 있고 없을 수도 있음 -->
                <% if(list.isEmpty()) {%>
                    <!-- 공지사항이 없을 경우 -->
                    <tr>
                        <td colspan="5">존재하는 공지사항이 없습니다.</td>
                    </tr>
                <% } else { %>
                    <!-- 공지사항이 있을 경우 -->
                    <% for(Notice n : list) { %>
                        <tr>
                        <!-- tr 행을 누르면 넘어가는 것이므로 tr에 이벤트 걸어야 함 -->
                            <td><%=n.getNoticeNo()%></td>
                            <td><%=n.getNoticeTitle()%></td>
                            <td><%=n.getNoticeWriter()%></td>
                            <td><%=n.getCount()%></td>
                            <td><%=n.getCreateDate()%></td>
                        </tr>
                    <% } %>
                <% } %>
            </tbody>
        </table>
    </div>
    <script>
        const trList = document.querySelectorAll(".list-area > tbody > tr");
        // [tr, tr, tr] // tr 리스트 쿼리셀렉터로 가져옴
        for(const tr of trList){ // 배열 안의 tr을 하나하나 꺼내서 for문으로 돌리고
            tr.onclick = function(ev){ // tr을 클릭 했을 때 익명함수로 이벤트 핸들러를 달아주고
                // url / kh/detail.no
                console.log(this.childNodes[0].innerText);
                console.log(this.childNodes[1].innerText);
                console.log(this.childNodes[2].innerText);
                // const noticeNo = this.childNodes[1].innerText;
                const noticeNo = this.children[0].innerText;
                console.log(this.childNodes)
                // noticeNo는 이걸 호출한 사람 즉 tr의 childNodes 첫번째의 innerText
                // console.log(noticeNo);

                // 클릭한 게 무조건 this가 됨
                // onclick 한 게 이 함수를 호출한 것일 테니까
                // 자식들을 배열로 꺼내줄 수 있음
                location.href = "<%=contextPath%>/detail.no?num=" + noticeNo;
                // 세부사항을 가져오려면 각각에 해당하는 정보를 가지고 있어야 함
            }
        }       
        // $(function(){
        //     $(".list-area > tbody > tr").click(function(){
        //         const noticeNo = $(this).children().eq(0).text();
        //         location.href = "<%=contextPath%>/detail.no?num=" + noticeNo;
        //     })
        // });
    </script>
    
</body>
</html>