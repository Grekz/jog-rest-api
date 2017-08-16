package mx.grekz.jog.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.grekz.jog.entity.Jog;
import mx.grekz.jog.entity.User;

@Transactional
@Repository
public class JogDAOImpl implements JogDAO {

	@PersistenceContext
	private EntityManager em;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static final String qry = "from Jog as jog ";
	private static final String qryById = qry + " where jog.userId = :userId ";
	private static final String qryOrderById = qryById + "order by jog.id";
	private static final String qryBetween = qryById + "and jog.date between :iniDate and :endDate order by jog.date";
	private static final String qryByUserAndJogIds = qryById + "and jog.id = :jogId";

	@SuppressWarnings("unchecked")
	@Override
	public List<Jog> getAllJogsByUserId(int userId) {
		return (List<Jog>) getQuery(qryOrderById, userId).getResultList();
	}

	@Override
	public List<Jog> getAllJogsByUser(User user) {
		return getAllJogsByUserId(user.getUserId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jog> getJogsBetweenDates(int userId, String initialDate, String endDate) {
		return (List<Jog>) getQuery(qryBetween, userId, initialDate, endDate).getResultList();
	}

	@Override
	public List<Jog> getJogsBetweenDates(User user, String initialDate, String endDate) {
		return getJogsBetweenDates(user.getUserId(), initialDate, endDate);
	}

	@Override
	public List<Jog> getJogsBetweenDates(int userId, Date initialDate, Date endDate) {
		return getJogsBetweenDates(userId, format.format(initialDate), format.format(endDate));
	}

	@Override
	public List<Jog> getJogsBetweenDates(User user, Date initialDate, Date endDate) {
		return getJogsBetweenDates(user.getUserId(), initialDate, endDate);
	}

	@Override
	public Jog getJogById(int userId, int jogId) {
		return (Jog) getQuery(qryByUserAndJogIds, userId, jogId).getSingleResult();
	}

	@Override
	public Jog getJogById(User user, int jogId) {
		return getJogById(user.getUserId(), jogId);
	}

	@Override
	public Jog getJogById(int jogId) {
		return em.find(Jog.class, jogId);
	}

	@Override
	public boolean addJog(int userId, Jog jog) {
		// TODO implement properly
		jog.setUserId(userId);
		return addJog(jog);
	}

	@Override
	public boolean addJog(User user, Jog jog) {
		return addJog(user.getUserId(), jog);
	}

	@Override
	public boolean addJog(Jog jog) {
		System.out.println(jog.toString());
		em.persist(jog);
		return true;
	}

	@Override
	public Jog updateJog(Jog jog) {
		return em.merge(jog);
	}

	@Override
	public boolean deleteJog(int jogId) {
		em.remove(getJogById(jogId));
		return true;
	}

	@Override
	public boolean userHasJogs(int userId) {
		return getQuery(qryById, userId).getResultList().size() > 0;
	}

	@Override
	public boolean userHasJog(int userId, int jogId) {
		return getQuery(qryByUserAndJogIds, userId, jogId).getResultList().size() > 0;
	}

	@Override
	public boolean userHasJog(User user, int jogId) {
		return userHasJog(user.getUserId(), jogId);
	}

	@Override
	public boolean jogExists(int jogId) {
		return em.find(Jog.class, jogId) != null;
	}

	private Query getQuery(String queryStr, int userId) {
		return em.createQuery(queryStr).setParameter("userId", userId);
	}

	private Query getQuery(String queryStr, int userId, String ini, String end) {
		return getQuery(queryStr, userId).setParameter("iniDate", ini).setParameter("endDate", end);
	}

	private Query getQuery(String queryStr, int userId, int jogId) {
		return getQuery(queryStr, userId).setParameter("jogId", jogId);
	}

}
