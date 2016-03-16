package data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Register {
	
	@Id
	@GeneratedValue
	private int id;
	
    @ManyToOne
    @JoinColumn
    private User user;
    
    @ManyToOne
    @JoinColumn
    private Training training;

    public Register(Training training, User user) {
        this.training = training;
        this.user = user;
    }
    
    public Register(Training training) {
        this(training, null);
    }

    public Register() {
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

}
