package com.zyl.centre.action;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.common.utils.CommonUtils;
import com.zyl.centre.common.utils.TimeString;
import com.zyl.centre.common.utils.TokenUtils;
import com.zyl.centre.entity.Imgsrc;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Service;
import com.zyl.centre.entity.Shop;
import com.zyl.centre.service.IImgService;
import com.zyl.centre.service.IOrderService;
import com.zyl.centre.service.IServiceService;
import com.zyl.centre.service.IShopService;
import com.zyl.centre.service.ITokenService;

public class OrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;
	private Shop shop;
	private int number;
	private int realprice;
	private int optype;
	private Order order;
	@Resource(name = "orderService")
	private IOrderService orderservice;

	@Resource(name = "shopService")
	private IShopService shopService;

	@Resource(name = "serviceService")
	private IServiceService m_Service;
	
	@Resource(name = "tokenService")
	private ITokenService tokenService;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public int getOptype() {
		return optype;
	}

	public void setOptype(int optype) {
		this.optype = optype;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getRealprice() {
		return realprice;
	}

	public void setRealprice(int realprice) {
		this.realprice = realprice;
	}
	
	public void getOrdersInfo() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (shop== null || token == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		Map<String, Object> maptoken = TokenUtils.manageToken(token,tokenService);
		if (maptoken.get("message").equals("SUCCESS")) {
			int shopid = shop.getShopid();
			List<Order> orders = shopService.getOrdersByShopid(shopid);
			if (orders != null && !orders.isEmpty()) {
				int sum =cmSum(orders.size());
				List<Order> reOrders = formatOrders(number, orders);
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "yes");
				reMap.put("sumPage", sum);
				reMap.put("orders", CommonUtils.ordsToJosn(reOrders, m_Service));
			} else {
				reMap.put("ResultMassage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "no");
			}
		} else {
			if (maptoken.get("message").equals("TOKENOUT")) {
				reMap.put("ResultMessage", CommonUtils.TOKENOUT);
			} else {
				reMap.put("ResultMessage", CommonUtils.ERROR);
			}
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}

	public List<Order> formatOrders(int number, List<Order> ords) {
		List<Order> hs = new ArrayList();
		int size = ords.size();
		int sum = cmSum(size);
		if (number == 0) {
			hs = ords;
		} else {
			if (sum > 1) {
				Order[] mser = (Order[]) ords.toArray();
				for (int i = 10 * (number - 1) + 1; i <= 10 * number; i++) {
					hs.add(mser[i]);
				}
			} else {
				hs = ords;
			}
		}
		return hs;
	}

	public int cmSum(int size) {
		int sum = 0;
		if (size > 10) {
			int m = size % 10;
			if (m == 0) {
				sum = size / 10;
			} else {
				sum = (size / 10) + 1;
			}
		} else {
			sum = 1;
		}
		return sum;
	}

	public void editOrder() throws IOException {
		System.out.println("编辑订单");
		Map<String, Object> reMap = new HashMap<String, Object>();
		if(token==null)
		{
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		Map<String, Object> maptoken = TokenUtils.manageToken(token,tokenService);
		if (maptoken.get("message").equals("SUCCESS")) {
			if (order.getOrderid() == null) {//id为空
				reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			} else {
				int id = order.getOrderid();
				Order getOrd = orderservice.getOrdByid(id);
				if (getOrd != null) {//getord 不为空
					if (optype == 1) // 订单完成
					{
						if(realprice!=0)
						{
							getOrd.setRealprice(realprice);
						}
						getOrd.setState(1);
						orderservice.update(getOrd);
						reMap.put("ResultMessage", CommonUtils.SUCCESS);
					} else if (optype == 0) // 订单关闭
					{
						getOrd.setState(0);
						orderservice.update(getOrd);
						reMap.put("ResultMessage", CommonUtils.SUCCESS);
					} else {
						reMap.put("ResultMessage", CommonUtils.PARAMERROR);
					}
				} else {//getord 为空
					reMap.put("ResultMessage", CommonUtils.PARAMERROR);
				}
			}
		} else {
			if (maptoken.get("message").equals("TOKENOUT")) {
				reMap.put("ResultMessage", CommonUtils.TOKENOUT);
			} else {
				reMap.put("ResultMessage", CommonUtils.ERROR);
			}
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}
}
