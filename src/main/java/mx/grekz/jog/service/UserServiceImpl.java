package mx.grekz.jog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.grekz.jog.dao.UserDAO;
import mx.grekz.jog.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public User getUserById(int userId) {
		// TODO: implement null pattern
		User usr = userDAO.getUserById(userId);
		return usr;
	}

	@Override
	public synchronized boolean addUser(User user) {
		if (!userDAO.userExists(user)) {
			userDAO.addUser(user);
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean updateUser(User user) {
		int userId = user.getUserId();
		boolean userExists = userDAO.userExists(userId);
		if (userExists) {
			userDAO.updateUser(user);
			return true;
		}
		return false;
	}

	@Override
	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}

	@Override
	public boolean userExists(Integer userId) {
		return userDAO.userExists(userId);
	}

	@Override
	public boolean userExists(User user) {
		return userDAO.userExists(user);
	}
}
