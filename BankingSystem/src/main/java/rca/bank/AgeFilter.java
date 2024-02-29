package rca.bank;

import jakarta.servlet.Filter;


import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public abstract class AgeFilter implements Filter {
	public void doFilter(jakarta.servlet.ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String ageStr = request.getParameter("age");
		if(ageStr != null && ageStr.isEmpty()) {
			try {
				int age = Integer.parseInt(ageStr);
				if(age > 18) {
					chain.doFilter(request,response);
				}else {
					request.setAttribute("errorMessage","You must be at least 18 years old to register");
					request.getRequestDispatcher("Register.jsp").forward(request, response);
					}
		}catch (NumberFormatException e) {
			 request.setAttribute("errorMessage", "Invalid age format.");
	            request.getRequestDispatcher("Register,jsp").forward(request, response);
		}

				}else {
					 request.setAttribute("errorMessage", "Age is required.");
				        request.getRequestDispatcher("Register.jsp").forward(request, response);
				    }
				}
	}


}
	
