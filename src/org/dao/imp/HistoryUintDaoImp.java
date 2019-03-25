package org.dao.imp;

import org.dao.HistoryUintDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.HistoryUint;
import org.util.HibernateSessionFactory;

public class HistoryUintDaoImp implements HistoryUintDao{
	@Override
	public HistoryUint getItemsValueByItemId(Long itemid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			String sql="from HistoryUint hu where hu.id.itemid=? order by hu.id.clock desc";
			Query query = session.createQuery(sql);
			query.setParameter(0, itemid);
			query.setMaxResults(1);
			
			HistoryUint historyUint = (HistoryUint) query.uniqueResult();
				return historyUint;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

}
