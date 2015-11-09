package com.zyl.centre.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IAreaDao;
import com.zyl.centre.entity.Area;

@Service("areaService")
public class AreaService extends AbstractService<Area> implements IAreaService {

	@Resource(name = "areaDao")
	private IAreaDao dao;

	public AreaService() {
		super();
	}

	@Override
	protected IOperations<Area> getDao() {
		return this.dao;
	}

	protected void setDao(IAreaDao dao) {
		this.dao = dao;
	}

	public Area GetByName(String areaname, String cityname) {
		return dao.GetByName(areaname, cityname);
	}

	public String GetCityNameByid(Integer areaid) {
		return dao.GetCityNameByid(areaid);
	}
}
