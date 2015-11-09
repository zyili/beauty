package com.zyl.centre.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IServiceDao;

@Service("serviceService")
public class ServiceService extends AbstractService<com.zyl.centre.entity.Service> implements IServiceService {

	@Resource(name = "serviceDao")
	private IServiceDao dao;

	public ServiceService() {
		super();
	}

	@Override
	protected IOperations<com.zyl.centre.entity.Service> getDao() {
		return this.dao;
	}

	@Override
	public List<com.zyl.centre.entity.Service> GetServiceByOrdid(int ordid) {
		// TODO Auto-generated method stub
		return dao.GetServiceByOrdid(ordid);
	}

	@Override
	public List<com.zyl.centre.entity.Service> getServByAreaType(String city,
			String area, int productid, List<Integer> prodtypeid) {
		// TODO Auto-generated method stub
		return dao.getServByAreaType(city, area, productid, prodtypeid);
	}
	public void DeleteTypeRelByid(int id)
	{
		dao.DeleteTypeRelByid(id);
	}
	public void UpdateTypeRel(int service_id,int product_id, List<Integer> prodtypeid)
	{
		dao.UpdateTypeRel(service_id, product_id, prodtypeid);
	}

	@Override
	public List<com.zyl.centre.entity.Service> getServicesByShopid(int shop) {
		// TODO Auto-generated method stub
		return dao.getServicesByShopid(shop);
	}

	@Override
	public com.zyl.centre.entity.Service getServiceByid(int id) {
		// TODO Auto-generated method stub
		return dao.getServiceByid(id);
	}
}
