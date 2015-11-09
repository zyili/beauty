package com.zyl.centre.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Area;
import com.zyl.centre.entity.District;
import com.zyl.centre.entity.Service;

@Repository("areaDao")
public class AreaDao extends HibernateDao<Area> implements IAreaDao {
	public AreaDao() {
		super();
		setClazz(Area.class);
		setLog(LogFactory.getLog(AreaDao.class));
	}

	public Area GetByName(String areaname, String cityname) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT a.* FROM `area`AS a,`district` AS d WHERE a.`districtid`=d.`districtid` AND a.`areaname`= '"
					+ areaname + "' AND d.`districtname`='" + cityname + "'";
			@SuppressWarnings("unchecked")
			List<Area> as = (List<Area>) getCurrentSession()
					.createSQLQuery(sql).addEntity(Area.class).list();
			if(!as.isEmpty())
			{
				return as.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	public String GetCityNameByid(Integer areaid) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String sql = "SELECT b.districtname city,a.areaname area FROM AREA AS a, district AS b WHERE a.districtid=b.districtid AND a.areaid ='"
					+ areaid + "'";
			org.hibernate.SQLQuery query = getCurrentSession().createSQLQuery(
					sql);
			query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			List<Map<String, Object>> listmap = query.list();
			if (listmap != null && !listmap.isEmpty()) {
				map = listmap.get(0);
				return map.get("city").toString();
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

}
