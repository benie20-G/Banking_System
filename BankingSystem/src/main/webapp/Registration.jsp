<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Registration Form</h1>

<form action="RegistrationServlet" method="post">
<label>username:</label>
<input type="text" name="username" required><br>
<label>Password:</label>
<input type="password" name="password" required>
<label>Age:</label>
<input type="number" name="age" requ<%!  %><%!  %>ired>

<input type="submit" value="Register">
</form>

</body>
</html>