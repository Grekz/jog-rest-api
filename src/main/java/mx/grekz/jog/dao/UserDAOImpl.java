package mx.grekz.jog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.grekz.jog.entity.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		final String hql = "from User as usr order by usr.id";
		return (List<User>) manager.createQuery(hql).getResultList();
	}

	@Override
	public User getUserById(int userId) {
		return manager.find(User.class, userId);
	}

	@Override
	public void addUser(User user) {
		manager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO: implement this
	}

	@Override
	public void deleteUser(int id) {
		manager.remove(getUserById(id));
	}

	@Override
	public boolean userExists(User usr) {
		String hql = "from User as usr where usr.email = ? or usr.username = ? ";
		int count = manager.createQuery(hql).setParameter(1, usr.getEmail()).setParameter(2, usr.getUsername())
				.getResultList().size();

		return count > 0;
	}

}
