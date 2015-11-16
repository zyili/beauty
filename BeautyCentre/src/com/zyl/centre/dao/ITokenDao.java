package com.zyl.centre.dao;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Token;

public interface ITokenDao extends IOperations<Token> {
	Token findOneByCode(String code);

	void createTokenCode(Token token);

	Token getTokenByTokenCode(String code);
}