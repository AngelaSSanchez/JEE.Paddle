package data.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Training;

@Repository
public class TrainingDaoImpl implements TrainingExtended{
	
	@Autowired
	private TrainingDao trainingDao;
	
	@Autowired
	private RegisterDao registerDao;
	
	@Override
	public void deleteTraining(int id) {
		Training training = trainingDao.findById(id);
		registerDao.deleteTrainingRegisterPlayer(training);
		trainingDao.delete(training);
		//reserveDao.deleteReserve()
	}

}
