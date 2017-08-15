package mx.grekz.jog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.grekz.jog.dao.JogDAO;
import mx.grekz.jog.entity.Jog;

@Service
public class JogServiceImpl implements JogService {

	@Autowired
	private JogDAO jogDAO;

	@Override
	public List<Jog> getAllJogsByUserId(int userId) {
		return jogDAO.getAllJogsByUserId(userId);
	}

	@Override
	public List<Jog> getJogsBetweenDates(int userId, String initialDate, String endDate) {
		return jogDAO.getJogsBetweenDates(userId, initialDate, endDate);
	}

	@Override
	public Jog getJogById(int userId, int jogId) {
		return jogDAO.getJogById(userId, jogId);
	}

	@Override
	public Jog updateJog(Jog jog) {
		return jogDAO.updateJog(jog);
	}

	@Override
	public boolean addJog(Jog jog) {
		return jogDAO.addJog(jog);
	}

	@Override
	public boolean userHasJog(int userId, int jogId) {
		return jogDAO.userHasJog(userId, jogId);
	}

	@Override
	public boolean jogExists(int jogId) {
		return jogDAO.jogExists(jogId);
	}

	@Override
	public void deleteJog(int jogId) {
		jogDAO.deleteJog(jogId);
	}
}
