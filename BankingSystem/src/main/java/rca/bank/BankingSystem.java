package rca.bank;

import jakarta.servlet.ServletException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;

public class BankingSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public BankingSystem() {
        super();
        // TODO Auto-generated constructor stub
    }



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(false);
		if(session != null && session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			Account account = null;
			try {
				account = getAccount(username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(account != null) {
				request.setAttribute("account", account);
				request.getRequestDispatcher("banking.jsp").forward(request,response);
			}else {
				response.sendRedirect("Login.jsp");
			}
		}else {
			response.sendRedirect("Login.jsp");
		}
	}

	private Account getAccount(String username) throws SQLException {
		try(Connection connection = CustomerConn.getConnection()){
			String query = "SELECT * FROM accounts WHERE username = ?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
			    preparedStatement.setString(1,username);
				try (ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.next()) {
						int accountId = resultSet.getInt("accountId");
						double balance = resultSet.getDouble("balance");
						return new Account(accountId,username,balance);
					}
				}
		}

		}catch(SQLException e){
			e.printStackTrace()	;
		}
		return null;
	}


}
