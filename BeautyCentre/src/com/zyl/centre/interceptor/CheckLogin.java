package com.zyl.centre.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zyl.centre.action.LoginAction;

public class CheckLogin implements Interceptor {
	public static final String LOGIN_PAGE = "global.login";

	@Override
	public void destroy() {
		System.out.println("------CheckLogin.destroy------");
	}

	@Override
	public void init() {
		System.out.println("------CheckLogin.init------");

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {

		System.out.println("------CheckLogin.intercept------");

		Map session = ActionContext.getContext().getSession();
		Object action = arg0.getAction();
		if (action instanceof LoginAction) {
			System.out
					.println("exit check login, because this is login action.");
			return arg0.invoke();
		}

		if (session.get("user.name") != null) {
			System.out.println("already login!");
			return arg0.invoke();
		} else {
			// 否则终止后续操作，返回LOGIN
			System.out.println("no login, forward login page!");
			return LOGIN_PAGE;
		}
	}

}
