<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>scope 확인</title>
</head>
<body>
    <h3>page : ${pageMessage}</h3>
    <h3>request : ${requestMessage}</h3>
    <h3>session : ${sessionMessage}</h3>
    <h3>application : ${applicationMessage}</h3>
</body>
</html>