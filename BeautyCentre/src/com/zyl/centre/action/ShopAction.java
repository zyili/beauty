package com.zyl.centre.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
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
import org.apache.poi.hssf.record.formula.functions.Request;
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

	@Resource(name = "tokenService")
	private ITokenService tokenService;

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

		String root = "/usr/apache-tomcat-8.0.28/webapps/BeautyCentre";
		Map<String, Object> token_reMap = new HashMap<String, Object>();

		System.out.println(shop.getShopname());
		System.out.println(shop.getShopaddress());
		System.out.println(shop.getShopphone());
		System.out.println(userid);
		System.out.println(token);
		System.out.println(areaname);
		System.out.println(cityname);

		Shop dbshop = shopService.getByUid(userid);

		Area area_temp = areaService.GetByName(areaname, cityname);
		System.out.println("updateShopInfo---area_temp"
				+ area_temp.getAreaname());
		dbshop.setArea(area_temp);
		dbshop.setShopaddress(shop.getShopaddress());
		dbshop.setShopname(shop.getShopname());
		dbshop.setShopphone(shop.getShopphone());
		if(dbshop.getShoplevel()==null)
		{
			dbshop.setShoplevel(3);
		}
		if(dbshop.getPerconsum()==null)
		{
			dbshop.setPerconsum(200);
		}
		shopService.update(dbshop);
		for (int i = 0; i < files.size(); i++) {
			InputStream is = new FileInputStream(files.get(i));

			String filename = time.getTimeString()
					+ this.getFilesFileName().get(i);

			String url = "shop_upload/" + filename;
			File destFile = new File(root, url);
			OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[400];
			int length = 0;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			is.close();
			os.close();
			Imgsrc img_temp = new Imgsrc();
			img_temp.setUrl(url);
			img_temp.setShop(dbshop);
			img_temp.setImgname(filename);
			imgService.create(img_temp);
		}
		reMap.put("ResultMessage", CommonUtils.SUCCESS);
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		return;
	}

	public void getShopInfo() throws Exception {
		JSONObject rejson = new JSONObject();
		if (userid == null || token == null) {
			rejson.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), rejson);
			return;
		}
		Map<String, Object> maptoken = TokenUtils.manageToken(token,
				tokenService);
		if (maptoken.get("message").equals("SUCCESS")) {
			Shop shop_temp = shopService.getByUid(userid);
			if (shop_temp == null) {
				rejson.put("shopsize", 0);
			}
			List<Shop> shops = new ArrayList<Shop>();
			shops.add(shop_temp);
			rejson.put("ResultMessage", CommonUtils.SUCCESS);
			rejson.put("shop", CommonUtils.shopsToJson(shops));
		} else {
			if (maptoken.get("message").equals("TOKENOUT")) {
				rejson.put("ResultMessage", CommonUtils.TOKENOUT);
			} else {
				rejson.put("ResultMessage", CommonUtils.ERROR);
			}
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), rejson);
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
		 * ser=m_Service.getServByAreaType("�Ͼ�","��������", 1, prodtypeid);
		 * Map<String, Object> reMap = new HashMap<String, Object>();
		 * reMap.put("shopname",CommonUtils.serviceToJson(ser));
		 * CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		 */
		/*
		 * Area a = areaService.GetByName("�����", "����"); Shop shop = new
		 * Shop(); User user=userservice.findAll().get(0); shop.setUser(user);
		 * shop.setShopname("wwww"); shop.setArea(a); shopService.create(shop);
		 * Map<String, Object> reMap = new HashMap<String, Object>();
		 * reMap.put("hotshops",
		 * CommonUtils.shopsToJson(shopService.findAll()));
		 * CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		 * 
		 * User user = new User(); user.setUsername("sun");
		 * user.setPassword("111"); userservice.createShopUser(user);
		 * Map<String, Object> reMap = new HashMap<String, Object>();
		 * reMap.put("hotshops", user.getUsername() +
		 * user.getUserid().toString());
		 * CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		 */
		//m_Service.deleteService(2);
		Shop dbshop = shopService.getByUid("1");
		if(dbshop.getShoplevel()==null)
		{
			System.out.println("可以进入level======nulll");
		}
		if(dbshop.getPerconsum()==null)
		{
			System.out.println("可以进入Perconsum======nulll");
		}
	}

	public void getHotShopsInfo() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (cityname != null) {
			String city = URLDecoder.decode(cityname, "UTF-8");
			System.out.println("city--------------------" + city);
			List<Shop> shops = shopService.getHotShops(city);
			if (shops.size() > 0) {
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "yes");
				reMap.put("hotshops", CommonUtils.shopsToJson(shops));
			} else {
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "no");
			}
		} else {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}
}
