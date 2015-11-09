package com.zyl.centre.service;

import java.util.List;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Shop;


public interface IShopService extends IOperations<Shop>{
	
	public Shop getByUid(String userid);
	public Shop getByShopid(int id);
	public List<Order> getOrdersByShopid(int id);
	public Shop getShopByServid(int servid);
	public List <Shop> getHotShops(String city);
}
