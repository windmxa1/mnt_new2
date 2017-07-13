package org.dao.imp;

import java.util.List;

import org.dao.ServicesDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Services;
import org.util.HibernateSessionFactory;

public class ServicesDaoImp implements ServicesDao {

	/**
	 * 获取一级服务组，即机房组
	 */
	public List<Services> getServicesGroup() {
		// TODO Auto-generated method stub

		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from Services where name like 'JF%'");
			List<Services> list = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			/*
			 * 通过组id获取所属所有设备 GroupsDao gDao = new GroupsDaoImp(); List<Object[]>
			 * li =gDao.getHostByGroupid((long) 8); for(Object o[] : li){
			 * for(Object a : o){ System.out.print(a.toString()+" "); }
			 * System.out.println("\n"); }
			 */
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Services getServicesById(Long serviceid) {
		// TODO Auto-generated method stub

		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from Services where serviceid = ?");
			query.setParameter(0, serviceid);
			query.setMaxResults(1);
			Services s = (Services) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();
			/*
			 * 通过组id获取所属所有设备 GroupsDao gDao = new GroupsDaoImp(); List<Object[]>
			 * li =gDao.getHostByGroupid((long) 8); for(Object o[] : li){
			 * for(Object a : o){ System.out.print(a.toString()+" "); }
			 * System.out.println("\n"); }
			 */
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Services getServiceByTriggerId(Long triggerid) {
		// TODO Auto-generated method stub

		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from Services where triggerid = ?");
			query.setParameter(0, triggerid);
			query.setMaxResults(1);
			Services s = (Services) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
