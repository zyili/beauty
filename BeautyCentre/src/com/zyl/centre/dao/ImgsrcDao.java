package com.zyl.centre.dao;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Imgsrc;
import com.zyl.centre.entity.Order;

@Repository("imgsrcDao")
public class ImgsrcDao extends HibernateDao<Imgsrc> implements IImgsrcDao {
	public ImgsrcDao() {
		super();
		setClazz(Imgsrc.class);
		setLog(LogFactory.getLog(ImgsrcDao.class));
	}

	@Override
	public List<Imgsrc> getImagsByServiceid(int serid) {
		// TODO Auto-generated method stub
		try {
			String sql = "from Imgsrc where serviceid='" + serid + "'";
			@SuppressWarnings("unchecked")
			List<Imgsrc> imgs = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!imgs.isEmpty()) {
				return imgs;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public List<Imgsrc> getImagsByShopid(int shopid) {
		// TODO Auto-generated method stub
		try {
			String sql = "from Imgsrc where shopid='" + shopid + "'";
			@SuppressWarnings("unchecked")
			List<Imgsrc> imgs = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!imgs.isEmpty()) {
				return imgs;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}
}
