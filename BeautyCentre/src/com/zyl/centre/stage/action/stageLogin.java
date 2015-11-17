package com.zyl.centre.stage.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.entity.Menu;
import com.zyl.centre.entity.User;
import com.zyl.centre.service.IMenuService;
import com.zyl.centre.service.IUserService;

public class stageLogin extends ActionSupport {

	@Resource(name = "userservice")
	private IUserService userservice;

	@Resource(name = "menuService")
	private IMenuService menuservice;
	private User user;
	
	private int pageSize = 10;// 每页显示几条

	private int page = 1; // 默认当前页

	private int totalPage;// 总共多少页

	private int userNum;// 总过多少条

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(user.getUsername() + "   " + user.getPassword());
		User getuser = userservice.findOneByPass(user.getUsername(),
				user.getPassword());
		if (getuser != null) {
			if (getuser.getType() == 100) {
				List<Menu> menus = menuservice.findAll();
				System.out.println("menus size"+menus.size());
				ServletActionContext.getRequest().getSession()
						.setAttribute("user", getuser);
				//String nodes = GetMenus(menus);
				System.out.println(menusToJson(menus));
				// System.out.println(nodes);
				ServletActionContext.getRequest().getSession()
						.setAttribute("menus", menusToJson(menus));
				return SUCCESS;
			}
		} else {
			return ERROR;
		}
		return ERROR;
	}
	
	public String listUser(){
		System.out.println("总页数：" + totalPage + "当前页：" + page);
		userNum = userservice.findAll().size();// 总过多少条
		if (userNum % pageSize == 0) {
			totalPage = userNum / pageSize;// 总共多少页
		} else {
			totalPage = userNum / pageSize + 1;// 总共多少页
		}
		System.out.println("总页数：" + totalPage + "当前页：" + page);
		List<User> users = userservice.findUserByPage(page, pageSize);
		ServletActionContext.getRequest().getSession()
				.setAttribute("allUsers", users);
		ServletActionContext.getRequest().getSession()
				.setAttribute("totalPage", totalPage);
		ServletActionContext.getRequest().getSession()
				.setAttribute("page", page);
		return SUCCESS;
	}

	public JSONArray menusToJson(List<Menu> menus) {
		JSONArray jsonStrs = new JSONArray();
		int n = menus.size();
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				JSONObject json = new JSONObject();
				json.put("id", menus.get(i).getMenuId());
				if (menus.get(i).getLevels()==1) {
					json.put("pId","0");
					json.put("font", "{'font-weight':'bold'}");
					json.put("isParent", "true");
					json.put("open","true");
					json.put("iconOpen", "css/diy/open.png");
					json.put("iconClose", "css/diy/close.png");
				} else {
					json.put("pId", menus.get(i).getParentId());
					json.put("isParent", false);
					json.put("toUrl", menus.get(i).getUrl());
					json.put("icon", "css/diy/3.png");
				}
				json.put("name", menus.get(i).getMenuName());
				jsonStrs.add(json);
			}
		}
		return jsonStrs;
	}
}
