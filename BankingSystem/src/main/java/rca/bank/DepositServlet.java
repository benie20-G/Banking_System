package rca.bank;


import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * Servlet implementation class DepositServlet
 */
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session != null && session.getAttribute("username") != null) {
			String username =(String) session.getAttribute("username");
			String amountStr = request.getParameter("amount");
			
			if(amountStr  != null && !amountStr.isEmpty()) {
				try(Connection connection = CustomerConn.getConnection()){
					int balance = balanceLeft(username,connection);
					int amount = Integer.parseInt(amountStr);
					if(amount > 0) {
						if(deposit(username,amount)) {
							request.setAttribute("successMessage", "You have deposited $" + amount);
							request.setAttribute("balanceMessage", "You balance is $"+balance);;
							request.getRequestDispatcher("banking.jsp").forward(request,response);
							return;
						}else {
							request.setAttribute("errorMessage","Failed to deposit the money");
						}
					}else {
						request.setAttribute("errorMessage", "Invalid deposit amount");
					}
				}catch(NumberFormatException e){
					request.setAttribute("errorMessage","Invalid deposit amount");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else {
			request.setAttribute("errorMessage","Please enter the deposit money");
		}
	}else {
		response.sendRedirect("Login.jsp");
	}

}


	private boolean deposit(String username , int amount) {
		try(Connection connection1 = CustomerConn.getConnection()){
			if(!accountExists(username,connection1)) {
				if(!createAccount(username,amount, connection1)) {
					return false;
				}
			}

			String query = "UPDATE accounts SET balance  = balance + ? WHERE username= ?";
			try(PreparedStatement preparedStatement = connection1.prepareStatement(query)){
				preparedStatement.setDouble(1, amount);
				preparedStatement.setString(2, username);
				int affectedRows = preparedStatement.executeUpdate();
				return affectedRows > 0;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;


	}
	
	private int balanceLeft(String username,Connection connection) throws SQLException{
		int balance =0;
		String selectQuery = "SELECT balance from accounts WHERE username = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)){
			preparedStatement.setString(1, username);
			try(ResultSet result = preparedStatement.executeQuery()){
				if(result.next()) {
					balance = result.getInt("balance");
				}
			}
		}
		return balance; 
	}


	private boolean createAccount(String username,int initialBalance, Connection connection) throws SQLException{
		String insertQuery ="INSERT INTO accounts(username,balance) VALUES(?,?)";
		try(PreparedStatement insertStatement=connection.prepareStatement(insertQuery)){
			insertStatement.setString(1, username);
			insertStatement.setInt(2, initialBalance);

			int rowsInserted =  insertStatement.executeUpdate();
			return rowsInserted > 0;
		}
	}

	private boolean  accountExists(String username,Connection connection) throws SQLException {
		String query = "SELECT COUNT(*) FROM accounts where username= ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setString(1,username);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.next()) {
					int count = resultSet.getInt(1);
					return count > 0;
				}
			}
		}
		return false;
	}
}
