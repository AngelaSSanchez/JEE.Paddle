package business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data.daos.RegisterDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Register;
import data.entities.Training;

@Controller
public class RegisterController {
	private static final int MAXPLAYERS = 4;
	
	private UserDao userDao;
	
	private TrainingDao trainingDao;
	
	private RegisterDao registerDao;
	
	private List<Training> availableTrainings;
	
    @Autowired
    public void setRegisterDao(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    @Autowired
    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public List<Training> showAvailableTrainings(){
    	List<Training> allTrainings = trainingDao.findAll();
    	
    	for (Training training : allTrainings){
    		if (registerDao.findTrainingIsComplete(training.getId()) < MAXPLAYERS){
    			availableTrainings.add(training);
    		}
    	}
		return availableTrainings;
    	
    }
    
    public boolean registerTraining(int trainingId, String userName) {
    	Register register = new Register(trainingDao.findOne(trainingId));
    	if (registerDao.findTrainingIsComplete(trainingDao.findOne(trainingId).getId()) >= MAXPLAYERS ||
    	   !registerDao.exists(trainingId))
    	{
    		return false;
    	}
        register.setUser(userDao.findByUsernameOrEmail(userName));
        registerDao.save(register);
        return true;
    }
    
    public List<Training> getAvailableTrainings() {
        return availableTrainings;
    }

    public void setAvailableTrainings(List<Training> availableTrainings) {
        this.availableTrainings = availableTrainings;
    }
    
    @Override
    public String toString() {
        return "Available Trainings [training=" + availableTrainings + "]";
    }
}
