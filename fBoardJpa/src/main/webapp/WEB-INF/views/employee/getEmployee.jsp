<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>직원 상세</title>
</head>
<body>
	<h1> 직원 상세 </h1>
	<br>
	<form action="updateEmployee" method="post">
		<input name="empno" type="hidden" values="${employee.empno}"/>
		<table border="1">
			<tr>
				<th bgcolor="orange" width="100">사원번호</th>
				<td><input name="empno" type="text" value="${employee.empno}" /></td>
			</tr>
			<tr>			
				<th bgcolor="orange" width="200">사원명</th>
				<td><input name="ename" type="text" value="${employee.ename}" /></td>
			<tr>	
				<th bgcolor="orange" width="150">업무</th>
				<td><input name="job" type="text" value="${employee.job}" /></td>
			</tr>
				<th bgcolor="orange" width="150">입사일</th>
				<td><input name="hiredate" type="text" value="${employee.hiredate}" /></td>
			<tr>	
				<th bgcolor="orange" width="100">월급</th>
				<td><input name="sal" type="text" value="${employee.sal}" /></td>
			</tr>	
				<th bgcolor="orange" width="100">커미션</th>
				<td><input name="comm" type="text" value="${employee.comm}" /></td>
			<tr>	
				<th bgcolor="orange" width="100">부서번호</th>
				<td><input name="deptno" type="text" value="${employee.deptno}" /></td>
			</tr>
			<tr>
			</tr>
		</table>
	</form>
	<br> <a href="updateEmployee">직원 수정</a>
	<br> <a href="deleteEmployee">직원 삭제</a>
</body>
</html>