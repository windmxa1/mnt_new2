package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.EventsDao;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory;
import org.view.VEvents;
import org.view.VEventsId;

public class EventsDaoImp implements EventsDao {

	@Override
	public Long getCount(String name, String type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(e.eventid) as total from v_events e where 1=1 ";
			SQLQuery query;
			if (name != null) {
				sql += "and e.name like :name ";
			}
			if (type != null) {
				sql += "and e.type = :type ";
			}
			query = session.createSQLQuery(sql);
			if (name != null) {
				query.setString("name", "%"+name+"%");
			}
			if (type != null) {
				query.setString("type", type+"离线");
			}
			query.setMaxResults(1);
			Long count = (Long) query.addScalar("total", Hibernate.LONG)
					.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VEventsId> getList(Integer start, Integer limit, String name,
			String type) {
		try {
			// Long startTime = System.currentTimeMillis();
			Session session = HibernateSessionFactory.getSession();
			String sql = "select * from v_events where 1=1 ";
			SQLQuery query;
			if (name != null) {
				sql += "and name like :name ";
			}
			if (type != null) {
				sql += "and type = :type ";
			}
			query = session.createSQLQuery(sql);
			if (name != null) {
				query.setString("name", "%"+name+"%");
			}
			if (type != null) {
				query.setString("type", type+"离线");
			}
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setFirstResult(start);
			query.addEntity(VEvents.class);
			List<VEvents> li = query.list();
			List list = new ArrayList<>();
			for (VEvents l : li) {
				list.add(l.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VEventsId> getListByClock(Integer start, Integer limit,
			Long start_clock, Long end_clock, String name, String type) {
		try {
			Long startTime = System.currentTimeMillis();
			Session session = HibernateSessionFactory.getSession();
			String sql = "select e.* from v_events e where e.clock between ? and ? and e.clock!=? ";
			SQLQuery query;
			if (name != null) {
				sql += "and e.name like :name ";
			}
			if (type != null) {
				sql += "and e.type = :type ";
			}
			query = session.createSQLQuery(sql);
			query.setParameter(0, start_clock.intValue());
			query.setParameter(1, end_clock.intValue());
			query.setParameter(2, end_clock.intValue());
			if (name != null) {
				query.setString("name", "%"+name+"%");
			}
			if (type != null) {
				query.setString("type", type+"离线");
			}
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setFirstResult(start);
			System.out.println(System.currentTimeMillis() - startTime);
			query.addEntity(VEvents.class);
			List<VEvents> li = query.list();
			List list = new ArrayList<>();
			for (VEvents a : li) {
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
	public Long getCountByClock(Long start_clock, Long end_clock, String name,
			String type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(*) as total from v_events e where e.clock between ? and ? and e.clock!=? ";
			SQLQuery query;
			if (name != null) {
				sql += "and e.name like :name ";
			}
			if (type != null) {
				sql += "and e.type = :type ";
			}
			query = session.createSQLQuery(sql);
			query.setParameter(0, start_clock.intValue());
			query.setParameter(1, end_clock.intValue());
			query.setParameter(2, end_clock.intValue());
			if (name != null) {
				query.setString("name", "%"+name+"%");
			}
			if (type != null) {
				query.setString("type", type+"离线");
			}
			query.setMaxResults(1);
			Long count = (Long) query.addScalar("total",Hibernate.LONG).uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean deleteByClock(Long start_clock, Long end_clock,String name,String type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select max(eventid),min(eventid) from v_events where (clock between ? and ?) and clock!=? ";
			if (name != null) {
				sql += "and name like :name ";
			}
			if (type != null) {
				sql += "and type = :type ";
			}
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, start_clock.intValue());
			query.setParameter(1, end_clock.intValue());
			query.setParameter(2, end_clock.intValue());
			if (name != null) {
				query.setString("name", "%"+name+"%");
			}
			if (type != null) {
				query.setString("type", type+"离线");
			}
			query.setMaxResults(1);
			Object[] num = (Object[]) query.uniqueResult();
			SQLQuery query2 = session
					.createSQLQuery("delete from events where eventid between ? and ?");
			query2.setParameter(0, num[0]);
			query2.setParameter(1, num[1]);
			query2.executeUpdate();
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
			String sql = "delete from events";
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

}
