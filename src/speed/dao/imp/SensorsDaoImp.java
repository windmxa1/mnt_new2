package speed.dao.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.varia.StringMatchFilter;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory;
import org.util.HibernateSessionFactory2;
import org.view.VSwitchAlarm;
import org.view.VSwitchAlarmId;

import speed.dao.SensorsDao;
import speed.model.SensorConfig;
import speed.view.VRecord;
import speed.view.VRecordId;
import speed.view.VSensors;
import speed.view.VSensorsId;

import com.mysql.fabric.xmlrpc.base.Data;
import com.opensymphony.xwork2.ActionContext;

public class SensorsDaoImp implements SensorsDao {
	@Override
	public List<VRecordId> getAlarmRecord(Integer start, Integer limit,
			String location, String displayname) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "from VRecord v where 1=1 ";
			Query query;
			if (location != null) {
				sql += "and v.id.location = :location ";
			}
			if (displayname != null) {
				sql += "and v.id.name like :displayname ";
			}
			sql += "order by v.id.no desc";
			query = session.createQuery(sql);
			if (location != null) {
				query.setString("location", location);
			}
			if (displayname != null) {
				query.setString("displayname", displayname+"%");
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
	public Long getAlarmCount(String location, String displayname) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select count (v.id.no) from VRecord v where 1=1 ";
			Query query;
			if (location != null) {
				sql += "and v.id.location = :location ";
			}
			if (displayname != null) {
				sql += "and v.id.name like :displayname ";
			}
			query = session.createQuery(sql);
			if (location != null) {
				query.setString("location", location);
			}
			if (displayname != null) {
				query.setString("displayname", displayname+"%");
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
	public List<VRecordId> getAlarmRecord(Integer start, Integer limit,
			String start_time, String end_time, String location, String displayname) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select v.* from v_record v where time > ? and time< ?";
			SQLQuery query;
			if (location != null) {
				sql += "and v.location = :location ";
			}
			if (displayname != null) {
				sql += "and v.name like :displayname ";
			}
			sql += "order by v.no desc";
			query = session.createSQLQuery(sql).addEntity(VRecord.class);
			if (location != null) {
				query.setString("location", location);
			}
			if (displayname != null) {
				query.setString("displayname", displayname+"%");
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
			query.setParameter(0, start_time);
			query.setParameter(1, end_time);

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
	public Long getRecordCount(String start_time, String end_time,
			String location, String displayname) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select count(v.no) as total from v_record v where time > ? and time< ?";

			if (location != null) {
				sql += "and v.location = :location ";
			}
			if (displayname != null) {
				sql += "and v.name like :displayname ";
			}
			SQLQuery query = session.createSQLQuery(sql);
			if (location != null) {
				query.setString("location", location);
			}
			if (displayname != null) {
				query.setString("displayname", displayname+"%");
			}

			query.setParameter(0, start_time);
			query.setParameter(1, end_time);
			query.setMaxResults(1);
			query.addScalar("total", Hibernate.LONG);
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
	public boolean deleteRecord(String start_time, String end_time,
			String location, String displayname) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select max(no),min(no) from v_record where time between ? and ? ";
			if (location != null) {
				sql += "and location = :location ";
			}
			if (displayname != null) {
				sql += "and name like :displayname ";
			}
			SQLQuery query = session.createSQLQuery(sql);
			if (location != null) {
				query.setString("location", location);
			}
			if (displayname != null) {
				query.setString("displayname", displayname);
			}
			query.setParameter(0, start_time);
			query.setParameter(1, end_time);
			query.setMaxResults(1);
			Object[] num = (Object[]) query.uniqueResult();
			Query query2 = session
					.createQuery("delete from Record where no between ? and ?");
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
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public boolean deleteAll() {
		try {
			Session session = HibernateSessionFactory2.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "truncate table record ";
			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

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
			// System.out.println("groupName:"+groupName);
			if (groupName != null) {
				sql = " from VSensors v where v.id.type=? and v.id.location like ? order by v.id.displayname asc";
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
	public List<VSensorsId> getSensorsByType1(Integer start, Integer limit,
			Short type) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = " from VSensors v where v.id.type=? order by location";
			Query query = session.createQuery(sql);
			query.setParameter(0, type);
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
	public Long getSensorsByType1Count(Short type) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select count(v.id.no) from VSensors v where v.id.type=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, type);
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
			Long startTime = System.currentTimeMillis();
			Session session = HibernateSessionFactory2.getSession();
			String sql = "from VRecord r where r.id.sensorindex=?";
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
			System.out.println(System.currentTimeMillis() - startTime);
			List<VRecord> list = query.list();
			System.out.println(System.currentTimeMillis() - startTime);
			List<VRecordId> list2 = new ArrayList<>();
			for (VRecord v : list) {
				list2.add(v.getId());
			}
			System.out.println(System.currentTimeMillis() - startTime);
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
			String sql = "select v.location,v.displayname,v.message from v_sensors v,sensor_config sc where v.status=1 and v.no = sc.sensor_id and sc.available = 1 order by v.time desc";
			SQLQuery query = session.createSQLQuery(sql);
			List<Object[]> list = query.list();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public List<Object[]> getAirConditionList() {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select v.location,v.displayname,v.sensorvalue from v_sensors v,sensor_config sc where v.status=0 and type=224 and v.no = sc.sensor_id and sc.available = 1 order by v.time desc";
			SQLQuery query = session.createSQLQuery(sql);
			List<Object[]> list = query.list();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

	@Override
	public Long getRecordIn5Min(String datetime) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select count(*) from Record where time <?";
			Query query = session.createQuery(sql);
			query.setParameter(0, datetime);
			return (Long) query.uniqueResult();
		} catch (Exception e) {
			return 1L;
		} finally {
			HibernateSessionFactory2.closeSession();
		}
	}

}
