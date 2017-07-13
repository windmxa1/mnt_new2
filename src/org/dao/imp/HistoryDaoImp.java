package org.dao.imp;

import java.util.List;

import org.dao.HistoryDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.model.History;
import org.util.HibernateSessionFactory;

public class HistoryDaoImp implements HistoryDao {

	@Override
	public History getItemsValueByItemId(Long itemid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			String sql = "from History h where h.id.itemid=? order by h.id.clock desc";
			Query query = session.createQuery(sql);
			query.setParameter(0, itemid);
			query.setMaxResults(1);
			
			History history = (History) query.uniqueResult();
			
			HibernateSessionFactory.closeSession();
			
			if(history!=null)
				return history;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List getItemsValueByItemId_10time(Long itemid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			String sql = "select h.id.clock,h.id.value from History h where h.id.itemid=? order by h.id.clock desc";
			Query query = session.createQuery(sql);
			query.setParameter(0, itemid);
			query.setFirstResult(0);
			query.setMaxResults(10);
//			List<History> hList = query.list();
			List<Object[]> list = query.list();
			
			ts.commit();
			org.util.HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
