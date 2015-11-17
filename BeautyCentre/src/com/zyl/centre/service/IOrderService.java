package com.zyl.centre.service;

import java.util.List;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Order;

public interface IOrderService extends IOperations<Order> {
	public Order getOrdByid(int id);

	public List<Order> getOrdsByUserid(int userid);

	public List<Order> getOrdsByServiceid(int serid);

}
