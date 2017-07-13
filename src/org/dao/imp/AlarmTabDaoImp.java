package org.dao.imp;

import java.util.List;

import org.dao.AlarmTabDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.AlarmTab;
import org.tool.ChangeTime;
import org.util.HibernateSessionFactory;

public class AlarmTabDaoImp implements AlarmTabDao {

	@Override
	public boolean insert(AlarmTab alarmTab) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			AlarmTab alarmTab2 = new AlarmTab();
			alarmTab2.setAlarmType(alarmTab.getAlarmType());
			alarmTab2.setClock(alarmTab.getClock());
			alarmTab2.setDeviceType(alarmTab.getDeviceType());
			alarmTab2.setHost(alarmTab.getHost());
			alarmTab2.setHostid(alarmTab.getHostid());
			alarmTab2.setConfirm(alarmTab.getConfirm());
			alarmTab2.setTime(alarmTab.getTime());
			session.save(alarmTab2);
			ts.commit();
			HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<AlarmTab> getAlarmTab() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from AlarmTab");
			List<AlarmTab> list = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public Long countAlarm(String host) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("select count(id) as count from AlarmTab where host=? and confirm = 0");
			query.setParameter(0, host);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return count;
		} catch (Exception e) {
			return null;
		}
	}

	public Long countCurrentAlarm(Long hostid) {
		try {
			String Current = ChangeTime.TimeStamp2Date(ChangeTime.timeStamp(),
					"YYYY-MM");
			Integer CurrentClock = Integer.parseInt(ChangeTime.date2TimeStamp(
					Current, "YYYY-MM"));

			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select Count(id) as count from AlarmTab where time>? and hostid=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, CurrentClock);
			query.setParameter(1, hostid);
			query.setMaxResults(1);
			Long CountCurrentAlarm = (Long) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return CountCurrentAlarm;
		} catch (Exception e) {
			return null;
		}

	}

	public List<AlarmTab> getUndealTab() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from AlarmTab where confirm = 0 ";
			Query query = session.createQuery(sql);
			List<AlarmTab> list = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public List<AlarmTab> getDealTab() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from AlarmTab where confirm = 1 ";
			Query query = session.createQuery(sql);
			List<AlarmTab> list = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

}
