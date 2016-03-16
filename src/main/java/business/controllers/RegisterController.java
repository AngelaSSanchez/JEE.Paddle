package business.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data.daos.CourtDao;
import data.daos.RegisterDao;
import data.daos.ReserveDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Register;
import data.entities.Reserve;

@Controller
public class RegisterController {
	private static final int MAXPLAYERS = 4;
	
	private UserDao userDao;
	
	private TrainingDao trainingDao;
	
	private RegisterDao registerDao;
	
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
}
