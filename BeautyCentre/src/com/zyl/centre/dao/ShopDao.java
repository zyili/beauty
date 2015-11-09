package com.zyl.centre.dao;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Shop;
import com.zyl.centre.entity.User;

@Repository("shopDao")
public class ShopDao extends HibernateDao<Shop> implements IShopDao {

	public ShopDao() {
		super();
		setClazz(Shop.class);
		setLog(LogFactory.getLog(ShopDao.class));
	}

	@Override
	public Shop getByUid(String userid) {
		Integer id = Integer.valueOf(userid);
		try {
			String sql = "from Shop where createuserid='" + id + "'";
			@SuppressWarnings("unchecked")
			List<Shop> shops = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!shops.isEmpty()) {
				return shops.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public Shop getByShopid(int id) {
		// TODO Auto-generated method stub
		log.debug("getting Shop instance with id:" + id);
		try {
			String sql = "from Shop where shopid='" + id + "'";
			@SuppressWarnings("unchecked")
			List<Shop> shops = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!shops.isEmpty() && shops != null) {
				return shops.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public List<Order> getOrdersByShopid(int id) {
		// TODO Auto-generated method stub
		log.debug("getting orders instance with shopid:" + id);
		try {
			String sql = "SELECT d.*  FROM shop AS a,service AS b, serviceordrel AS c,`order` AS d WHERE a.`shopid` = b.`shopid` AND b.`serviceid` = c.`serviceid` AND c.`orderid` = d.`orderid` AND a.`shopid` = '"
					+ id + "' ";
			@SuppressWarnings("unchecked")
			List<Order> orders = (List<Order>) getCurrentSession()
					.createSQLQuery(sql).addEntity(Order.class).list();
			return orders;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public List<Shop> getHotShops(String city) {
		// TODO Auto-generated method stub
		log.debug("getting getHotShops  with city:" + city);
		try {
			String sql = "SELECT s.* FROM`shop` AS s,(SELECT c.`ordcount`,s.`shopid` FROM `shopcity` AS s,`shopordcunt` AS c WHERE s.`shopid` = c.`shopid` AND s.`city` = '"
					+ city
					+ "' ORDER BY c.`ordcount` DESC, c.`orddate` DESC LIMIT 1) AS t WHERE s.`shopid` = t.`shopid`  ";
			@SuppressWarnings("unchecked")
			List<Shop> shops = (List<Shop>) getCurrentSession()
					.createSQLQuery(sql).addEntity(Shop.class).list();
			return shops;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}
}
