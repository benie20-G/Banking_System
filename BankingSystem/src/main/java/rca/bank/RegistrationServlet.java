package rca.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)  throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String  username = req.getParameter("username");
		String password = req.getParameter("password");

		Customer newCustomer = new Customer(username,password);
		newCustomer.setUsername(username);
		newCustomer.setPassword(password);


	     try (Connection connection = CustomerConn.getConnection()) {
	            CustomerConn customerConn =new CustomerConn(connection);

	            if (!newCustomer.isValidUsername() || !newCustomer.isValidPassword()) {
	                out.println("<p>Registration Failed. Invalid Input.</p>");
	            } else {
	                if (customerConn.addCustomer(newCustomer)) {
	                    out.println("<p>Registration successful!</p>");
	                } else {
	                    out.println("<p>Registration failed. Please try again.</p>");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle SQLException appropriately
	        }
	    }


	}


