package rca.bank;

import java.sql.SQLException;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class WithdrawServlet
 */
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    HttpSession session = request.getSession(true);
        if (session != null && session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            String amountStr = request.getParameter("withdraw");

            // Obtain a connection
            try (Connection connection = CustomerConn.getConnection()) {
                if (amountStr != null && !amountStr.isEmpty()) {
                    try {
                        int amount = Integer.parseInt(amountStr);
                        if (amount > 0) {
                            if (withDraw(username, amount, connection)) {
                                request.setAttribute("successMessage", "You have withdrawn $" + amount);
                                // Redirect to the banking page or any other appropriate page
                                request.getRequestDispatcher("banking.jsp").forward(request, response);
                                return;
                            } else {
                                request.setAttribute("errorMessage", "Failed to withdraw the money");
                            }
                        } else {
                            request.setAttribute("errorMessage", "Invalid withdrawal amount");
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("errorMessage", "Invalid withdrawal amount");
                    } catch (SQLException e) {
                        e.printStackTrace(); // Handle SQLException appropriately
                    }
                } else {
                    request.setAttribute("errorMessage", "Please enter the withdrawal amount");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle SQLException appropriately
            }
        } else {
            response.sendRedirect("Login.jsp");
        }
    }
	

	private boolean withDraw(String username, int amount,Connection connection) throws SQLException {
		String query ="UPDATE accounts SET balance = balance - ? WHERE username = ? ";
		try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, amount);
			preparedStatement.setString(2, username);
			int affectedRows  = preparedStatement.executeUpdate();
			return  affectedRows > 0;
			
		}
	
	}
}
