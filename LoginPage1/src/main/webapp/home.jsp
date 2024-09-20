<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home.jsp page</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="container">
<h1>Home Page</h1>

<form action="DetailsServlet" method="post">
<button type="submit">Details</button>
</form> <br>

<form>
<button type="button" onclick="location.href='update.jsp';">Update</button>
<%-- <button type="submit">
<a href="update.jsp" id="update">Update</a>
</button> --%>
</form> <br>

<form action="DeleteServlet" method="post">
<button type="submit">Delete</button> <br>

<p><a href="index.html">Back to Home</a></p>
</form> <br>


<%--after Updating data from UpdateServlet.java if data not update from the database 
it will redirect to update.jsp page  --%>
<%
//updating data from UpdateServlet.java in key value pair.
String UpdateSuccess=request.getParameter("updateSuccess");

if("success".equals(UpdateSuccess)){ 
%>
	 <p style="color: green">Details Updated</p>
<%} %>

<%--after Deleting data from DeleteServlet.java  from the database 
  --%>
<%
//taking data from LoginServlet.java in key value pair.
String DeleteFail=request.getParameter("deleteFail");

if("fail".equals(DeleteFail)){ 
%>
	 <p style="color: red">no record found</p>
<%} %>


</div>

</body>
</html>