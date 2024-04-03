<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        height: 700px;
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
    #enroll-form img:hover{
        cursor: pointer;
        scale: 0.98;
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
    <div class="outer">
        <br>
        <h2 align="center">사진게시판 작성하기</h2>
        <br>

        <form action="" id="enroll-form">
            <table align="center">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">
                        <input type="text" name="title" required>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                     <td>
                        <textarea name="content" rows="5" style="resize: none;"></textarea>
                     </td>
                </tr>
                <tr>
                    <th>대표이미지</th>
                    <td colspan="3">
                        <img id="title-img" width="250" height="170" onclick="chooseFile(1)">
                        <!-- alt가 있어서 제대로 나오지 않는다? -->
                    </td>
                    <!-- 결국 input에 넣을 수밖에 없음, 그럴 듯하게 사용자를 속이는 것 -->
                </tr>
                <tr>
                    <th>상세이미지</th>
                    <td><img id="content-img1" width="150" height="120" onclick="chooseFile(2)"alt=""></td>
                    <td><img id="content-img2" width="150" height="120" onclick="chooseFile(3)"alt=""></td>
                    <td><img id="content-img3" width="150" height="120" onclick="chooseFile(4)"alt=""></td>
                </tr>
                <!-- img의 온클릭을 input의 온클릭처럼 만들 것? -->
                <!-- img를 클릭하면 input이 클릭 되도록 -->
            </table>
            <div style="display: none;">
                <input type="file" name="file1" id="file1" required onchange="loadImg(this, 1)">
                <input type="file" name="file2" id="file2" onchange="loadImg(this, 2)">
                <input type="file" name="file3" id="file3" onchange="loadImg(this, 3)">
                <input type="file" name="file4" id="file4" onchange="loadImg(this, 4)">
            </div>

            <script>
                function loadImg(inputFile, num){
                        // console.log("file input 값 변경됨")
                        // input에 사진이 들어갔기 때문에 속성이 변경되어 함수 실행, 실행될 때 클릭된 input(this)를 보내줄 것
                    console.log(inputFile);
                    console.log(num);
                    // inputFile : 현재 변화가 생긴 input type=file 요소 객체
                    // inputFile.files[0] -> 선택된 파일이 담겨 있음
                    // inputFile.files.length -> 1

                    console.log(inputFile.files)
                    // file이기 때문에 files 속성이 있음
                    // file을 여러 개 한 번에 올리면 files로 들어옴
                    // files의 0번째에 하나가 들어옴
                    console.log(inputFile.files.length)

                    if (inputFile.files.length == 1) { // 파일을 하나 선택했다. onchange (없는 상태에서 취소 누르면 변화가 일어나지 않으므로 값이 없음)
                        // 파일을 읽어들일 FileReader 객체 생성
                        const reader = new FileReader();

                        // 파일을 읽어들이는 메소드
                        // 해당 파일을 읽어들이는 순간 해당 파일 만의 고유한 url을 부여
                        reader.readAsDataURL(inputFile.files[0]);
                            // 이 파일을 DataURL을 통해 읽어들이겠다
                            // 읽어오는데 조금 시간이 걸림

                        // 파일을 다 읽어들였을 때 실행할 함수 정의
                        reader.onload = function(ev){
                            // 로딩이 끝나면 실행하라
                            // 이벤트 핸들러?????????????
                            // ev 무조건 처음 가져오는 이벤트 객체
                            // ev.target : 이 이벤트를 누가 실행했냐
                            // console.log(ev.target.result); -> 읽어들인 파일의 고유한 url
                            // -> 이미지 url 나옴
                            switch(num){
                                case 1 : document.getElementById("title-img").src = ev.target.result; break;
                                case 2 : document.querySelector("#content-img1").src = ev.target.result; break;
                                case 3 : $("#content-img2").attr("src", ev.target.result); break;
                                case 4 : $("#content-img3").attr("src", ev.target.result); break;
                            }

                        }
                    } else {
                        switch(num){
                                case 1 : document.getElementById("title-img").src = null; break;
                                case 2 : document.querySelector("#content-img1").src = null; break;
                                case 3 : $("#content-img2").attr("src", null); break;
                                case 4 : $("#content-img3").attr("src", null); break;
                            }
                    }
                }



                function chooseFile(num){
                    // 몇 번째 이미지를 클릭 했는지 num에 넣어줄 것
                    const imgInput = document.querySelector("#file" + num);
                    imgInput.click();
                }
            </script>

            <br>

            <div align="center">
                <input type="submit" value="등록하기">
            </div>
        </form>
    </div>
</body>
</html>