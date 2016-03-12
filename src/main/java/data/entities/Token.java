package data.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Token {

	private static long ONEHOUR = 1000*60*60;
	
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String value;
    
    @Column(nullable = false)
    private Calendar expiringTime;

    @ManyToOne
    @JoinColumn
    private User user;

    public Token() {
    }

    public Token(User user) {
        assert user != null;
        this.user = user;
        this.value = new Encrypt().encryptInBase64UrlSafe("" + user.getId() + user.getUsername() + Long.toString(new Date().getTime())
                + user.getPassword());
        this.expiringTime = Calendar.getInstance();
        this.expiringTime.add(Calendar.HOUR_OF_DAY, 1);
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }

    public Calendar getExpiringTime() {
		return expiringTime;
	}

	public void setExpiringTime(Calendar expiringTime) {
		this.expiringTime = expiringTime;
	}
	
	public boolean hasExpired(){
		return (Calendar.getInstance().getTimeInMillis() - this.expiringTime.getTimeInMillis() > ONEHOUR);
	}

	@Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Token) obj).id;
    }

    @Override
    public String toString() {
        return "Token [id=" + id + ", value=" + value + ", userId=" + user.getId() + ", expiringTime=" + expiringTime.getTime().toString() + "]";
    }
}
