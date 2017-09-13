package org.dao.imp;

import java.util.List;

import org.dao.ZHostConfigDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZHostConfig;
import org.util.HibernateSessionFactory;

public class ZHostConfigDaoImp implements ZHostConfigDao {

	@Override
	public Long saveOrUpdate(ZHostConfig hostConfig) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from ZHostConfig where host = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, hostConfig.getHost());
			ZHostConfig hostConfig2 = (ZHostConfig) query.uniqueResult();
			if (hostConfig2 == null) {
				id = (Long) session.save(hostConfig);
			} else {
				hostConfig2.setAvailable(hostConfig.getAvailable());
				hostConfig2.setNotice(hostConfig.getNotice());
				session.update(hostConfig2);
			}
			ts.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean init() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql1 = "truncate table z_host_config";
			SQLQuery sqlQuery = session.createSQLQuery(sql1);
			sqlQuery.executeUpdate();

			String sql2 = "select h.host from Hosts h,HostsGroups hg,Groups g where h.hostid=hg.hostid and hg.groupid= g.groupid and g.name like '%海康%' ";
			Query query = session.createQuery(sql2);
			List<String> list = query.list();
			for (String host : list) {
				session.save(new ZHostConfig(host));
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
	public ZHostConfig getHostConfig(String host) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from ZHostConfig where host = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, host);
			ZHostConfig hostConfig = (ZHostConfig) query.uniqueResult();
			return hostConfig;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean updateAvailable(String host, Integer available) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update ZHostConfig hc set hc.available=? where hc.host=? ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, available);
			query.setParameter(1, host);
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
}
