package com.zyl.centre.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.google.common.base.Preconditions;
import com.zyl.centre.common.utils.*;

@SuppressWarnings("unchecked")
public abstract class HibernateDao<T extends Serializable> implements
		IOperations<T> {

	private Class<T> clazz;

	protected Log log;

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	protected final void setClazz(final Class<T> clazzToSet) {
		this.clazz = Preconditions.checkNotNull(clazzToSet);
	}

	public void setLog(Log log) {
		this.log = log;
	}

	protected final Session getCurrentSession() {
		if (null == sessionFactory.getCurrentSession()) {
			System.out.println("session为控制---------------");
			Session session = sessionFactory.openSession();
			return session;
		}
		return sessionFactory.getCurrentSession();
	}

	@Override
	public final T findOne(final long id) {
		log.debug("findOne " + clazz.getName() + " instance");
		try {
			T result = (T) getCurrentSession().get(clazz, id);
			log.debug("findOne successful");
			return result;
		} catch (RuntimeException re) {
			log.error("findOne  failed", re);
			throw re;
		}
	}

	@Override
	public final List<T> findAll() {
		log.debug("findAll " + clazz.getName() + " instance");
		try {
			List<T> result = getCurrentSession().createQuery(
					"from " + clazz.getName()).list();
			log.debug("findAll successful");
			return result;
		} catch (RuntimeException re) {
			log.error("findAll  failed", re);
			throw re;
		}
	}

	@Override
	public final void create(final T entity) {
		log.debug("creating " + clazz.getName() + " instance");
		try {
			Preconditions.checkNotNull(entity);
			getCurrentSession().saveOrUpdate(entity);
			getCurrentSession().flush();
			log.debug("create successful");
		} catch (RuntimeException re) {
			log.error("create  failed", re);
			throw re;
		}

	}

	@Override
	public final T update(final T entity) {
		log.debug("update " + clazz.getName() + " instance");
		try {
			Preconditions.checkNotNull(entity);
			getCurrentSession().update(entity);
			getCurrentSession().flush();
			log.debug("update successful");
			return entity;
		} catch (RuntimeException re) {
			log.error("update  failed", re);
			throw re;
		}

		// return (T)getCurrentSession().merge(entity);
	}

	@Override
	public final void delete(final T entity) {
		log.debug("delete " + clazz.getName() + " instance with entity:"
				+ entity.toString());
		try {
			Preconditions.checkNotNull(entity);
			getCurrentSession().delete(entity);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete  failed", re);
			throw re;
		}
	}

	@Override
	public final void deleteById(final long entityId) {
		log.debug("deletting " + clazz.getName() + " instance with id: "
				+ entityId);
		try {
			final T entity = findOne(entityId);
			Preconditions.checkState(entity != null);
			delete(entity);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}