package com.zyl.centre.dao;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.zyl.centre.entity.Area;
import com.zyl.centre.entity.Order;
import com.zyl.centre.entity.Prodtype;
import com.zyl.centre.entity.Service;
import com.zyl.centre.entity.Shop;

@Repository("serviceDao")
public class ServiceDao extends HibernateDao<Service> implements IServiceDao {
	public ServiceDao() {
		super();
		setClazz(Service.class);
		setLog(LogFactory.getLog(ServiceDao.class));
	}

	@Override
	public List<Service> GetServiceByOrdid(int ordid) {
		// TODO Auto-generated method stub
		log.debug("getting services instance with ordid:" + ordid);
		try {
			String sql = "SELECT b.* FROM service AS b, serviceordrel AS c, `order` AS d WHERE b.`serviceid` = c.`serviceid` AND c.`orderid` = d.`orderid` AND d.`orderid`='"
					+ ordid + "' ";
			@SuppressWarnings("unchecked")
			List<Service> services = (List<Service>) getCurrentSession()
					.createSQLQuery(sql).addEntity(Service.class).list();
			return services;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public Service getServiceByid(int id) {
		// TODO Auto-generated method stub
		try {
			String sql = "from Service where serviceid='" + id + "'";
			@SuppressWarnings("unchecked")
			List<Service> servs = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!servs.isEmpty()) {
				return servs.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public List<Service> getServByAreaType(String city, String area,
			int productid, List<Integer> prodtypeid) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM `service` AS ser WHERE ser.`shopid` IN (SELECT shop.`shopid` FROM `shop` AS shop,`area` AS a,`district` AS d WHERE a.`areaid` = shop.`areaid` AND a.`districtid` = d.`districtid` AND a.`areaname` = '"
					+ area
					+ "'AND d.`districtname` ='"
					+ city
					+ "') AND ser.`serviceid` IN (SELECT pt.`serviceid` FROM`prodtyperel` AS pt WHERE pt.`productid` = '"
					+ productid + "' AND pt.`prodtypeid` IN";
			StringBuffer strsql = new StringBuffer(sql);
			strsql.append('(');
			int n = prodtypeid.size();
			for (int i = 0; i < n; i++) {
				if (i > 0) {
					strsql.append(',');
				}
				strsql.append(prodtypeid.get(i));
			}
			strsql.append(')');
			strsql.append(')');
			@SuppressWarnings("unchecked")
			List<Service> sers = (List<Service>) getCurrentSession()
					.createSQLQuery(strsql.toString()).addEntity(Service.class)
					.list();
			return sers;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	public void UpdateTypeRel(int service_id, int product_id,
			List<Integer> prodtypeid) {
		Session session = null;
	    Transaction tr = null; 
		for (int count = 0; count < prodtypeid.size(); count++) {
			try {
				String sql = "insert into prodtyperel (productid, prodtypeid, serviceid) values (" + product_id
						+ "," + prodtypeid.get(count) + "," + service_id + ")";
				session = getCurrentSession();
				session.createSQLQuery(sql).executeUpdate();
				
			} catch (RuntimeException re) {
				log.error("get  failed", re);
				throw re;
			}
		}
	}

	public void DeleteTypeRelByid(int id) {
		try {
			String sql = "select * from prodtyperel as p where p.serviceid='" + id + "'";
			if(!getCurrentSession().createSQLQuery(sql).list().isEmpty())
			{
				sql = "delete from prodtyperel where serviceid='" + id + "'";
				getCurrentSession().createQuery(sql).list();
			}
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	@Override
	public List<Service> getServicesByShopid(int shop) {
		// TODO Auto-generated method stub
		try {
			String sql = "from Service where shopid='" + shop + "'";
			@SuppressWarnings("unchecked")
			List<Service> servs = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!servs.isEmpty()) {
				return servs;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}
}
