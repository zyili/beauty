package com.zyl.centre.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.common.utils.CommonUtils;
import com.zyl.centre.common.utils.TimeString;
import com.zyl.centre.common.utils.TokenUtils;
import com.zyl.centre.entity.Imgsrc;
import com.zyl.centre.entity.Prodtype;
import com.zyl.centre.entity.Product;
import com.zyl.centre.entity.Service;
import com.zyl.centre.entity.Shop;
import com.zyl.centre.service.IImgService;
import com.zyl.centre.service.IProdtypeService;
import com.zyl.centre.service.IProductService;
import com.zyl.centre.service.IServiceService;

public class ServiceAction extends ActionSupport {

	private Shop shop;
	private Service service;
	private Product product;
	private Prodtype productype;
	private String token;
	private List<File> files;
	private List<String> filesFileName;
	private List<String> filesContentType;
	private int number;

	@Resource(name = "serviceService")
	private IServiceService m_Service;

	@Resource(name = "productService")
	private IProductService m_Product;

	@Resource(name = "prodtypeService")
	private IProdtypeService m_ProdtypeService;

	@Resource(name = "imgService")
	public IImgService imgService;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Prodtype getProductype() {
		return productype;
	}

	public void setProductype(Prodtype producttype) {
		this.productype = producttype;
	}

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

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public void produceService() throws Exception {
		Map<String, Object> reMap = new HashMap<String, Object>();
		Map<String, Object> token_reMap = new HashMap<String, Object>();
		JSONObject json = CommonUtils.getJson();
		if (json == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		String root = ServletActionContext.getRequest().getRealPath(
				"service_upload");
		token_reMap = TokenUtils.manageToken(token);
		if (!token_reMap.get("message").equals("SUCCESS")) {
			CommonUtils.toJson(ServletActionContext.getResponse(), token_reMap);
			return;
		}
		if (null == ServletActionContext.getRequest().getParameter("token")
				|| null == ServletActionContext.getRequest().getParameter(
						"files") || shop == null || service == null
				|| product == null || productype == null) {

			reMap.put("ResultMessage", CommonUtils.JSONERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		if (json == null || json.toString() == "{}") {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
		}
		JSONArray ids = JSONArray.fromObject(json.get("types"));
		if (ids == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			return;
		}
		List<Integer> prodtypeid = new ArrayList<Integer>();
		for (int i = 0; i < ids.size(); i++) {
			JSONObject jsonObj = ids.getJSONObject(i);
			prodtypeid.add(jsonObj.getInt("prodtypeid"));
		}

		TimeString time = new TimeString();
		m_Service.update(service);
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
			String url = "service_upload" + filename;
			Imgsrc img_temp = new Imgsrc();
			img_temp.setUrl(url);
			img_temp.setService(service);
			img_temp.setImgname(filename);
			imgService.update(img_temp);
		}

		// update table prodtyperel
		m_Service.DeleteTypeRelByid(service.getServiceid());
		m_Service.UpdateTypeRel(service.getServiceid(), service.getProduct()
				.getProductid(), prodtypeid);

		reMap.put("ResultMessage", CommonUtils.SUCCESS);
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		return;

	}

	public void deleteService() throws Exception {
		Map<String, Object> reMap = new HashMap<String, Object>();
		Map<String, Object> token_reMap = new HashMap<String, Object>();
		if (token == null || service == null || service.getServiceid() == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		token_reMap = TokenUtils.manageToken(token);
		if (!token_reMap.get("message").equals("SUCCESS")) {
			CommonUtils.toJson(ServletActionContext.getResponse(), token_reMap);
			return;
		}
		if (service.getServiceid() == null) {
			reMap.put("ResultMessage", CommonUtils.JSONERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		m_Service.deleteById(service.getServiceid());
		m_Service.DeleteTypeRelByid(service.getServiceid());
		reMap.put("ResultMessage", CommonUtils.SUCCESS);
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		return;

	}

	public void getServsInfoByAreaType() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		JSONObject json = CommonUtils.getJson();
		if (json == null || json.toString() == "{}") {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
		} else {
			boolean falge = false;
			String city = json.getString("cityname");
			String area = json.getString("areaname");
			if (city == null || area == null) {
				falge = true;
			}
			Integer prodid = Integer.valueOf(json.getString("prodctid"));// 大类
			JSONArray ids = JSONArray.fromObject(json.get("types"));// type
			// 查询服务的所有小类
			if (prodid == null || ids == null) {
				falge = true;
			}
			if (falge) {
				reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			} else {
				List<Integer> prodtypeid = new ArrayList<Integer>();
				for (int i = 0; i < ids.size(); i++) {
					JSONObject jsonObj = ids.getJSONObject(i);
					prodtypeid.add(jsonObj.getInt("prodtypeid"));
				}
				List<Service> ser = m_Service.getServByAreaType(city, area,
						prodid, prodtypeid);
				if (ser.size() > 0) {
					reMap.put("ResultMessage", CommonUtils.SUCCESS);
					reMap.put("IfExist", "yes");
					reMap.put("servs", CommonUtils.serviceToJson(ser));
				} else {
					reMap.put("ResultMessage", CommonUtils.SUCCESS);
					reMap.put("IfExist", "no");
				}
			}
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}

	public void getServsInfo() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (shop == null || token == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		Map<String, Object> maptoken = TokenUtils.manageToken(token);
		if (maptoken.get("message").equals("SUCCESS")) {
			int shopid = shop.getShopid();
			List<Service> services = m_Service.getServicesByShopid(shopid);
			if (services != null && !services.isEmpty()) {
				int sum = cmSum(services.size());
				List<Service> reServs = formatServices(number, services);
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
				reMap.put("IfExist", "yes");
				reMap.put("sumPage", sum);
				reMap.put("services", CommonUtils.serviceToJson(reServs));
			} else {
				reMap.put("ResultMessage", CommonUtils.SUCCESS);
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

	public void getServtype() throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (token == null) {
			reMap.put("ResultMessage", CommonUtils.PARAMERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		Map<String, Object> maptoken = TokenUtils.manageToken(token);
		if (maptoken.get("message").equals("SUCCESS")) {
			List<Product> prods = m_Product.findAll();
			List<Prodtype> prodtypes = m_ProdtypeService.findAll();
			reMap.put("ResultMessage", CommonUtils.SUCCESS);
			reMap.put("prods", CommonUtils.prodsToJosn(prods));
			reMap.put("prodtypes", CommonUtils.prodtypesToJosn(prodtypes));
		} else {
			if (maptoken.get("message").equals("TOKENOUT")) {
				reMap.put("ResultMessage", CommonUtils.TOKENOUT);
			} else {
				reMap.put("ResultMessage", CommonUtils.ERROR);
			}
		}
		CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}

	public List<Service> formatServices(int number, List<Service> servs) {
		List<Service> hs = new ArrayList<Service>();
		int size = servs.size();
		int sum = cmSum(size);
		if (number == 0) {
			hs = servs;
		} else {
			if (sum > 1) {
				Service[] mser = (Service[]) servs.toArray();
				for (int i = 10 * (number - 1) + 1; i <= 10 * number; i++) {
					hs.add(mser[i]);
				}
			} else {
				hs = servs;
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

}
