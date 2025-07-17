<%@ page contentType="text/html; charset=UTF-8"%>


<!DOCTYPE>
<html>
<head>
<title> 회원가입</title>
</head>
<body>
<h1> 회원가입이 성공하였습니다.</h1>
<td>
    <c:if test="${sessionScope.memberFile.filename}">
        <img src="../files/${sessionScope.memberFile.filename}" width="200" height="200" alt="첨부이미지"/>
    </c:if>
</td>
</body>
</html>