<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE>
<head>
<meta charset="UTF-8">
<title>로그인성공</title>
</head>
<body>
	<h1>로그인성공페이지</h1>
	<div> ${member.name} 님 로그인성공</div>
	<hr/>

	<c:if test="${not empty memberfile.filename}">
	    <img src="${memberfile.filepath}" width="50" height="50" alt="첨부이미지"/>
	</c:if>

	<a href='logout'>로그아웃</a>
</body>
</html>
