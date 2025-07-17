<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새글등록</title>
</head>
<body>

<h3>새글 등록하기</h3>
<hr>
<form action="saveBoard" method="post" enctype='multipart/form-data'> <!--얘가 있어야 파일이 업로딩된당-->
<table border="1">
	<tr>
		<td bgcolor="orange" width="70">제목</td><td align="left">
		<input type="text" name="title"/></td>
	</tr>
	<tr>
		<td bgcolor="orange">작성자</td><td align="left">
		<input type="text" name="writer" size="10"/></td>
	</tr>
	<tr>
		<td bgcolor="orange">내용</td><td align="left">
		<textarea name="content" cols="40" rows="10"></textarea></td>
	</tr>
	<!--파일첨부 추가 여기까지가 파일처리의 시작-->
	<tr>
		<td bgcolor="orange">화일첨부</td><td align="left">
		<input type='file' name='file'></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="submit" value=" 새글 등록 "/></td>
	</tr>
</table>
</form>
<hr>

</body>
</html>
