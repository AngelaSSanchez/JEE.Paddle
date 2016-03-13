package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TokenDaoITest {

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private DaosService daosService;

    @Test
    public void testFindByUser() {
        Token token = (Token) daosService.getMap().get("tu1");
        User user = (User) daosService.getMap().get("u4");
        assertEquals(token, tokenDao.findByUser(token.getUser()));
        assertNull(tokenDao.findByUser(user));
    }
        
    @Test
    public void testFindByExpiringTimeLessThan(){
    	Token token = (Token) daosService.getMap().get("tu2");
        Calendar time = token.getExpiringTime();
        time.add(Calendar.HOUR_OF_DAY, -2);
        token.setExpiringTime(time);
        tokenDao.save(token);
        
        List<Token> tokens = tokenDao.findByExpiringTimeLessThan(Calendar.getInstance());

        assertEquals(token, tokens.get(0));
    }
    
    @Test
    public void testDeleteExpiredTokens(){
    	Token token = (Token) daosService.getMap().get("tu1");
        Calendar time = token.getExpiringTime();
        time.add(Calendar.HOUR_OF_DAY, -2);
        token.setExpiringTime(time);
        tokenDao.save(token);
        
        List<Token> tokens = tokenDao.findAll();
       
    	tokenDao.deleteExpiredTokens();
    	
        List<Token> activetokens = tokenDao.findAll();

        assertEquals(activetokens.size(),tokens.size()-1);
    }

}
