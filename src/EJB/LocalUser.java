package EJB;

import javax.ejb.Local;

import DAO.User;

@Local
public interface LocalUser {
	
	public User saveUser(User user);

}
