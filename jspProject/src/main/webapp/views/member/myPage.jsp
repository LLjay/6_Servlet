<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <style>
            .outer {
                background-color: black;
                color: white;
                width: 1000px;
                margin: auto;
                margin-top: 50px;
                padding: 10px 0px 50px 0px;
            }

            table {
                margin: auto;

            }
        </style>
    </head>

    <body>
        <%@ include file="../common/menubar.jsp" %>
            <!-- 하나 뒤로 빠져야 접근할 수 있음 : .. 쓰면 뒤로 한 칸 -->
            <% String userId=loginUser.getUserId(); 
            String userName=loginUser.getUserName(); 
            String phone=loginUser.getPhone()==null ? "" : loginUser.getPhone(); String email=loginUser.getEmail()==null
                ? "" : loginUser.getEmail(); String address=loginUser.getAddress()==null ? "" : loginUser.getAddress();
                String interest=loginUser.getInterest()==null ? "" : loginUser.getInterest(); %>
                <!-- required가 아닌 것들은 예외처리 해줘야 함 -->
                <div class="outer">
                    <br>
                    <h2 align="center">회원가입</h2>
                    <form action="<%=contextPath%>/update.me" id="enroll-form" method="POST">
                        <!-- contextPath라는 String 변수를 include 파일에서 사용하고 있기 때문에 쓸 수 있는 것 -->
                        <table>
                            <tr>
                                <td>*아이디</td>
                                <td><input type="text" name="userId" maxlength="12" value="<%=userId%>" readonly></td>
                                <!-- form 태그에 들어갈 정보가 아니므로 타입을 주지 않으면 submit에 함께 들어갈 수 있음 -->

                                <!-- 중복확인은 프론트에서 해주는 게 좋음 -->
                                <!-- 하지만 페이지는 바뀌면 이상함, 이럴 때 쓰는 게 ajax 통신 -->
                            </tr>
                            <tr>
                                <td>*이름</td>
                                <td><input type="text" name="userName" maxlength="6" value="<%=userName%>" required>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>전화번호</td>
                                <td><input type="text" name="phone" value="<%=phone%>" placeholder="- 포함해서 입력"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>이메일</td>
                                <td><input type="email" name="email" value="<%=email%>"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>주소</td>
                                <td><input type="text" name="address" value="<%=address%>"></td>
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

                        <script>
                            const interest = "<%=interest%>"; // 운동, 낚시
                            const inputArr = document.querySelectorAll("input[name=interest]");
                            // [, <input>,]

                            for (let input of inputArr) { // 가져온 checkbox 요소들을 전부 반복한다.
                                if (interest.includes(input.value)) { // interest에 input.value의 값
                                    input.checked = true;
                                }
                            }
                        </script>
                        <br><br>

                        <div align="center">
                            <!-- onclick이 return false이면 넘어가지 않음 -->
                            <button type="submit" class="btn btn-sm btn-secondary">정보변경</button>
                            <button type="button" class="btn btn-sm btn-primary" data-toggle="modal"
                                data-target="#update-pwd-modal">비밀정보변경</button>
                            <button type="button" class="btn btn-sm btn-danger">회원탈퇴</button>
                        </div>
                    </form>
                    <!-- 뒤가 까매지고 팝업창이 뜨는 창을 모달창이라고 함 -->
                </div>

                <script>
                    function checkPwd() {
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

                <!-- Button to Open the Modal -->

                <!-- 비밀번호 변경 Modal -->
                <div class="modal" id="update-pwd-modal">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">비밀번호 변경</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body" align="center">
                                <form action="<%=contextPath%>/updatePwd.me" method="POST">
                                    <!-- 비밀번호를 찾으려면 해당하는 아이디도 같이 넣어줘야 함 -->
                                    <!-- 근데 form 태그 안의 것들만 보내주므로 hidden으로 써주는 것 -->
                                    <input type="hidden" name="userId" value="<%=userId%>"">
                                    <table>
                                        <tr>
                                            <td>현재 비밀번호</td>
                                            <td><input type="password" name="userPwd" required></td>
                                        </tr>
                                        <tr>
                                            <td>변경할 비밀번호</td>
                                            <td><input type="password" name="updatePwd" required></td>
                                        </tr>
                                        <tr>
                                            <td>변경할 비밀번호 확인</td>
                                            <td><input type="password" name="checkPwd" required></td>
                                        </tr>
                                    </table>
                                    <br>
                                    <button id="edit-pwd-btn" type="submit" class="btn btn-sm btn-secondary">
                                        비밀번호 변경
                                    </button>
                                </form>
                                <script>
                                    // return false로 해도 안 막힐 수 있다?
                                    // if($("input[name=updatePwd]").val() !== $("input[name=checkPwd]").val()){

                                        // }
                                    document.getElementById("edit-pwd-btn").onclick = function(){
                                        const updatePwd = document.querySelector("input[name=updatePwd]").value;
                                        const checkPwd = document.querySelector("input[name=checkPwd]").value;
                                        
                                        if(updatePwd !== checkPwd){
                                            alert("비밀번호를 확인해주세요");
                                            return false;
                                        }
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
    </body>

    </html>