package business.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data.daos.CourtDao;
import data.daos.RegisterDao;
import data.daos.TrainingDao;
import data.entities.Training;

@Controller
public class TrainingController {

	private TrainingDao trainingDao;
	
	private CourtDao courtDao;
	
	//private RegisterDao registerDao;
	
	@Autowired
	public void setTrainingDao(TrainingDao trainingDao){
		this.trainingDao = trainingDao;
	}
	
	@Autowired
	public void setCourtDao(CourtDao courtDao){
		this.courtDao = courtDao;
	}
/*	
	@Autowired
	public void setRegisterDao(RegisterDao registerDao){
		this.registerDao = registerDao;
	}
*/	
	public boolean createTraining(int courtId, Calendar startDay, int numOfWeeks){
		return true;
	}
	
	public void deleteTraining(int trainingId){
		if(trainingDao.exists(trainingId)){
			trainingDao.deleteTraining(trainingId);
		}
	} 
	
}
