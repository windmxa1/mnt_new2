package org.dao.imp;

import java.util.List;

import org.dao.ZIPCRecordingDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZIpcRecording;
import org.util.HibernateSessionFactory;

public class ZIPCRecordingDaoImp implements ZIPCRecordingDao {

	@Override
	public Boolean updateRecordStatus(String host, Integer isRecording) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update ZIpcRecording set isRecording = ? where host = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, isRecording);
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

	@Override
	public Boolean init() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql2 = "select h.host from Hosts h,HostsGroups hg,Groups g where h.hostid=hg.hostid and hg.groupid= g.groupid and g.name like '%海康%' ";
			Query query = session.createQuery(sql2);
			List<String> list = query.list();
			String sql3 = "select host from z_ipc_recording";
			SQLQuery query3 = session.createSQLQuery(sql3);
			List<String> list2 = query3.list();
			list.removeAll(list2);

			for (String host : list) {
				session.save(new ZIpcRecording(host));
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
	public Boolean init1() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql2 = "update z_ipc_recording set is_recording = 0 ";
			SQLQuery query = session.createSQLQuery(sql2);
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
	public List<String> getRecordingList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select ni.ipc_name from z_ipc_recording zir , z_nvr_ipc ni where zir.is_recording =1 and ni.ipc_ip = zir.host";
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

}
