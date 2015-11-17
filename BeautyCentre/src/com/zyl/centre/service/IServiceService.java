package com.zyl.centre.service;

import java.util.List;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Service;

public interface IServiceService extends IOperations<Service> {
	List<Service> GetServiceByOrdid(int ordid);

	List<Service> getServByAreaType(String city, String area, int productid,
			List<Integer> prodtypeid);

	void DeleteTypeRelByid(int id);

	void UpdateTypeRel(int service_id, int product_id, List<Integer> prodtypeid);

	List<Service> getServicesByShopid(int shop);

	Service getServiceByid(int id);
	
	void deleteService(int serviceid);
}
