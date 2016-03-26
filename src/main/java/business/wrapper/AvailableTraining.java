package business.wrapper;

import java.util.Calendar;

import data.entities.Training;

public class AvailableTraining {
	
	private int trainingId;
	
	private int numOfWeek;
	
	private Calendar startHour;
	
	public AvailableTraining(){}
	
	public AvailableTraining(int trainingId, Calendar startHour, int numOfWeek){
		this.trainingId = trainingId;
		this.startHour = startHour;
		this.numOfWeek = numOfWeek;
	}
	
	public AvailableTraining(Training training){
		this(training.getId(), training.getStartHour(), training.getNumOfWeeks());
	}
	
	public int getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}

	public int getNumOfWeek() {
		return numOfWeek;
	}

	public void setNumOfWeek(int numOfWeek) {
		this.numOfWeek = numOfWeek;
	}

	public Calendar getStartHour() {
		return startHour;
	}

	public void setStartHour(Calendar startHour) {
		this.startHour = startHour;
	}
	
    @Override
    public String toString() {
        return "Available Trainings [training=" + this.trainingId + ", start time ="+this.startHour+", number of Weeks="+this.numOfWeek+"]";
    }
}
