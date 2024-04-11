<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String year = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
%>
Copyright © 1999-<%=year %>, The Apache Software Foundation <br>
Apache Tomcat, Tomcat, Apache, the Apache Tomcat logo and the Apache logo are either registered trademarks or trademarks of the Apache Software Foundation.
test : ${param.test}
<!-- param 영역에 담았으니까 앞에 param을 표기해주는 것 -->
</body>
</html>