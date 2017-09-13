package org.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dao.GroupsDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory;
import org.view.VHostGroup;
import org.view.VHostGroupId;

import com.opensymphony.xwork2.ActionContext;

public class GroupsDaoImp implements GroupsDao {

	public List<Object[]> getGroupLists() {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session.createQuery("from Groups");
			List<Object[]> list = query.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	public List<Long> getgroupIdsByhostId(Long hostid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select  groupid from HostsGroups where hostid = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, hostid);

			List<Long> list = query.list();


			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Object[]> getHostListByGroupid02(Long groupid) { // 网络主机用
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "select h.hostid,h.host "
					+ "from Hosts h,HostsGroups hg "
					+ "where h.hostid=hg.hostid "
					+ "and (h.flags<2 or h.flags>2) " + "and h.status=0 "
					+ "and hg.groupid=? ";

			Query query = session.createQuery(sql);
			query.setParameter(0, groupid);
			List<Object[]> list = query.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Object[]> getHostListByGroupid03(Long groupid) { // 监控主机用
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select h.hostid,h.host "
					+ "from Hosts h,HostsGroups hg "
					+ "where h.hostid=hg.hostid "
					+ "and (h.flags<2 or h.flags>2) " + "and h.status=0 "
					+ "and hg.groupid=? ";

			Query query = session.createQuery(sql);
			query.setParameter(0, groupid);
			List<Object[]> list = query.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		/**
		 * 使用方式 GroupsDao gDao = new GroupsDaoImp();
		 * 
		 * List<Object[]> list = gDao.getHostListByGroupid03(10L); for(Object[]
		 * o:list){ String id = o[0].toString(); String ip = o[1].toString();
		 * System.out.println("id: "+id); System.out.println("ip: "+ip); }
		 * return list;
		 */
	}

	/**
	 * 获取地址段
	 */
	private String getIpRange(Long groupid) {
		String host = "";
		switch ("" + groupid) {
		case "8":
			host = "192.168.117";
			break;
		case "9":
			host = "192.168.116";
			break;
		case "10":
			break;
		default:
			break;
		}
		return host;
	}

	@Override
	public List<VHostGroupId> getLiveHostByGroupid(Long groupid, Integer start,
			Integer limit) {
		// Long time = System.currentTimeMillis();
		try {
			Map<String, Object> session1 = ActionContext.getContext()
					.getSession();
			String sql = "";
			Session session = HibernateSessionFactory.getSession();
			SQLQuery sqlQuery = null;
			String groupName = (String) session1.get("groupName");
			if (groupName != null) {
				sql = "select * from v_host_group where name like ? and host like ? order by hostid";
				sqlQuery = session.createSQLQuery(sql);
				sqlQuery.setParameter(0, "%" + groupName + "%");
				String host = getIpRange(groupid);
				sqlQuery.setParameter(1, "%" + host + "%");
			} else {
				sql = "select * from v_host_group where groupid=? order by hostid";
				sqlQuery = session.createSQLQuery(sql);
				sqlQuery.setParameter(0, groupid);
			}
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
			sqlQuery.addEntity(VHostGroup.class);
			List<VHostGroup> li = sqlQuery.list();

			List<VHostGroupId> list = new ArrayList<>();
			for (VHostGroup v : li) {
				list.add(v.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// System.out.println(System.currentTimeMillis() - time + "毫秒");
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getHostCountByGroupid(Long groupid) {
		try {
			String sql = "";
			Session session = HibernateSessionFactory.getSession();
			Query query = null;
			Map<String, Object> session1 = ActionContext.getContext()
					.getSession();
			String groupName = (String) session1.get("groupName");
			if (groupName != null) {
				sql = "select count(v.id.groupid) from VHostGroup v where v.id.name like ? and v.id.host like ?";
				query = session.createQuery(sql);
				query.setParameter(0, "%" + groupName + "%");
				String host = getIpRange(groupid);
				query.setParameter(1, "%" + host + "%");
			} else {
				sql = "select count(v.id.groupid) from VHostGroup v where v.id.groupid=?";
				query = session.createQuery(sql);
				query.setParameter(0, groupid);
			}
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
	public List<String> getGroupList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select name from Groups g where g.name like '%JF%' and name !='JF-宾馆路主局' ";
			Query query = session.createQuery(sql);
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
	public String getJFNameByHost(String host) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select id.name from VHostGroup where id.host= ? and id.name like '%JF%'";
			Query query = session.createQuery(sql);
			query.setParameter(0, host);
			query.setMaxResults(1);
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
