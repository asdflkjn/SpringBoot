<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세</title>
</head>
<body>
		<h1>글 상세</h1>		
		<hr>
		<form action="updateMember" method="post">
			<input name="seq" type="hidden" value="${Member.seq}" />
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">아이디</td>
					<td align="left"><input name="title" type="text"
						value="${Member.id}" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">이름</td>
					<td align="left">${Member.name}</td>
				</tr>
				<tr>
					<td bgcolor="orange">비밀번호</td>
					<td align="left"><textarea name="content" cols="40" rows="10">
						${Member.password}</textarea></td>
				</tr>
				<!--첨부한 파일이름-->
				<tr>
					<td bgcolor="orange">첨부파일</td>
					<td align="left">${Member.originFilename }</td>
						<img src='../files/${Member.filename}'>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="글 수정" /></td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="insertMember">글등록</a>&nbsp;&nbsp;&nbsp; 
		<a href="deleteMember?seq=${Member.seq }">글삭제</a>&nbsp;&nbsp;&nbsp;
		<a href="getMemberList">글목록</a>
</body>
</html>
