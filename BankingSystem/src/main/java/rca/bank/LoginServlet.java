package rca.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if(validateInput(username,password)) {
			if(checkCredentials(username,password)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				response.sendRedirect("banking.jsp");

			}else {
				request.setAttribute("errorMessage", "Invalid username or password");
				request.getRequestDispatcher("Login.jsp").forward(request,response);
			}
		}else {
			request.setAttribute("errorMmessage","Invalid username or password format");
			request.getRequestDispatcher("Login.jsp").forward(request,response);

		}

	}

		private boolean validateInput(String username, String password){
			return username != null && !username.isEmpty() && password != null && !password.isEmpty();

		}

		private boolean checkCredentials(String username,String password) {
			try(Connection connection = CustomerConn.getConnection()) {
				String query = "SELECT * FROM customers WHERE username = ? AND password = ? ";
				try (PreparedStatement preparedStatement= connection.prepareStatement(query)){
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);

					try(ResultSet resultSet = preparedStatement.executeQuery()) {
						return resultSet.next();
					}
				}
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}

}

}

