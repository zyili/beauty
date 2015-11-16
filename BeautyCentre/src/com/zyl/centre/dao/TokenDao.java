package com.zyl.centre.dao;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.google.common.base.Preconditions;
import com.zyl.centre.entity.Token;
import com.zyl.centre.entity.User;

@Repository("tokenDao")
public class TokenDao extends HibernateDao<Token> implements ITokenDao {

	public TokenDao() {
		super();
		setClazz(Token.class);
		setLog(LogFactory.getLog(Token.class));
	}

	@Override
	public Token findOneByCode(String code) {
		// TODO Auto-generated method stub
		System.out.println("进入tokendao----------");
		log.debug("getting Token instance with code:" + code);

		try {
			System.out.print("getting Token instance with code:" + code);
			String sql = "from Token where tokencode='" + code + "'";
			@SuppressWarnings("unchecked")
			List<Token> token = getCurrentSession().createQuery(sql).list();
			System.out
					.print("getting Token instance with code:" + token.size());
			log.debug("get successful");
			if (!token.isEmpty()) {
				return token.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public void createTokenCode(Token token) {
		// TODO Auto-generated method stub
		log.debug("creating  tokencode  instance");
		// tx = null;
		try {
			// tx = getCurrentSession().beginTransaction();
			Preconditions.checkNotNull(token);
			getCurrentSession().saveOrUpdate(token);
			getCurrentSession().flush();
			// tx.commit();
			log.debug("create successful");
		} catch (RuntimeException re) {
			log.error("create  failed", re);
			throw re;
		}
	}

	@Override
	public Token getTokenByTokenCode(String code) {
		// TODO Auto-generated method stub
		System.out.println("进入tokendao----------");
		log.debug("getting Token instance with code:" + code);

		try {
			System.out.print("getting Token instance with code:" + code);
			String sql = "from Token where tokencode='" + code + "'";
			@SuppressWarnings("unchecked")
			List<Token> token = getCurrentSession().createQuery(sql).list();
			System.out
					.print("getting Token instance with code:" + token.size());
			log.debug("get successful");
			if (!token.isEmpty()) {
				return token.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
		}
		return null;
	}
}
