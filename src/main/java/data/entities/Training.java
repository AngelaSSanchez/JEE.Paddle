package data.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Training {
	
	@Id
	@GeneratedValue
	private int id;

	private Calendar startHour;

	private int numOfWeeks;
	
	@ManyToOne
	@JoinColumn
	private Court court;

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Training(){
		
	}
	
	public Training(Court court, int numOfWeeks, Calendar startHour){
		this.court = court;
		this.numOfWeeks = numOfWeeks;
		this.startHour = startHour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
    public Calendar getStartHour() {
		return startHour;
	}

	public void setStartHour(Calendar startHour) {
		this.startHour = startHour;
	}

    public int getNumOfWeeks() {
		return numOfWeeks;
	}

	public void setNumOfWeeks(int numOfWeeks) {
		this.numOfWeeks = numOfWeeks;
	}

	@Override
    public String toString() {
        return "Training [id=" + id + ", training Start Day and hour=" + startHour.getTime().toString() + "]";
    }
}
