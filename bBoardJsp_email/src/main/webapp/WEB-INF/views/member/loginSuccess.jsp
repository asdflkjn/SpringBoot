<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE>
<head>
<meta charset="UTF-8">
<title>로그인성공</title>
</head>
<body>
	<h1>로그인성공페이지</h1>
	<div> ${sessionScope.login.name} 님 로그인성공</div>
	
	<hr/>
	<a href='logout'>로그아웃</a>
</body>
</html>
