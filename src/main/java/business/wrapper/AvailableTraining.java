package business.wrapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import data.entities.Training;

public class AvailableTraining {
	
	private int trainingId;
	
	private int numOfWeek;
	
	private Calendar startHour;
	 
	private int courtId;
	
	public AvailableTraining(){}
	
	public AvailableTraining(int trainingId, Calendar startHour, int numOfWeek, int courtId){
		this.startHour = startHour;
		this.numOfWeek = numOfWeek;
		this.courtId = courtId;
		this.trainingId = trainingId;
	}

	public AvailableTraining(Training training){
		this(training.getId(), training.getStartHour(), training.getNumOfWeeks(), training.getCourt().getId());
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
    	String time = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(startHour.getTime());
        return "Available Trainings [training=" + this.trainingId + ", start time ="+time+", number of Weeks="+this.numOfWeek+", courtId="+this.courtId+"]";
    }
}
