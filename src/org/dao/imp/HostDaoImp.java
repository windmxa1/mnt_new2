package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.HostDao;
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
			Transaction ts = session.beginTransaction();

			String sql = "from Hosts where status = 0 and (flags < 2 or flags > 2) and host like '%192%'";
			Query query = session.createQuery(sql);

			List<Hosts> list = (List<Hosts>) query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			query.setParameter(0,hostid );
			query.setMaxResults(1);
			Hosts h = (Hosts) query.uniqueResult();
			
			HibernateSessionFactory.closeSession();
			
			return h;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Hosts getHostByHostip(String nvrHost) {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "from Hosts where host=?";
			
			Query query = session.createQuery(sql);
			query.setParameter(0,nvrHost );
			query.setMaxResults(1);
			Hosts h = (Hosts) query.uniqueResult();
			
			HibernateSessionFactory.closeSession();
			
			return h;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			for(VHostGroup v:li){
				list.add(v.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			HibernateSessionFactory.closeSession();
		}
	}


}
