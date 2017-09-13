package org.dao.imp;

import org.dao.ZConnectCtlDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZConnectCtl;
import org.util.HibernateSessionFactory;

public class ZConnectCtlDaoImp implements ZConnectCtlDao {

	@Override
	public Boolean saveOrUpdate(Integer userid, Integer type, String threadId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update ZConnectCtl set threadId=? where userid=? and type= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, threadId);
			query.setParameter(1, userid);
			query.setParameter(2, type);
			if (query.executeUpdate() > 0) {
				// System.out.println("更新成功1");
			} else {
				ZConnectCtl connectCtl = new ZConnectCtl(userid, threadId,type);
				session.save(connectCtl);
				// System.out.println("更新成功2");
			}
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public ZConnectCtl getConnect(Integer userid, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from ZConnectCtl where userid=? and type=?");
			query.setParameter(0, userid);
			query.setParameter(1, type);
			query.setMaxResults(1);
			ZConnectCtl connectState = (ZConnectCtl) query.uniqueResult();
			return connectState;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean deleteAll() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "truncate table z_connect_ctl";
			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public String getThreadId(Integer userid, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select threadId from ZConnectCtl where userid=? and type=?");
			query.setParameter(0, userid);
			query.setParameter(1, type);
			query.setMaxResults(1);
			String threadId = (String) query.uniqueResult();
			return threadId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
