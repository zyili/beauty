package com.zyl.centre.dao;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Prodtype;

@Repository("prodtypeDao")
public class ProdtypeDao extends HibernateDao<Prodtype> implements IProdtypeDao {

	public ProdtypeDao() {
		super();
		setClazz(Prodtype.class);
		setLog(LogFactory.getLog(ProdtypeDao.class));
	}
}
