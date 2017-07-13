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
			Transaction ts = session.beginTransaction();
			String sql = "from HostInventory order by hostid";
			Query query = session.createQuery(sql);
			List<HostInventory> list = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public HostInventory getHostInventory(Long hostid) {
		try {
			Session session = (Session) HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			System.out.println("getHostInventory##hostid=" + hostid + "##");

			String sql = "from HostInventory where hostid = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, hostid);
			query.setMaxResults(1);
			HostInventory hi = (HostInventory) query.uniqueResult();

//			System.out.println(hi.getContact());
			ts.commit();
			HibernateSessionFactory.closeSession();

			return hi;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

//	public Map<String, String> getMapHostInvent(Long hostid) {
//		// TODO Auto-generated method stub
//		try {
//			Session session = (Session) HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();
//
//			// System.out.println("##hostid=" + hostid + "##");
//
//			String sql = "from HostInventory where hostid = ?";
//			Query query = session.createQuery(sql);
//			query.setParameter(0, hostid);
//			query.setMaxResults(1);
//			HostInventory hi = (HostInventory) query.uniqueResult();
//
//			Map<String, String> hostInventory = new HashMap<String, String>();
//			hostInventory.put("contact", hi.getContact());
//			hostInventory.put("deployment", hi.getDeployment_status());
//			hostInventory.put("location", hi.getLocation());
//			hostInventory.put("locationLat", hi.getLocationLat());
//			hostInventory.put("locationLon", hi.getLocationLon());
//			hostInventory.put("name", hi.getName());
//
//			ts.commit();
//			HibernateSessionFactory.closeSession();
//
//			return hostInventory;
//		} catch (Exception e) {
//			// TODO: handle exception
//			return null;
//		}
//		
//	}

}
