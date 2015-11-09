package com.zyl.centre.dao;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Serviceordrel;

@Repository("serviceordrelDao")
public class ServiceordrelDao extends HibernateDao<Serviceordrel> implements
		IServiceordrelDao {
	public ServiceordrelDao() {
		super();
		setClazz(Serviceordrel.class);
		setLog(LogFactory.getLog(ServiceordrelDao.class));
	}
}
