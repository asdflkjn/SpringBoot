<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>아이디 목록</title>
</head>
<body>
    <h1>아이디 목록</h1>

    <table border="1">
        <tr>
            <th bgcolor="orange" width="200">아이디</th>
            <th bgcolor="orange" width="200">이름</th>
            <th bgcolor="orange" width="150">비밀번호</th>
            <th bgcolor="orange" width="150">첨부파일</th>
        </tr>
        <c:forEach items="${memberList}" var="member">
            <tr>
                <td align="left">
                    <a href="getmember?id=${member.id}">${member.id}</a>
                </td>
                <td>${member.name}</td>
                <td>${member.password}</td>
                <td>
                    <c:if test="${not empty memberfile.filename}">
                        <img src="../files/${memberfile.filepath}" width="100" height="100" alt="첨부이미지"/>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
