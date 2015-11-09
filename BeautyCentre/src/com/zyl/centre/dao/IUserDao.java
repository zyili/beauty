package com.zyl.centre.dao;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.User;

public interface IUserDao extends IOperations<User> {

	boolean checkByName(final String username);

	User findOneByPass(final String username, final String pass);

	int GetUserIDByName(final String username, final String password);
	
	User findUserByUserid(int id);
}
