package EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import DAO.User;
import DAO.UserDAO;

@Stateless
public class UserEJB implements LocalUser {
	
	@EJB
	UserDAO userDao;

	@Override
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}
	
}
