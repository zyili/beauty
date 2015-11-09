package com.zyl.centre.dao;

import java.util.List;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Order;

public interface IOrderDao extends IOperations<Order>{
	public Order getOrdByid(int id);
	public List<Order> getOrdsByUserid(int userid);
}
