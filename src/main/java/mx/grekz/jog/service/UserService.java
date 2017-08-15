package mx.grekz.jog.service;

import java.util.List;

import mx.grekz.jog.entity.User;

public interface UserService {
	List<User> getAllUsers();

	User getUserById(int userId);

	boolean addUser(User user);
	
	boolean userExists(Integer userId);
	
	boolean userExists(User user);

	boolean updateUser(User user);

	void deleteUser(int id);
}
