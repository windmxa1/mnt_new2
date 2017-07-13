package org.dao.imp;

import java.util.List;

import org.dao.ZRoleAuthorityDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZAuthority;
import org.model.ZRole;
import org.model.ZRoleAuthority;
import org.model.ZUserRole;
import org.util.HibernateSessionFactory;

public class ZRoleAuthorityDaoImp implements ZRoleAuthorityDao{

	@Override
	public boolean addRA(int roleId, int AuthorityId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			ZRoleAuthority ra = new ZRoleAuthority();
			ra.setRoleid(roleId);
			ra.setAuthorityid(AuthorityId);
			session.save(ra);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<ZRoleAuthority> getRAList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from ZRoleAuthority");
			List<ZRoleAuthority> list = query.list();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List getAListByR(int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("select authorityid from ZRoleAuthority ra where ra.roleid=?");
			query.setParameter(0, roleId);
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
	public boolean deleteRA(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			ZRoleAuthority ra = (ZRoleAuthority) session.load(ZRoleAuthority.class, id);
			session.delete(ra);
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List getIdListByR(int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("select id from ZRoleAuthority ra where ra.roleid=?");
			query.setParameter(0, roleId);
			List list = query.list();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
