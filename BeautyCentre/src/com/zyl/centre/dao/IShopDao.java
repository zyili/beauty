package com.zyl.centre.dao;

import java.util.List;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Shop;


public interface IShopDao extends IOperations<Shop>{
	public Shop getByUid(String userid);
	public Shop getByShopid(int id);
	public List<Order> getOrdersByShopid(int id);
	public List <Shop> getHotShops(String city);
}
