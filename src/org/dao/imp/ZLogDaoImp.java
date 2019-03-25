package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ZLogDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZLog;
import org.util.HibernateSessionFactory;
import org.view.VLog;
import org.view.VLogId;

public class ZLogDaoImp implements ZLogDao {

	@Override
	public boolean addLog(ZLog l) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			ZLog log = null;
			if (l.getMsg() != null) {
				log = new ZLog(l.getUsername(), l.getOperation(), l.getTime(),
						l.getMsg());
			} else {
				log = new ZLog(l.getUsername(), l.getOperation(), l.getTime());
			}
			session.save(log);
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
	public List getLogList(Integer start, Integer limit) {
		try {
			// Long startTime = System.currentTimeMillis();
			Session session = HibernateSessionFactory.getSession();
			String sql = "select * from v_log ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VLog.class);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				sqlQuery.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				sqlQuery.setMaxResults(limit);
			}
			sqlQuery.setFirstResult(start);

			// System.out.println(System.currentTimeMillis() - startTime);
			List<VLog> li = sqlQuery.list();
			List list = new ArrayList<>();
			for (VLog l : li) {
				list.add(l.getId()); // opertion表一定要有对应的name与日志中的operation对应,不然到这里会有空指针异常
			}
			// System.out.println(System.currentTimeMillis() - startTime);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session.createQuery("select count(id) from ZLog");
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VLogId> getLogList(Integer start, Integer limit,
			String start_time, String end_time) {
		try {
			Long startTime = System.currentTimeMillis();
			Session session = HibernateSessionFactory.getSession();
			String sql = "select * from v_log where (vtime between ? and ?) ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VLog.class);
			sqlQuery.setParameter(0, start_time);
			sqlQuery.setParameter(1, end_time);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				sqlQuery.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				sqlQuery.setMaxResults(limit);
			}
			sqlQuery.setFirstResult(start);
			System.out.println(System.currentTimeMillis() - startTime);

			List<VLog> li = sqlQuery.list();
			List list = new ArrayList<>();
			for (VLog a : li) {
				list.add(a.getId());
			}
			System.out.println(System.currentTimeMillis() - startTime);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getLogCount(String start_time, String end_time) {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "select count(*) from VLog v where (v.id.vtime between ? and ?)  ";
			Query query = session.createQuery(sql);
			query.setParameter(0, start_time);
			query.setParameter(1, end_time);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean deleteLog(Long start_clock, Long end_clock) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from ZLog where (time between ? and ?) and time!=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, start_clock);
			query.setParameter(1, end_clock);
			query.setParameter(2, end_clock);
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
	public boolean deleteAll() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "truncate table z_log";
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
	public List<VLogId> getAllLog() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VLog ";
			Query query = session.createQuery(sql);
			List<VLog> list = query.list();
			List<VLogId> list2 = new ArrayList<>();
			for (VLog v : list) {
				list2.add(v.getId());
			}
			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
