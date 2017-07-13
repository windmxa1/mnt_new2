package org.dao.imp;

import java.util.List;

import org.dao.JfHostDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory;

public class JfHostDaoImp implements JfHostDao {

	public List<String> getHostip(Long serviceid) {
		try {
			Session session = HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();
			String sql  =  "select host from JfHost where jfSerivce = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, serviceid);
			List<String> list = query.list();
			
//			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
