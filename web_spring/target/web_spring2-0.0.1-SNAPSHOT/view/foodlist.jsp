<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
table {
	width: 50%;
	border: 1px solid #444444;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #444444;
	padding: 10px;
}
</style>

</head>
<body>
	<h1>${dong }</h1>
	<table>
		<thead>
			<tr>
				<td>이름</td>
				<td>색깔</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${foodMap }" var="foodVO">
				<tr>
					<td><c:out value="${foodVO.key }" /></td>
					<td><c:out value="${foodVO.value.color }" /></td>
				</tr>
			</c:forEach>


		</tbody>

	</table>

	<form action="/FoodController" method="post" accept-charset="UTF-8">
		<input type="text" name="name" id="id" value="" /> 
		<input type="text" name="color" id="id" value="" /> 
		<input type="hidden" name="hidden" value="insert" /> 
		<input type="submit" name="menu" value="저장" />
	</form>
	<form action="/FoodController" method="post" accept-charset="UTF-8">
		<input type="text" name="name" id="name" value="" /> 
		<input type="hidden" name="hidden" value="delete" /> 
		<input type="submit" name="menu" value="삭제" />
	</form>


</body>
</html>