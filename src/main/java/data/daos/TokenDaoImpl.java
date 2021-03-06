package data.daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Token;

@Repository
public class TokenDaoImpl implements TokenExtended{
	
	@Autowired
	private TokenDao tokenDao;

	@Override
	public void deleteExpiredTokens() {
		List<Token> tokens = tokenDao.findByExpiringTimeLessThan(Calendar.getInstance());
		tokenDao.delete(tokens);
	}

}
