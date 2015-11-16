package com.zyl.centre.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IProductDao;
import com.zyl.centre.entity.Product;

@Service("productService")
public class ProductService extends AbstractService<Product> implements
		IProductService {

	@Resource(name = "productDao")
	private IProductDao dao;

	@Override
	protected IOperations<Product> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}
	public Product findOneById(int prodid)
	{
		return dao.findOneById(prodid);
	}

}
