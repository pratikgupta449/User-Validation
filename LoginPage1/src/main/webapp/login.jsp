<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp page</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="container">
		<h1>Login</h1>
		<form action="LoginServlet" method="post">
			<label for="email">email:</label> <input type="email" id="email"
				name="email" required> <br> <label for="password">Password:</label>
			<input type="text" id="password" name="password" required> <br>
			<button type="submit">Login</button>
		</form>

		<p>
			<a href="register.jsp">Register</a>
		</p>
		<br>
		<p>
			<a href="index.html">Back to Home</a>
		</p>


		<%--after register successfully RegisterServlet.java page redirect to login.jsp page and 
then it show this message on login page. --%>
		<%--writing java code into scripting JSP tag --%>
		<%
		//taking data from RegisterServlet.java in key and value pair.
		String registerSuccess = request.getParameter("registerSuccessfully");

		if ("success".equals(registerSuccess)) {
		%>
		<p style="color: green">Registration successfully. Please login</p>
		<%
		}
		%>


		<%--after validating data from LoginServlet.java if data not match from the database 
it will redirect to login.jsp page and show this error  --%>
		<%
		//taking data from LoginServlet.java in key value pair.
		String loginFailed = request.getParameter("loginFail");

		if ("fail".equals(loginFailed)) {
		%>
		<p style="color: red">Invalid username and password</p>
		<%
		}
		%>

		<%--after Deleting data from DeleteServlet.java  from the database 
  --%>
		<%
		//taking data from LoginServlet.java in key value pair.
		String DeleteSuccess = request.getParameter("deleteSuccess");

		if ("success".equals(DeleteSuccess)) {
		%>
		<p style="color: green">Details Deleted</p>
		<%
		}
		%>


	</div>

</body>
</html>