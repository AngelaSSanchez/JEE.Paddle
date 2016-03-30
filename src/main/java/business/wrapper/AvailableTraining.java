package business.wrapper;

import java.util.Calendar;

import data.entities.Training;

public class AvailableTraining {
	
	private int trainingId;
	
	private int numOfWeek;
	
	private Calendar startHour;
	 
	private int courtId;
	
	public AvailableTraining(){}
	
	public AvailableTraining(Calendar startHour, int numOfWeek, int courtId){
		this.startHour = startHour;
		this.numOfWeek = numOfWeek;
		this.courtId = courtId;
	}

	public AvailableTraining(Training training){
		this(training.getStartHour(), training.getNumOfWeeks(), training.getCourt().getId());
	}
	
	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
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
