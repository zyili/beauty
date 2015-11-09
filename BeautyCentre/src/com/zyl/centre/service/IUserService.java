package com.zyl.centre.service;

import java.util.Map;

import net.sf.json.JSONArray;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.User;

public interface IUserService extends IOperations<User> {

	boolean checkByName(final String username);
	User findOneByPass(final String username, final String pass);
	int GetUserIDByName(String username, String password);
	void createUser(User user,String tokenCode);
	User findUserByUserid(int id);
	Order bookService(int uerid,int serviceid,int number);
}
