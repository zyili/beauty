package com.zyl.centre.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.ITokenDao;
import com.zyl.centre.dao.TokenDao;
import com.zyl.centre.entity.Token;

@Service("tokenService")
public class TokenService extends AbstractService<Token> implements ITokenService {

	@Resource(name = "tokenDao")
	private ITokenDao dao;

	public TokenService() {
		super();
	}

	@Override
	protected IOperations<Token> getDao() {
		return this.dao;
	}

	@Override
	public void createTokenCode(Token token) {
		// TODO Auto-generated method stub
		dao.createTokenCode(token);
	}

	@Override
	public Token getTokenByTokenCode(String code) {
		return dao.getTokenByTokenCode(code);
	}

	@Override
	public Token findOneByCode(String code) {
		// TODO Auto-generated method stub
		return dao.findOneByCode(code);
	}
}