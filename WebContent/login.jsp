<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/lib/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submit").click(function() {

			var userId = $("#userId").val().trim();
			var password = $("#password").val().trim();
			var path = "${pageContext.request.contextPath}/user/loginAction";
			$.ajax({
				type : "post",
				dataType : "json",
				contentType : 'application/json',
				data : JSON.stringify({
					"userId" : userId,
					"password" : password
				}),
				url : path,
				success : function(data) {
					alert(data.msg);
				}

			})

			return false;
		});

	});
</script>
<body>


	<h2>登录界面</h2>
	<br>
	<form:form action="loginAction" method="post" modelAttribute="user"
		commandName="user">
		<br>
		用户：
		<form:input path="userId" id="userId" value="admin" />
		<form:errors path="userId"></form:errors>
		<font color="red"><form:errors path="*"></form:errors></font>
		<br>
		<br>
		密码：
		<form:password value="1234" path="password" id="password" />
		<font color="red"><form:errors path="password"></form:errors></font>
		<br>
		<input type="submit" value='登录' id="submit" />
	</form:form>

</body>
</html>