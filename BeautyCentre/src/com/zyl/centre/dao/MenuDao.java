package com.zyl.centre.dao;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Menu;

@Repository("menuDao")
public class MenuDao extends HibernateDao<Menu> implements IMenuDao {

	public MenuDao() {
		super();
		setClazz(Menu.class);
		setLog(LogFactory.getLog(MenuDao.class));
	}

}
