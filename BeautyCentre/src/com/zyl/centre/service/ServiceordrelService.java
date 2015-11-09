package com.zyl.centre.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IServiceordrelDao;
import com.zyl.centre.entity.Serviceordrel;

@Service("serviceordrelService")
public class ServiceordrelService extends AbstractService<Serviceordrel>
		implements IServiceordrelService {
	
	@Resource(name = "serviceordrelDao")
	private IServiceordrelDao dao;

	public ServiceordrelService() {
		super();
	}
	@Override
	protected IOperations<Serviceordrel> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

}
