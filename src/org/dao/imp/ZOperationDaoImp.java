package org.dao.imp;

import org.dao.ZOperationDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZOperationTime;
import org.util.HibernateSessionFactory;

public class ZOperationDaoImp implements ZOperationDao {

	@Override
	public boolean saveOrUpdate(Integer userid, Long clock) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update ZOperationTime set lastOperationTime=? where userid=?");
			query.setParameter(0, clock);
			query.setParameter(1, userid);
			if (query.executeUpdate() == 0) {
				session.save(new ZOperationTime(userid, clock));
			}
			ts.commit();
			System.out.println("更新最新操作时间");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean checkOpeartion(Integer userid, Long clock,Long interval) {
		System.out.println("检测操作时间");
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select count(userid) from ZOperationTime where lastOperationTime>? and userid=?");
			query.setParameter(0, clock - interval);
			query.setParameter(1, userid);
			Long count = (Long) query.uniqueResult();
			if (count > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
