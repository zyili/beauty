package com.zyl.centre.stage.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.entity.User;
import com.zyl.centre.service.IUserService;

public class statgeUser extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "userservice")
	private IUserService userservice;
	private int userid;
	public User userMessage;
	
	public User getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(User userMessage) {
		this.userMessage = userMessage;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String findUserMessage() {
		System.out.println(userid);
		User user = userservice.findUserByUserid(userid);
		setUserMessage(user);
		//ServletActionContext.getRequest().getSession()
				//.setAttribute("userMessage", user);
		return SUCCESS;
	}

	public String modifyUserPass() {
		User user = userservice.findUserByUserid(userid);
		user.setPassword("888888");
		userservice.update(user);
		return SUCCESS;
	}



}
