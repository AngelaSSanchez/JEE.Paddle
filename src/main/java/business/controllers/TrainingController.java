package business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.entities.Court;
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
	
	public boolean createTraining(int id, Court court){
		if(trainingDao.exists(id)){
			return false;
		}
		//courtDao
		//trainingDao.save(new Training(id, court));
		return true;
	}
}
