package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ServicesDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Services;
import org.util.HibernateSessionFactory;
import org.view.VServices;
import org.view.VServicesId;

public class ServicesDaoImp implements ServicesDao {

	/**
	 * 获取一级服务组，即机房组
	 */
	public List<VServicesId> getServicesGroup() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from VServices v where v.id.name like 'JF%' and v.id.name != 'JF-宾馆路主局' ");
			List<VServices> list = query.list();
			List<VServicesId> list2 = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (VServices v : list) {
					list2.add(v.getId());
				}
			}
			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public Services getServicesById(Long serviceid) {
		// TODO Auto-generated method stub

		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("from Services where serviceid = ?");
			query.setParameter(0, serviceid);
			query.setMaxResults(1);
			Services s = (Services) query.uniqueResult();

			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	public Services getServiceByTriggerId(Long triggerid) {
		// TODO Auto-generated method stub

		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("from Services where triggerid = ?");
			query.setParameter(0, triggerid);
			query.setMaxResults(1);
			Services s = (Services) query.uniqueResult();

			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

}
