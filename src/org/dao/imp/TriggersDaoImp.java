package org.dao.imp;

import java.util.List;

import org.dao.TriggersDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Triggers;
import org.util.HibernateSessionFactory;

public class TriggersDaoImp implements TriggersDao {


	@Override
	public List getTriggersByHostId(Long hostId) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "select t.triggerid from Triggers t,Functions f,Items i,Hosts h where t.triggerid = f.triggerid and f.itemid = i.itemid and i.hostid = h.hostid and h.hostid=? group by t.triggerid order by t.triggerid";
			Query query = session.createQuery(sql);
			query.setParameter(0, hostId);
			List list = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
