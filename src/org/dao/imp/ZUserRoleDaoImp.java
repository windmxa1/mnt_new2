package org.dao.imp;

import java.util.List;

import org.dao.ZUserRoleDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZRole;
import org.model.ZUser;
import org.model.ZUserRole;
import org.util.HibernateSessionFactory;

public class ZUserRoleDaoImp implements ZUserRoleDao {
	@Override
	public boolean addUR(int userId, int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZUserRole ur = new ZUserRole();
			ur.setRoleid(roleId);
			ur.setUserid(userId);
			
			session.save(ur);
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<ZUserRole> getURList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from ZUserRole");
			List<ZUserRole> list = query.list();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getRByU(int userId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from ZUserRole where userid=?");
			query.setParameter(0, userId);
			query.setMaxResults(1);
			ZUserRole ur = (ZUserRole) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			if(ur!=null){
				int roleid = ur.getRoleid();
				return roleid;
			}
			else 
				return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public boolean updateUR(int userId,int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("update ZUserRole ur set ur.roleid=? where ur.userid=?");
			query.setParameter(0, roleId);
			query.setParameter(1, userId);
			query.executeUpdate();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean deleteUR(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			ZUserRole ur = (ZUserRole) session.load(ZUserRole.class, id);
			session.delete(ur);
				
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public int getIdByUserid(int userId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("select id from ZUserRole z where userid=?");
			query.setParameter(0, userId);
			query.setMaxResults(1);
			int a = (int) query.uniqueResult();
					
			ts.commit();		
			HibernateSessionFactory.closeSession();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public List getIdByRoleid(int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("select id from ZUserRole z where roleid=?");
			query.setParameter(0, roleId);
//			query.setMaxResults(1);
//			int a = (int) query.uniqueResult();
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
	public boolean checkRoleid(int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from ZUserRole where roleid=?");
			query.setParameter(0, roleId);
			query.setMaxResults(1);
			ZUserRole ur = (ZUserRole) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			if(ur!=null)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean checkUserid(int userId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from ZUserRole where userid=?");
			query.setParameter(0, userId);
			query.setMaxResults(1);
			ZUserRole ur = (ZUserRole) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			if(ur!=null)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}