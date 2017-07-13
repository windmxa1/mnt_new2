package org.dao.imp;

import java.util.List;

import org.dao.FunctionsDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Functions;
import org.model.Services;
import org.util.HibernateSessionFactory;

public class FunctionsDaoImp implements FunctionsDao{

//	public List<Functions> getErrorFunctions(Long itemid) {
//		// TODO Auto-generated method stub
//		try {
//			Session session = HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();
//			
//			String sql="from Functions f where f.triggerid=?";
//			Query query = session.createQuery(sql);
//			query.setParameter(0, itemid);
//			List<Functions> list = query.list();
//			
//			ts.commit();
//			HibernateSessionFactory.closeSession();
//			return list;
//		} catch (Exception e) {
//			// TODO: handle exception
//			return null;
//		}
//		
//	}
	public Functions getFunctionByTrigger(Long triggerid){
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from Functions where triggerid = ?");
			query.setParameter(0, triggerid);
			query.setMaxResults(1);
			Functions f = (Functions) query.uniqueResult();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return f;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
	}
	public Functions getFunctionByItem(Long itemid) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from Functions where itemid = ?");
			query.setParameter(0, itemid);
			query.setMaxResults(1);
			Functions f = (Functions) query.uniqueResult();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return f;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	

}
