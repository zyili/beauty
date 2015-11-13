package com.zyl.centre.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.common.utils.TokenUtils;
import com.zyl.centre.common.utils.CommonUtils;
import com.zyl.centre.entity.Token;
import com.zyl.centre.entity.User;
import com.zyl.centre.service.*;

public class LoginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String token;
	private Log log = LogFactory.getLog(LoginAction.class);
	@Resource(name = "userservice")
	private IUserService userservice;

	@Resource(name = "tokenService")
	private ITokenService tokenService;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	/*
	 * ��¼�ӿ�
	 */
	public void login() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		try {
			if (user == null || user.getUsername() == null
					|| user.getPassword() == null) {
				reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			} else {
				User getuser = userservice.findOneByPass(user.getUsername(),
						user.getPassword());
				if (getuser != null) {
					String tokencode = getTokenCode();
					Token token = new Token();
					token.setUserid(getuser.getUserid());
					token.setTokencode(tokencode);
					Calendar cal = Calendar.getInstance();// 获取当前时间
					Date currentDate = cal.getTime();
					cal.add(Calendar.DAY_OF_MONTH, +30);// 获取30天后时间
					token.setCreatedatetime(currentDate);
					token.setExpiredatetime(cal.getTime());
					tokenService.createTokenCode(token);
					CommonUtils.setSessionMap(getuser.getUserid(), tokencode);
					reMap.put("ResultMessage", CommonUtils.SUCCESS);
					reMap.put("token", tokencode);
					reMap.put("userid", getuser.getUserid());
				} else {
					reMap.put("ResultMessage", CommonUtils.ERROR);
				}
			}
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 未登录/登录异常处理
	 */
	public void errorLogin() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		reMap.put("ResultMessage", CommonUtils.PARAMERROR);
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}

	public void unlogin() throws IOException {
		int reFlage = CommonUtils.ERROR;
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (token == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		Map<String, Object> map = CommonUtils.getsetSessionMap();// 获取session的token信息
		String tokenCode = this.token;
		if (map != null) {

			if (tokenCode.equals(map.get("tokenCode").toString())) {
				/*
				 * 如果session中的token与传过来的token相同
				 * 则进行注销操作，删除session中的内容和删除用户在数据库中保存的token信息
				 */
				ActionContext.getContext().getSession().remove("sessionMap");
				Token token = new Token();
				token.setUserid(Integer.parseInt(map.get("userid").toString()));
				token.setTokencode(tokenCode);
				tokenService.delete(token);
				reFlage = CommonUtils.SUCCESS;
			}
		} else {
			try {
				Token gettoken = tokenService.findOneByCode(tokenCode);
				if (gettoken != null) {
					/*
					 * 如果数据库中的token与传过来的token相同
					 * 则进行注销操作，删除session中的内容和删除用户在数据库中保存的token信息
					 */
					ActionContext.getContext().getSession()
							.remove("sessionMap");
					tokenService.delete(gettoken);
					reFlage = CommonUtils.SUCCESS;
				} else {
					/*
					 * 如果数据库中的token与传过来的token不同 表示tokenCode错误
					 */
					reFlage = CommonUtils.ERRORTOKEN;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		reMap.put("ResultMessage", reFlage);
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}

	public String getTokenCode() {
		log.debug("getting token code");
		String tokencode = TokenUtils.generateValue();
		if (tokenService.findOneByCode(tokencode) != null) {
			getTokenCode();
		}
		log.info("get token success");
		return tokencode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
