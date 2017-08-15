package mx.grekz.jog.dao;

import java.sql.Date;
import java.util.List;

import mx.grekz.jog.entity.Jog;
import mx.grekz.jog.entity.User;

public interface JogDAO {
	List<Jog> getAllJogsByUserId(int userId);
	List<Jog> getAllJogsByUser(User user);

	List<Jog> getJogsBetweenDates(int userId, Date initialDate, Date endDate);
	List<Jog> getJogsBetweenDates(int userId, String initialDate, String endDate);
	List<Jog> getJogsBetweenDates(User user, Date initialDate, Date endDate);
	List<Jog> getJogsBetweenDates(User user, String initialDate, String endDate);
	
	Jog getJogById(int userId, int jogId);
	Jog getJogById(User user, int jogId);
	Jog getJogById(int jogId);

	boolean addJog(int userId, Jog jog);
	boolean addJog(User user, Jog jog);
	boolean addJog(Jog jog);
	
	Jog updateJog(Jog jog);
	
	boolean deleteJog(int jogId);
	
	boolean userHasJogs(int userId);

	boolean userHasJog(int userId, int jogId);
	boolean userHasJog(User user, int jogId);
	
	boolean jogExists(int jogId);
}
