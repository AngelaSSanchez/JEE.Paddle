package data.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import data.entities.Token;
import data.entities.User;

public class TokenTest {
	
	private User user;

	private Token token;

	private Calendar time;

	@Before
	public void testCreateTokenUser(){
		this.user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
		this.token = new Token(user);
		this.time = Calendar.getInstance();
	}

	@Test
	public void testTokenUser() {
	    assertTrue(this.token.getValue().length() > 20);
	}

	@Test
	public void testTokenHasExpired() {
		this.time.add(Calendar.HOUR_OF_DAY, -2);
		this.token.setExpiringTime(this.time);
		System.out.println("TOKEN " + this.token.toString() + " HORA ACTUAL: " + Calendar.getInstance().getTime().toString());
		assertTrue(token.hasExpired());
	}

	@Test
	public void testTokenHasNotExpired() {
		this.time.add(Calendar.HOUR_OF_DAY, 3);
		this.token.setExpiringTime(this.time);
		System.out.println("TOKEN " + this.token.toString() + " HORA ACTUAL: " + Calendar.getInstance().getTime().toString());
		assertFalse(token.hasExpired());
	}

}
