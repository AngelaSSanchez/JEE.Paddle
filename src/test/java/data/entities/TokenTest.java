package data.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import data.entities.Token;
import data.entities.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        //System.out.println(token.toString());
        assertTrue(token.getValue().length() > 20);
    }
    
    @Test
    public void testTokenHasExpired() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        Calendar time = Calendar.getInstance();
        time.add(Calendar.HOUR_OF_DAY, -2);
        token.setExpiringTime(time);
        System.out.println("TOKEN "+token.toString()+" HORA ACTUAL: "+Calendar.getInstance().getTime().toString());
        assertTrue(token.hasExpired());
    }
    
    @Test
    public void testTokenHasNotExpired() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        Calendar time = Calendar.getInstance();
        time.add(Calendar.HOUR_OF_DAY, 3);
        token.setExpiringTime(time);
        System.out.println("TOKEN "+token.toString()+" HORA ACTUAL: "+Calendar.getInstance().getTime().toString());
        assertFalse(token.hasExpired());
    }

}
