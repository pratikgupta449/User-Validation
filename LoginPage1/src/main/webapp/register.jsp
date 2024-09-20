<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register.jsp page</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="container">
		<h1>Register</h1>
		
		<form action="RegisterServlet" method="post">
			<label for="name">name:</label> 
			<input type="text" id="name"name="name" required> <br>
			 
				<label for="email">Email:</label> 
				<input type="email" id="email" name="email" required> <br>
				
				 <label for="phone">Phone:</label> 
				<input type="number" id="phone" name="phone" required> <br> 
				
				<label for="password">Password:</label>
			<input type="text" id="password" name="password" required> <br>
			
			<button type="submit">Register</button>
		</form>
		<p>
			<a href="login.jsp">Login</a>
		</p> <br>
		<p>
			<a href="index.html">Back to Home</a>
		</p>
	</div>
	<%--on registering if if any error occur/duplicate email id enter on Registering then 
	it redirect to RegisterServlet.java to register.jsp and show this error on register.jsp page--%>
	<%--writing java code into scripting JSP tag --%>
	
	<%
	//taking data from RegisterServlet.java in key and value pair if failed.
	String failed=request.getParameter("registerFailed");
	
	if("fail".equals(failed)){
		%>
		<p style="color: red">already user exist</p>
	<% }%>
	


</body>
</html>