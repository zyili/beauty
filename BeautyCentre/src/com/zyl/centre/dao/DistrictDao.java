package com.zyl.centre.dao;

import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.District;

@Repository("districtDao")
public class DistrictDao extends HibernateDao<District> implements IDistrictDao {
	public DistrictDao() {
		super();
		setClazz(District.class);
	}
}
