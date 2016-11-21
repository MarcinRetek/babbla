package backingBeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import DAO.User;
import EJB.LocalUser;

@Named(value="userBean")
@RequestScoped
public class UserBean {
	//@EJB
	private LocalUser userEJB;	
	
	private String name;
	private String email;
	
	public String save() {
//		System.out.println("Inside USER SAVE");
//		User user = new User();
//		user.setName("kalle");
//		user.setEmail("kalle@kalle.se");
//		
//		userEJB.saveUser(user);
//		
		return "";
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
