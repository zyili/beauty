package com.zyl.centre.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IProdtypeDao;
import com.zyl.centre.entity.Prodtype;

@Service("prodtypeService")
public class ProdtypeService extends AbstractService<Prodtype> implements IProdtypeService{

	@Resource(name = "prodtypeDao")
	private IProdtypeDao dao;
	
	@Override
	protected IOperations<Prodtype> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

}
