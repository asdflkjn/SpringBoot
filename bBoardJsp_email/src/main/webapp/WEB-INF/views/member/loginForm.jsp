<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

<form action="loginMember" method="post">
	<table border="1">
		<tr>
			<td bgcolor="orange" width="70">이메일</td>
			<td align="left"><input type="text" name="id"/></td>
		</tr>
		<tr>
			<td bgcolor="orange">패스워드</td>
			<td align="left"><input type="password" name="password" size="10"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"	value=" 로그인 "/></td>
		</tr>
	</table>
</form>

</body>
</html>
