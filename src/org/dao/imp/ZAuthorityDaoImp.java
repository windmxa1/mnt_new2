package org.dao.imp;

import java.util.List;

import org.dao.ZAuthorityDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZAuthority;
import org.util.HibernateSessionFactory;

public class ZAuthorityDaoImp implements ZAuthorityDao{
	@Override
	public List<ZAuthority> getAList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			List list = session.createQuery("from ZAuthority").list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getIdByAction(String action) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("select id from ZAuthority a where a.action=?");
			query.setParameter(0, action);
			query.setMaxResults(1);
			int a = (int) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	public String getActionById(Integer Id) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("select action from ZAuthority where id = ?");
			query.setParameter(0, Id);
			query.setMaxResults(1);
			String action = (String) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return action;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

}
