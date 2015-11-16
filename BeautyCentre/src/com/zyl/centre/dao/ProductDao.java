package com.zyl.centre.dao;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Product;
import com.zyl.centre.entity.User;

@Repository("productDao")
public class ProductDao extends HibernateDao<Product> implements IProductDao {
	
	public ProductDao() {
		super();
		setClazz(Product.class);
		setLog(LogFactory.getLog(ProductDao.class));
	}




	@Override
	public Product findOneById(int prodid) {
		log.debug("getting product instance id username:" + prodid);
		try {
			String sql = "from Product where productid='"+prodid+"'";
			@SuppressWarnings("unchecked")
			List<Product> prod = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!prod.isEmpty()) {
				return prod.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	


}
