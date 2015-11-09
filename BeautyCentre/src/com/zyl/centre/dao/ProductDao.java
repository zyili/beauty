package com.zyl.centre.dao;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Product;

@Repository("productDao")
public class ProductDao extends HibernateDao<Product> implements IProductDao {
	
	public ProductDao() {
		super();
		setClazz(Product.class);
		setLog(LogFactory.getLog(ProductDao.class));
	}
}
