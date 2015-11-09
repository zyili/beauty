package com.zyl.centre.service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Token;

public interface ITokenService extends IOperations<Token> {
	Token findOneByCode(String code);
	void createTokenCode(Token token);
}
