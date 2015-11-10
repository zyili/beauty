package com.zyl.centre.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.common.utils.CommonUtils;
import com.zyl.centre.common.utils.TokenUtils;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Service;
import com.zyl.centre.entity.Serviceordrel;
import com.zyl.centre.entity.ServiceordrelId;
import com.zyl.centre.entity.User;
import com.zyl.centre.service.IImgService;
import com.zyl.centre.service.IOrderService;
import com.zyl.centre.service.IServiceService;
import com.zyl.centre.service.IServiceordrelService;
import com.zyl.centre.service.ITokenService;
import com.zyl.centre.service.IUserService;

public class UserAction extends ActionSupport {

	private String token;
	private User user;
	private Service service;
	private int number;
	@Resource(name = "userservice")
	private IUserService userservice;

	@Resource(name = "tokenService")
	private ITokenService tokenService;

	@Resource(name = "orderService")
	private IOrderService orderservice;

	@Resource(name = "serviceService")
	private IServiceService m_Service;

	@Resource(name = "serviceordrelService")
	private IServiceordrelService rel_Service;

	private Log log = LogFactory.getLog(LoginAction.class);

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void getOrdersInfoByUserid() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (token == null) {
			reMap.put("ResultMessage", CommonUtils.ERRORTOKEN);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		Map<String, Object> tokenmap = TokenUtils.manageToken(token);

		if (tokenmap.get("message").toString().equals("SUCCESS")) {
			List<Order> ords = orderservice.getOrdsByUserid(Integer
					.parseInt(tokenmap.get("userid").toString()));
			if (ords.size() > 0) {
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "yes");
				JSONArray ordsJson = CommonUtils.ordsToJosn(ords, m_Service);
				reMap.put("orders", ordsJson);

			} else {
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "no");
			}

		} else if (tokenmap.get("message").toString().equals("TOKENOUT")) {
			reMap.put("ResultMessage", CommonUtils.TOKENOUT);
		} else {
			reMap.put("ResultMessage", CommonUtils.ERRORTOKEN);
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}

	public void userRegiste() throws IOException {
		JSONObject reJson = new JSONObject();
		if (user == null || user.getUsername() == null
				|| user.getPassword() == null || user.getType() == null) {
			reJson.put("ResultMessage", CommonUtils.PARAMERROR);
		} else {
			if (user.getRealname() != null) {
				String name = URLDecoder.decode(user.getRealname(), "UTF-8");
				user.setRealname(name);
			}
			String tokenCode = getTokenCode();
			userservice.createUser(user, tokenCode);
			int u_id = userservice.GetUserIDByName(user.getUsername(),
					user.getPassword());
			if (u_id > 0) {
				reJson.put("ResultMessage", CommonUtils.SUCCESS);
				reJson.put("userid", u_id);
				reJson.put("username", user.getUsername());
				reJson.put("token", tokenCode);
			} else {
				reJson.put("ResultMessage", CommonUtils.ERROR);
			}
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), reJson);
	}

	public void userUpdate() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (token == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		Map<String, Object> tokenmap = TokenUtils.manageToken(token);
		if (tokenmap.get("message").equals("SUCCESS")) {
			if (user.getRealname() != null) {
				String name = URLDecoder.decode(user.getRealname(), "UTF-8");
				user.setRealname(name);
			}
			if (user.getUserid() != null) {
				userservice.update(user);
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
			} else {
				reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			}
		} else if (tokenmap.get("message").toString().equals("TOKENOUT")) {
			reMap.put("ResultMessage", CommonUtils.TOKENOUT);
		} else {
			reMap.put("ResultMessage", CommonUtils.ERRORTOKEN);
		}
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

	public void bookService() throws IOException {
		JSONObject rejson = new JSONObject();
		if (token == null) {
			rejson.put("ResultMessage", CommonUtils.ERRORTOKEN);
			CommonUtils.toJson(ServletActionContext.getResponse(), rejson);
			return;
		}
		Map<String, Object> tokenmap = TokenUtils.manageToken(token);
		if (tokenmap.get("message").toString().equals("SUCCESS")) {
			if (number < 0 || service == null || service.getServiceid() == null) {
				rejson.put("ResultMessage", CommonUtils.PARAMERROR);
				CommonUtils.toJson(ServletActionContext.getResponse(), rejson);
				return;
			}
			Order ord = userservice.bookService(
					Integer.valueOf(tokenmap.get("userid").toString()),
					service.getServiceid(), number);
			if(ord!=null)
			{
				rejson.put("ResultMessage", CommonUtils.SUCCESS);
				rejson.put("orderid",ord.getOrderid());
			}else
			{
				rejson.put("ResultMessage", CommonUtils.ERROR);
			}

		} else if (tokenmap.get("message").toString().equals("TOKENOUT")) {
			rejson.put("ResultMessage", CommonUtils.TOKENOUT);
		} else {
			rejson.put("ResultMessage", CommonUtils.ERRORTOKEN);
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), rejson);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
