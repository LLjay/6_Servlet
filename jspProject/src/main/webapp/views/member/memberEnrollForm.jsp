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
                    <td><button type="button" onclink="idCheck()">중복확인</button></td>
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
                <button type="submit" onclick="return checkPwd();" disabled>회원가입</button>
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
        
        function idCheck(){
        	// 중복 확인 버튼 클릭 시 사용자가 입력한 아이디 값을 서버에 보내서 조회 요청(존재하는 아이디인지)
            // 1) 사용 불가 -> alertMsg 출력(사용할 수 없는 아이디입니다.), 다시 입력
            // 2) 사용 가능 -> 정말로 사용할 것인지 한 번 더 확인
            //                -> 사용 O : 더 이상 아이디 수정 못 하게 막기 / 회원가입 버튼 활성화
           //                -> 사용 X : 다시 입력 하도록 유도

           const idInput = document.querySelector("#enroll-form input[name=userId]");
           console.log(idInput.value);

           // idInput.value 값을 서버에 보내야 함, ajax 사용
            // 달러는 jQuery에서 만들어 놓은 변수, 객체라고 생각하면 됨
           
        //    다음 버전도 있음


            /**
             * $.ajax({
                type : 전송 타입 GET | POST
                url : 어디로 요청을 보낼지
                data : {key : value} -> 어떤 데이터를 포함해서 보낼지
                success : function(){} -> 성공 시 
                error : function(){} -> 실패 시
                
                })
             */
        
            {
                name: "jisoo"
                age: 27
            }

            $.ajax({
                type: "GET",
                // 이 타입으로 보내줘 하면 ajax가 인식
                // 약속된 양식을 통해 데이터를 전달해줄 것
                url: "idCheck.me",
                // 어디로 보낼 것인지
                // 상대경로 -> context root 바로 뒤의 url이 바뀌는 것
                // 하지만 페이지가 넘어가진 않고 요청을 보내기만 하는 url
                // 다른 서버에 보낼 거면 이 앞에 http 붙이면 됨
                data: {
                    checkId : idInput.value
                },
                // 어떤 데이터를 보낼지 쓰는 것, 데이터가 하나가 아니므로 또 키값으로 보내주기
                // 다 키밸류 형태
                // 체크 아이디에 이 밸류를 넣어줘
                success: function(result) {
                    if(result === "NNNNY"){
                        if (confirm("사용 가능한 아이디입니다. 사용하시겠습니까?")){
                            // 아이디 수정 X, 회원가입 버튼 활성화
                            idInput.setAttribute("readonly", true);
                            // 이런 속성들은 바꾸려면 true false를 바꿔주면 됨
                            
                            const submitBtn = document.querySelector("#enroll-form button[type=submit]");
                            submitBtn.removeAttribute("disabled");
                            // Attribute : 속성
                        } else {
                            // 아니라고 한 경우 아이디 입력창 포커싱
                            idInput.focus();
                        }
                    } else {
                        alert("사용 불가능한 아이디입니다.");
                        idInput.focus();
                    }
                },
                // 데이터 요청에 성공 했을 때 실행할 함수
                error: function(err){
                    console.log("실패 : ", res)
                }
                // 데이터 요청에 실패 했을 때 실행할 함수
                // ajax는 이 데이터가 필요한 부분을 제외하고는 나머지 코드를 다 실행 시켜줌
                // 만약 이 데이터가 

            })
            // ajax는 비동기 코드이기 때문에 다른 이후 코드를 먼저 실행한 후 ajax 요청이 도착하면 반응 해주는 것
        }
    </script>
</body>
</html>