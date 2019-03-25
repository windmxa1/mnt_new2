package org.dao.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.dao.DeviceInfoDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory;
import org.util.HibernateSessionFactory2;
import org.view.VDcEvents;
import org.view.VDcEventsId;
import org.view.VItemValue;
import org.view.VItemValueId;

import com.opensymphony.xwork2.ActionContext;

public class DeviceDaoImp implements DeviceInfoDao {

	@Override
	public List<VDcEventsId> getDCEvents(String DcHost, Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from VDcEvents v where v.id.host = ? order by v.id.clock desc";
			Query query = session.createQuery(sql);
			query.setParameter(0, DcHost);
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
			List<VDcEvents> eventsList = query.list();
			// 修改门禁事件的状态为已读
			String sql3 = "update ZDcEvents set isRead = 1 where host = ?";
			Query query3 = session.createQuery(sql3);
			query3.setParameter(0, DcHost);
			query3.executeUpdate();

			List<VDcEventsId> list = new ArrayList<>();
			for (VDcEvents v : eventsList) {
				list.add(v.getId());
			}
			ts.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getDCEventsCount(String DcHost) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from ZDcEvents where host = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, DcHost);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VDcEventsId> readDCEvents() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql1 = "from VDcEvents v where v.id.isRead=0 order by v.id.host";
			Query query1 = session.createQuery(sql1);
			List<VDcEvents> list = query1.list();
			List<VDcEventsId> list2 = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (VDcEvents v : list) {
					String sql2 = "update ZHostConfig set notice = 1 where host=?";
					Query query2 = session.createQuery(sql2);
					query2.setParameter(0, v.getId().getHost());
					query2.executeUpdate();
					list2.add(v.getId());
				}
				String sql3 = "update ZDcEvents set isRead = 1 where isRead = 0";
				Query query3 = session.createQuery(sql3);
				query3.executeUpdate();
			}
			ts.commit();
			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VDcEventsId> getUnReadDCEvents() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql1 = "from VDcEvents v where v.id.isRead=0 order by v.id.host";
			Query query1 = session.createQuery(sql1);
			List<VDcEvents> list = query1.list();
			List<VDcEventsId> list2 = new ArrayList<>();
			for (VDcEvents v : list) {
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
	public List<Object[]> getJFError() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(*),groupname from v_last_data_jf where name = 'is_device_online' and value = 'ERROR' and host!='192.168.116.253' group by groupname order by groupname";
			SQLQuery query = session.createSQLQuery(sql);
			List<Object[]> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public String getNVRIP(String ipcIP) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select nvr_ip from z_nvr_ipc where ipc_ip=?";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, ipcIP);
			String nvrIP = (String) query.uniqueResult();
			return nvrIP;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public String getIPCIpByName(String ipcName) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select ipc_ip from z_nvr_ipc where ipc_name=?";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, ipcName);
			String ipcIP = (String) query.uniqueResult();
			return ipcIP;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Object[] getHKJFByIp(String host) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select groupname,type from v_last_data_jf where host=?";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, host);
			Object[] o = (Object[]) query.uniqueResult();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Object[]> getHKAlarmInfo() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select groupname,type from v_last_data_jf where name='is_device_online' and value = 'ERROR' order by clock desc";
			SQLQuery query = session.createSQLQuery(sql);
			List<Object[]> o = (List<Object[]>) query.list();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public String getHostByName(String name) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select id.host from VItemValue where id.deviceName = ? and id.value = 'OK' ";
			Query query = session.createQuery(sql);
			query.setParameter(0, name);
			query.setMaxResults(1);
			return (String) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VItemValueId> getHostList(String type, Integer start,
			Integer limit) {
		Map<String, Object> session1 = ActionContext.getContext().getSession();
		String groupName = (String) session1.get("groupName");
		// String groupName = "南开区委";
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = null;
			String sql = "";
			if (groupName != null) {
				sql = "from VItemValue where id.type = ? and id.groupname like ? ";
				query = session.createQuery(sql);
				query.setParameter(0, type);
				query.setParameter(1, "%" + groupName + "%");
				start = 0;
			} else {
				sql = "from VItemValue where id.type = ?";
				query = session.createQuery(sql);
				query.setParameter(0, type);
			}
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
			List<VItemValueId> list = new ArrayList<>();
			List<VItemValue> list2 = query.list();
			for (VItemValue v : list2) {
				v.getId().setGroupname(
						v.getId().getGroupname().replace("JF-", ""));
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
	public List<VItemValueId> getHostList1(String type, Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = null;
			String sql = "";
			sql = "from VItemValue where id.type = ?";
			query = session.createQuery(sql);
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
			List<VItemValueId> list = new ArrayList<>();
			List<VItemValue> list2 = query.list();
			for (VItemValue v : list2) {
				v.getId().setGroupname(
						v.getId().getGroupname().replace("JF-", ""));
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
	public List<Object[]> getRecordList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select v.groupname,v.device_name from v_item_value v where v.is_recording=1";
			SQLQuery query = session.createSQLQuery(sql);
			List<Object[]> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
