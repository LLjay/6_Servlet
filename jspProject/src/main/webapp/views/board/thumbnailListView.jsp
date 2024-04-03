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

    list-area

    .thumbnail p > span{
        display: inline-block;

        text-overflow: ellipsis;
        /* 넘치면 ... 해줘 */
        white-space: nowrap;
        /* 넘쳤을 때 다음 줄로 넘기지 마라 */
        overflow: hidden;
        /* 넘치면 더 보여주지 않고 그 뒤를 숨겨줘 */
        /* 보통 세 개를 세트로 넣어줌 */
    }

</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp"%>

    <div class="outer">
        <br>
        <h2 align="center">사진게시판</h2>
        <br>

        <div class="list-area">
            <div class="thumbnail" align="center">
                <img src="" alt="썸네일" width="200px" height="150px">
                <p>
                    <span>No.125 디트로이트 비컴 휴먼</span>
                    <!-- 이 길이가 넘치면 그 뒤의 텍스트를 ...으로 처리해줘 할 때 그 기준 넓이를 줘야 함 -->
                    조회수 : 155
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img src="" alt="썸네일" width="200px" height="150px">
                <p>
                    <span>No.125 디트로이트 비컴 휴먼</span>
                    <!-- 이 길이가 넘치면 그 뒤의 텍스트를 ...으로 처리해줘 할 때 그 기준 넓이를 줘야 함 -->
                    조회수 : 155
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img src="" alt="썸네일" width="200px" height="150px">
                <p>
                    <span>No.125 디트로이트 비컴 휴먼</span>
                    <!-- 이 길이가 넘치면 그 뒤의 텍스트를 ...으로 처리해줘 할 때 그 기준 넓이를 줘야 함 -->
                    조회수 : 155
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img src="" alt="썸네일" width="200px" height="150px">
                <p>
                    <span>No.125 디트로이트 비컴 휴먼</span>
                    <!-- 이 길이가 넘치면 그 뒤의 텍스트를 ...으로 처리해줘 할 때 그 기준 넓이를 줘야 함 -->
                    조회수 : 155
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img src="" alt="썸네일" width="200px" height="150px">
                <p>
                    <span>No.125 디트로이트 비컴 휴먼</span>
                    <!-- 이 길이가 넘치면 그 뒤의 텍스트를 ...으로 처리해줘 할 때 그 기준 넓이를 줘야 함 -->
                    조회수 : 155
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img src="" alt="썸네일" width="200px" height="150px">
                <p>
                    <span>No.125 디트로이트 비컴 휴먼</span>
                    <!-- 이 길이가 넘치면 그 뒤의 텍스트를 ...으로 처리해줘 할 때 그 기준 넓이를 줘야 함 -->
                    조회수 : 155
                </p>
            </div>
        </div>
    </div>
</body>
</html>