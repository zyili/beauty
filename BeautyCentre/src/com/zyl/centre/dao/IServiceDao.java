package com.zyl.centre.dao;

import java.util.List;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Service;

public interface IServiceDao extends IOperations<Service> {
	public List<Service> GetServiceByOrdid(int ordid);

	public Service getServiceByid(int id);

	public List<Service> getServByAreaType(String city, String area,
			int productid, List<Integer> prodtypeid);

	public void DeleteTypeRelByid(int id);

	public void UpdateTypeRel(int service_id, int product_id,
			List<Integer> prodtypeid);

	public List<Service> getServicesByShopid(int shop);
}
