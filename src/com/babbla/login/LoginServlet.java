package com.babbla.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babbla.exceptions.ValidateException;
import com.babbla.interfaces.LocalUser;
import com.babbla.models.User;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    LocalUser userEJB;
    
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
				
		User user = new User();
		user.setName(username);
		user.setEmail(email);
		
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();
		
		try {
			userEJB.validateUser(user);
			if (userEJB.saveUser(user) != null) {
				response.sendRedirect(baseURL + "/faces/chat.xhtml");
				//TODO: check session here.
			}
			else{
				System.out.println("ELSE");
				response.sendRedirect(baseURL + "/faces/error.xhtml");
				//TODO: check why else is now working

			}
			
		} catch (ValidateException e) {
			e.getMessage();
		}		
		
		
	}

}
