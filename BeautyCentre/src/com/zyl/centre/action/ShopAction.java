package com.zyl.centre.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.common.utils.CommonUtils;
import com.zyl.centre.common.utils.TimeString;
import com.zyl.centre.common.utils.TokenUtils;
import com.zyl.centre.entity.*;
import com.zyl.centre.service.*;

public class ShopAction extends ActionSupport {

	private Shop shop;
	private Imgsrc shop_img;
	private Area area;
	private String areaname;
	private String cityname;
	private String token;
	private String userid;
	private String serviceid;
	private List<File> files;
	private List<String> filesFileName;
	private List<String> filesContentType;
	private Log log = LogFactory.getLog(LoginAction.class);
	@Resource(name = "shopService")
	public IShopService shopService;
	
	@Resource(name = "userservice")
	private IUserService userservice;

	@Resource(name = "imgService")
	public IImgService imgService;

	@Resource(name = "areaService")
	public IAreaService areaService;

	@Resource(name = "serviceService")
	private IServiceService m_Service;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<String> getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}

	public List<String> getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Imgsrc getShop_img() {
		return shop_img;
	}

	public void setShop_img(Imgsrc shop_img) {
		this.shop_img = shop_img;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public void updateShopInfo() throws Exception {
		TimeString time = new TimeString();
		Map<String, Object> reMap = new HashMap<String, Object>();

		String root = ServletActionContext.getRequest().getRealPath(
				"shop_upload");
		Map<String, Object> token_reMap = new HashMap<String, Object>();
		if (token == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		token_reMap = TokenUtils.manageToken(token);
		if (!token_reMap.get("message").equals("SUCCESS")) {
			CommonUtils.toJson(ServletActionContext.getResponse(), token_reMap);
			return;
		}

		if (null == ServletActionContext.getRequest().getParameter("files")
				|| null == ServletActionContext.getRequest().getParameter(
						"area")
				|| null == ServletActionContext.getRequest().getParameter(
						"city")) {
			reMap.put("ResultMessage", CommonUtils.JSONERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}

		System.out.println(root + '\n');

		if (shop.getShopid() == null || shop.getShopname() == null
				|| shop.getExt2() == null || shop.getShopdec() == null) {
			reMap.put("ResultMessage", CommonUtils.JSONERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		Area area_temp = areaService.GetByName(areaname, cityname);
		log.error("updateShopInfo---area_temp" + area_temp.getAreaname());
		log.debug("updateShopInfo---area_temp" + area_temp.getAreaname());
		System.out.println("updateShopInfo---area_temp"
				+ area_temp.getAreaname());
		shop.setArea(area_temp);
		shopService.create(shop);
		for (int i = 0; i < files.size(); i++) {
			InputStream is = new FileInputStream(files.get(i));
			String filename = this.getFilesFileName().get(i)
					+ time.getTimeString();
			File destFile = new File(root, filename);
			OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[400];
			int length = 0;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			is.close();
			os.close();
			String url = "shop_upload//" + filename;
			Imgsrc img_temp = new Imgsrc();
			img_temp.setUrl(url);
			img_temp.setShop(shop);
			img_temp.setImgname(filename);
			imgService.create(img_temp);
		}
		reMap.put("ResultMessage", CommonUtils.SUCCESS);
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		return;
	}

	public void getShopInfo() throws Exception {
		Map<String, Object> token_reMap = new HashMap<String, Object>();
		Map<String, Object> reMap = new HashMap<String, Object>();
		Map<String, Object> imgMap = new HashMap<String, Object>();
		if (token == null || userid == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		token_reMap = TokenUtils.manageToken(token);
		if (!token_reMap.get("message").equals("SUCCESS")) {
			CommonUtils.toJson(ServletActionContext.getResponse(), token_reMap);
			return;
		}
		Shop shop_temp = shopService.getByUid(userid);
		reMap.put("ResultMessage", CommonUtils.SUCCESS);
		reMap.put("shopid", shop_temp.getShopid());
		reMap.put("shopname", shop.getShopname());
		HashSet<Imgsrc> img_set = (HashSet<Imgsrc>) shop_temp.getImgsrcs();
		JSONArray jsArr = JSONArray.fromObject(img_set);
		reMap.put("Shopimages", jsArr);
		reMap.put("telephone", shop_temp.getExt2());
		reMap.put("city",
				areaService.GetCityNameByid(shop_temp.getArea().getAreaid()));
		reMap.put("area", shop_temp.getArea().getAreaname());
		reMap.put("dec", shop_temp.getShopdec());
		CommonUtils.toJson(ServletActionContext.getResponse(), token_reMap);
	}

	public void getShopInfoByServiceid() throws Exception {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (serviceid != null) {
			Shop shop = shopService
					.getShopByServid(Integer.parseInt(serviceid));
			if (shop == null) {
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "no");
			} else {
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "yes");
				List<Shop> shops = new ArrayList<Shop>();
				shops.add(shop);
				reMap.put("shop", CommonUtils.shopsToJson(shops));
			}
		} else {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}

	public void test() throws IOException {
		/*
		 * HashSet<Imgsrc> img_set = new HashSet<Imgsrc>(); Imgsrc a=new
		 * Imgsrc(); a.setImgname("daa"); a.setUrl("ddd"); a.setImgdec("ada");
		 * Imgsrc b=new Imgsrc(); b.setImgname("daa"); b.setUrl("ddd");
		 * b.setImgdec("ada"); img_set.add(a); img_set.add(b); JSONArray jsArr =
		 * JSONArray.fromObject(img_set); Map<String, Object> reMap = new
		 * HashMap<String, Object>(); reMap.put("Shopimages", jsArr);
		 * CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		 * Map<String, Object> reMap = new HashMap<String, Object>();
		 * reMap.put("cityname", areaService.GetCityNameByid(7));
		 * CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		 */
		/*
		 * List<Order> orders = shopService.getOrdersByShopid(1); List<Service>
		 * ser = m_Service.GetServiceByOrdid(1); String json =
		 * JSONArray.fromObject(ser).toString();
		 * 
		 * Map<String, Object> reMap = new HashMap<String, Object>();
		 * System.out.println(serviceToJson(ser));
		 * reMap.put("shopname",serviceToJson(ser));
		 * CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		 * List<Integer> prodtypeid =new ArrayList<Integer>();
		 * prodtypeid.add(1); prodtypeid.add(2); List<Service>
		 * ser=m_Service.getServByAreaType("南京","江北新区", 1, prodtypeid);
		 * Map<String, Object> reMap = new HashMap<String, Object>();
		 * reMap.put("shopname",CommonUtils.serviceToJson(ser));
		 * CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		 */
		Area a = areaService.GetByName("武进区", "常州");
		Shop shop = new Shop();
		User user=userservice.findAll().get(0);
		shop.setUser(user);
		shop.setShopname("wwww");
		shop.setArea(a);
		shopService.create(shop);
		Map<String, Object> reMap = new HashMap<String, Object>();
		reMap.put("hotshops", CommonUtils.shopsToJson(shopService.findAll()));
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}

	public void getHotShopsInfo() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (cityname != null) {
			List<Shop> shops = shopService.getHotShops(cityname);
			if (shops.size() > 0) {
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "yes");
				reMap.put("hotshops", CommonUtils.shopsToJson(shops));
			} else {
				reMap.put("IfExist", "no");
			}
		} else {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}
}
