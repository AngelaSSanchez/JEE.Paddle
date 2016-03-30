package business.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.entities.Training;

@Controller
public class TrainingController {

	private TrainingDao trainingDao;
	
	private CourtDao courtDao;
	
	@Autowired
	public void setTrainingDao(TrainingDao trainingDao){
		this.trainingDao = trainingDao;
	}
	
	@Autowired
	public void setCourtDao(CourtDao courtDao){
		this.courtDao = courtDao;
	}
	
	public boolean createTraining(int courtId, Calendar startDay, int numOfWeeks){
		trainingDao.save(new Training(courtDao.findOne(courtId), numOfWeeks, startDay));
        return true;
	}
	
	public boolean deleteTraining(int trainingId){
		if(trainingDao.exists(trainingId)){
			trainingDao.deleteTraining(trainingId);
			return true;
		}
		return false;
	} 
	
    public boolean exist(int trainingId) {
        return trainingDao.findOne(trainingId) != null;
    }
	
}
