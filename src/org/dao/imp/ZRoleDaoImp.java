package org.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.dao.ZRoleDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.model.ZRole;
import org.util.HibernateSessionFactory;

public class ZRoleDaoImp implements ZRoleDao {

	@Override
	public boolean addRole(String role, final int[] authorityId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZRole rolein = new ZRole();
			rolein.setRole(role);
			final Integer roleid = (Integer) session.save(rolein);
			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					PreparedStatement stmt = connection
							.prepareStatement("insert into z_role_authority(roleid,authorityid) values(?,?)");
					connection.setAutoCommit(false);
					for (Integer aId : authorityId) {
						stmt.setInt(1, roleid);
						stmt.setInt(2, aId);
						stmt.addBatch();
					}
					stmt.executeBatch();
				}
			});
			ts.commit();
			session.flush();
			session.clear();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean deleteRole(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZRole role = (ZRole) session.load(ZRole.class, id);
			session.delete(role);

			Query query = session
					.createQuery("delete from ZRoleAuthority where roleid = ?");
			query.setParameter(0, id);
			query.executeUpdate();
			Query query2 = session
					.createQuery("delete from ZUserRole where roleid = ?");
			query2.setParameter(0, id);
			query2.executeUpdate();

			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<ZRole> getRoleList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			List<ZRole> list = session.createQuery("from ZRole").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean checkRole(String role) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZRole where role=?");
			query.setParameter(0, role);
			query.setMaxResults(1);

			ZRole r = (ZRole) query.uniqueResult();
			if (r != null)
				return false;
			else
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}

	@Override
	public int getRoleMaxid() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select Max(id) from ZRole");
			query.setMaxResults(1);

			int maxId = (int) query.uniqueResult();
			return maxId;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Integer getIdByRole(String role) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select id from ZRole where role=?");
			query.setParameter(0, role);
			query.setMaxResults(1);
			Integer id = (Integer) query.uniqueResult();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public String getRoleById(int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZRole where id=?");
			query.setParameter(0, roleId);
			query.setMaxResults(1);
			ZRole r = (ZRole) query.uniqueResult();
			return r.getRole();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean checkId(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZRole where id=?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			ZRole r = (ZRole) query.uniqueResult();
			if (r != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<String> getRoleNameList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			List<String> list = session.createQuery("select role from ZRole")
					.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
