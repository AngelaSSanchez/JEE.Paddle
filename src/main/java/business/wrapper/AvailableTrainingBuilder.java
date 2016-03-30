package business.wrapper;

import java.util.Calendar;

public class AvailableTrainingBuilder {
	
	private static final int DEFAULT = 1;

	private AvailableTraining availableTraining;
	
	public AvailableTrainingBuilder(int numOfWeeks, int courtId){
		availableTraining = new AvailableTraining(Calendar.getInstance(), numOfWeeks, courtId);
	}
	
	public AvailableTrainingBuilder(){
		this(DEFAULT, DEFAULT);
	}
	
	public AvailableTrainingBuilder startHour(Calendar day){
		availableTraining.setStartHour(day);
		return this;
	}
	
	public AvailableTrainingBuilder numOfWeeks(int numOfWeek){
		availableTraining.setNumOfWeek(numOfWeek);
		return this;
	}
	
	public AvailableTrainingBuilder courtId(int courtId){
		availableTraining.setCourtId(courtId);
		return this;
	}

    public AvailableTraining build() {
        return availableTraining;
    }
}
