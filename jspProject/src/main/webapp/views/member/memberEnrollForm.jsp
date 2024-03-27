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
        margin: auto;
        margin-top: 50px;
        padding: 10px 0px 50px 0px;
    }

    table{
        margin: auto;

    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
    <!-- 하나 뒤로 빠져야 접근할 수 있음 : .. 쓰면 뒤로 한 칸 -->

    <div class="outer">
        <br>
        <h2 align="center">회원가입</h2>
        <form action="<%=contextPath%>/insert.me" id="enroll-form" method="POST">
            <!-- contextPath라는 String 변수를 include 파일에서 사용하고 있기 때문에 쓸 수 있는 것 -->
            <table>
                <tr>
                    <td>*아이디</td>
                    <td><input type="text" name="userId" maxlength="12" required></td>
                    <td><button type="button">중복확인</button></td>
                    <!-- form 태그에 들어갈 정보가 아니므로 타입을 주지 않으면 submit에 함께 들어갈 수 있음 -->
                    
                    <!-- 중복확인은 프론트에서 해주는 게 좋음 -->
                    <!-- 하지만 페이지는 바뀌면 이상함, 이럴 때 쓰는 게 ajax 통신 -->
                </tr>
                <tr>
                    <td>*비밀번호</td>
                    <td><input type="password" name="userPwd" maxlength="15" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>*비밀번호 확인</td>
                    <td><input type="password" name="userPwdCheck" maxlength="15" required></td>
                    <td></td>
                    <!-- 원래 name도 써줄 필요 없음, 어차피 보내줄 정보가 아니므로 -->
                </tr>
                <tr>
                    <td>*이름</td>
                    <td><input type="text" name="userName" maxlength="6" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>전화번호</td>
                    <td><input type="text" name="phone" placeholder="- 포함해서 입력"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="email" name="email"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><input type="text" name="address"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>관심분야</td>
                    <td colspan="2">
                        <input type="checkbox" name="interest" id="sports" value="운동">
                        <label for="sports">운동</label>
                        <input type="checkbox" name="interest" id="climbing" value="등산">
                        <label for="climbing">등산</label>
                        <input type="checkbox" name="interest" id="fishing" value="낚시">
                        <label for="fishing">낚시</label>
                        <br>
                        <input type="checkbox" name="interest" id="cooking" value="요리">
                        <label for="cooking">요리</label>
                        <input type="checkbox" name="interest" id="game" value="게임">
                        <label for="game">게임</label>
                        <input type="checkbox" name="interest" id="movie" value="영화">
                        <label for="movie">영화</label>
                        <input type="checkbox" name="interest" id="etc" value="기타">
                        <label for="etc">기타</label>
                    </td>
                </tr>
            </table>

            <br><br>

            <div align="center">
                <!-- onclick이 return false이면 넘어가지 않음 -->
                <button type="submit" onclick="return checkPwd();">회원가입</button>
                <button type="reset">초기화</button>
            </div>
        </form>
    </div>

    <script>
        function checkPwd(){
            const pwd = document.querySelector("#enroll-form input[name=userPwd]").value;
            // 아이디가 enroll-form인 것 중 input 중 name 속성이 userPwd인 것의 value
            const pwdCheck = document.querySelector("#enroll-form input[name=userPwd]").value;

            if (pwd !== pwdCheck) {
                alert("비밀번호가 일치하지 않습니다.");
                return false;
                // 그래야 onclick이 넘어가지 않으므로
            }
        }
    </script>
</body>
</html>