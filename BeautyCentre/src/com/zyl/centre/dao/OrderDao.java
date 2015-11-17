package com.zyl.centre.dao;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Area;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Shop;

@Repository("orderDao")
public class OrderDao extends HibernateDao<Order> implements IOrderDao {
	public OrderDao() {
		super();
		setClazz(Order.class);
		setLog(LogFactory.getLog(OrderDao.class));
	}

	@Override
	public Order getOrdByid(int id) {
		// TODO Auto-generated method stub
		try {
			String sql = "from Order where orderid='" + id + "'";
			@SuppressWarnings("unchecked")
			List<Order> ords = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!ords.isEmpty()) {
				return ords.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public List<Order> getOrdsByUserid(int userid) {
		// TODO Auto-generated method stub
		try {
			String sql = "from Order where submituserid='" + userid + "'";
			@SuppressWarnings("unchecked")
			List<Order> ords = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!ords.isEmpty()) {
				return ords;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public List<Order> getOrdsByServiceid(int serid) {
		try {
			String sql = "SELECT orde.* FROM `order` AS orde WHERE orde.`orderid` IN(SELECT sero.`orderid` FROM `serviceordrel` AS sero WHERE sero.`serviceid`='"
					+ serid + "')";
			@SuppressWarnings("unchecked")
			List<Order> orders = (List<Order>) getCurrentSession()
					.createSQLQuery(sql).addEntity("orde",Order.class).list();
			return orders;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}
}
