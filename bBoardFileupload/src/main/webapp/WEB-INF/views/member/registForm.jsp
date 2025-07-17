<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title> 폼 예제 1 </title>
</head>
<body>

<h2>회원가입</h2>
<form name="frm" id='frm' method="post" action="saveMember" enctype='multipart/form-data'>
		<label for="id">아이디</label>
		<input type="text" id="id"  name="id"  pattern="[A-Za-z]{4,20}" required> 
		<br>
		<label for="password">비밀번호</label>
		<input type='password' id="password"  name="password" pattern="[A-Za-z0-9]{3,}"> 
		<br>
		<label for="name">이름</label>
		<input type="text" id="name"  name="name"  pattern="[가-힣]{2,5}" required> 
		<br>
		<!--파일첨부 추가 여기까지가 파일처리의 시작-->
		<tr>
			<td bgcolor="orange">화일첨부</td><td align="left">
			<input type='file' name='file'></td>
		</tr>
		<!--첨부한 파일이름-->
		<br>
		<tr>
			<td bgcolor="orange">첨부파일</td>
			<td align="left">${member.originFilename }</td>
				<img src='../files/${member.originFilename}'>
		</tr>
		<br/>
		<!--2.  체크사항 -->
		<label for='agree'>약관을 읽었습니다.</label>
		<input type="checkbox" id="agree"  name="agree" required> 네, 확인하였습니다.
		<br/><br/><br/>
	<input type="submit" value="회원가입"> 

</form>
</body>
</html>