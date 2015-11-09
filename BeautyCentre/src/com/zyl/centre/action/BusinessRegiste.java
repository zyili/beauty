package com.zyl.centre.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.common.utils.CommonUtils;
import com.zyl.centre.entity.Imgsrc;
import com.zyl.centre.entity.User;
import com.zyl.centre.service.*;

public class BusinessRegiste extends ActionSupport{
	private User user;
	@Resource(name = "userservice")
	private IUserService userservice;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String execute() throws Exception {
			return SUCCESS;
		}
	
	public void businessRegiste() throws Exception {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if(user==null||user.getUsername() == null || user.getPassword() == null 
				|| user.getRealname() == null || user.getPhone() == null ||
				user.getEmail() == null){
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		if(user.equals(null))
			System.out.println("user is null");
		else
			System.out.println("user is not null");
		userservice.create(user);
		int u_id = userservice.GetUserIDByName(user.getUsername(),user.getPassword());
		if(u_id >= 0) {
			reMap.put("ResulMessage", CommonUtils.SUCCESS);
			reMap.put("userid", u_id);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		}
		return;		
	}
	

}
