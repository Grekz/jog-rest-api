package mx.grekz.jog.dao;

import java.util.List;

import mx.grekz.jog.entity.User;

public interface UserDAO {
	List<User> getAllUsers();
	User getUserById(int userId);
	void addUser(User user);
	User updateUser(User user);
	void deleteUser(int id);
	boolean userExists(Integer userId);
	boolean userExists(User usr);
}
