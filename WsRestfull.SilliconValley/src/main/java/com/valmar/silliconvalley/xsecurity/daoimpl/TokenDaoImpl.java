package com.valmar.silliconvalley.xsecurity.daoimpl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.silliconvalley.dao.AbstractDao;
import com.valmar.silliconvalley.util.Util;
import com.valmar.silliconvalley.xsecurity.dao.TokenDao;
import com.valmar.silliconvalley.xsecurity.model.Token;
import com.valmar.silliconvalley.xsecurity.model.Usuario;

@Repository("tokenDao")
@EnableTransactionManagement
public class TokenDaoImpl extends AbstractDao<Integer, Token> implements TokenDao {

	private static final int expireTimeInSeconds = 900;
	private static final String USERNAME = "u.correo";

	@Override
	public String generateToken(Usuario user) {
		Token token = new Token();
		/*
		 * This works by choosing 130 bits from a cryptographically secure
		 * random bit generator, and encoding them in base-32. 128 bits is
		 * considered to be cryptographically strong, but each digit in a base
		 * 32 number can encode 5 bits, so 128 is rounded up to the next
		 * multiple of 5. This encoding is compact and efficient, with 5 random
		 * bits per character. Compare this to a random UUID, which only has 3.4
		 * bits per character in standard layout, and only 122 random bits in
		 * total.
		 */
		SecureRandom random = new SecureRandom();
		String authToken = new BigInteger(130, random).toString(32);
		token.setAuthToken(authToken);
		/**
		 * Current Date
		 */
		token.setIssuedOn(new Timestamp(new Date().getTime()));
		/*
		 * Current and Add expire time to it
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, expireTimeInSeconds);
		token.setExpiresOn(new Timestamp(calendar.getTimeInMillis()));
		token.setUser(user);

		Query query = getSession().createSQLQuery("INSERT INTO TOKEN " + "(authToken, issuedOn, expiresOn, userId ) "
				+ "VALUES (:authToken, :issuedOn, :expiresOn, :userId )");
		query.setParameter("authToken", token.getAuthToken());
		query.setParameter("issuedOn", token.getIssuedOn());
		query.setParameter("expiresOn", token.getExpiresOn());
		query.setParameter("userId", token.getUser().getId());
		query.executeUpdate();
		return token.getAuthToken();
	}

	@Override
	public boolean validateToken(String tokenId) {
		try {
			Query query = getSession().createSQLQuery(
					"SELECT * FROM Token t " + "WHERE t.AuthToken = :authToken " + "AND t.ExpiresOn > :currentDate");
			query.setString("authToken", tokenId);
			query.setTimestamp("currentDate", new Timestamp(new Date().getTime()));
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.list();
			if (results != null) {
				Token token = new Token();
				for (Object[] row : results) {
					token.setId(Long.parseLong(row[0].toString()));
					token.setAuthToken(row[1].toString());
					token.setIssuedOn(Util.getDateFromString(row[2].toString()));
					token.setExpiresOn(Util.getDateFromString(row[3].toString()));
				}
				//Refresh the Expires date of token
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.SECOND, expireTimeInSeconds);
				token.setExpiresOn(new Timestamp(calendar.getTimeInMillis()));

				Query queryToUpdate = getSession().createSQLQuery(
						"UPDATE Token t " + "SET t.ExpiresOn = :expiresOn " + "WHERE t.authToken = :authToken");
				queryToUpdate.setParameter("expiresOn", token.getExpiresOn());
				queryToUpdate.setParameter("authToken", token.getAuthToken());
				queryToUpdate.executeUpdate();
			}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public String getUsernameFromToken(String token) {
		String userName = "";
		try {
			Query query = getSession().createSQLQuery("SELECT " + USERNAME + " FROM USUARIO u "
					+ "INNER JOIN Token t ON u.id = t.userId " + "WHERE t.authToken = :authToken");
			query.setString("authToken", token);
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.list();
			if(!results.isEmpty()){
				Object obj = results.get(0);
				userName = (String) obj;
			}
		} catch (Exception ex) {
			return null;
		}
		return userName;
	}

}
