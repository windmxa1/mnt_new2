package org.dao.imp;

import java.util.List;

import org.dao.ServiceHostDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ServiceHost;
import org.util.HibernateSessionFactory;

public class ServiceHostDaoImp implements ServiceHostDao {

	public List<ServiceHost> getList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from ServiceHost");
			List<ServiceHost> list = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean insert(Long hostid, Long serviceid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ServiceHost sHost = new ServiceHost(serviceid, hostid);
			session.save(sHost);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean del(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			ServiceHost shHost = (ServiceHost) session.load(ServiceHost.class,
					id);
			session.delete(shHost);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// public boolean rewrite(Long hostid) {
	//
	// return false;
	// }

	@Override
	public ServiceHost getSHByHostid(Long hostid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from ServiceHost where hostid = ?");
			query.setParameter(0, hostid);
			query.setMaxResults(1);
			ServiceHost sHost = (ServiceHost) query.uniqueResult();
			System.out.println(sHost);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return sHost;
		} catch (Exception e) {
			return null;
		}

	}

}
