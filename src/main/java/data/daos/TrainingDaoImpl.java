package data.daos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Reserve;
import data.entities.Training;

@Repository
public class TrainingDaoImpl implements TrainingExtended{
	
	@Autowired
	private TrainingDao trainingDao;
	
	@Autowired
	private RegisterDao registerDao;
	
	@Autowired
	private ReserveDao reserveDao;
	
	@Override
	public void deleteTraining(int id) {
		Training training = trainingDao.findById(id);
		for (int i = 0; i < training.getNumOfWeeks() ; i++){
			Reserve reserve = reserveDao.findByCourtAndDate(training.getCourt(), training.getStartHour());
			reserveDao.delete(reserve);
			training.getStartHour().add(Calendar.DAY_OF_YEAR,7);
		}
		registerDao.deleteTrainingRegisterPlayer(training);
		trainingDao.delete(training);
	}

}
