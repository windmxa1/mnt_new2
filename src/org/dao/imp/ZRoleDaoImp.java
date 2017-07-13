package org.dao.imp;

import java.util.List;

import org.dao.ZRoleDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZRole;
import org.model.ZRoleAuthority;
import org.model.ZUserRole;
import org.util.HibernateSessionFactory;

public class ZRoleDaoImp implements ZRoleDao {

	@Override
	public boolean addRole(String role) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZRole rolein = new ZRole();
			rolein.setRole(role);

			session.save(rolein);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean deleteRole(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			ZRole role = (ZRole) session.load(ZRole.class, id);
			session.delete(role);
			
//			Query query = session.createQuery("from ZUserRole where roleid=?");
//			query.setParameter(0, id);
//			List<ZUserRole> urList = query.list();
//			for (ZUserRole r : urList) {
//				ZUserRole ur = (ZUserRole) session.load(ZUserRole.class,
//						r.getId());
//				session.delete(ur);
//			}
//			
//			String sql="";
//			Query query2 = session.createQuery("delete from ZRoleAuthority where roleid=?");
//			query2.setParameter(0, id);
//			query2.executeUpdate();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<ZRole> getRoleList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			List list = session.createQuery("from ZRole").list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean checkRole(String role) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from ZRole where role=?");
			query.setParameter(0, role);
			query.setMaxResults(1);
			
			ZRole r = (ZRole) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			if(r!=null)
				return false;
			else 
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public int getRoleMaxid() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("select Max(id) from ZRole");
			query.setMaxResults(1);
			
			int maxId = (int) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return maxId;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int getIdByRole(String role) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from ZRole where role=?");
			query.setParameter(0, role);
			query.setMaxResults(1);
			ZRole r = (ZRole) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return r.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public String getRoleById(int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from ZRole where id=?");
			query.setParameter(0, roleId);
			query.setMaxResults(1);
			ZRole r = (ZRole) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return r.getRole();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	@Override
	public boolean checkId(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from ZRole where id=?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			ZRole r = (ZRole) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			if(r!=null)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
