package com.zyl.centre.dao;


import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Area;

public interface IAreaDao extends IOperations<Area>{
	public Area GetByName(String areaname, String cityname);
	public String GetCityNameByid(Integer areaid);
}
