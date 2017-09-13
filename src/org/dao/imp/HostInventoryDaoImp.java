package org.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.HostInventoryDao;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.model.HostInventory;
import org.util.HibernateSessionFactory;

public class HostInventoryDaoImp implements HostInventoryDao {

	public List<HostInventory> getHostInventoryList() {
		try {
			Session session = (Session) HibernateSessionFactory.getSession();
			String sql = "from HostInventory order by hostid";
			Query query = session.createQuery(sql);
			List<HostInventory> list = query.list();

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public HostInventory getHostInventory(Long hostid) {
		try {
			Session session = (Session) HibernateSessionFactory.getSession();

			String sql = "from HostInventory where hostid = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, hostid);
			query.setMaxResults(1);
			HostInventory hi = (HostInventory) query.uniqueResult();


			return hi;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}

	}

}
