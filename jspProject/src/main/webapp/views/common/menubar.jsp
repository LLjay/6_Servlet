<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<% 
	String contextPath = request.getContextPath();
    Member loginUser = (Member)session.getAttribute("loginUser");
    // 로그인 시도 전 menubar.jsp 로딩 시 : null
    // 로그인 시도 성공 menubar.jsp 로딩 시 : 로그인 성공한 회원의 정보
    String alertMsg = (String)session.getAttribute("alertMsg");
    // 서비스 요청 전 : null
    // 서비스 요청 후 : alert를 띄워줄 메세지


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 중요한 건 jquery.min.js 파일이고 경로는 별 상관 없음 -->
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
    a{
        text-decoration: none;
    }
    .login-area > *{
        float: right;
        /* float는 그 뒤에 따라오는 애들한테도 영향을 미치므로 중간에 클리어로 털어줘야 함 */
    }
    .login-area > a{
        color: black;
        text-decoration: none;
    }
    .nav-area{background: black};
    .menu{
        display: table-cell;
        width: 150px;
        height: 50px;
    }
    .menu a{
        line-height: 50px;
        font-size:2px;
        font-weight: bold;
        text-align: center;
        width: 100%;
        height: 100%;
        display: block;
        color: white;
    }
</style>
</head>
<body>
<% if(alertMsg != null){ %>
	<!-- 스크립트 언어니까 -->
	<script>
		alert("<%=alertMsg%>");
	</script>
	<% session.removeAttribute("alertMsg"); %>
	<!-- 이번에는 session에 있는 모든 정보가 사라지면 안 되고 alertMsg만 사라져야 하므로 속성 삭제인 remove로 -->
<%}%>
	<h1 align="center">Welcome 왕밥빵 World</h1>
    <div class="login-area">
        <% if(loginUser == null) { %>
        <!-- 로그인 전 -->
        <form action="<%=contextPath %>/login.me" method="POST">
            <table>
                <tr>
                    <th>아이디</th>
                    <td><input type="text" name="userId" required placeholder="아이디를 입력하세요"></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" name="userPwd" required placeholder="비밀번호를 입력하세요"></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit" value="로그인">
                        <input type="button" value="회원가입" onclick="enrollPage();">
                    </th>
                </tr>
            </table>
            <script>
                // 회원가입 버튼을 눌렀을 때 어떤 이벤트를 부여해주기 위해
                // 회원가입 페이지 요청
                function enrollPage(){
                    // 다음 페이지로 보낼 것, a 태그로 해도 됨
                    // location.href = "<%=contextPath%>/views/member/memberEnrollForm.jsp";
                    // 이 url로 요청을 보냄
                    // 내 폴더 구조 경로를 보고 이렇게 쓴 것, 하지만 이대로 보내면 구조 경로가 곧바로 url에 노출됨
                    // 따라서 서블릿에 넘겨서 보내주면 됨, 서블릿이 포워딩이나 리다이렉트 시켜줄 것, path를 따로 지정해서
                    
                    location.href = "<%=contextPath%>/enrollForm.me";
                	// 웹 애플리케이션의 디렉토리 구조가 url에 노출되면 보안에 취약
                	// 단순한 페이지 요청도 servlet을 거쳐갈 것(즉, url에는 서블릿 맵핑값만 나타나도록)
                }
            </script>
        </form>
        <% } else { %>

        <!-- 로그인 후 -->
        <div align="center;">
            <b><%= loginUser.getUserName()%></b>의 방문을 환영합니다 <br><br>
            <div>
                <a href="">마이페이지</a>
                <a href="<%=contextPath%>/logout.me">로그아웃</a>
            </div>
        </div>
        <% } %>
    </div>
    

    <br><br>

    <br clear="both">

    <div class="nav-area" align="center">
        <div><a href="menu">HOME</a></div>
        <div><a href="menu">공지사항</a></div>
        <div><a href="menu">일반게시판</a></div>
        <div><a href="menu">사진게시판</a></div>

    </div>

</body>
</html>