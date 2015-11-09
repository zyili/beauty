package com.zyl.centre.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IServiceDao;
import com.zyl.centre.dao.IShopDao;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Shop;

@Service("shopService")
public  class ShopService extends AbstractService<Shop> implements IShopService {

	@Resource(name = "shopDao")
	private IShopDao dao;
	
	@Resource(name = "serviceDao")
	private IServiceDao servdao;
	
	public ShopService() {
		super();
	}

	@Override
	protected IOperations<Shop> getDao() {
		return this.dao;
	}
	public Shop getByUid(String userid){
		return dao.getByUid(userid);
	}

	@Override
	public Shop getByShopid(int id) {
		// TODO Auto-generated method stub
		return dao.getByShopid(id);
	}

	@Override
	public List<Order> getOrdersByShopid(int id) {
		// TODO Auto-generated method stub
		return dao.getOrdersByShopid(id);
	}

	@Override
	public Shop getShopByServid(int servid) {
		// TODO Auto-generated method stub
		com.zyl.centre.entity.Service serv=servdao.getServiceByid(servid);
		return serv.getShop();
	}

	@Override
	public List<Shop> getHotShops(String city) {
		// TODO Auto-generated method stub
		return dao.getHotShops(city);
	}
}
