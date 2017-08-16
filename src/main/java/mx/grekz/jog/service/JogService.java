package mx.grekz.jog.service;

import java.util.List;

import mx.grekz.jog.entity.Jog;

public interface JogService {
	List<Jog> getAllJogsByUserId(int userId);
	List<Jog> getJogsBetweenDates(int userId, String initialDate, String endDate);
	Jog getJogById(int userId, int jogId);

	Jog updateJog(Jog jog);
	
	boolean addJog(Jog jog);
	boolean userHasJog(int userId, int jogId);
	boolean jogExists(int jogId);
	
	void deleteJog(int jogId);
}
