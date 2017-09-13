package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ZSwitchDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory;
import org.view.VSwitch;
import org.view.VSwitchAlarm;
import org.view.VSwitchAlarmId;
import org.view.VSwitchId;

public class ZSwitchDaoImp implements ZSwitchDao {

	@Override
	public List<VSwitchId> getSwitchList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select s.* from v_switch s ";
			SQLQuery query = session.createSQLQuery(sql);
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
			query.addEntity(VSwitch.class);
			List<VSwitch> list = query.list();
			List<VSwitchId> list2 = new ArrayList<>();
			for (VSwitch v : list) {
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

	@Override
	public List<String> getAlarmDevice() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select s.name from v_switch s where s.ack = 0 and s.value = 'ERROR' and s.available=1 group by s.name";
			SQLQuery query = session.createSQLQuery(sql);
			List<String> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean AckAlarm(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update z_switch_alarm s set s.ack=1 where s.ack = 0 and s.value = 'ERROR' and switch_id=? ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, id);
			query.executeUpdate();
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
	public boolean updateAvailable(Integer available, Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update z_switch s set s.available=? where id=? ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, available);
			query.setParameter(1, id);
			query.executeUpdate();
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
	public List<VSwitchAlarmId> getSwitchAlarmHistory(Integer start,
			Integer limit, Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select s.* from v_switch_alarm s where switch_id=? order by id desc";
			SQLQuery query = session.createSQLQuery(sql);
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
			query.addEntity(VSwitchAlarm.class);
			query.setParameter(0, id);
			List<VSwitchAlarm> list = query.list();
			List<VSwitchAlarmId> list2 = new ArrayList<>();
			for (VSwitchAlarm v : list) {
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

	@Override
	public Long getSwitchAlarmCount(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(v.id.id) from VSwitchAlarm v where v.id.switchId=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
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
	public List<VSwitchAlarmId> getSwitchAlarmHistory(Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VSwitchAlarm v order by v.id.id desc";
			Query query = session.createQuery(sql);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			List<VSwitchAlarm> list = query.list();
			List<VSwitchAlarmId> list2 = new ArrayList<>();
			for (VSwitchAlarm v : list) {
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

	@Override
	public Long getSwitchAlarmCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count (v.id.id) from VSwitchAlarm v ";
			Query query = session.createQuery(sql);
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
	public List<VSwitchAlarmId> getSwitchAlarmHistory(Integer start,
			Integer limit, String start_time, String end_time) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VSwitchAlarm v where v.id.time between ? and ? order by v.id.id desc";
			Query query = session.createQuery(sql);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setParameter(0, start_time);
			query.setParameter(1, end_time);

			List<VSwitchAlarm> list = query.list();
			List<VSwitchAlarmId> list2 = new ArrayList<>();
			for (VSwitchAlarm v : list) {
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

	@Override
	public Long getSwitchAlarmCount(String start_time, String end_time) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count (v.id.id) from VSwitchAlarm v where v.id.time between ? and ?";
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
	public boolean deleteSwitchAlarm(Long start_clock, Long end_clock) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from ZSwitchAlarm where (clock between ? and ?) and clock!=? and id>6";
			Query query = session.createQuery(sql);
			query.setParameter(0, Integer.parseInt("" + start_clock));
			query.setParameter(1, Integer.parseInt("" + end_clock));
			query.setParameter(2, Integer.parseInt("" + end_clock));
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
	public String getSwitchName(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select name from ZSwitch where id= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			String name = (String) query.uniqueResult();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
