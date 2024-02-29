<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Login Page</h1>
 <%-- Display error message if present --%>
    <c:if test="${not empty errorMessage}">
        <p style="color: red">${errorMessage}</p>
    </c:if>
<form action="LoginServlet" method="post">
<label>Username: </label>
<input type="text" name="username" required><br>
<label>Password: </label>
<input type="password" name="password" required ><br>
<input type="submit" value="LogIn"><br>
<a href="Registration.jsp" style={color:blue}>Create Account</a>
</form>

</body>
</html>