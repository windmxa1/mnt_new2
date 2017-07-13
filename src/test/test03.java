package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tool.ChangeTime;
import org.util.HibernateSessionFactory;

public class test03 {
	public static void main(String[] args) throws Exception {
//		try {
			System.out.println(ChangeTime.timeStamp1());
//			System.out.println(date.getTime());
			
			
//			Current = Year+"-"+Month+"-"+"01 00:00:00";
//			Integer CurrentClock = Integer.parseInt(ChangeTime.date2TimeStamp(
//					Current, "YYYY-MM"));
//			System.out.println(Current);
//			System.out.println(CurrentClock);
			System.out.println(ChangeTime.timeStamp());
			
			
			
//			Session session = HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();
//
//			Query query = session
//					.createQuery("select COUNT(e.eventid) as alerts FROM Hosts h,Items i,HostsGroups hg,Groups g,Functions f,Triggers t,Events e where i.hostid = h.hostid and hg.hostid = h.hostid and hg.groupid = g.groupid and f.itemid = i.itemid and t.triggerid = f.triggerid and e.objectid = t.triggerid and (h.flags  < 2 or h.flags > 2)  and e.source = 0 and t.priority >2 and e.value = 1  and e.acknowledged < 2 and h.hostid=? and e.clock>? and g.name!='Discovered hosts' GROUP BY h.host ORDER BY  h.host DESC");
//			query.setParameter(0, (long)10211);
//			query.setParameter(1, CurrentClock);
//			query.setMaxResults(1);
//
//			Long TotalErr = (Long) query.uniqueResult();
//			System.out.println(TotalErr);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
	}
}
