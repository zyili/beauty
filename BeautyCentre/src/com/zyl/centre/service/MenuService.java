package com.zyl.centre.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IMenuDao;
import com.zyl.centre.dao.IUserDao;
import com.zyl.centre.entity.Menu;

@Service("menuService")
public class MenuService extends AbstractService<Menu> implements IMenuService{

	@Resource(name = "menuDao")
	private IMenuDao dao;
	
	@Override
	protected IOperations<Menu> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	

	protected void setDao(IMenuDao dao) {
		this.dao = dao;
	}

}
