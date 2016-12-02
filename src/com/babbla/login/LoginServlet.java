package com.babbla.login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babbla.backingbeans.LoginUserBean;
import com.babbla.interfaces.LocalUser;
import com.babbla.models.User;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	LocalUser userEJB;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		
		User user = new User();
		user.setName(username);
		user.setEmail(email);
		
		setUserSession(user);

		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();
				
		if(userEJB.validateUser(user)){
			response.sendRedirect(baseURL + "/faces/index.jsp");
		}else{
			setUserSession(user);
			String message = "User already exists in database";
			response.sendRedirect("index.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}
			
	}
	
	public void setUserSession(User user) {
		LoginUserBean loginUserBean = new LoginUserBean();		
		loginUserBean.setLoggedInUser(user);
		loginUserBean.doLogin();
	}
 

}
