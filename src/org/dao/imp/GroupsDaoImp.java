package org.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dao.GroupsDao;
import org.dao.ItemsDao;
import org.dao.JfHostDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory;
import org.view.VHostGroup;
import org.view.VHostGroupId;

import com.opensymphony.xwork2.ActionContext;

public class GroupsDaoImp implements GroupsDao {

	public List<Object[]> getHostListByGroupid(Long groupid) {// 根据groupid种类获取设备列表
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Map<String, Object> map = ActionContext.getContext().getSession();
			
			System.out.println(map.get("groupId"));
			String sql = "";
			if(map.get("groupId")!=null){
				JfHostDao jfDao = new JfHostDaoImp();
				List<String> hostips = jfDao.getHostip((Long) map.get("groupId"));
				int i = 0;
				for(String host:hostips){
					if(i==0){ 
					sql = "and (h.host='"+host+"'";
						if(hostips.size()==1){
							sql = sql +") ";
						}
					}
					else if(i==hostips.size()-1){
						sql = sql + " or h.host='" + host+"') ";
					}else {
						sql = sql + " or h.host='" + host+"'";
					}
					i++;
				}
			}
			System.out.println(sql);
			Query query = session
					.createQuery("select h.hostid,h.host,h.name,g.groupid,g.name from Hosts h,Groups g,HostsGroups hg where h.hostid=hg.hostid "+sql+"and hg.groupid=g.groupid and h.status=0 and g.groupid=?");
			query.setParameter(0, groupid);
			List<Object[]> list = query.list();
			System.out.println(JSONArray.fromObject(list).toString());
			ts.commit();
			HibernateSessionFactory.closeSession();
			/*
			 * 通过组id获取所属所有设备 GroupsDao gDao = new GroupsDaoImp(); List<Object[]>
			 * li =gDao.getHostByGroupid((long) 8); for(Object o[] : li){
			 * for(Object a : o){ System.out.print(a.toString()+" "); }
			 * System.out.println("\n"); }
			 */
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Object[]> getGroupLists() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from Groups");
			List<Object[]> list = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			/*
			 * 通过组id获取所属所有设备 GroupsDao gDao = new GroupsDaoImp(); List<Object[]>
			 * li =gDao.getHostByGroupid((long) 8); for(Object o[] : li){
			 * for(Object a : o){ System.out.print(a.toString()+" "); }
			 * System.out.println("\n"); }
			 */
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List getgroupIdsByhostId(Long hostid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select  groupid from HostsGroups where hostid = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, hostid);

			List list = query.list();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List getHostListByGroupid02(Long groupid) {	//网络主机用
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			String sql="select h.hostid,h.host " +
							"from Hosts h,HostsGroups hg " +
							"where h.hostid=hg.hostid " +
								"and (h.flags<2 or h.flags>2) " +
								"and h.status=0 " +
								"and hg.groupid=? ";
			
			Query query = session.createQuery(sql);
			query.setParameter(0, groupid);
			List<Object[]> list = query.list();
			
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List getHostListByGroupid03(Long groupid) {	//监控主机用
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql="select h.hostid,h.host " +
					"from Hosts h,HostsGroups hg " +
					"where h.hostid=hg.hostid " +
						"and (h.flags<2 or h.flags>2) " +
						"and h.status=0 " +
						"and hg.groupid=? ";
	
			Query query = session.createQuery(sql);
			query.setParameter(0, groupid);
			List<Object[]> list = query.list();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			HibernateSessionFactory.closeSession();
		}
		/**		使用方式
		GroupsDao gDao = new GroupsDaoImp();
		
		List<Object[]> list = gDao.getHostListByGroupid03(10L);
		for(Object[] o:list){
			String id = o[0].toString();
			String ip = o[1].toString();
			System.out.println("id: "+id);
			System.out.println("ip: "+ip);
		}
		return list;
		 */
	}

	@Override
	public List getLiveHostByGroupid(Long groupid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select * from v_host_group where groupid=?";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, groupid);
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
