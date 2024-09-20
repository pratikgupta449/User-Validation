<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update.jsp page</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="container">
		<h1>Updating name and Phone number</h1>
		
		<form action="UpdateServlet" method="post">
			<label for="name">name:</label> 
			<input type="text" id="name"name="name" required> <br>
			 
				<label for="email">Email:</label> 
				<input type="email" id="email" name="email" required> <br>
				
				 <label for="phone">Phone:</label> 
				<input type="number" id="phone" name="phone" required> <br> 
				
			<button type="submit">Update</button> <br>
			
			<p><a href="home.jsp"> Home</a></p>
		</form>
		
		<%--after validating data from LoginServlet.java if data not match from the database 
it will redirect to login.jsp page and show this error  --%>
<%
//taking data from LoginServlet.java in key value pair.
String updateFailed=request.getParameter("updateFail");

if("fail".equals(updateFailed)){ 
%>
	 <p style="color: red">Invalid email</p>
<%} %>
		
		</div>

</body>
</html>