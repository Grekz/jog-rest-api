package mx.grekz.jog.dao;

import java.util.List;

import mx.grekz.jog.entity.User;

public interface UserDAO {
	List<User> getAllUsers();
	User getUserById(int userId);
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(int id);
	boolean userExists(User usr);
}
