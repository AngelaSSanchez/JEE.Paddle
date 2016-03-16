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

	private Calendar startHour;

	@ManyToOne
    @JoinColumn
	private Court court;

	public Training(){
		
	}
	
	public Training(int id, Court court, Calendar startHour){
		this.id = id;
		this.court = court;
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
	
	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

    @Override
    public String toString() {
        return "Training [id=" + id + ", training Hour=" + startHour.getTime().toString() + "]";
    }
}
