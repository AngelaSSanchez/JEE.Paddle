package data.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Training {
	
	@Id
	private int id;

	private boolean complete;
	
	private Calendar startHour;

	@ManyToOne
    @JoinColumn
	private Court court;
	
	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Training(){}
	
	public Training(int id, Court court){
		this.id = id;
		this.complete = false;
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
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

    @Override
    public String toString() {
        return "Training [id=" + id + ", complete=" + complete + "]";
    }
}
