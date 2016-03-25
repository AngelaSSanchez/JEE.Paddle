package data.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Register;
import data.entities.Training;
import data.entities.User;

@Repository
public class RegisterDaoImpl implements RegisterExtended{
	
	@Autowired
	private RegisterDao registerDao;

	@Override
	public void deleteTrainingRegisterPlayer(Training training) {
		List<Register> register = registerDao.findByTraining(training);
		registerDao.delete(register);
	}
	
	@Override
	public void deleteTrainingPlayer(User user, Training training) {
		Register register = registerDao.findByUserAndTraining(user, training);
		registerDao.delete(register);
	}
	
}