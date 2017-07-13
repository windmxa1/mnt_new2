package org.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dao.EventsDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Events;
import org.tool.ChangeTime;
import org.util.HibernateSessionFactory;

public class EventsDaoImp implements EventsDao {

	public List<Map<String, String>> getErrEventsByHostId(Long hostid) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			List<Map<String, String>> list2 = new ArrayList<>();
			Query query = session
					.createQuery("select e.eventid ,t.description,g.name,t.priority,h.host,h.hostid,h.available,h.status,e.clock FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority >2 and t.priority <5 and e.value = 1  and e.acknowledged < 2 and g.name!='Discovered hosts' and h.hostid=? ORDER BY  h.available DESC");
			query.setParameter(0, hostid);
			List<Object[]> list = query.list();
			for (Object[] o : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("alerts", o[0].toString());
				map.put("description", o[1].toString());
				map.put("name", o[2].toString());
				map.put("priority", o[3].toString());
				map.put("host", o[4].toString());
				map.put("hostid", o[5].toString());
				map.put("available", o[6].toString());
				map.put("status", o[7].toString());
				map.put("clock", o[8].toString());
				list2.add(map);
			}

			ts.commit();
			HibernateSessionFactory.closeSession();

			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Map<String, String>> getFailureListWithoutTime() {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			List<Map<String, String>> list2 = new ArrayList<>();

			Query query = session
					.createQuery("select COUNT(e.eventid) as alerts,t.description,g.name,t.priority,h.host,h.hostid,h.available,h.status FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority =5and e.value = 1  and e.acknowledged < 2 and g.name!='Discovered hosts' GROUP BY h.host ORDER BY  h.available DESC");
			List<Object[]> list = query.list();

			for (Object[] o : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("alerts", o[0].toString());
				map.put("description", o[1].toString());
				map.put("name", o[2].toString());
				map.put("priority", o[3].toString());
				map.put("host", o[4].toString());
				map.put("hostid", o[5].toString());
				map.put("available", o[6].toString());
				map.put("status", o[7].toString());
				list2.add(map);
			}

			ts.commit();
			HibernateSessionFactory.closeSession();

			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, String>> getDCSingleList() {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			List<Map<String, String>> list2 = new ArrayList<>();

			Query query = session
					.createQuery("select e.eventid ,t.description,g.name,t.priority,h.host,h.hostid,h.available,h.status,e.clock FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority =5and e.value = 1  and e.acknowledged < 2 and g.name='海康门禁' ORDER BY  h.host DESC");
			List<Object[]> list = query.list();

			for (Object[] o : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("eventid", o[0].toString());
				map.put("description", o[1].toString());
				map.put("name", o[2].toString());
				map.put("priority", o[3].toString());
				map.put("host", o[4].toString());
				map.put("hostid", o[5].toString());
				map.put("available", o[6].toString());
				map.put("status", o[7].toString());
				map.put("clock", o[8].toString());
				list2.add(map);
			}

			ts.commit();
			HibernateSessionFactory.closeSession();

			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, String>> getIPCSingleList() {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			List<Map<String, String>> list2 = new ArrayList<>();

			Query query = session
					.createQuery("select e.eventid ,t.description,g.name,t.priority,h.host,h.hostid,h.available,h.status,e.clock FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority =5and e.value = 1  and e.acknowledged < 2 and g.name='海康网络摄像头' ORDER BY  h.host DESC");
			List<Object[]> list = query.list();

			for (Object[] o : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("eventid", o[0].toString());
				map.put("description", o[1].toString());
				map.put("name", o[2].toString());
				map.put("priority", o[3].toString());
				map.put("host", o[4].toString());
				map.put("hostid", o[5].toString());
				map.put("available", o[6].toString());
				map.put("status", o[7].toString());
				map.put("clock", o[8].toString());
				list2.add(map);
			}

			ts.commit();
			HibernateSessionFactory.closeSession();

			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, String>> getNVRSingleList() {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			List<Map<String, String>> list2 = new ArrayList<>();

			Query query = session
					.createQuery("select e.eventid ,t.description,g.name,t.priority,h.host,h.hostid,h.available,h.status,e.clock FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority =5and e.value = 1  and e.acknowledged < 2 and g.name='海康录像机' ORDER BY  h.host DESC");
			List<Object[]> list = query.list();

			for (Object[] o : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("eventid", o[0].toString());
				map.put("description", o[1].toString());
				map.put("name", o[2].toString());
				map.put("priority", o[3].toString());
				map.put("host", o[4].toString());
				map.put("hostid", o[5].toString());
				map.put("available", o[6].toString());
				map.put("status", o[7].toString());
				map.put("clock", o[8].toString());
				list2.add(map);
			}

			ts.commit();
			HibernateSessionFactory.closeSession();

			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long getTotalErr(Long hostid) {
		// TODO Auto-generated method stub
		try {
			Integer CurrentClock = Integer.parseInt(ChangeTime.timeStamp1());
			
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("select COUNT(e.eventid) as alerts FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority >2 and e.value = 1  and e.acknowledged < 2 and h.hostid=? and e.clock>? and g.name!='Discovered hosts' GROUP BY h.host ORDER BY  h.host DESC");
			query.setParameter(0, hostid);
			query.setParameter(1, CurrentClock);
			query.setMaxResults(1);

			Long TotalErr = (Long) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();
			System.out.println(TotalErr);
			return TotalErr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long getTotalFail(Long hostid) {
		// TODO Auto-generated method stub
		try {
			Integer CurrentClock = Integer.parseInt(ChangeTime.timeStamp1());

			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("select COUNT(e.eventid) as alerts FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority =5 and e.value = 1  and e.acknowledged < 2 and h.hostid=? and e.clock>? and g.name!='Discovered hosts' GROUP BY h.host ORDER BY  h.host DESC");
			query.setParameter(0, hostid);
			query.setParameter(1, CurrentClock);
			query.setMaxResults(1);

			Long TotalErr = (Long) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();

			return TotalErr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Map<String, String>> getFailEventsByHostId(Long hostid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			List<Map<String, String>> list2 = new ArrayList<>();
			Query query = session
					.createQuery("select e.eventid ,t.description,g.name,t.priority,h.host,h.hostid,h.available,h.status,e.clock FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority =5 and e.value = 1  and e.acknowledged < 2 and g.name!='Discovered hosts' and h.hostid=? ORDER BY  h.available DESC");
			query.setParameter(0, hostid);
			List<Object[]> list = query.list();
			for (Object[] o : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("alerts", o[0].toString());
				map.put("description", o[1].toString());
				map.put("name", o[2].toString());
				map.put("priority", o[3].toString());
				map.put("host", o[4].toString());
				map.put("hostid", o[5].toString());
				map.put("available", o[6].toString());
				map.put("status", o[7].toString());
				map.put("clock", o[8].toString());
				list2.add(map);
			}

			ts.commit();
			HibernateSessionFactory.closeSession();

			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Events> getUndealList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select e.acknowledged,h.hostid,h.host,e.clock,t.description FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority <5 and t.priority >2 and e.value = 1  and e.acknowledged = 0 and g.name!='海康录像机' and g.name!='Discovered hosts' ORDER BY  h.host DESC  ";
			Query query = session.createQuery(sql);
			List<Events> list = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Events> getDealList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select e.acknowledged,h.hostid,h.host,e.clock,t.description FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority <5 and t.priority >2 and e.value = 1  and e.acknowledged = 1 and g.name!='海康录像机' and g.name!='Discovered hosts' ORDER BY  h.host DESC ";
			Query query = session.createQuery(sql);
			List<Events> list = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
}
