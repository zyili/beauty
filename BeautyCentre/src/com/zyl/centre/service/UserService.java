package com.zyl.centre.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IAreaDao;
import com.zyl.centre.dao.IOrderDao;
import com.zyl.centre.dao.IServiceDao;
import com.zyl.centre.dao.IServiceordrelDao;
import com.zyl.centre.dao.IShopDao;
import com.zyl.centre.dao.ITokenDao;
import com.zyl.centre.dao.IUserDao;
import com.zyl.centre.entity.Area;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Serviceordrel;
import com.zyl.centre.entity.ServiceordrelId;
import com.zyl.centre.entity.Shop;
import com.zyl.centre.entity.Token;
import com.zyl.centre.entity.User;

@Service("userservice")
public  class UserService extends AbstractService<User> implements IUserService {
	@Resource(name = "usersDao")
	private IUserDao dao;

	@Resource(name = "tokenDao")
	private ITokenDao tokendao;
	

	@Resource(name = "serviceDao")
	private IServiceDao m_dao;

	@Resource(name = "orderDao")
	private IOrderDao ord_dao;

	@Resource(name = "serviceordrelDao")
	private IServiceordrelDao rel_dao;
	
	@Resource(name = "areaDao")
	private IAreaDao a_dao;
	

	@Resource(name = "shopDao")
	private IShopDao shop_dao;
	
	public UserService() {
		super();
	}

	@Override
	protected IOperations<User> getDao() {
		return this.dao;
	}

	protected void setDao(IUserDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean checkByName(String username) {
		// TODO Auto-generated method stub
		return dao.checkByName(username);
	}

	@Override
	public User findOneByPass(String username, String pass) {
		// TODO Auto-generated method stub
		return dao.findOneByPass(username, pass);
	}

	@Override
	public int GetUserIDByName(String username, String password) {
		// TODO Auto-generated method stub
		return dao.GetUserIDByName(username, password);
	}

	@Override
	public void createUser(User user, String tokenCode) {
		dao.create(user);
		Token token = new Token();
		token.setUserid(user.getUserid());
		token.setTokencode(tokenCode);
		Calendar cal = Calendar.getInstance();// 取当前日期。
		Date currentDate = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, +30);// 取当前日期的后30天.
		token.setCreatedatetime(currentDate);
		token.setExpiredatetime(cal.getTime());
		tokendao.createTokenCode(token);
	}

	@Override
	public User findUserByUserid(int id) {
		// TODO Auto-generated method stub
		return dao.findUserByUserid(id);
	}

	@Override
	public Order bookService(int uerid,int serviceid,int number) {
		// TODO Auto-generated method stub
		com.zyl.centre.entity.Service serv= m_dao.getServiceByid(serviceid);
		User user=findUserByUserid(uerid);
		Order ord =new Order();
		ord.setUser(user);
		ord.setCreatetime(new Date());
		ord.setNumber(number);
		ord.setSumprice(number*serv.getPrice());
		ord.setSubmitusername(user.getUsername());
		ord.setState(0);
		ord.setOrdphone(user.getPhone());
		ord_dao.create(ord);
		
		Serviceordrel ordrel =new Serviceordrel();
		ordrel.setDate(new Date());
		ordrel.setOrder(ord);
		ordrel.setService(serv);
		ordrel.setId(new ServiceordrelId(ord.getOrderid(),serv.getServiceid()));
		rel_dao.create(ordrel);
		return ord;
	}

	@Override
	public int createShopUser(User user) {
		// TODO Auto-generated method stub
		dao.create(user);
		Area a=a_dao.GetByName("鼓楼区","南京");
		if(a==null)
		{
			a=a_dao.findAll().get(0);
		}
		Shop shop=new Shop();
		shop.setArea(a);
		shop.setShopname("shop of "+user.getUsername());
		shop.setUser(user);
		shop_dao.create(shop);
		return user.getUserid();
	}
}
