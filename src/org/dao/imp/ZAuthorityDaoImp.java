package org.dao.imp;

import java.util.List;

import org.dao.ZAuthorityDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZAuthority;
import org.util.HibernateSessionFactory;

public class ZAuthorityDaoImp implements ZAuthorityDao {
	@Override
	public List<ZAuthority> getAList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			SQLQuery query = session
					.createSQLQuery("select * from z_authority");
			query.addEntity(ZAuthority.class);
			List<ZAuthority> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public int getIdByAction(String action) {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("select id from ZAuthority a where a.action=?");
			query.setParameter(0, action);
			query.setMaxResults(1);
			int a = (int) query.uniqueResult();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	public String getActionById(Integer Id) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("select action from ZAuthority where id = ?");
			query.setParameter(0, Id);
			query.setMaxResults(1);
			String action = (String) query.uniqueResult();

			return action;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}

	}

	@Override
	public List<ZAuthority> getAList(Integer roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			SQLQuery query = session
					.createSQLQuery("select a.* from z_authority a,z_role_authority ra where ra.roleid=? and ra.authorityid = a.id");
			query.setParameter(0, roleId);
			query.addEntity(ZAuthority.class);
			List<ZAuthority> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<String> getActionListByUser(Integer userId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select a.action from ZAuthority a,VUser u,ZRoleAuthority ra where ra.roleid=u.id.roleid and ra.authorityid = a.id and u.id.id=?");
			query.setParameter(0, userId);
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
	public List<String> getAliasList(Integer roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			SQLQuery query = session
					.createSQLQuery("select a.alias from z_authority a,z_role_authority ra where ra.roleid=? and ra.authorityid = a.id");
			query.setParameter(0, roleId);
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
