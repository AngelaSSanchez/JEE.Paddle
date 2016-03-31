package data.daos;

import data.entities.Training;

public interface RegisterExtended {
	
	public void deleteTrainingRegisterPlayer(Training training);
	
	public void deleteTrainingPlayer(int userId, int trainingId);
	
}
