package com.zyl.centre.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.zyl.centre.entity.Imgsrc;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Prodtype;
import com.zyl.centre.entity.Product;
import com.zyl.centre.entity.Service;
import com.zyl.centre.entity.Shop;
import com.zyl.centre.service.IImgService;
import com.zyl.centre.service.IServiceService;

public class CommonUtils {

	public static final int JSONERROR = -900;// 传入json数据错误
	public static final int ERROR = -999;// 系统错误
	public static final int PARAMERROR = -901;// 参数不能为空
	public static final int SUCCESS = 0;
	public static final int TOKENOUT = -902;// Token 超过期限
	public static final int ERRORTOKEN = -903;// Token 字符串错误

	public static void toJson(HttpServletResponse response, Object data)
			throws IOException {
		Gson gson = new Gson();
		String result = gson.toJson(data);// 将Map转换为json格式的数据
		System.out.print("返回客户端的json:" + result);
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
	public static void setSessionMap(Integer integer, String tokenCode) {
		Map<String, Object> sessionMap = new HashMap<String, Object>();
		sessionMap.put("userid", integer);
		sessionMap.put("tokenCode", tokenCode);
		ServletActionContext.getRequest().getSession()
				.setAttribute("sessionMap", sessionMap);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getsetSessionMap() {
		return (Map<String, Object>) ServletActionContext.getRequest()
				.getSession().getAttribute("sessionMap");
	}

	public static JSONArray imgsToJson(List<Imgsrc> imgs) {
		JSONArray jsonStrs = new JSONArray();
		int n = imgs.size();
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				JSONObject json = new JSONObject();
				json.put("imgid", imgs.get(i).getImgid());
				json.put("imgname", imgs.get(i).getImgname());
				json.put("url", imgs.get(i).getUrl());
				json.put("imgdec", imgs.get(i).getImgdec());
				jsonStrs.add(json);
			}
		} else {
			jsonStrs.add("{}");
		}
		return jsonStrs;
	}

	public static JSONArray serviceToJson(List<Service> ser) {
		JSONArray jsonStrs = new JSONArray();
		int n = ser.size();
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				JSONObject json = new JSONObject();
				json.put("serviceid", ser.get(i).getServiceid());
				json.put("servicename", ser.get(i).getServicename());
				json.put("createtime",
						TimeString.dateToString(ser.get(i).getCreatetime()));
				json.put("price", ser.get(i).getPrice().toString());
				List<Imgsrc> imgs = new ArrayList<Imgsrc>();
				imgs.addAll(ser.get(i).getImgsrcs());
				json.put("servimgs", imgsToJson(imgs));
				jsonStrs.add(json);
			}
		} else {
			jsonStrs.add("{}");
		}
		return jsonStrs;
	}

	public static JSONArray shopsToJson(List<Shop> shops) {
		JSONArray jsonStrs = new JSONArray();
		int n = shops.size();
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				JSONObject json = new JSONObject();
				json.put("shopid", shops.get(i).getShopid());
				json.put("shopname", shops.get(i).getShopname());
				json.put("shoptypeid", shops.get(i).getShoptypeid());
				json.put("createuserid", shops.get(i).getUser().getUserid());
				json.put("state", shops.get(i).getState());
				json.put("city", shops.get(i).getArea().getDistrict()
						.getDistrictname());
				json.put("area", shops.get(i).getArea().getAreaname());
				json.put("shopdec", shops.get(i).getShopdec());
				List<Imgsrc> shopimgs = new ArrayList<Imgsrc>();
				shopimgs.addAll(shops.get(i).getImgsrcs());
				json.put("shopimages", imgsToJson(shopimgs));
				List<Service> shopservs = new ArrayList<Service>();
				Set<Service> servs = shops.get(i).getServices();
				if (servs != null) {
					shopservs.addAll(servs);
				}
				json.put("shopservs", serviceToJson(shopservs));
				jsonStrs.add(json);
			}
		} else {
			jsonStrs.add("{}");
		}
		return jsonStrs;
	}

	public static JSONArray ordsToJosn(List<Order> ords,
			IServiceService m_Service) {
		JSONArray jsonStrs = new JSONArray();
		int n = ords.size();
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				JSONObject json = new JSONObject();
				json.put("orderid", ords.get(i).getOrderid());			
				json.put("number", ords.get(i).getNumber());
				json.put("sumprice", ords.get(i).getSumprice());
				json.put("createtime",
						TimeString.dateToString(ords.get(i).getCreatetime()));
				json.put("modifytime",
						TimeString.dateToString(ords.get(i).getModifytime()));
				json.put("userid", ords.get(i).getUser().getUserid());
				json.put("username",ords.get(i).getSubmitusername());
				json.put("ordphone", ords.get(i).getOrdphone());
				json.put("state", ords.get(i).getState());
				List<Service> sers = m_Service.GetServiceByOrdid(ords.get(i)
						.getOrderid());
				json.put("services", CommonUtils.serviceToJson(sers));
				jsonStrs.add(json);
			}
		} else {
			jsonStrs.add("{}");
		}
		return jsonStrs;
	}


	public static JSONObject getJson() {
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = ServletActionContext.getRequest()
					.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(json.length()>0) {
			JSONObject jsonObj = JSONObject.fromObject(json.toString());
			return jsonObj;
		}
		return null;
	}

	public static JSONArray prodtypesToJosn(List<Prodtype> types) {
		JSONArray jsonStrs = new JSONArray();
		int n = types.size();
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				JSONObject json = new JSONObject();
				json.put("prodtypeid", types.get(i).getProdtypeid());
				json.put("prodtypename", types.get(i).getProdtypename());
				jsonStrs.add(json);
			}
		} else {
			jsonStrs.add("{}");
		}
		return jsonStrs;
	}

	public static JSONArray prodsToJosn(List<Product> prods) {
		JSONArray jsonStrs = new JSONArray();
		int n = prods.size();
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				JSONObject json = new JSONObject();
				json.put("productid", prods.get(i).getProductid());
				json.put("productname", prods.get(i).getProductname());
				jsonStrs.add(json);
			}
		} else {
			jsonStrs.add("{}");
		}
		return jsonStrs;
	}
}