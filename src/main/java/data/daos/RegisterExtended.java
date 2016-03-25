package data.daos;

import data.entities.Training;
import data.entities.User;

public interface RegisterExtended {
	
	public void deleteTrainingRegisterPlayer(Training training);
	
	public void deleteTrainingPlayer(User user, Training training);
	
}
