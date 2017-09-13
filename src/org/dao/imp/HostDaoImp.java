package org.dao.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dao.HostDao;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Hosts;
import org.util.HibernateSessionFactory;
import org.view.VHostGroup;

public class HostDaoImp implements HostDao {

	public List<Hosts> getHostList() {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "from Hosts where status = 0 and (flags < 2 or flags > 2) and host like '%192%'";
			Query query = session.createQuery(sql);

			List<Hosts> list = (List<Hosts>) query.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		/*
		 * //打印所有设备 ip 和 hostid号 HostDao hDao = new HostDaoImp(); List<Hosts>
		 * list = hDao.getHostList(); // Iterator<Hosts> iterator =
		 * list.iterator(); // while(iterator.hasNext()){ //
		 * System.out.println("hostid: "
		 * +iterator.next().getHostid()+"host: "+iterator.next().getHost()); //
		 * } for(Hosts h:list){
		 * System.out.println(h.getHostid()+" "+h.getHost()); }
		 */
	}

	public void getTypenameByHostid() {
		// TODO Auto-generated method stub
	}

	public Hosts getHostByHostid(Long hostid) {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "from Hosts where hostid=?";

			Query query = session.createQuery(sql);
			query.setParameter(0, hostid);
			query.setMaxResults(1);
			Hosts h = (Hosts) query.uniqueResult();

			return h;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Hosts getHostByHostip(String nvrHost) {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "from Hosts where host=?";

			Query query = session.createQuery(sql);
			query.setParameter(0, nvrHost);
			query.setMaxResults(1);
			Hosts h = (Hosts) query.uniqueResult();

			return h;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List getHostLive() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select * from v_host_group";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VHostGroup.class);
			List<VHostGroup> li = sqlQuery.list();

			List list = new ArrayList<>();
			for (VHostGroup v : li) {
				list.add(v.getId());
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
	public String getHostGroup(Long hostid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select g.name from Groups g,HostsGroups hg where g.groupid = hg.groupid and hg.hostid = ? and g.name like '%JF%'";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			query.setParameter(0, hostid);
			String groupName = (String) query.uniqueResult();
			return groupName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}

	}

	@Override
	public Long getDeviceErrorCount(Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			switch (type) {
			case 8:
				sql = "select count(vld.hostid) as num from v_last_data vld,z_host_config hc where groupname like '%门禁%' and value != 'OK' and name = 'is_device_online' and hc.host = vld.host and hc.available=1 ";
				break;
			case 9:
				sql = "select count(vld.hostid) as num from v_last_data vld,z_host_config hc where groupname like '%摄像头%' and value != 'OK' and name = 'is_device_online' and hc.host = vld.host and hc.available=1 ";
				break;
			case 10:
				sql = "select count(vld.hostid) as num from v_last_data vld,z_host_config hc where groupname like '%录像机%' and value != 'OK' and name = 'is_device_online' and hc.host = vld.host and hc.available=1 ";
				break;
			default:
				break;
			}
			SQLQuery query = session.createSQLQuery(sql);
			query.setMaxResults(1);
			query.addScalar("num", Hibernate.LONG);
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
	public Set<String> getAlarmJf(Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			switch (type) {
			case 8:
				sql = "select vld.groupname from v_last_data_jf vld,z_host_config hc where vld.host like '%192.168.117%' and value = 'ERROR' and name = 'is_device_online' and hc.host = vld.host and hc.available=1 ";
				break;
			case 9:
				sql = "select vld.groupname from v_last_data_jf vld,z_host_config hc where vld.host like '%192.168.116%' and vld.groupname != 'JF-宾馆路主局'  and value = 'ERROR' and name = 'is_device_online' and hc.host = vld.host and hc.available=1 ";
				break;
			case 10:
				sql = "select vld.groupname from v_last_data_jf vld,z_host_config hc where groupname like '%主局%' and value ='ERROR' and name = 'is_device_online' and hc.host = vld.host and hc.available=1 ";
				break;
			default:
				break;
			}
			SQLQuery query = session.createSQLQuery(sql);
			Set<String> set = new HashSet<>();
			set.addAll(query.list());
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
