package data.daos;

import java.util.Calendar;
import java.util.List;

import data.entities.Token;

public class TokenDaoImpl implements TokenExtended{
	
	private TokenDao tokenDao;

	@Override
	public void deleteExpiredTokens() {
		List<Token> tokens = tokenDao.findByExpiringTimeLessThan(Calendar.getInstance());
		tokenDao.delete(tokens);
	}

}
