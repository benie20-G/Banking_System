<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome <%=session.getAttribute("username") %> to Your Banking Account</h1>

<h1>Want to deposit</h1>
 <form action="DepositServlet" method="post">
        <label for="amount">Enter Deposit Amount:</label>
        <input type="number" id="amount" name="amount" required><br>
        <button type="submit">Deposit</button>
 </form>
  
    
    <c:if test="${not empty balanceMessage}">
        <h3 style="">${balanceMessage}</h3>
    </c:if>
    
    <h1>Want to withdraw</h1>
    <form action="WithdrawServlet" method ="post">
    <label >Enter withdraw amount</label>
    <input type="number"  name ="withdraw" required><br>
    <button type="submit">withdraw</button>
    </form>
    
     <c:if test="${not empty successMessage}">
        <h3 style="">${successMessage}</h3>
    </c:if>
    
      <c:if test="${not empty balanceMessage}">
        <h3 style="">${balanceMessage}</h3>
    </c:if>
  <%-- <% --
      <c:if test="${not empty account}">
        <p>Account ID: ${account.accountId}</p>
        <p>Username: ${account.username}</p>
        <p>Balance: ${account.balance}</p>
    </c:if> --%>
       <form action="LogoutServlet" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>