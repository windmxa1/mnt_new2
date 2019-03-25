package org.dao.imp;

import java.util.List;

import org.dao.ItemsDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.model.Items;
import org.util.HibernateSessionFactory;

public class ItemsDaoImp implements ItemsDao {

	public List<Items> getItemsByHostid(Long hostid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from Items where hostid=? order by name");
			query.setParameter(0, hostid);
			List<Items> list = query.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	// -------------------------没有关闭Session的dao接口----------------------------------
	public Items getItemByItemid(Long itemid) {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session.createQuery("from Items i where itemid = ?");
			query.setParameter(0, itemid);
			query.setMaxResults(1);
			Items items = (Items) query.uniqueResult();

			return items;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Items getItemByName(Long hostid, String name) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from Items i where i.name=? and i.hostid=?");
			query.setParameter(0, name);
			query.setParameter(1, hostid);
			query.setMaxResults(1);
			Items items = (Items) query.uniqueResult();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
