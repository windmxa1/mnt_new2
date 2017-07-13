package speed.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.util.HibernateSessionFactory2;

import speed.dao.SensorsDao;
import speed.view.VSensors;
import speed.view.VSensorsId;

public class SensorsDaoImp implements SensorsDao {

	@Override
	public List<VSensorsId> getAllSensors(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "from VSensors";
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
	public List<VSensorsId> getSensorsByType(Integer start, Integer limit,
			Integer type) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "select s.* from vsensors s where s.No in (select c.SensorNo from class c where Parent=?)";
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
			query.setParameter(0, type);
			query.addEntity(VSensors.class);
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
	public List<VSensorsId> getSensorsByType2(Integer start, Integer limit,
			Short type) {
		try {
			Session session = HibernateSessionFactory2.getSession();
			String sql = "from VSensors where type=?";
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
			query.setParameter(0, type);
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

}
