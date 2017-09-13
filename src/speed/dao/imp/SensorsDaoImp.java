package speed.dao.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory2;

import speed.dao.SensorsDao;
import speed.model.SensorConfig;
import speed.view.VRecord;
import speed.view.VRecordId;
import speed.view.VSensors;
import speed.view.VSensorsId;

import com.opensymphony.xwork2.ActionContext;

public class SensorsDaoImp implements SensorsDao {

	@Override
	public List<VSensorsId> getSensorsByType2(Integer start, Integer limit,
			Short type) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "";
			Map<String, Object> session1 = ActionContext.getContext()
					.getSession();
			String groupName = (String) session1.get("groupName");
			// String groupName = null;
			Query query = null;
//			System.out.println("groupName:"+groupName);
			if (groupName != null) {
				sql = " from VSensors v where v.id.type=? and v.id.location like ? order by v.id.no desc";
				query = session.createQuery(sql);
				query.setParameter(0, type);
				query.setParameter(1, "%" + groupName + "%");
				start = 0;
			} else {
				sql = " from VSensors v where v.id.type=? order by v.id.no desc";
				query = session.createQuery(sql);
				query.setParameter(0, type);
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
			List<VSensors> list = query.list();
			List<VSensorsId> list2 = new ArrayList<>();
			for (VSensors v : list) {
				list2.add(v.getId());
			}
			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public Long getSensorsByType2Count(Short type) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "";
			Map<String, Object> session1 = ActionContext.getContext()
					.getSession();
			Query query = null;
			String groupName = (String) session1.get("groupName");
			if (groupName != null) {
				sql = "select count(v.id.no) from VSensors v where v.id.type=? and v.id.location like ?";
				query = session.createQuery(sql);
				query.setParameter(0, type);
				query.setParameter(1, "%" + groupName + "%");
			} else {
				sql = "select count(v.id.no) from VSensors v where v.id.type=?";
				query = session.createQuery(sql);
				query.setParameter(0, type);
			}
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public boolean checkAlarm(Short type) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select count(v.id.no) from VSensors v,SensorConfig sc where v.id.type=? and v.id.status=1 and v.id.no = sc.sensorId and sc.available = 1";
			Query query = session.createQuery(sql);
			query.setParameter(0, type);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public List<VRecordId> getHistory(Integer sensorIndex, Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "from VRecord r where r.id.sensorindex=? order by r.id.time desc";
			Query query = session.createQuery(sql);
			query.setParameter(0, sensorIndex);
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
			List<VRecord> list = query.list();
			List<VRecordId> list2 = new ArrayList<>();
			for (VRecord v : list) {
				list2.add(v.getId());
			}
			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public Long getHistoryCount(Integer sensorIndex) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select count(r.id.no) from VRecord r where r.id.sensorindex=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, sensorIndex);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public boolean init() {
		try {
			Session session = HibernateSessionFactory2.getSession();
			Transaction ts = session.beginTransaction();
			String sql1 = "truncate table sensor_config";
			SQLQuery sqlQuery = session.createSQLQuery(sql1);
			sqlQuery.executeUpdate();

			String sql2 = "select no from Sensors ";
			Query query = session.createQuery(sql2);
			List<Integer> list = query.list();
			for (Integer id : list) {
				session.save(new SensorConfig(id));
			}
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public boolean updateAvailable(Integer sensorIndex, Integer available) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update SensorConfig sc set sc.available=? where sc.sensorId=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, available);
			query.setParameter(1, sensorIndex);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public Set<String> getAlarmJF(Short type) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select v.id.location from VSensors v,SensorConfig sc where v.id.type=? and v.id.status=1 and v.id.no = sc.sensorId and sc.available = 1";
			Query query = session.createQuery(sql);
			query.setParameter(0, type);
			Set<String> list = new HashSet<String>();
			list.addAll(query.list());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public List<Object[]> getJFError() {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select sum(status),location from v_sensors group by location order by location;";
			SQLQuery query = session.createSQLQuery(sql);
			List<Object[]> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public Set<String> getAlarmJF() {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select v.id.location from VSensors v,SensorConfig sc where v.id.status=1 and type!=224 and v.id.no = sc.sensorId and sc.available = 1";
			Query query = session.createQuery(sql);
			Set<String> list = new HashSet<String>();
			list.addAll(query.list());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public Object[] getSensorInfoByNo(Integer sensorIndex) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select v.id.location,v.id.displayname from VSensors v where v.id.no = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, sensorIndex);
			Object[] list = (Object[]) query.uniqueResult();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public List<Object[]> getAlarmInfo() {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select v.location,v.displayname from v_sensors v,sensor_config sc where v.status=1 and type!=224 and v.no = sc.sensor_id and sc.available = 1 order by v.time desc";
			SQLQuery query = session.createSQLQuery(sql);
			List<Object[]> list = query.list();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

}
